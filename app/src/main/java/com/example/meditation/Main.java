package com.example.meditation;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.Objects;

public class Main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainv);

        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawLayout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.navview);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_open, R.string.navigation_close);
        toggle.setDrawerIndicatorEnabled(false);
        toggle.setHomeAsUpIndicator(R.drawable.hamburger);

        toggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        Intent asd = getIntent();
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        TabLayout.Tab Main = tabLayout.newTab();
        Main.setIcon(R.drawable.home);
        tabLayout.addTab(Main, 0, true);

        TabLayout.Tab Sounds = tabLayout.newTab();
        Sounds.setIcon(R.drawable.sounds);
        tabLayout.addTab(Sounds, 1, true);

        TabLayout.Tab Profile = tabLayout.newTab();
        Profile.setIcon(R.drawable.profile);
        tabLayout.addTab(Profile, 2, true);

        String perehod = asd.getStringExtra("prof");

        Fragment fragment;
        if (Objects.equals(perehod, "Profile")) {
            fragment = new PageFragmentProfile();
            tabLayout.getTabAt(2).select();
        }
        else {
            fragment = new PageFragmentMain();
            tabLayout.getTabAt(0).select();
        }
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frame, fragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commit();

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Fragment fragment = null;
                switch (tab.getPosition()) {
                    case 1:
                        fragment = new PageFragmentSounds();
                        break;
                    case 2:
                        fragment = new PageFragmentProfile();
                        break;
                    case 0:
                    default:
                        fragment = new PageFragmentMain();
                }
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.frame, fragment);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.commit();
            }
            @Override
                public void onTabUnselected(TabLayout.Tab tab) {

                }
            @Override
                public void onTabReselected(TabLayout.Tab tab) {
                }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        Fragment fragment;
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);

        switch (item.getItemId()) {
            case R.id.toolbarProfile:
                fragment = new PageFragmentProfile();
                tabLayout.getTabAt(2).select();
                ft.replace(R.id.frame, fragment);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.commit();
                break;
            case R.id.toolbarBack:
                fragment = new PageFragmentMain();
                tabLayout.getTabAt(0).select();
                ft.replace(R.id.frame, fragment);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.commit();
                break;
        }
    return true;
    }

}