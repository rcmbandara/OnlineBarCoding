package ob.app.view;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;
import ob.app.MainApp;
import ob.app.bean.BasicDatafromTireCodeBean;
import ob.app.bean.DefectBean;
import ob.app.bean.PidFxBean;
import ob.app.db.CreateConn;
import ob.app.tbl.JoinTbl;
import ob.app.tbl.SizeBasicTbl;

public class PIDChangeController implements Initializable {
	@FXML
	TableView<PidFxBean> tblPid;
	@FXML
	TableColumn<PidFxBean, Number> clmnPid;
	@FXML
	TableColumn<PidFxBean, String> clmnSb;
	@FXML
	TableColumn<PidFxBean, String> clmnLt;
	@FXML
	TableColumn<PidFxBean, String> clmnCon;
	@FXML
	TableColumn<PidFxBean, String> clmnRs;
	@FXML
	TableColumn<PidFxBean, String> clmnTt;
	@FXML
	TableColumn<PidFxBean, String> clmnBr;
	@FXML
	TableColumn<PidFxBean, String> clmnSw;
	@FXML
	TextField txtFFSB, txtFFLT, txtFFRS, txtFFCon, txtFFTT, txtFFBR, txtFFSWMSG;

	private MainApp mainApp;
	ObservableList<PidFxBean> filteredData = FXCollections.observableArrayList();
	ObservableList<PidFxBean> masterData = FXCollections.observableArrayList();

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		clmnPid.setCellValueFactory(cellData -> cellData.getValue().pidProperty());
		clmnSb.setCellValueFactory(cellData -> cellData.getValue().sbProperty());
		clmnCon.setCellValueFactory(cellData -> cellData.getValue().conProperty());
		clmnLt.setCellValueFactory(cellData -> cellData.getValue().ltProperty());
		clmnRs.setCellValueFactory(cellData -> cellData.getValue().rsProperty());
		clmnTt.setCellValueFactory(cellData -> cellData.getValue().ttProperty());
		clmnBr.setCellValueFactory(cellData -> cellData.getValue().brProperty());
		clmnSw.setCellValueFactory(cellData -> cellData.getValue().swmsgProperty());
		// Create mastser data(frimPID Tbl)
		pidOListCreator();

		// Add filtered data to the table
		tblPid.setItems(filteredData);
		addTextChangLitnerstoTxtFileds();

		
		
	}

	private void addTextChangLitnerstoTxtFileds() {
		txtFFSB.textProperty().addListener((observable, oldValue, newSB) -> {
			filteredData.clear();
			for (PidFxBean p : masterData) {
				if (matchesFilter(p)) {
					filteredData.add(p);
				}
			}
		});

		txtFFLT.textProperty().addListener((observable, oldValue, newSB) -> {
			filteredData.clear();
			for (PidFxBean p : masterData) {
				if (matchesFilter(p)) {
					filteredData.add(p);
				}
			}
		});

		txtFFCon.textProperty().addListener((observable, oldValue, newSB) -> {
			filteredData.clear();
			for (PidFxBean p : masterData) {
				if (matchesFilter(p)) {
					filteredData.add(p);
				}
			}
		});
		txtFFRS.textProperty().addListener((observable, oldValue, newSB) -> {
			filteredData.clear();
			for (PidFxBean p : masterData) {
				if (matchesFilter(p)) {
					filteredData.add(p);
				}
			}
		});
		txtFFTT.textProperty().addListener((observable, oldValue, newSB) -> {
			filteredData.clear();
			for (PidFxBean p : masterData) {
				if (matchesFilter(p)) {
					filteredData.add(p);
				}
			}
		});
		txtFFBR.textProperty().addListener((observable, oldValue, newSB) -> {
			filteredData.clear();
			for (PidFxBean p : masterData) {
				if (matchesFilter(p)) {
					filteredData.add(p);
				}
			}
		});
		txtFFSWMSG.textProperty().addListener((observable, oldValue, newSB) -> {
			filteredData.clear();
			for (PidFxBean p : masterData) {
				if (matchesFilter(p)) {
					filteredData.add(p);
				}
			}
		});

	}

	private boolean matchesFilter(PidFxBean bean) {
		String refValue = "";

		String refsb = (bean.getSb()).toLowerCase();
		String reflt = (bean.getLt()).toLowerCase();
		String refcon = (bean.getCon().toLowerCase());
		String refrs = (bean.getRs()).toLowerCase();
		String reftt = (bean.getTt()).toLowerCase();
		String refbr = (bean.getBr()).toLowerCase();
		String refswmsg = (bean.getSwmsg()).toLowerCase();

		String txtValsb = txtFFSB.getText().toLowerCase();
		String txtVallt = txtFFLT.getText().toLowerCase();
		String txtValcon = txtFFCon.getText().toLowerCase();
		String txtValrs = txtFFRS.getText().toLowerCase();
		String txtValtt = txtFFTT.getText().toLowerCase();
		String txtValbr = txtFFBR.getText().toLowerCase();
		String txtValswmsg = txtFFSWMSG.getText().toLowerCase();

		/*
		 * if (newText == null || newText.isEmpty()) { // No filter --> Add all. return
		 * true; }
		 */

		// String lowerCaseNewText = newText.toLowerCase();
		if ((refsb.indexOf(txtValsb) != -1) && (reflt.indexOf(txtVallt) != -1) && (refcon.indexOf(txtValcon) != -1)
				&& (refrs.indexOf(txtValrs) != -1) && (reftt.indexOf(txtValtt) != -1) && (refbr.indexOf(txtValbr) != -1)
				&& (refswmsg.indexOf(txtValswmsg) != -1)) {
			return true;
		}

		return false; // Does not match
	}

	@FXML
	private void pidOListCreator() {
		JoinTbl jtbl = new JoinTbl();
		CreateConn createConn = new CreateConn();
		try (Connection conn = createConn.GetConn()) {

			masterData = jtbl.getAllPIDs(conn);

			filteredData.addAll(masterData);
		} catch (SQLException | ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "PIDChangeController.Initialize  E1 :- " + e);
		}

	}

	private boolean matchesFiltersb(PidFxBean bean, String newText) {
		String sb = (bean.getSb());

		String lowerCaseNewText = newText.toLowerCase();

		if (sb.toLowerCase().indexOf(lowerCaseNewText) != -1) {
			return true;
		}
		return false;// Does not match

	}

	private void reapplyTableSortOrder() {

		// Must re-sort table after items changed
		// Filter
		ArrayList<TableColumn<PidFxBean, ?>> sortOrder = new ArrayList<>(tblPid.getSortOrder());
		tblPid.getSortOrder().clear();
		tblPid.getSortOrder().addAll(sortOrder);
	}

}
