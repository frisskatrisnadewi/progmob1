package com.friska.myapplication.adapter_controller_data_util_volley;

public class Data_Volley {

    // mmebuat objek untuk setiap item yang akan diparsing JSOn
    private String id,nama,alamat,nohp;

    public Data_Volley(){
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNohp() {
        return nohp;
    }

    public void setNohp(String nohp) {
        this.nohp = nohp;
    }

    public Data_Volley(String id, String nama, String alamat, String nohp){
        this.id =  id;
        this.nama = nama;
        this.alamat = alamat;
        this.nohp = nohp;
    }
}
