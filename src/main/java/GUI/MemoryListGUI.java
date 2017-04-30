package GUI;

import java.util.Date;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import DAO.MemoryPartDAOImpl;
import Entityes.MemoryPart;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MemoryListGUI extends Application {
	
	private ObservableList<MemoryPart> data = FXCollections.observableArrayList();
	
	ApplicationContext ctx = new ClassPathXmlApplicationContext("ApplicationContext.xml");
	
	MemoryPartDAOImpl memoryDAO = (MemoryPartDAOImpl) ctx.getBean("memoryDAO");
	
	TableView<MemoryPart> table = new TableView();
	
	TableColumn idcol = new TableColumn("Id");
	TableColumn namecol = new TableColumn("Name");
	TableColumn desccol = new TableColumn("Description");
	TableColumn datecol = new TableColumn("Date");
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		BorderPane root = new BorderPane();
		Scene scene = new Scene(root, 600, 600);
		
		final Stage addg = new AddGUI(primaryStage);
		
		primaryStage.setTitle("Memory List");
		
		HBox buttons = new HBox();
		
		root.setBottom(buttons);
		root.setCenter(table);
		
		primaryStage.setResizable(false);
		
		makeinf();
		
		Button add = new Button("Add Record");
		Button update = new Button("Update");
		Button delete = new Button("Delete");													
		
		add.setOnAction(new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent event) {
				addg.show();
				
			}
		});
		
		table.getColumns().addAll(idcol,namecol,desccol,datecol);
		
		table.setEditable(true);
		
		buttons.setSpacing(100);
		
		buttons.getChildren().addAll(add,update,delete);
		
		primaryStage.setScene(scene);
		primaryStage.show();
		
		
		
	}
	
	private void makeinf() {
		
		List<MemoryPart> ms = memoryDAO.getAll();
		
		ObservableList<MemoryPart> data = FXCollections.observableArrayList(ms);
		
		idcol.setCellValueFactory(
				new PropertyValueFactory<MemoryPart, Integer>("id")
				);
		
		namecol.setCellValueFactory(
				new PropertyValueFactory<MemoryPart, String>("FIO")
				);
		
		desccol.setCellValueFactory(
				new PropertyValueFactory<MemoryPart, String>("event")
				);
		
		datecol.setCellValueFactory(
				new PropertyValueFactory<MemoryPart, Date>("date")
				);
		table.setItems(data);
		
	}

	public class AddGUI extends Stage {		
						
	public AddGUI(Stage parent) {
		
		super();
		initOwner(parent);
		initModality(Modality.WINDOW_MODAL);
		Button butAdd = new Button("Add");
		Button butCan = new Button("Cancel");		
		
		setResizable(false);
		
		HBox buts = new HBox(40);
		
		final BorderPane root = new BorderPane();
		
		Scene scene = new Scene(root, 300, 100);
		setScene(scene);
		setTitle("Add Record");
		GridPane grid = new GridPane();
		
		buts.getChildren().addAll(butAdd,butCan);
		
		root.setBottom(buts);
		
		root.setCenter(grid);
		
		root.setAlignment(buts, Pos.CENTER);
		
		grid.setPadding(new Insets(5));
		grid.setHgap(4);
		grid.setVgap(4);			
		
		Label titleLabel = new Label("Title: ");
		Label descLabel = new Label("Description: ");
		
		final Label errorlabel = new Label("Title and Description must be filled up!");
		final Label successLabel = new Label("Done");
		errorlabel.setTextFill(Color.RED);
		successLabel.setTextFill(Color.GREEN);
		
		final TextField titlefield = new TextField();
		final TextField descfield = new TextField();
		
		titlefield.setPromptText("Set title");
		descfield.setPromptText("Set description");
		
		grid.add(titleLabel, 0, 1);	
		grid.add(titlefield, 1, 1);	
		grid.add(descLabel, 0, 2);	
		grid.add(descfield, 1, 2);						
			
		butAdd.setOnAction(new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent event) {
				if (titlefield.getText().isEmpty() || descfield.getText().isEmpty()){
					root.setTop(errorlabel);
				}else {
					MemoryPart mem = new MemoryPart(titlefield.getText(),new Date(),descfield.getText());
					memoryDAO.addOne(mem);
					titlefield.clear();
					descfield.clear();
					makeinf();
					root.setTop(successLabel);
				}				
			}
		});	
		
		butCan.setOnAction(new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent event) {
				close();			
			}
		});			
	}				
		
	}
	
}
	

