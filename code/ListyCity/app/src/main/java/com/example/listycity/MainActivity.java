package com.example.listycity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
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


    private Button add_button;
    private Button delete_button;

    private EditText new_city;



    ListView cityList;

    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        add_button = findViewById(R.id.add_city);

        delete_button = findViewById(R.id.delete_city);




        cityList = findViewById(R.id.city_list);
        String []cities = {"Edmonton", "Vancouver", "Moscow", "Sydney", "Berlin", "Vienna", "Tokyo", "Beijing", "Osaka", "New Delhi"};

        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));


        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);

        cityList.setAdapter(cityAdapter);

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){

                if (v.getId() == R.id.add_city) {
                    String new_city_text = new_city.getText().toString().trim();

                    if (!new_city_text.isEmpty()) {
                        dataList.add(new_city_text);
                        cityAdapter.notifyDataSetChanged();
                        new_city.setText("");
                    }
                }

            }

        });







        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }


}