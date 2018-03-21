package com.example.dog.mtoolbarsimple.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.dog.mtoolbarsimple.R;
import com.example.dog.mtoolbarsimple.base.BaseActivity;

/**
 * Created by Dog on 2018/3/21.
 */

public class NewActivity extends BaseActivity
{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);
    }
}
