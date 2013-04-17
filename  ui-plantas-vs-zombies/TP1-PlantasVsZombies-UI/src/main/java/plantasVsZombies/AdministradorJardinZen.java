package plantasVsZombies;

import java.util.List;

import org.uqbar.commons.utils.Observable;
import org.uqbar.commons.utils.Transactional;

import plantaszombies.Jardin;
import plantaszombies.JardinZen;
import plantaszombies.Partida;
import plantaszombies.Semilla;

@Transactional
@Observable
public class AdministradorJardinZen {
	
	private Semilla semillaSeleccionada;
	private JardinZen jardinZen; //= new JardinZen(new Jardin(2,2));
	private List<Semilla> semillasSelect;// = jardinZen.getSemillasAcuaticas();
	private String jardinSelect = "Jardin Acuatico";
	private int espacioDisponible; // = (20 - this.semillasSelect.size());
	private Partida partida;
	
		

	

	public AdministradorJardinZen(Partida partida) {
		this.partida = partida;
		this.jardinZen = this.partida.getJardinZen();
		this.semillasSelect= this.jardinZen.getSemillasAcuaticas();
		this.espacioDisponible= (20 - this.semillasSelect.size());
	}



	/**
	 * Acciones
	 */
	
	public void jugar(){
		
	}
	


	public void irAlOtroJardin() {
			if (this.jardinSelect.equals("Jardin Acuatico")){
				this.semillasSelect = jardinZen.getSemillasTerrestres();
				this.jardinSelect = "Jardin Terreste";
				}
			if (this.jardinSelect.equals("Jardin Terrestre")){
					this.semillasSelect = jardinZen.getSemillasAcuaticas();
					this.jardinSelect = "Jardin Acuatico";
				}
			
	}
					
	
	
		
	/**
	 * Getter y Setters
	 */
	
	public Partida getPartida() {
		return partida;
	}
	public void setPartida(Partida partida) {
		this.partida = partida;
	}
	public Semilla getSemillaSeleccionada() {
		return semillaSeleccionada;
	}
	public void setSemillaSeleccionada(Semilla semillaSeleccionada) {
		this.semillaSeleccionada = semillaSeleccionada;
	}
	public JardinZen getJardinZen() {
		return jardinZen;
	}
	public void setJardinZen(JardinZen jardinZen) {
		this.jardinZen = jardinZen;
	}
		
	public List<Semilla> getSemillasSelect() {
		return semillasSelect;
	}

	public void setSemillasSelect(List<Semilla> semillasSelect) {
		this.semillasSelect = semillasSelect;
	}

	public String getJardinSelect() {
		return jardinSelect;
	}

	public void setJardinSelect(String jardinSelect) {
		this.jardinSelect = jardinSelect;
	}

	public int getEspacioDisponible() {
		return espacioDisponible;
	}

	public void setEspacioDisponible(int espacioDisponible) {
		this.espacioDisponible = espacioDisponible;
	}
	
	
	
	
}
