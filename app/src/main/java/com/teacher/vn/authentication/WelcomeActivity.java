package com.teacher.vn.authentication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.github.ybq.android.spinkit.style.ThreeBounce;
import com.teacher.vn.R;

public class WelcomeActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        progressBar=findViewById(R.id.spin_kit);
        ThreeBounce threeBounce=new ThreeBounce();
        progressBar.setIndeterminateDrawable(threeBounce);
        final Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    progressBar.setVisibility(View.INVISIBLE);
                }catch (Exception e){

                }finally {

                    startActivity(new Intent(WelcomeActivity.this,SigninActivity.class));
                }
            }
        });
        thread.start();
    }
}
