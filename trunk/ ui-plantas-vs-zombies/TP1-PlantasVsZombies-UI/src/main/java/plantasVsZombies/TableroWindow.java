package plantasVsZombies;


import org.uqbar.arena.actions.MessageSend;

import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.layout.HorizontalLayout;
import org.uqbar.arena.widgets.Button;
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

		Button edit = new Button(actionsPanel);
		edit.setCaption("Almanaque de zombies");
		edit.onClick(new MessageSend(this, "almanaqueDeZombies"));

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

	protected void openDialog(Dialog<?> dialog) {
		dialog.onAccept(new MessageSend(this.getModelObject(), "search"));
		dialog.open();
		
	}

}
