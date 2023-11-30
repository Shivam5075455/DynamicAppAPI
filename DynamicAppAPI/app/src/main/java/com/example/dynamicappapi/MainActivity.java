package com.example.dynamicappapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.dynamicappapi.databinding.ActivityMainBinding;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ActivityMainBinding binding;
    //      using timezon API to fetch time and date details
    String url = "https://worldtimeapi.org/api/timezone/Asia/Kolkata"; //GET request
    //        using another api
    String urlcat = "https://catfact.ninja/fact"; //GET request
    String urlemp = "https://jsonplaceholder.typicode.com/users";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        refresh();


    } // end of onCreate fxn


    public void refresh(){
        //        refresh fxn
        binding.swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                allWork();
                binding.swipeRefresh.setRefreshing(false);
                Toast.makeText(MainActivity.this, "Refreshed", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void allWork(){

//        initialize the namendroid networking api
//        AndroidNetworking.initialize(getApplicationContext());

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

//                use try catch block to avoid application crash while data is not available in API
                try{

//                    String week_number = response.getString("datetime");
//                    binding.timeDetail.setText(week_number);
//                    abbreviation
                    String abbreviation = response.getString("abbreviation");
                    binding.abbreviation.setText(abbreviation);
//                    client_ip
                    String client_ip = response.getString("client_ip");
                    binding.clientIp.setText(client_ip);
//    TextView textView, abbreviation, client_ip, day_of_week, day_of_year, timezone, utc_offset, week_number, listview;
//                    day_of_week
                    String day_of_week = response.getString("day_of_week");
                    binding.dayOfWeek.setText(day_of_week);
//                    day_of_year
                    String day_of_year = response.getString("day_of_year");
                    binding.dayOfWeek.setText(day_of_year);
//                    timezon
                    String timezone = response.getString("timezone");
                    binding.timezone.setText(timezone);
//                    utc_offset
                    String utc_offset = response.getString("utc_offset");
                    binding.utcOffset.setText(utc_offset);
//                    week_number
                    String week_number = response.getString("week_number");
                    binding.weekNumber.setText(week_number);
//                  date and time
                    String datetime = response.getString("datetime");
                    binding.datetime.setText(datetime);

                    Log.d("Message from timedata api: ",abbreviation);

                }catch (Exception e){
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        Volley.newRequestQueue(this).add(request);


//        using another API and getching data
        JsonObjectRequest request1 = new JsonObjectRequest(Request.Method.GET, urlcat, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try{
                    String fact = response.getString("fact");
                    binding.fact.setText(fact);
                    String length = response.getString("length");
                    binding.length.setText(length+"cm");
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){

            }
        });

        Volley.newRequestQueue(this).add(request1);

//        button api 1   to go to otherAPIs activity
        binding.btnApi1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, OtherAPIs.class);
                startActivity(intent);
            }
        });

//        button api 2
        binding.btnApi2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Api2.class);
                startActivity(intent);
            }
        });
// button Coindesk
        binding.btnCoindesk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CurrencyActivity.class);
                startActivity(intent);
            }
        });
    }


}