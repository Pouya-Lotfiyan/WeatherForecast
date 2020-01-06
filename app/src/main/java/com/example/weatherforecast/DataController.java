package com.example.weatherforecast;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import model.WeatherForecaster;

public class DataController {


    private static final String FORECASTS_JSON = "forecasts.json";
    public static final String FILE_ERROR = "file_error";

    public  static  WeatherForecaster mustGetinfo(Context context){

        File file = new File( context.getFilesDir() , FORECASTS_JSON);

        if ( file.exists() ){

            String json =  readJsonFile(file);
            Gson gson =new  GsonBuilder().setLenient().create();
            return ( (WeatherForecaster) gson.fromJson(json ,WeatherForecaster.class) );



        }

        return null;

    }

    private static  String readJsonFile(File file){
        StringBuilder builder =  new StringBuilder();
        try {

            BufferedReader  reader =  new BufferedReader(new FileReader(file));
            String line = "" ;

            while ( ( line = reader.readLine() ) != null){
                builder.append(line);
                builder.append("\n");
            }


            reader.close();


        } catch (IOException e) {
            Log.e(FILE_ERROR, e.getLocalizedMessage());
        }
        return builder.toString();

    }


    public static void saveData(Context context, WeatherForecaster onjectRoot) {

        try {

            File file = new File(context.getFilesDir() , FORECASTS_JSON );
            BufferedWriter writer =  new BufferedWriter(new FileWriter(file ,false));
            Gson gson = new GsonBuilder().setLenient().setPrettyPrinting().create();
            String jsson =  gson.toJson(onjectRoot);
            writer.write(jsson);
            writer.close();

        } catch (IOException e) {
            Log.e(FILE_ERROR , e.getLocalizedMessage());
        }


    }
}
