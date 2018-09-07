package ob.app.bean;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PidFxBean {

	private final StringProperty sb;
	private final StringProperty lt;
	private final StringProperty con;
	private final StringProperty rs;
	private final StringProperty tt;
	private final StringProperty swmsg;
	private final StringProperty br;
	private final StringProperty pidAccess;

	private final IntegerProperty pid;
	private final IntegerProperty sbid;
	private final IntegerProperty ltid;
	private final IntegerProperty conid;
	private final IntegerProperty rsid;
	private final IntegerProperty ttid;
	private final IntegerProperty swmsgid;
	private final IntegerProperty brid;

	public PidFxBean() {
		this(0, 0, 0, 0, 0, 0, 0, 0, null, null, null, null, null, null, null, null);
	}

	public PidFxBean(int pidx, int sbidx, int ltidx, int conidx, int rsidx, int ttidx, int swmsgidx, int bridx,
			String sbx, String ltx, String conx, String rsx, String ttx, String swmsgx, String brx, String PIDAcx) {

		this.sb = new SimpleStringProperty(sbx);
		this.lt = new SimpleStringProperty(ltx);
		this.con = new SimpleStringProperty(conx);
		this.rs = new SimpleStringProperty(rsx);
		this.tt = new SimpleStringProperty(ttx);
		this.swmsg = new SimpleStringProperty(swmsgx);
		this.br = new SimpleStringProperty(brx);

		this.sbid = new SimpleIntegerProperty(sbidx);
		this.ltid = new SimpleIntegerProperty(ltidx);
		this.conid = new SimpleIntegerProperty(conidx);
		this.rsid = new SimpleIntegerProperty(rsidx);
		this.ttid = new SimpleIntegerProperty(ttidx);
		this.swmsgid = new SimpleIntegerProperty(swmsgidx);
		this.brid = new SimpleIntegerProperty(bridx);
		this.pid = new SimpleIntegerProperty(pidx);
		this.pidAccess = new SimpleStringProperty(PIDAcx);
	}

	public final StringProperty sbProperty() {
		return this.sb;
	}

	public final String getSb() {
		return this.sbProperty().get();
	}

	public final void setSb(final String sb) {
		this.sbProperty().set(sb);
	}

	public final StringProperty ltProperty() {
		return this.lt;
	}

	public final String getLt() {
		return this.ltProperty().get();
	}

	public final void setLt(final String lt) {
		this.ltProperty().set(lt);
	}

	public final StringProperty conProperty() {
		return this.con;
	}

	public final String getCon() {
		return this.conProperty().get();
	}

	public final void setCon(final String con) {
		this.conProperty().set(con);
	}

	public final StringProperty rsProperty() {
		return this.rs;
	}

	public final String getRs() {
		return this.rsProperty().get();
	}

	public final void setRs(final String rs) {
		this.rsProperty().set(rs);
	}

	public final StringProperty ttProperty() {
		return this.tt;
	}

	public final String getTt() {
		return this.ttProperty().get();
	}

	public final void setTt(final String tt) {
		this.ttProperty().set(tt);
	}

	public final StringProperty swmsgProperty() {
		return this.swmsg;
	}

	public final String getSwmsg() {
		return this.swmsgProperty().get();
	}

	public final void setSwmsg(final String swmsg) {
		this.swmsgProperty().set(swmsg);
	}

	public final StringProperty brProperty() {
		return this.br;
	}

	public final String getBr() {
		return this.brProperty().get();
	}

	public final void setBr(final String br) {
		this.brProperty().set(br);
	}

	public final IntegerProperty pidProperty() {
		return this.pid;
	}

	public final int getPid() {
		return this.pidProperty().get();
	}

	public final void setPid(final int pid) {
		this.pidProperty().set(pid);
	}

	public final IntegerProperty sbidProperty() {
		return this.sbid;
	}

	public final int getSbid() {
		return this.sbidProperty().get();
	}

	public final void setSbid(final int sbid) {
		this.sbidProperty().set(sbid);
	}

	public final IntegerProperty ltidProperty() {
		return this.ltid;
	}

	public final int getLtid() {
		return this.ltidProperty().get();
	}

	public final void setLtid(final int ltid) {
		this.ltidProperty().set(ltid);
	}

	public final IntegerProperty conidProperty() {
		return this.conid;
	}

	public final int getConid() {
		return this.conidProperty().get();
	}

	public final void setConid(final int conid) {
		this.conidProperty().set(conid);
	}

	public final IntegerProperty rsidProperty() {
		return this.rsid;
	}

	public final int getRsid() {
		return this.rsidProperty().get();
	}

	public final void setRsid(final int rsid) {
		this.rsidProperty().set(rsid);
	}

	public final IntegerProperty ttidProperty() {
		return this.ttid;
	}

	public final int getTtid() {
		return this.ttidProperty().get();
	}

	public final void setTtid(final int ttid) {
		this.ttidProperty().set(ttid);
	}

	public final IntegerProperty swmsgidProperty() {
		return this.swmsgid;
	}

	public final int getSwmsgid() {
		return this.swmsgidProperty().get();
	}

	public final void setSwmsgid(final int swmsgid) {
		this.swmsgidProperty().set(swmsgid);
	}

	public final IntegerProperty bridProperty() {
		return this.brid;
	}

	public final int getBrid() {
		return this.bridProperty().get();
	}

	public final void setBrid(final int brid) {
		this.bridProperty().set(brid);
	}

	public final StringProperty pidAccessProperty() {
		return this.pidAccess;
	}
	

	public final String getPidAccess() {
		return this.pidAccessProperty().get();
	}
	

	public final void setPidAccess(final String pidAccess) {
		this.pidAccessProperty().set(pidAccess);
	}
	

}
