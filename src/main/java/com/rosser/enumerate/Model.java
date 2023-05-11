package com.rosser.enumerate;

public enum Model {
    GPT35TURBO("gpt-3.5-turbo") ;
    private String value;

     Model(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}