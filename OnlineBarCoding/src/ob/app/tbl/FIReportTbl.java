package ob.app.tbl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import ob.app.bean.FIReportBean;

public class FIReportTbl {
	public boolean InsertFIReportTbl(Connection conn, FIReportBean bean) throws Exception {

		// insert data toquality.fireport table.
		// return bool to verify insert success
		// System.out.println("PID = "+bean.getPid());
		String sql = "insert into quality.fireport (sn,	pid,tirecode,ld,bo,bg,bfm,trd,sfm,ms,tf,speu,stpi,sb,mm,dnm,defectid,other,	qg,moldno,fgdate) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,now());";

		ResultSet keys = null;
		try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {

			// inser into data mfgcorrectiontable this method is called by SNListController
			// Get Values for insert FIReport Table
			stmt.setInt(1, bean.getSn());
			stmt.setInt(2, bean.getPid());
			stmt.setInt(3, bean.getTirecode());
			stmt.setInt(4, bean.getLd());
			stmt.setInt(5, bean.getBo());
			stmt.setInt(6, bean.getBg());
			stmt.setInt(7, bean.getBfm());
			stmt.setInt(8, bean.getTrd());
			stmt.setInt(9, bean.getSfm());
			stmt.setInt(10, bean.getMs());
			stmt.setInt(11, bean.getTf());
			stmt.setInt(12, bean.getSpeu());
			stmt.setInt(13, bean.getStpi());
			stmt.setInt(14, bean.getSb());
			stmt.setInt(15, bean.getMm());
			stmt.setInt(16, bean.getDnm());
			stmt.setInt(17, bean.getDefectid());
			stmt.setInt(18, bean.getOther());
			stmt.setString(19, bean.getQg());
			stmt.setString(20, bean.getMoldno());

			int affected = stmt.executeUpdate();

			if (affected == 1) {
				return true;
			} else {
				System.err.println("Now rows affected");
				JOptionPane.showMessageDialog(null,
						"FIReportTbl.InsertFIReportTbl() not inserted to fireport  SN-:" + bean.getSn());
				return false;
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "FIReportl-E01-FIReportl.InsertFIReportTbl() E1-1 :-" + e);
			return false;
		} finally {
			if (keys != null)
				keys.close();
		}
	}

	public boolean CrossChleckInsert(Connection conn, int sn) throws SQLException {
		String sql = "select * from quality.fireport where sn = " + sn + "; ";
		ResultSet rs = null;
		boolean avl = false;
		try (PreparedStatement stmt = conn.prepareStatement(sql);) {
			rs = stmt.executeQuery();
			if (rs.next()) {
				avl = true;
			} else {
				JOptionPane.showMessageDialog(null, "FIReportTbl.CrossChleckInsert SN is not available");
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, " FIReportTbl.CrossChleckInsert " + e);

		} finally {
			if (rs != null) {
				rs.close();
			}
		}
		return avl;
	}

	// Check Availbility
	public boolean serialNoAvailablityCheck(Connection conn, int sn) {

		boolean bln = false;

		try {

			Statement s = conn.createStatement();

			String selTable = "SELECT * FROM quality.fireport where sn = " + sn + ";";
			s.execute(selTable);
			ResultSet rs = s.getResultSet();
			if (rs.next()) {
				bln = true;
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "AccessFile.getRow  E3" + e);
		}

		return bln;

	}
}
