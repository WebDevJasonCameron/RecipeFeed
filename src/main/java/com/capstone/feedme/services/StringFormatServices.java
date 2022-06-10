package com.capstone.feedme.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class StringFormatServices {

    public String dateFormat(LocalDateTime localDateTime){

        System.out.println("Before : " + localDateTime);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyy HH:mm");

        String outputDateTimeString = localDateTime.format(formatter);

        System.out.println("After : " + outputDateTimeString);

        return outputDateTimeString;

    }

}
