package br.unitins.tp1.loja.resource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import br.unitins.tp1.loja.dto.LoteRequestDTO;
import br.unitins.tp1.loja.model.Lote;
import br.unitins.tp1.loja.service.LoteService;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

@QuarkusTest
public class LoteResourceTest {
    @Inject
    public LoteService loService;
   
    @Test
    public void testFindAll(){
        given()
            .when().get("/lotes")
            .then().statusCode(200);
    }


    @Test
    public void testCreate() {
        LoteRequestDTO dto = new LoteRequestDTO("JBYFTYT8977665", 300, 
                                            LocalDate.of(2023, 10, 30), 20987);

        given()
        .contentType(ContentType.JSON)
        .body(dto)
        .when()
            .post("/lotes")
        .then()
            .statusCode(201)
            .body("id", notNullValue(),
                  "codigo", is("JBYFTYT8977665"),
                  "quantidade", is(300),  // Corrigido para ser um número inteiro
                  "dataFabricacao", is("2023-10-30"), 
                  "numero", is(20987));

        // Removendo o dado que foi inserido
        loService.delete(loService.findByCodigo("JBYFTYT8977665").getId());
    }

    @Test
    public void testUpdate() {
    // Inserindo dado para alteração (evitando a manipulação de dados)
        LoteRequestDTO dto = new LoteRequestDTO("BJJBHVIUO", 98, LocalDate.of(2020, 8, 2), 1);
    
        long id = loService.create(dto).getId();

        LoteRequestDTO novoDto = new LoteRequestDTO("BBBBBBBBBB", 98, LocalDate.of(2020, 8, 2), 1);

        given()
        .contentType(ContentType.JSON)
        .body(novoDto)
        .when()
            .put("/lotes/" + id)
        .then()
            .statusCode(204);

        Lote lote = loService.findById(id);
        assertEquals("BBBBBBBBBB", lote.getCodigo());  // Simplificado para ser mais claro

            // Removendo o dado que foi inserido
        loService.delete(lote.getId());
    }

    @Test
    public void testDelete() {
        // inserindo dado para alteracao (evitando a manipulacao de dados)
        LoteRequestDTO dto = 
        new LoteRequestDTO("BJJBHVIUO", 98, LocalDate.of(2020, 8, 2), 1);

        long id = loService.create(dto).getId();

        given()
        .when()
            .delete("/lotes/"+id)
        .then()
            .statusCode(204);

        // verificando se foi apagado no banco de dados
        Lote lote = loService.findById(id);
        assertNull(lote);
    
    }
    
    @Test
    @TestHTTPEndpoint(LoteResource.class)
    public void testFindAll2() {
        given()
            .when().get()
            .then()
                .statusCode(200)
                .body("$.size()", is(notNullValue())) 
                .body("[0].codigo", notNullValue());
    }
}
