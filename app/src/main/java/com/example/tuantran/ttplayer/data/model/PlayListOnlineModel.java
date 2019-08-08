package com.example.tuantran.ttplayer.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PlayListOnlineModel implements Parcelable {

@SerializedName("IDPlayList")
@Expose
private String iDPlayList;
@SerializedName("TenPlayList")
@Expose
private String tenPlayList;
@SerializedName("HinhPlayList")
@Expose
private String hinhPlayList;
@SerializedName("IconPlayList")
@Expose
private String iconPlayList;

    protected PlayListOnlineModel(Parcel in) {
        iDPlayList = in.readString();
        tenPlayList = in.readString();
        hinhPlayList = in.readString();
        iconPlayList = in.readString();
    }

    public static final Creator<PlayListOnlineModel> CREATOR = new Creator<PlayListOnlineModel>() {
        @Override
        public PlayListOnlineModel createFromParcel(Parcel in) {
            return new PlayListOnlineModel(in);
        }

        @Override
        public PlayListOnlineModel[] newArray(int size) {
            return new PlayListOnlineModel[size];
        }
    };

    public String getIDPlayList() {
        return iDPlayList;
}

public void setIDPlayList(String iDPlayList) {
this.iDPlayList = iDPlayList;
}

public String getTenPlayList() {
return tenPlayList;
}

public void setTenPlayList(String tenPlayList) {
this.tenPlayList = tenPlayList;
}

public String getHinhPlayList() {
return hinhPlayList;
}

public void setHinhPlayList(String hinhPlayList) {
this.hinhPlayList = hinhPlayList;
}

public String getIconPlayList() {
return iconPlayList;
}

public void setIconPlayList(String iconPlayList) {
this.iconPlayList = iconPlayList;
}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(iDPlayList);
        dest.writeString(tenPlayList);
        dest.writeString(hinhPlayList);
        dest.writeString(iconPlayList);
    }
}