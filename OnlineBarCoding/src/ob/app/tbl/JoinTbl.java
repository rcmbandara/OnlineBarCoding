package ob.app.tbl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ob.app.bean.BasicDatafromTireCodeBean;
import ob.app.bean.PidFxBean;

public class JoinTbl {

	public ObservableList<BasicDatafromTireCodeBean> getAllRows(Connection conn) throws SQLException {
		ObservableList<BasicDatafromTireCodeBean> List_oL = FXCollections.observableArrayList();
		String sql = " select tiresizebasic,config,lugtypecap,rimsize,tiretypecap,swmsg,brand,p.pid pid_a,m.moldno m_no,stktbl.sn sn_a, "
				+ " stktbl.mfgdate mfgdate_a,stktbl.tc tc_a  " + " from stock.stk stktbl "
				+ "  join srtspec.tirecode tctbl on tctbl.tirecode = stktbl.tc "
				+ " join srtspec.pid p on p.pid= tctbl.pid " + " 	join srtspec.size s on s.sizeid = p.sizeid  "
				+ " join srtspec.sizebasic sb on sb.sizebasicid = s.sizebasicid "
				+ " join srtspec.config c on c.configid = s.configid  "
				+ " join srtspec.lugtype l on l.lugtypeid = s.lugtypeid  "
				+ " join srtspec.rimsize rs on rs.rimsizeid = p.rimsizeid  "
				+ " join srtspec.tiretype tt on tt.tiretypeid = p.tiretypeid  "
				+ " join srtspec.swmsg sw on sw.swmsgid = p.swmsgid  "
				+ " join srtspec.brand br on br.brandid = p.brandid  "
				+ " join srtspec.mold m on m.moldid =  tctbl.moldid "
				+ " join srtspec.wheelcolor wc on wc.wheelcolorid = p.wheelcolorid where stktbl.avl ='3' "
				+ " order by sn_a";

		ResultSet rs = null;

		try (PreparedStatement stmt = conn.prepareStatement(sql);) {
			rs = stmt.executeQuery();
			while (rs.next()) {
				BasicDatafromTireCodeBean bean = new BasicDatafromTireCodeBean();
				bean.setBirthday(rs.getDate("mfgdate_a"));
				bean.setSizebasic(rs.getString("tiresizebasic"));
				bean.setConfig(rs.getString("config"));
				bean.setLugtype(rs.getString("lugtypecap"));
				bean.setRimsize(rs.getString("rimsize"));
				bean.setTiretype(rs.getString("tiretypecap"));
				bean.setSwmsg(rs.getString("swmsg"));
				bean.setBrand(rs.getString("brand"));
				bean.setPid(rs.getInt("pid_a"));
				bean.setSn(rs.getInt("sn_a"));
				bean.setTireCode(rs.getInt("tc_a"));
				bean.setMoldNo(rs.getString("m_no"));
				List_oL.add(bean);
			}
			return List_oL;
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "JoinTbl.GetAllRows."+e);
			return null;
		} finally {
			if (rs != null) {
				rs.close();
			}
		}

	}

	public String getTireDetailFrmstkTbl(Connection conn, int sn) throws SQLException {
		String tireDescriptoin = "";
		String sql = " select tiresizebasic,config,lugtypecap,rimsize,tiretypecap,swmsg,brand,p.pid pid_a,m.moldno m_no,stktbl.sn sn_a, "
				+ " stktbl.mfgdate mfgdate_a,stktbl.tc tc_a  " + " from stock.stk stktbl "
				+ "  join srtspec.tirecode tctbl on tctbl.tirecode = stktbl.tc "
				+ " join srtspec.pid p on p.pid= tctbl.pid " + " 	join srtspec.size s on s.sizeid = p.sizeid  "
				+ " join srtspec.sizebasic sb on sb.sizebasicid = s.sizebasicid "
				+ " join srtspec.config c on c.configid = s.configid  "
				+ " join srtspec.lugtype l on l.lugtypeid = s.lugtypeid  "
				+ " join srtspec.rimsize rs on rs.rimsizeid = p.rimsizeid  "
				+ " join srtspec.tiretype tt on tt.tiretypeid = p.tiretypeid  "
				+ " join srtspec.swmsg sw on sw.swmsgid = p.swmsgid  "
				+ " join srtspec.brand br on br.brandid = p.brandid  "
				+ " join srtspec.mold m on m.moldid =  tctbl.moldid "
				+ " join srtspec.wheelcolor wc on wc.wheelcolorid = p.wheelcolorid where sn = " + sn + " ;";
		System.out.println(sql);
		ResultSet rs = null;

		try (PreparedStatement stmt = conn.prepareStatement(sql);) {
			rs = stmt.executeQuery();
			if (rs.next()) {

				String tiresizebasics = (rs.getString("tiresizebasic"));
				String con = (rs.getString("config"));
				String lt = (rs.getString("lugtypecap"));
				String rsize = (rs.getString("rimsize"));
				String tt = (rs.getString("tiretypecap"));
				String swmsg = (rs.getString("swmsg"));

				String br = (rs.getString("brand"));
				tireDescriptoin = tiresizebasics + " " + con + " " + lt + " " + rsize + " " + tt + " " + br + " "
						+ swmsg;

			} else {
				JOptionPane.showMessageDialog(null, "JoinTbl.getTireDetail() SN is not avialable");
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "JoinTbl.getTireDetail()" +  "JoinTbl.getTireDetailFrmstkTbl"+e);

		} finally {
			if (rs != null) {
				rs.close();
			}
		}
		return tireDescriptoin;

	}

	public ObservableList<PidFxBean> getAllPIDs(Connection conn) throws SQLException {
		ObservableList<PidFxBean> List_oL = FXCollections.observableArrayList();
		String sql = " select p.pid pid,sb.sizebasicid sbid,sb.tiresizebasic sb,lt.lugtypeid ltid,lt.lugtype lt,con.configid conid,con.config con,rs.rimsizeid rsid,rs.rimsize rs,"
				+ "tt.tiretypeid ttid,tt.tiretype tt,sw.swmsgid swmsgid,sw.swmsg swmsg,br.brandid brid,br.brand br  from srtspec.pid p "
				+ "join srtspec.size s on s.sizeid = p.sizeid "
				+ "join srtspec.sizebasic sb on sb.sizebasicid = s.sizebasicid "
				+ "join srtspec.lugtype lt on lt.lugtypeid = s.lugtypeid  "
				+ "join srtspec.config con on con.configid = s.configid "
				+ "join srtspec.rimsize rs on rs.rimsizeid = p.rimsizeid "
				+ "join srtspec.tiretype tt on tt.tiretypeid = p.tiretypeid "
				+ "join srtspec.swmsg sw on sw.swmsgid = p.swmsgid "
				+ "join srtspec.brand br on br.brandid = p.brandid ";
		System.out.println(sql);
		ResultSet rs = null;

		try (PreparedStatement stmt = conn.prepareStatement(sql);) {
			rs = stmt.executeQuery();
			while (rs.next()) {
				PidFxBean bean = new PidFxBean();

				bean.setPid(rs.getInt("pid"));

				bean.setSbid(rs.getInt("sbid"));
				bean.setLtid(rs.getInt("ltid"));
				bean.setConid(rs.getInt("conid"));
				bean.setRsid(rs.getInt("rsid"));
				bean.setTtid(rs.getInt("ttid"));
				bean.setBrid(rs.getInt("brid"));
				bean.setSwmsgid(rs.getInt("swmsgid"));

				bean.setSb(rs.getString("sb"));
				bean.setLt(rs.getString("lt"));
				bean.setCon(rs.getString("con"));
				bean.setRs(rs.getString("rs"));
				bean.setTt(rs.getString("tt"));
				bean.setBr(rs.getString("br"));
				bean.setSwmsg(rs.getString("swmsg"));

				List_oL.add(bean);

			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "getTireDetailFrmstkTbl.getAllPIDs"+e);
			return null;
		} finally {
			if (rs != null) {
				rs.close();
			}
		}
	
		return List_oL;

	}

}
