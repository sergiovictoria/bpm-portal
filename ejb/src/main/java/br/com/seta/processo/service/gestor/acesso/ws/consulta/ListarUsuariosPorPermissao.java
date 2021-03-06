
package br.com.seta.processo.service.gestor.acesso.ws.consulta;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de listarUsuariosPorPermissao complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="listarUsuariosPorPermissao">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="permissao" type="{http://webservice.portal.seta.com.br/}permissaoTagDTO" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "listarUsuariosPorPermissao", propOrder = {
    "permissao"
})
public class ListarUsuariosPorPermissao {

    protected PermissaoTagDTO permissao;

    /**
     * Obtém o valor da propriedade permissao.
     * 
     * @return
     *     possible object is
     *     {@link PermissaoTagDTO }
     *     
     */
    public PermissaoTagDTO getPermissao() {
        return permissao;
    }

    /**
     * Define o valor da propriedade permissao.
     * 
     * @param value
     *     allowed object is
     *     {@link PermissaoTagDTO }
     *     
     */
    public void setPermissao(PermissaoTagDTO value) {
        this.permissao = value;
    }

}
