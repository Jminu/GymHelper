package com.example.gymhelper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

/*
여기서는 운동이름을 사용자가 입력하고,
입력 완료 버튼을 누르면 start_layout.xml의 리스트에
입력한 운동이 표시된다.
StartActivity.java에 운동이름 전달(intent사용)
 */

public class AddWorkOutActivity extends Activity {
    EditText addWorkOutText; //입력한 운동
    Button addBtnDialog; //버튼 누르면 입력한 운동이 StartActivity로 전달

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_add_workout);

        addWorkOutText = (EditText) findViewById(R.id.addWorkoutEdtText); //운동 입력 텍스트
        addBtnDialog = (Button) findViewById(R.id.addWorkoutBtn_dialog); //운동 입력 완료 버튼

        addBtnDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //운동 이름을 workout 스트링 객체에 저장
                String workout = addWorkOutText.getText().toString().trim();

                if(!workout.isEmpty()) {
                    //인텐트에 운동 이름 전달
                    Intent intent = new Intent();
                    intent.putExtra("workout", workout); //"workout은 key, workout은 value
                    setResult(RESULT_OK, intent);
                    Toast.makeText(AddWorkOutActivity.this, "추가가 완료되었습니다.", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }
}
