package com.example.dynamicappapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.AsyncTask;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.dynamicappapi.databinding.ActivityCurrencyBinding;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class CurrencyActivity extends AppCompatActivity {

    String url = "https://api.coindesk.com/v1/bpi/currentprice.json";

    ActivityCurrencyBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCurrencyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        refresh();

    } // end of onCreate fxn


    public void refresh(){
        binding.swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                jsonParseCode();
                binding.swipeRefresh.setRefreshing(false);
            }
        });
    }

        public void jsonParseCode() {
//        API object sequence: JSON -> bpi -> USD -> code
//                                            GBP -> code
//                                            EUR -> code
//        Request from JSON object
            JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

//code, description, rate, rate_float, symbol

                    try {
//                        Request from bpi object
                        JSONObject bpi = response.getJSONObject("bpi");
//                        USD object
                        JSONObject usd = bpi.getJSONObject("USD");
//                        GBP object
                        JSONObject GBP = bpi.getJSONObject("GBP");
//                        EUR object
                        JSONObject EUR = bpi.getJSONObject("EUR");

//                        USD data
                        String code = usd.getString("code");
                        String  description = usd.getString("description");
                        String rate = usd.getString("rate");
                        String  rateFloat = usd.getString("rate_float");
                        String symbol = usd.getString("symbol");

                        binding.tvCode.setText(code);
                        binding.tvDescription.setText(description);
                        binding.tvRate.setText(rate);
                        binding.tvRateFloat.setText(rateFloat);
                        binding.tvSymbol.setText(symbol);

//                        GBP data
                        String codeGBP = GBP.getString("code");
                        String  descriptionGBP = GBP.getString("description");
                        String rateGBP = GBP.getString("rate");
                        String  rateFloatGBP = usd.getString("rate_float");
                        String symbolGBP = GBP.getString("symbol");

//                        set data of GBP
                        binding.tvCodeGBP.setText(codeGBP);
                        binding.tvDescriptionGBP.setText(descriptionGBP);
                        binding.tvRateGBP.setText(rateGBP);
                        binding.tvRateFloatGBP.setText(rateFloatGBP);
                        binding.tvSymbolGBP.setText(symbolGBP);

//                        EUR data
                        String codeEUR = EUR.getString("code");
                        String  descriptionEUR = EUR.getString("description");
                        String rateEUR = EUR.getString("rate");
                        String  rateFloatEUR = EUR.getString("rate_float");
                        String symbolEUR = EUR.getString("symbol");

//                        set data of EUR
                        binding.tvCodeEUR.setText(codeEUR);
                        binding.tvDescriptionEUR.setText(descriptionEUR);
                        binding.tvRateEUR.setText(rateEUR);
                        binding.tvRateFloatEUR.setText(rateFloatEUR);
                        binding.tvSymbolEUR.setText(symbolEUR);



                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                }
            });
            Volley.newRequestQueue(CurrencyActivity.this).add(objectRequest);
        }
//    }

}