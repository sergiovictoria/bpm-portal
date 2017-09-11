package br.com.seta.processo.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;

import javax.annotation.Resource;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.seta.processo.dto.CadastroFornecedor;
import br.com.seta.processo.dto.FormularioFornecedor;
import br.com.seta.processo.utils.WrapperUtils;


@WebServlet("/Formulario/Fornecedores")


public class FormularioFormularioServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Resource(mappedName = "java:/queue/formularios")
	private Queue queueFormularios;

	@Resource(mappedName = "java:/ConnectionFactory")
	private ConnectionFactory factory;

	private CadastroFornecedor cadastroFornecedor = new CadastroFornecedor();
	private FormularioFornecedor formularioFornecedor = new FormularioFornecedor();
	private Collection<Object> objects = new ArrayList<Object>();

	public FormularioFormularioServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FormularioFormularioServlet.list(request);
		
		try {
			formularioFornecedor = (FormularioFornecedor) WrapperUtils.getObject(request, FormularioFornecedor.class);
		} catch (IllegalArgumentException | IllegalAccessException | InstantiationException  | NoSuchFieldException | ParseException e) {
			e.printStackTrace();
		}

		response.setHeader("Access-Control-Allow-Origin", "*");
		cadastroFornecedor.setNomeRespPreench(formularioFornecedor.getRazaoSocialReduzido());
		cadastroFornecedor.setComentarioCadastro("Enviado pelo Portal Web");
		cadastroFornecedor.setDescricao("Pré-cadastro de Inclusão de Fornecedor");
		cadastroFornecedor.setEmailSolicitante(formularioFornecedor.getEmailContato());
		String telefoneComercial = formularioFornecedor.getDdFoneComercial() == null ? "" : formularioFornecedor.getDdFoneComercial() + " ";
		telefoneComercial = telefoneComercial + (formularioFornecedor.getFoneComercial() == null ? "" : formularioFornecedor.getFoneComercial());
		cadastroFornecedor.setTelefoneSolicitante(telefoneComercial);
		cadastroFornecedor.setArea("Portal");
		cadastroFornecedor.setNomeSolicitante("Inclusão de Fornecedor");
		cadastroFornecedor.setDataSolicitacao(new java.util.Date());

		try {
			objects.add(cadastroFornecedor);
			objects.add(formularioFornecedor);
			Connection conn = factory.createConnection("admin","admin123.");
			Session session = conn.createSession(false,Session.AUTO_ACKNOWLEDGE);
			MessageProducer  messageProducer = session.createProducer(queueFormularios);
			ObjectMessage objectMessage = session.createObjectMessage((Serializable) objects);
			messageProducer.send(objectMessage);
			conn.start();
			conn.close();
			response(response, "OK");   		
		} catch (Exception e) {
			response(response, "ERROR");
		}

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
