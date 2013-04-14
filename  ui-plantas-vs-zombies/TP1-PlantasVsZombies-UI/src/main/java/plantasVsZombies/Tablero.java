package plantasVsZombies;

import java.io.Serializable;
import java.util.List;

import org.uqbar.commons.utils.Observable;

import plantaszombies.Jardin;
import plantaszombies.Planta;
import plantaszombies.Terreno;
import plantaszombies.TipoTerrenoAcuatico;


@SuppressWarnings("serial")
@Observable
public class Tablero implements Serializable{
	private int filasTerrestres;
	private int filasAcuaticas;
	private Jardin jardin = new Jardin (2,2);
	
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
	
	/**
	 * Acciones
	 */
	
	public void jugar(){
//		Jardin jardin = new Jardin(this.getFilasTerrestres(),this.getFilasAcuaticas());
//		jardin.getFilas().get(1).getPlantas().add(0, new Planta(50,50,"planta", new Terreno(new TipoTerrenoAcuatico())));
		this.setJardin(new Jardin(this.getFilasTerrestres(),this.getFilasAcuaticas()));
	}
}
