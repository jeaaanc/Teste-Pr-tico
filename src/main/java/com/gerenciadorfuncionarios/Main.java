package com.gerenciadorfuncionarios;

import com.gerenciadorfuncionarios.controller.ConsoleController;
import com.gerenciadorfuncionarios.menu.MenuUI;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        MenuUI menuUI = new MenuUI();
        ConsoleController consoleController = new ConsoleController();

        consoleController.iniciar();
        consoleController.start(scanner, menuUI);


        scanner.close();
    }
}