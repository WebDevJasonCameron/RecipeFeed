package com.capstone.feedme.models;

public class Error {

    // ATT
    private String name;
    private int code;
    private String description;

    // CON
    public Error(String name, int code, String description) {
        this.name = name;
        this.code = code;
        this.description = description;
    }

    // GET
    public String getName() {
        return name;
    }
    public int getCode() {
        return code;
    }
    public String getDescription() {
        return description;
    }

    // SET
    public void setName(String name) {
        this.name = name;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    // CHECK
    @Override
    public String toString() {
        return "Error{" +
                "name='" + name + '\'' +
                ", code=" + code +
                ", description='" + description + '\'' +
                '}';
    }

} //<--END
