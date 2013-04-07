package plantasVsZombies;

import java.util.List;

import plantaszombies.AlmanaqueDeZombies;
import plantaszombies.Zombie;

public class AlmanaqueZombie {
	
	private String nombre;
	private Zombie zombieSeleccionado;
	private Zombie resultado;
	private AlmanaqueDeZombies almanaqueDeZombies;
	
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
	public Zombie getResultados() {
		return resultado;
	}
	public void setResultados(Zombie resultado) {
		this.resultado = resultado;
	}
	
	public void buscar(){
		this.resultado = this.almanaqueDeZombies.buscar(this.nombre);
	}

}
