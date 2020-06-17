package com.example.expertsystem_final.Question;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.expertsystem_final.DBConfig.DBConfigKnowledgeActivity;
import com.example.expertsystem_final.DBConfig.DBConfigQuestionActivity;
import com.example.expertsystem_final.R;
import com.example.expertsystem_final.RequestHandlerActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class JawabQuestionActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView textQuestion;
    private RadioGroup radioKondisi;
    private RadioButton radioButton;
    private Button buttonNext;
    private String JSON_STRING;

    int id_question = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        setupLayout();
    }

    private void setupLayout() {
        textQuestion = (TextView) findViewById(R.id.textQuestion);
        radioKondisi = (RadioGroup) findViewById(R.id.radioKondisi);
        int selectedId = radioKondisi.getCheckedRadioButtonId();
        radioButton = (RadioButton) findViewById(selectedId);

        buttonNext = (Button) findViewById(R.id.button_next);
        buttonNext.setOnClickListener(this);

    }

    private void addQuestion() {
        final String kondisi = (String) radioButton.getText();
        JSONObject jsonObject = null;

        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray question = jsonObject.getJSONArray(DBConfigQuestionActivity.TAG_JSON_ARRAY);

            for (int i=0; i<question.length(); i++) {
                JSONObject jo = question.getJSONObject(i);
                String nama_question = jo.getString(DBConfigQuestionActivity.TAG_NAMA_QUESTION);
                textQuestion.setText(nama_question);

                class addQuestion extends AsyncTask<Void,Void,String> {

                    ProgressDialog loading;

                    @Override
                    protected void onPreExecute() {
                        super.onPreExecute();
                        loading = ProgressDialog.show(JawabQuestionActivity.this,
                                "Menambahkan",
                                "Tunggu",
                                false,false);
                    }

                    @Override
                    protected void onPostExecute(String s) {
                        super.onPostExecute(s);
                        loading.dismiss();
                        Toast.makeText(JawabQuestionActivity.this,s,Toast.LENGTH_LONG).show();
                    }

                    @Override
                    protected String doInBackground(Void... v) {
                        double  prosenKlinis        = 0.0;
                        double  resultKlinis        = 0.0;
                        double  prosenPenyakit      = 0.0;
                        JSONObject jo = null;
                        ArrayList<HashMap<String,String>> list = new ArrayList<>();


                        try {
                            jo = new JSONObject(JSON_STRING);
                            JSONArray result = jo.getJSONArray(DBConfigKnowledgeActivity.TAG_JSON_ARRAY);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        return null;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {

    }
}
