package ob.app.view;

import java.net.URL;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.util.Callback;
import ob.app.MainApp;
import ob.app.bean.BasicDatafromTireCodeBean;
import ob.app.bean.FIReportBean;
import ob.app.db.CreateConn;
import ob.app.tbl.JoinTbl;
import ob.app.tbl.StkTbl;

public class SNListController implements Initializable {

	public SNListController() {

	}

	private MainApp mainApp;
	////////////////
	@FXML
	private TextField filterField;
	private ObservableList<BasicDatafromTireCodeBean> filteredData = FXCollections.observableArrayList();
	ObservableList<BasicDatafromTireCodeBean> List_oL = FXCollections.observableArrayList();

	@FXML
	private TableView<BasicDatafromTireCodeBean> tblTireData;

	@FXML
	private TableColumn<BasicDatafromTireCodeBean, Number> snCol;
	@FXML
	private TableColumn<BasicDatafromTireCodeBean, String> rimSizeCol;
	@FXML
	private TableColumn<BasicDatafromTireCodeBean, String> trireSizeCol;
	@FXML
	private TableColumn<BasicDatafromTireCodeBean, Number> pidCol;
	@FXML
	private TableColumn<BasicDatafromTireCodeBean, String> LugTypeCol;
	@FXML
	private TableColumn<BasicDatafromTireCodeBean, String> ConfigCol;
	@FXML
	private TableColumn<BasicDatafromTireCodeBean, String> tireTypeCol;
	@FXML
	private TableColumn<BasicDatafromTireCodeBean, String> swMsgCol;
	@FXML
	private TableColumn<BasicDatafromTireCodeBean, String> brandCol;
	@FXML
	private TableColumn<BasicDatafromTireCodeBean, Number> tireCodeCol;
	@FXML
	private TableColumn<BasicDatafromTireCodeBean, Date> mfdDateCol;
	@FXML
	private TableColumn<BasicDatafromTireCodeBean, String> moldNoCol;



	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

	public void setBean(ObservableList<BasicDatafromTireCodeBean> obList) {
		// Get data from refresh Button
		this.List_oL = obList;

		// Initially add all data to filtered data
		filteredData.addAll(List_oL);
		tblTireData.setItems(filteredData);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		snCol.setCellValueFactory(cellData -> cellData.getValue().snProperty());
		trireSizeCol.setCellValueFactory(cellData -> cellData.getValue().sizebasicProperty());
		rimSizeCol.setCellValueFactory(cellData -> cellData.getValue().rimsizeProperty());

		pidCol.setCellValueFactory(cellData -> cellData.getValue().pidProperty());
		LugTypeCol.setCellValueFactory(cellData -> cellData.getValue().lugtypeProperty());
		ConfigCol.setCellValueFactory(cellData -> cellData.getValue().configProperty());
		tireTypeCol.setCellValueFactory(cellData -> cellData.getValue().tiretypeProperty());
		swMsgCol.setCellValueFactory(cellData -> cellData.getValue().swmsgProperty());
		brandCol.setCellValueFactory(cellData -> cellData.getValue().brandProperty());
		tireCodeCol.setCellValueFactory(cellData -> cellData.getValue().tireCodeProperty());

		brandCol.setCellValueFactory(cellData -> cellData.getValue().brandProperty());
		mfdDateCol.setCellValueFactory(cellData -> cellData.getValue().birthdayProperty());
		moldNoCol.setCellValueFactory(cellData -> cellData.getValue().moldNoProperty());

		// Bold and increase the font size of SN Column
		snCol.setStyle("-fx-font-size:15px;-fx-font-weight:bold; ");

		////////////////////
		// When select a Tire shows FIFormat.fxml
		tblTireData.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			System.out.println("ListView Selection Changed (selected: " + newValue.getSizebasic() + ")");
			mainApp.addFIFormat(newValue);
			mainApp.addButtonBar();
		});

		//////////////////////////////////////////////////////////////////

		// Listen for text changes in the filter text field
		filterField.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observables, String oldValues, String newValues) {
				// Filter
				filteredData.clear();

				for (BasicDatafromTireCodeBean p : List_oL) {
					if (matchesFilter(p)) {
						filteredData.add(p);
					}
				}

				// Must re-sort table after items changed
				// Filter
				// ArrayList<TableColumn<BasicDatafromTireCodeBean, ?>> sortOrder = new
				// ArrayList<>(tblTireData.getSortOrder());
				// tblTireData.getSortOrder().clear();
				// tblTireData.getSortOrder().addAll(sortOrder);
			}
		});
	}

	/**
	 * Updates the filteredData to contain all data from the masterData that matches
	 * the current filter.
	 */
	private void updateFilteredData() {

	}

	/**
	 * Returns true if the person matches the current filter. Lower/Upper case is
	 * ignored.
	 * 
	 * @param person
	 * @return
	 */
	private boolean matchesFilter(BasicDatafromTireCodeBean bean) {
		// Filter
		String filterString = filterField.getText();
		String x = Integer.toString(bean.getSn());
		if (filterString == null || filterString.isEmpty()) {
			// No filter --> Add all.
			return true;
		}

		String lowerCaseFilterString = filterString.toLowerCase();

		if (x.toLowerCase().indexOf(lowerCaseFilterString) != -1) {
			return true;
		}

		return false; // Does not match
	}

	private void reapplyTableSortOrder() {

	}

}
