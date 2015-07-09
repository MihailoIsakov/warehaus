package rs.ac.uns.ftn.xws.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;

/**
 * You'll need these jar's below:
 *	
 * jasperreports-5.0.1.jar
 * iText-2.1.7.jar (needed to generate PDF)
 * jfreechart-1.0.12.jar (needed to graphics and charts)
 * jcommon-1.0.15.jar (needed to graphics and charts)
 * commons-beanutils-1.8.2.jar
 * commons-collections-3.2.1.jar
 * commons-digester-2.1.jar
 * commons-logging-1.1.jar
 */
public class GenerateReport {

    public static void createLagerList() {
        String location = "Reports/";
        String filename = "LagerLista";
        String destination = "pdfs/";
        Map<String, Object> params = new HashMap<String, Object>();

        createPdf(location, filename, destination, params);
    }

    public static void createAnalitika(int IdMK) {
        String location = "Reports/";
        String filename = "Analitika";
        String destination = "pdfs/";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("IdMagacinskaKartica", IdMK);

        createPdf(location, filename, destination, params);
    }

    public static void createPopis(int IdPopis) {
        String location = "Reports/";
        String filename = "PopisniDokument";
        String destination = "pdfs/";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("PopisID", IdPopis);

        createPdf(location, filename, destination, params);
    }
 
	private static void createPdf(String fileLocation, String filename, String destination, Map<String, Object> parameters) {
        
        Connection conn = null;
		try {

            //Establish a database connection 
            conn = null;
            Properties connectionProps = new Properties();
            connectionProps.put("user", "root");
            connectionProps.put("password", "root");
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/",
                    connectionProps);
 
			// compiles jrxml
			JasperCompileManager.compileReportToFile(fileLocation + filename + ".jrxml");
			// fills compiled report with parameters and a connection
			JasperPrint print = JasperFillManager.fillReport(fileLocation + filename + ".jasper", parameters, conn);
			// exports report to pdf
			JRExporter exporter = new JRPdfExporter();
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, new FileOutputStream(destination + filename + ".pdf")); // your output goes here
			
			exporter.exportReport();
 
		} catch (Exception e) {
            e.printStackTrace();
			throw new RuntimeException("It's not possible to generate the pdf report.", e);
		} finally {
			// it's your responsibility to close the connection, don't forget it!
			if (conn != null) {
				try { conn.close(); } catch (Exception e) {}
			}
		}
	}
}
