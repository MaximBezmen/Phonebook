package entity;

import type.TypeNumber;

public class PhoneNumber {
    private Long id;
    private Integer codeOfCountry;
    private Integer operatorCode;
    private String number;
    private TypeNumber type;
    private String comment;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCodeOfCountry() {
        return codeOfCountry;
    }

    public void setCodeOfCountry(Integer codeOfCountry) {
        this.codeOfCountry = codeOfCountry;
    }

    public Integer getOperatorCode() {
        return operatorCode;
    }

    public void setOperatorCode(Integer operatorCode) {
        this.operatorCode = operatorCode;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public TypeNumber getType() {
        return type;
    }

    public void setType(TypeNumber type) {
        this.type = type;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
