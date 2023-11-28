/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.deblasalejandro_pruebatec2.logica;

import com.mycompany.deblasalejandro_pruebatec2.persistencia.ControladoraPersistencia;
import java.util.List;

/**
 *
 * @author alexd
 */
public class Controladora {
    ControladoraPersistencia controlPersis = new ControladoraPersistencia();
    
    //------------------Controles Ciudadano------------------
    public void crearCiudadano (Ciudadano ciuda) {
        controlPersis.crearCiudadano(ciuda);
    }
    
    public void eliminarCiudadano (Long id) {
        controlPersis.eliminarCiudadano(id);
    }
    
    public List<Ciudadano> traerCiudadano () {
        return controlPersis.traerCiudadano();
    }
    
    public Ciudadano findCiudadanoNombre (String nombre) {
        return controlPersis.findCiudadanoNombre (nombre);
    }
    
    public Ciudadano getCiudadano (Long id) {
        return controlPersis.getCiudadano(id);
    }
    
    public void editarCiudadano (Ciudadano ciuda) {
        controlPersis.editarCiudadano(ciuda);
    }
    
    //------------------Controles Turno------------------
    public void crearTurno (Turno turno) {
        controlPersis.crearTurno(turno);
    }
    
    public void eliminarTurno (Long id) {
        controlPersis.eliminarTurno(id);
    }
    
    public List<Turno> traerTurno () {
        return controlPersis.traerTurno();
    }
    
    public Turno findTurno(Long id){
        return controlPersis.findTurno(id);
    }
    
    public Turno getTurno (Long id) {
        return controlPersis.getTurno(id);
    }
    
    public void editarTurno (Turno turno) {
        controlPersis.editarTurno(turno);
    }
}
