package ob.app.view;

import java.net.URL;
import java.sql.Connection;
import java.util.Date;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import ob.app.MainApp;
import ob.app.bean.AccessBean;
import ob.app.bean.BCPendingBean;
import ob.app.bean.PidFxBean;
import ob.app.db.CreateConn;
import ob.app.tbl.AccessFile;
import ob.app.tbl.BCPendingTbl;
import ob.app.tbl.PidAccessTbl;
import ob.app.util.AccessBeanCreator;
import ob.app.util.PendingPIDListCreator;

public class AccessFileUpdateController implements Initializable {

	@FXML
	TableView<BCPendingBean> tblPendingTires;
	@FXML
	TableColumn<BCPendingBean, Number> clmnSN;
	@FXML
	TableColumn<BCPendingBean, Number> clmnPID;
	@FXML
	TableColumn<BCPendingBean, Date> clmnBCDate;
	@FXML
	TableColumn<BCPendingBean, String> clmnQG;
	@FXML
	Label lblNotUpdated;
	///////////////
	/////
	@FXML
	TableView<PidFxBean> tblPendingPid;
	@FXML
	TableColumn<PidFxBean, String> clmnPid;
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

	////
	ObservableList<BCPendingBean> oList = FXCollections.observableArrayList();
	//
	ObservableList<PidFxBean> oL_AccessPID = FXCollections.observableArrayList();
	ObservableList<PidFxBean> oL_PSQLPID = FXCollections.observableArrayList();
	ObservableList<PidFxBean> oL_MatchedPID = FXCollections.observableArrayList();

	private MainApp mainApp;

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		clmnPid.setCellValueFactory(cellData -> cellData.getValue().pidAccessProperty());
		clmnSb.setCellValueFactory(cellData -> cellData.getValue().sbProperty());
		clmnCon.setCellValueFactory(cellData -> cellData.getValue().conProperty());
		clmnLt.setCellValueFactory(cellData -> cellData.getValue().ltProperty());
		clmnRs.setCellValueFactory(cellData -> cellData.getValue().rsProperty());
		clmnTt.setCellValueFactory(cellData -> cellData.getValue().ttProperty());
		clmnBr.setCellValueFactory(cellData -> cellData.getValue().brProperty());
		clmnSw.setCellValueFactory(cellData -> cellData.getValue().swmsgProperty());
		try {
			updateTableView_AccessFileUpdate();
			updatePIDTbl();

		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "AccessFileUpdateController.Initialize " + e);
		}
	}

	@FXML
	private void updatePIDTbl() {
		PendingPIDListCreator pending = new PendingPIDListCreator();
		oL_MatchedPID = pending.notUpdatedPIDListCriator();
		tblPendingPid.setItems(oL_MatchedPID);
	}

	@FXML
	private void uploadPIDs() {
		CreateConn createConn = new CreateConn();
		// Get not updated to Access File data from database
		try (Connection conn = createConn.GetConn()) {
			oL_MatchedPID.forEach((PidFxBean bean) -> {
				PidAccessTbl pidAccessTbl = new PidAccessTbl();
				try {
					pidAccessTbl.insertPIDPendingTbl(conn, bean);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "AccessFileUpdateController.uploadPIDs " + e);
				}
			});
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "AccessFileUpdateController.uploadPIDs " + e);
		}
	}

	private void updateTableView_AccessFileUpdate() throws ClassNotFoundException {
		BCPendingTbl bcPendingTbl = new BCPendingTbl();
		CreateConn createConn = new CreateConn();
		// Get not updated to Access File data from database
		try (Connection conn = createConn.GetConn()) {
			oList = bcPendingTbl.getNotUpdatedList(conn);
			tblPendingTires.setItems(oList);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "AccessFileUpdateController.updateTableView_AccessFileUpdate" + e);
		}
		// Set column cell value factory
		clmnSN.setCellValueFactory(cellData -> cellData.getValue().snProperty());
		clmnPID.setCellValueFactory(cellData -> cellData.getValue().pidProperty());
		clmnBCDate.setCellValueFactory(cellData -> cellData.getValue().bcDateProperty());
		clmnQG.setCellValueFactory(cellData -> cellData.getValue().qgProperty());

	}

	@FXML
	private void insertAccessFile() throws ClassNotFoundException {
		oList.forEach((BCPendingBean value) -> {
			AccessFile accessFile = new AccessFile();
			AccessBeanCreator accessBeanCreator = new AccessBeanCreator();

			// Check PID is available in access File
			boolean blnPIDinAccessFile = accessFile.pidAvaialbilityCheck(value.getPid());
			if (blnPIDinAccessFile) {
				AccessBean accessBean = accessBeanCreator.CreateAccessBean(value.getSn(), value.getPid(), "A");
				// Check access file already have the tire with SN
				boolean snAvlAccess = accessFile.serialNoAvailablityCheck(accessBean.getStencilno());
				if (!snAvlAccess) {
					// Insert deta to Access File
					System.out.println("");
					boolean insertedtoAccess = accessFile.inserttoAccessFilea(accessBean);
					insertedtoAccess = accessFile.serialNoAvailablityCheck(accessBean.getStencilno());

					if (insertedtoAccess) {
						// Update bcPending Table with true
						BCPendingTbl bcpendingTbl = new BCPendingTbl();
						CreateConn createConn = new CreateConn();
						try (Connection conn = createConn.GetConn()) {
							bcpendingTbl.updateBCPendingTbl(conn, value.getSn());
						} catch (Exception e) {
							JOptionPane.showMessageDialog(null,
									"A ccessFileUdateController.insertAccessFile not Updated bcpending table with ture  E1 :- "
											+ e);
							delAccessRecord(value.getSn(), value.getPid(), "A");
						}
					} else {// (insertedtoAccess)
						JOptionPane.showMessageDialog(null, value.getSn() + " not inserted to access file");
					}
				} else {// (!snAvlAccess)
					JOptionPane.showMessageDialog(null, "SNo is already avaialble in Access File. Not updated ");
					System.out.println(value.getSn() + " Not Updated");
				}

			} else {// (blnPIDinAccessFile)
				JOptionPane.showMessageDialog(null, value.getPid() + "  PID is not available in Access File");
				System.out.println(value.getSn() + " Not Updated");
			}

		});
		updateTableView_AccessFileUpdate();

	}

	private void delAccessRecord(int sn, int pid, String gq) {
		// if postgresql database is not updated delete the record in MSAccess file
		AccessBeanCreator accessBeanCreator = new AccessBeanCreator();
		AccessFile accessFile = new AccessFile();

		AccessBean accessBean = accessBeanCreator.CreateAccessBean(sn, pid, "A");
		accessFile.DeleteRecordAccessFile(accessBean);
		JOptionPane.showMessageDialog(null, "Deleted", "Delete Record", 1);
	}

}
