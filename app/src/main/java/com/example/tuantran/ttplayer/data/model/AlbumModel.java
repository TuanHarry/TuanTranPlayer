package com.example.tuantran.ttplayer.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AlbumModel implements Parcelable {

@SerializedName("IDAlbum")
@Expose
private String iDAlbum;
@SerializedName("TenAlbum")
@Expose
private String tenAlbum;
@SerializedName("TenCaSiAl")
@Expose
private String tenCaSiAl;
@SerializedName("HinhAnh")
@Expose
private String hinhAnh;

    protected AlbumModel(Parcel in) {
        iDAlbum = in.readString();
        tenAlbum = in.readString();
        tenCaSiAl = in.readString();
        hinhAnh = in.readString();
    }

    public static final Creator<AlbumModel> CREATOR = new Creator<AlbumModel>() {
        @Override
        public AlbumModel createFromParcel(Parcel in) {
            return new AlbumModel(in);
        }

        @Override
        public AlbumModel[] newArray(int size) {
            return new AlbumModel[size];
        }
    };

    public String getIDAlbum() {
return iDAlbum;
}

public void setIDAlbum(String iDAlbum) {
this.iDAlbum = iDAlbum;
}

public String getTenAlbum() {
return tenAlbum;
}

public void setTenAlbum(String tenAlbum) {
this.tenAlbum = tenAlbum;
}

public String getTenCaSiAl() {
return tenCaSiAl;
}

public void setTenCaSiAl(String tenCaSiAl) {
this.tenCaSiAl = tenCaSiAl;
}

public String getHinhAnh() {
return hinhAnh;
}

public void setHinhAnh(String hinhAnh) {
this.hinhAnh = hinhAnh;
}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(iDAlbum);
        dest.writeString(tenAlbum);
        dest.writeString(tenCaSiAl);
        dest.writeString(hinhAnh);
    }
}