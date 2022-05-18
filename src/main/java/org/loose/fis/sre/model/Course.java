package org.loose.fis.sre.model;

public class Course {
    public String id;
    public String name;
    public String prof;

    public Course(String id,String name, String prof){
        this.id = id;
        this.name = name;
        this.prof = prof;
    }
    public String getId(){
        return id;
    }

    public String getName() {
        return name;
    }

    public String getProf() {
        return prof;
    }
    public String toString(){
        return id + " " + name + " " + prof;
    }
}
