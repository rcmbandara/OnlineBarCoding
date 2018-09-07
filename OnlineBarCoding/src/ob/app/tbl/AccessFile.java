package ob.app.tbl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ob.app.bean.AccessBean;
import ob.app.bean.PidFxBean;
import ob.app.db.MsAccessDriver;

public class AccessFile {

	public boolean inserttoAccessFilea(AccessBean bean) {

		MsAccessDriver msAccessDriver = new MsAccessDriver();
		String sql = "insert into tbqualitycontrol (config,tyresize,tyrerim,tyretype,brand,sidewall,barcode,qualitygrade,remarks,"
				+ "dateofmanufacture,Processid,tyrestatus,CreateDate,Status,stencilno,DispatchStatus,MergeStatus) "
				+ "values(?,?,?,?,?,?,?,?,?,now()),?,?,now(),?,?,?,?";
		try (Connection conn = msAccessDriver.getMsAccessConn();
				PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {

			stmt.setString(1, bean.getConfig());
			stmt.setString(2, bean.getTyresize());
			stmt.setString(3, bean.getTyrerim());
			stmt.setString(4, bean.getTyretype());
			stmt.setString(5, bean.getBrand());
			stmt.setString(6, bean.getSidewall());
			stmt.setString(7, bean.getBarcode());
			stmt.setString(8, bean.getQualitygrade());
			stmt.setString(9, bean.getRemarks());
			stmt.setString(10, bean.getProcessid());
			stmt.setString(11, bean.getTyrestatus());
			stmt.setInt(12, bean.getStatus());
			stmt.setString(13, bean.getStencilno());
			stmt.setString(14, bean.getDispatchStatus());
			stmt.setInt(15, bean.getMergeStatus());

			int affected = stmt.executeUpdate();
			if (affected == 1) {
				return true;
			} else {
				System.err.println("Now rows affected");
				JOptionPane.showMessageDialog(null,
						"FIReport inspected tire not Inserted Class:-SNListController  SN-: " + bean.getStencilno());

				return false;
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "AccessFile.inserttoAccessFile() not inserted E1" + e);

			return false;
		}
	}

	public boolean DeleteRecordAccessFile(AccessBean bean) {
		String sno = bean.getStencilno();
		MsAccessDriver msAccessDriver = new MsAccessDriver();
		String sql = "DELETE FROM tbqualitycontrol WHERE stencilno ='" + sno + "'";
		try (Connection conn = msAccessDriver.getMsAccessConn();
				PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {

			// stmt.setString(1, bean.getConfig());

			int affected = stmt.executeUpdate();

			if (affected == 1) {
				return true;
			} else {
				System.err.println("Now rows affected");
				JOptionPane.showMessageDialog(null,
						"FIReport inspected tire not Inserted Class:-SNListController E2 SN-:  " + bean.getStencilno());
				return false;
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"AccessFile.inserttoAccessFile() sn not found  " + bean.getStencilno() + "    " + e);
			return false;
		}
	}

	public boolean serialNoAvailablityCheck(String sn) {

		boolean bln = false;
		MsAccessDriver msAccessDriver = new MsAccessDriver();
		try (Connection conn = msAccessDriver.getMsAccessConn();) {

			Statement s = conn.createStatement();

			String selTable = "SELECT * FROM tbqualitycontrol where stencilno = '" + sn + "'";
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

	public boolean pidAvaialbilityCheck(int pid) {
		String ProecessID = "";
		// Convert pid to ProcessID in TTS
		if (pid > 199999) {
			pid = pid - 200000;
			ProecessID = "P-0" + Integer.toString(pid);
		} else {
			ProecessID = "P-" + Integer.toString(pid);
		}

		boolean bln = false;
		MsAccessDriver msAccessDriver = new MsAccessDriver();
		try (Connection conn = msAccessDriver.getMsAccessConn();) {
			Statement s = conn.createStatement();

			String selTable = "SELECT * FROM ProcessID_Details where ProcessID = '" + ProecessID + "'";
			s.execute(selTable);
			ResultSet rs = s.getResultSet();
			if (rs.next()) {
				bln = true;
			} else {
				JOptionPane.showMessageDialog(null, "PID is not available in Access File " + ProecessID);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "AccessFile.getRow  E3" + e);
		}
		return bln;
	}

	public AccessBean getAccessBean(String ProcessID) throws SQLException {
		AccessBean bean = new AccessBean();
		MsAccessDriver msAccessDriver = new MsAccessDriver();
		try (Connection conn = msAccessDriver.getMsAccessConn();) {

			Statement stmt = conn.createStatement();
			String sql = "SELECT * FROM ProcessID_Details where ProcessID = '" + ProcessID + "'";
			stmt.execute(sql);

			ResultSet rs = stmt.getResultSet();
			if (rs.next()) {
				bean.setConfig(rs.getString("Config"));
				bean.setTyresize(rs.getString("TyreSize"));
				bean.setTyrerim((rs.getString("TyreRim")));
				bean.setTyretype((rs.getString("TyreType")));
				bean.setBrand((rs.getString("Brand")));
				bean.setSidewall((rs.getString("Sidewall")));
				bean.setProcessid((rs.getString("ProcessID")));
				return bean;
			} else {
				return null;
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "AccessFile.getAccessBean  E4" + e);
			return null;
		}

	}

	public ObservableList<PidFxBean> getAllPIDsAccess(Connection conn) throws SQLException {
		ObservableList<PidFxBean> List_oL = FXCollections.observableArrayList();
		String sql = "SELECT * FROM ProcessID_Details";

		ResultSet rs = null;

		try (PreparedStatement stmt = conn.prepareStatement(sql);) {
			rs = stmt.executeQuery();
			while (rs.next()) { 
				PidFxBean bean = new PidFxBean();
				bean.setCon(rs.getString("Config"));
				bean.setSb(rs.getString("TyreSize"));
				bean.setRs(rs.getString("TyreRim"));
				bean.setTt(rs.getString("TyreType"));
				bean.setBr(rs.getString("Brand"));
				bean.setSwmsg(rs.getString("Sidewall"));
				bean.setPidAccess(rs.getString("ProcessID"));
				

				List_oL.add(bean);

			}
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "AccessFile.getAllPIDsAccess " + e);
			return null;
		} finally {
			if (rs != null) {
				rs.close();
			}
		}

		return List_oL;

	}
}
