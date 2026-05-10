package com.zonetimedata;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class zonetimedate {
    public static void main(String[] args) {
         ZonedDateTime now = ZonedDateTime.now();
        System.out.println(now);
         int hour = now.getHour();
        System.out.println(hour);
        for (String availableZoneId : ZoneId.getAvailableZoneIds()) {
            System.out.println(availableZoneId);
        }

    }
}
