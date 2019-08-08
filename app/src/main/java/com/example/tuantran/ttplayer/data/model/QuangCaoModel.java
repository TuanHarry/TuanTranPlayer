package com.example.tuantran.ttplayer.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class QuangCaoModel implements Parcelable {

@SerializedName("IDQuangCao")
@Expose
private String iDQuangCao;
@SerializedName("HinhAnh")
@Expose
private String hinhAnh;
@SerializedName("NoiDung")
@Expose
private String noiDung;
@SerializedName("IDBaiHat")
@Expose
private String iDBaiHat;
@SerializedName("TenBaiHat")
@Expose
private String tenBaiHat;
@SerializedName("HinhBaiHat")
@Expose
private String hinhBaiHat;

    protected QuangCaoModel(Parcel in) {
        iDQuangCao = in.readString();
        hinhAnh = in.readString();
        noiDung = in.readString();
        iDBaiHat = in.readString();
        tenBaiHat = in.readString();
        hinhBaiHat = in.readString();
    }

    public static final Creator<QuangCaoModel> CREATOR = new Creator<QuangCaoModel>() {
        @Override
        public QuangCaoModel createFromParcel(Parcel in) {
            return new QuangCaoModel(in);
        }

        @Override
        public QuangCaoModel[] newArray(int size) {
            return new QuangCaoModel[size];
        }
    };

    public String getIDQuangCao() {
return iDQuangCao;
}

public void setIDQuangCao(String iDQuangCao) {
this.iDQuangCao = iDQuangCao;
}

public String getHinhAnh() {
return hinhAnh;
}

public void setHinhAnh(String hinhAnh) {
this.hinhAnh = hinhAnh;
}

public String getNoiDung() {
return noiDung;
}

public void setNoiDung(String noiDung) {
this.noiDung = noiDung;
}

public String getIDBaiHat() {
return iDBaiHat;
}

public void setIDBaiHat(String iDBaiHat) {
this.iDBaiHat = iDBaiHat;
}

public String getTenBaiHat() {
return tenBaiHat;
}

public void setTenBaiHat(String tenBaiHat) {
this.tenBaiHat = tenBaiHat;
}

public String getHinhBaiHat() {
return hinhBaiHat;
}

public void setHinhBaiHat(String hinhBaiHat) {
this.hinhBaiHat = hinhBaiHat;
}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(iDQuangCao);
        dest.writeString(hinhAnh);
        dest.writeString(noiDung);
        dest.writeString(iDBaiHat);
        dest.writeString(tenBaiHat);
        dest.writeString(hinhBaiHat);
    }
}