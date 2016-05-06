/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.gov.santafe.meduc.relevamientos.infraestructura.service;

import java.security.Key;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.annotation.PostConstruct;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.mail.internet.MailDateFormat;
import org.jose4j.keys.AesKey;
import org.jose4j.lang.ByteUtil;

/**
 *
 * @author mdominguez
 */
@Singleton
public class TokenProvider {
    
    private Key key;
    private String token;
    private SimpleDateFormat sdf;
    
    @PostConstruct
    public void init(){
        key = new AesKey(ByteUtil.randomBytes(16));
        this.sdf = new SimpleDateFormat("YYYYMMMMMddHHmmsszzzz");
        this.token = sdf.format(Calendar.getInstance().getTime());
    }

    @Lock(LockType.READ)
    public Key getKey(){
        return key;
    }
    
    @Lock(LockType.READ)
    public String getDigestToken(){
        return token;
    }
}
