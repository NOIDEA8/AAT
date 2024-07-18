package com.example.mvp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.mvp.R;
import com.example.mvp.model.Song;
import com.example.mvp.model.SongCallback;
import com.example.mvp.presenter.playerPresenter;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    playerPresenter playerInstance;
    ImageButton back;
    ImageButton next;
    ImageButton play;
    ImageButton previous;
    ImageView albumCover;
    TextView songName;
    TextView artistName;
    Song temp=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initView();
        playerInstance=playerPresenter.getInstance(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.ib_back:
                playerInstance.clickBack();
                break;
            case R.id.ib_next:
               playerInstance.clickNext(new SongCallback() {

                    @Override
                    public void onSuccess(Song song) {
                        songShow(song);
                    }

                    @Override
                    public void onFailure(Throwable t) {

                    }
                });
                break;
            case R.id.ib_play:
                break;
            case R.id.ib_previous:
                temp=playerInstance.clickPrevious();
                if(temp==null){
                    Toast.makeText(this,"没有更多了",Toast.LENGTH_LONG).show();
                }
                else{songShow(temp);}
                break;
        }
    }
    private void songShow(Song song){
        String url=song.getUrl();
        String picurl=song.getPicurl();
        RequestOptions requestOptions=new RequestOptions()
                .placeholder(R.drawable.logo)
                .error(R.drawable.error)
                .fallback(R.drawable.logo)
                .centerCrop();
        Glide.with(this)
                .load(picurl)
                .apply(requestOptions)
                .into(albumCover);
        artistName.setText(song.getArtistsname());
        songName.setText(song.getName());

    }

    private void initView() {
        back=findViewById(R.id.ib_back);
        next=findViewById(R.id.ib_next);
        play=findViewById(R.id.ib_play);
        previous=findViewById(R.id.ib_previous);
        albumCover=findViewById(R.id.iv_albumCover);
        artistName=findViewById(R.id.tv_artistName);
        songName=findViewById(R.id.tv_songName);


        back.setOnClickListener(this);
        next.setOnClickListener(this);
        play.setOnClickListener(this);
        previous.setOnClickListener(this);
    }
}