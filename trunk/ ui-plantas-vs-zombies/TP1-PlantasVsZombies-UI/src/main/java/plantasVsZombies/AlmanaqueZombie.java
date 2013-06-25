package plantasVsZombies;

import java.util.List;

import org.uqbar.commons.utils.Observable;

import plantaszombies.AlmanaqueDeZombies;
import plantaszombies.Zombie;

/**
 * @author Mariano Varela, Pablo Loiacono
 * 
 */
@Observable
public class AlmanaqueZombie {
	
	private String nombre;
	private Zombie zombieSeleccionado;
	private List<Zombie> resultados;
	private AlmanaqueDeZombies almanaqueDeZombies = new AlmanaqueDeZombies();
	
	/**
	 * Getters y setters
	 */

	public Zombie getZombieSeleccionado() {
				return zombieSeleccionado;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setZombieSeleccionado(Zombie zombieSeleccionado) {
		this.zombieSeleccionado = zombieSeleccionado;
	}
	public List<Zombie> getResultados() {
		return resultados;
	}
	public void setResultados(List<Zombie> resultado) {
		this.resultados = resultado;
	}
	
	public String nombreZombieSeleccionado(){
		if (this.zombieSeleccionado == null)			
			return "No hay seleccionado ningun zombie, eliga uno.";
		else
			return this.zombieSeleccionado.getNombre();
	}
	
	public int ataqueZombieSeleccionado(){
		if (this.zombieSeleccionado == null)			
			return 0;
		else
			return this.zombieSeleccionado.getAtaque();
	}
		

	
	
	/**
	 * Acciones
	 */
	
	public void clear(){
		this.nombre = "";
		this.buscar();
	}
	public void buscar(){
		this.resultados = this.almanaqueDeZombies.search(this.nombre);
	}
	

	

}
