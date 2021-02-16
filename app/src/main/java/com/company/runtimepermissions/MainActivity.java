package com.company.runtimepermissions;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.btn);




        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int checkPermission= ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE);

                if(checkPermission != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(MainActivity.this
                            ,new String[]{Manifest.permission.CALL_PHONE},1);
                }
                else{
                    Uri uri = Uri.parse("tel:24436457");
                    Intent intent = new Intent(Intent.ACTION_CALL, uri);
                    if (intent.resolveActivity(getPackageManager()) != null) {
                        startActivity(intent);
                    }
                }

            }
        });
    }


    @SuppressLint("MissingPermission")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1:{
                if(grantResults.length>0&& grantResults[0]== PackageManager.PERMISSION_GRANTED){
                    Uri uri = Uri.parse("tel:24436457");
                    Intent intent = new Intent(Intent.ACTION_CALL, uri);
                    if (intent.resolveActivity(getPackageManager()) != null) {
                        startActivity(intent);
                    }
                }
                else{

                }
            }
        }
    }
}