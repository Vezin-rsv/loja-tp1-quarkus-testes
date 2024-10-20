package br.unitins.tp1.loja.resource;


import br.unitins.tp1.loja.dto.FabricanteRequestDTO;
import br.unitins.tp1.loja.dto.FabricanteResponseDTO;
import br.unitins.tp1.loja.service.FabricanteService;
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

@Path("/fabricantes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FabricanteResource {

    @Inject
    public FabricanteService fabricanteService;

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        return Response.ok(FabricanteResponseDTO.valueOf(fabricanteService.findById(id))).build();
    }

    @GET
    @Path("/search/{nome}")
    public Response findByNome(@PathParam("nome") String nome) {
        return Response.ok(fabricanteService.findByNome(nome).
                                    stream().
                                    map(o -> FabricanteResponseDTO.valueOf(o)).
                                    toList()).build();
    }

    @GET
    public Response findAll() {
        return Response.ok(fabricanteService.
                    findAll().
                    stream().
                    map(o -> FabricanteResponseDTO.valueOf(o)).
                    toList()).build();
        
    }

    @POST
    public Response create(@Valid FabricanteRequestDTO dto) {
        return Response.status(Status.CREATED).entity(FabricanteResponseDTO.valueOf(fabricanteService.create(dto))).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, @Valid FabricanteRequestDTO dto) {
        fabricanteService.update(id, dto);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        fabricanteService.delete(id);
        return Response.noContent().build();
    }
    
}
