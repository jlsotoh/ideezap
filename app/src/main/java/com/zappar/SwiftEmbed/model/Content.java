
package com.zappar.SwiftEmbed.model;


import java.util.List;

public class Content {


    private String id;

    private List<ContentDetail> contentDetails = null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<ContentDetail> getContentDetails() {
        return contentDetails;
    }

    public void setContentDetails(List<ContentDetail> contentDetails) {
        this.contentDetails = contentDetails;
    }

}
