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

import java.sql.Time;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


public class PageFragmentSounds extends Fragment {

    public static final String[] audio_names = {
            "Darko US - Infinite Beauty",
            "Newcomer - 2031 A NEW YORK ODYSSEY",
            "Newcomer - ONCE UPON AUTUMN APRIL, BEGINS ITS WINTER MAY",
            "Sleep Token - High Water",
            "Blind Channel - Left Outside Alone"
    };

    public static final Integer[] audio_ids = {
            R.raw.infinitebeauty,
            R.raw.song2,
            R.raw.song3,
            R.raw.song4,
            R.raw.song5
    };

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

        RecyclerView recyclerView = (RecyclerView) getView().findViewById(R.id.audios);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        ArrayList<CreateList> createLists = prepareData();
        MusicAdapter adapter = new MusicAdapter(getActivity().getApplicationContext(), createLists);
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
        public String getAudio_duration() { return audio_duration; }

        public void setAudio_title(String android_version_name) { this.audio_title = android_version_name; }
        public void setAudio_duration(String android_audio_duration) { this.audio_duration = android_audio_duration; }
        public void setAudio_ID(Integer android_audio_url) { this.audio_id = android_audio_url; }
    }

    public String calcDuration(int i) {
        MediaPlayer mediaPlayer;
        Long TimeDurationMinutes;
        Long TimeDurationSeconds;
        String TimeDuration;

        mediaPlayer = MediaPlayer.create(getView().getContext(), audio_ids[i]);
        TimeDurationMinutes = TimeUnit.MILLISECONDS.toMinutes(mediaPlayer.getDuration());
        TimeDurationSeconds = TimeUnit.MILLISECONDS.toSeconds(mediaPlayer.getDuration()) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(mediaPlayer.getDuration()));mediaPlayer.release();
        TimeDuration = String.format("%02d:%02d", TimeDurationMinutes, TimeDurationSeconds);
        mediaPlayer.release();

        return TimeDuration;
    }

    private ArrayList<CreateList> prepareData() {

        ArrayList<CreateList> theaudio = new ArrayList<>();
        for(int i = 0; i < audio_ids.length; i++){
            CreateList createList = new CreateList();
            createList.setAudio_title(audio_names[i]);
            createList.setAudio_ID(audio_ids[i]);
            createList.setAudio_duration(calcDuration(i));
            theaudio.add(createList);
        }
        return theaudio;
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.profile, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
}