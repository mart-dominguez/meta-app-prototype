/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.gov.santafe.meduc.relevamientos.infraestructura.security;

import org.jose4j.lang.JoseException;

/**
 *
 * @author mdominguez
 */

public interface JsonWebToken {
    public String crearDigesto(String entrada) throws JoseException;
    public String verificarDigesto(String digesto) ;
}
