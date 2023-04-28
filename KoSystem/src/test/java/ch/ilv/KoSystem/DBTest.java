package ch.ilv.KoSystem;

import ch.ilv.KoSystem.teams.Team;
import ch.ilv.KoSystem.teams.TeamRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest()
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
class DBTest {

    @Autowired
    private TeamRepository teamRepository;

    @Test
    void insertTeam() {
        Team objName = this.teamRepository.save(new Team("Memphis Grizzlies"));
        Assertions.assertNotNull(objName.getId());
    }
@Test
    void getTeam(){
        Team savedTeam = this.teamRepository.save(new Team("Memphis Grizzlies"));
        Team team = teamRepository.findById(savedTeam.getId()).orElse(null);

        Assertions.assertNotNull(team.getId());
        Assertions.assertEquals(savedTeam.getId(), team.getId());
        Assertions.assertEquals(savedTeam.getTeamName(), team.getTeamName());
    }

    @Test
    void updateTeam(){
        Team savedTeam = teamRepository.save(new Team("Memphis Grizzlies"));

        savedTeam.setTeamName("K6");
        teamRepository.save(savedTeam);

        Team team = teamRepository.findById(savedTeam.getId()).orElse(null);

        Assertions.assertNotNull(team);
        Assertions.assertEquals(savedTeam.getId(), team.getId());
        Assertions.assertEquals(savedTeam.getTeamName(), team.getTeamName());
    }

    @Test
    void deleteTeam(){
        Team savedTeam = this.teamRepository.save(new Team("Memphis Grizzlies"));
        teamRepository.deleteById(savedTeam.getId());
        Team team = teamRepository.findById(savedTeam.getId()).orElse(null);

        Assertions.assertNull(team);

    }

}
