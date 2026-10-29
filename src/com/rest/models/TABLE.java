package com.rest.models;

public class TABLE {
    public enum availability{
        FREE , OCCUPY
    }

    private int id;
    private int capacity;
    private availability status;

    public TABLE(int id , int capacity ){
        this.id=id;
        this.capacity=capacity;
        this.status=availability.FREE;
    }

    public int getid(){
        return id;
    }

    public int getcapacity(){
        return capacity;
    }

    public availability getstatus(){
        return status;
    }

    public void freetable(){
        this.status=availability.FREE;
    }

    public void occupytetable(){
        this.status=availability.OCCUPY;
    }

    

}
