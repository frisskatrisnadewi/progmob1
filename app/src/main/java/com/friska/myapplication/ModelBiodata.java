package com.friska.myapplication;

/*import android.support.annotation.NonNull;*/

import java.io.Serializable;

import androidx.annotation.NonNull;

public class ModelBiodata implements Serializable {
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

    public String getKabupaten() {
        return kabupaten;
    }

    public void setKabupaten(String kabupaten) {
        this.kabupaten = kabupaten;
    }

    public String getKecamatan() {
        return kecamatan;
    }

    public void setKecamatan(String kecamatan) {
        this.kecamatan = kecamatan;
    }

    public String getKodepos() {
        return kodepos;
    }

    public void setKodepos(String kodepos) {
        this.kodepos = kodepos;
    }

    public String getJk() {
        return jk;
    }

    public void setJk(String jk) {
        this.jk = jk;
    }

    private String id= "";
    private String nama= "";
    private String alamat= "";
    private String kabupaten= "";
    private String kecamatan= "";
    private String kodepos= "";
    private String jk= "";

    @NonNull
    @Override
    public String toString() {

        return "Biodata{" +
                "id='" + id + '\'' +
                ", nama='" + nama + '\'' +
                ", alamat='" + alamat + '\'' +
                ", kabupaten='" + kabupaten + '\'' +
                ", kecamatan='" + kecamatan + '\'' +
                ", kodepos='" + kodepos + '\'' +
        '}';
    }
}
