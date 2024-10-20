package br.unitins.tp1.loja.resource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import br.unitins.tp1.loja.dto.VentiladorRequestDTO;
import br.unitins.tp1.loja.model.Ventilador;
import br.unitins.tp1.loja.service.VentiladorService;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

@QuarkusTest
public class VentiladorResourceTest {

    @Inject
    public VentiladorService vService;
   
    @Test
    public void testFindAll(){
        given()
            .when().get("/ventiladores")
            .then().statusCode(200);
    }


    @Test
    public void testCreate(){
        VentiladorRequestDTO dto = new VentiladorRequestDTO("Ventilador Turbinado", 700.0, "Potente e silencioso", 
                                    1, 1, 1l, 1l, 1l, 2l);

        Ventilador ventiCriado = given()
            .contentType(ContentType.JSON)
            .body(dto)
            .when()
                .post("/ventiladores")
            .then()
                .statusCode(201)
                .body("id", notNullValue(), "nome", is("Ventilador Turbinado"),
                  "preco", is(700.0F),
                  "descricao", is("Potente e silencioso"))
                  .extract().as(Ventilador.class);

        // removendo o dado que foi inserido
        vService.delete(ventiCriado.getId());
    }

    @Test
    public void testUpdate() {
        // inserindo dado para alteracao (evitando a manipulacao de dados)
        VentiladorRequestDTO dto = new VentiladorRequestDTO("Ventilador Turbinado", 700.0, "Potente e silencioso", 
                                    1, 1, 1l, 1l, 1l, 1l);
        
        Ventilador createdVentilador = vService.create(dto);
        long id = createdVentilador.getId();
        assertNotNull(createdVentilador);

        VentiladorRequestDTO novoDto = new VentiladorRequestDTO("Ventilador Turbo", 700.0, "Potente e silencioso", 
                                    1, 1, 1l, 1l, 1l, 1l);

        given()
        .contentType(ContentType.JSON)
        .body(novoDto)
        .when()
            .put("/ventiladores/"+id)
        .then()
            .statusCode(204);

        Ventilador v = vService.findById(id);

        assertEquals(v.getNome(), "Ventilador Turbo");

         // Removendo o dado que foi inserido
        vService.delete(id);

    }

    @Test
    public void testDelete() {
        // inserindo dado para alteracao (evitando a manipulacao de dados)
        VentiladorRequestDTO dto = new VentiladorRequestDTO("Ventilador teste", 199.99, "Teste de exclusão", 
                                    1, 1, 1l, 1l, 1l, 1l);
        
        long id = vService.create(dto).getId();

        given()
        .when()
            .delete("/ventiladores/"+id)
        .then()
            .statusCode(204);

        // verificando se foi apagado no banco de dados
        Ventilador v = vService.findById(id);
        assertNull(v);
    
    }
}

