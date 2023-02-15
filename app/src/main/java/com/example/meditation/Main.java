package com.example.meditation;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.tabs.TabLayout;

import java.util.Objects;

public class Main extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private ActionBarDrawerToggle toggle;
    private FragmentManager fragmentManager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainv);

        drawerLayout = findViewById(R.id.drawLayout);
        toolbar = findViewById(R.id.toolbar);
        tabLayout = findViewById(R.id.tabLayout);

        fragmentManager = getSupportFragmentManager();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_open, R.string.navigation_close);
        toggle.setDrawerIndicatorEnabled(false);
        toggle.setHomeAsUpIndicator(R.drawable.hamburger);
        toggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        Intent translation = getIntent();

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        TabLayout.Tab Main = tabLayout.newTab();
        Main.setIcon(R.drawable.home);
        tabLayout.addTab(Main, 0, true);

        TabLayout.Tab Sounds = tabLayout.newTab();
        Sounds.setIcon(R.drawable.sounds);
        tabLayout.addTab(Sounds, 1, true);

        TabLayout.Tab Profile = tabLayout.newTab();
        Profile.setIcon(R.drawable.profile);
        tabLayout.addTab(Profile, 2, true);

        if (Objects.equals(translation.getStringExtra("prof"), "Profile")) {
            openFragment(new PageFragmentProfile());
            tabLayout.getTabAt(2).select();
        }
        else {
            openFragment(new PageFragmentMain());
            tabLayout.getTabAt(0).select();
        }

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 1:
                        openFragment(new PageFragmentSounds());
                        break;
                    case 2:
                        openFragment(new PageFragmentProfile());
                        break;
                    case 0:
                    default:
                        openFragment(new PageFragmentMain());
                }
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
        switch (item.getItemId()) {
            case R.id.toolbarProfile:
                tabLayout.getTabAt(2).select();
                openFragment(new PageFragmentProfile());
                break;
            case R.id.toolbarBack:
                tabLayout.getTabAt(0).select();
                openFragment(new PageFragmentMain());
                break;
        }
        return true;
    }

    private void openFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame, fragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.commit();
    }

}