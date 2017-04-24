package cl.aldogallardo.miartista.adapter;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cl.aldogallardo.miartista.R;
import cl.aldogallardo.miartista.background.MyAplication;
import cl.aldogallardo.miartista.models.Song;


/**
 * Created by Aldo Gallardo on 20-04-2017.
 */

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.ViewHolder>{

    private List<Song> songs = new ArrayList<Song>();
    MediaPlayer mp = new MediaPlayer();

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_song, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.title.setText(songs.get(position).getTitle());
        holder.preview = songs.get(position).getPreview();
        holder.playbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mp.isPlaying()){
                    holder.playbtn.setBackgroundResource(R.mipmap.ic_play_circle_outline_black_24dp);
                    mp.reset();
                }else{

                try {
                    Uri path = Uri.parse(songs.get(position).getPreview());
                    mp.setDataSource(MyAplication.getAppContext(),path);
                    mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    mp.prepareAsync();

                    mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mp) {
                            mp.start();
                            holder.playbtn.setBackgroundResource(R.mipmap.ic_stop_black_24dp);

                        }
                    });

                    mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            holder.playbtn.setBackgroundResource(R.mipmap.ic_play_circle_outline_black_24dp);
                            mp.reset();
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }}

            }

        });



    }



    @Override
    public int getItemCount() {
        return songs.size();
    }

    public void update(Song[] songs) {
        if (songs != null) {
            List<Song> songList = new ArrayList<Song>(Arrays.asList(songs));
            this.songs.addAll(songList);
            notifyDataSetChanged();
        }
    }



    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        String preview;
        Button playbtn;

        public ViewHolder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.nameTv);
            playbtn = (Button) itemView.findViewById(R.id.playBtn);
            preview = "";

        }

    }

}
