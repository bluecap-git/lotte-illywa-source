package com.bluecapsystem.lotte.illywa.sample.player;

import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.FragmentActivity;

import com.bluecapsystem.fmPlayer.FMPlayer;
import com.bluecapsystem.lotte.illywa.R;


public class FMPlayerView extends FragmentActivity {


	private static final String LOG_TAG = "FMPlayerView";
	private FMPlayer fmPlayer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fm_player_view);


		final EditText txtPath = (EditText) findViewById(R.id.tx_path);

		final Button btnPlay = (Button) findViewById(R.id.btn_play);
		btnPlay.setOnClickListener( new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				String filePath = txtPath.getText().toString();


				fmPlayer.setDataSource(filePath);
				Log.d(LOG_TAG,"=============================================" + fmPlayer.getDuration());

			}
		});

		((Button) findViewById(R.id.btn_pause)).setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {

				Log.d(LOG_TAG, "is Playing ?>>>>>>>>>>>>>>>>>>>>>>>>>." + fmPlayer.isPlaying());

				if(fmPlayer.isPlaying())
					fmPlayer.pause();
				else
					fmPlayer.play();

			}
		});



		((Button) findViewById(R.id.btn_stop)).setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				if(fmPlayer.isPlaying() == true) {
					fmPlayer.stop();
				}else {
					fmPlayer.play();
				}
			}
		});


		((Button) findViewById(R.id.btn_seek)).setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				fmPlayer.seekTo(0);
			}
		});


		final SurfaceView surfaceView = (SurfaceView) findViewById(R.id.surfaceview);
		fmPlayer = new FMPlayer(surfaceView, null);
		fmPlayer.init();

	}


	@Override
	protected void onDestroy() {
		super.onDestroy();

		if(fmPlayer != null){
			fmPlayer.destroy();;
		}
	}

}