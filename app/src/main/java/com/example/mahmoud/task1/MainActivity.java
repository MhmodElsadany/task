package com.example.mahmoud.task1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ArrayList<SouqItems> itemsList = new ArrayList<>();
    RecyclerView recyclerList;
    StringRequest stringRequest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerList = (RecyclerView) findViewById(R.id.item_llist);

        String url = "http://souq.hardtask.co/app/app.asmx/GetCategories?categoryId=0&countryId=1";
        stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(final String response) {
                        try {
                            Log.i("data2", response);

                            JSONArray jsonArray = new JSONArray(response);

                            for (int i = 0; i < jsonArray.length(); i++) {

                                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                                itemsList.add(new SouqItems(jsonObject1.getString(getResources().getString(R.string.name_item))
                                        , jsonObject1.getString("Photo"), jsonObject1.getString("Id")));

                            }

                            CustomAdapterSouq mCustomAdapterSouq = new CustomAdapterSouq(MainActivity.this, itemsList, "1");
                            recyclerList.setAdapter(mCustomAdapterSouq);
                            recyclerList.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));

                        } catch (Exception e) {

                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println(error.getMessage());
                    }
                });
        Singleton.getInstance(MainActivity.this).addRequestQue(stringRequest);

    }
}
