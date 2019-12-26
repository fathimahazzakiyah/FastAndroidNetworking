package com.zakiyah.fathimah.fastandroidnetworking;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListAnggotaAdapter extends RecyclerView.Adapter<ListAnggotaAdapter.ViewHolder> {
    private List<DataAnggota> dataAnggota; //inisialisasi List dengan object DataAnggota

    //construktor ListAnggotaAdapter
    public ListAnggotaAdapter(ReadAllActivity readAllActivity, List<DataAnggota> dataAnggota) {
        this.dataAnggota = dataAnggota;
    }

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflate view yang akan digunakan yaitu layout list_anggota_row.xml
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_anggota_row, parent, false);
        ViewHolder holder = new ViewHolder(v); //inisialisasi ViewHolder
        return holder;
    } //fungsi yang dijalankan saat ViewHolder dibuat

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DataAnggota data = dataAnggota.get(position); //inisialisasi object DataAnggota
        holder.mNama.setText(data.getNamaAnggota()); //menset value view "mJurusan" sesuai data dari getJurusanAnggota();
        holder.mPeminatan.setText(data.getPeminatanAnggota()); //menset value view "mPeminatan" sesuai data dari getPeminatanAnggota();
    }

    @Override
    public int getItemCount() {
        return dataAnggota.size(); //mengambil item sesuai urutan
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView mNama, mPeminatan; //inisialisasi variabel

        public ViewHolder(View itemView) {
            super(itemView);
            mNama = itemView.findViewById(R.id.list_nama); //find layout sesuai dengan yg di list_anggota_row.xml
            mPeminatan = itemView.findViewById(R.id.list_peminatan);
        }
    }

}
