package com.acme.xxlightingcontrol.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @author jx9@msn.com
 */
public class ModeDto {

    private Long id;

    @JsonProperty("bgcolor")
    private String bgColor;

    private String icon;

    @JsonProperty("icon_click")
    private String iconClick;

    @JsonProperty("is_cycle")
    private Integer isCycle;

    @JsonProperty("is_selected")
    private Integer isSelected;

    private String name;

    @JsonProperty("name_en")
    private String nameEn;

    private Long parent;

    private Integer type;

    @JsonProperty("children")
    private List<ModeDto> children;

    private boolean haveChild;

    private int selectedChildIndex = -1;

    public ModeDto() {

    }

    public ModeDto(Long id) {
        this.id = id;
    }

    public ModeDto(String name) {
        this.name = name;
    }

    public ModeDto(Long id, String bgColor, String icon, String iconClick,
                   Integer isCycle, Integer isSelected, String name,
                   String nameEn, Long parent, Integer type,
                   List<ModeDto> children) {
        this.id = id;
        this.bgColor = bgColor;
        this.icon = icon;
        this.iconClick = iconClick;
        this.isCycle = isCycle;
        this.isSelected = isSelected;
        this.name = name;
        this.nameEn = nameEn;
        this.parent = parent;
        this.type = type;
        this.children = children;
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

    public String getIconClick() {
        return iconClick;
    }

    public void setIconClick(String iconClick) {
        this.iconClick = iconClick;
    }

    public Integer getIsCycle() {
        return isCycle;
    }

    public void setIsCycle(Integer isCycle) {
        this.isCycle = isCycle;
    }

    public Integer getIsSelected() {
        return isSelected;
    }

    public void setIsSelected(Integer isSelected) {
        this.isSelected = isSelected;
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

    public Long getParent() {
        return parent;
    }

    public void setParent(Long parent) {
        this.parent = parent;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public List<ModeDto> getChildren() {
        return children;
    }

    public void setChildren(List<ModeDto> children) {
        this.children = children;
    }

    public boolean isHaveChild() {
        return this.children != null && !this.children.isEmpty();
    }

    public void setHaveChild(boolean haveChild) {
        this.haveChild = haveChild;
    }

    public int getSelectedChildIndex() {
        return selectedChildIndex;
    }

    public void setSelectedChildIndex(int selectedChildIndex) {
        this.selectedChildIndex = selectedChildIndex;
    }

}