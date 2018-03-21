package com.example.dog.mtoolbarsimple.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.dog.mtoolbarsimple.R;

/**
 * Created by Dog on 2018/3/21.
 */

public class BaseFragment extends Fragment
{
    private LinearLayout root_layout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_base, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        root_layout = view.findViewById(R.id.root_layout);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState)
    {
        super.onSaveInstanceState(outState);
    }

    public void setContentView(int resId)
    {
        if (root_layout != null) {
            root_layout.addView(
                    LayoutInflater.from(getContext())
                            .inflate(resId, null, false)
            );
        }
    }
}
