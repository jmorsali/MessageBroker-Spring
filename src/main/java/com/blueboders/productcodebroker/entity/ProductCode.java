package com.blueboders.productcodebroker.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "ProductCode")
public class ProductCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
    private   String source;
    private  String codeListCode;
    @Column(unique = true)
    private  String code;
    private  String displayValue;

    @Column(columnDefinition = "nvarchar(MAX)")
    private  String longDescription;
    private String fromDate;
    private  String toDate;
    private  String sortingPriority;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getCodeListCode() {
        return codeListCode;
    }

    public void setCodeListCode(String codeListCode) {
        this.codeListCode = codeListCode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDisplayValue() {
        return displayValue;
    }

    public void setDisplayValue(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getSortingPriority() {
        return sortingPriority;
    }

    public void setSortingPriority(String sortingPriority) {
        this.sortingPriority = sortingPriority;
    }
}
