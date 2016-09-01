package com.sjcdigital.temis.model.domain;

import com.google.gson.annotations.SerializedName;

public class Laws {
    @SerializedName("_embedded")
    private Embedded embedded;
    @SerializedName("_links")
    private Links links;

    public Embedded getEmbedded() {
        return embedded;
    }

    public void setEmbedded(Embedded embedded) {
        this.embedded = embedded;
    }

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }
}
