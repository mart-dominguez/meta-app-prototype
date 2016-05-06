/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.gov.santafe.meduc.relevamientos.infraestructura.api;

import ar.gov.santafe.meduc.relevamientos.infraestructura.security.Credentials;
import ar.gov.santafe.meduc.relevamientos.infraestructura.security.JsonWebToken;
import ar.gov.santafe.meduc.relevamientos.infraestructura.security.JsonWebTokenJOSE;
import ar.gov.santafe.meduc.relevamientos.infraestructura.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/auth")
@Stateless
public class Login {
   
    @EJB
    private UserService userService;
    
    @EJB
    private JsonWebToken jwt;
    
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes("application/json")
    public Response authenticateUser(String userPass) {
        System.out.println("======> USER: "+userPass);
        String token = "";
        try {            
            ObjectMapper mapper = new ObjectMapper();
            Credentials user = mapper.readValue(userPass, Credentials.class);
            if(userService.validar(user.getUserName(),user.getClaveMd5())){
                token=jwt.crearDigesto(user.getUserName()+"|"+user.getClaveMd5());
            }          
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }      
        return Response.ok(token).build();
    }
    
    @POST
    @Path("/validar")
    @Produces(MediaType.TEXT_PLAIN)    
    public Response validarToken(String tokenParam) {
        String token = "";
        try {            
            ObjectMapper mapper = new ObjectMapper();
            ObjectNode root = (ObjectNode) mapper.readTree(tokenParam);
            token=root.get("token").asText();
            System.out.println(jwt.verificarDigesto(token));
            String payload = jwt.verificarDigesto(token);
            System.out.println("====> CARGA");
            System.out.println("====> "+payload);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }      
        return Response.ok(token).build();
    }
    
    /*@POST
    public Response login(){
        String claveJOSE = "Bearer " + appKey; // appKey is unique number

        HTTP/1.1 200 OK
Status: 200 OK
Content-Type: application/json; charset=utf-8
...
Content-Encoding: gzip
Content-Length: 140

{"token_type":"bearer","access_token":"AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA%2FAAAAAAAAAAAAAAAAAAAA%3DAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"}

        Response resp =  Response.ok()
        .header("Content-Type", "application/json;charset=UTF-8")
        .header("Authorization", claveJOSE)
        .get(ClientResponse.class);

            .header("Authorization", appKey)

    }*/
    
}
