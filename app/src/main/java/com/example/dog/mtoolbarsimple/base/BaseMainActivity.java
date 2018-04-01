package com.example.dog.mtoolbarsimple.base;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.example.dog.mtoolbarsimple.R;
import com.example.dog.mtoolbarsimple.adapter.PagerAdapter;
import com.example.dog.mtoolbarsimple.custom.CustomDrawerToggle;
import com.example.dog.mtoolbarsimple.custom.CustomViewPager;

import java.util.List;

/**
 * Created by Dog on 2018/3/30.
 */

public class BaseMainActivity extends AppCompatActivity
{
    // listener
    private NavigationView.OnNavigationItemSelectedListener navigation_item_selected_listener = null;

    // toolbar
    private Toolbar            mToolbar;
    private DrawerLayout       drawer;
    private CustomDrawerToggle toggle;
    private NavigationView     navigationView;

    // tab layout
    private TabLayout            tabLayout;
    private CustomViewPager      pager;
    private FragmentPagerAdapter pagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_main);

        findView();
        init_actionBar();
    }

    private void findView()
    {
        mToolbar = findViewById(R.id.toolbar_main);
        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
    }

    private void init_actionBar()
    {
        mToolbar.setTitle("Base ToolBar Title");
        mToolbar.setTitleTextColor(Color.WHITE);
        mToolbar.setSubtitle("Base Subtitle");
        mToolbar.setSubtitleTextColor(Color.RED);
        setSupportActionBar(mToolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);


        toggle = new CustomDrawerToggle(this,
                drawer, mToolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        // first item selection
//        MenuItem first = navigationView.getMenu().getItem(0);
//        navigationView.setCheckedItem(first.getItemId());
    }

    public void init_viewPager(List<Fragment> fragments, String[] tab_texts)
    {
        if (fragments == null) {
            try {
                throw new Exception("fragments is null");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (tab_texts == null) {
            try {
                throw new Exception("tab_texts is null");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // TabLayout
        {
            tabLayout = findViewById(R.id.tab_layout);
            for (String text : tab_texts)
                tabLayout.addTab(tabLayout.newTab().setText(text));
        }

        // ViewPager
        {
            pager = findViewById(R.id.pager);
            pager.setPagingEnabled(false);
            pagerAdapter = new PagerAdapter(getSupportFragmentManager(), fragments);
            pager.setAdapter(pagerAdapter);
        }

        // Listener
        {
            tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener()
            {
                @Override
                public void onTabSelected(TabLayout.Tab tab)
                {
                    int pos = tab.getPosition();
                    pager.setCurrentItem(pos);
                    Menu menu = navigationView.getMenu();
                    if (menu != null) {
                        int item_size = navigationView.getMenu().size();
                        if (item_size == tabLayout.getTabCount()) {
                            navigationView.setCheckedItem(
                                    navigationView.getMenu().getItem(pos).getItemId()
                            );
                        }
                    }
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab)
                {

                }

                @Override
                public void onTabReselected(TabLayout.Tab tab)
                {

                }
            });
            pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        }
    }

    public void setNavigationItemSelectedListener(NavigationView.OnNavigationItemSelectedListener navigation_item_selected_listener)
    {
        this.navigation_item_selected_listener = navigation_item_selected_listener;
        if (navigation_item_selected_listener != null)
            navigationView.setNavigationItemSelectedListener(navigation_item_selected_listener);
    }

    public TabLayout getTabLayout()
    {
        return this.tabLayout;
    }

    public ViewPager getViewPager()
    {
        return this.pager;
    }


    @Override
    public void onBackPressed()
    {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


}
