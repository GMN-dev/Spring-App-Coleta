package com.stefanini.app.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Formatter{
    public static LocalDate setStringToLocalDate(String date){
        DateTimeFormatter formatterToLocalDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate formatted = LocalDate.parse(date, formatterToLocalDate);
        return formatted;
    }
}
