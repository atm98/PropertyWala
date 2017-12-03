package com.example.an160.propertywala;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Launcher extends AppCompatActivity {
    boolean isConnected;
    Context context;
    private static final int SPLASH_TIME = 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        context =  getApplicationContext();
        ConnectivityManager  check = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = check.getActiveNetworkInfo();
        isConnected = activeNetwork  != null && activeNetwork.isConnectedOrConnecting();
        if(isConnected == true){
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent nextActivity = new Intent(Launcher.this,Choice.class);
                    nextActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    nextActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(nextActivity);
                    finish();
                }
            },SPLASH_TIME);
        }
        else{
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(R.string.noInternetConnection);
            builder.setMessage("You Are currently not Connected to Wifi or Mobile Data Press Ok to Exit the App");
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            });
            builder.show();
        }

    }
}
