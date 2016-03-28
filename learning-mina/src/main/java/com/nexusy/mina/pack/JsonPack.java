package com.nexusy.mina.pack;

/**
 * @author lanhuidong
 * @since 2016-03-27
 */
public class JsonPack {

    private Integer opCode;
    private Boolean isComplete;
    private Integer length;
    private String json;

    public Integer getOpCode() {
        return opCode;
    }

    public void setOpCode(Integer opCode) {
        this.opCode = opCode;
    }

    public Boolean getComplete() {
        return isComplete;
    }

    public void setComplete(Boolean complete) {
        isComplete = complete;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }
}
