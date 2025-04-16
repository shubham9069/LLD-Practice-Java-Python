package com.example.model;
import java.util.UUID;

import com.example.abstractClass.BasicInfo;
public class User extends BasicInfo {
 
    public User(String name, String email) {
        super(UUID.randomUUID().toString(), name, email);
    }

    public String getId(){
        return super.id();
    }
    


  
}