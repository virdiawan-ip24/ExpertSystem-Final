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
import android.widget.Toast;

import com.example.expertsystem_final.DBConfig.DBConfigPasienActivity;

import java.util.HashMap;

public class AddPasienActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextNama;
    private EditText editTextUsia;
    private EditText editTextAlamat;

    private RadioGroup radioJenisKelamin;
    private RadioButton radioButton;

    private Button buttonAdd;
    private Button buttonView;
    private Button buttonKembali;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pasien);

        editTextNama = (EditText) findViewById(R.id.editTextNama);
        editTextUsia = (EditText) findViewById(R.id.editTextUsia);
        editTextAlamat = (EditText) findViewById(R.id.editTextAlamat);

        radioJenisKelamin = (RadioGroup) findViewById(R.id.radioJenisKelamin);
        int selectedId = radioJenisKelamin.getCheckedRadioButtonId();
        radioButton = (RadioButton) findViewById(selectedId);

        buttonAdd = (Button) findViewById(R.id.buttonAdd);
        buttonView = (Button) findViewById(R.id.buttonView);
        buttonKembali = (Button) findViewById(R.id.button_kembali);

        buttonAdd.setOnClickListener(this);
        buttonView.setOnClickListener(this);
        buttonKembali.setOnClickListener(this);
    }

    private void addPasien() {
        final String nama = editTextNama.getText().toString().trim();
        final String usia = editTextNama.getText().toString().trim();
        final String alamat = editTextNama.getText().toString().trim();
        final String jenis_kelamin = (String) radioButton.getText();

        class AddPasien extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(AddPasienActivity.this,
                                                "Menambahkan...",
                                             "Tunggu...",
                                         false,
                                           false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(AddPasienActivity.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(DBConfigPasienActivity.KEY_NAMA_PASIEN,nama);
                params.put(DBConfigPasienActivity.KEY_USIA_PASIEN,usia);
                params.put(DBConfigPasienActivity.KEY_ALAMAT,alamat);
                params.put(DBConfigPasienActivity.KEY_JENIS_KELAMIN,jenis_kelamin);

                RequestHandlerActivity rh = new RequestHandlerActivity();
                String res = rh.sendPostRequest(DBConfigPasienActivity.URL_ADD,params);
                return res;
            }
        }

        AddPasien ap = new AddPasien();
        ap.execute();
    }

    @Override
    public void onClick(View v) {
        if (v == buttonAdd) {
            addPasien();
        }

        if (v == buttonView) {
            startActivity(new Intent(this, TampilPasienActivity.class));
        }

        if (v == buttonKembali) {
            startActivity(new Intent(this,MainActivity.class));
        }
    }
}
