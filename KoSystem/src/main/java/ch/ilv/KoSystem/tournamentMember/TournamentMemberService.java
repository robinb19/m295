package ch.ilv.KoSystem.tournamentMember;

import ch.ilv.KoSystem.base.MessageResponse;
import ch.ilv.KoSystem.Tournament.Tournament;
import ch.ilv.KoSystem.Tournament.TournamentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class TournamentMemberService {

    private final TournamentMemberRepository tournamentMemberRepository;
    private final TournamentRepository tournamentRepository;

    public TournamentMemberService(TournamentMemberRepository tournamentMemberRepository, TournamentRepository tournamentRepository){
        this.tournamentMemberRepository = tournamentMemberRepository;
        this.tournamentRepository = tournamentRepository;
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
                    tournamentMemberOrig.setTournament(tournamentMember.getTournament());
                    return tournamentMemberRepository.save(tournamentMemberOrig);
                })
                .orElseGet(() -> tournamentMemberRepository.save(tournamentMember));
    }

    public MessageResponse deleteTournamentMember(Long id) {
        tournamentMemberRepository.deleteById(id);
        return new MessageResponse("TournamentMember " + id + " deleted");
    }

    public TournamentMember getWinner(Long tournamentId){
        Tournament tournament = tournamentRepository.findById(tournamentId).get();
        List<TournamentMember> tournamentMembers = tournamentMemberRepository.findByTournament(tournament);
        Random rand = new Random();
        int randomIndex = rand.nextInt(0, tournamentMembers.size() - 1);
        return tournamentMembers.get(randomIndex);
    }



}
