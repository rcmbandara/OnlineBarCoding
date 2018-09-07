package ob.app.view;

//Note if you add any FTR defect pls rewrite last 2 methods accordingly.
//Otervise A,A+ wold be not correct
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import ob.app.MainApp;
import ob.app.bean.AccessBean;
import ob.app.bean.BasicDatafromTireCodeBean;
import ob.app.bean.FIReportBean;
import ob.app.bean.PidFxBean;
import ob.app.db.CreateConn;
import ob.app.print.BarCodePrinter;
import ob.app.tbl.AccessFile;
import ob.app.tbl.BCPendingTbl;
import ob.app.tbl.FIReportTbl;
import ob.app.tbl.JoinTbl;
import ob.app.tbl.StkTbl;
import ob.app.util.AccessBeanCreator;
import ob.app.util.BarCodeCreator;
import ob.app.util.SNListGetter;

public class FIFormatController implements Initializable {
	@FXML
	ToggleGroup T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14;
	@FXML
	Label lblTireSize, lblBrnSWMsg, lblTireType, lblMoldNo, lblSN;
	@FXML
	Button btnEnter, btnDownGrade;
	@FXML
	RadioButton rbtnLDS, rbtnLDM, rbtnLDL, rbtnBOL, rbtnBOM, rbtnBOS, rbtnBGS, rbtnBGM, rbtnBGL, rbtnBFML, rbtnBFMM,
			rbtnBFMS;

	@FXML
	RadioButton rbtnTRDS;
	@FXML
	RadioButton rbtnTRDM;
	@FXML
	RadioButton rbtnTRDL;

	@FXML
	RadioButton rbtnSFMM;
	@FXML
	RadioButton rbtnSFML;
	@FXML
	RadioButton rbtnSFMS;

	@FXML
	RadioButton rbtnMSS;
	@FXML
	RadioButton rbtnMSM;
	@FXML
	RadioButton rbtnMSL;

	@FXML
	RadioButton rbtnTFS;
	@FXML
	RadioButton rbtnTFM;
	@FXML
	RadioButton rbtnTFL;

	@FXML
	RadioButton rbtnSPEUS;
	@FXML
	RadioButton rbtnSPEUM;
	@FXML
	RadioButton rbtnSPEUL;

	@FXML
	RadioButton rbtnSTPIS;
	@FXML
	RadioButton rbtnSTPIL;
	@FXML
	RadioButton rbtnSTPIM;

	@FXML
	RadioButton rbtnSBS;
	@FXML
	RadioButton rbtnSBM;
	@FXML
	RadioButton rbtnSBL;

	@FXML
	RadioButton rbtnMMS;
	@FXML
	RadioButton rbtnMMM;
	@FXML
	RadioButton rbtnMML;

	@FXML
	RadioButton rbtnDNML;
	@FXML
	RadioButton rbtnDNMS;
	@FXML
	RadioButton rbtnDNMM;
	@FXML
	RadioButton rbtnOther;
	//////////////

	@FXML
	Button btnLD;
	@FXML
	Button btnBO;
	@FXML
	Button btnBG;
	@FXML
	Button btnBFM;
	@FXML
	Button btnTRD;
	@FXML
	Button btnSFM;
	@FXML
	Button btnMS;
	@FXML
	Button btnTF;
	@FXML
	Button btnSPEU;
	@FXML
	Button btnSTPI;
	@FXML
	Button btnSB;
	@FXML
	Button btnMM;
	@FXML
	Button btnDNM;
	@FXML
	Button btnOther;
	////////////
	// PID Change controllres
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
	TextField txtFFSB, txtFFLT, txtFFRS, txtFFCon, txtFFTT, txtFFBR, txtFFSWMSG, txtFFPID;
	@FXML
	AnchorPane apPIDChange;
	ObservableList<PidFxBean> filteredData = FXCollections.observableArrayList();
	ObservableList<PidFxBean> masterData = FXCollections.observableArrayList();
	////////////
	BasicDatafromTireCodeBean bTireDataStatBean = new BasicDatafromTireCodeBean();
	FIReportBean fiReportBean = new FIReportBean();
	private MainApp mainApp;

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		rbtnBFML.setSelected(false);
		// Initialize PIDlist

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
		// Hide PIC Change Anchor Pane
		// apPIDChange.setVisible(false);

