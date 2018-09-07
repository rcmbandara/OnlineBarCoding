package ob.app.util;

import java.sql.Connection;

import javax.swing.JOptionPane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ob.app.bean.BasicDatafromTireCodeBean;
import ob.app.db.CreateConn;
import ob.app.tbl.JoinTbl;

public class SNListGetter {
	public ObservableList<BasicDatafromTireCodeBean> getSNList() {
		// Get the sn List form stock.stk table where avl = 3
		ObservableList<BasicDatafromTireCodeBean> List_oL = FXCollections.observableArrayList();
		CreateConn createConn = new CreateConn();
		try (Connection conn = createConn.GetConn()) {
			JoinTbl joinTbl = new JoinTbl();
			List_oL = joinTbl.getAllRows(conn);
			return List_oL;

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
	}

}
