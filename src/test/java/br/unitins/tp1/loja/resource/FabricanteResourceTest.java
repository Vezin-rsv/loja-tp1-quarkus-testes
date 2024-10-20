package br.unitins.tp1.loja.resource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import br.unitins.tp1.loja.dto.FabricanteRequestDTO;
import br.unitins.tp1.loja.model.Fabricante;
import br.unitins.tp1.loja.service.FabricanteService;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

@QuarkusTest
public class FabricanteResourceTest {
    @Inject
    public FabricanteService fabService;
   
    @Test
    public void testFindAll(){
        given()
            .when().get("/fabricantes")
            .then().statusCode(200);
    }


    @Test
    public void testCreate(){
        FabricanteRequestDTO dto = 
            new FabricanteRequestDTO("Ventisol", "33394574982308","fabventilador@ventisol.com");

        given()
            .contentType(ContentType.JSON)
            .body(dto)
            .when()
                .post("/fabricantes")
            .then()
                .statusCode(201)
                .body("id", notNullValue(),
                      "nome", is("Ventisol"),
                      "cnpj", is("33394574982308"), "enderecoEmail", is("fabventilador@ventisol.com"));

        // removendo o dado que foi inserido
        fabService.delete(fabService.findByCnpj("33394574982308").getId());
    }

    @Test
    public void testUpdate() {
        // inserindo dado para alteracao (evitando a manipulacao de dados)
        FabricanteRequestDTO dto = 
        new FabricanteRequestDTO("teste", "00000000000000", "testando@zerinho.com");
        
        long id = fabService.create(dto).getId();

        FabricanteRequestDTO novoDto = 
            new FabricanteRequestDTO("ventilinho", "00000000000000", "testando@zerinho.com");

        given()
        .contentType(ContentType.JSON)
        .body(novoDto)
        .when()
            .put("/fabricantes/"+id)
        .then()
            .statusCode(204);

        Fabricante fab = fabService.findById(id);

        assertEquals(fab.getNome(), "ventilinho");

        // removendo o dado que foi inserido
        fabService.delete(fabService.findByCnpj("00000000000000").getId());

    }

    @Test
    public void testDelete() {
        // inserindo dado para alteracao (evitando a manipulacao de dados)
        FabricanteRequestDTO dto = 
        new FabricanteRequestDTO("teste", "00000000000000", "testando@zerinho.com");

        long id = fabService.create(dto).getId();

        given()
        .when()
            .delete("/fabricantes/"+id)
        .then()
            .statusCode(204);

        // verificando se foi apagado no banco de dados
        Fabricante fab = fabService.findById(id);
        assertNull(fab);
    
    }
       
    @Test
    @TestHTTPEndpoint(FabricanteResource.class)
    public void testFindAll2(){
        given()
            .when().get()
            .then()
                .statusCode(200)
                .body("$.size()", is(2),
                     "[1].nome", is("Mondial"));
    }
}
