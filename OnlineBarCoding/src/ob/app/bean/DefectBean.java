package ob.app.bean;

public class DefectBean {
	int defectID;
	String defect;

	public DefectBean() {
		this(0, null);
	}

	public DefectBean(int defectID, String defect) {
		super();
		this.defectID = defectID;
		this.defect = defect;
	}

	public int getDefectID() {
		return defectID;
	}

	public void setDefectID(int defectID) {
		this.defectID = defectID;
	}

	public String getDefect() {
		return defect;
	}

	public void setDefect(String defect) {
		this.defect = defect;
	}

}
