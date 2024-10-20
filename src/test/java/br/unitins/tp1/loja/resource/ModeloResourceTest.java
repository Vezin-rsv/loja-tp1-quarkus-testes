package br.unitins.tp1.loja.resource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import br.unitins.tp1.loja.dto.ModeloRequestDTO;
import br.unitins.tp1.loja.model.Modelo;
import br.unitins.tp1.loja.service.ModeloService;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

@QuarkusTest
public class ModeloResourceTest {
    @Inject
    public ModeloService modeloService;
   
    @Test
    public void testFindAll(){
        given()
            .when().get("/modelos")
            .then().statusCode(200);
    }


    @Test
    public void testCreate() {
        ModeloRequestDTO dto = new ModeloRequestDTO("Teto", 78);

        Modelo modCriado = given()
        .contentType(ContentType.JSON)
        .body(dto)
        .when()
            .post("/modelos")
        .then()
            .statusCode(201)
            .body("id", notNullValue(),
                  "nome", is("Teto"),
                  "comprimento", is(78))
            .extract().as(Modelo.class);

        // Removendo o dado que foi inserido
        modeloService.delete(modCriado.getId());
    }

    @Test
    public void testUpdate() {
    // Inserindo dado para alteração (evitando a manipulação de dados)
        ModeloRequestDTO dto = new ModeloRequestDTO("Teste", 98);
    
        long id = modeloService.create(dto).getId();

        ModeloRequestDTO novoDto = new ModeloRequestDTO("Teto", 98);

        given()
        .contentType(ContentType.JSON)
        .body(novoDto)
        .when()
            .put("/modelos/" + id)
        .then()
            .statusCode(204);

        Modelo modelo = modeloService.findById(id);

        assertEquals(modelo.getNome(), "Teto");

            // Removendo o dado que foi inserido
            modeloService.delete(modelo.getId());
    }

    @Test
    public void testDelete() {
        // inserindo dado para alteracao (evitando a manipulacao de dados)
        ModeloRequestDTO dto = new ModeloRequestDTO("Teste", 98);

        long id = modeloService.create(dto).getId();

        given()
        .when()
            .delete("/modelos/"+id)
        .then()
            .statusCode(204);

        // verificando se foi apagado no banco de dados
        Modelo modelo = modeloService.findById(id);
        assertNull(modelo);
    
    }


    @Test
    @TestHTTPEndpoint(ModeloResource.class)
    public void testFindAll2(){
        given()
            .when().get()
            .then()
                .statusCode(200)
                .body("$.size()", is(4),
                     "[1].nome", is("Mesa"));
    }
}
