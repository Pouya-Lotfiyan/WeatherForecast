package com.example.weatherforecast;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import java.util.List;
import model.WeatherForecaster;

public class SildesAdapter extends PagerAdapter {

    private List< WeatherForecaster.DailyForecast >  forecasts ;
    private  Context context;


    public SildesAdapter(List<WeatherForecaster.DailyForecast> forecasts  , Context context) {
        this.forecasts = forecasts;
        this.context = context;
    }


    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        WeatherForecaster.DailyForecast  forecast = forecasts.get(position);
        View slide = LayoutInflater.from(context)
                .inflate(R.layout.layout_weather ,container ,false);

        //date
        String date = WeatherForecaster.getExactDateFormat(forecast);
        ( (TextView) slide.findViewById(R.id.txt_date) ).setText(date);

       //average
        int  ave = WeatherForecaster.getAverage(forecast);
        ( (TextView) slide.findViewById(R.id.txt_degree) ).setText(ave+"");

        //Weather
        String dayWeather = WeatherForecaster.getDayWeather(forecast);
        ( (TextView) slide.findViewById(R.id.txt_weather) ).setText(dayWeather);
        ( (TextView) slide.findViewById(R.id.txt_day_value) ).setText(" "+dayWeather);

        //background

        switch (dayWeather){


            case WeatherForecaster.WEATHER_SUNNY :

                slide.setBackground(context.getDrawable(R.drawable.sunny));
                break;

            case WeatherForecaster.WEATHER_RAINY :
                slide.setBackground(context.getDrawable(R.drawable.rainy));

                break;
            case WeatherForecaster.WEATHER_CLEAR :
                slide.setBackground(context.getDrawable(R.drawable.clear));

                break;
            case WeatherForecaster.WEATHER_CLOUDY :
                slide.setBackground(context.getDrawable(R.drawable.cloudy));

                break;


        }

        //night weather
        String nightWeather  = WeatherForecaster.getNightWeather(forecast);
        ( (TextView) slide.findViewById(R.id.txt_night_value) ).setText(" "+nightWeather);
        //Max
        int max = forecast.getTemoerature().getMax().getValue();
        ( (TextView) slide.findViewById(R.id.txt_max_value) ).setText(""+max);
        //min
        int min = forecast.getTemoerature().getMin().getValue();
        ( (TextView) slide.findViewById(R.id.txt_min_value) ).setText(""+min);





        container.addView(slide);
        return slide;
    }

    @Override
    public int getCount() {
        return forecasts.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        View silde = (View) object;
        container.removeView(silde);
    }
}
