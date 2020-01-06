package model;

import androidx.annotation.NonNull;

import com.example.weatherforecast.R;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WeatherForecaster {


    public final static String WEATHER_SUNNY ="sunny";

    public final static String WEATHER_CLOUDY ="cloudy";

    public final static String WEATHER_CLEAR ="clear";

    public final static String WEATHER_RAINY ="rainy";





    @SerializedName("Headline")
    private  HeadLine headLine ;

    @SerializedName("DailyForecasts")
    private List< DailyForecast > dailyForecasts ;


    public WeatherForecaster(HeadLine headLine, List<DailyForecast> dailyForecasts) {
        this.headLine = headLine;
        this.dailyForecasts = dailyForecasts;
    }

    public static int  getAverage(DailyForecast forecast) {

        int max =  forecast.temoerature.max.value;
        int min = forecast.temoerature.min.value;

        return (max + min)/2 ;

    }

    public static String getDayWeather(DailyForecast forecast) {
        String weather = forecast.day.iconPhrase.toLowerCase();



        if (weather.contains("shower")) {

            return WEATHER_RAINY;


        }
        if (weather.contains("cloudy")){


            return WEATHER_CLOUDY;

        }

        if (weather.contains("sunny")){

            return WEATHER_SUNNY;



        }
        if (weather.contains("clear")){

            return WEATHER_CLEAR;



        }




        return "no-parameter";

    }

    public static String getNightWeather(DailyForecast forecast) {

        String weather = forecast.night.iconPhrase.toLowerCase();



        if (weather.contains("shower")) {

            return WEATHER_RAINY;


        }
        if (weather.contains("cloudy")){


            return WEATHER_CLOUDY;

        }

        if (weather.contains("sunny")){

            return WEATHER_SUNNY;



        }
        if (weather.contains("clear")){

            return WEATHER_CLEAR;



        }




        return "no-parameter";

    }


    public HeadLine getHeadLine() {
        return headLine;
    }

    public List<DailyForecast> getDailyForecasts() {
        return dailyForecasts;
    }





    public static String getExactDateFormat(DailyForecast forecast) {

        String date = forecast.date;

        return date.substring(0, date.lastIndexOf("T"));




    }







    public static class HeadLine {

        String Text ;
        String link;

        public HeadLine(String text, String link) {
            Text = text;
            this.link = link;
        }

        public String getText() {
            return Text;
        }

        public String getLink() {
            return link;
        }
    }

    public static class DailyForecast {

        @SerializedName("Date")
        private   String date;
        @SerializedName("Temperature")
        private Temoerature temoerature ;
        @SerializedName("Day")
        private Day day ;
        @SerializedName("Night")
        private Night night ;
        @SerializedName("Link")
        private   String link ;

        public String getDate() {
            return date;
        }

        public Temoerature getTemoerature() {
            return temoerature;
        }

        public Day getDay() {
            return day;
        }

        public Night getNight() {
            return night;
        }

        public String getLink() {
            return link;
        }

        @NonNull
        @Override
        public String toString() {
            return   "Date :"+date+"\n"
                    +"max :"+temoerature.max.value+"\n"
                    +"min :"+temoerature.min.value+"\n"
                    +"day :"+day.iconPhrase+"\n"
                    +"night :"+night.iconPhrase+"\n"

                    ;
        }

        public   static  class Temoerature {
            @SerializedName("Maximum")
            Max max ;

            @SerializedName("Minimum")
            Min min ;

            public Temoerature(Max max, Min min) {
                this.max = max;
                this.min = min;
            }

            public Max getMax() {
                return max;
            }

            public Min getMin() {
                return min;
            }

            public static class Min {
                @SerializedName("Value")
                int value ;

                @SerializedName("Unit")
                String unit ;

                public Min( int value ,String unit  ) {
                    this.unit = unit;
                    this.value = value;

                }

                public int getValue() {
                    return value;
                }

                public String getUnit() {
                    return unit;
                }
            }

            public static class Max {

                @SerializedName("Value")
                int value ;

                @SerializedName("Unit")
                String unit ;

                public Max( int value , String unit  ) {
                    this.unit = unit;
                    this.value = value;

                }

                public int getValue() {
                    return value;
                }

                public String getUnit() {
                    return unit;
                }
            }
        }

        public static class Day {

            @SerializedName("IconPhrase")
            String iconPhrase ;

            @SerializedName("HasPrecipitation")
            Boolean hasPrecipitation ;

            @SerializedName("PrecipitationType")
            String precipitationType ;

            @SerializedName("PrecipitationIntensity")
            String precipitationIntensity ;

            public Day(Boolean hasPrecipitation, String precipitationType, String precipitationIntensity , String iconPhrase) {
                this.hasPrecipitation = hasPrecipitation;
                this.precipitationType = precipitationType;
                this.precipitationIntensity = precipitationIntensity;
                this.iconPhrase = iconPhrase;
            }

            public Boolean isHasPrecipitation() {
                return hasPrecipitation;
            }

            public String getPrecipitationType() {
                return precipitationType;
            }

            public String getPrecipitationIntensity() {
                return precipitationIntensity;
            }

            public String getIconPhrase() {
                return iconPhrase;
            }
        }


        public class Night {

            @SerializedName("IconPhrase")
            String iconPhrase ;

            @SerializedName("HasPrecipitation")
            Boolean hasPrecipitation ;

            @SerializedName("PrecipitationType")
            String precipitationType ;

            @SerializedName("PrecipitationIntensity")
            String precipitationIntensity ;

            public Night(Boolean hasPrecipitation, String precipitationType, String precipitationIntensity , String iconPhrase) {
                this.hasPrecipitation = hasPrecipitation;
                this.precipitationType = precipitationType;
                this.precipitationIntensity = precipitationIntensity;
                this.iconPhrase = iconPhrase;
            }

            public Boolean isHasPrecipitation() {
                return hasPrecipitation;
            }

            public String getPrecipitationType() {
                return precipitationType;
            }

            public String getPrecipitationIntensity() {
                return precipitationIntensity;
            }

            public String getIconPhrase() {
                return iconPhrase;
            }
        }
    }







}
