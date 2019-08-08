package com.example.tuantran.ttplayer.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SongModel implements Parcelable {
    @SerializedName("IDBaiHat")
    @Expose
    private String id;

    @SerializedName("HinhBaiHat")
    @Expose
    private String hinhBaiHat;

    public String getHinhBaiHat() {
        return hinhBaiHat;
    }

    public void setHinhBaiHat(String hinhBaiHat) {
        this.hinhBaiHat = hinhBaiHat;
    }

    @SerializedName("Casi")
    @Expose
    private String artist;

    @SerializedName("TenBaiHat")
    @Expose
    private String title;


    private String displayName;

    @SerializedName("LinkBaiHat")
    @Expose
    private String streamUri;

    private String albumId;

    @SerializedName("LuotThich")
    @Expose
    private String luotThich;


    private long date;
    private long duration;
    private long fileSize;

    public SongModel() {
    }

    // Constructor for load local songs
    public SongModel(String id, String artist, String title, String displayName, String streamUri, String albumId, long date, long duration, long fileSize) {
        this.id = id;
        this.artist = artist;
        this.title = title;
        this.displayName = displayName;
        this.streamUri = streamUri;
        this.albumId = albumId;
        this.date = date;
        this.duration = duration;
        this.fileSize = fileSize;
    }

    // Constructor for load songs online
    public SongModel(String id, String artist, String displayName, String streamUri, String luotThich) {
        this.id = id;
        this.artist = artist;
        this.displayName = displayName;
        this.streamUri = streamUri;
        this.luotThich = luotThich;
    }

    protected SongModel(Parcel in) {
        id = in.readString();
        artist = in.readString();
        title = in.readString();
        displayName = in.readString();
        streamUri = in.readString();
        albumId = in.readString();
        luotThich = in.readString();
        date = in.readLong();
        duration = in.readLong();
        fileSize = in.readLong();
    }

    public static final Creator<SongModel> CREATOR = new Creator<SongModel>() {
        @Override
        public SongModel createFromParcel(Parcel in) {
            return new SongModel(in);
        }

        @Override
        public SongModel[] newArray(int size) {
            return new SongModel[size];
        }
    };

    public String getLuotThich() {
        return luotThich;
    }

    public void setLuotThich(String luotThich) {
        this.luotThich = luotThich;
    }




    @Override
    public String toString() {
        return "SongModxel{" +
                "id='" + id + '\'' +
                ", artist='" + artist + '\'' +
                ", title='" + title + '\'' +
                ", displayName='" + displayName + '\'' +
                ", streamUri='" + streamUri + '\'' +
                ", albumId='" + albumId + '\'' +
                ", date=" + date +
                ", duration=" + duration +
                ", fileSize=" + fileSize +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getStreamUri() {
        return streamUri;
    }

    public void setStreamUri(String streamUri) {
        this.streamUri = streamUri;
    }

    public String getAlbumId() {
        return albumId;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(artist);
        dest.writeString(title);
        dest.writeString(displayName);
        dest.writeString(streamUri);
        dest.writeString(albumId);
        dest.writeString(luotThich);
        dest.writeLong(date);
        dest.writeLong(duration);
        dest.writeLong(fileSize);
    }
}
