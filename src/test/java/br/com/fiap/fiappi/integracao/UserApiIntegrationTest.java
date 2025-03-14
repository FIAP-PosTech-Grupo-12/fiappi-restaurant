package br.com.fiap.fiappi.integracao;

import br.com.fiap.fiappi.core.user.dto.ChangeUserPasswordDto;
import br.com.fiap.fiappi.core.user.dto.CreateUserDto;
import br.com.fiap.fiappi.core.user.dto.UpdateRoleUserDTO;
import br.com.fiap.fiappi.core.user.dto.UpdateUserDto;
import br.com.fiap.fiappi.core.user.enums.RoleName;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc(addFilters = false)
@Sql(scripts = "/db/basic-inserts.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Transactional
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserApiIntegrationTest {

    private static final String URL_BASE = "/v2/users";

    @Autowired
    protected MockMvc mvc;

    private final UUID ownerId = UUID.fromString("1555d5ff-837c-4d6e-a824-114ce54921ad");

    @Test
    void shouldCreateUser() throws Exception {

        CreateUserDto dto = new CreateUserDto("name",
                "email@email.com.br",
                "email_login",
                "password#123",
                RoleName.ROLE_ADMINISTRATOR,
                "address");

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(URL_BASE)
                .content(asJsonString(dto))
                .contentType(MediaType.APPLICATION_JSON);

        mvc.perform(request)
                .andExpect(status().isCreated());

    }

    @Test
    @WithMockUser(username = "Master", roles = {"ADMINISTRATOR"})
    void shouldfindUserById() throws Exception {

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get(URL_BASE + "/1555d5ff-837c-4d6e-a824-114ce54921ad")
                .contentType(MediaType.APPLICATION_JSON);

        mvc.perform(request)
                .andExpect(status().isOk());

    }

    @Test
    @WithMockUser(username = "Master", roles = {"ADMINISTRATOR"})
    void shouldUpdateUser() throws Exception {

        UpdateUserDto dto = new UpdateUserDto("name2",
                "email@email.com.br",
                "email_login");

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .patch(URL_BASE + "/1555d5ff-837c-4d6e-a824-114ce54921ad")
                .content(asJsonString(dto))
                .contentType(MediaType.APPLICATION_JSON);

        mvc.perform(request)
                .andExpect(status().isOk());

    }

    @Test
    @WithMockUser(username = "Master", roles = {"ADMINISTRATOR"})
    void shouldChangePasswordUser() throws Exception {

        ChangeUserPasswordDto dto = new ChangeUserPasswordDto("passwordNew123$");

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .patch(URL_BASE + "/1555d5ff-837c-4d6e-a824-114ce54921ad/password")
                .content(asJsonString(dto))
                .contentType(MediaType.APPLICATION_JSON);

        mvc.perform(request)
                .andExpect(status().isOk());

    }

    @Test
    void shouldUpdateRoleUser() throws Exception {

        UpdateRoleUserDTO dto = new UpdateRoleUserDTO(ownerId, RoleName.ROLE_CUSTOMER);

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
