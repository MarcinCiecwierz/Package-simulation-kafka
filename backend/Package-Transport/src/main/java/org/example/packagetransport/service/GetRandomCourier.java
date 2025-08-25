package org.example.packagetransport.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class GetRandomCourier {

    private static Map<Integer, String> couriers = Map.of(
            0, "Jan Kowalski",
            1, "Anna Nowak",
            2, "Piotr Wiśniewski",
            3, "Katarzyna Zielińska",
            4, "Marek Lewandowski",
            5, "Agnieszka Wójcik"
    );

    public static String getCourier() {
        Random rand = new Random();
        return couriers.get(rand.nextInt(couriers.size()));
    }

}
