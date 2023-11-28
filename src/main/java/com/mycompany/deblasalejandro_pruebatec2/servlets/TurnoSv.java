/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.deblasalejandro_pruebatec2.servlets;

import com.mycompany.deblasalejandro_pruebatec2.logica.Ciudadano;
import com.mycompany.deblasalejandro_pruebatec2.logica.Controladora;
import com.mycompany.deblasalejandro_pruebatec2.logica.Turno;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author alexd
 */
@WebServlet("/TurnoSv")
public class TurnoSv extends HttpServlet {
    
    private List<Turno> listaTurno = new ArrayList<>();
    private Controladora control = new Controladora();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
        String fechaUsu = request.getParameter("fecha");
        String estadoUsu = request.getParameter("estado");
        String tramiteUsu = request.getParameter("tramite");
        String ciudadanoUsu = request.getParameter("ciudadano");
        
        if (!fechaUsu.isEmpty() && !estadoUsu.isEmpty() && !tramiteUsu.isEmpty() && !ciudadanoUsu.isEmpty()) {
            //Parseamos la variable para poder buscar el id del Ciudadano y guardarlo en una variable
            long idCiudadano = Long.parseLong(ciudadanoUsu);
            Ciudadano ciudadano = control.getCiudadano(idCiudadano);

            if (ciudadano != null) {
                Turno turno = new Turno();

                turno.setEstado(estadoUsu);
                turno.setTramite(tramiteUsu);          
                turno.setCiudadano(ciudadano);

                // Convertir la cadena de fecha a un objeto Date(pasando primero por java.util.Date)
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");//formato de la fecha
                    java.util.Date fechaUtil = dateFormat.parse(fechaUsu);     
                    Date fecha = new Date(fechaUtil.getTime());           
                    turno.setFecha(fecha);
                } 
                catch (ParseException e) {
                    e.printStackTrace(); 
                }

                control.crearTurno(turno);

                response.sendRedirect("index.jsp");
            } else {
                // Manejar el caso en que el ciudadano no existe
                response.sendRedirect("index.jsp?error=CiudadanoNoEncontrado");
            }
        } else {
            // Comprobacion para que no existan campos vacios
            response.sendRedirect("index.jsp?error=CampoVacioTurno");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
        
        String accion = request.getParameter("accion");
        
        //Switch que maneja el boton accion para realizar las interacciones con el usuario
        if (accion != null) {
            switch (accion) {
                case "mostrar":
                    listaTurno = control.traerTurno();
                    
                    ArrayList<Turno> turnoLista = new ArrayList<>();
                    
                    for (Turno turno : listaTurno) { turnoLista.add(turno); }  

                    request.setAttribute("turnos", turnoLista);

                    request.getRequestDispatcher("index.jsp").forward(request, response);  
                    break;
                case "filtrarFecha":
                    listaTurno = control.traerTurno();
                    
                    String fechaUsu = request.getParameter("filtroFechaUsu");
                    
                    ArrayList<Turno> turnoFiltrado = new ArrayList<>();
                    
                    Date filtroFecha;
                    try {
                        filtroFecha = parsearFecha(fechaUsu); // llamada a una funcion para simplificar codigo
                        
                        for (Turno turno : listaTurno) {
                            if (turno.getFecha().equals(filtroFecha)) {
                                turnoFiltrado.add(turno);
                            }
                        }
                    } catch (ParseException ex) {
                        Logger.getLogger(TurnoSv.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    request.setAttribute("filtroFecha", turnoFiltrado);
                  
                    request.getRequestDispatcher("index.jsp").forward(request, response);  
                    break;
                case "filtrarEstado":
                    
                    listaTurno = control.traerTurno();
                    
                    String fechaUsuEspera = request.getParameter("filtroFechaUsu");
                    String estadoFiltro = request.getParameter("filtrarEspera");

                    Date filtroFechaEspera;
                    try {
                        filtroFechaEspera = parsearFecha(fechaUsuEspera);
                        
                        List<Turno> turnoFiltradoEspera = listaTurno.stream()
                            .filter(turno ->
                                    turno.getFecha().equals(filtroFechaEspera) &&
                                    turno.getEstado().equals(estadoFiltro))
                            .collect(Collectors.toList());
                        
                        request.setAttribute("filtroEstado", turnoFiltradoEspera);
                    } catch (ParseException ex) {
                        Logger.getLogger(TurnoSv.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    request.getRequestDispatcher("index.jsp").forward(request, response);  
                    break;
            }
        }         
    }
    
    public Date parsearFecha(String fechaUsu) throws ParseException{
        
        // Funcion sencilla para parsear la fecha
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");//formato de la fecha
        java.util.Date fechaUtil = dateFormat.parse(fechaUsu);  
        
        return new Date(fechaUtil.getTime());
    }
    
    
    
}
