/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.gov.santafe.meduc.relevamientos.infraestructura.service;

import javax.ejb.Local;

/**
 *
 * @author mdominguez
 */
@Local
public interface UserService {
    public boolean validar(String user,String pass);
}
