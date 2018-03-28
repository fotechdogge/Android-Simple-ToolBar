package com.example.dog.mtoolbarsimple;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.dog.mtoolbarsimple.adapter.PagerAdapter;
import com.example.dog.mtoolbarsimple.custom.CustomDrawerToggle;
import com.example.dog.mtoolbarsimple.custom.CustomViewPager;
import com.example.dog.mtoolbarsimple.fragment.FragmentActivityFour;
import com.example.dog.mtoolbarsimple.fragment.FragmentActivityOne;
import com.example.dog.mtoolbarsimple.fragment.FragmentActivityThree;
import com.example.dog.mtoolbarsimple.fragment.FragmentActivityTwo;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener

{
    // toolbar
    private Toolbar            mToolbar;
    private DrawerLayout       drawer;
    private CustomDrawerToggle toggle;
    private NavigationView     navigationView;

    // tab layout
    private TabLayout            tabLayout;
    private CustomViewPager      pager;
    private List<Fragment>       fragments;
    private FragmentPagerAdapter pagerAdapter;

    // tabs
    private String[] tab_text = {"One", "Two", "Three", "Four"};

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_main);

        init_actionBar();
        init_viewPager();
    }

    private void init_actionBar()
    {
        mToolbar = findViewById(R.id.toolbar_main);
        mToolbar.setTitle("ToolBar Title");
        mToolbar.setTitleTextColor(Color.WHITE);
        mToolbar.setSubtitle("Subtitle");
        mToolbar.setSubtitleTextColor(Color.RED);
        setSupportActionBar(mToolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);


        drawer = findViewById(R.id.drawer_layout);
        toggle = new CustomDrawerToggle(this,
                drawer, mToolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // first item selection
        MenuItem first = navigationView.getMenu().getItem(0);
        navigationView.setCheckedItem(first.getItemId());
    }

    private void init_viewPager()
    {
        // TabLayout
        {
            tabLayout = findViewById(R.id.tab_layout);
            for (String text : tab_text)
                tabLayout.addTab(tabLayout.newTab().setText(text));
        }

        // ViewPager
        {
            pager = findViewById(R.id.pager);
            pager.setPagingEnabled(false);
            fragments = new ArrayList<>();
            fragments.add(new FragmentActivityOne());
            fragments.add(new FragmentActivityTwo());
            fragments.add(new FragmentActivityThree());
            fragments.add(new FragmentActivityFour());
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
                    navigationView.setCheckedItem(
                            navigationView.getMenu().getItem(pos).getItemId()
                    );
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

    public void test()
    {
        System.err.println("test()");
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


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item)
    {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        switch (id) {
            case R.id.nav_tab1:
                pager.setCurrentItem(0, true);
                break;
            case R.id.nav_tab2:
                pager.setCurrentItem(1, true);
                break;
            case R.id.nav_tab3:
                pager.setCurrentItem(2, true);
                break;
            case R.id.nav_tab4:
                pager.setCurrentItem(3, true);
                break;

            case R.id.nav_act1:

                break;

            case R.id.nav_act2:

                break;
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
