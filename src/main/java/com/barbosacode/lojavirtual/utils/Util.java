package com.barbosacode.lojavirtual.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Util {



    public static String getLocalTime(){
        LocalDateTime localDateTime = LocalDateTime.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        return localDateTime.format(formatter);

    }
}
