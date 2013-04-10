package plantasVsZombies;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


import plantaszombies.AlmanaqueDeZombies;
import plantaszombies.Zombie;

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
	
	/**
	 * Acciones
	 */
	
	public void clear(){
		this.nombre = "";
	}
	public void buscar(){
		this.resultados = this.almanaqueDeZombies.search(this.nombre);
	}
	

	

}
