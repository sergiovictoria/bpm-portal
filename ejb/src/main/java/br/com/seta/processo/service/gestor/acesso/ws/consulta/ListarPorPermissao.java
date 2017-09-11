
package br.com.seta.processo.service.gestor.acesso.ws.consulta;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de listarPorPermissao complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="listarPorPermissao">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="permissoes" type="{http://webservice.portal.seta.com.br/}listaPermissaoDTORequest" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "listarPorPermissao", propOrder = {
    "permissoes"
})
public class ListarPorPermissao {

    protected ListaPermissaoDTORequest permissoes;

    /**
     * Obtém o valor da propriedade permissoes.
     * 
     * @return
     *     possible object is
     *     {@link ListaPermissaoDTORequest }
     *     
     */
    public ListaPermissaoDTORequest getPermissoes() {
        return permissoes;
    }

    /**
     * Define o valor da propriedade permissoes.
     * 
     * @param value
     *     allowed object is
     *     {@link ListaPermissaoDTORequest }
     *     
     */
    public void setPermissoes(ListaPermissaoDTORequest value) {
        this.permissoes = value;
    }

}
