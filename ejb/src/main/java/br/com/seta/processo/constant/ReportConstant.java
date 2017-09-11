package br.com.seta.processo.constant;

import java.text.SimpleDateFormat;

public interface ReportConstant {
	
	public static SimpleDateFormat _FMT     = new SimpleDateFormat("ddMMyyyyHHmmss");
	public static final String _FILE_NAME   = _FMT.format(new java.util.Date());
	public static final String _SEPARATOR   = System.getProperty("file.separator");
	public static final String _LOG_SETA    = "/home/seta.admin/app/jasper/logoRelatorio.png";
	public static final String _PAGE_FORMAT = "Página {0} de {1}";
	public static final String _ORGANIZATION= "Seta Atacadista";
//	public static final String _PATH_FILE   = "/home/sergio/app/bonita/report";
	public static final String _PATH_FILE   = System.getProperty("java.io.tmpdir");
	
	
		
	
}
