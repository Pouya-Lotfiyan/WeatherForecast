package webservice;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import model.WeatherForecaster;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



public class Handler {


        public final static  String BASE_URl ="";
        public static final String API_KEY = "";
        private  static  WeatherForecaster weatherForecaster;
        private Callback<WeatherForecaster> callback;
        private  Retrofit handler ;


        private Handler(Retrofit handler ,  Callback callback ) {

        this.handler = handler;
        this.callback =callback;
    }

        public void setCallback(Callback<WeatherForecaster> callback) {
        this.callback = callback;
        }

        public void setForeCastsFromServer(  ) {


                Requests requests = handler.create(Requests.class);
                Call<WeatherForecaster> call = requests.getForecasts(API_KEY);
                call.enqueue(callback);


                    }




        public  static  class  Builder{

        private static Handler handler ;





            public static Handler build( Gson gson , Callback<WeatherForecaster> callback ){

                if (handler == null){

                    Retrofit retrofit =  new Retrofit.Builder().
                            baseUrl(BASE_URl).
                            addConverterFactory(GsonConverterFactory.create(gson)).build();

                    handler = new Handler( retrofit , callback );


                }

                return handler;

            }


            public static Handler build(  Callback<WeatherForecaster> callback ){

                if (handler == null){



                    Retrofit retrofit =  new Retrofit.Builder().
                            baseUrl(BASE_URl).
                            addConverterFactory(GsonConverterFactory.create()).build();

                    handler = new Handler( retrofit , callback );


                }

                return handler;

            }
    }

}
