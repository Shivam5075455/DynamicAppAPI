package com.example.dynamicappapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.dynamicappapi.databinding.ActivityOtherApisBinding;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class OtherAPIs extends AppCompatActivity {
    String url = "https://api.publicapis.org/entries";

    ActivityOtherApisBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOtherApisBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        String url = "https://jsonplaceholder.typicode.com/users";

//        String url = "https://api.coindesk.com/v1/bpi/currentprice.json";
//        String url = "https://www.boredapi.com/api/activity";

// API button
        binding.btnApi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jsonParseApi();
            }
        });

//        AUTH Button
        binding.btnLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            jsonParseAuth();
            }
        });

        binding.btnCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jsonParseCategory();
            }
        });

    } // end of onCreate fxn
int i;
    public void jsonParseApi(){
        ArrayList<String> arrayList = new ArrayList<>();

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try{
//                    take data in the form of array because all the objects are present in the array
                    JSONArray jsonArray = response.getJSONArray("entries");
                    for (i=0;i<jsonArray.length();i++){
//                        create an instance of an object to get object from the array list
                        JSONObject object = jsonArray.getJSONObject(i);
//                        fetch the data from one object
                        String api = object.getString("API");
                        arrayList.add(api);
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(OtherAPIs.this, android.R.layout.simple_list_item_1, arrayList);
//                        binding.tv.setText(api);
                        binding.listview.setAdapter(adapter);
                    }
                }catch (Exception e){
                        e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        Volley.newRequestQueue(OtherAPIs.this).add(request);
    }

    public void jsonParseAuth(){

        ArrayList<String> linkArrayList = new ArrayList<String>();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {

                    JSONArray jsonArray = response.getJSONArray("entries");

                    for(i=0;i<jsonArray.length();i++){
                    JSONObject object = jsonArray.getJSONObject(i);
                    String Link = object.getString("Link");
                    linkArrayList.add(Link);
                    ArrayAdapter<String> adapter = new ArrayAdapter(OtherAPIs.this, android.R.layout.simple_list_item_1, linkArrayList);
                    binding.listview.setAdapter(adapter);
                    }

                }catch (Exception e){
                        e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
    Volley.newRequestQueue(OtherAPIs.this).add(request);
    }


    public void jsonParseCategory(){

        ArrayList<String> categoryArrayList = new ArrayList<>();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("entries");
                    for(i=0; i<jsonArray.length();i++ ){

                        JSONObject object = jsonArray.getJSONObject(i);
                        String category = object.getString("Category");
                        categoryArrayList.add(category);
                        ArrayAdapter<String> adapter = new ArrayAdapter<>(OtherAPIs.this, android.R.layout.simple_list_item_1, categoryArrayList);
                        binding.listview.setAdapter(adapter);

                    }
                }catch (Exception e){

                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        Volley.newRequestQueue(OtherAPIs.this).add(request);
    }

}