package com.teacher.vn.feature;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.teacher.vn.MainActivity;
import com.teacher.vn.R;

import java.util.ArrayList;
import java.util.List;

public class FilterActivity extends AppCompatActivity {
    private Spinner spn_class,spn_subject,spn_district,spn_gender;
    private Button btn_search;
    private int subjectId=-1;
    private int districtId=-1;
    private int gender=-1;
    private int classId=-1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        spn_class =  findViewById(R.id.spn_class);
        spn_subject =  findViewById(R.id.spn_subject);
        spn_district =  findViewById(R.id.spn_district);
        spn_gender =  findViewById(R.id.spn_gender);
        btn_search=findViewById(R.id.btn_search);
        List<String> lisClass = new ArrayList<>();
        lisClass.add("Chọn lớp");
        lisClass.add("Lớp 1");
        lisClass.add("Lớp 2");
        lisClass.add("Lớp 3");
        lisClass.add("Lớp 4");
        lisClass.add("Lớp 5");
        lisClass.add("Lớp 6");
        lisClass.add("Lớp 7");
        lisClass.add("Lớp 8");
        lisClass.add("Lớp 9");
        lisClass.add("Lớp 10");
        lisClass.add("Lớp 11");
        lisClass.add("Lớp 12");

        List<String> listSubject=new ArrayList<>();
        listSubject.add("Chọn môn");
        listSubject.add("Toán");
        listSubject.add("Tiếng việt");
        listSubject.add("Lý");
        listSubject.add("Hóa");
        listSubject.add("Anh");
        listSubject.add("Sinh");
        listSubject.add("Văn");

        List<String> listDistrict=new ArrayList<>();
        listDistrict.add("Chọn Quận");
        listDistrict.add("Quận 1");
        listDistrict.add("Quận 2");
        listDistrict.add("Quận 3");
        listDistrict.add("Quận 4");
        listDistrict.add("Quận 5");
        listDistrict.add("Quận 6");
        listDistrict.add("Quận 7");
        listDistrict.add("Quận 8");
        listDistrict.add("Quận 9");
        listDistrict.add("Quận 10");
        listDistrict.add("Quận 11");
        listDistrict.add("Quận 12");

        List<String> listGender=new ArrayList<>();
        listGender.add("Chọn giới tính");
        listGender.add("Nam");
        listGender.add("Nữ");

        ArrayAdapter<String> adapterClass = new ArrayAdapter(this, android.R.layout.simple_spinner_item,lisClass);
        ArrayAdapter<String> adapterSubject = new ArrayAdapter(this, android.R.layout.simple_spinner_item,listSubject);
        ArrayAdapter<String> adapterDictrict = new ArrayAdapter(this, android.R.layout.simple_spinner_item,listDistrict);
        ArrayAdapter<String> adapterGender = new ArrayAdapter(this, android.R.layout.simple_spinner_item,listGender);
        adapterClass.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        adapterSubject.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        adapterDictrict.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        adapterGender.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spn_class.setAdapter(adapterClass);
        spn_class.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i>0){
                    classId=i;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spn_subject.setAdapter(adapterSubject);
        spn_subject.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i>0){
                    subjectId=i;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spn_district.setAdapter(adapterDictrict);
        spn_district.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i>0){
                    districtId=i;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        spn_gender.setAdapter(adapterGender);
        spn_gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i>0){
                    if(i==1){
                        gender=i;
                    }else gender=0;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(FilterActivity.this,MainActivity.class);
                intent.putExtra("classId",classId);
                intent.putExtra("subjectId",subjectId);
                intent.putExtra("districtId",districtId);
                intent.putExtra("gender",gender);
                intent.putExtra("check",1);
                startActivity(intent);
            }
        });

    }

}
