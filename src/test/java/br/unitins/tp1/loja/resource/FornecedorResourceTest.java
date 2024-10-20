package br.unitins.tp1.loja.resource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import br.unitins.tp1.loja.dto.FornecedorRequestDTO;
import br.unitins.tp1.loja.model.Fornecedor;
import br.unitins.tp1.loja.service.FornecedorService;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

@QuarkusTest
public class FornecedorResourceTest {
    @Inject
    public FornecedorService fornService;
   
    @Test
    public void testFindAll(){
        given()
            .when().get("/fornecedores")
            .then().statusCode(200);
    }


    @Test
    public void testCreate(){
        FornecedorRequestDTO dto = 
            new FornecedorRequestDTO("Ventisol", "33394574982308","fabventilador@ventisol.com");

        given()
            .contentType(ContentType.JSON)
            .body(dto)
            .when()
                .post("/fornecedores")
            .then()
                .statusCode(201)
                .body("id", notNullValue(),
                      "nome", is("Ventisol"),
                      "cnpj", is("33394574982308"), "contato", is("fabventilador@ventisol.com"));

        // removendo o dado que foi inserido
        fornService.delete(fornService.findByCnpj("33394574982308").getId());
    }

    @Test
    public void testUpdate() {
        // inserindo dado para alteracao (evitando a manipulacao de dados)
        FornecedorRequestDTO dto = 
        new FornecedorRequestDTO("teste", "00000000000000", "testando@zerinho.com");
        
        long id = fornService.create(dto).getId();

        FornecedorRequestDTO novoDto = 
            new FornecedorRequestDTO("ventilinho", "00000000000000", "testando@zerinho.com");

        given()
        .contentType(ContentType.JSON)
        .body(novoDto)
        .when()
            .put("/fornecedores/"+id)
        .then()
            .statusCode(204);

        Fornecedor forn = fornService.findById(id);

        assertEquals(forn.getNome(), "ventilinho");

        // removendo o dado que foi inserido
        fornService.delete(fornService.findByCnpj("00000000000000").getId());

    }

    @Test
    public void testDelete() {
        // inserindo dado para alteracao (evitando a manipulacao de dados)
        FornecedorRequestDTO dto = 
        new FornecedorRequestDTO("teste", "00000000000000", "testando@zerinho.com");

        long id = fornService.create(dto).getId();

        given()
        .when()
            .delete("/fornecedores/"+id)
        .then()
            .statusCode(204);

        // verificando se foi apagado no banco de dados
        Fornecedor forn = fornService.findById(id);
        assertNull(forn);
    
    }

}
