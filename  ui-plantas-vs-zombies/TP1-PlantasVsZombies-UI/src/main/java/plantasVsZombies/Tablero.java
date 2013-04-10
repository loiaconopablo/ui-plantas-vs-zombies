package plantasVsZombies;

import java.io.Serializable;

import org.uqbar.commons.utils.Observable;

import plantaszombies.Jardin;


@SuppressWarnings("serial")
@Observable
public class Tablero implements Serializable{
	private Jardin jardin = new Jardin (2,2);

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
	
}
