package edu.bbte.bibliospringdata.api.exeption;

public class CreationFaildException extends RuntimeException{

    private Class type;

    public CreationFaildException(Class type) {
        this.type = type;
    }

    public Class getType() {
        return type;
    }

    public void setType(Class type) {
        this.type = type;
    }
}
