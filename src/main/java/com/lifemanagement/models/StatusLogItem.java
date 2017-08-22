package com.lifemanagement.models;

public class StatusLogItem {
    private long timestamp;
    private Indicator indicatorStatus;

    public StatusLogItem(){
        this.setTimestamp(System.currentTimeMillis());
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public Indicator getIndicatorStatus() {
        return indicatorStatus;
    }

    public void setIndicatorStatus(Indicator indicatorStatus) {
        this.indicatorStatus = indicatorStatus;
    }
}
