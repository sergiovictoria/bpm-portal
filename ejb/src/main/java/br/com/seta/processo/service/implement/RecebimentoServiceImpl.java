package br.com.seta.processo.service.implement;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.interceptor.Interceptors;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.http.ParseException;
import org.bonitasoft.engine.exception.BonitaException;
import org.bonitasoft.engine.identity.GroupCriterion;
import org.bonitasoft.engine.identity.UserCriterion;

import br.com.seta.processo.bean.MapInstanciaProcesso;
import br.com.seta.processo.dto.Group;
import br.com.seta.processo.dto.OrRequisicao;
import br.com.seta.processo.dto.User;
import br.com.seta.processo.exceptions.HttpStatus401Exception;
import br.com.seta.processo.exceptions.HttpStatusException;
import br.com.seta.processo.interceptor.LogInterceptor;
import br.com.seta.processo.service.ExecuteRestAPI;
import br.com.seta.processo.service.GroupService;
import br.com.seta.processo.service.interfaces.RecebimentoService;



@Stateless(name="RecebimentoService")
@Local(RecebimentoService.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
@Interceptors({LogInterceptor.class})
public class RecebimentoServiceImpl implements RecebimentoService {


	@Inject	private ExecuteRestAPI restApi;
	@Inject	private GroupService groupService;
	@Inject private MapInstanciaProcesso mapInstanciaProcesso;

	/**
	 * Constructor
	 */
	public RecebimentoServiceImpl() {
	}

	@Override
	public List<OrRequisicao> listaRequisicoes(User user) throws BonitaException {
		boolean userAdm = false;
		List<OrRequisicao> retorno = new ArrayList<OrRequisicao>();
		try {
			userAdm = verificaPermissaoUsuario(user, userAdm);
			if (userAdm) {
				retorno = (List<OrRequisicao>) mapInstanciaProcesso.listaOrRequisicaoRecebimento();
			} else {
				retorno = listaRequisicoesPorGrupo();
			}
		} catch (ParseException | IOException | HttpStatusException e) {
			e.printStackTrace();
		}
		return retorno;
	}

	@Override
	public void receberPedidoSelecionado(OrRequisicao requisicao) throws BonitaException {
		mapInstanciaProcesso.update(requisicao.getSeqrequisicao());
	}

	private List<OrRequisicao> listaRequisicoesPorGrupo() throws BonitaException {
		List<OrRequisicao> requisicoes = new ArrayList<OrRequisicao>();
		List<Group> grupos;
		try {
			grupos = listaGrupos();
			for (Group grupo : grupos) {
				String grupoRecebimento = grupo.getId() + " - " + grupo.getName();
				requisicoes = (List<OrRequisicao>) mapInstanciaProcesso.listaRequisicoesPorGrupoS(grupoRecebimento);
			}
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		return requisicoes;
	}

	private List<Group> listaGrupos() throws BonitaException, IllegalAccessException, InvocationTargetException {
		List<Group> listaGrupo = new ArrayList<Group>();
		List<org.bonitasoft.engine.identity.Group> grupos = groupService.findGroups(0, 99999, GroupCriterion.NAME_ASC);
		for (org.bonitasoft.engine.identity.Group grupo : grupos) {
			if ("/Seta/Check - In".equals(grupo.getParentPath()) || "CSC Validação".equals(grupo.getName())) {
				Group grp = new Group();
				BeanUtils.copyProperties(grp, grupo);
				listaGrupo.add((Group) grp);
			}
		}
		return listaGrupo;
	}

	/**
	 * @param user
	 * @param userAdm
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 * @throws HttpStatus401Exception
	 * @throws HttpStatusException
	 * @throws BonitaException
	 */
	private boolean verificaPermissaoUsuario(User user, boolean userAdm)
			throws ParseException, IOException, HttpStatus401Exception, HttpStatusException, BonitaException {
		List<Group> grupos = restApi.retriveGroupList(user, 0, 99999);
		for (Group g : grupos) {
			if ("/Seta/Check - In ADM".equals(g.getParent_path())) {
				List<org.bonitasoft.engine.identity.User> users = restApi.findUserForGroups(user, g.getId(), 0, 100,
						UserCriterion.USER_NAME_ASC);
				for (org.bonitasoft.engine.identity.User u : users) {
					if (user.getUserName().equalsIgnoreCase(u.getUserName())) {
						userAdm = true;
					}
				}
			}
		}
		return userAdm;
	}

}
