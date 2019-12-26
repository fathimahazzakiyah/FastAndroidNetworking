package com.zakiyah.fathimah.fastandroidnetworking;

public class DataAnggota {
    int idAnggota; //idMahasiswa didatabase merupakan int (Auto Increment)
    String namaAnggota; //namaAnggota didatabase merupakan string
    String jurusanAnggota;
    String peminatanAnggota;

    //construktor datamahasiswa
    public DataAnggota(int idAnggota, String namaAnggota, String jurusanAnggota, String peminatanAnggota) {
        this.idAnggota = idAnggota;
        this.namaAnggota = namaAnggota;
        this.jurusanAnggota = jurusanAnggota;
        this.peminatanAnggota = peminatanAnggota;

    }
    //getter dan setter

    public int getIdAnggota() {
        return idAnggota;
    }

    public void setIdAnggota(int idAnggota) {
        this.idAnggota = idAnggota;
    }

    public String getNamaAnggota() {
        return namaAnggota;
    }

    public void setNamaAnggota(String namaAnggota) {
        this.namaAnggota = namaAnggota;
    }

    public String getJurusanAnggota() {
        return jurusanAnggota;
    }

    public void setJurusanAnggota(String jurusanAnggota) {
        this.jurusanAnggota = jurusanAnggota;
    }

    public String getPeminatanAnggota() {
        return peminatanAnggota;
    }

    public void setPeminatanAnggota(String peminatanAnggota) {
        this.peminatanAnggota = peminatanAnggota;
    }
}
