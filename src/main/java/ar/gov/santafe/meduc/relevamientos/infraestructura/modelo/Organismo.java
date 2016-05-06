/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.gov.santafe.meduc.relevamientos.infraestructura.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author mdominguez
 */
@Entity
@Table(name = "LO_ORGANISMOS" )
public class Organismo {
    @Id
    private Integer id;
    @Column(name = "SNEP")
    private Long snep;
    @Column(name = "NOM_ORGAN_TITULO")
    private String nomOrganTitulo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getSnep() {
        return snep;
    }

    public void setSnep(Long snep) {
        this.snep = snep;
    }

    public String getNomOrganTitulo() {
        return nomOrganTitulo;
    }

    public void setNomOrganTitulo(String nomOrganTitulo) {
        this.nomOrganTitulo = nomOrganTitulo;
    }

}
