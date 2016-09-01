package com.sjcdigital.temis.model.domain;

public class Links {
    private Self self;
    private First first;
    private Next next;
    private Last last;

    public Self getSelf() {
        return this.self;
    }

    public void setSelf(Self self) {
        this.self = self;
    }

    public First getFirst() {
        return this.first;
    }

    public void setFirst(First first) {
        this.first = first;
    }

    public Next getNext() {
        return this.next;
    }

    public void setNext(Next next) {
        this.next = next;
    }

    public Last getLast() {
        return this.last;
    }

    public void setLast(Last last) {
        this.last = last;
    }
}
