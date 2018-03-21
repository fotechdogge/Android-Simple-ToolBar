package com.example.dog.mtoolbarsimple.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.dog.mtoolbarsimple.R;

/**
 * Created by Dog on 2018/3/18.
 */

public class FragmentActivityThree extends Fragment implements View.OnClickListener
{
    private TextView textView;
    private Button   button;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState)
    {
        return  inflater.inflate(R.layout.fragment_three, container, false);
//        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        textView = view.findViewById(R.id.frag_text);
        button = view.findViewById(R.id.frag_button);
        button.setOnClickListener(this);
    }


    @Override
    public void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onClick(View v)
    {

    }
}
