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
		
		new Button(panel)
		.setCaption("Limpiar")
		.onClick(new MessageSend(this.getModelObject(), "clear"));
		
	}
	
	@Override
	protected void createMainTemplate(Panel mainPanel) {
		this.setTitle("AlmanaqueZombie");
		this.setTaskDescription("Inserte el nombre del zombie a buscar");

		super.createMainTemplate(mainPanel);

		this.createResultsGrid(mainPanel);
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
		new Column<Zombie>(table) 
			.setTitle("Nombre")
			.setFixedSize(150)
			.bindContentsToProperty("nombre");
		
	Column<Zombie> modeloColumn = new Column<Zombie>(table);
	modeloColumn.setTitle("Ataque");
	modeloColumn.setFixedSize(150);
	modeloColumn.bindContentsToProperty("ataque");

				

	}

	@Override
	protected void createFormPanel(Panel mainPanel) {
		Panel searchFormPanel = new Panel(mainPanel);
		searchFormPanel.setLayout(new ColumnLayout(2));
		
		new Label(searchFormPanel).setText("Nombre del zombie").setForeground(Color.BLUE);
		new TextBox(searchFormPanel).bindValueToProperty("nombre");

	}

}
