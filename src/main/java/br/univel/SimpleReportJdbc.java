package br.univel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

public class SimpleReportJdbc {

	private String arq = "C:\\Users\\darhoden\\Desktop\\jaspersoftstudio\\MyReports\\simplesjdbc.jasper";
	private Connection con;

	public SimpleReportJdbc() throws SQLException {
		
		JasperPrint jasperPrint = null;
		try {
			jasperPrint = JasperFillManager.fillReport(arq, null, getConnection());
			JasperViewer jasperViewer = new JasperViewer(jasperPrint);
			jasperViewer.setVisible(true);
		} catch (JRException ex) {
			ex.printStackTrace();
		}
	}

	private Connection getConnection() throws SQLException {
		
		String url = "jdbc:mysql://192.168.101.10/employees" ;
		String user = "jasper";
		String password = "jasper";

		con = DriverManager.getConnection(url, user, password);
		
		return con;
	}

	public static void main(String[] args) throws SQLException {
		new SimpleReportJdbc();
	}
}