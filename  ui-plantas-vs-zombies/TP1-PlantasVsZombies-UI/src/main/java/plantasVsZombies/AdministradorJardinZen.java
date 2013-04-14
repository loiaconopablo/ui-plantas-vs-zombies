package plantasVsZombies;

import java.util.LinkedList;
import java.util.List;
import plantaszombies.Jardin;
import plantaszombies.Tipo;
import plantaszombies.JardinZen;
import plantaszombies.Mejora;
import plantaszombies.Zombie;

import org.uqbar.commons.utils.Observable;
@Observable
public class AdministradorJardinZen {
	
	private String nombre;
	private Zombie zombieSeleccionado;
	private Jardin jardinmomentao = new Jardin (2,2);
	
	private List<Mejora> listaDeMejoras = new LinkedList<Mejora>().add();
	private Mejora mejora1 = new Mejora("Mejora1",20, Tipo.DEFENSIVA, 20);
	private Mejora mejora2 = new Mejora("Mejora2",20, Tipo.OFENSIVA, 20);
	private Mejora mejora3 = new Mejora("Mejora3",30, Tipo.DEFENSIVA, 30);
	private Mejora mejora4 = new Mejora("Mejora4",30, Tipo.OFENSIVA, 30);
	private Mejora mejora5 = new Mejora("Mejora5",50, Tipo.DEFENSIVA, 50);
	
//	listaDeMejoras.add(mejora1).add(mejora2).add(mejora3).add(mejora4).add(mejora5); // No se porque no toma el add.?¡
	private JardinZen jardinZen = new JardinZen(jardin, mejoras);
	

}
