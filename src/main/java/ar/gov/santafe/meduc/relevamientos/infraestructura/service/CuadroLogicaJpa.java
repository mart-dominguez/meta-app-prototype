/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.gov.santafe.meduc.relevamientos.infraestructura.service;

import ar.gov.santafe.meduc.relevamientos.infraestructura.modelo.Cuadro;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author mdominguez
 */
@Stateless
public class CuadroLogicaJpa implements CuadroLogica{
    @PersistenceContext(unitName = "infra_pu")
    EntityManager em;
 
    @Override
    public Cuadro guardar(Cuadro c) {
        em.persist(c);
        em.flush();
        em.refresh(c);
        return c;
    }

    @Override
    public Cuadro get(Integer id) {
        return em.find(Cuadro.class,id);
    }


    @Override
    public List<Cuadro> listar() {
        return em.createQuery("SELECT c FROM Cuadro c").getResultList();
    }    
}
