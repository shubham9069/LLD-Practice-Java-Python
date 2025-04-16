package com.example.abstractClass;

public abstract class BasicInfo {
    private String id;
    private String name;
    private String email;

    protected BasicInfo(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public String id(){
        return this.id;
    }
    
}
