package com.example.dog.mtoolbarsimple.custom;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Dog on 2018/3/19.
 */

public class CustomViewPager extends ViewPager
{
    private boolean isPagingEnabled = true;

    public CustomViewPager(@NonNull Context context)
    {
        super(context);
    }

    public CustomViewPager(@NonNull Context context, @Nullable AttributeSet attrs)
    {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev)
    {
        return this.isPagingEnabled && super.onTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev)
    {
        return this.isPagingEnabled && super.onInterceptTouchEvent(ev);
    }

    public void setPagingEnabled(boolean b)
    {
        this.isPagingEnabled = b;
    }
}
