package com.example.tuantran.ttplayer.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BaiHatModel implements Parcelable {

@SerializedName("IDBaiHat")
@Expose
private String iDBaiHat;
@SerializedName("TenBaiHat")
@Expose
private String tenBaiHat;
@SerializedName("HinhBaiHat")
@Expose
private String hinhBaiHat;
@SerializedName("Casi")
@Expose
private String casi;
@SerializedName("LinkBaiHat")
@Expose
private String linkBaiHat;
@SerializedName("LuotThich")
@Expose
private String luotThich;

    protected BaiHatModel(Parcel in) {
        iDBaiHat = in.readString();
        tenBaiHat = in.readString();
        hinhBaiHat = in.readString();
        casi = in.readString();
        linkBaiHat = in.readString();
        luotThich = in.readString();
    }

    public static final Creator<BaiHatModel> CREATOR = new Creator<BaiHatModel>() {
        @Override
        public BaiHatModel createFromParcel(Parcel in) {
            return new BaiHatModel(in);
        }

        @Override
        public BaiHatModel[] newArray(int size) {
            return new BaiHatModel[size];
        }
    };

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

public String getCasi() {
return casi;
}

public void setCasi(String casi) {
this.casi = casi;
}

public String getLinkBaiHat() {
return linkBaiHat;
}

public void setLinkBaiHat(String linkBaiHat) {
this.linkBaiHat = linkBaiHat;
}

public String getLuotThich() {
return luotThich;
}

public void setLuotThich(String luotThich) {
this.luotThich = luotThich;
}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(iDBaiHat);
        dest.writeString(tenBaiHat);
        dest.writeString(hinhBaiHat);
        dest.writeString(casi);
        dest.writeString(linkBaiHat);
        dest.writeString(luotThich);
    }
}