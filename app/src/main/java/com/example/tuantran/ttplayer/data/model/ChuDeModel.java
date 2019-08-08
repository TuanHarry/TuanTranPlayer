package com.example.tuantran.ttplayer.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChuDeModel implements Parcelable {

@SerializedName("IDChuDe")
@Expose
private String iDChuDe;
@SerializedName("TenChuDe")
@Expose
private String tenChuDe;
@SerializedName("HinhChuDe")
@Expose
private String hinhChuDe;

    protected ChuDeModel(Parcel in) {
        iDChuDe = in.readString();
        tenChuDe = in.readString();
        hinhChuDe = in.readString();
    }

    public static final Creator<ChuDeModel> CREATOR = new Creator<ChuDeModel>() {
        @Override
        public ChuDeModel createFromParcel(Parcel in) {
            return new ChuDeModel(in);
        }

        @Override
        public ChuDeModel[] newArray(int size) {
            return new ChuDeModel[size];
        }
    };

    public String getIDChuDe() {
return iDChuDe;
}

public void setIDChuDe(String iDChuDe) {
this.iDChuDe = iDChuDe;
}

public String getTenChuDe() {
return tenChuDe;
}

public void setTenChuDe(String tenChuDe) {
this.tenChuDe = tenChuDe;
}

public String getHinhChuDe() {
return hinhChuDe;
}

public void setHinhChuDe(String hinhChuDe) {
this.hinhChuDe = hinhChuDe;
}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(iDChuDe);
        dest.writeString(tenChuDe);
        dest.writeString(hinhChuDe);
    }
}