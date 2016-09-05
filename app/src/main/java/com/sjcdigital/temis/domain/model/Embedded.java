package com.sjcdigital.temis.domain.model;

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
