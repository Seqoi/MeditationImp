package com.example.meditation;

import android.icu.text.UFormat;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.sql.Time;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


public class PageFragmentSounds extends Fragment {

    //TODO: Для работы плеера нужно переместить заботливо удаленные мной аудиозаписи в папку raw проекта в формате mp3!

    private RecyclerView recyclerView;
    private MusicAdapter adapter;

    public PageFragmentSounds() {
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
        return inflater.inflate(R.layout.fragment_page_sounds, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        recyclerView = getView().findViewById(R.id.audios);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new MusicAdapter(getActivity().getApplicationContext(), fillMusic());
        recyclerView.setAdapter(adapter);
    }

    public class CreateList {
        private String audio_title;
        private Integer audio_id;
        private String audio_duration;

        public String getAudio_title() {
            return audio_title;
        }
        public Integer getAudio_ID() {
            return audio_id;
        }

        public void setAudio_title(String android_version_name) { this.audio_title = android_version_name; }
        public void setAudio_duration(String android_audio_duration) { this.audio_duration = android_audio_duration; }
        public void setAudio_ID(Integer android_audio_url) { this.audio_id = android_audio_url; }
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.profile, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    private ArrayList<CreateList> fillMusic() {
        ArrayList<CreateList> list = new ArrayList<>();
        Field[] fields = R.raw.class.getFields();
        for (Field f : fields)
            try {
                CreateList createList = new CreateList();
                createList.setAudio_ID(f.getInt(null));
                createList.setAudio_title(f.getName());
                list.add(createList);
            } catch (IllegalArgumentException | IllegalAccessException e) {
                e.printStackTrace();
            }
        return list;
    }
}