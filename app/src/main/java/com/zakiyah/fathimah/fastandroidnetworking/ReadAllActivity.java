package com.zakiyah.fathimah.fastandroidnetworking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ReadAllActivity extends AppCompatActivity {
    private static final String TAG = "ReadAllActivity";
    private List<DataAnggota> dataAnggota;
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_all);

        recyclerView = findViewById(R.id.rcl_read); //findId recyclerView yg ada pada activity_read_all.xml

        recyclerView.setHasFixedSize(true); //agar recyclerView tergambar lebih cepat
        recyclerView.setLayoutManager(new LinearLayoutManager(this)); //menset layout manager sebagai LinearLayout(scroll kebawah)
        dataAnggota = new ArrayList<>(); //arraylist untuk menyimpan banyak data anggota
        AndroidNetworking.initialize(getApplicationContext()); //inisialisasi FAN
        getData(); // pemanggilan fungsi get data
    }

    public void getData() {
        //koneksi ke file tampil.php, jika menggunakan localhost gunakan ip sesuai dengan ip kamu
        AndroidNetworking.get("http://192.168.43.49/commit/tampil.php")
                .setPriority(Priority.LOW).build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, "onResponse : " + response);
                        {
                            //mengambil data dari JSON array pada tampil.php
                            try {
                                for (int i = 0; i < response.length(); i++) {
                                    JSONObject data = response.getJSONObject(i);

                                    dataAnggota.add(new DataAnggota(
                                            data.getInt("id_anggota"), //"name:/String" diisi sesuai dengan yang di JSON pada tampil.php
                                            data.getString("nama_anggota"),
                                            data.getString("jurusan"),
                                            data.getString("peminatan")
                                    ));
                                }
                                //men inisialisasi adapter RecyclerView yang sudah dibuat sebelumnya
                                ListAnggotaAdapter adapter = new ListAnggotaAdapter(ReadAllActivity.this, dataAnggota);
                                recyclerView.setAdapter(adapter); //menset adapter yang akan digunakan pada recyclerView
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onError(ANError error) {
                        Log.d(TAG, "onError : " + error);
                    }
                });
    }
}
