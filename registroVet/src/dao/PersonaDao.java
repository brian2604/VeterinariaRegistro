package dao;

import conexion.Conexion;

import vo.MascotaVo;
import vo.PersonaVo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class PersonaDao {

    private final Conexion conexion;

    public PersonaDao() {
        this.conexion = Conexion.getInstance();
    }

    public void registrarPersona(PersonaVo persona) {
        try {
            Connection conn = Conexion.getInstance().getConexion();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO personas (documento, nombre, telefono) VALUES (?, ?, ?)");
            ps.setString(1, persona.getDocumento());
            ps.setString(2, persona.getNombre());
            ps.setString(3, persona.getTelefono());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public PersonaVo consultar(String documento) {
        String sql = "SELECT * FROM personas WHERE documento = ?";
        Connection con = conexion.getConexion();
        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, documento);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                PersonaVo persona = new PersonaVo();
                persona.setDocumento(rs.getString("documento"));
                persona.setNombre(rs.getString("nombre"));
                persona.setTelefono(rs.getString("telefono"));
                return persona;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void eliminar(String documento) {
        String sql = "DELETE FROM personas WHERE documento = ?";
        Connection con = conexion.getConexion();
        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, documento);
            ps.executeUpdate();

        } catch (SQLException e) {
            if (e.getMessage().contains("foreign key constraint fails")) {
                throw new RuntimeException("No se puede eliminar la persona porque tiene mascotas registradas");
                
            }else {
                e.printStackTrace();
            }

        }
    }

    public void actualizar(PersonaVo persona) {
        String sql = "UPDATE personas SET nombre = ?, telefono = ? WHERE documento = ?";
        Connection con = conexion.getConexion();
        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, persona.getNombre());
            ps.setString(2, persona.getTelefono());
            ps.setString(3, persona.getDocumento());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String listar() {
        StringBuilder sb = new StringBuilder();
        String sql = "SELECT * FROM personas";
        Connection con = conexion.getConexion();
        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                sb.append("Documento: ").append(rs.getString("documento")).append("\n");
                sb.append("Nombre: ").append(rs.getString("nombre")).append("\n");
                sb.append("Telefono: ").append(rs.getString("telefono")).append("\n\n");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return sb.toString();
    }

    public boolean existeDocumento(String documento) {
        String sql = "SELECT 1 FROM personas WHERE documento = ?";
        Connection con = conexion.getConexion();
        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, documento);
            ResultSet rs = ps.executeQuery();
            return rs.next(); // Si hay resultados, ya existe

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


}
