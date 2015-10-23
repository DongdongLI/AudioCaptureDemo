package com.example.audiocapturedemo;

import java.io.IOException;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	Button recBtn;
	Button stopBtn;
	Button playBtn;
	
	MediaRecorder mediaRecorder;
	String outputFile;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        recBtn=(Button)findViewById(R.id.record);
        stopBtn=(Button)findViewById(R.id.stop);
        playBtn=(Button)findViewById(R.id.play);
        
        stopBtn.setEnabled(false);
        playBtn.setEnabled(false);
        outputFile=Environment.getExternalStorageDirectory().getAbsolutePath()+"/recording.3gp";
        
        mediaRecorder=new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        mediaRecorder.setOutputFile(outputFile);
        
        recBtn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				try {
					mediaRecorder.prepare();
				} catch (Exception e) {
					e.printStackTrace();
				} 
				mediaRecorder.start();
				recBtn.setEnabled(false);
				stopBtn.setEnabled(true);
				Toast.makeText(getApplicationContext(), "start...", Toast.LENGTH_SHORT).show();
			}
		});
        
        stopBtn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				mediaRecorder.stop();
				mediaRecorder.release();
				mediaRecorder=null;
				
				stopBtn.setEnabled(false);
				playBtn.setEnabled(true);
			}
		});
        
        playBtn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				MediaPlayer mediaPlayer=new MediaPlayer();
				try {
					mediaPlayer.setDataSource(outputFile);
					mediaPlayer.prepare();
					
				} catch (Exception e) {
					e.printStackTrace();
				} 
				mediaPlayer.start();
			}
		});
	}
}
