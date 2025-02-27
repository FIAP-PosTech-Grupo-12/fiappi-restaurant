package br.com.fiap.fiappi.integracao;

import br.com.fiap.fiappi.adapter.database.jpa.user.entity.User;
import br.com.fiap.fiappi.core.restaurant.dto.RestaurantDTO;
import br.com.fiap.fiappi.core.restaurant.enums.KitchenTypeEnum;
import br.com.fiap.fiappi.core.user.enums.RoleName;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
import org.springframework.transaction.annotation.Transactional;


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
@Sql(scripts = "/db/basic-inserts.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Transactional
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RestaurantApiIntegrationTest {

    private static final String URL_BASE = "/v1/restaurant";

    @Autowired
    protected MockMvc mvc;

    private final UUID ownerId = UUID.fromString("1555d5ff-837c-4d6e-a824-114ce54921ad");

    @BeforeEach
    void setup(){

        User user = new User(ownerId, "Master", "test_master@master.com", "master", "123", LocalDateTime.now(), "address", RoleName.ROLE_ADMINISTRATOR);

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
                "Test",
                "Address",
                KitchenTypeEnum.FAST_FOOD,
                "7-22",
                ownerId
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
    @Order(1)
    void shouldFindRestaurantById() throws Exception {

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get(URL_BASE + "/b28a1c34-4a6b-4e3d-a70f-5d2f1a5c6e3a");

        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is("b28a1c34-4a6b-4e3d-a70f-5d2f1a5c6e3a")));
    }

    @Test
    @Order(2)
    void shouldListRestaurants() throws Exception {

        Pageable pageable = PageRequest.of(0, 10);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get(URL_BASE)
                .param("page", String.valueOf(pageable.getPageNumber()))
                .param("size", String.valueOf(pageable.getPageSize()));

        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", is(2)))
                .andExpect(jsonPath("$[0].id", is("c5a8d2e4-5b9c-4f3a-88b5-6e7d4f5a9c8d")))
                .andExpect(jsonPath("$[1].id", is("b28a1c34-4a6b-4e3d-a70f-5d2f1a5c6e3a")));
    }

    @Test
    void shouldDeleteRestaurant() throws Exception {

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .delete(URL_BASE + "/b28a1c34-4a6b-4e3d-a70f-5d2f1a5c6e3a");

        mvc.perform(request)
                .andExpect(status().isNoContent());
    }

    @Test
    void shouldUpdateRestaurant() throws Exception {

        RestaurantDTO dto = new RestaurantDTO(
                UUID.fromString("b28a1c34-4a6b-4e3d-a70f-5d2f1a5c6e3a"),
                "Restaurante Sabor Caseiro Novo",
                "Endereco novo, numero 123",
                KitchenTypeEnum.FAST_FOOD,
                "7-22",
                ownerId
        );

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .patch(URL_BASE)
                .content(asJsonString(dto))
                .contentType(MediaType.APPLICATION_JSON);

        mvc.perform(request)
                .andExpect(status().isOk());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



}
