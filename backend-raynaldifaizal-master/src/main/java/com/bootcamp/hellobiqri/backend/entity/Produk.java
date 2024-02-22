package com.bootcamp.hellobiqri.backend.entity;

public class Produk {
    Integer id;
    String nama;
    String jenis;
    Produsen produsen;
    Double harga;
    String berat;

    public Produk(){

    }


    public Produsen getProdusen() {
        return produsen;
    }

    public void setProdusen(Produsen produsen) {
        this.produsen = produsen;
    }


    public String getBerat() {
        return berat;
    }

    public void setBerat(String berat) {
        this.berat = berat;
    }





    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }



    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }
    public Double getHarga() {
        return harga;
    }

    public void setHarga(Double harga) {
        this.harga = harga;
    }
}
