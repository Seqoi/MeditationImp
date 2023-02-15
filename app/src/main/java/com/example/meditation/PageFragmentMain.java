package com.example.meditation;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ToggleButton;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;


public class PageFragmentMain extends Fragment {

    static final Integer[] image_ids = {
            R.drawable.previewimg1,
            R.drawable.previewimg2,
            R.drawable.previewimg1,
            R.drawable.previewimg2,
            R.drawable.previewimg1,
            R.drawable.previewimg2,
            R.drawable.previewimg1,
            R.drawable.previewimg2,
            R.drawable.previewimg1,
            R.drawable.previewimg2
    };
    private MainAdapter adapter;
    private RecyclerView recycler;

    public PageFragmentMain() {
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_page_main, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        recycler = getView().findViewById(R.id.lenta);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recycler.setLayoutManager(layoutManager);

        adapter = new MainAdapter(getContext(), image_ids);
        recycler.setAdapter(adapter);
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.profile, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

}