package ob.app.util;

import ob.app.bean.AccessBean;
import ob.app.bean.BasicDatafromTireCodeBean;

public class BarCodeCreator {
	public String create(BasicDatafromTireCodeBean bean, String qg) {

		AccessBean accessBean = new AccessBean();
		String processId = "";
		String barCode = "";
		String stencilNo = "L" + bean.getSn();
		// Create ProcessID
		int pid = bean.getPid();
		if (pid > 199999) {
			pid = pid - 200000;
			processId = "P-0" + Integer.toString(pid);
		} else {
			processId = "P-" + Integer.toString(pid);
		}
		return barCode = processId + stencilNo + qg;
	}
}
