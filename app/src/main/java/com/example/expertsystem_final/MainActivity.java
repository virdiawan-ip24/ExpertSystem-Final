package com.example.expertsystem_final;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.expertsystem_final.Pasien.AddPasienActivity;
import com.example.expertsystem_final.Question.QuestionActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button_menu_pasien = (Button) findViewById(R.id.button_menu_pasien);
        Button button_menu_question = (Button) findViewById(R.id.button_menu_question);

        button_menu_pasien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, AddPasienActivity.class);
                startActivity(i);
            }
        });

        button_menu_question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, QuestionActivity.class);
                startActivity(i);
            }
        });

    }
}
