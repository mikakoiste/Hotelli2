package com.example.mika.hotelli2;

import java.util.ArrayList;

/**
 * Created by Mervi on 18.1.2018.
 */

public class Player {

    int player_id = 0;

    public Player(String _name)
    {
        String name = _name;
        int id = player_id;
        player_id++;
        ArrayList<Hotel> owned_hotels = new ArrayList<>();
        int money = 0;
    }
}
