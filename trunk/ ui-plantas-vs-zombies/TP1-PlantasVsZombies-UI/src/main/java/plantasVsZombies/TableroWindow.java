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
import plantaszombies.Planta;
import plantaszombies.Semilla;
import plantaszombies.Terreno;
import plantaszombies.Zombie;

/**
 * @author Mariano Varela, Pablo Loiacono
 * 
 */
public class TableroWindow extends TransactionalDialog<Tablero> {

	public TableroWindow(WindowOwner parent, Tablero tablero) {
		super(parent, tablero);
		this.getModelObject().jugar();
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

		Panel panelDeTitulo = new Panel(mainPanel);
		panelDeTitulo.setLayout(new HorizontalLayout());
		
		Panel panelDeSiembra = new Panel(mainPanel);
		panelDeSiembra.setLayout(new VerticalLayout());

		new Label(panelDeTitulo).setText("Plantas").setForeground(Color.BLUE);
		new Label(panelDeTitulo).setText("Elige una planta y selecciona una posicion").setForeground(Color.BLACK).setFontSize(8);
		
		this.crearGrillaDeSiembra(panelDeSiembra);

	}

	protected void createGridActions(Panel mainPanel) {
		Panel actionsPanel = new Panel(mainPanel);
		actionsPanel.setLayout(new HorizontalLayout());

		Button zen = new Button(actionsPanel);
		zen.setCaption("Ir al Jardin Zen");
		zen.onClick(new MessageSend(this.getModelObject(), "setZombieAtacanteParaAtaqueYJardinZen"));
		zen.onClick(new MessageSend(this, "irAlJardinZen"));
		
		Button almanaque = new Button(actionsPanel);
		almanaque.setCaption("Almanaque de zombies");
		almanaque.onClick(new MessageSend(this, "almanaqueDeZombies"));

		Button restart = new Button(actionsPanel);
		restart.setCaption("Reiniciar");
		restart.onClick(new MessageSend(this.getModelObject(), "jugar"));
		
		new Label(actionsPanel).setText("Recursos").setForeground(Color.BLACK).setFontSize(10);
		new Label(actionsPanel).bindValueToProperty("jardin.recursos");

	}

	@Override
	protected void createFormPanel(Panel mainPanel) {
		Panel searchFormPanel = new Panel(mainPanel);
		searchFormPanel.setLayout(new ColumnLayout(2));
	}
	
	private void crearPanelDeZombie(Panel jardinPanel) {
		Panel panelZombie = new Panel(jardinPanel);
		panelZombie.setLayout(new VerticalLayout());

		Panel panelTitulo = new Panel(panelZombie);
		panelTitulo.setLayout(new HorizontalLayout());

		new Label(panelTitulo).setText("ZOMBIS").setForeground(Color.RED);
		new Label(panelTitulo).setText("Elige el zombie y la fila")
				.setForeground(Color.BLACK).setFontSize(10);

		new Label(panelZombie).setText("Zombie: ").setForeground(Color.BLACK);
		Selector<Zombie> selector = new Selector<Zombie>(panelZombie) //
				.allowNull(false);
		selector.bindValueToProperty("zombieAtacante");

		Binding<ListBuilder<Zombie>> itemsBinding = selector.bindItems( //
				new ObservableProperty(this.getModelObject(), "almanaque"));

		itemsBinding.setAdapter( //
				new PropertyAdapter(Zombie.class, "nombre"));

		new Label(panelZombie).setText("Fila:").setForeground(Color.GREEN);
		new TextBox(panelZombie).bindValueToProperty("filaAAtacar");

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
		
		Binding<ListBuilder<Semilla>> itemsBinding = selector.bindItemsToProperty("partida.jardinZen.semillas");

		itemsBinding.setAdapter( //
				new PropertyAdapter(Semilla.class, "nombre"));

		Button plantar = new Button(panel);
		plantar.setCaption("Plantar");
		plantar.onClick(new MessageSend(this.getModelObject(), "plantar"));

		this.crearLogs(panel);
	}

	private void createResultsGrid(Panel mainPanel) {
		Table<Terreno> table = new Table<Terreno>(mainPanel, Terreno.class);
		table.setHeigth(200);
		table.setWidth(500);

		table.bindItemsToProperty("jardin.filas");

		this.describeResultsGrid(table);
	}

	protected void describeResultsGrid(Table<Terreno> table) {
		new Column<Terreno>(table).setTitle("Tipo").setFixedSize(100)
				.bindContentsToProperty("tipoTerrenoToString");
		new Column<Terreno>(table).setTitle("0").setFixedSize(100)
				.bindContentsToProperty("primero");
		new Column<Terreno>(table).setTitle("1").setFixedSize(100)
				.bindContentsToProperty("segundo");
		new Column<Terreno>(table).setTitle("2").setFixedSize(100)
				.bindContentsToProperty("tercero");
		new Column<Terreno>(table).setTitle("3").setFixedSize(100)
				.bindContentsToProperty("cuarto");
		new Column<Terreno>(table).setTitle("4").setFixedSize(100)
				.bindContentsToProperty("quinto");

	}

	private void crearLogs(Panel panel) {
		
		Table<Jardin> table = new Table<Jardin>(panel, Jardin.class);
		table.setHeigth(200);
		table.setWidth(400);
		
		table.bindItemsToProperty("jardin.logs");
		}

	/**
	 * Acciones
	 */

	public void almanaqueDeZombies() {
		this.openDialog(new AlmanaqueZombieWindow(this));
	}

	public void irAlJardinZen() {
		this.openDialog(new JardinZenWindow(this,this.getModelObject().getPartida()));
		}
	
	protected void openDialog(Dialog<?> dialog) {
		dialog.onAccept(new MessageSend(this.getModelObject(), "search"));
		dialog.open();
	}

}
