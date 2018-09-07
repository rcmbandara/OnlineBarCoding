package ob.app.tbl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ob.app.bean.BCPendingBean;
import ob.app.bean.FIReportBean;

public class BCPendingTbl {
	public boolean insertBCPendingTbl(Connection conn, FIReportBean bean, String qg) throws Exception {
		// qg is seperately sent since Both A+ and A tires should be barcodes as A;
		int sn = bean.getSn();
		int pid = bean.getPid();
		int tc = bean.getTirecode();

		// Insert BCPending table
		String sql = "insert into barcode.bcpending (sn,pid,tc,bcdate,qg) values(" + sn + "," + pid + "," + tc
				+ ",now(),'" + qg + "');";

		ResultSet keys = null;
		try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
			int affected = stmt.executeUpdate();
			if (affected == 1) {
				return true;
			} else {
				JOptionPane.showMessageDialog(null,
						"E01-Not updated BCPendingTbl.insertBCPendingTbl() table-:" + bean.getSn());
				return false;
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "E02-BCPendingTbl.insertBCPendingTbl():-" + e);
			return false;
		} finally {
			if (keys != null)
				keys.close();
		}
	}

	public boolean CrossChleckInsert(Connection conn, int sn) throws SQLException {
		String sql = "select * from barcode.bcpending where sn = " + sn + "; ";
		ResultSet rs = null;
		boolean avl = false;
		try (PreparedStatement stmt = conn.prepareStatement(sql);) {
			rs = stmt.executeQuery();
			if (rs.next()) {
				avl = true;
			} else {
				JOptionPane.showMessageDialog(null, "E03BCPendingTbl.CrossChleckInsert() SN is not inserted");
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, " E04 BCPendingTbl.CrossChleckInsert()  " + e);

		} finally {
			if (rs != null) {
				rs.close();
			}
		}
		return avl;
	}

	public boolean updateBCPendingTbl(Connection conn, int sn) throws Exception {
		// set repari boolean
		boolean updated = false;

		// Update stk table
		String sql = "update barcode.bcpending set updated=true  where sn=" + sn + "; ";

		ResultSet keys = null;
		try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {

			int affected = stmt.executeUpdate();
			if (affected == 1) {
				updated = true;
			} else {
				JOptionPane.showMessageDialog(null, "E05 Not updated BCPendingTbl.updateBCPendingTbl table-:" + sn);

			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "E06-BCPendingTbl.updateBCPendingTbl() :-" + e);

		} finally {
			if (keys != null)
				keys.close();
		}
		return updated;
	}

	public ObservableList<BCPendingBean> getNotUpdatedList(Connection conn) throws SQLException {
		ObservableList<BCPendingBean> oList = FXCollections.observableArrayList();

		String sql = "select * from barcode.bcpending where updated = false ";
		ResultSet rs = null;

		try (PreparedStatement stmt = conn.prepareStatement(sql);) {
			rs = stmt.executeQuery();
			while (rs.next()) {
				BCPendingBean bean = new BCPendingBean();
				bean.setSn(rs.getInt("sn"));
				bean.setPid(rs.getInt("pid"));
				bean.setBcDate(rs.getDate("bcdate"));
				bean.setQg(rs.getString("qg"));
				oList.add(bean);
			
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, " E07 BCPendingTbl.CrossChleckInsert()  " + e);

		} finally {
			if (rs != null) {
				rs.close();
			}
		}
		return oList;

	}

}
