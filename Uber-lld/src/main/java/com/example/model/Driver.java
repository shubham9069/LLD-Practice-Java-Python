
package com.example.model;
import java.util.UUID;

import com.example.abstractClass.BasicInfo;
import com.example.constant.Status;

public class Driver extends BasicInfo {
    private Status status;
    private Location location;
    public Driver(String name, String email) {
        super(UUID.randomUUID().toString(), name, email);
        this.status = Status.NOT_AVAILABLE;
    }

    public String getId(){
        return super.id();
    }

    public Status getStatus(){
        return this.status;
    }

    public void setStatus(Status status){
        this.status = status;
    }

    public void ready(Status status, Location location){
        this.status = status;
        this.location = location;
    }

    public Location getLocation(){
        return this.location;
    }
    


  
}
