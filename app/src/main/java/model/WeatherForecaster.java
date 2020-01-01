package model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WeatherForecaster {

    @SerializedName("Headline")
    private  HeadLine headLine ;

    @SerializedName("DailyForecasts")
    private List< DailyForecast > dailyForecasts ;


    public WeatherForecaster(HeadLine headLine, List<DailyForecast> dailyForecasts) {
        this.headLine = headLine;
        this.dailyForecasts = dailyForecasts;
    }

    public HeadLine getHeadLine() {
        return headLine;
    }

    public List<DailyForecast> getDailyForecasts() {
        return dailyForecasts;
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
        String date;
        @SerializedName("Temperature")
        Temoerature temoerature ;
        @SerializedName("Day")
        Day day ;
        @SerializedName("Night")
        Night night ;
        @SerializedName("Link")
        String link ;

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
