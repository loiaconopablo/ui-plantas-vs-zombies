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


import plantaszombies.Planta;
import plantaszombies.Semilla;
import plantaszombies.Terreno;
import plantaszombies.Zombie;

public class TableroWindow extends TransactionalDialog<Tablero> {

	public TableroWindow(WindowOwner parent, Tablero tablero) {
		super(parent, tablero);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void addActions(Panel actionsPanel) {

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
		
		this.crearPanelDeZombie(jardinPanel);
		
		Panel panelDeSiembra = new Panel(mainPanel);
		panelDeSiembra.setLayout(new VerticalLayout());
		
		new Label(panelDeSiembra).setText("Plantas").setForeground(Color.BLUE);
		
		this.crearGrillaDeSiembra(panelDeSiembra);
		
	}

	private void crearPanelDeZombie(Panel jardinPanel) {
		Panel panelZombie = new Panel(jardinPanel);
		panelZombie.setLayout(new VerticalLayout());
		
		Panel panelTitulo = new Panel(panelZombie);
		panelTitulo.setLayout(new HorizontalLayout());
		
		new Label(panelTitulo).setText("ZOMBIS").setForeground(Color.RED);
		new Label(panelTitulo).setText("Elige el zombie y la fila").setForeground(Color.BLACK).setFontSize(10);
		
		new Label(panelZombie).setText("Zombie: ").setForeground(Color.BLACK);
		Selector<Zombie> selector = new Selector<Zombie>(panelZombie) //
				.allowNull(false);
			selector.bindValueToProperty("zombieAtacante");
		
		new Label(panelZombie).setText("Fila:").setForeground(Color.GREEN);
		new TextBox(panelZombie).setFontSize(5).bindValueToProperty("filaAAtacar");
	
		Button atacar = new Button(panelZombie);
		atacar.setCaption("Atacar");
		atacar.onClick(new MessageSend(this.getModelObject(), "atacar"));
	}

	private void crearGrillaDeSiembra(Panel panelDeSiembra) {
		Panel panel = new Panel(panelDeSiembra);
		panel.setLayout(new HorizontalLayout());
		
		new Label(panel).setText("Planta:").setForeground(Color.GREEN);
		Selector<Semilla> selector = new Selector<Semilla>(panel) //
				.allowNull(false);
			selector.bindValueToProperty("semilla");

		
		new Label(panel).setText("Fila:").setForeground(Color.GREEN);
		new TextBox(panel).bindValueToProperty("fila");
		
		new Label(panel).setText("Columna:").setForeground(Color.GREEN);
		new TextBox(panel).bindValueToProperty("columna");
		
//			Binding<ListBuilder<Planta>> itemsBinding = selector.bindItems( //
//				new ObservableProperty(RepositorioModelos.getInstance(), "modelos"));
//
//			itemsBinding.setAdapter( //
//				new PropertyAdapter(Planta.class, "descripcionEntera"));

		Button plantar = new Button(panel);
		plantar.setCaption("Plantar");
		plantar.onClick(new MessageSend(this.getModelObject(), "plantar"));
	}

	private void createResultsGrid(Panel mainPanel) {
		Table<Planta> table = new Table<Planta>(mainPanel, Planta.class);
		table.setHeigth(200);
		table.setWidth(450);

		table.bindItemsToProperty("filas");

		//this.describeResultsGrid(table);
	}

	protected void describeResultsGrid(Table<Planta> table) {

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
