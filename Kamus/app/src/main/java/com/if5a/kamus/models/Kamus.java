package com.if5a.kamus.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Kamus implements Parcelable {
    private int id;
    private String title;
    private String description;

    // kalau pakai parcelable kita kirim separsel, lebih enak put extra langsung sepaket
    // kalau ga pakai bakal ribet putextra satu2
    // setter getter tdk wajib tp kalo dk pakai brrti melanggar aturan enkapsulasi

    // dipakai kalau mau inisialisai tanpa ada data
    public Kamus() {
    }

    // menambah data karena id itu increment dari sananya
    public Kamus(String title, String description) {
        this.title = title;
        this.description = description;
    }

    // menampilkan data
    public Kamus(int id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    protected Kamus(Parcel in) {
        id = in.readInt();
        title = in.readString();
        description = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(description);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Kamus> CREATOR = new Creator<Kamus>() {
        @Override
        public Kamus createFromParcel(Parcel in) {
            return new Kamus(in);
        }

        @Override
        public Kamus[] newArray(int size) {
            return new Kamus[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
