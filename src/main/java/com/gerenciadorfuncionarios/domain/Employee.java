package com.gerenciadorfuncionarios.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.gerenciadorfuncionarios.util.DateUtil;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.Locale;

public class Employee extends Person {
    private final BigDecimal salary;
    private final String function;
    private EmployeeStatus status;

    @JsonCreator
    public Employee(@JsonProperty("name") String name,
                    @JsonProperty("dateBirth") LocalDate dateBirth,
                    @JsonProperty("salary") BigDecimal salary,
                    @JsonProperty("function") String function,
                    @JsonProperty("status") EmployeeStatus status) {
        super(name, dateBirth);
        this.salary = salary;
        this.function = function;
        this.status = (status != null ? status : EmployeeStatus.ACTIVE);
    }


    public Employee withSalary(BigDecimal newSalary){
        return new Employee(this.getName(), this.getDateBirth(), newSalary, this.getFunction(), this.status);
    }

    public Employee withStatus(EmployeeStatus newStatus){
        return  new Employee(this.getName(), this.getDateBirth(), this.getSalary(),this.getFunction(), newStatus);
    }


    @Override
    public String toString(){
        String formatadDate = DateUtil.formatterBR(this.getDateBirth());

        String salaryFormatad = NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(this.getSalary());

        return "\nFuncionário{" +
                "Nome = '" + this.getName() + '\'' +
                ", Data de Nascimento = '" + formatadDate + '\'' +
                ", Salário = " + salaryFormatad +
                " , Função = '" + this.getFunction() + '\'' +
                ", Status = '" + this.getStatus() + '\'' +
                '}';
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public String getFunction() {
        return function;
    }
    public EmployeeStatus getStatus() {
        return status;
    }
    public void setStatus(EmployeeStatus status) {
        this.status = status;
    }
}
