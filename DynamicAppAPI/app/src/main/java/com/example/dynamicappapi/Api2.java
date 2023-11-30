package com.example.dynamicappapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.dynamicappapi.databinding.ActivityApi2Binding;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Api2 extends AppCompatActivity {

    String url = "https://jsonplaceholder.typicode.com/users";
    int i;
    ActivityApi2Binding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityApi2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        jsonParseName();
        jsonParseId();

        } // end of onCreate fxn


        public void jsonParseName(){
            ArrayList<String> arrayList = new ArrayList<>();
            ArrayList<String> arrayListId = new ArrayList<>();
            JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {

                    try {
                        for (i=0;i<response.length();i++){
                            JSONObject object = response.getJSONObject(i);
                            String id = object.getString("id");
                            String name = object.getString("name");
                            arrayListId.add(id);
                            arrayList.add(name);

                            ArrayAdapter<String> adapterId = new ArrayAdapter<>(Api2.this, android.R.layout.simple_list_item_1, arrayListId);
                            binding.listviewId.setAdapter(adapterId);
                            ArrayAdapter<String> adapter = new ArrayAdapter<>(Api2.this, android.R.layout.simple_list_item_1, arrayList);
                            binding.listviewEmploye.setAdapter(adapter);
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
            Volley.newRequestQueue(Api2.this).add(request);
        }

        public void jsonParseId(){

        ArrayList<String> arrayListId = new ArrayList<>();
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                try {
                    for(i=0;i<response.length();i++){
                        JSONObject object = response.getJSONObject(i);
                        String id = object.getString("id");
                        arrayListId.add(id);
                        ArrayAdapter<String> adapter = new ArrayAdapter<>(Api2.this, android.R.layout.simple_list_item_1, arrayListId);
                        binding.listviewId.setAdapter(adapter);
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
            Volley.newRequestQueue(Api2.this).add(request);

        }
}