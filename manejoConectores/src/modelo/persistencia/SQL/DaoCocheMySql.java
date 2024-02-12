package modelo.persistencia.SQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modelo.entidad.Coche;
import modelo.persistencia.interfaces.DaoCoche;

public class DaoCocheMySql implements DaoCoche {
	

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
	public boolean alta(Coche c) {
		// TODO Auto-generated method stub
		
		boolean alta = false;
		if(openConnection()) {
			String consulta = "insert into coches (ID,MARCA,MODELO,AÑO,KM) values(?,?,?,?,?)";
			
			try{
				PreparedStatement ps = connection.prepareStatement(consulta);
				ps.setInt(1, c.getId());
				ps.setString(2, c.getMarca());
				ps.setString(3, c.getModelo());
				ps.setString(4, c.getAño());
				ps.setDouble(5, c.getKm());
				
				int filasAfectadas = ps.executeUpdate();
				if(filasAfectadas > 0) {
					alta = true;
					
				}
		
			}catch (SQLException e) {
				System.out.println("Error al dar de alta el: " +c);
				e.printStackTrace();
			}finally {
				closeConnection();
			}
		}
		return alta;
		
		
	}

	@Override
	public boolean baja(int id) {
		boolean baja = false;
		if(openConnection()) {
			String consulta = "delete from coches where id=?";
			try {
				PreparedStatement ps = connection.prepareStatement(consulta);
				ps.setInt(1, id);
				 
				int filasAfectadas = ps.executeUpdate();
				if(filasAfectadas > 0) {
					baja = true;
				}
			} catch (SQLException e) {
				System.out.println("Error al dar de baja/eliminar: " +id);
				e.printStackTrace();
			}finally {
				closeConnection();
			}
		}
		return baja;
	}

	@Override
	public boolean modificar(Coche c) {
		
		boolean modificar = false;
		if(openConnection()) {
			String consulta = "update coches set ID = ?, MARCA = ?, MODELO = ?, AÑO = ?, KM = ?";
			try {
				
				PreparedStatement ps = connection.prepareStatement(consulta);
				ps.setInt(1, c.getId());
				ps.setString(2, c.getMarca());
				ps.setString(3, c.getModelo());
				ps.setString(4, c.getAño());
				ps.setDouble(5, c.getKm());
				
				int filasAfectadas = ps.executeUpdate();
				if(filasAfectadas > 0) {
					modificar = true;
				}
			}catch (SQLException e) {
				System.out.println("Error al modificar el : " +c);
				e.printStackTrace();
			} finally {
				closeConnection();
			}
		}
		return modificar;
	}

	@Override
	public Coche obtener(int id) {
		Coche coche = null;
		if(openConnection()) {
			
			String consulta = "select * from coches where id = ?";
			try {
				PreparedStatement ps = connection.prepareStatement(consulta);
				ps.setInt(1, id);
				
				ResultSet rs = ps.executeQuery();
				while(rs.next()) {
					coche = new Coche();
					coche.setId(rs.getInt(1));
					coche.setMarca(rs.getString(2));
					coche.setModelo(rs.getString(3));
					coche.setAño(rs.getString(4));
					coche.setKm(rs.getDouble(5));
					
				}
				} catch (SQLException e) {
					System.out.println("Error al obtener el coche con id " + id);
					e.printStackTrace();
				} finally {
					closeConnection();
				}
			}
			return coche;
		}
					
	

	@Override
	public List<Coche> lista() {
		List<Coche> listaCoches = new ArrayList<>();
		if(openConnection()) {
			String consulta = "select * from coches";
			try {
				PreparedStatement ps = connection.prepareStatement(consulta);
				ResultSet rs = ps.executeQuery();
				
				while(rs.next()) {
					Coche coche = new Coche();
					coche.setId(rs.getInt(1));
					coche.setMarca(rs.getString(2));
					coche.setModelo(rs.getString(3));
					coche.setAño(rs.getString(4));
					coche.setKm(rs.getDouble(5));
					
					listaCoches.add(coche);
				}
				}catch (SQLException e) {
					System.out.println("Error al obtener el listado de los coches");
					e.printStackTrace();
			}finally {
				closeConnection();
			}
		}
		return listaCoches;
	}

}
	
	

