package plantasVsZombies;

import org.uqbar.arena.Application;
import org.uqbar.arena.windows.Window;

public class PlantasVsZombiesApplication extends Application {

	public static void main(String[] args) {
		new PlantasVsZombiesApplication().start();
	}
	
	@Override
	protected Window<?> createMainWindow() {
		return new InicioWindow(this);
	}

}
