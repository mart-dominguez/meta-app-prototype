/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.gov.santafe.meduc.relevamientos.infraestructura.service;

import ar.gov.santafe.meduc.relevamientos.infraestructura.modelo.Usuario;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author mdominguez
 */
@Stateless
public class UserServiceJPA implements UserService {

    @PersistenceContext(unitName = "infra_pu")
    EntityManager em;

    @Override
    public boolean validar(String user, String pass) {
        boolean valid = false;
        try {
            Usuario usr = (Usuario) em.createQuery("SELECT u FROM Usuario u WHERE u.usuario = :usrName AND u.passwordMd5 = :pwdMd5 ").setParameter("usrName", user).setParameter("pwdMd5", pass).getSingleResult();
            if (usr != null) {
                valid = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return valid;
    }

    private String issueToken(String username) {
        String x="";
        // Issue a token (can be a random String persisted to a database or a JWT token)
        // The issued token must be associated to a user
        // Return the issued token
        return "";
    }
}
