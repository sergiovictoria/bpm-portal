
package br.com.seta.processo.service.gestor.acesso.ws.consulta;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebService(name = "ConsultaPermissaoWS", targetNamespace = "http://webservice.portal.seta.com.br/")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface ConsultaPermissaoWS {


    /**
     * 
     * @param parameters
     * @param acesso
     * @return
     *     returns br.com.seta.processo.service.gestor.acesso.ws.consulta.ListarResponse
     * @throws BusinessException
     * @throws AutorizacaoException
     */
    @WebMethod
    @WebResult(name = "listarResponse", targetNamespace = "http://webservice.portal.seta.com.br/", partName = "parameters")
    public ListarResponse listar(
        @WebParam(name = "listar", targetNamespace = "http://webservice.portal.seta.com.br/", partName = "parameters")
        Listar parameters,
        @WebParam(name = "acesso", targetNamespace = "http://webservice.portal.seta.com.br/", header = true, partName = "acesso")
        AcessoDTO acesso)
        throws AutorizacaoException, BusinessException
    ;

    /**
     * 
     * @param parameters
     * @param acesso
     * @return
     *     returns br.com.seta.processo.service.gestor.acesso.ws.consulta.ValidarAcessoResponse
     * @throws BusinessException
     * @throws AutorizacaoException
     */
    @WebMethod
    @WebResult(name = "validarAcessoResponse", targetNamespace = "http://webservice.portal.seta.com.br/", partName = "parameters")
    public ValidarAcessoResponse validarAcesso(
        @WebParam(name = "validarAcesso", targetNamespace = "http://webservice.portal.seta.com.br/", partName = "parameters")
        ValidarAcesso parameters,
        @WebParam(name = "acesso", targetNamespace = "http://webservice.portal.seta.com.br/", header = true, partName = "acesso")
        AcessoDTO acesso)
        throws AutorizacaoException, BusinessException
    ;

    /**
     * 
     * @param parameters
     * @param acesso
     * @return
     *     returns br.com.seta.processo.service.gestor.acesso.ws.consulta.ListarLojaResponse
     * @throws BusinessException
     * @throws AutorizacaoException
     */
    @WebMethod
    @WebResult(name = "listarLojaResponse", targetNamespace = "http://webservice.portal.seta.com.br/", partName = "parameters")
    public ListarLojaResponse listarLoja(
        @WebParam(name = "listarLoja", targetNamespace = "http://webservice.portal.seta.com.br/", partName = "parameters")
        ListarLoja parameters,
        @WebParam(name = "acesso", targetNamespace = "http://webservice.portal.seta.com.br/", header = true, partName = "acesso")
        AcessoDTO acesso)
        throws AutorizacaoException, BusinessException
    ;

    /**
     * 
     * @param parameters
     * @param acesso
     * @return
     *     returns br.com.seta.processo.service.gestor.acesso.ws.consulta.ListarUsuariosPorPermissaoResponse
     * @throws BusinessException
     * @throws AutorizacaoException
     */
    @WebMethod
    @WebResult(name = "listarUsuariosPorPermissaoResponse", targetNamespace = "http://webservice.portal.seta.com.br/", partName = "parameters")
    public ListarUsuariosPorPermissaoResponse listarUsuariosPorPermissao(
        @WebParam(name = "listarUsuariosPorPermissao", targetNamespace = "http://webservice.portal.seta.com.br/", partName = "parameters")
        ListarUsuariosPorPermissao parameters,
        @WebParam(name = "acesso", targetNamespace = "http://webservice.portal.seta.com.br/", header = true, partName = "acesso")
        AcessoDTO acesso)
        throws AutorizacaoException, BusinessException
    ;

    /**
     * 
     * @param parameters
     * @param acesso
     * @return
     *     returns br.com.seta.processo.service.gestor.acesso.ws.consulta.ListarPorPermissaoResponse
     * @throws BusinessException
     * @throws AutorizacaoException
     */
    @WebMethod
    @WebResult(name = "listarPorPermissaoResponse", targetNamespace = "http://webservice.portal.seta.com.br/", partName = "parameters")
    public ListarPorPermissaoResponse listarPorPermissao(
        @WebParam(name = "listarPorPermissao", targetNamespace = "http://webservice.portal.seta.com.br/", partName = "parameters")
        ListarPorPermissao parameters,
        @WebParam(name = "acesso", targetNamespace = "http://webservice.portal.seta.com.br/", header = true, partName = "acesso")
        AcessoDTO acesso)
        throws AutorizacaoException, BusinessException
    ;

}