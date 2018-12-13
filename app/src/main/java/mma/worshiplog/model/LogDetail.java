package mma.worshiplog.model;

import android.arch.persistence.room.ColumnInfo;

public class LogDetail {

    @ColumnInfo(name = "id_log")
    Integer logId;

    @ColumnInfo(name = "id_song")
    Integer songId;

    @ColumnInfo(name = "name_song")
    String songName;

    @ColumnInfo(name = "order_part")
    Integer partOrder;

    @ColumnInfo(name = "name_part")
    String partName;

    @ColumnInfo(name = "extra_info")
    String extraInfo;

    public LogDetail(Integer logId, Integer songId, String songName, Integer partOrder, String partName, String extraInfo) {
        this.logId = logId;
        this.songId = songId;
        this.songName = songName;
        this.partOrder = partOrder;
        this.partName = partName;
        this.extraInfo = extraInfo;
    }

    public Integer getLogId() {
        return logId;
    }

    public void setLogId(Integer logId) {
        this.logId = logId;
    }

    public Integer getSongId() {
        return songId;
    }

    public void setSongId(Integer songId) {
        this.songId = songId;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public Integer getPartOrder() {
        return partOrder;
    }

    public void setPartOrder(Integer partOrder) {
        this.partOrder = partOrder;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public String getExtraInfo() {
        return extraInfo;
    }

    public void setExtraInfo(String extraInfo) {
        this.extraInfo = extraInfo;
    }

    @Override
    public String toString() {
        return "LogDetail{" +
                "logId=" + logId +
                ", songId=" + songId +
                ", songName='" + songName + '\'' +
                ", partOrder=" + partOrder +
                ", partName='" + partName + '\'' +
                ", extraInfo='" + extraInfo + '\'' +
                '}';
    }
}
