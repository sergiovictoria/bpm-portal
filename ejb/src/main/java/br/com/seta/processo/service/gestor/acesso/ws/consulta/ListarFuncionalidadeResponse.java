
package br.com.seta.processo.service.gestor.acesso.ws.consulta;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de listarFuncionalidadeResponse complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="listarFuncionalidadeResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
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
@XmlType(name = "listarFuncionalidadeResponse", propOrder = {
    "permissoes"
})
public class ListarFuncionalidadeResponse {

    protected ListarFuncionalidadeResponse.Permissoes permissoes;

    /**
     * Obtém o valor da propriedade permissoes.
     * 
     * @return
     *     possible object is
     *     {@link ListarFuncionalidadeResponse.Permissoes }
     *     
     */
    public ListarFuncionalidadeResponse.Permissoes getPermissoes() {
        return permissoes;
    }

    /**
     * Define o valor da propriedade permissoes.
     * 
     * @param value
     *     allowed object is
     *     {@link ListarFuncionalidadeResponse.Permissoes }
     *     
     */
    public void setPermissoes(ListarFuncionalidadeResponse.Permissoes value) {
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
