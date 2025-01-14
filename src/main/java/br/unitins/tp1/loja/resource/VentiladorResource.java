package br.unitins.tp1.loja.resource;


import br.unitins.tp1.loja.dto.VentiladorRequestDTO;
import br.unitins.tp1.loja.dto.VentiladorResponseDTO;
import br.unitins.tp1.loja.service.VentiladorService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/ventiladores")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class VentiladorResource {

    @Inject
    public VentiladorService ventiladorService;

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        return Response.ok(VentiladorResponseDTO.valueOf(ventiladorService.findById(id))).build();
    }

    @GET
    @Path("/search/{nome}")
    public Response findByNome(@PathParam("nome") String nome) {
        return Response.ok(ventiladorService.findByNome(nome).
                                    stream().
                                    map(o -> VentiladorResponseDTO.valueOf(o)).
                                    toList()).build();
    }

    @GET
    public Response findAll() {
        return Response.ok(ventiladorService.
                    findAll().
                    stream().
                    map(o -> VentiladorResponseDTO.valueOf(o)).
                    toList()).build();
        
    }

    @POST
    public Response create(@Valid VentiladorRequestDTO dto) {
        return Response.status(Status.CREATED).entity(VentiladorResponseDTO.valueOf(ventiladorService.create(dto))).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, @Valid VentiladorRequestDTO dto) {
        ventiladorService.update(id, dto);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        ventiladorService.delete(id);
        return Response.noContent().build();
    }
    
}
