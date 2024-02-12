package modelo.negocio;

import java.util.List;

import modelo.entidad.Coche;
import modelo.entidad.Pasajero;
import modelo.persistencia.interfaces.DaoPasajero;
import modelo.persistencia.SQL.DaoPasajeroMySql;


public class GestorPasajero {
	
	private DaoPasajero daoPasajero = new DaoPasajeroMySql();
	
	public boolean añadir (Pasajero p) {
		boolean alta =false;
		boolean verificado = verificarPasajero(p);
		if(verificado) {
			alta = daoPasajero.alta(p);
		}
		return alta;
	}
	
	public boolean añadir(int idPasajero, int idCoche) {
		boolean alta = false;
		boolean verificadoPasajero = verificarId(idPasajero);
		boolean verificadoCoche = verificarId(idCoche);
		
		if (verificadoPasajero && verificadoCoche) {
			alta = daoPasajero.alta(idPasajero, idCoche);
		}
		return alta;			
	}
	
	
	
	public boolean borrar (int id) {
		boolean baja = false;
		boolean verificado = verificarId(id);
		if(verificado) {
			baja = daoPasajero.baja(id);
		}
		return baja;
	}
	
	public boolean desasignar(int idPasajero) {
		boolean isBaja = false;
		boolean isVerificadoPasajero = verificarId(idPasajero);
		if (isVerificadoPasajero) {
			isBaja = daoPasajero.asignar(idPasajero);
		}
		return isBaja;		
	}
	
	public boolean modificar (Pasajero p) {
		boolean modificado = false;
		boolean verificado = verificarPasajero(p);
		if(verificado) {
			modificado = daoPasajero.modificar(p);
		}
		return modificado;
		
	}
	

	
	
	
	public Pasajero obtener(int id) {
		Pasajero pasajero = new Pasajero();
		boolean isVerificado = verificarId(id);
		if (isVerificado) {
			pasajero = daoPasajero.obtener(id);
		}
		return pasajero;
		
	}

	

	
	public List<Pasajero> listar() {
		List<Pasajero> listaPasajeros = daoPasajero.listar();
		return listaPasajeros;
	}
	
	public List<Pasajero> listar(int idCoche) {
		List<Pasajero> listaPasajeros = daoPasajero.list(idCoche);
		return listaPasajeros;
	}
	
	
public boolean verificarPasajero(Pasajero pasajero) {
		
		String patronVarchar = "^\\w{1,20}$";
		boolean verificado = false;
		if (!pasajero.getNombre().matches(patronVarchar)) {
			System.out.println("El nombre debe ser una cadena de texto");
		} else if (!(pasajero.getEdad() < 0) && pasajero.getEdad() > 999) {
			System.out.println("La edad debe ser un número entero positivo de máximo 3 cifras");
		} else if (!(pasajero.getPeso() < 0) && pasajero.getPeso() > 999 ) {
			System.out.println("El peso debe ser una cantidad positiva con un máximo de 5 cifras incluyendo decimales");		
		} else if (pasajero.getId() < 1 && pasajero.getId() > 9999) {
			System.out.println("El ID debe estar entre 1 y 9999");
		} else {
			verificado = true;
		}
		return verificado;
	}

public boolean verificarId(int id) {
	boolean verificado = false;
	if (id < 1 && id > 9999) {
		System.out.println("El ID debe estar entre 1 y 9999");
	} else {
		verificado = true;
	}
	return verificado;
}

}
