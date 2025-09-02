package com.gerenciadorfuncionarios.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtil {

    private static  final DateTimeFormatter dateTimeFormatterBR = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private DateUtil (){}

    public static String formatterBR(LocalDate date){
        if (date == null){
            throw new IllegalArgumentException("A data esta vazia");
        }
        return date.format(dateTimeFormatterBR);
    }
}
