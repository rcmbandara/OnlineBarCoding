package ob.app.util;

import javax.swing.JOptionPane;

import ob.app.bean.AccessBean;
import ob.app.bean.BasicDatafromTireCodeBean;
import ob.app.bean.FIReportBean;
import ob.app.tbl.AccessFile;

public class AccessBeanCreator {
	public AccessBean CreateAccessBean( int sn,int pid, String qg) {
		// qg is seperately sent since Both A+ and A tires should be barcodes as A;
		try {

			AccessFile accessFile = new AccessFile();
			AccessBean accessBean = new AccessBean();
			String processId = "";
			String barCode = "";
			String stencilNo = "L" + sn;
			// Create ProcessID

			if (pid > 199999) {
				pid = pid - 200000;
				processId = "P-0" + Integer.toString(pid);
			} else {
				processId = "P-" + Integer.toString(pid);
			}
			// Crate BarCode
			barCode = processId + stencilNo + qg;
	
			accessBean = accessFile.getAccessBean(processId);
			// Setting data to Bean
			accessBean.setBarcode(barCode);
			accessBean.setQualitygrade(qg);
			accessBean.setRemarks("0");
			accessBean.setProcessid(processId);
			accessBean.setTyrestatus("No");
			accessBean.setStatus(1);
			accessBean.setStencilno(stencilNo);
			accessBean.setDispatchStatus("No");
			accessBean.setMergeStatus(1);

			return accessBean;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "AccessBeanCreator.AccessBean()" + e);
			return null;
		}

	}

}
