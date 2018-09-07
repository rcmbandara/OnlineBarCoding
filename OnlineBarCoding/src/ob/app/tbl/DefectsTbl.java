package ob.app.tbl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ob.app.bean.DefectBean;

public class DefectsTbl {
	public ObservableList<DefectBean> getDefects(Connection conn) throws SQLException {

		ObservableList<DefectBean> list = FXCollections.observableArrayList();
		String sql = " select * from quality.defect";
		ResultSet rs = null;

		System.out.println(sql);
		try (PreparedStatement stmt = conn.prepareStatement(sql);) {
			rs = stmt.executeQuery();
			while (rs.next()) {
				list.add(new DefectBean(rs.getInt("defectid"), rs.getString("defect")));
			}
			//Remove defects assigned in Buttons(10 fefects)
			list.remove(0);
			list.remove(0);
			list.remove(0);
			list.remove(0);
			list.remove(0);
			list.remove(0);
			list.remove(0);
			list.remove(0);
			list.remove(0);
			list.remove(0);
			return list;

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ob.app.tbl.Defect getDefects E1 :-" + e);
			return null;
		} finally {
			if (rs != null) {
				rs.close();
			}
		}

	}

	public String getDefectfrmID(Connection conn, int id) throws SQLException {
		String defect = "";
		String sql = " select * from quality.defect where defectid =" + id + "  ; ";
		ResultSet rs = null;

		System.out.println(sql);
		try (PreparedStatement stmt = conn.prepareStatement(sql);) {
			rs = stmt.executeQuery();
			if (rs.next()) {
				defect = rs.getString("defect");
				return defect;
			} else {
				return null;
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ob.app.tbl.Defect getDefects E1 :-" + e);
			return null;
		} finally {
			if (rs != null) {
				rs.close();
			}
		}

	}
}
