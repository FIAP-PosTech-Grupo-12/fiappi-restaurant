package br.com.fiap.fiappi.integracao;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.fiap.fiappi.adapter.database.jpa.user.entity.User;
import br.com.fiap.fiappi.core.menu.dto.MenuDTO;
import br.com.fiap.fiappi.core.user.enums.RoleName;
import jakarta.transaction.Transactional;
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
public class MenuApiIntegrationTest {

        private static final String URL_BASE = "/v1/menu";

        @Autowired
        protected MockMvc mvc;

        private final UUID ownerId = UUID.fromString("1555d5ff-837c-4d6e-a824-114ce54921ad");

        @BeforeEach
        void setup() {
                User user = new User(ownerId, "Master", "test_master@master.com", "master", "123",
                                LocalDateTime.now(), "address", RoleName.ROLE_ADMINISTRATOR);
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user, null,
                                List.of());
                SecurityContext context = SecurityContextHolder.createEmptyContext();
                context.setAuthentication(auth);
                SecurityContextHolder.setContext(context);
        }

        @Test
        void shouldCreateMenu() throws Exception {
                UUID restaurantId = UUID.fromString("b28a1c34-4a6b-4e3d-a70f-5d2f1a5c6e3a");
                MenuDTO dto = new MenuDTO(restaurantId, "Test Menu", "Test Description", 12.50, true);
                String dtoJson = asJsonString(dto);
                MockMultipartFile file = new MockMultipartFile("file", "menu.txt", MediaType.TEXT_PLAIN_VALUE,
                                "dummy content".getBytes());

                MockHttpServletRequestBuilder request = MockMvcRequestBuilders.multipart(URL_BASE)
                                .file(file)
                                .param("dto", dtoJson);

                mvc.perform(request)
                                .andExpect(status().isCreated());
        }

        @Test
        @Order(1)
        void shouldUpdateMenu() throws Exception {
                MenuDTO dto = new MenuDTO(
                                UUID.fromString("b28a1c34-4a6b-4e3d-a70f-5d2f1a5c6e5a"),
                                UUID.fromString("b28a1c34-4a6b-4e3d-a70f-5d2f1a5c6e3a"),
                                "Feijoada Atualizada",
                                "Nova descrição da feijoada",
                                45.90,
                                true,
                                null);

                MockHttpServletRequestBuilder request = MockMvcRequestBuilders.patch(URL_BASE)
                                .content(asJsonString(dto))
                                .contentType(MediaType.APPLICATION_JSON);

                mvc.perform(request)
                                .andExpect(status().isOk());
        }

        @Test
        void shouldDeleteMenu() throws Exception {
                MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                                .delete(URL_BASE + "/b28a1c34-4a6b-4e3d-a70f-5d2f1a5c6e5a");
                mvc.perform(request)
                                .andExpect(status().isNoContent());
        }

        @AfterEach
        void removeFiles() {
                File imagesDir = new File("src/main/resources/static/images");
                if (imagesDir.exists() && imagesDir.isDirectory()) {
                        File[] files = imagesDir.listFiles();
                        if (files != null) {
                                for (File file : files) {
                                        file.delete();
                                }
                        }
                }
        }

        public static String asJsonString(final Object obj) {
                try {
                        return new ObjectMapper().writeValueAsString(obj);
                } catch (Exception e) {
                        throw new RuntimeException(e);
                }
        }
}
