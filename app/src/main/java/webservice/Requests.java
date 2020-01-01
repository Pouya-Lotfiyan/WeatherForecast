package webservice;

import model.WeatherForecaster;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Requests {

    @GET("210841")
    Call < WeatherForecaster > getForecasts( @Query("apikey") String apiKey );



}
