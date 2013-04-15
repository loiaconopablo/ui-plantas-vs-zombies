package plantasVsZombies;

import java.awt.Color;

import org.uqbar.arena.actions.MessageSend;
import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;

public class InicioWindow extends SimpleWindow<Tablero> {

	public InicioWindow(WindowOwner parent) {
		super(parent, new Tablero());
	}

	@Override
	protected void addActions(Panel actionsPanel) {
		Panel panel = new Panel(actionsPanel);
		panel.setLayout(new VerticalLayout());
		
		new Label(panel).setText("Filas Terrestres").setForeground(Color.BLUE);
		new TextBox(panel).bindValueToProperty("filasTerrestres");
		
		new Label(panel).setText("Filas Acuaticas").setForeground(Color.BLUE);
		new TextBox(panel).bindValueToProperty("filasAcuaticas");
		
		Button inicio = new Button(panel);
		inicio.setCaption("Jugar");
		inicio.onClick(new MessageSend(this, "jugar"));
		
	}

	@Override
	protected void createFormPanel(Panel mainPanel) {
		Panel formPanel = new Panel(mainPanel);
		formPanel.setLayout(new VerticalLayout());

	}

	@Override
	protected void createMainTemplate(Panel mainPanel) {
		this.setTitle("Plantas Vs Zombies");
		this.setTaskDescription("Ingrese la cantidad de filas terrestres y acuaticas que desee que posea su jardin");

		super.createMainTemplate(mainPanel);

	}
	
	/**
	 * Acciones
	 */
	public void jugar(){
		this.openDialog(new TableroWindow(this, this.getModelObject()));
	}
	
	protected void openDialog(Dialog<?> dialog) {
		dialog.onAccept(new MessageSend(this.getModelObject(),"jugar"));
		dialog.open();
		
	}
}
