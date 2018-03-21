package com.example.dog.mtoolbarsimple.base;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.example.dog.mtoolbarsimple.R;

/**
 * Created by Dog on 2018/3/21.
 */

public class BaseActivity extends AppCompatActivity
{
    private Toolbar      toolbar;
    private LinearLayout root_layout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_base);

        toolbar = findViewById(R.id.toolbar_base);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setSubtitleTextColor(Color.RED);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(nav_clickListener);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        root_layout = findViewById(R.id.root_layout);
    }

    @Override
    public void setContentView(int resId)
    {
        if (root_layout != null) {
            root_layout.addView(LayoutInflater.from(getApplication()).inflate(resId, null, false));
        }
    }

    private View.OnClickListener nav_clickListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            finish();
        }
    };

}
