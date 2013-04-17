package plantasVsZombies;

import java.awt.Color;

import org.uqbar.arena.actions.MessageSend;
import org.uqbar.arena.aop.windows.TransactionalDialog;
import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.layout.HorizontalLayout;
import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.WindowOwner;

import plantaszombies.Partida;
import plantaszombies.Semilla;
import plantaszombies.Zombie;

public class JardinZenWindow extends TransactionalDialog<AdministradorJardinZen> {

	public JardinZenWindow(WindowOwner owner, Partida partida ) {
		super(owner, new AdministradorJardinZen(partida));
		
	}

	
	@Override
	protected void createMainTemplate(Panel mainPanel) {
		this.setTitle("JardinZen");
		this.setTaskDescription("Estas son las semillas que tiene disponible");

		super.createMainTemplate(mainPanel);
			
		Panel infoPanel = new Panel(mainPanel);
		infoPanel.setLayout(new HorizontalLayout());

		this.crearPanelDeInformacion(infoPanel);
		
		Panel panelDeSemillas = new Panel(mainPanel);
		panelDeSemillas.setLayout(new VerticalLayout());
		
		this.createResultsGrid(panelDeSemillas);
				
		Panel mejorarPanel = new Panel(mainPanel);
		mejorarPanel.setLayout(new HorizontalLayout());
		
		new Label(panelDeSemillas).setText("Seleccionado").setForeground(Color.BLACK);
		new Label(panelDeSemillas).bindValueToProperty("semillaSeleccionada.nombre");
		
		Button plantar = new Button(mejorarPanel);
		plantar.setCaption("Mejorar");
		plantar.onClick(new MessageSend(this, "irAMejorarPlantas"));
		
		Button irAlOtroJardin = new Button(mejorarPanel);
		irAlOtroJardin.setCaption("Ir al Siguiente Jardin");
		irAlOtroJardin.onClick(new MessageSend(this.getModelObject(), "irAlOtroJardin"));
		
	}
	private void crearPanelDeInformacion(Panel infoPanel) {
		Panel panelEtiqueta = new Panel(infoPanel);
		panelEtiqueta.setLayout(new VerticalLayout());

		Panel panelInfo = new Panel(infoPanel);
		panelInfo.setLayout(new VerticalLayout());
		
			new Label(panelEtiqueta).setText("Zombie").setForeground(Color.BLACK);
			new Label(panelInfo).setText("Nombre del Zombie").setForeground(Color.BLACK);
		
			new Label(panelEtiqueta).setText("Jardin Zen Seleccionado").setForeground(Color.RED);
			new Label(panelInfo).bindValueToProperty("jardinSelect");	
	
			new Label(panelInfo).bindValueToProperty("espacioDisponible");
			new Label(panelEtiqueta).setText("Lugares Disponibles:").setForeground(Color.BLACK);	
		
		}
			
			


	@Override
	protected void createFormPanel(Panel mainPanel) {
		Panel searchFormPanel = new Panel(mainPanel);
		searchFormPanel.setLayout(new ColumnLayout(2));
						
	}

	
		//this.createColumnaLabel(actionsPanel);
			

//	private void createColumnaLabel(Panel actionsPanel) {
//		Panel panelColumnaLabel = new Panel(actionsPanel);
//		panelColumnaLabel.setLayout(new HorizontalLayout());
//		new Label(actionsPanel).setText("Zombie").setForeground(Color.BLACK);
//		//new Label(actionsPanel).bindValueToProperty("zombieAtacante.nombre");
//		new Label(actionsPanel).setText("Jardin Zen Seleccionado").setForeground(Color.RED);
//				new Label(actionsPanel).bindValueToProperty("jardinSelect");
//		new Label(actionsPanel).setText("Lugares Disponibles:").setForeground(Color.BLACK);
//		new Label(actionsPanel).bindValueToProperty("espacioDisponible");
//						
//	}

	


	private void createResultsGrid(Panel mainPanel) {
		Table<Semilla> table = new Table<Semilla>(mainPanel, Semilla.class);
		table.setHeigth(200);
		table.setWidth(450);

		table.bindItemsToProperty("semillasSelect");
		table.bindValueToProperty("semillaSeleccionada");

		this.describeResultsGrid(table);
		
	}

	protected void describeResultsGrid(Table<Semilla> table) {
		new Column<Semilla>(table).setTitle("Nombre").setFixedSize(150)
				.bindContentsToProperty("nombre");


		Column<Semilla> defenseColumn = new Column<Semilla>(table);
		defenseColumn.setTitle("CapacidadDefensiva");
		defenseColumn.setFixedSize(150);
		defenseColumn.bindContentsToProperty("capacidadDefensiva");
		
		}
	
	/*
	 * Acciones
	 */
	public void irAMejorarPlantas() {
		this.openDialog(new MejorasWindows(this, this.getModelObject()));
	}
	
	
	protected void openDialog(Dialog<?> dialog) {
		dialog.onAccept(new MessageSend(this.getModelObject(), "search"));
		dialog.open();
		}
	
	

}
