package com.relife.coffeshops.models;

import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.DiffUtil;

public class ModelProduk {
    private String id;
    private String nama;
    private int harga;
    private String images;

    public ModelProduk(String id, String nama, int harga, String images) {
        this.id = id;
        this.nama = nama;
        this.harga = harga;
        this.images = images;
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

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    @Override
    public String toString() {
        return "ModelProduk{" +
                "id='" + id + '\'' +
                ", nama='" + nama + '\'' +
                ", harga=" + harga +
                ", images='" + images + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ModelProduk that = (ModelProduk) o;
        return getHarga() == that.getHarga() &&
                getId().equals(that.getId()) &&
                getNama().equals(that.getNama()) &&
                getImages().equals(that.getImages());
    }

    public static DiffUtil.ItemCallback<ModelProduk> itemCallback = new DiffUtil.ItemCallback<ModelProduk>() {
        @Override
        public boolean areItemsTheSame(@NonNull ModelProduk oldItem, @NonNull ModelProduk newItem) {
            return oldItem.getId().equals(newItem.getId());
        }

        @Override
        public boolean areContentsTheSame(@NonNull ModelProduk oldItem, @NonNull ModelProduk newItem) {
            return oldItem.equals(newItem);
        }
    };

    //init library untuk ambil images dari web
    @BindingAdapter("android:produkImage")
    public static void loadImage(ImageView imageView, String images){
        Glide.with(imageView).load(images).fitCenter().into(imageView);
    }

}
