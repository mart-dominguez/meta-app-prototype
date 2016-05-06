/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.gov.santafe.meduc.relevamientos.infraestructura.security;

import ar.gov.santafe.meduc.relevamientos.infraestructura.service.TokenProvider;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jose4j.jwe.ContentEncryptionAlgorithmIdentifiers;
import org.jose4j.jwe.JsonWebEncryption;
import org.jose4j.jwe.KeyManagementAlgorithmIdentifiers;
import org.jose4j.keys.AesKey;
import org.jose4j.lang.ByteUtil;
import org.jose4j.lang.JoseException;
import java.security.Key;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Stateless;

/**
 *
 * @author mdominguez
 */
@Stateless
public class JsonWebTokenJOSE implements JsonWebToken{

   @EJB TokenProvider tknProvider;

    public String crearDigesto(String entrada) throws JoseException {
        String ret = null;
        try {
            JsonWebEncryption jwe = new JsonWebEncryption();
            jwe.setPayload(entrada);
            jwe.setAlgorithmHeaderValue(KeyManagementAlgorithmIdentifiers.A128KW);
            jwe.setEncryptionMethodHeaderParameter(ContentEncryptionAlgorithmIdentifiers.AES_128_CBC_HMAC_SHA_256);
            jwe.setKey(tknProvider.getKey());
            ret = jwe.getCompactSerialization();
        } catch (JoseException ex) {
            Logger.getLogger(JsonWebTokenJOSE.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        return ret;
    }

    public String verificarDigesto(String digesto) {
        String user[] = null;
        String carga = null;
        try {
            JsonWebEncryption jwe = new JsonWebEncryption();
            jwe.setKey(tknProvider.getKey());
            jwe.setCompactSerialization(digesto);
            carga = jwe.getPayload();
            user= carga.split("\\|");
            
            System.out.println("Payload: " + carga );            
            System.out.println("User: " + user );
        } catch (JoseException ex) {
            Logger.getLogger(JsonWebTokenJOSE.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        return user[0];
    }
    
      

}
