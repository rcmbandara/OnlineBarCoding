package ob.app.view;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.sql.rowset.Joinable;
import javax.swing.JOptionPane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import ob.app.MainApp;
import ob.app.bean.BasicDatafromTireCodeBean;
import ob.app.db.CreateConn;

import ob.app.tbl.AccessFile;
import ob.app.tbl.JoinTbl;
import ob.app.tbl.StkTbl;
import ob.app.util.SNListGetter;

public class ButtonBarController implements Initializable {

	int callStation;
	@FXML
	Button btnRefresh, btnUpdateAccessFile;
	@FXML
	Label lblNotUpdated;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

	public void setBean() {

	}

	private MainApp mainApp;

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;

	}

	@FXML
	private void RefreshList() throws ClassNotFoundException, SQLException {
		// Reload the SNList
		SNListGetter snListGetter = new SNListGetter();
		ObservableList<BasicDatafromTireCodeBean> List_oL = snListGetter.getSNList();

		mainApp.addSNList(List_oL);
	}

	@FXML
	private void accessInsert() throws SQLException {

		mainApp.addAccessUpdate();

	}

}
