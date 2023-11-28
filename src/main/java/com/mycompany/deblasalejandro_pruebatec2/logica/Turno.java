/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.deblasalejandro_pruebatec2.logica;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author alexd
 */

@Entity
public class Turno implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date fecha;
    private String estado;
    private String tramite;
    
    //Mismo mapeo pero en direccion contraria, estableciendo cada turno el id del Ciudadano que lo tiene
    @ManyToOne
    @JoinColumn(name = "ciudadano_Id")
    private Ciudadano ciudadano;
    
    //Contructor
    public Turno() {
    }

    public Turno(Long id, Date fecha, String estado, String tramite, Ciudadano ciudadano) {
        this.id = id;
        this.fecha = fecha;
        this.estado = estado;
        this.tramite = tramite;
        this.ciudadano = ciudadano;
    }
    
    //Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTramite() {
        return tramite;
    }

    public void setTramite(String tramite) {
        this.tramite = tramite;
    }

    public Ciudadano getCiudadano() {
        return ciudadano;
    }

    public void setCiudadano(Ciudadano ciudadano) {
        this.ciudadano = ciudadano;
    }
    
}
