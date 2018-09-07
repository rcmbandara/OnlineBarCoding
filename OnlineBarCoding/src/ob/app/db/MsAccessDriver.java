package ob.app.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class MsAccessDriver {

	public  Connection getMsAccessConn() {
		// variables
		Connection connection = null;

		// Step 1: Loading or registering Oracle JDBC driver class
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
		} catch (ClassNotFoundException cnfex) {
			System.out.println("Problem in loading or " + "registering MS Access JDBC driver");
			cnfex.printStackTrace();
		}
		// Step 2: Opening database connection
		try {
			String msAccDB = "E:/BARCODE_APP/tyreBarcode.mdb";
			String dbURL = "jdbc:ucanaccess://" + msAccDB;
			// Step 2.A: Create and get connection using DriverManager class
			connection = DriverManager.getConnection(dbURL + ";" + "jackcessOpener=ob.app.tbl.CryptCodecOpener", "User",
					"abcd@abcd");
			return connection;
		} catch (SQLException sqlex) {
			JOptionPane.showMessageDialog(null, "MsAccessDriver.getMsAccessConn()"+sqlex);
			return null;
		} 
	}

}
