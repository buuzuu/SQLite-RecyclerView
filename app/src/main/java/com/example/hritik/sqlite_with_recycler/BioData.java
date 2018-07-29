package com.example.hritik.sqlite_with_recycler;

public class BioData {

    private int id;
    private String name;
    private String religion;


    public BioData() {
    }

    public BioData(int id, String name, String religion) {
        this.id = id;
        this.name = name;
        this.religion = religion;
    }

    public BioData(String name, String religion) {
        this.name = name;
        this.religion = religion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }
}
