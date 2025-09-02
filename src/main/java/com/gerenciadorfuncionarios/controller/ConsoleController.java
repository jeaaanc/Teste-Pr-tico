package com.gerenciadorfuncionarios.controller;

import com.gerenciadorfuncionarios.Main;
import com.gerenciadorfuncionarios.domain.Employee;
import com.gerenciadorfuncionarios.service.EmployeeService;
import com.gerenciadorfuncionarios.menu.MenuUI;
import com.gerenciadorfuncionarios.util.InputsUtil;
import com.gerenciadorfuncionarios.view.EmployeeView;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.text.NumberFormat;
import java.util.*;

public class ConsoleController {
    private final EmployeeService employeeService = new EmployeeService();
    private final EmployeeView employeeView = new EmployeeView();

    public void begin(){
        InputStream inputStream = Main.class.getResourceAsStream("/employee.json");
        if (inputStream == null) {
            System.out.println("Arquivo employee.json não encontrado!");
            return;
        }
        try (InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8)) {
            employeeService.uploadFileJson(reader);
            System.out.println("\nDados iniciais carregados.\n");
        } catch (IOException e) {
            throw new RuntimeException("Erro inesperado TESTE" + e);
        }

    }

    public void start(Scanner sc, MenuUI menuUI){
        int option = 0;

        while (option != 10){
            menuUI.showOptionsMenu();
            option = InputsUtil.readInt(sc, "Escolha uma Opção");

            switch (option) {
                case 1:
                    System.out.println("\n---Lista de Funcionários---");
                    List<Employee> employees = employeeService.searchActiveEmployees();//---
                    employeeView.displayAll(employees);
                    break;
                case 2:
                    System.out.println("\nInativar Funcionário");

                    String name = InputsUtil.readString(sc, "Digite o nome para remover:");
                    if (employeeService.removeEmployee(name)){
                        System.out.println("Funcionario Inativado com sucesso!");
                    }else {
                        System.out.println("Funcionário não encontrado.");
                    }
                    break;
                case 3:
                    System.out.println("\nAplicando aumento de 10%.");
                    employeeService.salaryIncrease(new BigDecimal("0.10"));
                    employeeView.displayAll(employeeService.searchActiveEmployees());//---
                    break;
                case 4:
                    System.out.println("\nMapeamento por função");

                    Map<String, List<Employee>> employeesByFunction = employeeService.groupByFunction();
                    employeeView.displayGroupedByFunction(employeesByFunction);
                    break;
                case 5:
                    System.out.println("Aniversariantes de Outubro (10) e Dezembro (12): ");

                    List<Employee> birthday = employeeService.searchBirthDayMonth(Arrays.asList(10, 12));
                    employeeView.displayAll(birthday);
                    break;
                case 6:
                    Optional<Employee> oldest = employeeService.lookingForOldEmployee();
                    employeeView.displayOldestEmployee(oldest);
                    break;
                case 7:
                    System.out.println("\nLista de Funcionários por ordem Alfabética: ");
                    List<Employee> orderedList = employeeService.search_AllForNameOrderly();
                    employeeView.displayAll(orderedList);
                    break;
                case 8:
                    System.out.println("\nTotal dos Salários: ");
                    BigDecimal wageTotal = employeeService.calculateTotalSalaries();
                    String wageFormated = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"))
                            .format(wageTotal);
                    System.out.println("Total: " + wageFormated);
                    break;
                case 9:
                    employeeView.displaySalariesInMinimumWages(employeeService.searchActiveEmployees());//---
                    break;
                default:
                    System.out.println("!!!!Opão invalida. 1 a 10");
                    break;
            }

        }
        System.out.println("Processo finalizado");
    }

}
