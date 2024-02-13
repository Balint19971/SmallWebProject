package edu.bbte.bibliospringdata.api.exeption;

public class NotFoundException extends RuntimeException{

    private Class type;

    private Long id;

    public NotFoundException(Class type, Long id){
        this.type = type;
        this.id = id;
    }

    public Class getType() {
        return type;
    }

    public void setType(Class type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
