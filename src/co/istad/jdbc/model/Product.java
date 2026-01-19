package co.istad.jdbc.model;

import java.math.BigDecimal;

public class Product {
    private Integer id;
    private String code;
    private String name;
    private BigDecimal price;
    private Integer qty;
    private Boolean isDeleted;

    public Product() {
    }

    public Product(String code, String name, BigDecimal price, Boolean isDeleted, Integer qty) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.isDeleted = isDeleted;
        this.qty = qty;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }
}
