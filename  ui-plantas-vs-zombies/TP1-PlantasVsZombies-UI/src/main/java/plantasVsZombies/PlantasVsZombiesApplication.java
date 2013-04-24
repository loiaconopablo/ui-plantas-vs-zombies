package plantasVsZombies;

import org.uqbar.arena.Application;
import org.uqbar.arena.windows.Window;

/**
 * @author Mariano Varela, Pablo Loiacono
 * 
 */
public class PlantasVsZombiesApplication extends Application {

	public static void main(String[] args) {
		new PlantasVsZombiesApplication().start();
	}
	
	@Override
	protected Window<?> createMainWindow() {
		return new InicioWindow(this);
	}

}
