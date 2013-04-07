package plantasVsZombies;

import java.awt.Color;

import org.uqbar.arena.aop.windows.TransactionalDialog;
import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.Window;
import org.uqbar.arena.windows.WindowOwner;


public class AlmanaqueZombieWindow extends TransactionalDialog<AlmanaqueZombie> {

//	public AlmanaqueZombieWindow(WindowOwner parent, AlmanaqueZombie model) {
	public AlmanaqueZombieWindow(WindowOwner parent) {
		super(parent,new AlmanaqueZombie());
		//this.getModelObject().search();
		
	}

	@Override
	protected void addActions(Panel arg0) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	protected void createMainTemplate(Panel mainPanel) {
		this.setTitle("AlmanaqueZombie");
		this.setTaskDescription("Inserte el nombre del zombie a buscar");

		super.createMainTemplate(mainPanel);

		//this.createResultsGrid(mainPanel);
		//this.createGridActions(mainPanel);
	}
	
	@Override
	protected void createFormPanel(Panel mainPanel) {
		Panel searchFormPanel = new Panel(mainPanel);
		searchFormPanel.setLayout(new ColumnLayout(2));

		//new Label(searchFormPanel).setText("Nombre").setForeground(Color.BLUE);
		//new TextBox(searchFormPanel).bindValueToProperty("nombre");

		
	}



}
