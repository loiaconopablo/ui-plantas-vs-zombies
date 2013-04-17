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
		this.setTaskDescription("Estas son las mejoas que tienes disponible");

		super.createMainTemplate(mainPanel);

		Panel infoPanel = new Panel(mainPanel);
		infoPanel.setLayout(new HorizontalLayout());

		this.crearPanelDeInformacion(infoPanel);

		Panel panelDeMejoras = new Panel(mainPanel);
		panelDeMejoras.setLayout(new HorizontalLayout());

		this.createResultsGrid(panelDeMejoras);
		this.createResultsGridTwo(panelDeMejoras);

		Panel resultadoCompra = new Panel(mainPanel);
		resultadoCompra.setLayout(new HorizontalLayout());

		this.crearPanelDeResultadoCompra(resultadoCompra);

		Button comprar = new Button(resultadoCompra);
		comprar.setCaption("Comprar");
		comprar.onClick(new MessageSend(this, "irAMejorarPlantas"));

		Button cerrar = new Button(resultadoCompra);
		cerrar.setCaption("Ir al Siguiente Jardin");
		cerrar.onClick(new MessageSend(this.getModelObject(), "irAlOtroJardin"));

	}

	private void crearPanelDeResultadoCompra(Panel resultadoCompra) {
		new Label(resultadoCompra).setText("Aca va a ir el resultado de la compra").setForeground(Color.BLACK);
		
	}

	private void createResultsGrid(Panel panelDeMejoras) {
		Table<Mejora> table = new Table<Mejora>(panelDeMejoras, Mejora.class);
		table.setHeigth(200);
		table.setWidth(450);

		table.bindItemsToProperty("semillaSeleccionada.mejorasAplicadas");
		// table.bindValueToProperty("semillaSeleccionada");

		this.describeResultsGrid(table);

	}

	private void createResultsGridTwo(Panel panelDeMejoras) {
		Table<Mejora> table = new Table<Mejora>(panelDeMejoras, Mejora.class);
		table.setHeigth(200);
		table.setWidth(450);

		table.bindItemsToProperty("jardinZen.getMejorasPredefinidas");
		// table.bindValueToProperty("semillaSeleccionada");

		this.describeResultsGridTwo(table);

	}

	private void describeResultsGridTwo(Table<Mejora> table) {
		new Column<Mejora>(table).setTitle("Nombre").setFixedSize(150)
				.bindContentsToProperty("nombre");

		Column<Mejora> namekColumn = new Column<Mejora>(table);
		namekColumn.setTitle("Nombre");
		namekColumn.setFixedSize(150);
		namekColumn.bindContentsToProperty("nombre");

		Column<Mejora> defenseColumn = new Column<Mejora>(table);
		defenseColumn.setTitle("Costo");
		defenseColumn.setFixedSize(150);
		defenseColumn.bindContentsToProperty("costo");

	}
	private void describeResultsGrid(Table<Mejora> table) {
		new Column<Mejora>(table).setTitle("Nombre").setFixedSize(150)
				.bindContentsToProperty("nombre");

		Column<Mejora> namekColumn = new Column<Mejora>(table);
		namekColumn.setTitle("Nombre");
		namekColumn.setFixedSize(150);
		namekColumn.bindContentsToProperty("nombre");


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
		new Label(panelInfo).setText("EJ 100").setForeground(Color.BLACK);

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