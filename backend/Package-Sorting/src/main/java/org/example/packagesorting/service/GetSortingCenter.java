package org.example.packagesorting.service;

import java.util.Map;

public class GetSortingCenter {

    private static final Map<Character, String> sortingCenters = Map.of(
            '0', "Warszawa",
            '1', "Olsztyn",
            '2', "Lublin",
            '3', "Kraków",
            '4', "Katowice",
            '5', "Wrocław",
            '6', "Poznań",
            '7', "Szczecin",
            '8', "Gdańsk",
            '9', "Łódź"
    );

    public static String getSortingCenter(String postalCode) {

        char c = postalCode.charAt(0);

        return sortingCenters.get(c);
    }

}
