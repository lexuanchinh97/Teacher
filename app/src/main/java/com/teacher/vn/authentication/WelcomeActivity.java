package com.teacher.vn.authentication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.github.ybq.android.spinkit.style.ThreeBounce;
import com.teacher.vn.MainActivity;
import com.teacher.vn.R;
import com.teacher.vn.adapter.SliderAdapter;

public class WelcomeActivity extends AppCompatActivity {

    private SliderAdapter sliderAdapter;
    private WrapContentViewPager mMsgSelectionPager;
    private TabLayout tabLayout;
    private Button btnLogin;
    private Button btnSignup;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        mapping();
        sliderAdapter = new SliderAdapter(this);
        mMsgSelectionPager.setAdapter(sliderAdapter);
        tabLayout.setupWithViewPager(mMsgSelectionPager, true);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WelcomeActivity.this,SigninActivity.class));
            }
        });
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WelcomeActivity.this,SignupActivity.class));
            }
        });

    }

    public void mapping(){
        mMsgSelectionPager = (WrapContentViewPager) findViewById(R.id.slideViewPaper);
        tabLayout = (TabLayout) findViewById(R.id.dotsLayout);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnSignup =(Button) findViewById(R.id.btnSignup);
    }

    WrapContentViewPager.OnPageChangeListener viewListener = new WrapContentViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int i) {
            // addDotsIndicator(i);
        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };

}
