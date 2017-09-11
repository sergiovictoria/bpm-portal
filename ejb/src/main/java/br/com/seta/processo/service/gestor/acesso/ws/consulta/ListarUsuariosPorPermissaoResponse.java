
package br.com.seta.processo.service.gestor.acesso.ws.consulta;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de listarUsuariosPorPermissaoResponse complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="listarUsuariosPorPermissaoResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="usuarios" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="usuario" type="{http://webservice.portal.seta.com.br/}dadosUsuarioDTOV2" maxOccurs="unbounded" minOccurs="0"/>
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
@XmlType(name = "listarUsuariosPorPermissaoResponse", propOrder = {
    "usuarios"
})
public class ListarUsuariosPorPermissaoResponse {

    protected ListarUsuariosPorPermissaoResponse.Usuarios usuarios;

    /**
     * Obtém o valor da propriedade usuarios.
     * 
     * @return
     *     possible object is
     *     {@link ListarUsuariosPorPermissaoResponse.Usuarios }
     *     
     */
    public ListarUsuariosPorPermissaoResponse.Usuarios getUsuarios() {
        return usuarios;
    }

    /**
     * Define o valor da propriedade usuarios.
     * 
     * @param value
     *     allowed object is
     *     {@link ListarUsuariosPorPermissaoResponse.Usuarios }
     *     
     */
    public void setUsuarios(ListarUsuariosPorPermissaoResponse.Usuarios value) {
        this.usuarios = value;
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
     *         &lt;element name="usuario" type="{http://webservice.portal.seta.com.br/}dadosUsuarioDTOV2" maxOccurs="unbounded" minOccurs="0"/>
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
        "usuario"
    })
    public static class Usuarios {

        protected List<DadosUsuarioDTOV2> usuario;

        /**
         * Gets the value of the usuario property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the usuario property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getUsuario().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link DadosUsuarioDTOV2 }
         * 
         * 
         */
        public List<DadosUsuarioDTOV2> getUsuario() {
            if (usuario == null) {
                usuario = new ArrayList<DadosUsuarioDTOV2>();
            }
            return this.usuario;
        }

    }

}
