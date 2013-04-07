package plantasVsZombies;

import org.uqbar.arena.actions.MessageSend;
import org.uqbar.arena.aop.windows.TransactionalDialog;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.WindowOwner;

public class JardinZenWindow extends TransactionalDialog<AdministradorJardinZen> {

	public JardinZenWindow(WindowOwner owner) {
		super(owner, new AdministradorJardinZen());
	}

	
	@Override
	protected void createMainTemplate(Panel mainPanel) {
		this.setTitle("JardinZen");
		this.setTaskDescription("Estas son las semillas que tiene disponible");

		super.createMainTemplate(mainPanel);


	}
	
	@Override
	protected void createFormPanel(Panel mainPanel) {
		
	}
	
	

}
