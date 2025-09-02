package com.gerenciadorfuncionarios.service;

import com.gerenciadorfuncionarios.domain.Employee;

import com.gerenciadorfuncionarios.domain.EmployeeStatus;
import com.gerenciadorfuncionarios.util.LocalDateAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.Reader;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EmployeeService {
    private List<Employee> allEmployee = new ArrayList<>();

    public void uploadFileJson(Reader reader) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();

        Type employeeList = new TypeToken<List<Employee>>() {}.getType();
        List<Employee> employeeJson = gson.fromJson(reader, employeeList);

        if (employeeJson != null) {
            List<Employee> normalized = employeeJson.stream()
                    .map(emp -> emp.getStatus() == null ? emp.withStatus(EmployeeStatus.ACTIVE) : emp)
                    .collect(Collectors.toList());
            this.allEmployee.addAll(normalized);
        }
    }

    public boolean removeEmployee(String name){
        for (Employee emp : allEmployee){
            if (emp.getName().equalsIgnoreCase(name) && emp.getStatus() == EmployeeStatus.ACTIVE){
                emp.setStatus(EmployeeStatus.INACTIVE);
                return true;
            }
        }
        return false;
    }

    public void salaryIncrease(BigDecimal percentage){
        BigDecimal factorIncrease = BigDecimal.ONE.add(percentage);

        List<Employee> newListEmployee = this.allEmployee.stream()
                .map(emp -> {
                    if (emp.getStatus() == EmployeeStatus.ACTIVE) {
                        BigDecimal newSalary = emp.getSalary().multiply(factorIncrease);
                        return emp.withSalary(newSalary);
                    }else {
                        return emp;
                    }
                })
                .collect(Collectors.toList());
        this.allEmployee = newListEmployee;
    }

    public Map<String, List<Employee>> groupByFunction(){
        return getActiveStream()
                .collect(Collectors.groupingBy(Employee::getFunction));
    }

    public List<Employee> searchBirthDayMonth(List<Integer> months){
        return getActiveStream()
                .filter(emp -> months.contains(emp.getDateBirth().getMonthValue()))
                .collect(Collectors.toList());
    }

    public Optional<Employee> lookingForOldEmployee(){
        return getActiveStream()
                .min(Comparator.comparing(Employee::getDateBirth));
    }

    public List<Employee> search_AllForNameOrderly(){
        return getActiveStream()
                .sorted(Comparator.comparing(Employee::getName))
                .collect(Collectors.toList());
    }

    public BigDecimal calculateTotalSalaries(){
        return getActiveStream()
                .map(Employee::getSalary)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public List<Employee> searchActiveEmployees(){
        return getActiveStream().collect(Collectors.toList());
    }

    private Stream<Employee> getActiveStream(){
        return this.allEmployee.stream()
                .filter(emp -> emp.getStatus() == EmployeeStatus.ACTIVE);
    }
}
