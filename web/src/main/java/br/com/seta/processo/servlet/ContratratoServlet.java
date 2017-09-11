package br.com.seta.processo.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.seta.processo.bean.ScheduleContratoService;


@WebServlet("/contrato")


public class ContratratoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	@Inject private ScheduleContratoService scheduleContratoService;
	

	public ContratratoServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String horas = request.getParameter("horas");
		String minutos = request.getParameter("minutos");
		String segundos = request.getParameter("segundos");
		String inicioSemana = request.getParameter("inicioSemana");
		String fimSemana = request.getParameter("fimSemana");
		scheduleContratoService.save(horas, minutos, inicioSemana, fimSemana);
		scheduleContratoService.stop("ContratosServicos");
		scheduleContratoService.agendar();
		
	}
	

	private void response(HttpServletResponse resp, String msg)	throws IOException {
		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<body>");
		out.println("<t1>" + msg + "</t1>");
		out.println("</body>");
		out.println("</html>");
	}
	
	private static void list(HttpServletRequest request) {
		Enumeration<String> parameterNames = request.getParameterNames();
		while (parameterNames.hasMoreElements()) {
			String paramName = parameterNames.nextElement();
			String[] paramValues = request.getParameterValues(paramName);
			System.out.println(paramName);
			for (int i =0 ; i<paramValues.length; i++)  {
				System.out.println(paramName +": "+paramValues[i] );
			}
		}
	}
}