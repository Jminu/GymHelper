package com.example.gymhelper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import com.example.gymhelper.WorkOut;

public class StartActivity extends Activity {
    Button addWorkOutBtn;
    Button countPlusBtn;
    Button countMinusBtn;
    TextView dateTextView;
    WorkoutAdapter adapter; //커스텀 어댑터 생성
    ArrayList<WorkOut> workoutList;
    ListView listView;
    int selectedItemPosition = -1;


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

        //세트 수 버튼 증감 버튼
        countPlusBtn = (Button) findViewById(R.id.plusBtn);
        countMinusBtn = (Button) findViewById(R.id.minusBtn);

        //운동 리스트 뷰
        listView = (ListView) findViewById(R.id.workOutList);

        //운동 리스트
        workoutList = new ArrayList<>();
        //adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, workoutList);
        adapter = new WorkoutAdapter(this, workoutList); //커스텀 어댑터 적용
        listView.setAdapter(adapter); //커스텀 어댑터를 리스트뷰에 적용

        //운동 추가 버튼 누르면
        addWorkOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addWorkOutIntent = new Intent(getApplicationContext(), AddWorkOutActivity.class);
                startActivityForResult(addWorkOutIntent, ADD_WORKOUT_REQUEST_CODE); //AddWorkOutActivity에서 값 전달받음
            }
        });

        //리스트뷰 요소 클릭하면 활성화되고, 위치 기억함
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedItemPosition = position;
                adapter.setSelectedPosition(position);
                view.setSelected(true);
            }
        });

        //'+'버튼 누르면
        countPlusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectedItemPosition != -1) {
                    WorkOut selectedWorkout = workoutList.get(selectedItemPosition);
                    selectedWorkout.setCount(selectedWorkout.getCount() + 1);
                    adapter.notifyDataSetChanged();
                }
            }
        });

        //'-'버튼 누르면
        countMinusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectedItemPosition != -1) {
                    WorkOut selectedWorkout = workoutList.get(selectedItemPosition);
                    selectedWorkout.setCount(selectedWorkout.getCount() - 1);
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == ADD_WORKOUT_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            String workoutName = data.getStringExtra("workout"); //key값이 workout인 객체 찾아옴
            int reps = data.getIntExtra("reps", 0); //key값이 reps인 객체 찾아옴
            int weight = data.getIntExtra("weight", 0); //key값이 weight인 객체 찾아옴

            if(workoutName != null) {
                WorkOut workout = new WorkOut(workoutName, reps, weight); //찾아온 것들로 객체 운동객체 생성
                workoutList.add(workout); //리스트에 넣음
                adapter.notifyDataSetChanged();
            }
        }
    }
}