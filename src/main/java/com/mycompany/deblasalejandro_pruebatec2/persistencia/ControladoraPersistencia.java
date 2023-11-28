/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.deblasalejandro_pruebatec2.persistencia;

import com.mycompany.deblasalejandro_pruebatec2.logica.Ciudadano;
import com.mycompany.deblasalejandro_pruebatec2.logica.Turno;
import com.mycompany.deblasalejandro_pruebatec2.persistencia.exceptions.NonexistentEntityException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alexd
 */
public class ControladoraPersistencia {
    CiudadanoJpaController ciudadanoJPA = new CiudadanoJpaController();
    TurnoJpaController turnoJPA = new TurnoJpaController();
    
    //------------------Controlador Ciudadano------------------
    public void crearCiudadano (Ciudadano ciudadano) {
        ciudadanoJPA.create(ciudadano);
    }
    
    public void eliminarCiudadano (Long id) {
        try {
            ciudadanoJPA.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Ciudadano> traerCiudadano () {
        return ciudadanoJPA.findCiudadanoEntities();
    }
    
    public Ciudadano findCiudadanoNombre (String nombre) {
        return ciudadanoJPA.findCiudadanoNombre(nombre);
    }
    
    public Ciudadano getCiudadano (Long id) {
        return ciudadanoJPA.findCiudadano(id);
    }
    
    public void editarCiudadano (Ciudadano ciudadano) {
        try {
            ciudadanoJPA.edit(ciudadano);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
    
    //------------------Controlador Turno------------------
    public void crearTurno (Turno turno) {
        turnoJPA.create(turno);
    }
    
    public void eliminarTurno (Long id) {
        try {
            turnoJPA.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Turno> traerTurno () {
    return turnoJPA.findTurnoEntities();
    }
    
    public Turno getTurno (Long id) {
        return turnoJPA.findTurno(id);
    }
    
    public Turno findTurno (Long id) {
        return turnoJPA.findTurno(id);
    }
    
    public void editarTurno (Turno turno) {
        try {
            turnoJPA.edit(turno);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
