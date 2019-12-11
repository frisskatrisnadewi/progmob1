package com.friska.myapplication.model_recyclerview;

public class ListPesananModel {
    private String JudulPesanan;
    private String StatusPesanan;
    private int ThumbnailPesanan;

    public String getJudulPesanan() {
        return JudulPesanan;
    }

    public void setJudulPesanan(String judulPesanan) {
        JudulPesanan = judulPesanan;
    }

    public String getStatusPesanan() {
        return StatusPesanan;
    }

    public void setStatusPesanan(String statusPesanan) {
        StatusPesanan = statusPesanan;
    }

    public int getThumbnailPesanan() {
        return ThumbnailPesanan;
    }

    public void setThumbnailPesanan(int thumbnailPesanan) {
        ThumbnailPesanan = thumbnailPesanan;
    }

    public ListPesananModel(String judulPesanan, String statusPesanan, Integer thumbnailPesanan){
        JudulPesanan = judulPesanan;
        StatusPesanan=statusPesanan;
        ThumbnailPesanan=thumbnailPesanan;
    }
}
