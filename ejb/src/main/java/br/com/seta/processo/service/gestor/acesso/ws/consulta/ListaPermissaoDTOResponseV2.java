
package br.com.seta.processo.service.gestor.acesso.ws.consulta;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de listaPermissaoDTOResponseV2 complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="listaPermissaoDTOResponseV2">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="usuario" type="{http://webservice.portal.seta.com.br/}dadosUsuarioDTOV2" minOccurs="0"/>
 *         &lt;element name="permissoes" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="permissao" type="{http://webservice.portal.seta.com.br/}permissaoTagDTOV2" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "listaPermissaoDTOResponseV2", propOrder = {
    "usuario",
    "permissoes"
})
public class ListaPermissaoDTOResponseV2 {

    protected DadosUsuarioDTOV2 usuario;
    protected ListaPermissaoDTOResponseV2 .Permissoes permissoes;

    /**
     * Obtém o valor da propriedade usuario.
     * 
     * @return
     *     possible object is
     *     {@link DadosUsuarioDTOV2 }
     *     
     */
    public DadosUsuarioDTOV2 getUsuario() {
        return usuario;
    }

    /**
     * Define o valor da propriedade usuario.
     * 
     * @param value
     *     allowed object is
     *     {@link DadosUsuarioDTOV2 }
     *     
     */
    public void setUsuario(DadosUsuarioDTOV2 value) {
        this.usuario = value;
    }

    /**
     * Obtém o valor da propriedade permissoes.
     * 
     * @return
     *     possible object is
     *     {@link ListaPermissaoDTOResponseV2 .Permissoes }
     *     
     */
    public ListaPermissaoDTOResponseV2 .Permissoes getPermissoes() {
        return permissoes;
    }

    /**
     * Define o valor da propriedade permissoes.
     * 
     * @param value
     *     allowed object is
     *     {@link ListaPermissaoDTOResponseV2 .Permissoes }
     *     
     */
    public void setPermissoes(ListaPermissaoDTOResponseV2 .Permissoes value) {
        this.permissoes = value;
    }


    /**
     * <p>Classe Java de anonymous complex type.
     * 
     * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="permissao" type="{http://webservice.portal.seta.com.br/}permissaoTagDTOV2" maxOccurs="unbounded" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "permissao"
    })
    public static class Permissoes {

        protected List<PermissaoTagDTOV2> permissao;

        /**
         * Gets the value of the permissao property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the permissao property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getPermissao().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link PermissaoTagDTOV2 }
         * 
         * 
         */
        public List<PermissaoTagDTOV2> getPermissao() {
            if (permissao == null) {
                permissao = new ArrayList<PermissaoTagDTOV2>();
            }
            return this.permissao;
        }

    }

}
