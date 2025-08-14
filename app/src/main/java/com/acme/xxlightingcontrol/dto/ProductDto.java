package com.acme.xxlightingcontrol.dto;

import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * @author jx9@msn.com
 */
public class ProductDto {

    private Long id;

    @JsonProperty("bgcolor")
    private String bgColor;

    private String icon;

    @JsonProperty("is_cycle")
    private Integer isCycle;

    private String name;

    @JsonProperty("name_en")
    private String nameEn;

    private Integer type;

    private int status;

    public ProductDto() {

    }

    public ProductDto(Long id) {
        this.id = id;
    }

    public ProductDto(String name) {
        this.name = name;
    }

    public ProductDto(Long id, String bgColor, String icon, Integer isCycle, String name, String nameEn, Integer type, int status) {
        this.id = id;
        this.bgColor = bgColor;
        this.icon = icon;
        this.isCycle = isCycle;
        this.name = name;
        this.nameEn = nameEn;
        this.type = type;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBgColor() {
        return bgColor;
    }

    public void setBgColor(String bgColor) {
        this.bgColor = bgColor;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getIsCycle() {
        return isCycle;
    }

    public void setIsCycle(Integer isCycle) {
        this.isCycle = isCycle;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}