package modelo.negocio;

import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.List;

import modelo.entidad.Coche;
import modelo.persistencia.SQL.DaoCocheMySql;

import modelo.persistencia.interfaces.DaoCoche;

public class GestorCoche {

	private DaoCoche daoCoche = new DaoCocheMySql();
	
	
	
	
	
	
	public boolean añadir (Coche c) {
		boolean alta =false;
		boolean verificado = verificarCoche(c);
		if(verificado) {
			alta = daoCoche.alta(c);
		}
		return alta;
	}
	
	public boolean borrar (int id) {
		boolean baja = false;
		boolean verificado = verificarId(id);
		if(verificado) {
			baja = daoCoche.baja(id);
		}
		return baja;
	}
	
	public boolean modificar (Coche c) {
		boolean modificado = false;
		boolean verificado = verificarCoche(c);
		if(verificado) {
			modificado = daoCoche.modificar(c);
		}
		return modificado;
		
	}
	
	public Coche obtener(int id) {
		Coche coche = new Coche();
		boolean verificado = verificarId(id);
		if(verificado) {
			coche = daoCoche.obtener(id);
		}
		return coche;
	}

	
	public List<Coche> lista() {
		List<Coche> listaCoches = daoCoche.lista();
		return listaCoches;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public boolean verificarCoche(Coche coche) {
	
		String varchar = "^\\w{1,20}$";
		
		boolean verificado = false;
				
		if (!verificarAño(coche.getAño())) {
			System.out.println("\"El año debe contener 4 números");
		} else if (!coche.getMarca().matches(varchar)) {
			System.out.println("La marca debe contener entre 1 y 20 letras y/o números");
		} else if (!coche.getModelo().matches(varchar)) {
			System.out.println("El modelo debe contener entre 1 y 20 letras y/o números");
	
		} else if (coche.getId() < 1 && coche.getId() > 9999) {
			System.out.println("El ID debe estar entre 1 y 9999");
		}else if (!verificarKilometros(coche.getKm())) {
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
	
	public boolean verificarAño(String año) {
	    
	    String patronAño = "^[0-9]{4}$";
	    if (!año.matches(patronAño)) {
	        System.out.println("El año debe contener 4 números");
	        return false;
	    }
	    return true;
	}
	

	public boolean verificarKilometros(double kilometros) {
	    // Verificar si los kilómetros están dentro de un rango aceptable 
	    double kilometrosMinimos = 0;
	    double kilometrosMaximos = 500000; // Establezco un límite de 500.000 KM
	    if (kilometros < kilometrosMinimos || kilometros > kilometrosMaximos) {
	        System.out.println("Los kilómetros deben estar entre " + kilometrosMinimos + " y " + kilometrosMaximos);
	        return false;
	    }
	    return true;
	}
	
	
	
	
	
}
