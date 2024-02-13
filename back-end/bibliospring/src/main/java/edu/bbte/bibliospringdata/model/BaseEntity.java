package edu.bbte.bibliospringdata.model;

import jakarta.persistence.*;

@MappedSuperclass
public abstract class BaseEntity extends AbstractModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return super.toString() + "BaseEntity{" +
                "id=" + id +
                '}';
    }
}
