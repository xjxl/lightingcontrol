package com.acme.xxlightingcontrol.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * @author jx9@msn.com
 */
public class ProductDto implements Serializable {
    private static final long serialVersionUID = 1L;

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

    private boolean play;

    public ProductDto() {

    }

    public ProductDto(Long id) {
        this.id = id;
    }

    public ProductDto(String name) {
        this.name = name;
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

    public boolean isPlay() {
        return play;
    }

    public void setPlay(boolean play) {
        this.play = play;
    }

}