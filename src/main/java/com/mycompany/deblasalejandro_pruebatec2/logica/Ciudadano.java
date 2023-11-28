/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.deblasalejandro_pruebatec2.logica;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author alexd
 */

@Entity
public class Ciudadano implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String Apellido;
   
    //Mapeamos la base de datos para que las dos tablas esten comunicadas, dejando que cada ciudadano tenga una lista de turnos
    @OneToMany(mappedBy = "ciudadano")
    private List<Turno> turnos;

    //Contructores
    public Ciudadano() {
    }

    public Ciudadano(Long id, String nombre, String Apellido, List<Turno> turnos) {
        this.id = id;
        this.nombre = nombre;
        this.Apellido = Apellido;
        this.turnos = turnos;
    }
    
    //Getters y Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String Apellido) {
        this.Apellido = Apellido;
    }

    public List<Turno> getTurnos() {
        return turnos;
    }

    public void setTurnos(List<Turno> turnos) {
        this.turnos = turnos;
    }
}
