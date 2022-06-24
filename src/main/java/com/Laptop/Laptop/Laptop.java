package com.Laptop.Laptop;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class Laptop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private Long precio;
    private LocalDate fabricacion;
    private Boolean online;

    public Laptop() {
    }

    public Laptop(Long id, String titulo, Long precio, LocalDate fabricacion, Boolean online) {
        this.id = id;
        this.titulo = titulo;
        this.precio = precio;
        this.fabricacion = fabricacion;
        this.online = online;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Long getPrecio() {
        return precio;
    }

    public void setPrecio(Long precio) {
        this.precio = precio;
    }

    public LocalDate getFabricacion() {
        return fabricacion;
    }

    public void setFabricacion(LocalDate fabricacion) {
        this.fabricacion = fabricacion;
    }

    public Boolean getOnline() {
        return online;
    }

    public void setOnline(Boolean online) {
        this.online = online;
    }
}
