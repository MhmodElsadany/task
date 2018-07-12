package com.example.mahmoud.task1;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


public class ForItemFragment extends Fragment {
    ArrayList<SouqItems> itemsList = new ArrayList<>();
    RecyclerView recyclerList;
    StringRequest stringRequest;
    ImageView imageView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_for_item, container, false);
        recyclerList = (RecyclerView) view.findViewById(R.id.item_llist);
        TextView mTextView = (TextView) view.findViewById(R.id.txt);
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        mTextView.setText(getActivity().getIntent().getStringExtra("name"));
        imageView = (ImageView) view.findViewById(R.id.icon);
        Typeface typefaceuser = Typeface.createFromAsset(getActivity().getAssets(), "fonts/GE Dinar One Medium.ttf");
        mTextView.setTypeface(typefaceuser);


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });


        String url = "http://souq.hardtask.co/app/app.asmx/GetCategories?categoryId=" + getActivity().getIntent().getStringExtra
                ("id") + "&countryId=1";
        stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(final String response) {
                        try {
                            Log.i("data", response);

                            JSONArray jsonArray = new JSONArray(response);

                            for (int i = 0; i < jsonArray.length(); i++) {

                                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                                itemsList.add(new SouqItems(jsonObject1.getString("TitleEN")
                                        , jsonObject1.getString("Photo"), jsonObject1.getString("Id")));

                            }

                            CustomAdapterSouq mCustomAdapterSouq = new CustomAdapterSouq(getActivity(), itemsList, "0");
                            recyclerList.setAdapter(mCustomAdapterSouq);
                            recyclerList.setLayoutManager(new GridLayoutManager(getActivity(), 2));

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
        Singleton.getInstance(getActivity()).addRequestQue(stringRequest);
        return view;
    }

}
