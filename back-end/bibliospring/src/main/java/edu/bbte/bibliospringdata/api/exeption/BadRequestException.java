package edu.bbte.bibliospringdata.api.exeption;

public class BadRequestException extends RuntimeException{

    private Class type;
    private String id;

    public BadRequestException(Class type, String id){
        this.type = type;
        this.id = id;
    }

    public Class getType() {
        return type;
    }

    public void setType(Class type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
