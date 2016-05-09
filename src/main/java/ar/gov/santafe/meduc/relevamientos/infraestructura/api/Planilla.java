/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.gov.santafe.meduc.relevamientos.infraestructura.api;

import ar.gov.santafe.meduc.relevamientos.infraestructura.modelo.Cuadro;
import ar.gov.santafe.meduc.relevamientos.infraestructura.service.CuadroLogica;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.IOException;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author mdominguez
 */
@Path("/planilla")
@Stateless
public class Planilla {

    @EJB
    CuadroLogica cuadroService;
    
    @POST
    @Path("/{idTipoCuadro}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response recibirCuadro(String s,@PathParam(value = "idTipoCuadro") Integer idTipoCuadro) throws IOException{
        System.out.println("LLego");
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode root = (ObjectNode) mapper.readTree(s);
        String titulo = root.get("titulo").asText();
        //String datos = .asText();
        JsonNode arrNode = root.get("datos");
        System.out.println("titulo: " + titulo);
        System.out.println("datos1: "+arrNode.textValue());
        System.out.println("datos2: "+arrNode.toString());
        Cuadro c = new Cuadro();
        c.setTitulo(titulo);
        c.setDatos(arrNode.toString());        
        cuadroService.guardar(c);

        String json = mapper.writeValueAsString(c);
        return Response.ok(json, MediaType.APPLICATION_JSON).build();
    }
    
    @GET
    @Path("/{idplanilla}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response mostrarCuandro(@PathParam(value = "idplanilla") Integer idplanilla) throws IOException{ 
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(cuadroService.get(idplanilla));
        return Response.ok(json, MediaType.APPLICATION_JSON).build();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarCuadros() throws IOException{ 
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(cuadroService.listar());
        return Response.ok(json, MediaType.APPLICATION_JSON).build();
    }
}
