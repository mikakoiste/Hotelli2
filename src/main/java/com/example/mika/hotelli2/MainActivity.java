package com.example.mika.hotelli2;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;

public class MainActivity extends Activity implements View.OnClickListener{

    private Context mContext;
    private LinearLayout mLinearLayout;
    Button noppa;
    Button buy;
    Button build;
    TextView ruutu;
    TextView raha;
    TextView tieto;
    TextView currentPlot;
    int current_plot = 1;
    int money = 10000;
    final static int prize = 2000;
    GameBoard gameboard = new GameBoard();
    ArrayList<Player> players = new ArrayList<>();
    private PopupWindow buyPopupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        noppa = findViewById(R.id.noppa_button);
        buy = findViewById(R.id.buy_button);
        build = findViewById(R.id.build_button);
        ruutu = findViewById(R.id.ruutu);
        raha = findViewById(R.id.raha);
        tieto = findViewById(R.id.tieto);
        mLinearLayout = (LinearLayout) findViewById(R.id.verticalLayout);
        currentPlot = findViewById(R.id.current_plot_TV);
        findViewById(R.id.noppa_button).setOnClickListener(this);
        findViewById(R.id.buy_button).setOnClickListener(this);
        findViewById(R.id.build_button).setOnClickListener(this);
        ruutu.setText(String.valueOf(current_plot));
        raha.setText(String.valueOf(money));
        addPlayers();
    }

    public void onClick(View arg0) {
        Button btn = (Button)arg0;
        switch (btn.getId()) {
            case R.id.noppa_button:
                this.move();
                break;
            case R.id.buy_button:
                this.buy();
                break;
            case R.id.build_button:
                this.build();
                break;
        }

        ArrayList<Hotel> hotels = gameboard.available_hotels(current_plot);
        if (hotels.size() == 0)
        {
            buy.setEnabled(false);
        }
        else
        {
            buy.setEnabled(true);
        }
    }

    void buy()
    {
        ArrayList<String> hotels = gameboard.available_hotels_str(current_plot);

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
        LayoutInflater inflater = getLayoutInflater();
        View convertView = (View) inflater.inflate(R.layout.buy_layout, null);
        alertDialog.setView(convertView);
        alertDialog.setTitle("Select Hotel to buy");
        ListView lv = (ListView) convertView.findViewById(R.id.hotels_to_buy);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, hotels);
        lv.setAdapter(adapter);
        alertDialog.show();
    }

    void build() {
    }

    int rollDice(){
        return 1 + (int)(Math.random() * 6);
    }

    void move(){
        int luku = this.rollDice();
        currentPlot.setText(String.valueOf(luku));
        advance(luku);
    }

    public void addPlayers(){
        Player player1 = new Player("Pelaaja1");
        Player player2 = new Player("Pelaaja2");
        players.add(player1);
        players.add(player2);
    }

    public void advance(int count){
        int _prev_plot = current_plot;
        current_plot = current_plot + count;
        if (current_plot > gameboard.plots() - 1)  // 1st index is 0
        {
            current_plot = current_plot % gameboard.plots();
        };
        ruutu.setText(String.valueOf(current_plot));
        if (_prev_plot < gameboard.prize && current_plot > gameboard.prize)
        {
            this.get_prize();
        }
        raha.setText(String.valueOf(money));
        printAvailableHotels();
    }

    public void printAvailableHotels()
    {
        ArrayList<Hotel> hotels = gameboard.available_hotels(current_plot);
        Iterator<Hotel> hotelIterator = hotels.iterator();
        String hotelsStr = "";
        while (hotelIterator.hasNext()) {
            Hotel _hotel = hotelIterator.next();
            hotelsStr += _hotel._name + "\n";
        }
        tieto.setText(hotelsStr);
    }

    void get_prize()
    {
        money += prize;
    }

}
