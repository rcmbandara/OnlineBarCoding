package ob.app;

import java.io.IOException;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import ob.app.bean.BasicDatafromTireCodeBean;
import ob.app.view.AccessFileUpdateController;
import ob.app.view.BERController;
import ob.app.view.ButtonBarController;

import ob.app.view.FIFormatController;
import ob.app.view.SNListController;

public class MainApp extends Application {
	private Stage primaryStage;

	private BorderPane rootLayout;
	private BorderPane ChartPane;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("SUN-TWS ONLINE BARCODING SYSTEM");
		initRootLayout();
		addButtonBar();
	   //addPidChange();
	}

	public void addButtonBar() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/ButtonBar.fxml"));
			AnchorPane buttonBar = (AnchorPane) loader.load();
			rootLayout.setLeft(buttonBar);

			ButtonBarController controller = loader.getController();
			controller.setMainApp(this);
			controller.setBean();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	

	public void addAccessUpdate() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/AccessFileUpdate.fxml"));
			AnchorPane buttonBar = (AnchorPane) loader.load();
			rootLayout.setCenter(buttonBar);

			AccessFileUpdateController controller = loader.getController();
			controller.setMainApp(this);
			// controller.setBean();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void addBER(BasicDatafromTireCodeBean bean) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/BER.fxml"));
			AnchorPane buttonBar = (AnchorPane) loader.load();
			rootLayout.setCenter(buttonBar);

			BERController controller = loader.getController();

			controller.setBean(bean);
			controller.setMainApp(this);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void addSNList(ObservableList<BasicDatafromTireCodeBean> obList) {
		try {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/SNList.fxml"));
			AnchorPane buttonBar = (AnchorPane) loader.load();
			rootLayout.setCenter(buttonBar);

			SNListController controller = loader.getController();
			controller.setBean(obList);
			controller.setMainApp(this);

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void addFIFormat(BasicDatafromTireCodeBean bean) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/FIFormat.fxml"));

			AnchorPane FIFormat = (AnchorPane) loader.load();
			rootLayout.setCenter(FIFormat);

			FIFormatController controller = loader.getController();
			controller.setBean(bean);
			controller.setMainApp(this);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void initRootLayout() {
		try {
			// Load root layout from fxml file.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();

			// Show the scene containing the root layout.
			Scene scene = new Scene(rootLayout);

			for (Screen screen : Screen.getScreens()) {
				Rectangle2D bounds = screen.getVisualBounds();
				primaryStage.setX(bounds.getMinX() + 50);
				primaryStage.setY(bounds.getMinY() + 50);
			}

			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
