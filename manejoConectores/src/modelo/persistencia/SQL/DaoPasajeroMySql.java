package modelo.persistencia.SQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.entidad.Pasajero;
import modelo.persistencia.interfaces.DaoPasajero;

public class DaoPasajeroMySql implements DaoPasajero {
	
private Connection connection;
	
	public boolean openConnection() {
		String url = "jdbc:mysql://localhost:3306/concesionario";
		String user = "root";
		String password = "";
		try {
			connection = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean closeConnection() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	
	

	@Override
	public boolean alta(Pasajero p) {
		
		boolean alta = false;
		if (openConnection()) {

			String consulta = "insert into pasajeros (NOMBRE, EDAD, PESO) values(?,?,?)";
			try {
				PreparedStatement ps = connection.prepareStatement(consulta);
				ps.setString(1, p.getNombre());
				ps.setInt(2, p.getEdad());
				ps.setDouble(3, p.getPeso());

				int numeroFilasAfectadas = ps.executeUpdate();
				if (numeroFilasAfectadas > 0) {
					alta = true;
				}
			} catch (SQLException e) {
				System.out.println("Error al insertar: " + p);
				e.printStackTrace();
			} finally {
				closeConnection();
			}
		}
		return alta;
	}
	
		
	
	@Override
	public boolean baja(int id) {
		boolean baja = false;
		if (openConnection()) {
			String consulta = "delete from pasajeros where id=?";
			try {
				PreparedStatement ps = connection.prepareStatement(consulta);
				ps.setInt(1, id);

				int filasAfectadas = ps.executeUpdate();
				if (filasAfectadas > 0) {
					baja = true;
				}
			} catch (SQLException e) {
				System.out.println("Error al borrar el identificador " + id);
				e.printStackTrace();
			} finally {
				closeConnection();
			}
		}
		return baja;
	}

	@Override
	public boolean modificar(Pasajero p) {
		boolean modificar = false;
		if (openConnection()) {

			String consulta = "update coches set NOMBRE=?, MARCA=?, EDAD=?, PESO=? WHERE ID=?";
			try {
				PreparedStatement ps = connection.prepareStatement(consulta);
				ps.setString(1, p.getNombre());
				ps.setInt(2, p.getEdad());
				ps.setDouble(3, p.getPeso());
				ps.setInt(5, p.getId());

				int filasAfectadas = ps.executeUpdate();
				if (filasAfectadas > 0) {
					modificar = true;
				}
			} catch (SQLException e) {
				System.out.println("Error al modificar pasajero: " + p);
				e.printStackTrace();
			} finally {
				closeConnection();
			}
		}
		return modificar;
	}

	@Override
	public Pasajero obtener(int id) {
		Pasajero pasajero = null;
		if (openConnection()) {

			String consulta = "SELECT * from pasajeros WHERE id_coche = ?";
			try {
				PreparedStatement ps = connection.prepareStatement(consulta);
				ps.setInt(1, id);

				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					pasajero = new Pasajero();
					pasajero.setId(rs.getInt(1));
					pasajero.setNombre(rs.getString(2));
					pasajero.setEdad(rs.getInt(3));
					pasajero.setPeso(rs.getDouble(4));					
				}
			} catch (SQLException e) {
				System.out.println("Error al obtener el pasajero con id " + id);
				e.printStackTrace();
			} finally {
				closeConnection();
			}
		}
		return pasajero;
	}

	@Override
	public List<Pasajero> listar() {
		List<Pasajero> listaPasajeros = new ArrayList<>();
		if (openConnection()) {

			String consulta = "select * from pasajeros";
			try {
				PreparedStatement ps = connection.prepareStatement(consulta);

				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					Pasajero pasajero = new Pasajero();
					pasajero.setId(rs.getInt(1));
					pasajero.setNombre(rs.getString(2));
					pasajero.setEdad(rs.getInt(3));
					pasajero.setPeso(rs.getDouble(4));

					listaPasajeros.add(pasajero);
				}
			} catch (SQLException e) {
				System.out.println("Error al obtener la lista de pasajeros");
				e.printStackTrace();
			} finally {
				closeConnection();
			}
		}
		return listaPasajeros;
	}

	@Override
	public boolean alta(int idPasajero, int idCoche) {
		boolean alta = false;
		if (openConnection()) {

			String consulta = "UPDATE pasajeros SET id_coche=? where id=?";
			try {
				PreparedStatement ps = connection.prepareStatement(consulta);				
				ps.setInt(1, idCoche);
				ps.setInt(2, idPasajero);				

				int filasAfectadas = ps.executeUpdate();
				if (filasAfectadas > 0) {
					alta = true;
				}
			} catch (SQLException e) {
				System.out.println("Error al insertar al pasajero");
				e.printStackTrace();
			} finally {
				closeConnection();
			}
		}
		return alta;
		
	}


	@Override
	public List<Pasajero> list(int idCoche) {
		List<Pasajero> listaPasajeros = new ArrayList<>();
		if (openConnection()) {

			String consulta = "select id, nombre, edad, peso from pasajeros WHERE id_coche = ?";
			try {
				PreparedStatement ps = connection.prepareStatement(consulta);
				ps.setInt(1, idCoche);
				
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					Pasajero pasajero = new Pasajero();
					pasajero.setId(rs.getInt(1));
					pasajero.setNombre(rs.getString(2));
					pasajero.setEdad(rs.getInt(3));
					pasajero.setPeso(rs.getDouble(4));				

					listaPasajeros.add(pasajero);
				}
			} catch (SQLException e) {
				System.out.println("Error al obtener la lista de pasajeros con el identificador:  " +idCoche);
				e.printStackTrace();
			} finally {
				closeConnection();
			}
		}
		return listaPasajeros;
	}

	@Override
	public boolean asignar(int idPasajero) {
		boolean borrar = false;
		if (openConnection()) {
			String query = "UPDATE pasajeros SET id_coche=NULL where id=?";
			try {
				PreparedStatement ps = connection.prepareStatement(query);				
				ps.setInt(1, idPasajero);

				int filasAfectadas = ps.executeUpdate();
				if (filasAfectadas > 0) {
					borrar = true;
				}
			} catch (SQLException e) {
				System.out.println("Error al borrar el identificador del pasajero : " + idPasajero);
				e.printStackTrace();
			} finally {
				closeConnection();
			}
		}
		return borrar;
	}

	

}

