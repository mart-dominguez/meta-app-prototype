/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.gov.santafe.meduc.relevamientos.infraestructura.security;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.regex.Pattern;
import javax.ejb.EJB;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author mdominguez
 */
public class SecurityTokenFilter implements Filter {
    
    @EJB
    JsonWebToken jwt;
    
 @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //*jwtVerifier = new JWTVerifier(new Base64(true).decode("YOUR_CLIENT_SECRET"),"YOUR_CLIENT_ID");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        try {
            HttpServletRequest req = (HttpServletRequest) request;            
            System.out.println("PATH :"+req.getPathInfo());
            if(!req.getPathInfo().equalsIgnoreCase("/auth")) {
                String token = getToken(req);
                String x = jwt.verificarDigesto(token);
                System.out.println("Token:"+token);
                System.out.println("User :"+x);
                System.out.println("Verificar :"+x.equalsIgnoreCase("mdominguez"));
                chain.doFilter(request, response);
            }else{
                chain.doFilter(request, response);
            }
            //Map<String, Object> decoded = jwtVerifier.verify(token);
            // Do something with decoded information like UserId
            
        } catch (Exception e) {
            String errorMessage = "No está autorizado el usuario o la contraseña ingresadas";
            ((HttpServletResponse)response).setHeader("X-Error-Message", errorMessage);
            ((HttpServletResponse)response).sendError(HttpServletResponse.SC_FORBIDDEN, errorMessage);
        }
    }

    private String getToken(HttpServletRequest httpRequest) throws ServletException {
      String token = null;
        final String authorizationHeader = httpRequest.getHeader("authorization");
        if (authorizationHeader == null) {
            throw new ServletException("Unauthorized: No Authorization header was found");
        }

        String[] parts = authorizationHeader.split(" ");
        if (parts.length != 2) {
            throw new ServletException("Unauthorized: Format is Authorization: Bearer [token]");
        }

        String scheme = parts[0];
        String credentials = parts[1];

        Pattern pattern = Pattern.compile("^Bearer$", Pattern.CASE_INSENSITIVE);
        if (pattern.matcher(scheme).matches()) {
            token = credentials;
        }
        return token;
    }

  @Override
  public void destroy() {

  }    
    
}
