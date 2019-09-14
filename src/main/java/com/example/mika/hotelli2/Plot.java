package com.example.mika.hotelli2;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Mervi on 31.12.2017.
 */

public class Plot {

    public enum Type {
        BUY,
        BUILD,
        BUILD_FREE,
        FREE_ENTRANCE,
        NO_ACTION
    }

    public ArrayList<Hotel> available_hotels()
    {
        ArrayList<Hotel> hotelList = new ArrayList<>();
        Iterator<Hotel> hotelIterator = this.hotels.iterator();
        while (hotelIterator.hasNext()) {
            Hotel _hotel = hotelIterator.next();
            if (_hotel.owner == 0)
            {
                hotelList.add(_hotel);
            }
        }
        return hotelList;
    }

    public ArrayList<String> available_hotels_str()
    {
        ArrayList<String> hotelList = new ArrayList<>();
        Iterator<Hotel> hotelIterator = this.hotels.iterator();
        while (hotelIterator.hasNext()) {
            Hotel _hotel = hotelIterator.next();
            if (_hotel.owner == 0)
            {
                hotelList.add(_hotel._name);
            }
        }
        return hotelList;
    }

    // List of hotels which are adjacent to this plot
    ArrayList<Hotel> hotels = new ArrayList<Hotel>();
    Boolean has_entrance = false;
    int index = 0;
    Type type = Type.NO_ACTION;

}
