package model;

import org.neo4j.driver.v1.Value;

public class Person {

    String id, name, job, birthday;
    public Person(){

    }
    public Person (String id, String name, String job){
        this.id = id;
        this.name = name;
        this.job = job;
        this.birthday = birthday;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", job='" + job + '\'' +
                ", birthday='" + birthday + '\'' +
                '}';
    }
}
