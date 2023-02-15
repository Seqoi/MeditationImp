package com.example.meditation;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.ViewHolder> {

    private final ArrayList<PageFragmentSounds.CreateList> musicList;
    private final Context context;
    static int stateMediaPlayer = 0;
    static int mediaPlayerSwitch = -1;
    static MediaPlayer lastMP;

    public MusicAdapter(Context context, ArrayList<PageFragmentSounds.CreateList> musicList) {
        this.context = context;
        this.musicList = musicList;
    }

    @NonNull
    @Override
    public MusicAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.audio_layout, viewGroup, false);
        return new MusicAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MusicAdapter.ViewHolder holder, int position) {

        MediaPlayer mediaPlayer;

        holder.title.setText(musicList.get(position).getAudio_title());
        mediaPlayer = MediaPlayer.create(holder.itemView.getContext(), musicList.get(position).getAudio_ID());

        Long TimeDurationMinutes = TimeUnit.MILLISECONDS.toMinutes(mediaPlayer.getDuration());
        Long TimeDurationSeconds = TimeUnit.MILLISECONDS.toSeconds(mediaPlayer.getDuration()) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(mediaPlayer.getDuration()));
        holder.duration.setText(String.format("%02d:%02d", TimeDurationMinutes, TimeDurationSeconds));
        holder.position.setText("00:00");

        holder.seekbar.setMax(mediaPlayer.getDuration());
        holder.seekbar.setProgress(mediaPlayer.getCurrentPosition());

        SeekBar.OnSeekBarChangeListener seekBarOnSeekChangeListener = new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    mediaPlayer.seekTo(progress);
                    holder.seekbar.setProgress(progress);
                    Long TimePositionMinutes = TimeUnit.MILLISECONDS.toMinutes(mediaPlayer.getCurrentPosition());
                    Long TimePositionSeconds = TimeUnit.MILLISECONDS.toSeconds(mediaPlayer.getCurrentPosition()) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(mediaPlayer.getCurrentPosition()));
                    holder.position.setText(String.format("%02d:%02d", TimePositionMinutes, TimePositionSeconds));
                }
            }
        };

        holder.seekbar.setOnSeekBarChangeListener(seekBarOnSeekChangeListener);

        holder.play.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                switch (stateMediaPlayer) {
                    case 0:
                    case 1:
                        if (mediaPlayerSwitch != holder.getAdapterPosition()) {
                            mediaPlayerSwitch = holder.getAdapterPosition();
                            mediaPlayer.start();
                            startBarUpdater(holder, mediaPlayer);
                            holder.play.setBackgroundResource(R.drawable.ic_baseline_pause_24);
                            stateMediaPlayer = 2;
                        } else {
                            mediaPlayer.start();
                            startBarUpdater(holder, mediaPlayer);
                            stateMediaPlayer = 2;
                            holder.play.setBackgroundResource(R.drawable.ic_baseline_pause_24);
                        }
                        break;
                    case 2:
                        if (mediaPlayerSwitch == holder.getAdapterPosition()) {
                        mediaPlayer.pause();
                        holder.play.setBackgroundResource(R.drawable.ic_baseline_play_arrow_24);
                        stateMediaPlayer = 1;
                        }
                        break;
                }
            }
        });
    }

    private void startBarUpdater(ViewHolder holder, MediaPlayer mediaPlayer) {
        Handler handler = new Handler();
        Runnable moveSeekBarThread = new Runnable() {
            @Override
            public void run() {
                if (mediaPlayer.isPlaying()) {
                    Long TimePositionMinutes = TimeUnit.MILLISECONDS.toMinutes(mediaPlayer.getCurrentPosition());
                    Long TimePositionSeconds = TimeUnit.MILLISECONDS.toSeconds(mediaPlayer.getCurrentPosition()) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(mediaPlayer.getCurrentPosition()));
                    holder.position.setText(String.format("%02d:%02d", TimePositionMinutes, TimePositionSeconds));
                    holder.seekbar.setProgress(mediaPlayer.getCurrentPosition());
                    handler.postDelayed(this, 1000);
                }
            }
        };

        handler.removeCallbacks(moveSeekBarThread);
        handler.postDelayed(moveSeekBarThread, 1000);

    }

    @Override
    public int getItemCount() {
        return musicList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView title;
        private final Button play;
        private final SeekBar seekbar;
        private final TextView duration;
        private final TextView position;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
                title = itemView.findViewById(R.id.song_title);
                play = itemView.findViewById(R.id.playpause);
                seekbar = itemView.findViewById(R.id.seekBar);
                duration = itemView.findViewById(R.id.duration);
                position = itemView.findViewById(R.id.position);
        }
    }
}
