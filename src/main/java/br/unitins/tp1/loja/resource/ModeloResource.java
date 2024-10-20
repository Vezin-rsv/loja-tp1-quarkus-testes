package br.unitins.tp1.loja.resource;


import br.unitins.tp1.loja.dto.ModeloRequestDTO;
import br.unitins.tp1.loja.dto.ModeloResponseDTO;
import br.unitins.tp1.loja.service.ModeloService;
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

@Path("/modelos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ModeloResource {

    @Inject
    public ModeloService modeloService;

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        return Response.ok(ModeloResponseDTO.valueOf(modeloService.findById(id))).build();
    }

    @GET
    @Path("/search/{nome}")
    public Response findByNome(@PathParam("nome") String nome) {
        return Response.ok(modeloService.findByNome(nome).
                                    stream().
                                    map(o -> ModeloResponseDTO.valueOf(o)).
                                    toList()).build();
    }

    @GET
    public Response findAll() {
        return Response.ok(modeloService.
                    findAll().
                    stream().
                    map(o -> ModeloResponseDTO.valueOf(o)).
                    toList()).build();
        
    }

    @POST
    public Response create(@Valid ModeloRequestDTO dto) {
        return Response.status(Status.CREATED).entity(ModeloResponseDTO.valueOf(modeloService.create(dto))).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, @Valid ModeloRequestDTO dto) {
        modeloService.update(id, dto);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        modeloService.delete(id);
        return Response.noContent().build();
    }
    
}
