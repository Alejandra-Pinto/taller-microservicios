package com.example.notification.entity;

import com.example.notification.entity.enums.EstadoDocumento;
import jakarta.persistence.*;

@Entity
public class Document {
    @Id
    private Long id;
    private String url;

    @Enumerated(EnumType.STRING)
    private EstadoDocumento estado;

    @ManyToOne
    private DegreeWork degreeWork;

    // getters/setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }

    public EstadoDocumento getEstado() {
        return estado;
    }
    public void setEstado(EstadoDocumento estado) {
        this.estado = estado;
    }

    public DegreeWork getDegreeWork() {
        return degreeWork;
    }
    public void setDegreeWork(DegreeWork degreeWork) {
        this.degreeWork = degreeWork;
    }

}

