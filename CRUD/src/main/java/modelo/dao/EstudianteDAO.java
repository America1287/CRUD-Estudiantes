package modelo.dao;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import modelo.Estudiante;

public class EstudianteDAO {

    private Connection cn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public ArrayList<Estudiante> ListarTodos() {
        ArrayList<Estudiante> lista = new ArrayList<>();

        try {
            cn = Conexion.getConnection();
            String sql = "SELECT * FROM estudiante";
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Estudiante obj = new Estudiante();
                obj.setId(rs.getInt("id_estudiante"));
                obj.setNombres(rs.getString("nombres"));
                obj.setApellidos(rs.getString("apellidos"));
                obj.setDocumento(rs.getLong("documento"));
                lista.add(obj);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (cn != null) {
                    cn.close();
                }
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (Exception ex) {
            }
        }

        return lista;
    }

    public int registrar(Estudiante obj) {
        int result = 0;

        try {
            cn = Conexion.getConnection();
            String sql = "INSERT INTO estudiante (nombres,apellidos,documento)"
                    + "VALUES (?,?,?)";
            ps = cn.prepareStatement(sql);
            ps.setString(1, obj.getNombres());
            ps.setString(2, obj.getApellidos());
            ps.setLong(3, obj.getDocumento());

            result = ps.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (cn != null) {
                    cn.close();
                }

                if (ps != null) {
                    ps.close();
                }
            } catch (Exception ex) {
            }
        }

        return result;
    }

    public int editar(Estudiante obj) {
        int result = 0;

        try {
            cn = Conexion.getConnection();
            String sql = "UPDATE estudiante SET nombres=?, apellidos=?, documento=? WHERE id_estudiante=?";
            ps = cn.prepareStatement(sql);
            ps.setString(1, obj.getNombres());
            ps.setString(2, obj.getApellidos());
            ps.setLong(3, obj.getDocumento());
            ps.setInt(4, obj.getId());  // Aquí estaba mal antes: usabas índice 5

            result = ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (cn != null) {
                    cn.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (Exception ex) {
            }
        }

        return result;
    }

    public int eliminar(int id) {
        int result = 0;

        try {
            cn = Conexion.getConnection();
            String sql = "DELETE FROM estudiante WHERE id_estudiante = ?";
            ps = cn.prepareStatement(sql);
            ps.setInt(1, id);
            result = ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (cn != null) {
                    cn.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (Exception ex) {
            }
        }

        return result;
    }

    public Estudiante buscarPorId(int id) {
        Estudiante obj = null;

        try {
            cn = Conexion.getConnection();
            String sql = "SELECT * FROM estudiante WHERE id_estudiante = ?";
            ps = cn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                obj = new Estudiante();
                obj.setId(rs.getInt("id_estudiante"));
                obj.setNombres(rs.getString("nombres"));
                obj.setApellidos(rs.getString("apellidos"));
                obj.setDocumento(rs.getLong("documento"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (cn != null) {
                    cn.close();
                }
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (Exception ex) {
            }
        }

        return obj;
    }
}
