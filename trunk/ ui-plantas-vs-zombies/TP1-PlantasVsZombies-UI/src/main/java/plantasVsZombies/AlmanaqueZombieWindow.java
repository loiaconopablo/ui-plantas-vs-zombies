package plantasVsZombies;

import java.awt.Color;

import org.uqbar.arena.actions.MessageSend;
import org.uqbar.arena.aop.windows.TransactionalDialog;
import org.uqbar.arena.bindings.NotNullObservable;
import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.layout.HorizontalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.Window;
import org.uqbar.arena.windows.WindowOwner;

import plantaszombies.Zombie;


public class AlmanaqueZombieWindow extends TransactionalDialog<AlmanaqueZombie> {

	public AlmanaqueZombieWindow(WindowOwner parent) {
		super(parent,new AlmanaqueZombie());
		
	}

	@Override
	protected void addActions(Panel panel) {
		new Button(panel)
		.setCaption("Buscar")
		.onClick(new MessageSend(this.getModelObject(), "buscar"));
		//.setAsDefault()
		//.disableOnError();
	}
	
	@Override
	protected void createMainTemplate(Panel mainPanel) {
		this.setTitle("AlmanaqueZombie");
		this.setTaskDescription("Inserte el nombre del zombie a buscar");

		super.createMainTemplate(mainPanel);

		//this.createResultsGrid(mainPanel);
	}
	

	private void createResultsGrid(Panel mainPanel) {
		Table<Zombie> table = new Table<Zombie>(mainPanel, Zombie.class);
		table.setHeigth(200);
		table.setWidth(450);

		table.bindItemsToProperty("resultados");
		table.bindValueToProperty("celularSeleccionado");

		this.describeResultsGrid(table);		
	}
	
	protected void describeResultsGrid(Table<Zombie> table) {
		new Column<Zombie>(table) //
			.setTitle("Nombre")
			.setFixedSize(150)
			.bindContentsToProperty("nombre");

	}

	@Override
	protected void createFormPanel(Panel mainPanel) {
		Panel searchFormPanel = new Panel(mainPanel);
		searchFormPanel.setLayout(new ColumnLayout(2));

	}

}
