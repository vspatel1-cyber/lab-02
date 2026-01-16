package com.example.listycity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity{


    Boolean city_picked = false;

    int picked_city_pos = 0;

    private AdapterView.OnItemClickListener selected_city_data = (parent, v, pos, id) -> {
        picked_city_pos = pos;
        city_picked = true;

        findViewById(R.id.delete_city).setEnabled(city_picked);
    };



    ListView cityList;

    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        findViewById(R.id.delete_city).setEnabled(city_picked);


        cityList = findViewById(R.id.city_list);
        String []cities = {"Edmonton", "Vancouver", "Moscow", "Sydney", "Berlin", "Vienna", "Tokyo", "Beijing", "Osaka", "New Delhi"};

        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));


        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);

        cityList.setAdapter(cityAdapter);

        cityList.setOnItemClickListener(selected_city_data);







        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void add_city(View v) {
        EditText text_input = findViewById(R.id.add_city_name);
        String text_city = text_input.getText().toString();

        if (!text_city.matches("")) {
            text_input.getText().clear();
            dataList.add(text_city);
            cityAdapter.notifyDataSetChanged();
        }


    }

    public void delete_city_b(View v) {
        city_picked = false;

        findViewById(R.id.delete_city).setEnabled(city_picked);
        dataList.remove(picked_city_pos);
        cityAdapter.notifyDataSetChanged();
    }


}