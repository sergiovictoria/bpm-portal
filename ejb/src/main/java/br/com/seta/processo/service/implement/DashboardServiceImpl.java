package br.com.seta.processo.service.implement;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.interceptor.Interceptors;

import org.bonitasoft.engine.exception.BonitaException;

import br.com.seta.processo.dto.Actor;
import br.com.seta.processo.dto.DashboardUserTasks;
import br.com.seta.processo.dto.TaskProcess;
import br.com.seta.processo.dto.User;
import br.com.seta.processo.grafico.GraficoBarraVertical;
import br.com.seta.processo.interceptor.LogInterceptor;
import br.com.seta.processo.service.interfaces.DashboardService;
import br.com.seta.processo.service.interfaces.GestaoFilasService;

@Stateless(name = "DashboardService")
@Local(DashboardService.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
@Interceptors({LogInterceptor.class})

public class DashboardServiceImpl implements DashboardService {
	
	@Inject
	private GestaoFilasService gestaoFilasService;
	
	@Override
	public String dashboardGestaoFilas(String divId, User usuarioLogado, TaskProcess processoSelecionado, List<User> usersBPM) throws BonitaException {
		
		List<Object[]> resultado = new ArrayList<Object[]>();
		List<DashboardUserTasks> dashboardDatasource = new ArrayList<DashboardUserTasks>();
		
		dashboardDatasource = gestaoFilasService.retornaTotalAtividadesPorUsuario(usuarioLogado, processoSelecionado, usersBPM);
		
		for (DashboardUserTasks d : dashboardDatasource) {
			resultado.add(new Object[]{d.getTotalTasks(), d.getUser().getNomeCompleto()});
		}		
		
		GraficoBarraVertical vertical = new GraficoBarraVertical(divId, resultado, 1, 0, "Analista", "Quantidade");
		
		return vertical.gerarGrafico();
	}

	@Override
	public Object dashboardGestaoFilasPorAtor(User usuarioLogado, TaskProcess processoSelecionado, Actor atorSelecionado) throws BonitaException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object dashboardGestaoFilasPorUsuario(User usuarioLogado, TaskProcess processoSelecionado, Actor atorSelecionado, User usuarioSelecionado) throws BonitaException {
		// TODO Auto-generated method stub
		return null;
	}

	
}
