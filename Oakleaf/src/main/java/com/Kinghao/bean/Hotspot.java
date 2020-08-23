package com.Kinghao.bean;


import java.sql.Timestamp;

public class Hotspot {
    private Long id;
    private String hotspotName;
    private String hotspotType;
    private String username;
    private String clickResult;
    private Timestamp clickTime;
    private String remarks;
    private int readBookId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHotspotName() {
        return hotspotName;
    }

    public void setHotspotName(String hotspotName) {
        this.hotspotName = hotspotName;
    }

    public String getHotspotType() {
        return hotspotType;
    }

    public void setHotspotType(String hotspotType) {
        this.hotspotType = hotspotType;
    }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    public Timestamp getClickTime() { return clickTime; }

    public void setClickTime(Timestamp clickTime) { this.clickTime = clickTime; }

    public String getClickResult() {
        return clickResult;
    }

    public void setClickResult(String clickResult) {
        this.clickResult = clickResult;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public int getReadBookId() {
        return readBookId;
    }

    public void setReadBookId(int readBookId) {
        this.readBookId = readBookId;
    }
}
