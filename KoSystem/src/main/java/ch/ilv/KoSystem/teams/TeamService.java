package ch.ilv.KoSystem.teams;

import ch.ilv.KoSystem.base.MessageResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {
    private final TeamRepository teamRepository;
    public TeamService(TeamRepository teamRepository){
        this.teamRepository = teamRepository;
        }

    public List<Team> getTeam() {
        return teamRepository.findAll();
    }

    public Team getTeam(Long id) {
        return teamRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException());
    }

    public Team insertTeam( Team team) {
        Team save = teamRepository.save(team);
        return save;
    }

    public Team updateTeam(Team team, Long id) {
        return teamRepository.findById(id)
                .map(teamOrig -> {
                    teamOrig.setTeamName(team.getTeamName());
                    return teamRepository.save(teamOrig);
                })
                .orElseGet(() -> teamRepository.save(team));
    }

    public MessageResponse deleteTeam(Long id) {
        teamRepository.deleteById(id);
        return new MessageResponse("Team " + id + " deleted");
    }
}
