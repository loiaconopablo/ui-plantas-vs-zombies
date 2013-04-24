package plantasVsZombies;

import java.awt.Color;

import org.uqbar.arena.actions.MessageSend;
import org.uqbar.arena.bindings.NotNullObservable;
import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.layout.HorizontalLayout;
import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.WindowOwner;

import plantaszombies.Partida;
import plantaszombies.Semilla;

/**
 * @author Mariano Varela, Pablo Loiacono
 * 
 */

public class JardinZenWindow extends Dialog<AdministradorJardinZen> {
	private static final long serialVersionUID = 1L;

	public JardinZenWindow(WindowOwner owner, Partida partida ) {
		super(owner, new AdministradorJardinZen(partida));
//		this.getModelObject().borrarResultadoUltimaCompra();
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
		
		this.createPanelUltimoBotones(mainPanel);
		
	}
	
	@Override
	protected void createFormPanel(Panel mainPanel) {
		Panel searchFormPanel = new Panel(mainPanel);
		searchFormPanel.setLayout(new ColumnLayout(2));
						
	}
	
	private void crearPanelDeInformacion(Panel infoPanel) {
		Panel panelEtiqueta = new Panel(infoPanel);
		panelEtiqueta.setLayout(new VerticalLayout());

		Panel panelInfo = new Panel(infoPanel);
		panelInfo.setLayout(new VerticalLayout());
		
			new Label(panelEtiqueta).setText("Jardin Zen Seleccionado").setForeground(Color.RED).setFontSize(12);
			new Label(panelInfo).bindValueToProperty("jardinSelect");	
	
			new Label(panelInfo).bindValueToProperty("espacioDisponible");
			new Label(panelEtiqueta).setText("Lugares Disponibles:").setForeground(Color.BLUE).setFontSize(12);	
		
		}
		
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
		
		Column<Semilla> ataqueColumn = new Column<Semilla>(table);
		ataqueColumn.setTitle("Puntos de Danio");
		ataqueColumn.setFixedSize(150);
		ataqueColumn.bindContentsToProperty("puntosDeDanio");
		
		}
	
	private void createPanelUltimoBotones(Panel mainPanel) {
		Panel mejorarPanel = horizontal(mainPanel);
				
		new Label(mejorarPanel).setText("Seleccionado").setForeground(Color.BLACK).setFontSize(12);
		new Label(mejorarPanel).setWidth(100).bindValueToProperty("semillaSeleccionada.nombre");
			
		Button plantar = new Button(mejorarPanel);
		plantar.setCaption("Mejorar");
		plantar.onClick(new MessageSend(this.getModelObject(), "borrarResultadoUltimaCompra"));
		plantar.onClick(new MessageSend(this, "irAMejorarPlantas"));
		
		Panel botonesFinales = new Panel(mainPanel);
		botonesFinales.setLayout(new HorizontalLayout());
				
		new Button(botonesFinales)
			.setCaption("Aceptar")
			.onClick(new MessageSend(this, "cancel"))
			.setAsDefault();
		
		Button irAlOtroJardin = new Button(botonesFinales);
		irAlOtroJardin.setCaption("Ir al Siguiente Jardin");
		irAlOtroJardin.onClick(new MessageSend(this.getModelObject(), "irAlOtroJardin"));
		
		// Deshabilitar los botones si no hay ningun elemento seleccionado en la grilla.
		NotNullObservable elementSelected = new NotNullObservable("semillaSeleccionada");
		plantar.bindEnabled(elementSelected);
		
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
	
	
	public Panel horizontal(Panel parent){
		Panel panel = new Panel(parent);
		panel.setLayout(new HorizontalLayout());
		return panel;
	}
	
	public Panel vertical(Panel parent){
		Panel panel = new Panel(parent);
		panel.setLayout(new VerticalLayout());
		return panel;
	}
	

}
