package edu.upf.model.integration;
 
import static org.hamcrest.Matchers.containsString;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
 
import java.nio.charset.Charset;
 
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
 
import edu.upf.model.controller.DefaultController;
 
/**
 * https://spring.io/guides/gs/testing-web/
 *
 * @author u4537
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles(value = {"TEST"})
@AutoConfigureMockMvc
@Transactional
public class DefaultControllerTest {
     
    @Autowired
    private DefaultController controller;
 
    @Test
    public void contexLoads() throws Exception {
        assertThat(controller).isNotNull();
    }
     
    @Autowired
    private WebApplicationContext wac;
     
    @Autowired
    private MockMvc mockMvc;
         
    @Before
    public void setUp() {
         
        mockMvc = MockMvcBuilders
                    .webAppContextSetup(this.wac)
                    .apply(springSecurity())
                    .build();
    }
     
    @Test
    @WithUserDetails("127681")
    @Sql({
        "/db/sql/clean-up.sql",
        "/db/sql/insert-cap.sql"       
        }) 
    public void peticioEntradaMostraPantallaInicial() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("inici"))
                .andExpect(content().contentType(new MediaType("text", "html", Charset.forName("UTF-8"))))
                .andExpect(content().string(containsString("exemple de pantalla inicial")));
    }
}