package com.player.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class Product {
    private Integer id;             //产品id
    private Integer categoryId;    //品类id
    private String name;            //产品名称
    private String subtitle;       //产品副标题
    private String mainImage;      //产品主图
    private String subImages;      //产品地址
    private BigDecimal price;       //产品价格
    private Date createTime;       //创建时间
    private Date updateTime;       //更新时间
    private Integer stock;          //产品库存
    private Integer status;         //产品状态 1在售 2下架 3删除
    private String detail;          //产品详情

    public Product(Integer id, Integer categoryId, String name, String subtitle, String mainImage, String subImages, String detail,
                   BigDecimal price,Integer stock, Integer status, Date createTime, Date updateTime) {
        this.id = id;
        this.categoryId = categoryId;
        this.name = name;
        this.subtitle = subtitle;
        this.mainImage = mainImage;
        this.subImages = subImages;
        this.price = price;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.stock = stock;
        this.status = status;
        this.detail = detail;
    }

    public Product() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getMainImage() {
        return mainImage;
    }

    public void setMainImage(String mainImage) {
        this.mainImage = mainImage;
    }

    public String getSubImages() {
        return subImages;
    }

    public void setSubImages(String subImages) {
        this.subImages = subImages;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
