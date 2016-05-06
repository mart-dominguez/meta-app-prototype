/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.gov.santafe.meduc.relevamientos.infraestructura.security;

/**
 *
 * @author mdominguez
 */
public class Credentials {
 
    private String userName;
    private String claveMd5;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getClaveMd5() {
        return claveMd5;
    }

    public void setClaveMd5(String claveMd5) {
        this.claveMd5 = claveMd5;
    }
    
}
