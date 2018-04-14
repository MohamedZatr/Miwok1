package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Mohamed Ramadan on 06/02/2018.
 */

public class WordAdapter extends ArrayAdapter<Word> implements AdapterView.OnItemClickListener {

    private AudioManager audioManager = (AudioManager) getContext().getSystemService(Context.AUDIO_SERVICE);
    private int mColorId;
    private Word word;
    private MediaPlayer player;
    private MediaPlayer.OnCompletionListener mOnCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };

    public WordAdapter(@NonNull Context context, @NonNull List<Word> objects, int colorId) {
        super(context, 0, objects);
        this.mColorId = colorId;
    }


    private AudioManager.OnAudioFocusChangeListener focusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int i) {
            if (i == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || i == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                player.pause();
                player.seekTo(0);
            } else if (i == AudioManager.AUDIOFOCUS_GAIN) {
                player.start();

            } else if (i == AudioManager.AUDIOFOCUS_LOSS) {
                releaseMediaPlayer();
            }

        }
    };

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = convertView;
        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.single_itme_list, parent, false);
        }
        word = getItem(position);
        //set text of Miwok
        RelativeLayout relativeLayout = (RelativeLayout) view.findViewById(R.id.liner_text);
        relativeLayout.setBackgroundResource(mColorId);
        TextView textView = (TextView) view.findViewById(R.id.miwok_text);
        textView.setText(word.getMiwokWord());
        // set text of defulte
        TextView textView1 = (TextView) view.findViewById(R.id.english_text);
        textView1.setText(word.getDefulteWord());

        if (word.hasImage()) {
            ImageView imageView = (ImageView) view.findViewById(R.id.image_icon);
            imageView.setImageResource(word.getImageResourceId());
            imageView.setVisibility(View.VISIBLE);
        }
        //set the image

        return view;
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        word = getItem(i);
        releaseMediaPlayer();
        int res = audioManager.requestAudioFocus(focusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
        if (res == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
            player = MediaPlayer.create(getContext(), word.getsoundResourse());
            player.start();
            //setup a liseneron the media player , sothat we cane stoped and release
            // medai player once the sound has finished playing
            player.setOnCompletionListener(mOnCompletionListener);
        }

    }

    public void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (player != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            player.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            player = null;
            audioManager.abandonAudioFocus(focusChangeListener);
        }
    }
}
