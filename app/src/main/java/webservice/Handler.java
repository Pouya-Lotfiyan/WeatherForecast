package webservice;

import android.content.Context;
import android.util.Log;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.weatherforecast.MainActivity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import model.WeatherForecaster;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Handler {


        public final static  String BASE_URl ="http://dataservice.accuweather.com/forecasts/v1/daily/5day/";
        public static final String API_KEY = "SoqAllZXPcmkH3JBtW637KAysPkN8UQZ";
        private  static  WeatherForecaster weatherForecaster;
        private Callback<WeatherForecaster> callback;
        private  Retrofit handler ;

        private Handler(Retrofit handler) {

        this.handler = handler;

    }
        private Handler(Retrofit handler ,  Callback callback ) {

        this.handler = handler;
        this.callback =callback;
    }

        public void setCallback(Callback<WeatherForecaster> callback) {
        this.callback = callback;
        }

        public void setForeCastsFromServer(final Context context , final ListView listView ) {


            Requests requests = handler.create(Requests.class);

            Call<WeatherForecaster> call = requests.getForecasts(API_KEY);

            if (callback != null) {

                call.enqueue(callback);

            } else {

                call.enqueue(new Callback<WeatherForecaster>() {

                    @Override
                    public void onResponse(Call<WeatherForecaster> call, Response<WeatherForecaster> response) {

                        if (response.isSuccessful()) {

                            WeatherForecaster weatherForecaster = response.body();
                            List<String> details = new ArrayList<>();
                            List<WeatherForecaster.DailyForecast> forecasts = weatherForecaster.getDailyForecasts();
                            for (WeatherForecaster.DailyForecast day : forecasts) {

                                details.add(day.toString());

                            }
                            listView.setAdapter(new ArrayAdapter<String>(
                                    context, android.R.layout.simple_list_item_1,
                                    details
                            ));
                        }

                    }

                    @Override
                    public void onFailure(Call<WeatherForecaster> call, Throwable t) {
                        Log.e(MainActivity.Tag, t.getLocalizedMessage());
                    }
                });


            }




        }



        public  static  class  Builder{

        private static Handler handler ;


        public static Handler build(){

            if (handler == null){

                Retrofit retrofit =  new Retrofit.Builder().
                        baseUrl(BASE_URl).
                        addConverterFactory(GsonConverterFactory.create()).build();
                handler = new Handler( retrofit );


            }

            return handler;

        }
            public static Handler build( Gson gson ){

                if (handler == null){

                    Retrofit retrofit =  new Retrofit.Builder().
                            baseUrl(BASE_URl).
                            addConverterFactory(GsonConverterFactory.create(gson)).build();
                    handler = new Handler( retrofit );


                }

                return handler;

            }

            public static Handler build( Gson gson , Callback<WeatherForecaster> callback ){

                if (handler == null){

                    Retrofit retrofit =  new Retrofit.Builder().
                            baseUrl(BASE_URl).
                            addConverterFactory(GsonConverterFactory.create(gson)).build();

                    handler = new Handler( retrofit , callback );


                }

                return handler;

            }


    }

}
