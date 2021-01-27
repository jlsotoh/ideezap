
package com.zappar.SwiftEmbed.model;

import java.util.List;

public class Content_ {


    private String id;

    private List<ContentDetail_> contentDetails = null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<ContentDetail_> getContentDetails() {
        return contentDetails;
    }

    public void setContentDetails(List<ContentDetail_> contentDetails) {
        this.contentDetails = contentDetails;
    }

}
