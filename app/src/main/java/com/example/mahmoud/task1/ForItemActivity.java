package com.example.mahmoud.task1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ForItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_for_item);

        if (savedInstanceState == null) {
            ForItemFragment forItemFragment = new ForItemFragment();

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment, forItemFragment, "").commit();
        }

    }
}
