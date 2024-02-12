package modelo.persistencia.interfaces;

import java.util.List;

import modelo.entidad.Coche;



public interface DaoCoche {
	
	public boolean alta (Coche c);
	public boolean baja(int id);
	public boolean modificar (Coche c);
	Coche obtener (int id);
	List <Coche> lista();

}
