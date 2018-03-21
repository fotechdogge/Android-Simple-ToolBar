package com.example.dog.mtoolbarsimple.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.dog.mtoolbarsimple.R;
import com.example.dog.mtoolbarsimple.activity.NewActivity;
import com.example.dog.mtoolbarsimple.base.BaseFragment;

/**
 * Created by Dog on 2018/3/18.
 */

public class FragmentActivityOne extends BaseFragment implements View.OnClickListener
{
    private TextView textView;
    private Button   button;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        setContentView(R.layout.fragment_one);
        textView = view.findViewById(R.id.frag_text);
        button = view.findViewById(R.id.frag_button);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        startActivity(new Intent().setClass(getContext(), NewActivity.class));
    }
}
