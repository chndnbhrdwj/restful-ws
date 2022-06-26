package com.cns.restfulws.helloworld;

public class HelloWorldBean {

    private String mesaage;

    public HelloWorldBean(String message) {
        this.mesaage = message;
    }

    public void setMesaage(String mesaage) {
        this.mesaage = mesaage;
    }

    public String getMesaage() {
        return mesaage;
    }

    @Override
    public String toString() {
        return "HelloWorldBean{" +
                "mesaage='" + mesaage + '\'' +
                '}';
    }
}
