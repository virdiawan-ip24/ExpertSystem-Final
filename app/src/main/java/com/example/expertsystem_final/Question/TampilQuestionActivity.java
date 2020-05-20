package com.example.expertsystem_final.Question;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.expertsystem_final.DBConfig.DBConfigQuestionActivity;
import com.example.expertsystem_final.R;
import com.example.expertsystem_final.RequestHandlerActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class TampilQuestionActivity extends AppCompatActivity implements ListView.OnItemClickListener{

    private ListView listView;
    private String JSON_STRING;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil_question);
        listView = (ListView) findViewById(R.id.listQuestionView);
        listView.setOnItemClickListener(this);
        getJSON();
    }

    private void getJSON() {
        class GetJSON extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(TampilQuestionActivity.this,
                                                "Mengambil Data...",
                                             "Tunggu...",
                                         false,
                                           false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                JSON_STRING = s;
                tampilSemuaQuestion();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandlerActivity rh = new RequestHandlerActivity();
                String s = rh.sendGetRequest(DBConfigQuestionActivity.URL_GET);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }

    private void tampilSemuaQuestion() {
        JSONObject jsonObject = null;
        ArrayList<HashMap<String,String>> list = new ArrayList<>();

        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(DBConfigQuestionActivity.TAG_JSON_ARRAY);

            for (int i=0; i<result.length(); i++) {
                JSONObject jo = result.getJSONObject(i);
                String id_question = jo.getString(DBConfigQuestionActivity.TAG_ID_QUESTION);
                String nama_question = jo.getString(DBConfigQuestionActivity.TAG_NAMA_QUESTION);

                HashMap<String,String> question = new HashMap<>();
                question.put(DBConfigQuestionActivity.TAG_ID_QUESTION,id_question);
                question.put(DBConfigQuestionActivity.TAG_NAMA_QUESTION,nama_question);
                list.add(question);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this,TampilQuestionActivity.class);
        HashMap map = (HashMap) parent.getItemAtPosition(position);
        String id_question = map.get(DBConfigQuestionActivity.TAG_ID_QUESTION).toString();
        intent.putExtra(DBConfigQuestionActivity.ID_QUESTION,id_question);
        startActivity(intent);
    }
}
