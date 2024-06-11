package com.example.gymhelper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class StartActivity extends Activity {
    Button addWorkOutBtn;
    TextView dateTextView;
    ArrayAdapter<String> adapter;
    ArrayList<String> workoutList;
    ListView listView;
    private static final int ADD_WORKOUT_REQUEST_CODE = 1;
    @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_layout);

        //오늘 날짜 표시
        String currentDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        dateTextView = (TextView) findViewById(R.id.dateTextView);
        dateTextView.setText(currentDate);

        //운동 추가 버튼
        addWorkOutBtn = (Button) findViewById(R.id.addWorkoutBtn);

        //운동 리스트 뷰
        listView = (ListView) findViewById(R.id.workOutList);

        //운동 리스트
        workoutList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, workoutList);
        listView.setAdapter(adapter);

        addWorkOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addWorkOutIntent = new Intent(getApplicationContext(), AddWorkOutActivity.class);
                startActivityForResult(addWorkOutIntent, ADD_WORKOUT_REQUEST_CODE); //AddWorkOutActivity에서 값 전달받음
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == ADD_WORKOUT_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            String workout = data.getStringExtra("workout"); //key값이 workout인 객체 찾아옴
            int weight = data.getIntExtra("weight", 0);


            if(workout != null) {
                String workoutWithWeight = workout + "-" + weight + " kg";
                workoutList.add(workoutWithWeight); //리스트에 넣음
                adapter.notifyDataSetChanged();
            }
        }
    }
}