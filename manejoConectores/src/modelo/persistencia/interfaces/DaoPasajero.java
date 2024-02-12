package modelo.persistencia.interfaces;

import java.util.List;

import modelo.entidad.Pasajero;

public interface DaoPasajero {
	
	public boolean alta (Pasajero p);
	public boolean alta(int idPasajero, int idCoche);
	public boolean baja(int id);
	public boolean modificar (Pasajero p);
	Pasajero obtener (int id);
	List <Pasajero> listar();
	
	List<Pasajero> list(int idCoche);
	
	boolean asignar(int idPasajero);

}
