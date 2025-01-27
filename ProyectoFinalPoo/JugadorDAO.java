package o2024.Casino;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JugadorDAO {
    public void guardarJugador(Jugador jugador) {
        String sql = "INSERT INTO jugadores (nombre, apellidos, diaNacimiento, mesNacimiento, añoNacimiento, estadoCivil, sexo, saldo) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, jugador.getNombre());
            stmt.setString(2, jugador.getApellidos());
            stmt.setInt(3, jugador.getFechaNacimiento().getDia());
            stmt.setInt(4, jugador.getFechaNacimiento().getMes());
            stmt.setInt(5, jugador.getFechaNacimiento().getAño());
            stmt.setString(6, jugador.getEstadoCivil().toString());
            stmt.setString(7, jugador.getSexo().toString());
            stmt.setDouble(8, jugador.getSaldo());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al guardar el jugador: " + e.getMessage());
        }
    }

    public Jugador obtenerJugadorPorNombre(String nombre) {
        String sql = "SELECT * FROM jugadores WHERE nombre = ?";
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nombre);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Jugador(
                    rs.getString("nombre"),
                    rs.getString("apellidos"),
                    new Fecha(rs.getInt("diaNacimiento"), rs.getInt("mesNacimiento"), rs.getInt("anioNacimiento")), // Cambié año por anio
                    EstadoCivil.valueOf(rs.getString("estadoCivil")),
                    Sexo.valueOf(rs.getString("sexo")),
                    NivelJugador.valueOf(rs.getString("nivel")), // Recuperar el nivel desde la base de datos
                    rs.getDouble("saldo"),
                    rs.getInt("numeroApuestas") // Añadir número de apuestas
                );
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener el jugador: " + e.getMessage());
        }
        return null;
    }

    public void actualizarSaldo(String nombre, double nuevoSaldo) {
        String sql = "UPDATE jugadores SET saldo = ? WHERE nombre = ?";
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, nuevoSaldo);
            stmt.setString(2, nombre);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al actualizar el saldo: " + e.getMessage());
        }
    }

    public Jugador obtenerJugadorPorId(int id) {
        String sql = "SELECT * FROM jugadores WHERE id = ?";
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Jugador(
                    rs.getString("nombre"),
                    rs.getString("apellidos"),
                    new Fecha(rs.getInt("diaNacimiento"), rs.getInt("mesNacimiento"), rs.getInt("añoNacimiento")),
                    EstadoCivil.valueOf(rs.getString("estadoCivil")),
                    Sexo.valueOf(rs.getString("sexo")),
                    NivelJugador.valueOf(rs.getString("nivel")),
                    rs.getDouble("saldo"),
                    rs.getInt("numeroApuestas")  // Recuperar el número de apuestas
                );
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener el jugador por ID: " + e.getMessage());
        }
        return null;
    }
    
    
    public void actualizarNumeroApuestas(String nombre, int numeroApuestas) {
        String sql = "UPDATE jugadores SET numeroApuestas = ? WHERE nombre = ?";
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, numeroApuestas);
            stmt.setString(2, nombre);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al actualizar el número de apuestas: " + e.getMessage());
        }
    }
    
    
    public void actualizarNivel(String nombre, NivelJugador nivel) {
        String sql = "UPDATE jugadores SET nivel = ? WHERE nombre = ?";
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nivel.toString());
            stmt.setString(2, nombre);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al actualizar el nivel del jugador: " + e.getMessage());
        }
    }

    
    
}
