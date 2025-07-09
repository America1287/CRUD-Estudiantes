<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css" integrity="sha512-Evv84Mr4kqVGRNSgIGL/F/aIDqQb7xQ2vcrdIwxfjThSH8CSR7PBEakCr51Ck+w+/U6swU2Im1vVX0SVk9ABhg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <title>Formulario Estudiante</title>
    </head>
    <body>
        <div class="container mt-3">
            <div class="card">                
                <div class="card-body">
                    <h3>${estudiante.id == 0 ? "Nuevo ": "Editar "}Estudiante</h3>
                    <hr />
                    <form action="EstudianteControlador" method="post">
                        <div class="mb-3">
                            <label>Nombres</label>
                            <input value="${estudiante.nombres}" name="nombres" type="text" 
                                   maxlength="50" class="form-control" required="">                            
                        </div>
                        <div class="mb-3">
                            <label>Apellidos</label>
                            <input value="${estudiante.apellidos}" name="apellidos" type="text" 
                                   maxlength="50" class="form-control" required="">                            
                        </div>
                        <div class="mb-3">
                            <label>Documento</label>
                            <input value="${estudiante.documento}" name="documento" type="number" min="1" class="form-control" required="">                            
                        </div>
                        <div class="mb-3">
                            <input type="hidden" name="id" value="${estudiante.id}">
                            <input type="hidden" name="accion" value="guardar">
                            <button class="btn btn-primary btn-sm">
                                <i class="fa fa-save"></i> Guardar                                
                            </button>
                            <a href="EstudianteControlador?accion=listar" class="btn btn-dark btn-sm">
                                <i class="fa fa-arrow-left"></i> Volver atras
                            </a>
                        </div>
                    </form>                    
                </div>                
            </div>            
        </div>
    </body>
</html>
