package com.example.weatherforecast;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import model.WeatherForecaster;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import webservice.Handler;
import webservice.Requests;

public class MainActivity extends AppCompatActivity {


    public static final String Tag = MainActivity.class.getSimpleName();
    ListView listView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.list_days);
      /*  Handler handler = Handler.Builder.build();
       handler.setForeCastsFromServer(this , listView);*/





        Retrofit retrofit =  new Retrofit.Builder().baseUrl(Handler.BASE_URl)
        .addConverterFactory(GsonConverterFactory.create()).build();
        Requests req = retrofit.create(Requests.class);
        Call <WeatherForecaster> call = req.getForecasts(Handler.API_KEY);
        call.enqueue(new Callback<WeatherForecaster>() {
            @Override
            public void onResponse(Call<WeatherForecaster> call, Response<WeatherForecaster> response) {

                WeatherForecaster weatherForecaster = response.body();
                List<String> details =new ArrayList<>();
                List<WeatherForecaster.DailyForecast> forecasts = weatherForecaster.getDailyForecasts();
                for (WeatherForecaster.DailyForecast day: forecasts) {

                    details.add(day.toString());



                }

                listView.setAdapter(new ArrayAdapter<String>(
                        MainActivity.this , android.R.layout.simple_list_item_1,
                        details
                ));



            }

            @Override
            public void onFailure(Call<WeatherForecaster> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });








    }
}
