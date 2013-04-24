package plantasVsZombies;

import java.awt.Color;

import org.uqbar.arena.actions.MessageSend;
import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.WindowOwner;

import plantaszombies.Zombie;

/**
 * @author Mariano Varela, Pablo Loiacono
 * 
 */
public class AlmanaqueZombieWindow extends Dialog<AlmanaqueZombie> {

	public AlmanaqueZombieWindow(WindowOwner parent) {
		super(parent, new AlmanaqueZombie());
		this.getModelObject().buscar();
	}

	@Override
	protected void addActions(Panel panel) {
		new Button(panel).setCaption("Buscar").onClick(
				new MessageSend(this.getModelObject(), "buscar"));
		// .setAsDefault()
		// .disableOnError();

		new Button(panel).setCaption("Limpiar").onClick(
				new MessageSend(this.getModelObject(), "clear"));

	}

	@Override
	protected void createMainTemplate(Panel mainPanel) {
		this.setTitle("AlmanaqueZombie");
		this.setTaskDescription("Inserte el nombre del zombie a buscar");

		super.createMainTemplate(mainPanel);

		this.createResultsGrid(mainPanel);
		
	}
	@Override
	protected void createFormPanel(Panel mainPanel) {
		Panel searchFormPanel = new Panel(mainPanel);
		searchFormPanel.setLayout(new VerticalLayout());
		
		new Label(searchFormPanel).bindValueToProperty("zombieSeleccionado.nombre");

		new Label(searchFormPanel).setText("Nombre del zombie").setForeground(
				Color.BLUE);
		new TextBox(searchFormPanel).bindValueToProperty("nombre");

	}
	private void createResultsGrid(Panel mainPanel) {
		Table<Zombie> table = new Table<Zombie>(mainPanel, Zombie.class);
		table.setHeigth(200);
		table.setWidth(450);

		table.bindItemsToProperty("resultados");
		table.bindValueToProperty("zombieSeleccionado");

		this.describeResultsGrid(table);
	}

	protected void describeResultsGrid(Table<Zombie> table) {
		new Column<Zombie>(table).setTitle("Nombre").setFixedSize(150)
				.bindContentsToProperty("nombre");

		Column<Zombie> attackColumn = new Column<Zombie>(table);
		attackColumn.setTitle("Ataque");
		attackColumn.setFixedSize(150);
		attackColumn.bindContentsToProperty("ataque");

		Column<Zombie> defenseColumn = new Column<Zombie>(table);
		defenseColumn.setTitle("Resistencia");
		defenseColumn.setFixedSize(150);
		defenseColumn.bindContentsToProperty("resistenciaInicial");
		
	}

	

}
