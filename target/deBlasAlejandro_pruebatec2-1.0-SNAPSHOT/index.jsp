<%-- 
    Document   : index
    Created on : 23 nov 2023, 17:43:29
    Author     : alexd
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.sql.Date"%>
<%@page import="com.mycompany.deblasalejandro_pruebatec2.logica.Turno"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.mycompany.deblasalejandro_pruebatec2.logica.Ciudadano"%>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Turnero</title>
        <!-- Agregar estilos en CSS -->
        <style>
            /*Estilo de la pagina en CSS*/
            :root {
                /*Colores de la pagina*/
                --primary-color: #3A6EA5; /* Azul */
                --secondary-color: #004E98; /* azul oscuro */
                --accent-color: #f5cb5c; /* naranja */
                --text-color: #e7ecef; /* Negro */
                --background-color: #6096ba; /* azul claro */
            }
            body {
                font-family: Arial, sans-serif;
                background-color: var(--background-color);
                margin: 0;
                padding: 0; 
                display: flex;
                height: 100vh;
            }
            .left-section {
                width: 30%;
                padding: 20px;
                margin: 5%;
            }
            .right-section {
                width: 60%;
            }
            .form-group{
                color: var(--text-color);
                font-size: 20px;
            }
            #fecha {
                width: 100%; 
                padding: 8px;
                box-sizing: border-box;
            }
            #estado {
                width: 100%; 
                padding: 8px;
                box-sizing: border-box;
            }
            .form-control {
                margin-bottom: 15px; 
            }
            form {
                background-color: var(--primary-color);
                padding: 20px;
                border-radius: 8px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                max-width: 500px;
                width: 100%;
            }
            label {
                display: block;
                margin-bottom: 8px;
            }
            input {
                width: 100%;
                padding: 8px;
                margin-bottom: 16px;
                box-sizing: border-box;
            }
            button {
                background-color: var(--secondary-color);
                color: var(--accent-color);
                padding: 10px 15px;
                margin-bottom: 10px;
                border: none;
                border-radius: 4px;
                cursor: pointer;
            }
            button:hover {
                background-color: var(--accent-color);
                color: var(--secondary-color);
            }
            table {
                margin-top: 20px;
                width: 100%;
                border-collapse: collapse;
            }
            h2 {
                background-color: var(--background-color);
                color: var(--text-color);
            }
            h3 {               
                color: var(--text-color);
            }
            td {
                border: 1px solid var(--primary-color);
                padding: 8px;
                text-align: left;
                color: var(--text-color);
            }
            th{
                border: 1px solid var(--primary-color);
                padding: 8px;
                text-align: left;
                color: var(--accent-color);
            }        
        </style>
    </head>
    <body>
        <!-- Dividimos la web en dos mitades, una para los ciudadanos y la otra para los turnos -->
        <div class="left-section">
            <div >
                <h2>Ciudadanos</h2>
                <form action="CiudadanoSv" method="post">
                    <div class="form-group">
                        <label for="nombre">Nombre:</label>
                        <input type="text" class="form-control" id="nombre" name="nombre">
                    </div>
                    <div class="form-group">
                        <label for="apellido">Apellido:</label>
                        <input type="text" class="form-control" id="apellido" name="apellido">
                    </div>
                    <button type="submit">Añadir</button>
                    <!-- Manejo de errores con mensaje para el usuario -->
                    <%
                        String error = request.getParameter("error");
                        if (error != null && error.equals("CampoVacioUsuario")) {
                    %>
                        <div style="color: red;">Usuario no Añadido</div>
                        <div style="color: red;">Asegurese de no dejar ningun campo vacio</div>
                    <%
                        }
                    %>
                </form>
            </div>
            <div >
                <h2>Tabla Ciudadanos</h2>
                <form action="CiudadanoSv" method="get">
                    <button type="submit">Mostrar</button>
                    <!-- Resultados de Ciudadanos -->
                    <div class="results-table">
                     <% if (request.getAttribute("ciudadanos") != null) { %>
                            <h3>Ciudadanos:</h3>
                            <table class="table">
                                <thead>
                                    <tr>
                                        <th>Id</th>
                                        <th>Nombre</th>
                                        <th>Apellido</th>
                                        <th>Turnos</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <% for (Ciudadano ciudadano : (ArrayList<Ciudadano>) request.getAttribute("ciudadanos")) { %>
                                        <tr>
                                            <td><%= ciudadano.getId() %></td>
                                            <td><%= ciudadano.getNombre() %></td>
                                            <td><%= ciudadano.getApellido() %></td>
                                            <td><%= ciudadano.getTurnos().size() %></td>
                                        </tr>
                                    <% } %>
                                </tbody>
                            </table>
                        <% } %>
                    </div>
                </form>
            </div>
        </div>  
        <div class="right-section">
            <div >
                <h2>Turnos</h2>
                <form action="TurnoSv" method="post">
                    <div class="form-group">
                        <label for="fecha">Fecha:</label>
                        <input type="date" class="form-control" id="fecha" name="fecha">
                    </div>
                    <div class="form-group">
                        <label for="estado">Estado:</label>
                        <select class="form-control" id="estado" name="estado">
                            <option value="En espera">En espera</option>
                            <option value="Atendido">Atendido</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="tramite">Tramite:</label>
                        <input type="text" class="form-control" id="tramite" name="tramite">
                    </div>
                    <div class="form-group">
                        <label for="ciudadano">Id Ciudadano:</label>
                        <input type="text" class="form-control" id="ciudadano" name="ciudadano">
                    </div>
                    <button type="submit">Añadir</button>
                    <!-- Manejo de errores con mensaje para el Turno -->
                    <%
                        error = request.getParameter("error");
                        if (error != null && error.equals("CampoVacioTurno")) {
                    %>
                        <div style="color: red;">Turno no Añadido</div>
                        <div style="color: red;">Asegurese de no dejar ningun campo vacio</div>
                    <%
                        } else if (error != null && error.equals("CiudadanoNoEncontrado")) {
                    %>
                        <div style="color: red;">Turno no Añadido</div>
                        <div style="color: red;">Parece que el usuario indicado no existe, creelo antes de añadir</div>
                    <%
                        }
                    %>
                </form>              
            </div>
            <div >
                <h2>Tabla Turnos</h2>
                <form action="TurnoSv" method="get">
                    <button type="submit" name="accion" value="mostrar">Mostrar</button>
                    <button type="submit" name="accion" value="filtrarFecha">Filtrar por Fecha</button>
                    <div class="form-group">
                        <label for="filtroFechaUsu">Fecha:</label>
                        <input type="date" class="form-control" id="filtroFechaUsu" name="filtroFechaUsu" >         
                    </div>
                    <!-- If para mostrar las opciones de Filtrar por Estado solo si se ha filtrado por Fecha antes o si ya se ha modificado el Estado -->
                    <% if (request.getAttribute("filtroFecha") != null || request.getAttribute("filtroEstado") != null ) {%>
                    <button type="submit" name="accion" value="filtrarEstado">Filtrar por Estado</button>
                    <select class="form-control" id="filtrarEspera" name="filtrarEspera">
                        <option value="En espera">En espera</option>
                        <option value="Atendido">Atendido</option>
                    </select>
                    <!-- Script para guardar el valor de la fecha del usuario, almacenado en la url, y asi no tener que volver a escribirla en cada interaccion -->
                    <script type="text/javascript">
                        function getQueryVariable() {
                            // Obtener url y filtrar el parametro que le pases, en este caso filtroFechaUsu
                            let params = new URLSearchParams(location.search);
                            var filtroFU = params.get('filtroFechaUsu');                            
                            return filtroFU;
                        }                     
                        // Se guarda asi mismo para no utilizar otra variable o otro input
                        document.getElementById("filtroFechaUsu").value = getQueryVariable('filtroFechaUsu');
                    </script>
                    <% } %>
                    <% 
                        //Codigo para mostrar una tabla distinta segun el boton seleccionado
                        ArrayList<String> atributos = new ArrayList<>();
                        if (request.getParameter("accion") != null) {
                            switch (request.getParameter("accion")) {
                                case "mostrar":
                                    atributos.add("turnos");
                                    break;
                                case "filtrarFecha":
                                    atributos.add("filtroFecha");
                                    break;
                                case "filtrarEstado":
                                    atributos.add("filtroEstado");
                                    break;
                            }
                        }
                        
                        // Recorrer el array completo
                        for (String atributo : atributos) {
                            // Verificar si el atributo existe en la solicitud
                            if (request.getAttribute(atributo) != null) { 
                    %>
                    <!-- Resultados en tabla -->
                    <div class="results-table">
                        <h3><%= atributo %>:</h3>
                        <table class="table">
                            <thead>
                                <tr>
                                    <th>Id</th>
                                    <th>Fecha</th>
                                    <th>Estado</th>
                                    <th>Trámite</th>
                                    <th>Id Ciudadano</th>
                                    <th>Ciudadano</th>
                                </tr>
                            </thead>
                            <tbody>
                                <% 
                                // Recorrer los elementos del atributo
                                for (Turno turno : (ArrayList<Turno>) request.getAttribute(atributo)) { 
                                %>
                                    <tr>
                                        <td><%= turno.getId() %></td>
                                        <td><%= turno.getFecha() %></td>
                                        <td><%= turno.getEstado() %></td>
                                        <td><%= turno.getTramite() %></td>
                                        <td><%= turno.getCiudadano().getId()%></td>
                                        <td><%= turno.getCiudadano().getNombre() %></td>
                                    </tr>
                                <% } %>
                            </tbody>
                        </table>
                        <% 
                            }
                        } 
                        %>
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>


