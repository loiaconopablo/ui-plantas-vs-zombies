package plantasVsZombies;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.uqbar.commons.model.ObservableUtils;
import org.uqbar.commons.utils.Observable;

import plantaszombies.AlmanaqueDeZombies;
import plantaszombies.Jardin;
import plantaszombies.JardinZen;
import plantaszombies.Partida;
import plantaszombies.Semilla;
import plantaszombies.Terreno;
import plantaszombies.Zombie;


@SuppressWarnings("serial")
@Observable
public class Tablero implements Serializable{
	private int filasTerrestres;
	private int filasAcuaticas;
	private Jardin jardin;
	private int fila;
	private int columna;
	private Semilla semilla;
	private Zombie zombieAtacante;
	private int filaAAtacar;
	private Partida partida;
	private AlmanaqueDeZombies almanaque = new AlmanaqueDeZombies();
	
	public Tablero(){
		
	}
	
	/**
	 * Accesors
	 */
	public List<String> getLogs() {
		return this.jardin.getLogs();
	}
	
	public Zombie getZombieAtacante() {
		return zombieAtacante;
	}

	public void setZombieAtacante(Zombie zombieAtacante) {
		this.zombieAtacante = zombieAtacante;
	}
	
	public List<Zombie> getAlmanaque() {
		return almanaque.getZombies();
	}
	
	public void setAlmanaque(AlmanaqueDeZombies almanaque) {
		this.almanaque = almanaque;
	}
	
	public int getFilaAAtacar() {
		return filaAAtacar;
	}

	public void setFilaAAtacar(int filaAAtacar) {
		this.filaAAtacar = filaAAtacar;
	}	

	public Semilla getSemilla() {
		return semilla;
	}

	public void setSemilla(Semilla semilla) {
		this.semilla = semilla;
	}

	public int getFila() {
		return fila;
	}

	public void setFila(int fila) {
		this.fila = fila;
	}

	public int getColumna() {
		return columna;
	}

	public void setColumna(int columna) {
		this.columna = columna;
	}

	public int getFilasTerrestres() {
		return filasTerrestres;
	}

	public void setFilasTerrestres(int filasTerrestres) {
		this.filasTerrestres = filasTerrestres;
	}

	public int getFilasAcuaticas() {
		return filasAcuaticas;
	}

	public void setFilasAcuaticas(int filasAcuraticas) {
		this.filasAcuaticas = filasAcuraticas;
	}

	public int getRecursos() {
		return this.jardin.getRecursos();
	}

	public void setRecursos(int recursos) {
		this.jardin.setRecursos(recursos);
	}

	public Jardin getJardin() {
		return jardin;
	}

	public void setJardin(Jardin jardin) {
		this.jardin = jardin;
	}
	
	public List<Terreno> getFilas(){
		return this.jardin.getFilas();
	}

	public void setFilas(List<Terreno> terrenos){
		this.jardin.setFilas(terrenos);
	}
	
	
	public Partida getPartida() {
		return partida;
	}

	public void setPartida(Partida partida) {
		this.partida = partida;
	}
	
	/**
	 * Acciones
	 */
	
	
	public void jugar(){
		this.jardin = new Jardin(this.getFilasAcuaticas(),this.getFilasTerrestres());
		this.partida = new Partida(this.getJardin(),new JardinZen(this.getJardin()));
		this.actualizaGrilla();
	}

	private void actualizaGrilla() {
		for (Terreno terreno : this.getJardin().getFilas()) {
			ObservableUtils.forceFirePropertyChanged(terreno, "primero", terreno.getPrimero());
			ObservableUtils.forceFirePropertyChanged(terreno, "segundo", terreno.getPrimero());
			ObservableUtils.forceFirePropertyChanged(terreno, "tercero", terreno.getPrimero());
			ObservableUtils.forceFirePropertyChanged(terreno, "cuarto", terreno.getPrimero());
			ObservableUtils.forceFirePropertyChanged(terreno, "quinto", terreno.getPrimero());
		}
		
	}

	public void plantar(){
		this.getJardin().plantar(this.getFila(),this.getColumna(), this.getSemilla());
		ObservableUtils.forceFirePropertyChanged(this, "logs", this.jardin.getLogs());
		}
	
	public void atacar(){
		this.setZombieAtacanteParaAtaqueYJardinZen();
		this.partida.setTerrenoAAtacar(this.jardin.getFilas().get(this.filaAAtacar));
		this.partida.atacar();
		this.actualizaGrilla();
			
	}
	
	public void setZombieAtacanteParaAtaqueYJardinZen(){
		this.partida.setZombieAtacante(this.getZombieAtacante());
		ObservableUtils.forceFirePropertyChanged(partida, "zombieAtacante", partida.getZombieAtacante());
		ObservableUtils.forceFirePropertyChanged(this, "partida", this.getPartida());
		}
	
	
	
	
	
}
