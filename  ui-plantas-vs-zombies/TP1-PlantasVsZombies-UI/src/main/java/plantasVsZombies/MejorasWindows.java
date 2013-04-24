package plantasVsZombies;

import java.awt.Color;

import org.uqbar.arena.actions.MessageSend;
import org.uqbar.arena.aop.windows.TransactionalDialog;
import org.uqbar.arena.bindings.ObservableProperty;
import org.uqbar.arena.bindings.PropertyAdapter;

import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.layout.HorizontalLayout;
import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.Selector;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.WindowOwner;
import org.uqbar.lacar.ui.model.ListBuilder;
import org.uqbar.lacar.ui.model.bindings.Binding;

import plantaszombies.Jardin;
import plantaszombies.Mejora;
import plantaszombies.Semilla;
import plantaszombies.Planta;
import plantaszombies.Semilla;
import plantaszombies.Terreno;
import plantaszombies.Zombie;

public class MejorasWindows extends TransactionalDialog<AdministradorJardinZen> {

	public MejorasWindows(WindowOwner parent,
			AdministradorJardinZen administradorJardinZen) {
		super(parent, administradorJardinZen);
	}

	protected void createMainTemplate(Panel mainPanel) {
		this.setTitle("Aplicar Mejoras");
		this.setTaskDescription("Aquí podras mejorar tus plantas!");
		super.createMainTemplate(mainPanel);

		Panel infoPanel = new Panel(mainPanel);
		infoPanel.setLayout(new HorizontalLayout());

		this.crearPanelDeInformacion(infoPanel);
		this.crearCuadros(mainPanel);
		this.crearResultadoCompraYBotonesFinales(mainPanel);
				
	}
	
	private void crearPanelDeInformacion(Panel infoPanel) {
		Panel panelEtiqueta = new Panel(infoPanel);
		panelEtiqueta.setLayout(new VerticalLayout());

		Panel panelInfo = new Panel(infoPanel);
		panelInfo.setLayout(new VerticalLayout());

		new Label(panelEtiqueta).setText("Estas Mejorando A:").setForeground(
				Color.BLACK);
		new Label(panelInfo).bindValueToProperty("semillaSeleccionada.nombre");
		new Label(panelEtiqueta).setText("Recursos").setForeground(Color.BLACK);
		new Label(panelInfo).bindValueToProperty("jardinZen.jardin.recursos");
	}

	private void crearCuadros(Panel infoPanel) {
		Panel panelDeMejoras = new Panel(infoPanel);
		panelDeMejoras.setLayout(new HorizontalLayout());

		Panel columnaCompradas = new Panel(panelDeMejoras);
		infoPanel.setLayout(new VerticalLayout());
		new Label(columnaCompradas).setText("Mejoras Compradas").setForeground(Color.BLACK).setFontSize(15);
		this.createResultsGrid(columnaCompradas);
		
		Panel columnaDisponibles = new Panel(panelDeMejoras);
		infoPanel.setLayout(new VerticalLayout());
		new Label(columnaDisponibles).setText("Mejoras Disponibles").setForeground(Color.BLACK).setFontSize(15);
		this.createResultsGridTwo(columnaDisponibles);
							
		}
	
	private void crearResultadoCompraYBotonesFinales(Panel mainPanel) {
		Panel resultadoCompra = new Panel(mainPanel);
		resultadoCompra.setLayout(new HorizontalLayout());

		new Label(resultadoCompra).setText("Haz Comprado una Mejora:").setForeground(Color.BLACK);
		new Label(resultadoCompra).setWidth(150).bindValueToProperty("resultadoCompra");
		
		
		Button comprar = new Button(resultadoCompra);
		comprar.setCaption("Comprar");
		comprar.onClick(new MessageSend(this.getModelObject(), "comprarMejora"));
		

		Button cerrar = new Button(resultadoCompra);
		cerrar.setCaption("Cerrar");
		cerrar.onClick(new MessageSend(this.getModelObject(), "actualizarSemillas"));//Chequear estas dos cosas que no actuliza los valores de las semillas en el cuadro 
		cerrar.onClick(new MessageSend(this.getModelObject(), "borrarResultadoUltimaCompra"));//Sigue Figurando la ultima compra.
		cerrar.onClick(new MessageSend(this, "cancel"));
			
		
	}
		
	private void createResultsGrid(Panel mainPanel) {
		Table<Mejora> table = new Table<Mejora>(mainPanel, Mejora.class);
		table.setHeigth(200);
		table.setWidth(450);

		table.bindItemsToProperty("semillaSeleccionada.mejorasAplicadas");

		this.describeResultsGrid(table);
	}
	private void createResultsGridTwo(Panel mainPanel) {
		Table<Mejora> table = new Table<Mejora>(mainPanel, Mejora.class);
		table.setHeigth(200);
		table.setWidth(450);

		table.bindItemsToProperty("jardinZen.mejorasPredefinidas");
		table.bindValueToProperty("mejoraSeleccionada");

		this.describeResultsGridTwo(table);
	}

	private void describeResultsGridTwo(Table<Mejora> table) {
		new Column<Mejora>(table).setTitle("Mejora").setFixedSize(200)
				.bindContentsToProperty("nombre");
	
		Column<Mejora> defenseColumn = new Column<Mejora>(table);
		defenseColumn.setTitle("Costo");
		defenseColumn.setFixedSize(50);
		defenseColumn.bindContentsToProperty("costo");

	}
	private void describeResultsGrid(Table<Mejora> table) {
		new Column<Mejora>(table).setTitle("Mejora").setFixedSize(200)
				.bindContentsToProperty("nombre");
		}	
	
	@Override
	protected void createFormPanel(Panel mainPanel) {

		Panel searchFormPanel = new Panel(mainPanel);
		searchFormPanel.setLayout(new ColumnLayout(2));

		Panel actionsPanel = new Panel(mainPanel);
		actionsPanel.setLayout(new VerticalLayout());
		this.primeraFila(actionsPanel);
		this.segundaFila(actionsPanel);

	}

	private void primeraFila(Panel actionsPanel) {
		Panel panelFilaUno = new Panel(actionsPanel);
		panelFilaUno.setLayout(new HorizontalLayout());
	}

	private void segundaFila(Panel actionsPanel) {
		Panel panelFilaDos = new Panel(actionsPanel);
		panelFilaDos.setLayout(new HorizontalLayout());
		;
	}

}