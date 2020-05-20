package com.example.expertsystem_final.Pasien;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
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
import com.example.expertsystem_final.R;
import com.example.expertsystem_final.RequestHandlerActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;

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

        id = intent.getStringExtra(DBConfigPasienActivity.ID_PASIEN);

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
                String s = rh.sendGetRequestParam(DBConfigPasienActivity.URL_GET,id);
                return s;
            }
        }
        GetPasien getPasien = new GetPasien();
        getPasien.execute();
    }

    private void showPasien(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray(DBConfigPasienActivity.TAG_JSON_ARRAY);
            JSONObject jsonObject1 = result.getJSONObject(0);
            String nama = jsonObject1.getString(DBConfigPasienActivity.TAG_NAMA_PASIEN);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updatePasien() {
        final String nama = editTextNama.getText().toString().trim();
        final String usia = editTextUsia.getText().toString().trim();
        final String alamat = editTextAlamat.getText().toString().trim();
        final String jenis_kelamin = (String) radioButton.getText();

        class UpdatePasien extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(DataPasienActivity.this,
                                                "Updating",
                                            "Wait...",
                                        false,
                                          false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(DataPasienActivity.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... params) {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put(DBConfigPasienActivity.KEY_ID_PASIEN,id);
                hashMap.put(DBConfigPasienActivity.KEY_NAMA_PASIEN,nama);
                hashMap.put(DBConfigPasienActivity.KEY_USIA_PASIEN,usia);
                hashMap.put(DBConfigPasienActivity.KEY_ALAMAT,alamat);
                hashMap.put(DBConfigPasienActivity.KEY_JENIS_KELAMIN,jenis_kelamin);

                RequestHandlerActivity rh = new RequestHandlerActivity();
                String s = rh.sendPostRequest(DBConfigPasienActivity.URL_UPDATE,hashMap);
                return s;
            }
        }
        UpdatePasien up = new UpdatePasien();
        up.execute();
    }

    private void deletePasien() {
        class DeletePasien extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(DataPasienActivity.this,
                                                "Updating",
                                            "Wait...",
                                        false,
                                          false);
            }

            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(DataPasienActivity.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandlerActivity rh = new RequestHandlerActivity();
                String s = rh.sendGetRequestParam(DBConfigPasienActivity.URL_DELETE,id);
                return s;
            }
        }
        DeletePasien dp = new DeletePasien();
        dp.execute();
    }

    private void confirmDeletePasien() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Apakah Anda Yakin Ingin Menghapus?");

        alertDialogBuilder.setPositiveButton("Ya",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deletePasien();
                        startActivity(new Intent(DataPasienActivity.this, TampilPasienActivity.class));
                    }
                });

        alertDialogBuilder.setNegativeButton("Tidak",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    @Override
    public void onClick(View v) {
        if (v == buttonUpdate) {
            updatePasien();
        }

        if (v == buttonDelete) {
            confirmDeletePasien();
        }
    }
}
