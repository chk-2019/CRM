package com.ck.domain;

public class Dic_Value implements Comparable<Dic_Value> {
   private String id;
   private String value;
   private String text;
   private String orderNo;
   private String typeCode;

    public Dic_Value() {
    }

    @Override
    public String toString() {
        return "Dic_Value{" +
                "id='" + id + '\'' +
                ", value='" + value + '\'' +
                ", text='" + text + '\'' +
                ", orderNo='" + orderNo + '\'' +
                ", typeCode='" + typeCode + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    @Override
    public int compareTo(Dic_Value o) {
        int i = this.getValue().compareTo(o.getValue());
        return i;
    }
}
