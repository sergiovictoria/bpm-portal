
package br.com.seta.processo.service.gestor.acesso.ws.consulta;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for listaPermissaoDTOResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="listaPermissaoDTOResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="usuario" type="{http://webservice.portal.seta.com.br/}dadosUsuarioDTO" minOccurs="0"/>
 *         &lt;element name="permissoes" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="permissao" type="{http://webservice.portal.seta.com.br/}permissaoTagDTO" maxOccurs="unbounded" minOccurs="0"/>
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
@XmlType(name = "listaPermissaoDTOResponse", propOrder = {
    "usuario",
    "permissoes"
})
public class ListaPermissaoDTOResponse {

    protected DadosUsuarioDTO usuario;
    protected ListaPermissaoDTOResponse.Permissoes permissoes;

    /**
     * Gets the value of the usuario property.
     * 
     * @return
     *     possible object is
     *     {@link DadosUsuarioDTO }
     *     
     */
    public DadosUsuarioDTO getUsuario() {
        return usuario;
    }

    /**
     * Sets the value of the usuario property.
     * 
     * @param value
     *     allowed object is
     *     {@link DadosUsuarioDTO }
     *     
     */
    public void setUsuario(DadosUsuarioDTO value) {
        this.usuario = value;
    }

    /**
     * Gets the value of the permissoes property.
     * 
     * @return
     *     possible object is
     *     {@link ListaPermissaoDTOResponse.Permissoes }
     *     
     */
    public ListaPermissaoDTOResponse.Permissoes getPermissoes() {
        return permissoes;
    }

    /**
     * Sets the value of the permissoes property.
     * 
     * @param value
     *     allowed object is
     *     {@link ListaPermissaoDTOResponse.Permissoes }
     *     
     */
    public void setPermissoes(ListaPermissaoDTOResponse.Permissoes value) {
        this.permissoes = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="permissao" type="{http://webservice.portal.seta.com.br/}permissaoTagDTO" maxOccurs="unbounded" minOccurs="0"/>
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

        protected List<PermissaoTagDTO> permissao;

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
         * {@link PermissaoTagDTO }
         * 
         * 
         */
        public List<PermissaoTagDTO> getPermissao() {
            if (permissao == null) {
                permissao = new ArrayList<PermissaoTagDTO>();
            }
            return this.permissao;
        }

    }

}
