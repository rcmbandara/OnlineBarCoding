package ob.app.view;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.util.StringConverter;
import ob.app.MainApp;
import ob.app.bean.AccessBean;
import ob.app.bean.BasicDatafromTireCodeBean;
import ob.app.bean.DefectBean;
import ob.app.bean.FIReportBean;
import ob.app.db.CreateConn;
import ob.app.tbl.AccessFile;
import ob.app.tbl.DefectsTbl;
import ob.app.tbl.FIReportTbl;
import ob.app.tbl.StkTbl;
import ob.app.util.AccessBeanCreator;
import ob.app.util.SNListGetter;

public class BERController implements Initializable {
	@FXML
	Label lblTireSize, lblDefect;
	@FXML
	ComboBox<DefectBean> cmbDefect;
	@FXML
	Button btnBGrade;
	@FXML
	Button btnRGrade;
	@FXML
	Button btnEGrade;
	@FXML
	Button btnLGrade;

	DefectBean defectBean = new DefectBean();
	BasicDatafromTireCodeBean bean = new BasicDatafromTireCodeBean();
	FIReportBean fiReportBean = new FIReportBean();

	private MainApp mainApp;

	ObservableList<DefectBean> defectList = FXCollections.observableArrayList();

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

	public void setBean(BasicDatafromTireCodeBean bean) {
		this.bean = bean;
		// Display tire details
		String tireSize = bean.getSizebasic() + " " + bean.getLugtype() + " " + bean.getConfig() + " "
				+ bean.getRimsize() + "     " + bean.getTiretype();
		String tireType = bean.getTiretype();
		String bNameSWMsg = bean.getBrand() + "   " + bean.getSwmsg();
		// Assign values to lables
		lblTireSize.setText(tireSize + "  " + bNameSWMsg);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		lblDefect.setText("");
		hideBtns();
		// Load combobox
		CreateConn createConn = new CreateConn();
		try (Connection conn = createConn.GetConn()) {
			// Get Defects form database
			DefectsTbl def = new DefectsTbl();
			defectList = def.getDefects(conn);
			// Load defects to combo box
			cmbDefect.setItems(defectList);
			// Redner items in combobox
			cmbDefect.setCellFactory((comboBox) -> {
				return new ListCell<DefectBean>() {
					@Override
					protected void updateItem(DefectBean item, boolean empty) {
						super.updateItem(item, empty);

						if (item == null || empty) {
							setText(null);
						} else {
							setText(item.getDefect());
						}
					}
				};
			});
			// render selected item in combo box
			cmbDefect.setConverter(new StringConverter<DefectBean>() {
				@Override
				public String toString(DefectBean defect) {
					if (defect == null) {
						return null;
					} else {
						return defect.getDefect();
					}
				}

				@Override
				public DefectBean fromString(String personString) {
					return null; // No conversion fromString needed.
				}
			});

			// Handle ComboBox event.
			cmbDefect.setOnAction((event) -> {
				DefectBean selectedDefect = cmbDefect.getSelectionModel().getSelectedItem();
				System.out.println(selectedDefect.getDefect());
				defectBean.setDefectID(selectedDefect.getDefectID());

				// Update defect Lable
				updateDefectLable();
				showBtns();
			});

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ob.app.view.BERController combobox loading E1 :-" + e);
		}

	}

	@FXML
	private void insertFIReport(String qg) {
		// Insert data in to FIReport 'PGS' Table
		fiReportBean.setSn(bean.getSn());
		fiReportBean.setPid(bean.getPid());
		fiReportBean.setTirecode(bean.getTireCode());
		fiReportBean.setMoldno(bean.getMoldNo());
		fiReportBean.setQg(qg);
		fiReportBean.setDefectid(defectBean.getDefectID());

		CreateConn createConn = new CreateConn();
		try (Connection conn = createConn.GetConn()) {
			conn.setAutoCommit(false);
			FIReportTbl fiReportTbl = new FIReportTbl();
			StkTbl stkTbl = new StkTbl();
			boolean blnInsertedDefectTbl = fiReportTbl.InsertFIReportTbl(conn, fiReportBean);
			boolean blnUpdatedStkTbl = stkTbl.updateStkTbl(conn, fiReportBean, qg);
		
			if (blnInsertedDefectTbl && blnUpdatedStkTbl ) {
				// Go again SNlist
				// Reload the SNList
				conn.commit();

				SNListGetter snListGetter = new SNListGetter();
				ObservableList<BasicDatafromTireCodeBean> List_oL = snListGetter.getSNList();

				mainApp.addSNList(List_oL);

			} else {
				conn.rollback();
			
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "BERController.insertFIReport E1" + e, null, 0);
		}

	}

	
	
	private void updateDefectLable() {
		CreateConn createConn = new CreateConn();
		try (Connection conn = createConn.GetConn()) {
			DefectsTbl defect = new DefectsTbl();
			String defectName = defect.getDefectfrmID(conn, defectBean.getDefectID());
			lblDefect.setText(defectName);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ob.app.view.BERController combobox loading E2 :-" + e);
		}

	}

	//////////////////////////////////////
	// Quality Grade Buttons
	@FXML
	private void bGradeBtn() {
		insertFIReport("B");
	}

	@FXML
	private void eGradeBtn() {
		insertFIReport("E");
	}

	@FXML
	private void lGradeBtn() {
		insertFIReport("L");
	}

	@FXML
	private void rGradeBtn() {
		insertFIReport("R");
	}

	/////////////////////////////////////////////////
	// Defect Buttons
	@FXML
	private void fmBtn() {
		defectBean.setDefectID(1);
		updateDefectLable();
		showBtns();
	}

	@FXML
	private void bwDamageBtn() {
		defectBean.setDefectID(2);
		updateDefectLable();
		showBtns();
	}

	@FXML
	private void bDamageBtn() {
		defectBean.setDefectID(3);
		updateDefectLable();
		showBtns();
	}

	@FXML
	private void bCometoTrBtn() {
		defectBean.setDefectID(4);
		updateDefectLable();
		showBtns();
	}

	@FXML
	private void blowHoleBtn() {
		defectBean.setDefectID(5);
		updateDefectLable();
		showBtns();
	}

	@FXML
	private void cPiStickBtn() {
		defectBean.setDefectID(6);
		updateDefectLable();
		showBtns();
	}

	@FXML
	private void peakBtn() {
		defectBean.setDefectID(7);
		updateDefectLable();
		showBtns();
	}

	@FXML
	private void underCureBtn() {
		defectBean.setDefectID(8);
		updateDefectLable();
		showBtns();
	}

	@FXML
	private void wrongBandBtn() {
		defectBean.setDefectID(9);
		updateDefectLable();
		showBtns();
	}

	@FXML
	private void wrongMoldBtn() {
		defectBean.setDefectID(10);
		updateDefectLable();
		showBtns();
	}

	private void hideBtns() {
		btnBGrade.setVisible(false);
		btnEGrade.setVisible(false);
		btnRGrade.setVisible(false);
		btnLGrade.setVisible(false);
	}

	private void showBtns() {

		btnBGrade.setVisible(true);
		btnEGrade.setVisible(true);
		btnRGrade.setVisible(true);
		btnLGrade.setVisible(true);
	}

}
