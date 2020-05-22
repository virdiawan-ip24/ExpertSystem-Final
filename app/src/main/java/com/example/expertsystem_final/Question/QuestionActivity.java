package com.example.expertsystem_final.Question;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
    private Button buttonNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        setupView();
    }

    private void setupView() {
        textQuestion.setText(DBConfigQuestionActivity.TAG_NAMA_QUESTION);
    }

    @Override
    public void onClick(View v) {
        textQuestion = (TextView) findViewById(R.id.textQuestion);
        radioKondisi = (RadioGroup) findViewById(R.id.radioKondisi);
        int selectedId = radioKondisi.getCheckedRadioButtonId();
        radioButton = (RadioButton) findViewById(selectedId);

        buttonNext = (Button) findViewById(R.id.button_next);
        buttonNext.setOnClickListener(this);
    }
}
