package com.example.tuantran.ttplayer.data.model;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Item  implements MultiItemEntity {

    @SerializedName("type")
    @Expose
    private int type;

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("icon")
    @Expose
    private String icon;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("sub")
    @Expose
    private String sub;

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Item(int type, String title, String icon, String image, String sub) {
        this.type = type;
        this.title = title;
        this.icon = icon;
        this.image = image;
        this.sub = sub;
    }

    @Override
    public int getItemType() {
        return type;
    }
}