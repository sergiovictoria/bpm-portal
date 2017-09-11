package br.com.seta.processo.report;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRPrintPage;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.wicket.util.io.IOUtils;

public class ReportUtil {

	public static final String PDF = ".pdf";
	public static final String XLS = ".xls";
	
	public static String compilar(List<ReportModel> listaReport, Map<String,Object> parametros,String destFileName) throws IOException, JRException {
		JasperReport relatorioJasper = (JasperReport) JRLoader.loadObjectFromFile(parametros.get("REPORT_JASPER")+"");
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(listaReport);
		JasperPrint jasperPrint = JasperFillManager.fillReport(relatorioJasper, parametros, dataSource);
		JasperExportManager.exportReportToPdfFile(jasperPrint, destFileName);
		return destFileName;
	}	


	public static InputStream getLogo(String logoPath){

		InputStream retorno = null;
		try {
			retorno = new FileInputStream(new File(logoPath));
		} catch (FileNotFoundException e) {
		}

		return retorno;
	}


	public static File export(List<ReportModel> listaReport, Map<String,Object> parametros, TypeReportEnum tipo){

		File file= null;

		switch (tipo) {
		case PDF:
			file = exportarPdf(listaReport, parametros, tipo);
			break;
		case XLS:
			file = exportarXLS(listaReport, parametros);
			break;

		default:
			exportarPdf(listaReport, parametros, tipo);
		}

		return file;
	}


	private static  File exportarPdf(List<ReportModel> listaReport, Map<String,Object> parametros, TypeReportEnum tipo){
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			JasperReport relatorioJasper = (JasperReport) JRLoader.loadObjectFromFile(parametros.get("REPORT_JASPER")+"");

			JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(listaReport);
			JasperPrint jasperPrint = JasperFillManager.fillReport(relatorioJasper, parametros, dataSource);
			
			JasperExportManager.exportReportToPdfStream(jasperPrint, out);



			InputStream relatorios = new ByteArrayInputStream(out.toByteArray());
			return stream2file(relatorios, parametros.get("REPORT_NAME")+"" , PDF);
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;

	}
	
	public static JasperPrint gerarRelatorio(List<ReportModel> listaReport, Map<String,Object> parametros) {
		try {
			JasperReport relatorioJasper = (JasperReport) JRLoader.loadObjectFromFile(parametros.get("REPORT_JASPER")+"");

			JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(listaReport);
			JasperPrint jasperPrint = JasperFillManager.fillReport(relatorioJasper, parametros, dataSource);
			
			return jasperPrint;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * SOMENTE PDF
	 */
	public static File juntarRelatorios(List<JasperPrint> relatorios, Map<String,Object> parametros) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		
		//pega o primeiro relatorio
		JasperPrint jsr1 = relatorios.get(0);
		//removo o primeiro para ñ repetir
		relatorios.remove(jsr1);
		
		//varre os demais relatorios
		for(JasperPrint jsr : relatorios) {
			//adiciona as paginas do relatorio atual no primeiro
			for(JRPrintPage page : jsr.getPages()) 
				jsr1.addPage(page);			
		}
		
		try {
			JasperExportManager.exportReportToPdfStream(jsr1, out);
			
			InputStream relatorio = new ByteArrayInputStream(out.toByteArray());
			return stream2file(relatorio, parametros.get("REPORT_NAME")+"" , PDF);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	private static  File exportarXLS(List<ReportModel> listaReport, Map<String,Object> parametros){

		try {
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("Sheet 1");  

            HSSFRow rowhead = sheet.createRow((short)0);
            
            int index = 0;
        	for (Entry<String, Object> row : listaReport.get(0).getModel().entrySet()) {
        		rowhead.createCell(index).setCellValue(row.getValue() + "");
        		index ++;
			}

        	index = 0;
        	for(int i = 1; i < listaReport.size(); i++) {
	            HSSFRow row = sheet.createRow(i);
	            
	            for (Entry<String, Object> rowList : listaReport.get(i).getModel().entrySet()) {
	            	row.createCell(index).setCellValue(rowList.getValue() + "");
	        		index ++;
				}
	            
	            index = 0;
        	}
            
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            workbook.write(out);
            
            InputStream relatorios = new ByteArrayInputStream(out.toByteArray());
            return stream2file(relatorios, parametros.get("REPORT_NAME")+"" , XLS);

        } catch ( Exception ex ) {
            System.out.println(ex);
        }
		 
		
		return null;

	}


	public static byte[] getBytes(InputStream is) throws IOException {

		int len;
		int size = 1024;
		byte[] buf;

		if (is instanceof ByteArrayInputStream) {
			size = is.available();
			buf = new byte[size];
			len = is.read(buf, 0, size);
		} else {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			buf = new byte[size];
			while ((len = is.read(buf, 0, size)) != -1)
				bos.write(buf, 0, len);
			buf = bos.toByteArray();
		}
		return buf;
	}


	public static File stream2file (InputStream in,String nameFile,String extensaoComPonto ) throws IOException {
		File tempFile = File.createTempFile(nameFile, extensaoComPonto);
		tempFile.deleteOnExit();
		try (FileOutputStream out = new FileOutputStream(tempFile)) {
			IOUtils.copy(in, out);
		}
		return tempFile;
	}

}
