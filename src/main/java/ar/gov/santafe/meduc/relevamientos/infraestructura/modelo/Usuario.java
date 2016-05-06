package ar.gov.santafe.meduc.relevamientos.infraestructura.modelo;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "AD_USUARIO")
public class Usuario implements java.io.Serializable {

	@Column(name = "APELLIDO")
	private String apellido;

	@Column(name = "NOMBRE")
	private String nombre;

	@Column(name = "PASSWORD")
	private String password;

	@Column(name = "PASS_ENC")
	private String passwordMd5;

	@Id
	@Column(name = "ID_USUARIO")
	private Long id;

	@Column(name = "USUARIO")
	private String usuario;

	@Column(name = "EMAIL_PARTICULAR")
	private String emailParticular;

	@Column(name = "EMAIL_OFICIAL")
	private String emailOficial;

	@Column(name = "NRO_DOCUMENTO")
	private Long nroDocumento;

	@Column(name = "USUARIO_LOGUEADO")
	private String usuarioLogueado;
	
	@Column(name = "FECHA_OPERACION")
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date fechaOperacion;

	@Column(name = "SEXO")
	private String sexo;

	@Column(name = "FECHA_CAMBIO_CLAVE")
	@Temporal(value = TemporalType.DATE)
	private Date fechaCambioClave;
	
	@Column(name = "FECHA_ALTA")
	@Temporal(value = TemporalType.DATE)
	private Date fechaAlta;
	
	@Column(name = "FECHA_HASTA")
	@Temporal(value = TemporalType.DATE)
	private Date fechaHasta;
	
	
	@Column(name = "FUNCIONES")
	private String funciones;
	
	@Column(name = "TELEFONO")
	private String telefono;
	
	@Column(name = "DOCUMENTO")
	private String documento;
	
	@Column(name = "CENTREX")
	private String centrex;
	
	@Column(name = "FECHA_NACIMIENTO")
	@Temporal(value = TemporalType.DATE)
	private Date fechaNacimiento;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPassword() {
		return password;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String toString() {
		return getId() + "";
	}

	public String getEmailParticular() {
		return emailParticular;
	}

	public void setEmailParticular(String emailParticular) {
		this.emailParticular = emailParticular;
	}

	public String getEmailOficial() {
		return emailOficial;
	}

	public void setEmailOficial(String emailOficial) {
		this.emailOficial = emailOficial;
	}

	public Long getNroDocumento() {
		return nroDocumento;
	}

	public void setNroDocumento(Long nroDocumento) {
		this.nroDocumento = nroDocumento;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public Date getFechaCambioClave() {
		return fechaCambioClave;
	}

	public void setFechaCambioClave(Date fechaCambioClave) {
		this.fechaCambioClave = fechaCambioClave;
	}

	public String getUsuarioLogueado() {
		return usuarioLogueado;
	}

	public void setUsuarioLogueado(String usuarioLogueado) {
		this.usuarioLogueado = usuarioLogueado;
	}

	public Date getFechaOperacion() {
		return fechaOperacion;
	}

	public void setFechaOperacion(Date fechaOperacion) {
		this.fechaOperacion = fechaOperacion;
	}	

	public String getPasswordMd5() {
		return passwordMd5;
	}

	public void setPasswordMd5(String passwordMd5) {
		this.passwordMd5 = passwordMd5;
	}
	
	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public Date getFechaHasta() {
		return fechaHasta;
	}

	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}
	
	public String getFunciones() {
		return funciones;
	}

	public void setFunciones(String funciones) {
		this.funciones = funciones;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getCentrex() {
		return centrex;
	}

	public void setCentrex(String centrex) {
		this.centrex = centrex;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	@Override
	public boolean equals(Object otroObjeto) {
		if (id == null) return false;
		if (otroObjeto == null || !(otroObjeto instanceof Usuario)) return false;
		Usuario otroUsr = (Usuario) otroObjeto;
		return this.id.equals(otroUsr.getId());
	}
	
	@Override
    public int hashCode() {
		return id != null ? this.getClass().hashCode() + id.hashCode() : super.hashCode();
    }

}