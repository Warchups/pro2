package com.pmm.simarro.proyectofinal_christianllopis;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import java.io.IOException;

public class Radio extends AppCompatActivity implements View.OnClickListener {
    //Ignorar esta Activity, era un intent de fer una radio

    MediaPlayer mp;

    ImageButton botonPlayRadio;

    private String m80 = "http://play.m80radio.com/";
    private String rfm = "http://player.rockfm.fm/";
    private String o0 = "http://www.ondacero.es/emisoras/comunidad-valenciana/valencia/directo/";
    private String kfm = "http://kissfm.es/player/";
    private String rap = "http://www.rapunico.com/radio/";
    private String extremoduro = "http://es.jango.com/music/Extremoduro";

    private String emisora = "";

    //https://www.rapunico.com/radio/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio);

        botonPlayRadio = (ImageButton) findViewById(R.id.botonPlayRadio);

        botonPlayRadio.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        mp = new MediaPlayer();
        mp.setAudioStreamType(AudioManager.STREAM_MUSIC);

        /*try {
            mp.setDataSource(m80);
            mp.prepare();
            mp.start();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        new PlayerTask().execute("http://192.168.1.33/music/jesucristo_garcia.mp3");

        //http://mic.duytan.edu.vn:86/ncs.mp3
    }

    class PlayerTask extends AsyncTask<String, Void, Boolean> {

        @Override
        protected Boolean doInBackground(String... strings) {
            try {
                mp.setDataSource(strings[0]);
                mp.prepare();
            }catch (IOException e) {
                e.printStackTrace();
            }

            return true;
        }

        @Override
        protected void onPostExecute(Boolean bool) {
            super.onPostExecute(bool);
            if (mp.isPlaying())
                mp.pause();
            else
                mp.start();
        }
    }
}
