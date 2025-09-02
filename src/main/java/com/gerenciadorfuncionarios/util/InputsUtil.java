package com.gerenciadorfuncionarios.util;

import java.util.Scanner;

public class InputsUtil {

    public static int readInt(Scanner sc, String message){
        while (true){
            try {
                System.out.println(message);
                return Integer.parseInt(sc.nextLine());
            }catch (NumberFormatException e){
                System.out.println("Entrada inválida. Digite um número Inteiro. " + e);
            }
        }
    }

    public static String readString(Scanner sc, String message){
        System.out.println(message);
        return sc.nextLine();
    }
}
