package ob.app.db;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

public class CreateConn {
	private static String USERNAME = "";
	private static String PASSWORD = "";
	private static String P_CONN_STRING = "";
	private static String M_CONN_STRING = "";

	public  Connection GetConn() throws ClassNotFoundException {
		int lineNo;
		String xx = System.getProperty("os.name");
		try {
			FileReader fr = new FileReader("C:\\Program Files\\MySQL\\MySQL Server 5.7\\DBUtil.txt");
			// FileReader fr = new FileReader("/home/fg-admin/DBUtil/DBUtil.txt");
			BufferedReader br = new BufferedReader(fr);
			for (lineNo = 1; lineNo < 10; lineNo++) {
				if (lineNo == 1) {
					M_CONN_STRING = br.readLine();
				} else if (lineNo == 2) {
					USERNAME = br.readLine();
				} else if (lineNo == 3) {
					PASSWORD = br.readLine();
				} else if (lineNo == 4) {
					P_CONN_STRING = br.readLine();
				} else
					br.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		Class.forName("org.postgresql.Driver");
		try {
			Connection conn = DriverManager.getConnection(P_CONN_STRING, USERNAME, PASSWORD);
			return conn;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "CreateConn.GetConn method" + e);
			return null;
		}
	}

}