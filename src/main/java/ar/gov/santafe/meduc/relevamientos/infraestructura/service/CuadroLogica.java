/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.gov.santafe.meduc.relevamientos.infraestructura.service;

import ar.gov.santafe.meduc.relevamientos.infraestructura.modelo.Cuadro;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author mdominguez
 */
@Local
public interface CuadroLogica {
    public Cuadro guardar(Cuadro c);
    public Cuadro get(Integer id);
    public List<Cuadro> listar();

}
