<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css" integrity="sha512-Evv84Mr4kqVGRNSgIGL/F/aIDqQb7xQ2vcrdIwxfjThSH8CSR7PBEakCr51Ck+w+/U6swU2Im1vVX0SVk9ABhg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <title>Estudiante</title>
    </head>
    <body>
        <div class="container mt-3">
            <div class="card">                
                <div class="card-body">
                    <h3>Gestión Estudiantes</h3>
                    <hr />
                    <a href="EstudianteControlador?accion=nuevo" class="btn btn-success btn-sm">
                        <i class="fa fa-plus-circle"></i> Nuevo
                    </a>
                    <jsp:include page="../components/Mensaje.jsp" />
                    <table class="table table-bordered table-striped mt-2">
                        <thead>
                            <tr>
                                <th>Id</th>
                                <th>Nombres</th>
                                <th>Apellidos</th>
                                <th>Documento</th>
                                <th>Acciones</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items = "${estudiantes}" var="item">
                                <tr>
                                    <td>${item.id}</td>
                                    <td>${item.nombres}</td>
                                    <td>${item.apellidos}</td>
                                    <td>${item.documento}</td>
                                    <td>
                                        <a href="EstudianteControlador?accion=editar&id=${item.id}" class="btn btn-info btn-sm">
                                            <i class="fa fa-edit"></i>
                                        </a>
                                        <a href="EstudianteControlador?accion=eliminar&id=${item.id}" 
                                           onclick="return confirm('¿Desea eliminar el registro?')"
                                           class="btn btn-danger btn-sm">
                                            <i class="fa fa-trash"></i>
                                        </a>
                                    </td>
                                </tr>                    
                            </c:forEach>
                            <c:if test="${estudiantes.size()== 0}">
                                <tr>
                                    <td colspan="5">No hay registros</td>
                                </tr>
                            </c:if>
                        </tbody>
                    </table>
                </div>            
            </div>            
        </div>
    </body>
</html>
