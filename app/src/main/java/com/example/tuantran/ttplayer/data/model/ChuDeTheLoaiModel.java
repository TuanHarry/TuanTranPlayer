package com.example.tuantran.ttplayer.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ChuDeTheLoaiModel {

@SerializedName("TheLoai")
@Expose
private List<TheLoaiModel> theLoai = null;
@SerializedName("ChuDe")
@Expose
private List<ChuDeModel> chuDe = null;

public List<TheLoaiModel> getTheLoai() {
return theLoai;
}

public void setTheLoai(List<TheLoaiModel> theLoai) {
this.theLoai = theLoai;
}

public List<ChuDeModel> getChuDe() {
return chuDe;
}

public void setChuDe(List<ChuDeModel> chuDe) {
this.chuDe = chuDe;
}

}