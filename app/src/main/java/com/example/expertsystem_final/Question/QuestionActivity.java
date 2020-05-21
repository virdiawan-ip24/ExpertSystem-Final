package com.example.expertsystem_final.Question;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.expertsystem_final.DBConfig.DBConfigKondisiActivity;
import com.example.expertsystem_final.DBConfig.DBConfigQuestionActivity;
import com.example.expertsystem_final.R;

public class QuestionActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView textQuestion;
    private RadioGroup radioKondisi;
    private RadioButton radioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        textQuestion = (TextView) findViewById(R.id.textQuestion);
        


    }

    @Override
    public void onClick(View v) {

    }
}
