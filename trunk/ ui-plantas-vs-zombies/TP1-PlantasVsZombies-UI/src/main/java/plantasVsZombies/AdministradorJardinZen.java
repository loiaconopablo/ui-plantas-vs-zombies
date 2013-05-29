package plantasVsZombies;

import java.util.List;

import org.uqbar.commons.model.ObservableUtils;
import org.uqbar.commons.utils.Observable;

import plantaszombies.JardinZen;
import plantaszombies.Mejora;
import plantaszombies.Partida;
import plantaszombies.Semilla;

/**
 * @author Mariano Varela, Pablo Loiacono
 * 
 */

@Observable
public class AdministradorJardinZen {

	private Semilla semillaSeleccionada;
	private JardinZen jardinZen;
	private List<Semilla> semillasSelect;
	private String jardinSelect = "Jardin Terrestre";
	private int espacioDisponible;
	private Partida partida;
	private Mejora mejoraSeleccionada;
	private String resultadoCompra;

	public AdministradorJardinZen(Partida partida) {
		this.partida = partida;
		this.jardinZen = this.partida.getJardinZen();
		this.semillasSelect = this.jardinZen.getSemillasTerrestres();
		this.espacioDisponible = this.calcularEspacioDisponible();
	}

	/**
	 * Acciones
	 */

	/**
	 * Busca y setea la semilla seleccionada
	 */
	public void buscarYSetearSemilla(String nombre) {
		if (this.esTerrestre()) {
			this.semillaSeleccionada = buscarSemilla(nombre,
					this.jardinZen.getSemillasTerrestres());
		} else {
			this.semillaSeleccionada = buscarSemilla(nombre,
					this.jardinZen.getSemillasAcuaticas());
		}
	}

	public Semilla buscarSemilla(String nombre, List<Semilla> semillas) {
		Semilla resultado = null;
		for (Semilla semilla : semillas) {
			if ((nombre.equals(semilla.getNombre()))) {
				resultado = semilla;
			}
		}
		return resultado;
	}

	public void buscarYSetearMejora(String mejoraSeleccionada) {
		for (Mejora mejora : this.jardinZen.getMejorasPredefinidas()){
			if (mejoraSeleccionada.equals(mejora.getNombre())){
				this.setMejoraSeleccionada(mejora);
				break;
			}
			
		}
	}

	public Mejora buscarMejora(String mejoraSeleccionada) {
		Mejora result = null;
		for (Mejora mejora : this.jardinZen.getMejorasPredefinidas()){
			if (mejoraSeleccionada.equals(mejora.getNombre())){
				result = mejora; break;
			}
			
		}
		return result;
	}
	
	
	/**
	 * Setea los resultados de la busqueda en la propiedad semillasSelect
	 */
	public void buscar(String nombre) {
		if (this.esTerrestre()) {
			this.setSemillasSelect(this.jardinZen.buscarEnTerrestre(nombre));
		} else {
			this.setSemillasSelect(this.jardinZen.buscarEnAcuatica(nombre));
		}
	}

	public void buscar(String nombre, String ordenadoPor) {
		if (this.esTerrestre()) {
			this.setSemillasSelect(this.jardinZen.ordenarSemillasSelectPor(
					this.jardinZen.buscarEnTerrestre(nombre), ordenadoPor));
		} else {
			this.setSemillasSelect(this.jardinZen.ordenarSemillasSelectPor(
					this.jardinZen.buscarEnAcuatica(nombre), ordenadoPor));
		}
	}

	public void irAlOtroJardin() {
		if (this.esAcuatico()) {
			this.semillasSelect = jardinZen.getSemillasTerrestres();
			this.jardinSelect = "Jardin Terrestre";
		} else {
			this.semillasSelect = jardinZen.getSemillasAcuaticas();
			this.jardinSelect = "Jardin Acuatico";
		}
		this.espacioDisponible = this.calcularEspacioDisponible();
	}

	public void comprarMejora() {
		this.partida.getJardin().descontarRecursos(
				mejoraSeleccionada.getCosto());
		this.getSemillaSeleccionada().aplicarMejora(this.mejoraSeleccionada);
		this.resultadoCompra = this.mejoraSeleccionada.getNombre();

		//ObservableUtils.forceFirePropertyChanged(this, "semillaSeleccionada",
		//		this.getSemillaSeleccionada());

		this.partida.getJardin().setRecursos(
				this.partida.getJardin().getRecursos());
		

	}

	protected int calcularEspacioDisponible() {
		return 20 - this.semillasSelect.size();
	}

	public void borrarResultadoUltimaCompra() {
		this.resultadoCompra = null;

	}

	public boolean esTerrestre() {
		return this.jardinSelect.equals("Jardin Terrestre");
	}

	public boolean esAcuatico() {
		return this.jardinSelect.equals("Jardin Acuatico");
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

	public Mejora getMejoraSeleccionada() {
		return mejoraSeleccionada;
	}

	public void setMejoraSeleccionada(Mejora mejoraSeleccionada) {
		this.mejoraSeleccionada = mejoraSeleccionada;
	}

	public String getResultadoCompra() {
		return resultadoCompra;
	}

	public void setResultadoCompra(String resultadoCompra) {
		this.resultadoCompra = resultadoCompra;
	}

}
