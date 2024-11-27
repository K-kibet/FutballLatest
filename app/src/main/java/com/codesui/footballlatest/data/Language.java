package com.codesui.footballlatest.data;

public class Language {
    private final String code;
    private final String name;
    private final String nativeName;
    private boolean isSelected;

    public Language(String code, String name, String nativeName, boolean isSelected) {
        this.code = code;
        this.name = name;
        this.nativeName = nativeName;
        this.isSelected = isSelected;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getNativeName() {
        return nativeName;
    }

    public boolean isSelected() { return isSelected; }
    public void setSelected(boolean selected) { isSelected = selected; }
}
