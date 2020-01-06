package com.example.weatherforecast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import model.WeatherForecaster;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import webservice.Handler;


public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private LinearLayout dots;
    private  boolean isProgressing;
    private  static ProgressDialog dialog ;
    private  int totalView;

    public static final String Tag = MainActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = findViewById(R.id.slider);
        dots = findViewById(R.id.dots);
        setStatusBarColor();




        WeatherForecaster forecaster = DataController.mustGetinfo(this);
        if ( forecaster == null ) {

            getInfo();

        }else {


            viewPager.setAdapter(new SildesAdapter( forecaster.getDailyForecasts() ,this));
            totalView = forecaster.getDailyForecasts().size();
            showDots( totalView ,viewPager.getCurrentItem());
            viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {

                showDots(totalView ,position);


                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });


        }






    }

    private void showDots( int allDots  ,int currentItem ) {
       dots.removeAllViews();
        TextView [] vdots = new TextView[allDots];
        for (int i = 0; i < vdots.length ; i++) {
            vdots[i] = new TextView(this);
            vdots[i].setText(Html.fromHtml("&#8226;"));
            vdots[i].setTextSize(TypedValue.COMPLEX_UNIT_DIP , 32 );
            vdots[i].setTextColor(

                    ( i == currentItem  ?
                            getResources().getColor( R.color.dots_visible )  :  getResources().getColor( R.color.dots_invisible )  )

            );



            dots.addView(vdots[i]);
        }






    }

    private void setStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            Window window = getWindow();
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);

            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(android.R.color.transparent));
        }
    }

    private  void getInfo() {

        dialog = new ProgressDialog(this);
        dialog.setCancelable(false);
        dialog.setMessage("please wait");
        dialog.show();

        Handler handler = Handler.Builder.build(new Callback<WeatherForecaster>() {
            @Override
            public void onResponse(Call<WeatherForecaster> call, Response<WeatherForecaster> response) {

                  dialog.dismiss();

                if (response.isSuccessful()) {

                    WeatherForecaster onjectRoot = response.body();
                    viewPager.setAdapter(new SildesAdapter(onjectRoot.getDailyForecasts(), MainActivity.this));
                    totalView =onjectRoot.getDailyForecasts().size();
                    showDots(totalView , viewPager.getCurrentItem());
                    viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                        @Override
                        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                        }

                        @Override
                        public void onPageSelected(int position) {

                            showDots(totalView ,position);


                        }

                        @Override
                        public void onPageScrollStateChanged(int state) {

                        }
                    });
                    Toast.makeText(MainActivity.this, "Updated", Toast.LENGTH_SHORT).show();
                    DataController.saveData(MainActivity.this , onjectRoot);





                }else {

                    AlertDialog alertDialog=   new AlertDialog.Builder(MainActivity.this)
                            .setMessage("not Found any result")
                            .setTitle("unavailable Server")
                            .setNeutralButton("ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    dialog.dismiss();
                                }
                            })
                            .setNeutralButton("try again", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    getInfo();



                                }
                            })
                            .show();


                }



            }

            @Override
            public void onFailure(Call<WeatherForecaster> call, Throwable t) {
                dialog.dismiss();
             AlertDialog alertDialog=   new AlertDialog.Builder(MainActivity.this)
                        .setMessage("Please check your connection then try again")
                        .setTitle("Connection failed ")
                        .setNeutralButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                dialog.dismiss();
                            }
                        })
                        .show();

            }
        });


        handler.setForeCastsFromServer();
    }

    public void onRefreshClickListener(View view) {

        view.animate().rotation(180 * 6).setDuration(500);

        getInfo();





    }
}
