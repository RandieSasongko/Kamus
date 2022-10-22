package com.pab2.kamus.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Kamus implements Parcelable {
    private int id;
    private String title;
    private String description;

    // object kosong
    public Kamus() {
    }

    // utk simpan
    public Kamus(String title, String description) {
        this.title = title;
        this.description = description;
    }

    // dari database dapat id
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(title);
        parcel.writeString(description);
    }
}
