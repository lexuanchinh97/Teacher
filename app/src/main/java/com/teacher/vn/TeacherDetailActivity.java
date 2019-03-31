package com.teacher.vn;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class TeacherDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_detail);
        int id=1;
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            id = extras.getInt("id");
        }
    }
}
