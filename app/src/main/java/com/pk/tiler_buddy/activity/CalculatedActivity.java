package com.pk.tiler_buddy.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.pk.tiler_buddy.InputValuesWrapper;
import com.pk.tiler_buddy.R;

public class CalculatedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculated);
        //Grabbing values from main Activity
        InputValuesWrapper calculatedValuesWrapper = (InputValuesWrapper) getIntent().getSerializableExtra("data");

    }
}


