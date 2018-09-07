package ob.app.bean;

import java.util.Date;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class BCPendingBean {

	private final IntegerProperty sn;
	private final IntegerProperty pid;
	private final StringProperty tireDescpiton;
	private final StringProperty qg;
	private final ObjectProperty<Date> bcDate;

	public BCPendingBean() {
		this(0, 0, null, null, null);
	}

	public BCPendingBean(int snx, int pidx, String tireDespctionx, String qgx, Date bcDatex) {

		this.tireDescpiton = new SimpleStringProperty(tireDespctionx);
		this.sn = new SimpleIntegerProperty(snx);
		this.pid = new SimpleIntegerProperty(pidx);
		this.qg = new SimpleStringProperty(qgx);
		this.bcDate = new SimpleObjectProperty(bcDatex);
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

	public final StringProperty tireDescpitonProperty() {
		return this.tireDescpiton;
	}

	public final String getTireDescpiton() {
		return this.tireDescpitonProperty().get();
	}

	public final void setTireDescpiton(final String tireDescpiton) {
		this.tireDescpitonProperty().set(tireDescpiton);
	}

	public final StringProperty qgProperty() {
		return this.qg;
	}

	public final String getQg() {
		return this.qgProperty().get();
	}

	public final void setQg(final String qg) {
		this.qgProperty().set(qg);
	}

	public final ObjectProperty<Date> bcDateProperty() {
		return this.bcDate;
	}

	public final Date getBcDate() {
		return this.bcDateProperty().get();
	}

	public final void setBcDate(final Date bcDate) {
		this.bcDateProperty().set(bcDate);
	}

}
