package br.univel;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;

public class SimpleReportDiretoPdf {

	private static final String OUT_PDF = "out.pdf";
	private String arq = "C:\\Users\\darhoden\\Desktop\\jaspersoftstudio\\MyReports\\simples.jasper";

	public SimpleReportDiretoPdf() {

		TableModel tableModel = getTableModel();

		JasperPrint jp = null;
		try {

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("empresa", "Petrobras");
			map.put("telefone", "123pim567pim");

			jp = JasperFillManager.fillReport(arq, map,
					new JRTableModelDataSource(tableModel));

			// JasperViewer jasperViewer = new JasperViewer(jp);
			//
			// jasperViewer.setBounds(50, 50, 320, 240);
			// jasperViewer.setLocationRelativeTo(null);
			// jasperViewer.setExtendedState(JFrame.MAXIMIZED_BOTH);
			//
			// jasperViewer.setVisible(true);

			JasperExportManager.exportReportToPdfFile(jp, OUT_PDF);

			JOptionPane
					.showMessageDialog(
							null,
							"<html>Arquivo exportado para PDF!<br><br>A aplicação vai pedir"
							+ " ao Sistema operacional <br>para abrir com o visualizador"
							+ " padrão.");

			Desktop.getDesktop().open(new File(OUT_PDF));

		} catch (JRException ex) {
			ex.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private TableModel getTableModel() {
		String[] columnNames = { "Id", "Nome", "Departamento", "Email" };

		String[][] data = { { "1", "Hugo", "Financeiro", "hugod@univel.br" },
				{ "2", "José", "Comercial", "josed@univel.br" },
				{ "3", "Luiz", "Contábil", "luizd@univel.br" } };

		return new DefaultTableModel(data, columnNames);
	}

	public static void main(String[] args) {
		new SimpleReportDiretoPdf();
	}
}