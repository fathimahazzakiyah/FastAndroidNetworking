package com.zakiyah.fathimah.fastandroidnetworking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity"; //untuk melihat log
    private EditText edNama, edJurusan, edPeminatan;
    private Button btnTambahdata, btnLihatdata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "onCreate: inisialisasi"); //untuk log pada oncreate

        edNama = findViewById(R.id.ed_nama);
        edJurusan = findViewById(R.id.ed_jurusan);
        edPeminatan = findViewById(R.id.ed_peminatan);
        btnTambahdata = findViewById(R.id.btn_tambah);
        btnLihatdata = findViewById(R.id.btn_lihat);

        AndroidNetworking.initialize(getApplicationContext());//inisialisasi library FAN
        simpanData();
    }

    private void simpanData() {
        btnTambahdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nama = edNama.getText().toString();
                String jurusan = edJurusan.getText().toString();
                String peminatan = edPeminatan.getText().toString();

                if (nama.equals("") || jurusan.equals("") || peminatan.equals("")) {
                    Toast.makeText(getApplicationContext(), "Semua data harus diisi", Toast.LENGTH_SHORT).show();
                } else {
                    tambahdata(nama, jurusan, peminatan); //memanggil fungsi tambahData()
                    edNama.setText("");//mengosongkan form setelah data berhasil ditambahkan
                    edJurusan.setText("");
                    edPeminatan.setText("");

                }

            }
        });
        btnLihatdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ReadAllActivity.class);
                startActivity(intent);
            }
        });
    }

    public void tambahdata(String nama, String jurusan, String peminatan) {
        //koneksi ke file create.php, jika menggunakan localhost gunakan ip sesuai dengan ip kamu
        AndroidNetworking.post("http://192.168.43.49/commit/create.php")
                .addBodyParameter("id_anggota", "")
                .addBodyParameter("nama_anggota", nama)//mengirimkan data nama_anggota yang akan diisi dengan varibel nama
                .addBodyParameter("jurusan", jurusan)
                .addBodyParameter("peminatan", peminatan)
                .setPriority(Priority.MEDIUM).build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //handle response
                        Log.d(TAG, "onResponse: " + response); //untuk log pada onresponse
                        Toast.makeText(getApplicationContext(), "Data berhasil ditambahkan", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(ANError error) {
                        //Handle Error
                        Log.d(TAG, "onError: Failed" + error); //untuk log pada onerror
                        Toast.makeText(getApplicationContext(), "Data gagal ditambahkan", Toast.LENGTH_SHORT).show();

                    }
                });

    }
}
