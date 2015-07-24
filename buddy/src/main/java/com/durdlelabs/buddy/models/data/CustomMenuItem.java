package com.durdlelabs.buddy.models.data;

public class CustomMenuItem {
    private int resId;
    private String label;

    public CustomMenuItem() //just basic constructor
    {
        super();
    }

    public CustomMenuItem(int resId, String label) {
        this.resId = resId;
        this.label = label;
    }

    public int getResId() {
        return resId;
    }

    public String getLabel() {
        return label;
    }
}