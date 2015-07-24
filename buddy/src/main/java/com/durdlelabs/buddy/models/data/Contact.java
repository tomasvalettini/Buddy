package com.durdlelabs.buddy.models.data;

public class Contact {
    private String id;
    private String name;
    private String phone;
    private boolean selected;

    public Contact() {
        super();
        phone = "";
    }

    public Contact(String _id, String _name, String _phone) {
        super();

        id = _id;
        name = _name;
        phone = _phone;
        selected = false;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        if (this.phone.isEmpty() || this.phone.equals("")) {
            this.phone = phone;
        }
        else
        {
            this.phone += "\n" + phone;
        }
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
    // good to know
    // override the toString method to quickly display something in a listview!
}