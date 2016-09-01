package com.sjcdigital.temis.model.domain;

import java.util.ArrayList;

public class Embedded {
    private ArrayList<LawList> lawList;

    public ArrayList<LawList> getLawList() {
        return this.lawList;
    }

    public void setLawList(ArrayList<LawList> lawList) {
        this.lawList = lawList;
    }
}
