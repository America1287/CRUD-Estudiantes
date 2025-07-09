package controlador;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Estudiante;
import modelo.dao.EstudianteDAO;

public class EstudianteControlador extends HttpServlet {
    
    private final EstudianteDAO estDao = new EstudianteDAO();
    private final String pagListar = "/vista/listar.jsp";
    private final String pagNuevo = "/vista/nuevo.jsp";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String accion = request.getParameter("accion");
        System.out.println("Acción recibida: " + accion); // Útil para depuración

        if (accion == null) {
            // Acción no especificada: redirigir a listar
            listar(request, response);
            return;
        }
        
        switch (accion) {
            case "listar":
                listar(request, response);
                break;
            case "nuevo":
                nuevo(request, response);
                break;
            case "guardar":
                guardar(request, response);
                break;
            case "editar":
                editar(request, response);
                break;
            case "eliminar":
                eliminar(request, response);
                break;
            default:
                // Acción no reconocida: mostrar error controlado
                request.setAttribute("mensajeError", "Acción no válida: " + accion);
                request.getRequestDispatcher(pagListar).forward(request, response);
                break;
            //throw new AssertionError();
        }
    }
    
    private void eliminar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        
        int result = estDao.eliminar(id);
        
        if (result > 0) {
            request.getSession().setAttribute("success", "Registro del estudiante eliminado");
        } else {
            request.getSession().setAttribute("error", "No se pudo eliminar el registro del estudiante");
        }
        response.sendRedirect("EstudianteControlador?accion=listar");
    }
    
    private void editar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int id = Integer.parseInt(request.getParameter("id"));
        
        Estudiante obj = estDao.buscarPorId(id);
        
        System.out.println(" ID recibido desde el formulario: " + request.getParameter("id")); // 👈 AQUI
        System.out.println(" ID convertido a entero: " + obj.getId()); // 👈 Y AQUI

        if (obj != null) {
            request.setAttribute("estudiante", obj);
            request.getRequestDispatcher(pagNuevo).forward(request, response);
        } else {
            request.getSession().setAttribute("error", "No existe el estudiante con ID " + id);
            response.sendRedirect("EstudianteControlador?accion=listar");
        }
        
    }
    
    private void guardar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Estudiante obj = new Estudiante();
        obj.setId(Integer.parseInt(request.getParameter("id")));
        obj.setNombres(request.getParameter("nombres"));
        obj.setApellidos(request.getParameter("apellidos"));
        obj.setDocumento(Long.parseLong(request.getParameter("documento")));
        
        int result;
        
        if (obj.getId() == 0) {
            result = estDao.registrar(obj);
        } else {
            result = estDao.editar(obj);
        }
        
        if (result > 0) {
            request.getSession().setAttribute("success", "Datos guardados correctamente.");
            response.sendRedirect("EstudianteControlador?accion=listar");
        } else {
            request.getSession().setAttribute("error", "No se pudieron guardar los datos.");
            request.setAttribute("estudiante", obj);
            request.getRequestDispatcher(pagNuevo).forward(request, response);
        }
        
    }
    
    private void nuevo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.setAttribute("estudiante", new Estudiante());
        request.getRequestDispatcher(pagNuevo).forward(request, response);
    }
    
    protected void listar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        request.setAttribute("estudiantes", estDao.ListarTodos());
        request.getRequestDispatcher(pagListar).forward(request, response);
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        processRequest(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        processRequest(request, response);
    }
    
    @Override
    public String getServletInfo() {
        return "Controlador de estudiante para manejar acciones como listar.";
    }
    
}
