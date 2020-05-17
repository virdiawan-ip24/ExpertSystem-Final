package com.example.expertsystem_final;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.expertsystem_final.DBConfig.DBConfigPasienActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class TampilPasienActivity extends AppCompatActivity implements ListView.OnItemClickListener {

    private ListView listView;
    private String JSON_STRING;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil_semua_pasien);
        listView = (ListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(this);
        getJSON();
    }

    private void tampilSemuaPasien() {
        JSONObject jsonObject = null;
        ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String, String>>();

        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(DBConfigPasienActivity.TAG_JSON_ARRAY);

            for (int i = 0; i < result.length(); i++) {
                JSONObject jo = result.getJSONObject(i);
                String id_pasien = jo.getString(DBConfigPasienActivity.TAG_ID_PASIEN);
                String nama_pasien = jo.getString(DBConfigPasienActivity.TAG_NAMA_PASIEN);
                String jenis_kelamin = jo.getString(DBConfigPasienActivity.TAG_JENIS_KELAMIN);
                String usia_pasien = jo.getString(DBConfigPasienActivity.TAG_USIA_PASIEN);
                String alamat = jo.getString(DBConfigPasienActivity.TAG_ALAMAT);

                HashMap<String,String> pasien = new HashMap<>();
                pasien.put(DBConfigPasienActivity.TAG_ID_PASIEN,id_pasien);
                pasien.put(DBConfigPasienActivity.TAG_NAMA_PASIEN,nama_pasien);
                pasien.put(DBConfigPasienActivity.TAG_JENIS_KELAMIN,jenis_kelamin);
                pasien.put(DBConfigPasienActivity.TAG_USIA_PASIEN,usia_pasien);
                pasien.put(DBConfigPasienActivity.TAG_ALAMAT,alamat);
                list.add(pasien);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ListAdapter adapter = new SimpleAdapter(
                TampilPasienActivity.this, list, R.layout.activity_list_pasien,
                new String[] {DBConfigPasienActivity.TAG_ID_PASIEN,
                              DBConfigPasienActivity.TAG_NAMA_PASIEN,
                              DBConfigPasienActivity.TAG_JENIS_KELAMIN,
                              DBConfigPasienActivity.TAG_USIA_PASIEN,
                              DBConfigPasienActivity.TAG_ALAMAT},
                new int[]{R.id.id_pasien,
                          R.id.nama_pasien,
                          R.id.jenis_kelamin,
                          R.id.usia_pasien,
                          R.id.alamat}
        );
        listView.setAdapter(adapter);
    }

    private void getJSON() {
        class GetJSON extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(TampilPasienActivity.this,
                                              "Mengambil Data",
                                           "Mohon Tunggu...",
                                        false,
                                          false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                JSON_STRING = s;
                tampilSemuaPasien();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandlerActivity rh = new RequestHandlerActivity();
                String s = rh.sendGetRequest(DBConfigPasienActivity.URL_GET);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, TampilPasienActivity.class);
        HashMap<String,String> map = (HashMap) parent.getItemAtPosition(position);
        String id_pasien = map.get(DBConfigPasienActivity.TAG_ID_PASIEN).toString();
        intent.putExtra(DBConfigPasienActivity.ID_PASIEN,id);
        startActivity(intent);
    }
}
