package com.nexusy.mina.vo;

/**
 * @author lan
 * @since 2016-03-30
 */
public class ValueObject {

    private Integer aInteger;
    private long aLong;
    private Double aDouble;
    private byte[] arrayValue;
    private String strValue;

    public Integer getaInteger() {
        return aInteger;
    }

    public void setaInteger(Integer aInteger) {
        this.aInteger = aInteger;
    }

    public long getaLong() {
        return aLong;
    }

    public void setaLong(long aLong) {
        this.aLong = aLong;
    }

    public Double getaDouble() {
        return aDouble;
    }

    public void setaDouble(Double aDouble) {
        this.aDouble = aDouble;
    }

    public byte[] getArrayValue() {
        return arrayValue;
    }

    public void setArrayValue(byte[] arrayValue) {
        this.arrayValue = arrayValue;
    }

    public String getStrValue() {
        return strValue;
    }

    public void setStrValue(String strValue) {
        this.strValue = strValue;
    }

    public static ValueObject makeObject() {
        ValueObject object = new ValueObject();
        object.aInteger = 1024;
        object.aLong = (long) (Math.random() * 3000);
        object.aDouble = Math.random();
        object.strValue = "This is just a demo!";
        byte[] array = new byte[object.aInteger];
        for (int i = 0; i < object.aInteger; i++) {
            array[i] = (byte) i;
        }
        object.arrayValue = array;
        return object;
    }
}
