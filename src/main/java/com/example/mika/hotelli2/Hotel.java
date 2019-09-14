package com.example.mika.hotelli2;

import java.util.ArrayList;

/**
 * Created by Mervi on 31.12.2017.
 */

public class Hotel {

    String _name = "";
    int _entrance_price = 0;
    int _rent[][] = {
            {100, 200, 300, 400, 500, 600},
            {200, 400, 600, 800, 1000, 1200}
    };
    int _build_prices[] = {1500, 800};
    int buildings = 0;
    ArrayList<Integer> plots = new ArrayList<Integer>();
    ArrayList<Integer> entrances = new ArrayList<Integer>();
    int owner = 0;  // 0 = not owned
    public Hotel(String name, int entrance_price)
    {
        _name = name;
        _entrance_price = entrance_price;
    }

}
