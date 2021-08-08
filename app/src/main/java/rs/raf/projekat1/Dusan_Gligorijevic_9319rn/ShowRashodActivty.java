package rs.raf.projekat1.Dusan_Gligorijevic_9319rn;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.media.AudioAttributes;
import android.media.AudioFocusRequest;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import timber.log.Timber;

public class ShowRashodActivty extends AppCompatActivity {
    private TextView naslov, kolicina, opis;
    private ImageView btPlay, btPause;
    private ImageView btRew, btFw;
    private TextView playerPosition, playerDuration;
    private SeekBar seekBar;
    private MediaPlayer mediaPlayer;
    private AudioManager audioManager;
    private AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener;
    private AudioFocusRequest audioFocusRequest;
    private Handler handler = new Handler();
    private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_rashod_activty);
        init();

    }
    public void init(){
        btPlay = findViewById(R.id.show_play);
        btPause = findViewById(R.id.show_pause);
        naslov = findViewById(R.id.show_naslov_value);
        kolicina = findViewById(R.id.show_kolicina_value);
        opis = findViewById(R.id.show_opis_value);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String stringnaslov = (String) bundle.get("naslov");
        String stringkolicina = (String) bundle.get("kolicina");
        naslov.setText(stringnaslov);
        kolicina.setText(stringkolicina);

        initVariables();
        initPlayer();
        initRunnable();
        initListeners();
    }


    private void initPlayer() {
        // Initialize media player
        if(rs.raf.projekat1.Dusan_Gligorijevic_9319rn.fragments.Unos_Fragment.file != null)
            mediaPlayer = MediaPlayer.create(this, Uri.fromFile(rs.raf.projekat1.Dusan_Gligorijevic_9319rn.fragments.Unos_Fragment.file));
        else
            mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.song);
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);


    }

    private void initRunnable() {
        // Initialize runnable
        runnable = new Runnable() {
            @Override
            public void run() {
                // Set progress on seek bar
                seekBar.setProgress(mediaPlayer.getCurrentPosition());
                // Handler post delay for 0.5 second
                handler.postDelayed(this, 500);
            }
        };
    }
    private void initVariables() {
        /*
        playerPosition = findViewById(R.id.player_position);
        playerDuration = findViewById(R.id.player_duration);
        seekBar = findViewById(R.id.seek_bar);
        btRew = findViewById(R.id.bt_rew);
        btPlay = findViewById(R.id.bt_play);
        btPause = findViewById(R.id.bt_pause);
        btFw = findViewById(R.id.bt_fw);

         */
    }
    private void initListeners() {
        btPlay.setOnClickListener(v -> play());

        btPause.setOnClickListener(v -> pause());




        // Description of the audioFocusRequest
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            audioFocusRequest = new AudioFocusRequest.Builder(AudioManager.AUDIOFOCUS_GAIN)
                    .setAudioAttributes(new AudioAttributes.Builder()
                            .setUsage(AudioAttributes.USAGE_MEDIA)
                            .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                            .build())
                    .setAcceptsDelayedFocusGain(true)
                    .setWillPauseWhenDucked(true)
                    .setOnAudioFocusChangeListener(onAudioFocusChangeListener)
                    .build();
        }
    }

    private void pause() {
        // Hide pause button
        btPause.setVisibility(View.GONE);
        // Show play button
        btPlay.setVisibility(View.VISIBLE);
        // Pause media player
        mediaPlayer.pause();
        // Stop handler
        handler.removeCallbacks(runnable);
    }

    private void play(){
        // Request audio focus

        int result = 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            result = audioManager.requestAudioFocus(audioFocusRequest);
        }
       // if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
            Timber.e("AUDIOFOCUS_REQUEST_GRANTED");
            // Hide play button
            btPlay.setVisibility(View.GONE);
            // Show pause button
            btPause.setVisibility(View.VISIBLE);
            // Start media player
            try {
                mediaPlayer.prepare();
                mediaPlayer.start();
            } catch (IOException e) {
                e.printStackTrace();
            }


        //}



    }

    private void releaseMediaPlayer() {
        // Release resources
        if(mediaPlayer != null)
            mediaPlayer.release();
        mediaPlayer = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            audioManager.abandonAudioFocusRequest(audioFocusRequest);
        }
        // Remove and stop running threads
        handler.removeCallbacks(runnable);
        handler.removeCallbacksAndMessages(null);
        runnable = null;
        handler = null;
    }

    public synchronized void playerDuck(boolean duck) {
        if (mediaPlayer != null) {
            // Reduce the volume when ducking - otherwise play at full volume.
            mediaPlayer.setVolume(duck ? 0.2f : 1.0f, duck ? 0.2f : 1.0f);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Proverite sta se desava sa zvukom kada sklonite komentar i izadjete iz aplikacije sa home button
        //pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        releaseMediaPlayer();
    }
}