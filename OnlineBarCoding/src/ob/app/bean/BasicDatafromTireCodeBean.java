package ob.app.bean;

import java.util.Date;

import javafx.beans.property.IntegerProperty;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class BasicDatafromTireCodeBean {

	private final StringProperty sizebasic;
	private final StringProperty lugtype;
	private final StringProperty config;
	private final StringProperty rimsize;
	private final StringProperty tiretype;
	private final StringProperty swmsg;
	private final StringProperty brand;
	private final StringProperty moldNo;
	private final IntegerProperty sn;
	private final IntegerProperty pid;
	private final IntegerProperty tireCode;
	private final SimpleObjectProperty<Date> birthday;

	public BasicDatafromTireCodeBean() {
		this(null, null, null, null, null, null, null, null, 0, 0, 0, null);
	}

	public BasicDatafromTireCodeBean(String sizebasic, String lugtype, String config, String rimsize, String tiretype,
			String swmsg, String brand, String moldNo, int sn, int pid, int tireCode, Date bithday) {
		this.sizebasic = new SimpleStringProperty(sizebasic);
		this.lugtype = new SimpleStringProperty(lugtype);
		this.config = new SimpleStringProperty(config);
		this.rimsize = new SimpleStringProperty(rimsize);
		this.tiretype = new SimpleStringProperty(tiretype);
		this.swmsg = new SimpleStringProperty(swmsg);
		this.brand = new SimpleStringProperty(brand);
		this.moldNo = new SimpleStringProperty(moldNo);
		this.sn = new SimpleIntegerProperty(sn);
		this.pid = new SimpleIntegerProperty(pid);
		this.tireCode = new SimpleIntegerProperty(tireCode);
		this.birthday = new SimpleObjectProperty(bithday);
	}

	public final StringProperty sizebasicProperty() {
		return this.sizebasic;
	}

	public final String getSizebasic() {
		return this.sizebasicProperty().get();
	}

	public final void setSizebasic(final String sizebasic) {
		this.sizebasicProperty().set(sizebasic);
	}

	public final StringProperty lugtypeProperty() {
		return this.lugtype;
	}

	public final String getLugtype() {
		return this.lugtypeProperty().get();
	}

	public final void setLugtype(final String lugtype) {
		this.lugtypeProperty().set(lugtype);
	}

	public final StringProperty configProperty() {
		return this.config;
	}

	public final String getConfig() {
		return this.configProperty().get();
	}

	public final void setConfig(final String config) {
		this.configProperty().set(config);
	}

	public final StringProperty rimsizeProperty() {
		return this.rimsize;
	}

	public final String getRimsize() {
		return this.rimsizeProperty().get();
	}

	public final void setRimsize(final String rimsize) {
		this.rimsizeProperty().set(rimsize);
	}

	public final StringProperty tiretypeProperty() {
		return this.tiretype;
	}

	public final String getTiretype() {
		return this.tiretypeProperty().get();
	}

	public final void setTiretype(final String tiretype) {
		this.tiretypeProperty().set(tiretype);
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

	public final StringProperty brandProperty() {
		return this.brand;
	}

	public final String getBrand() {
		return this.brandProperty().get();
	}

	public final void setBrand(final String brand) {
		this.brandProperty().set(brand);
	}

	public final StringProperty moldNoProperty() {
		return this.moldNo;
	}

	public final String getMoldNo() {
		return this.moldNoProperty().get();
	}

	public final void setMoldNo(final String moldNo) {
		this.moldNoProperty().set(moldNo);
	}

	public final IntegerProperty snProperty() {
		return this.sn;
	}

	public final int getSn() {
		return this.snProperty().get();
	}

	public final void setSn(final int sn) {
		this.snProperty().set(sn);
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

	public final IntegerProperty tireCodeProperty() {
		return this.tireCode;
	}

	public final int getTireCode() {
		return this.tireCodeProperty().get();
	}

	public final void setTireCode(final int tireCode) {
		this.tireCodeProperty().set(tireCode);
	}

	public final SimpleObjectProperty<Date> birthdayProperty() {
		return this.birthday;
	}

	public final Date getBirthday() {
		return this.birthdayProperty().get();
	}

	public final void setBirthday(final Date birthday) {
		this.birthdayProperty().set(birthday);
	}

}
