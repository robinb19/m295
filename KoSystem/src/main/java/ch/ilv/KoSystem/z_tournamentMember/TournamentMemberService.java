package ch.ilv.KoSystem.z_tournamentMember;

import ch.ilv.KoSystem.base.MessageResponse;
import ch.ilv.KoSystem.z_favoriteTeam.FavoriteTeam;
import ch.ilv.KoSystem.z_teams.Team;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TournamentMemberService {

    private final TournamentMemberRepository tournamentMemberRepository;

    public TournamentMemberService(TournamentMemberRepository tournamentMemberRepository){
        this.tournamentMemberRepository = tournamentMemberRepository;
    }
    public List<TournamentMember> getTournamentMember() {
        return tournamentMemberRepository.findAll();
    }
    public TournamentMember getTournamentMember(Long id) {
        return tournamentMemberRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException());
    }

    public TournamentMember insertTournamentMember( TournamentMember tournamentMember) {
        TournamentMember save = tournamentMemberRepository.save(tournamentMember);
        return save;
    }

    public TournamentMember updateTournamentMember(TournamentMember tournamentMember, Long id) {
        return tournamentMemberRepository.findById(id)
                .map(tournamentMemberOrig -> {
                    tournamentMemberOrig.setTeam(tournamentMember.getTeam());
                   // tournamentMemberOrig.setTournament(tournamentMember.getTournament());
                    return tournamentMemberRepository.save(tournamentMemberOrig);
                })
                .orElseGet(() -> tournamentMemberRepository.save(tournamentMember));
    }

    public MessageResponse deleteTournamentMember(Long id) {
        tournamentMemberRepository.deleteById(id);
        return new MessageResponse("TournamentMember " + id + " deleted");
    }
    public static void getWinner(){

    }
}
