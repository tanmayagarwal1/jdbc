package com.uhg.opioid.loader;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Auditor {
    public static String dateProvider(){
        LocalDateTime myDate = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-dd-MM-hh-mm-ss");
        String MyDate = myDate.format(formatter);
        return MyDate;
    }

    public static String dateStamp(){
        LocalDateTime myDate = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-dd-MM-hh-mm-ss");
        String MyDate = myDate.format(formatter);
        return MyDate;
    }
}
