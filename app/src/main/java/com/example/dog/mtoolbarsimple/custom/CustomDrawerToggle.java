package com.example.dog.mtoolbarsimple.custom;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.animation.DecelerateInterpolator;

/**
 * Created by Dog on 2018/3/19.
 */

public class CustomDrawerToggle extends ActionBarDrawerToggle
{
    public final boolean OPEN  = true;
    public final boolean CLOSE = false;

    private DrawerLayout drawerLayout;

    public CustomDrawerToggle(Activity activity, DrawerLayout drawerLayout, int openDrawerContentDescRes, int closeDrawerContentDescRes)
    {
        super(activity, drawerLayout, openDrawerContentDescRes, closeDrawerContentDescRes);
        this.drawerLayout = drawerLayout;
    }

    public CustomDrawerToggle(Activity activity, DrawerLayout drawerLayout, Toolbar toolbar, int openDrawerContentDescRes, int closeDrawerContentDescRes)
    {
        super(activity, drawerLayout, toolbar, openDrawerContentDescRes, closeDrawerContentDescRes);
        this.drawerLayout = drawerLayout;
    }

    public void changeState(boolean state)
    {
        ValueAnimator anim;
        if (state) anim = ValueAnimator.ofFloat(0, 1);
        else anim = ValueAnimator.ofFloat(1, 0);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
        {
            @Override
            public void onAnimationUpdate(ValueAnimator animation)
            {
                float slideOffset = (float) animation.getAnimatedValue();
                onDrawerSlide(drawerLayout, slideOffset);
            }
        });
        anim.setInterpolator(new DecelerateInterpolator());
        anim.setDuration(500);
        anim.start();
    }
}
