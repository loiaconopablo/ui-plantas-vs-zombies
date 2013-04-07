package plantasVsZombies;

import java.io.Serializable;

import org.uqbar.commons.utils.Observable;

import plantaszombies.Jardin;


@SuppressWarnings("serial")
@Observable
public class Tablero implements Serializable{
	
	private Jardin jardin;

	public Jardin getJardin() {
		return jardin;
	}

	public void setJardin(Jardin jardin) {
		this.jardin = jardin;
	}
	
}
