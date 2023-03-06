package com.example.yp_trifonova;

import android.os.Parcel;
import android.os.Parcelable;

public class MaskaFeelings implements Parcelable {

    private int id;
    private String title;
    private int position;
    private String image;

    public MaskaFeelings(int id, String title, int position,String image) {
        this.id = id;
        this.title = title;
        this.position=position;
        this.image = image;

    }

    protected MaskaFeelings(Parcel in) {
        id = in.readInt();
        title = in.readString();
        position = in.readInt();
        image = in.readString();
    }

    public static final Creator<MaskaFeelings> CREATOR = new Creator<MaskaFeelings>() {
        @Override
        public MaskaFeelings createFromParcel(Parcel in) {
            return new MaskaFeelings(in);
        }

        @Override
        public MaskaFeelings[] newArray(int size) {
            return new MaskaFeelings[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getPosition() {
        return position;
    }

    public String getImage() {
        return image;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(title);
        parcel.writeInt(position);
        parcel.writeString(image);
    }
}
