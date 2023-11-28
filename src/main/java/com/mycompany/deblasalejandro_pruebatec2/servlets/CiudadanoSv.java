/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.deblasalejandro_pruebatec2.servlets;

import com.mycompany.deblasalejandro_pruebatec2.logica.Ciudadano;
import com.mycompany.deblasalejandro_pruebatec2.logica.Controladora;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author alexd
 */
@WebServlet("/CiudadanoSv")
public class CiudadanoSv extends HttpServlet {

    private List<Ciudadano> listaCiudadano = new ArrayList<>();
    private Controladora control = new Controladora();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        
        if (!nombre.isEmpty() && !apellido.isEmpty()) {
            Ciudadano ciudadano = new Ciudadano();
            ciudadano.setNombre(nombre);
            ciudadano.setApellido(apellido);

            control.crearCiudadano(ciudadano);

            response.sendRedirect("index.jsp");
        } else {
            // Comprobacion para que no existan campos vacios
            response.sendRedirect("index.jsp?error=CampoVacioUsuario");
        }
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      
        //Traeremos la lista de objetos desde la base de datos
        listaCiudadano = control.traerCiudadano();
        
        ArrayList<Ciudadano> ciudadanosLista = new ArrayList<>();

        for (Ciudadano ciudadano : listaCiudadano) {
            ciudadanosLista.add(ciudadano);
        }
                
        // Establecer los resultados en la solicitud para que se muestren en el JSP
        request.setAttribute("ciudadanos", ciudadanosLista);

        // Redirigir de vuelta al formulario
        request.getRequestDispatcher("index.jsp").forward(request, response);
        
    }
}
