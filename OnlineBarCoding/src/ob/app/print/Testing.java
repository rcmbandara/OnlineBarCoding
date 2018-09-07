package ob.app.print;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import ob.app.db.MsAccessDriver;

public class Testing {

	public static void main(String[] args) {
		try {
			
			BarCodePrinter printBarCode = new BarCodePrinter();
			printBarCode.printScaleWgt("SriLanka");
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
}
