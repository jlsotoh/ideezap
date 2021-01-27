
package com.zappar.SwiftEmbed.model;

import java.util.List;

public class Scene {


    private String id;

    private String businessSubtype;

    private String name;

    private String description;

    private List<Content> contents = null;

    private List<Relation> relations = null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBusinessSubtype() {
        return businessSubtype;
    }

    public void setBusinessSubtype(String businessSubtype) {
        this.businessSubtype = businessSubtype;
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

    public List<Content> getContents() {
        return contents;
    }

    public void setContents(List<Content> contents) {
        this.contents = contents;
    }

    public List<Relation> getRelations() {
        return relations;
    }

    public void setRelations(List<Relation> relations) {
        this.relations = relations;
    }

}
