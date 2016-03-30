package com.nexusy.mina.pack;

/**
 * @author lanhuidong
 * @since 2016-03-27
 */
public class Data {

    private Integer contentType;
    private Integer opCode;
    private Integer length;
    private byte[] content;

    public Integer getContentType() {
        return contentType;
    }

    public void setContentType(Integer contentType) {
        this.contentType = contentType;
    }

    public Integer getOpCode() {
        return opCode;
    }

    public void setOpCode(Integer opCode) {
        this.opCode = opCode;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
}
