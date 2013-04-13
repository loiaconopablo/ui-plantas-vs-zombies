package plantasVsZombies;

import java.awt.Color;

import org.uqbar.arena.actions.MessageSend;
import org.uqbar.arena.aop.windows.TransactionalDialog;

import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.layout.HorizontalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.WindowOwner;

import plantaszombies.Planta;
import plantaszombies.Terreno;
import plantaszombies.Zombie;

public class TableroWindow extends TransactionalDialog<Tablero> {

	public TableroWindow(WindowOwner parent, Tablero tablero) {
		super(parent, tablero);
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

		Panel actionsPanel = new Panel(mainPanel);
		actionsPanel.setLayout(new HorizontalLayout());

		super.createMainTemplate(mainPanel);

		this.createGridActions(mainPanel);

		Panel jardinPanel = new Panel(mainPanel);
		jardinPanel.setLayout(new HorizontalLayout());

		this.createResultsGrid(jardinPanel);
	}

	private void createResultsGrid(Panel mainPanel) {
		Table<Planta> table = new Table<Planta>(mainPanel, Planta.class);
		table.setHeigth(200);
		table.setWidth(450);

		table.bindItemsToProperty("filas");

		this.describeResultsGrid(table);
	}

	protected void describeResultsGrid(Table<Planta> table) {

		for (Terreno terreno : this.getModelObject().getFilas()) {
			new Column<Planta>(table).setTitle("0").setFixedSize(150)
					.bindContentsToProperty("nombre");
			new Column<Planta>(table).setTitle("1").setFixedSize(150)
					.bindContentsToProperty("nombre");
			new Column<Planta>(table).setTitle("2").setFixedSize(150)
					.bindContentsToProperty("nombre");
			new Column<Planta>(table).setTitle("3").setFixedSize(150)
					.bindContentsToProperty("nombre");
			new Column<Planta>(table).setTitle("4").setFixedSize(150)
					.bindContentsToProperty("nombre");
		}

	}

	protected void createGridActions(Panel mainPanel) {
		Panel actionsPanel = new Panel(mainPanel);
		actionsPanel.setLayout(new HorizontalLayout());

		Button zen = new Button(actionsPanel);
		zen.setCaption("Ir al Jardin Zen");
		zen.onClick(new MessageSend(this, "irAlJardinZen"));

		Button almanaque = new Button(actionsPanel);
		almanaque.setCaption("Almanaque de zombies");
		almanaque.onClick(new MessageSend(this, "almanaqueDeZombies"));

		new Label(actionsPanel).setText("Recursos").setForeground(Color.BLACK);
		new Label(actionsPanel).bindValueToProperty("jardin.recursos");
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
