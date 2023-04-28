package ch.ilv.KoSystem;

import ch.ilv.KoSystem.z_teams.Team;
import ch.ilv.KoSystem.z_teams.TeamRepository;
import ch.ilv.KoSystem.z_teams.TeamService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
@AutoConfigureDataJpa
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RestControllerTests {
    @Autowired
    private MockMvc api;

    @Autowired
    private TeamRepository teamRepository;
    private Team team1;
    private Team team2;
    @BeforeAll
    void setup() {
        this.team1 = teamRepository.save(new Team("Memphis Grizzlies"));
        this.team2 = teamRepository.save(new Team("Miami Heats"));
    }

    @Test
    @Order(1)
    void testGetTeam() throws Exception {
        String accessToken = obtainAccessToken();

        api.perform(get("/api/team").header("Authorization", "Bearer " + accessToken)
                        .with(csrf()))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Miami Heats")));
    }

    @Test

    @Order(1)
    void testPostTeam() throws Exception {

        Team team = new Team();

        team.setTeamName("Chicago Bulls");

        String accessToken = obtainAccessToken();
        String body = new ObjectMapper().writeValueAsString(team);

        api.perform(post("/api/team")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body)
                        .header("Authorization", "Bearer " + accessToken)
                        .with(csrf()))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Chicago Bulls")));
    }

    @Test
    @Order(1)
    void testPutCategory() throws Exception {

        team1.setTeamName("Chicago Bulls");

        String accessToken = obtainAccessToken();
        String body = new ObjectMapper().writeValueAsString(team1);

        api.perform(put("/api/team/" + team1.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body)
                        .header("Authorization", "Bearer " + accessToken)
                        .with(csrf()))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Chicago Bulls")));
    }


    @Test
    void deleteTeam() throws Exception {
        String accessToken = obtainAccessToken();
        String body = new ObjectMapper().writeValueAsString(team2);

        api.perform(delete("/api/team/" + team2.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body)
                        .header("Authorization", "Bearer " + accessToken)
                        .with(csrf()))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(status().isOk());


        team2 = teamRepository.findById(team2.getId()).orElse(null);
        Assertions.assertNull(team2);

    }



    private String obtainAccessToken() {

        RestTemplate rest = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        String body = "client_id=Ko_system&" +
                "grant_type=password&" +
                "scope=openid profile roles offline_access&" +
                "username=user_main&" +
                "password=write";

        HttpEntity<String> entity = new HttpEntity<>(body, headers);

        ResponseEntity<String> resp = rest.postForEntity("http://localhost:8080/realms/ILV/protocol/openid-connect/token", entity, String.class);

        JacksonJsonParser jsonParser = new JacksonJsonParser();
        return jsonParser.parseMap(resp.getBody()).get("access_token").toString();
    }

}
