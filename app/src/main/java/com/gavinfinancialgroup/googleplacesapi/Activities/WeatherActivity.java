package com.gavinfinancialgroup.googleplacesapi.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.gavinfinancialgroup.googleplacesapi.R;

public class WeatherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        //TextView country = (TextView) findViewById(R.id.country_tv);
        TextView city = (TextView) findViewById(R.id.city_tv);
        TextView min = (TextView) findViewById(R.id.min_tv);
        TextView max = (TextView) findViewById(R.id.max_tv);
        TextView description = (TextView) findViewById(R.id.description_tv);

        Intent intent = getIntent();
        //country.setText(intent.getStringExtra("country"));
        city.setText(intent.getStringExtra("city"));
        min.setText(intent.getStringExtra("min"));
        max.setText(intent.getStringExtra("max"));
        description.setText(intent.getStringExtra("description"));
    }
}
