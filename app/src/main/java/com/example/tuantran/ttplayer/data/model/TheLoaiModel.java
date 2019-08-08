package com.example.tuantran.ttplayer.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TheLoaiModel implements Parcelable {

@SerializedName("IDTheLoai")
@Expose
private String iDTheLoai;
@SerializedName("IDKeyChuDe")
@Expose
private String iDKeyChuDe;
@SerializedName("TenTheLoai")
@Expose
private String tenTheLoai;
@SerializedName("HinhTheLoai")
@Expose
private String hinhTheLoai;

    protected TheLoaiModel(Parcel in) {
        iDTheLoai = in.readString();
        iDKeyChuDe = in.readString();
        tenTheLoai = in.readString();
        hinhTheLoai = in.readString();
    }

    public static final Creator<TheLoaiModel> CREATOR = new Creator<TheLoaiModel>() {
        @Override
        public TheLoaiModel createFromParcel(Parcel in) {
            return new TheLoaiModel(in);
        }

        @Override
        public TheLoaiModel[] newArray(int size) {
            return new TheLoaiModel[size];
        }
    };

    public String getIDTheLoai() {
return iDTheLoai;
}

public void setIDTheLoai(String iDTheLoai) {
this.iDTheLoai = iDTheLoai;
}

public String getIDKeyChuDe() {
return iDKeyChuDe;
}

public void setIDKeyChuDe(String iDKeyChuDe) {
this.iDKeyChuDe = iDKeyChuDe;
}

public String getTenTheLoai() {
return tenTheLoai;
}

public void setTenTheLoai(String tenTheLoai) {
this.tenTheLoai = tenTheLoai;
}

public String getHinhTheLoai() {
return hinhTheLoai;
}

public void setHinhTheLoai(String hinhTheLoai) {
this.hinhTheLoai = hinhTheLoai;
}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(iDTheLoai);
        dest.writeString(iDKeyChuDe);
        dest.writeString(tenTheLoai);
        dest.writeString(hinhTheLoai);
    }
}