package com.example.gymhelper;

import android.app.Activity;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Locale;

public class RecordActivity extends Activity {
    CalendarView calendarView;
    TextView recordTextView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.record_layout);

        calendarView = findViewById(R.id.calendarView);
        recordTextView = findViewById(R.id.recordTextView);

        //달력 날짜 클릭하면
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String selectedDate = String.format(Locale.getDefault(), "%04d-%02d-%02d", year, month + 1, dayOfMonth);
                loadWorkOutData(selectedDate);
            }
        });
    }

    //운동 정보 기록된 file에서 데이터 읽어오기
    private void loadWorkOutData(String selectedDate) {
        StringBuilder data = new StringBuilder();
        int k = 0; //날짜 한번만 표시되게 하려고

        try {
            FileInputStream fis = openFileInput("workout_data.txt"); //파일 스트림 열고
            InputStreamReader isr = new InputStreamReader(fis); //연 파일 스트림에서 읽고
            BufferedReader reader = new BufferedReader(isr); //읽어온거 버퍼리더에 저장

            String line;
            boolean isDateFound = false;

            while((line = reader.readLine()) != null) { //한 라인씩 계속 읽어댐
                if(line.contains("Date : " + selectedDate)) { //특정 날짜 있으면
                    isDateFound = true; //true로 바꾸고
                    if(k == 0) {
                        data.append(line).append("\n"); //date에 그 날짜 저장
                        k = 1;
                    }

                    //읽은 날짜 아래로 추가 5줄을 더 읽어야함(운동 정보)
                    for(int i = 0; i < 5; i++) {
                        if((line = reader.readLine()) != null) {
                            data.append(line).append("\n");
                        }
                    }
                    data.append("\n");
                }
            }

            k = 0;

            if(isDateFound) {
                recordTextView.setText(data.toString());
            }
            else {
                recordTextView.setText("이날은 운동을 안했습니다!");
            }
        } catch (IOException e) {
            e.printStackTrace();
            recordTextView.setText("Error");
        }
    }
}
