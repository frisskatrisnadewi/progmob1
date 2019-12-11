package com.friska.myapplication.model_recyclerview;

public class ListDressModel {
    private String JudulDress;
    private String KategoriDress;
    private int ThumbnailDress;

    public String getJudulDress() {
        return JudulDress;
    }

    public void setJudulDress(String judulDress) {
        JudulDress = judulDress;
    }

    public String getKategoriDress() {
        return KategoriDress;
    }

    public void setKategoriDress(String kategoriDress) {
        KategoriDress = kategoriDress;
    }

    public int getThumbnailDress() {
        return ThumbnailDress;
    }

    public void setThumbnailDress(int thumbnailDress) {
        ThumbnailDress = thumbnailDress;
    }

    public ListDressModel(String judulDress, String kategoriDress, Integer thumbnailDress){
        JudulDress = judulDress;
        KategoriDress=kategoriDress;
        ThumbnailDress=thumbnailDress;
    }
}
