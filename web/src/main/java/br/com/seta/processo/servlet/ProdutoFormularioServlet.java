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

import br.com.seta.processo.dto.CadastroProduto;
import br.com.seta.processo.dto.FormularioProduto;
import br.com.seta.processo.utils.WrapperUtils;


@WebServlet("/Formulario/Produtos")


public class ProdutoFormularioServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Resource(mappedName = "java:/queue/produtos")
	private Queue queueProdutos;

	@Resource(mappedName = "java:/ConnectionFactory")
	private ConnectionFactory factory;

	private FormularioProduto formularioProduto = new FormularioProduto();
	private CadastroProduto cadastroProduto = new CadastroProduto();
	private Collection<Object> objects = new ArrayList<Object>();

	public ProdutoFormularioServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Access-Control-Allow-Origin", "*");
			
		try {
			formularioProduto = (FormularioProduto) WrapperUtils.getObject(request, FormularioProduto.class);
		} catch (IllegalArgumentException | IllegalAccessException | InstantiationException  | NoSuchFieldException | ParseException e) {
			e.printStackTrace();
		}
		
				
		cadastroProduto.setNomeRespPreench("Enviado pelo Portal Web");
		cadastroProduto.setComentarioCadastro("Enviado pelo Portal Web");
		cadastroProduto.setDescricao("Pré-cadastro de Inclusão de Produto");
		cadastroProduto.setArea("Portal");
		cadastroProduto.setNomeSolicitante("Inclusão de Prosutos");
		cadastroProduto.setDataSolicitacao(new java.util.Date());
		
		
		try {
			objects.add(formularioProduto);
			objects.add(cadastroProduto);
			Connection conn = factory.createConnection("admin","admin123.");
			Session session = conn.createSession(false,Session.AUTO_ACKNOWLEDGE);
			MessageProducer  messageProducer = session.createProducer(queueProdutos);
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
