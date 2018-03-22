package com.pmm.simarro.proyectofinal_christianllopis;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.pmm.simarro.proyectofinal_christianllopis.pojo.CancionYoutube;

public class ReproductorYoutube extends YouTubeBaseActivity {

    private YouTubePlayerView youtubePlayer;
    private YouTubePlayer.OnInitializedListener onInitializedListener;
    private CancionYoutube cancionYoutube;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reproductor_youtube);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        youtubePlayer = findViewById(R.id.youtubePlayer);

        cancionYoutube = (CancionYoutube) this.getIntent().getExtras().getSerializable("CANCION");
        final String [] url = cancionYoutube.getUrl().split("v=");

        onInitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.loadVideo(url[1]);

            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };

        youtubePlayer.initialize("AIzaSyBtXubgeqmzAaD-VQK8JNtcOZ59XLn2iMk", onInitializedListener);

    }


}
