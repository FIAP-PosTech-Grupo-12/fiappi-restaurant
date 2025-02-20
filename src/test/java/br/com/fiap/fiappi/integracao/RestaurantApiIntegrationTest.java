package br.com.fiap.fiappi.integracao;

import br.com.fiap.fiappi.adapter.database.jpa.restaurant.repository.RestaurantRepository;
import br.com.fiap.fiappi.adapter.database.jpa.user.entity.User;
import br.com.fiap.fiappi.adapter.web.RestaurantApiController;
import br.com.fiap.fiappi.core.restaurant.dto.RestaurantDTO;
import br.com.fiap.fiappi.core.restaurant.enums.KitchenTypeEnum;
import br.com.fiap.fiappi.core.user.enums.RoleName;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc(addFilters = false)
@Sql(scripts = "/db/basic-inserts.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
public class RestaurantApiIntegrationTest {

    private static final String URL_BASE = "/v1/restaurant";

    @Autowired
    protected MockMvc mvc;

    @Autowired
    private RestaurantApiController restaurantApiController;

    @Autowired
    private RestaurantRepository repository;

    private UUID onwerId = UUID.fromString("1555d5ff-837c-4d6e-a824-114ce54921ad");

    @BeforeEach
    void setup(){

        User user = new User(onwerId, "Master", "test_master@master.com", "master", "123", LocalDateTime.now(), "address", RoleName.ROLE_ADMINISTRATOR); // Ajuste conforme sua entidade

        UsernamePasswordAuthenticationToken auth =
                new UsernamePasswordAuthenticationToken(user, null, List.of());

        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(auth);
        SecurityContextHolder.setContext(context);
    }

    @Test
    void shouldCreateRestaurant() throws Exception {

        RestaurantDTO dto = new RestaurantDTO(
                UUID.randomUUID(),
                "test",
                "Adress",
                KitchenTypeEnum.FAST_FOOD,
                "7-22",
                onwerId
        );


        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(URL_BASE)
                .content(asJsonString(dto))
                .contentType(MediaType.APPLICATION_JSON)
                ;

        mvc.perform(request)
                .andExpect(status().isCreated());

    }

    @Test
    void shouldFindRestaurantById() throws Exception {

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get(URL_BASE + "/b28a1c34-4a6b-4e3d-a70f-5d2f1a5c6e3a");

        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Restaurante Sabor Caseiro")));
    }



    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



}
