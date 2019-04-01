package com.teacher.vn;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.teacher.vn.feature.FilterActivity;
import com.teacher.vn.feature.HomeFragment;
import com.teacher.vn.feature.PostFragment;
import com.teacher.vn.feature.ProfileFragment;
import com.teacher.vn.feature.SaveFragment;

public class MainActivity extends AppCompatActivity {
    int check=0;
    private int subjectId=-1;
    private int districtId=-1;
    private int gender=-1;
    private int classId=-1;
    private ActionBar toolbar;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fragment = new HomeFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_post:
                    fragment = new PostFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_save:
                    fragment = new SaveFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_profile:
                    fragment = new ProfileFragment();
                    loadFragment(fragment);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            check=extras.getInt("check");
            classId = extras.getInt("classId");
            subjectId = extras.getInt("subjectId");
            districtId = extras.getInt("districtId");
            gender = extras.getInt("gender");
        }
        toolbar = getSupportActionBar();
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        loadFragment(new HomeFragment());
    }
    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if(check!=0){
            Bundle data = new Bundle();//create bundle instance
            data.putInt("classId",classId);
            data.putInt("subjectId",subjectId);
            data.putInt("districtId",districtId);
            data.putInt("gender",gender);
            fragment.setArguments(data);
        }

        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.filter:
                Intent intent=new Intent(MainActivity.this,FilterActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
