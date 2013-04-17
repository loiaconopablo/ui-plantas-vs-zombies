package plantasVsZombies;

import org.uqbar.arena.actions.MessageSend;
import org.uqbar.arena.aop.windows.TransactionalDialog;
import org.uqbar.arena.bindings.ObservableProperty;
import org.uqbar.arena.bindings.PropertyAdapter;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import plantaszombies.Jardin;
import plantaszombies.Semilla;
import plantaszombies.Tipo;
import plantaszombies.JardinZen;
import plantaszombies.Mejora;
import plantaszombies.Zombie;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.WindowOwner;
import org.uqbar.commons.utils.Observable;
import org.uqbar.commons.utils.Transactional;

@Transactional
@Observable
public class AdministradorJardinZen {
	
	private Semilla semillaSeleccionada;
	private JardinZen jardinZen = new JardinZen(new Jardin(2,2));
	private List<Semilla> semillasSelect = jardinZen.getSemillasAcuaticas();
	private String jardinSelect = "Jardin Acuatico";
	private int espacioDisponible = (20 - this.semillasSelect.size());
		

	

	/**
	 * Acciones
	 */
	
	public void jugar(){
		
	}
	


	public void irAlOtroJardin() {
			if (this.jardinSelect.equals("Jardin Acuatico")){
				this.semillasSelect = jardinZen.getSemillasTerrestres();
				this.jardinSelect = "Jardin Terreste";}
				else {
					this.semillasSelect = jardinZen.getSemillasAcuaticas();
					this.jardinSelect = "Jardin Acuatico";
						}
				}
					
	
	
		
	/**
	 * Getter y Setters
	 */
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
