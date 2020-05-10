package com.example.expertsystem_final;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DataPasienActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextId;
    private EditText editTextNama;
    private EditText editTextUsia;
    private EditText editTextAlamat;

    private RadioGroup radioJenisKelamin;
    private RadioButton radioButton;

    private Button buttonUpdate;
    private Button buttonDelete;

    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_pasien);

        Intent intent = getIntent();

        id = intent.getStringExtra(DBConfigActivity.ID_PASIEN);

        editTextId = (EditText) findViewById(R.id.editTextId);
        editTextNama = (EditText) findViewById(R.id.editTextNama);
        editTextUsia = (EditText) findViewById(R.id.editTextUsia);
        editTextAlamat = (EditText) findViewById(R.id.editTextAlamat);

        radioJenisKelamin = (RadioGroup) findViewById(R.id.radioJenisKelamin);
        int selectedId = radioJenisKelamin.getCheckedRadioButtonId();
        radioButton = (RadioButton) findViewById(selectedId);

        buttonUpdate = (Button) findViewById(R.id.buttonUpdate);
        buttonDelete = (Button) findViewById(R.id.buttonDelete);

        buttonUpdate.setOnClickListener(this);
        buttonDelete.setOnClickListener(this);
        editTextId.setText(id);
        
        getPasien();
    }

    private void getPasien() {
        class GetPasien extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(DataPasienActivity.this,"Fetching...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                showPasien(s);
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandlerActivity rh = new RequestHandlerActivity();
                String s = rh.sendGetRequestParam(DBConfigActivity.URL_GET,id);
                return s;
            }
        }
        GetPasien getPasien = new GetPasien();
        getPasien.execute();
    }

    private void showPasien(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray(DBConfigActivity.TAG_JSON_ARRAY);
            JSONObject jsonObject1 = result.getJSONObject(0);
            String nama = jsonObject1.getString(DBConfigActivity.TAG_NAMA_PASIEN);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onClick(View v) {

    }
}
