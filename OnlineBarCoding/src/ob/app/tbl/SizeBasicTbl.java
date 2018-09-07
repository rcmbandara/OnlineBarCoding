package ob.app.tbl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ob.app.bean.PidFxBean;

public class SizeBasicTbl {

	public static ObservableList<PidFxBean> getAllRows(Connection conn) throws SQLException {
		ObservableList<PidFxBean> List_oL = FXCollections.observableArrayList();
		String sql = " select * from srtspec.sizebasic";

		ResultSet rs = null;

		try (PreparedStatement stmt = conn.prepareStatement(sql);) {
			rs = stmt.executeQuery();
			while (rs.next()) {
				PidFxBean bean = new PidFxBean();
				bean.setSb(rs.getString("tiresizebasic"));
				bean.setSbid(rs.getInt("sizebasicid"));
				List_oL.add(bean);
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e);
			return null;
		} finally {
			if (rs != null) {
				rs.close();
			}
		}
		return List_oL;
	}

}
