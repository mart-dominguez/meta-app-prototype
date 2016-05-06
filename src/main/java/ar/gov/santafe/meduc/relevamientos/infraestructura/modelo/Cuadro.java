/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.gov.santafe.meduc.relevamientos.infraestructura.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author mdominguez
 */
@Entity
@Table(name = "INFRA_CUADRO")
public class Cuadro {
    @Column(name="ID_CUADRO")
    @Id
    @GeneratedValue(generator = "SEQ_CUADRO")
    @SequenceGenerator(name = "SEQ_CUADRO", sequenceName = "SEQ_CUADRO", allocationSize = 1)
    private Integer id;

	    
    @Column(name="TITULO")
    private String titulo;
    
    @Lob
    @Column(name="CONTENIDO")
    private String datos;
 
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDatos() {
        return datos;
    }

    public void setDatos(String datos) {
        this.datos = datos;
    }
}