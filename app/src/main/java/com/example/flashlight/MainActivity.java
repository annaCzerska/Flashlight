package com.example.flashlight;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    private ToggleButton toggleFlashOnOff;
    private CameraManager cameraManager;
    private String getCameraID;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        toggleFlashOnOff = findViewById(R.id.toggleFlashlight);
        cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);

        try {
            getCameraID = cameraManager.getCameraIdList()[0];
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void toggleFlashLight(View view){
        if(toggleFlashOnOff.isChecked()){
            try{
                cameraManager.setTorchMode(getCameraID,true);
                Toast.makeText(MainActivity.this,"Flashlight is turned on", Toast.LENGTH_SHORT).show();
                ConstraintLayout background = findViewById(R.id.background);
                background.setBackgroundColor(this.getResources().getColor(R.color.white));
            }catch(CameraAccessException e){
                e.printStackTrace();
            }
        }else {
            try{
                cameraManager.setTorchMode(getCameraID,false);
                Toast.makeText(MainActivity.this, "Flashlight is turned off", Toast.LENGTH_SHORT).show();
                ConstraintLayout background = findViewById(R.id.background);
                background.setBackgroundColor(this.getResources().getColor(R.color.black));
            }catch(CameraAccessException e){
                e.printStackTrace();
            }
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void finish(){
        super.finish();
        try{
            cameraManager.setTorchMode(getCameraID,false);
            Toast.makeText(MainActivity.this, "Flashlight is turnet off", Toast.LENGTH_SHORT).show();
        }catch(CameraAccessException e){
            e.printStackTrace();
        }
    }

}
