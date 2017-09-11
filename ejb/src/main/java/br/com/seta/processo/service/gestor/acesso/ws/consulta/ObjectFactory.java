
package br.com.seta.processo.service.gestor.acesso.ws.consulta;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the br.com.seta.processo.service.gestor.acesso.ws.consulta package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Acesso_QNAME = new QName("http://webservice.portal.seta.com.br/", "acesso");
    private final static QName _PermissaoTagDTOV2_QNAME = new QName("http://webservice.portal.seta.com.br/", "permissaoTagDTOV2");
    private final static QName _ListaPermissaoDTORequest_QNAME = new QName("http://webservice.portal.seta.com.br/", "listaPermissaoDTORequest");
    private final static QName _ListarResponse_QNAME = new QName("http://webservice.portal.seta.com.br/", "listarResponse");
    private final static QName _ListaPermissaoDTOResponseV2_QNAME = new QName("http://webservice.portal.seta.com.br/", "listaPermissaoDTOResponseV2");
    private final static QName _SetaFault_QNAME = new QName("http://webservice.portal.seta.com.br/", "SetaFault");
    private final static QName _ValidarAcessoResponse_QNAME = new QName("http://webservice.portal.seta.com.br/", "validarAcessoResponse");
    private final static QName _DadosUsuarioDTOV2_QNAME = new QName("http://webservice.portal.seta.com.br/", "dadosUsuarioDTOV2");
    private final static QName _ListarPorPermissaoResponse_QNAME = new QName("http://webservice.portal.seta.com.br/", "listarPorPermissaoResponse");
    private final static QName _PermissaoTagDTO_QNAME = new QName("http://webservice.portal.seta.com.br/", "permissaoTagDTO");
    private final static QName _Listar_QNAME = new QName("http://webservice.portal.seta.com.br/", "listar");
    private final static QName _ListarPorPermissao_QNAME = new QName("http://webservice.portal.seta.com.br/", "listarPorPermissao");
    private final static QName _ListarUsuariosPorPermissao_QNAME = new QName("http://webservice.portal.seta.com.br/", "listarUsuariosPorPermissao");
    private final static QName _ListarLojaResponse_QNAME = new QName("http://webservice.portal.seta.com.br/", "listarLojaResponse");
    private final static QName _ListarFuncionalidadeResponse_QNAME = new QName("http://webservice.portal.seta.com.br/", "listarFuncionalidadeResponse");
    private final static QName _ListarUsuariosPorPermissaoResponse_QNAME = new QName("http://webservice.portal.seta.com.br/", "listarUsuariosPorPermissaoResponse");
    private final static QName _ListarLoja_QNAME = new QName("http://webservice.portal.seta.com.br/", "listarLoja");
    private final static QName _ListarFuncionalidade_QNAME = new QName("http://webservice.portal.seta.com.br/", "listarFuncionalidade");
    private final static QName _ValidarAcesso_QNAME = new QName("http://webservice.portal.seta.com.br/", "validarAcesso");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: br.com.seta.processo.service.gestor.acesso.ws.consulta
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ListarLojaResponse }
     * 
     */
    public ListarLojaResponse createListarLojaResponse() {
        return new ListarLojaResponse();
    }

    /**
     * Create an instance of {@link ListarFuncionalidadeResponse }
     * 
     */
    public ListarFuncionalidadeResponse createListarFuncionalidadeResponse() {
        return new ListarFuncionalidadeResponse();
    }

    /**
     * Create an instance of {@link ListarUsuariosPorPermissaoResponse }
     * 
     */
    public ListarUsuariosPorPermissaoResponse createListarUsuariosPorPermissaoResponse() {
        return new ListarUsuariosPorPermissaoResponse();
    }

    /**
     * Create an instance of {@link ListaPermissaoDTOResponseV2 }
     * 
     */
    public ListaPermissaoDTOResponseV2 createListaPermissaoDTOResponseV2() {
        return new ListaPermissaoDTOResponseV2();
    }

    /**
     * Create an instance of {@link ValidarAcessoResponse }
     * 
     */
    public ValidarAcessoResponse createValidarAcessoResponse() {
        return new ValidarAcessoResponse();
    }

    /**
     * Create an instance of {@link DadosUsuarioDTOV2 }
     * 
     */
    public DadosUsuarioDTOV2 createDadosUsuarioDTOV2() {
        return new DadosUsuarioDTOV2();
    }

    /**
     * Create an instance of {@link ListarPorPermissaoResponse }
     * 
     */
    public ListarPorPermissaoResponse createListarPorPermissaoResponse() {
        return new ListarPorPermissaoResponse();
    }

    /**
     * Create an instance of {@link PermissaoTagDTO }
     * 
     */
    public PermissaoTagDTO createPermissaoTagDTO() {
        return new PermissaoTagDTO();
    }

    /**
     * Create an instance of {@link ListaPermissaoDTORequest }
     * 
     */
    public ListaPermissaoDTORequest createListaPermissaoDTORequest() {
        return new ListaPermissaoDTORequest();
    }

    /**
     * Create an instance of {@link ListarResponse }
     * 
     */
    public ListarResponse createListarResponse() {
        return new ListarResponse();
    }

    /**
     * Create an instance of {@link PermissaoTagDTOV2 }
     * 
     */
    public PermissaoTagDTOV2 createPermissaoTagDTOV2() {
        return new PermissaoTagDTOV2();
    }

    /**
     * Create an instance of {@link AcessoDTO }
     * 
     */
    public AcessoDTO createAcessoDTO() {
        return new AcessoDTO();
    }

    /**
     * Create an instance of {@link ListarLoja }
     * 
     */
    public ListarLoja createListarLoja() {
        return new ListarLoja();
    }

    /**
     * Create an instance of {@link ListarFuncionalidade }
     * 
     */
    public ListarFuncionalidade createListarFuncionalidade() {
        return new ListarFuncionalidade();
    }

    /**
     * Create an instance of {@link ValidarAcesso }
     * 
     */
    public ValidarAcesso createValidarAcesso() {
        return new ValidarAcesso();
    }

    /**
     * Create an instance of {@link Listar }
     * 
     */
    public Listar createListar() {
        return new Listar();
    }

    /**
     * Create an instance of {@link ListarPorPermissao }
     * 
     */
    public ListarPorPermissao createListarPorPermissao() {
        return new ListarPorPermissao();
    }

    /**
     * Create an instance of {@link ListarUsuariosPorPermissao }
     * 
     */
    public ListarUsuariosPorPermissao createListarUsuariosPorPermissao() {
        return new ListarUsuariosPorPermissao();
    }

    /**
     * Create an instance of {@link LojaDTO }
     * 
     */
    public LojaDTO createLojaDTO() {
        return new LojaDTO();
    }

    /**
     * Create an instance of {@link ListarLojaResponse.Lojas }
     * 
     */
    public ListarLojaResponse.Lojas createListarLojaResponseLojas() {
        return new ListarLojaResponse.Lojas();
    }

    /**
     * Create an instance of {@link ListarFuncionalidadeResponse.Permissoes }
     * 
     */
    public ListarFuncionalidadeResponse.Permissoes createListarFuncionalidadeResponsePermissoes() {
        return new ListarFuncionalidadeResponse.Permissoes();
    }

    /**
     * Create an instance of {@link ListarUsuariosPorPermissaoResponse.Usuarios }
     * 
     */
    public ListarUsuariosPorPermissaoResponse.Usuarios createListarUsuariosPorPermissaoResponseUsuarios() {
        return new ListarUsuariosPorPermissaoResponse.Usuarios();
    }

    /**
     * Create an instance of {@link ListaPermissaoDTOResponseV2 .Permissoes }
     * 
     */
    public ListaPermissaoDTOResponseV2 .Permissoes createListaPermissaoDTOResponseV2Permissoes() {
        return new ListaPermissaoDTOResponseV2 .Permissoes();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AcessoDTO }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.portal.seta.com.br/", name = "acesso")
    public JAXBElement<AcessoDTO> createAcesso(AcessoDTO value) {
        return new JAXBElement<AcessoDTO>(_Acesso_QNAME, AcessoDTO.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PermissaoTagDTOV2 }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.portal.seta.com.br/", name = "permissaoTagDTOV2")
    public JAXBElement<PermissaoTagDTOV2> createPermissaoTagDTOV2(PermissaoTagDTOV2 value) {
        return new JAXBElement<PermissaoTagDTOV2>(_PermissaoTagDTOV2_QNAME, PermissaoTagDTOV2 .class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListaPermissaoDTORequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.portal.seta.com.br/", name = "listaPermissaoDTORequest")
    public JAXBElement<ListaPermissaoDTORequest> createListaPermissaoDTORequest(ListaPermissaoDTORequest value) {
        return new JAXBElement<ListaPermissaoDTORequest>(_ListaPermissaoDTORequest_QNAME, ListaPermissaoDTORequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListarResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.portal.seta.com.br/", name = "listarResponse")
    public JAXBElement<ListarResponse> createListarResponse(ListarResponse value) {
        return new JAXBElement<ListarResponse>(_ListarResponse_QNAME, ListarResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListaPermissaoDTOResponseV2 }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.portal.seta.com.br/", name = "listaPermissaoDTOResponseV2")
    public JAXBElement<ListaPermissaoDTOResponseV2> createListaPermissaoDTOResponseV2(ListaPermissaoDTOResponseV2 value) {
        return new JAXBElement<ListaPermissaoDTOResponseV2>(_ListaPermissaoDTOResponseV2_QNAME, ListaPermissaoDTOResponseV2 .class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.portal.seta.com.br/", name = "SetaFault")
    public JAXBElement<String> createSetaFault(String value) {
        return new JAXBElement<String>(_SetaFault_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ValidarAcessoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.portal.seta.com.br/", name = "validarAcessoResponse")
    public JAXBElement<ValidarAcessoResponse> createValidarAcessoResponse(ValidarAcessoResponse value) {
        return new JAXBElement<ValidarAcessoResponse>(_ValidarAcessoResponse_QNAME, ValidarAcessoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DadosUsuarioDTOV2 }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.portal.seta.com.br/", name = "dadosUsuarioDTOV2")
    public JAXBElement<DadosUsuarioDTOV2> createDadosUsuarioDTOV2(DadosUsuarioDTOV2 value) {
        return new JAXBElement<DadosUsuarioDTOV2>(_DadosUsuarioDTOV2_QNAME, DadosUsuarioDTOV2 .class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListarPorPermissaoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.portal.seta.com.br/", name = "listarPorPermissaoResponse")
    public JAXBElement<ListarPorPermissaoResponse> createListarPorPermissaoResponse(ListarPorPermissaoResponse value) {
        return new JAXBElement<ListarPorPermissaoResponse>(_ListarPorPermissaoResponse_QNAME, ListarPorPermissaoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PermissaoTagDTO }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.portal.seta.com.br/", name = "permissaoTagDTO")
    public JAXBElement<PermissaoTagDTO> createPermissaoTagDTO(PermissaoTagDTO value) {
        return new JAXBElement<PermissaoTagDTO>(_PermissaoTagDTO_QNAME, PermissaoTagDTO.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Listar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.portal.seta.com.br/", name = "listar")
    public JAXBElement<Listar> createListar(Listar value) {
        return new JAXBElement<Listar>(_Listar_QNAME, Listar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListarPorPermissao }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.portal.seta.com.br/", name = "listarPorPermissao")
    public JAXBElement<ListarPorPermissao> createListarPorPermissao(ListarPorPermissao value) {
        return new JAXBElement<ListarPorPermissao>(_ListarPorPermissao_QNAME, ListarPorPermissao.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListarUsuariosPorPermissao }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.portal.seta.com.br/", name = "listarUsuariosPorPermissao")
    public JAXBElement<ListarUsuariosPorPermissao> createListarUsuariosPorPermissao(ListarUsuariosPorPermissao value) {
        return new JAXBElement<ListarUsuariosPorPermissao>(_ListarUsuariosPorPermissao_QNAME, ListarUsuariosPorPermissao.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListarLojaResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.portal.seta.com.br/", name = "listarLojaResponse")
    public JAXBElement<ListarLojaResponse> createListarLojaResponse(ListarLojaResponse value) {
        return new JAXBElement<ListarLojaResponse>(_ListarLojaResponse_QNAME, ListarLojaResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListarFuncionalidadeResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.portal.seta.com.br/", name = "listarFuncionalidadeResponse")
    public JAXBElement<ListarFuncionalidadeResponse> createListarFuncionalidadeResponse(ListarFuncionalidadeResponse value) {
        return new JAXBElement<ListarFuncionalidadeResponse>(_ListarFuncionalidadeResponse_QNAME, ListarFuncionalidadeResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListarUsuariosPorPermissaoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.portal.seta.com.br/", name = "listarUsuariosPorPermissaoResponse")
    public JAXBElement<ListarUsuariosPorPermissaoResponse> createListarUsuariosPorPermissaoResponse(ListarUsuariosPorPermissaoResponse value) {
        return new JAXBElement<ListarUsuariosPorPermissaoResponse>(_ListarUsuariosPorPermissaoResponse_QNAME, ListarUsuariosPorPermissaoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListarLoja }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.portal.seta.com.br/", name = "listarLoja")
    public JAXBElement<ListarLoja> createListarLoja(ListarLoja value) {
        return new JAXBElement<ListarLoja>(_ListarLoja_QNAME, ListarLoja.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListarFuncionalidade }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.portal.seta.com.br/", name = "listarFuncionalidade")
    public JAXBElement<ListarFuncionalidade> createListarFuncionalidade(ListarFuncionalidade value) {
        return new JAXBElement<ListarFuncionalidade>(_ListarFuncionalidade_QNAME, ListarFuncionalidade.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ValidarAcesso }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.portal.seta.com.br/", name = "validarAcesso")
    public JAXBElement<ValidarAcesso> createValidarAcesso(ValidarAcesso value) {
        return new JAXBElement<ValidarAcesso>(_ValidarAcesso_QNAME, ValidarAcesso.class, null, value);
    }

}
