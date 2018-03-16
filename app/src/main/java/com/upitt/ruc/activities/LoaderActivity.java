package com.upitt.ruc.activities;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.upitt.ruc.R;

public class LoaderActivity extends AppCompatActivity {

    public static Context loader_Activity;
    Context context1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loader);

        context1 = this;
        loader_Activity = context1;
    }

    @Override
    public void onBackPressed() {

    }
}
