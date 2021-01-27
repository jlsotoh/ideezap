
package com.zappar.SwiftEmbed.model;

import java.util.List;

public class Object {


    private String id;

    private String name;

    private String description;

    private String businessSubtype;

    private List<Content_> contents = null;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBusinessSubtype() {
        return businessSubtype;
    }

    public void setBusinessSubtype(String businessSubtype) {
        this.businessSubtype = businessSubtype;
    }

    public List<Content_> getContents() {
        return contents;
    }

    public void setContents(List<Content_> contents) {
        this.contents = contents;
    }

}
