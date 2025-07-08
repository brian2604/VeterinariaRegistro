package dao;

import conexion.Conexion;
import vo.MascotaVo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MascotaDao {

    private final Conexion conexion;

    public MascotaDao() {
        this.conexion = Conexion.getInstance(); // Usamos Singleton
    }

    public void registrarMascota(MascotaVo mascota) {
        String sql = "INSERT INTO mascotas (nombre, id_dueno, raza, sexo) VALUES (?, ?, ?, ?)";
        Connection con = conexion.getConexion();
        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, mascota.getNombre());
            ps.setString(2, mascota.getIdDueno());
            ps.setString(3, mascota.getRaza());
            ps.setString(4, mascota.getSexo());
            ps.executeUpdate();

        } catch (SQLException e) {
            if (e.getMessage().contains("foreign key constraint fails")) {
                throw new RuntimeException("No se puede registrar la mascota, el duaño aun no existe");
            }else {
                e.printStackTrace();
            }

        }
    }

    public MascotaVo consultar(String nombre) {
        String sql = "SELECT * FROM mascotas WHERE nombre = ?";
        Connection con = conexion.getConexion();
        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nombre);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                MascotaVo mascota = new MascotaVo();
                mascota.setNombre(rs.getString("nombre"));
                mascota.setIdDueno(rs.getString("id_dueno"));
                mascota.setRaza(rs.getString("raza"));
                mascota.setSexo(rs.getString("sexo"));
                return mascota;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void eliminar(String nombre) {
        String sql = "DELETE FROM mascotas WHERE nombre = ?";
        Connection con = conexion.getConexion();
        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nombre);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void actualizar(MascotaVo mascota) {
        String sql = "UPDATE mascotas SET raza = ?, sexo = ?, id_dueno = ? WHERE nombre = ?";
        Connection con = conexion.getConexion();
        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, mascota.getRaza());
            ps.setString(2, mascota.getSexo());
            ps.setString(3, mascota.getIdDueno());
            ps.setString(4, mascota.getNombre());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String listar() {
        StringBuilder sb = new StringBuilder();
        String sql = "SELECT * FROM mascotas";
        Connection con = conexion.getConexion();
        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                sb.append("Nombre: ").append(rs.getString("nombre")).append("\n");
                sb.append("ID Dueño: ").append(rs.getString("id_dueno")).append("\n");
                sb.append("Raza: ").append(rs.getString("raza")).append("\n");
                sb.append("Sexo: ").append(rs.getString("sexo")).append("\n\n");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return sb.toString();
    }

}

