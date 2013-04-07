package plantasVsZombies;


import java.awt.Color;

import org.uqbar.arena.actions.MessageSend;

import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.layout.HorizontalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;


public class TableroWindow extends SimpleWindow<Tablero> {

	public TableroWindow(WindowOwner parent) {
		super(parent, new Tablero());
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void addActions(Panel actionsPanel) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	protected void createMainTemplate(Panel mainPanel) {
		this.setTitle("Plantas Vs Zombies");
		this.setTaskDescription("Seleccione una accion y empiece a jugar!!");

		super.createMainTemplate(mainPanel);

		this.createGridActions(mainPanel);
	}
	
	protected void createGridActions(Panel mainPanel) {
		Panel actionsPanel = new Panel(mainPanel);
		actionsPanel.setLayout(new HorizontalLayout());

		Button zen = new Button(actionsPanel);
		zen.setCaption("Ir al Jardin Zen");
		zen.onClick(new MessageSend(this, "irAlJardinZen"));
		
		Button edit = new Button(actionsPanel);
		edit.setCaption("Almanaque de zombies");
		edit.onClick(new MessageSend(this, "almanaqueDeZombies"));
		
		new Label(actionsPanel).setText("Estado").setForeground(Color.BLACK);
	}

	@Override
	protected void createFormPanel(Panel mainPanel) {
		Panel searchFormPanel = new Panel(mainPanel);
		searchFormPanel.setLayout(new ColumnLayout(2));

	}
	
	/**
	 * Acciones
	 */
	
	public void almanaqueDeZombies() {
		this.openDialog(new AlmanaqueZombieWindow(this));
	}

	public void irAlJardinZen() {
		this.openDialog(new JardinZenWindow(this));
	}
	
	protected void openDialog(Dialog<?> dialog) {
		dialog.onAccept(new MessageSend(this.getModelObject(), "search"));
		dialog.open();
		
	}

}