		// PID Tbl Selection
		tblPid.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			System.out.println("Selected");
		});
	}

	public void setBean(BasicDatafromTireCodeBean bean) {
		this.bTireDataStatBean = bean;
		// Display Tire Size in lables
		String tireSize = bean.getSizebasic() + " " + bean.getLugtype() + " " + bean.getConfig() + " "
				+ bean.getRimsize() + "     " + bean.getTiretype();
		String tireType = bean.getTiretype();
		String bNameSWMsg = bean.getBrand() + "   " + bean.getSwmsg();
		// Assign values to lables
		lblTireSize.setText(tireSize);
		lblBrnSWMsg.setText(bNameSWMsg);
		lblTireType.setText(tireType);
		lblMoldNo.setText("M.No:- " + bean.getMoldNo());
		lblSN.setText(Integer.toString(bean.getSn()));
	}

	@FXML
	private void ShowBER() {
		// Show BER Scene
		mainApp.addBER(bTireDataStatBean);
	}

	@FXML
	private void enterButton() throws SQLException {

		// Insert data in to FIReport 'PGS' Table
		fiReportBean.setSn(bTireDataStatBean.getSn());
		fiReportBean.setPid(bTireDataStatBean.getPid());
		fiReportBean.setTirecode(bTireDataStatBean.getTireCode());
		fiReportBean.setMoldno(bTireDataStatBean.getMoldNo());

		// Get Defect info form radiobuttons
		setfiRepoertBean();

		CreateConn createConn = new CreateConn();
		try (Connection conn = createConn.GetConn()) {
			conn.setAutoCommit(false);
			FIReportTbl fiReportTbl = new FIReportTbl();
			StkTbl stkTbl = new StkTbl();
			AccessFile accessFile = new AccessFile();
			BCPendingTbl bcPendingTbl = new BCPendingTbl();
			// First check already barcoded sn. Then only we have to barcode
			boolean snAvlAccess = accessFile.serialNoAvailablityCheck(("L" + fiReportBean.getSn()));
			if (!snAvlAccess) {
				// Cross check pid availability in access file
				boolean blnPIDinAccessFile = accessFile.pidAvaialbilityCheck(fiReportBean.getPid());
				if (blnPIDinAccessFile) {
					// Cross check already barceded by fireport table has sn.
					boolean blnFfireportavl = fiReportTbl.serialNoAvailablityCheck(conn, fiReportBean.getSn());
					if (!blnFfireportavl) {
						boolean blnInsertedDefectTbl = fiReportTbl.InsertFIReportTbl(conn, fiReportBean);
						boolean blnUpdatedStkTbl = stkTbl.updateStkTbl(conn, fiReportBean, "A");
						boolean blnBCPendingTbl = bcPendingTbl.insertBCPendingTbl(conn, fiReportBean, "A");

						// Cross check again
						blnInsertedDefectTbl = fiReportTbl.CrossChleckInsert(conn, fiReportBean.getSn());
						blnUpdatedStkTbl = stkTbl.CrossChleckUpdate(conn, fiReportBean.getSn());
						blnBCPendingTbl = bcPendingTbl.CrossChleckInsert(conn, fiReportBean.getSn());

						if (blnInsertedDefectTbl && blnUpdatedStkTbl && blnBCPendingTbl) {
							// Go again SNlist
							// Reload the SNList
							conn.commit();
							BarCodeCreator barCodeCreator = new BarCodeCreator();
							String barCode = barCodeCreator.create(bTireDataStatBean, "A");

							BarCodePrinter barCodePrinter = new BarCodePrinter();
							barCodePrinter.printScaleWgt(barCode);

							// Refresh form
							SNListGetter snListGetter = new SNListGetter();
							ObservableList<BasicDatafromTireCodeBean> List_oL = snListGetter.getSNList();
							mainApp.addSNList(List_oL);

						} else {// (blnInsertedDefectTbl && blnUpdatedStkTbl && blnBCPendingTbl)
							conn.rollback();
							JOptionPane.showMessageDialog(null, "Completely rolled Back");
						}
					} else {// (!blnFfireportavl)
						JOptionPane.showMessageDialog(null,
								fiReportBean.getSn() + " is  already avaialble in QUALITY REPORT! Can  not BarCode ",
								"Allready BarCoded Tire", 1);
					}

				} else {// (blnPIDinAccessFile)
					JOptionPane.showMessageDialog(null,
							fiReportBean.getPid() + " is not avaialble in Access File! Can  not BarCode ",
							"PID is not available in Access File", 1);
				}

			} else {// (!snAvlAccess) {
				JOptionPane.showMessageDialog(null,
						fiReportBean.getSn() + " is already avaialble in Access File! Can  not BarCode ",
						"Already BarCoded", 1);
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "FIFrmatController.insertFIReport E1" + e, null, 0);
		}

	}

	///////////////////////////////////////////////////////////////////////////////////////////
	// This method only applicable for FTR Scene only.
	// this will store miner defects status
	// Then catogarize A+ or A grade
	private void setfiRepoertBean() {
		// Set Bean
		// Lug Dammage
		if (rbtnLDS.isSelected())
			fiReportBean.setLd(1);
		if (rbtnLDM.isSelected())
			fiReportBean.setLd(2);
		if (rbtnLDL.isSelected())
			fiReportBean.setLd(3);
		// Beed Out
		if (rbtnBOS.isSelected())
			fiReportBean.setBo(1);
		if (rbtnBOM.isSelected())
			fiReportBean.setBo(2);
		if (rbtnBOL.isSelected())
			fiReportBean.setBo(3);

		// BackGriding
		if (rbtnBGS.isSelected())
			fiReportBean.setBg(1);
		if (rbtnBGM.isSelected())
			fiReportBean.setBg(2);
		if (rbtnBGL.isSelected())
			fiReportBean.setBg(3);

		// bASE fLOW mARKS
		if (rbtnBFMS.isSelected())
			fiReportBean.setBfm(1);
		if (rbtnBFMM.isSelected())
			fiReportBean.setBfm(2);
		if (rbtnBFML.isSelected())
			fiReportBean.setBfm(3);

		// TR Damage
		if (rbtnTRDS.isSelected())
			fiReportBean.setTrd(1);
		if (rbtnTRDM.isSelected())
			fiReportBean.setTrd(2);
		if (rbtnTRDL.isSelected())
			fiReportBean.setTrd(3);

		// Flow Marks
		if (rbtnSFMS.isSelected())
			fiReportBean.setSfm(1);
		if (rbtnSFMM.isSelected())
			fiReportBean.setSfm(2);
		if (rbtnSFML.isSelected())
			fiReportBean.setSfm(3);
		// Mold Slip
		if (rbtnMSS.isSelected())
			fiReportBean.setMs(1);
		if (rbtnMSM.isSelected())
			fiReportBean.setMs(2);
		if (rbtnMSL.isSelected())
			fiReportBean.setMs(3);

		// tHICK fLSH
		if (rbtnTFS.isSelected())
			fiReportBean.setTf(1);
		if (rbtnTFM.isSelected())
			fiReportBean.setTf(2);
		if (rbtnTFL.isSelected())
			fiReportBean.setTf(3);

		// speu particls

		if (rbtnSPEUS.isSelected())
			fiReportBean.setSpeu(1);
		if (rbtnSPEUM.isSelected())
			fiReportBean.setSpeu(2);
		if (rbtnSPEUL.isSelected())
			fiReportBean.setSpeu(3);
		// Stick Pieses
		if (rbtnSTPIS.isSelected())
			fiReportBean.setStpi(1);
		if (rbtnSTPIM.isSelected())
			fiReportBean.setStpi(2);
		if (rbtnSTPIL.isSelected())
			fiReportBean.setStpi(3);

		// Mold mARK
		if (rbtnMMS.isSelected())
			fiReportBean.setMm(1);
		if (rbtnMMM.isSelected())
			fiReportBean.setMm(2);
		if (rbtnMML.isSelected())
			fiReportBean.setMm(3);

		// Surface Bead
		if (rbtnSBS.isSelected())
			fiReportBean.setSb(1);
		if (rbtnSBM.isSelected())
			fiReportBean.setSb(2);
		if (rbtnSBL.isSelected())
			fiReportBean.setSb(3);

		// DIRTY NM
		if (rbtnDNMS.isSelected())
			fiReportBean.setDnm(1);
		if (rbtnDNMM.isSelected())
			fiReportBean.setDnm(2);
		if (rbtnDNML.isSelected())
			fiReportBean.setDnm(3);

		// Other
		if (rbtnOther.isSelected())
			fiReportBean.setOther(1);

		if (fiReportBean.getLd() != 0 || fiReportBean.getBo() != 0 || fiReportBean.getBg() != 0
				|| fiReportBean.getBfm() != 0 || fiReportBean.getTrd() != 0 || fiReportBean.getSfm() != 0
				|| fiReportBean.getMs() != 0 || fiReportBean.getTf() != 0 || fiReportBean.getSpeu() != 0
				|| fiReportBean.getStpi() != 0 || fiReportBean.getSb() != 0 || fiReportBean.getDnm() != 0
				|| fiReportBean.getOther() != 0) {
			fiReportBean.setQg("A");

		} else {
			fiReportBean.setQg("A+");
		}
	}

	////////////////////////////////////////////////
	// Clear radio button toggle groups
	@FXML
	private void clsLD() {
		T1.selectToggle(null);
		fiReportBean.setLd(0);
	}

	@FXML
	private void clsBO() {
		T2.selectToggle(null);
		fiReportBean.setBo(0);
	}

	@FXML
	private void clsBG() {
		T3.selectToggle(null);
		fiReportBean.setBg(0);
	}

	@FXML
	private void clsBFM() {
		T4.selectToggle(null);
		fiReportBean.setBfm(0);
	}

	@FXML
	private void clsTRD() {
		T5.selectToggle(null);
		fiReportBean.setTrd(0);
	}

	@FXML
	private void clsSFM() {
		T6.selectToggle(null);
		fiReportBean.setSfm(0);
	}

	@FXML
	private void clsMS() {
		T7.selectToggle(null);
		fiReportBean.setMs(0);
	}

	@FXML
	private void clsTF() {
		T8.selectToggle(null);
		fiReportBean.setTf(0);
	}

	@FXML
	private void clsSPEU() {
		T9.selectToggle(null);
		fiReportBean.setSpeu(0);
	}

	@FXML
	private void clsSTPI() {
		T10.selectToggle(null);
		fiReportBean.setStpi(0);
	}

	@FXML
	private void clsSB() {
		T11.selectToggle(null);
		fiReportBean.setSb(0);
	}

	@FXML
	private void clsMM() {
		T12.selectToggle(null);
		fiReportBean.setMm(0);
	}

	@FXML
	private void clsDNM() {
		T13.selectToggle(null);
		fiReportBean.setDnm(0);
	}

	@FXML
	private void clsOther() {
		T14.selectToggle(null);
		fiReportBean.setOther(0);
	}
	//////////////////////////////////////
	/////////////////////////////////////

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

		txtFFPID.textProperty().addListener((observable, oldValue, newSB) -> {
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
		String refpid = Integer.toString(bean.getPid()).toLowerCase();

		String txtValsb = txtFFSB.getText().toLowerCase();
		String txtVallt = txtFFLT.getText().toLowerCase();
		String txtValcon = txtFFCon.getText().toLowerCase();
		String txtValrs = txtFFRS.getText().toLowerCase();
		String txtValtt = txtFFTT.getText().toLowerCase();
		String txtValbr = txtFFBR.getText().toLowerCase();
		String txtValswmsg = txtFFSWMSG.getText().toLowerCase();
		String txtPid = txtFFPID.getText().toLowerCase();

		/*
		 * if (newText == null || newText.isEmpty()) { // No filter --> Add all. return
		 * true; }
		 */

		// String lowerCaseNewText = newText.toLowerCase();
		if ((refsb.indexOf(txtValsb) != -1) && (reflt.indexOf(txtVallt) != -1) && (refcon.indexOf(txtValcon) != -1)
				&& (refrs.indexOf(txtValrs) != -1) && (reftt.indexOf(txtValtt) != -1) && (refbr.indexOf(txtValbr) != -1)
				&& (refpid.indexOf(txtPid) != -1) && (refswmsg.indexOf(txtValswmsg) != -1)) {
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



	@FXML
	private void showPIDChangeTbl() {

		apPIDChange.setVisible(true);
		btnEnter.setVisible(false);
		btnDownGrade.setVisible(false);

	}
}
