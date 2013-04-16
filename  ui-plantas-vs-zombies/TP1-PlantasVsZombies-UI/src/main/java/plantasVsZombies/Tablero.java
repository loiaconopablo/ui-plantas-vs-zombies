package plantasVsZombies;

import java.io.Serializable;
import java.util.List;

import org.uqbar.commons.utils.Observable;
import org.uqbar.commons.utils.Transactional;

import plantaszombies.AlmanaqueDeZombies;
import plantaszombies.Jardin;
import plantaszombies.JardinZen;
import plantaszombies.Partida;
import plantaszombies.Planta;
import plantaszombies.Semilla;
import plantaszombies.Terreno;
import plantaszombies.TipoTerrenoAcuatico;
import plantaszombies.Zombie;


@SuppressWarnings("serial")
@Observable
@Transactional
public class Tablero implements Serializable{
	private int filasTerrestres;
	private int filasAcuaticas;
	private Jardin jardin = new Jardin (2,2);
	private int fila;
	private int columna;
	private Semilla semilla;
	private Zombie zombieAtacante;
	private int filaAAtacar;
	private Partida partida;
	private AlmanaqueDeZombies almanaque;
	
	
	
	public Tablero(){
		this.jardin.getFilas().get(0).aniadirEn(new Semilla(new TipoTerrenoAcuatico(),"Girasol",50,50), 0);
	}
	/**
	 * Accesors
	 */
	
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
		this.jardin = new Jardin(this.getFilasTerrestres(),this.getFilasAcuaticas());
		this.partida = new Partida(this.getJardin(),new JardinZen(this.getJardin(),null));//falta pasarle la lista de mejoras o ya crear una lista de mejoras por defecto en el jardin zen
	}

	public void plantar(){
		this.getJardin().getFilas().get(this.getFila()).aniadirEn(this.getSemilla(), this.getColumna());
	}
	
	public void atacar(){
		this.partida.setZombieAtacante(this.getZombieAtacante());
		this.partida.setTerrenoAAtacar(this.jardin.getFilas().get(this.filaAAtacar));
		this.partida.atacar();
	}
}
