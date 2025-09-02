package com.gerenciadorfuncionarios.view;

import com.gerenciadorfuncionarios.domain.Employee;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class EmployeeView {

    public void displayAll(List<Employee> employees) {
        if (employees == null || employees.isEmpty()){
            System.out.println("Nenhum funcionário cadastrado.");
        }else {
            employees.forEach(System.out::println);
        }
        System.out.println("----------------------------------\n");
    }

    public void displayGroupedByFunction(Map<String, List<Employee>> mapEmployee){
        System.out.println("Funcionários Agrupados por função: ");
        mapEmployee.forEach((function, list) ->{
            System.out.println("\nFunção: " + function);
            list.forEach(System.out::println);
            System.out.println("-----------------------------------------------------------------------------------");
        });
    }

    public void displayOldestEmployee(Optional<Employee> employeeOpt){
        System.out.println("Funcionario com maior idade: ");
        employeeOpt.ifPresent(emp -> {
            System.out.println("Nome: " + emp.getName() + ", Idade: " + emp.getIdade());
        });
    }

    public void displaySalariesInMinimumWages(List<Employee> employees){
        System.out.println("Salários em relação ao Salário Mínimo (R$1212.00)");
        BigDecimal wageMin = new BigDecimal("1212.00");
        for (Employee emp : employees){
            BigDecimal howManySM = emp.getSalary().divide(wageMin, 2, RoundingMode.HALF_UP);
            System.out.println("Nome: " + emp.getName() + ", Ganha: " + howManySM + "Salários mínimos.");
        }
    }

}
