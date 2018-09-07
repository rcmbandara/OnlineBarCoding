package ob.app.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ob.app.bean.PidFxBean;
import ob.app.db.CreateConn;
import ob.app.db.MsAccessDriver;
import ob.app.tbl.AccessFile;
import ob.app.tbl.PidAccessTbl;

public class PendingPIDListCreator {

	ObservableList<PidFxBean> oL_AccessPID = FXCollections.observableArrayList();
	ObservableList<PidFxBean> oL_PSQLPID = FXCollections.observableArrayList();
	ObservableList<PidFxBean> oL_MatchedPID = FXCollections.observableArrayList();

	public ObservableList<PidFxBean> notUpdatedPIDListCriator() {

		MsAccessDriver msAccessDriver = new MsAccessDriver();
		try (Connection conn = msAccessDriver.getMsAccessConn();) {
			AccessFile accessFile = new AccessFile();

			oL_AccessPID = accessFile.getAllPIDsAccess(conn);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "AccessFileUpdateController.Initializeable" + e);
		}

		CreateConn createConn = new CreateConn();
		// Get not updated to Access File data from database
		try (Connection conn = createConn.GetConn()) {
			PidAccessTbl pidAccessTbl = new PidAccessTbl();
			oL_PSQLPID = pidAccessTbl.getAllPIDsAccess(conn);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "AccessFileUpdateController.Initializeable" + e);
		}
		// Find not including pids
		oL_MatchedPID.clear();
		System.out.println(oL_AccessPID.size());
		for (PidFxBean p : oL_AccessPID) {
			for (PidFxBean q : oL_PSQLPID) {
				if (matchesFilter(p, q)) {
					Platform.runLater(() -> oL_AccessPID.remove(p));
				}
			}
		}
		return oL_AccessPID;
	}

	private boolean matchesFilter(PidFxBean oL_AccessPID, PidFxBean oL_PSQLPID) {

		if ((oL_AccessPID.getPidAccess().equals(oL_PSQLPID.getPidAccess()))) {
			return true;
		} else {
			return false;
		}

	}
}
