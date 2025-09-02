package com.gerenciadorfuncionarios.util;

import java.util.Scanner;

public class InputsUtil {

    public static int readInt(Scanner sc, String mensagem){
        while (true){
            try {
                System.out.println(mensagem);
                return Integer.parseInt(sc.nextLine());
            }catch (NumberFormatException e){
                System.out.println("Entrada inválida. Digite um número Inteiro. " + e);
            }
        }
    }

    public static String readString(Scanner sc, String mensagem){
        System.out.println(mensagem);
        return sc.nextLine();
    }
}
