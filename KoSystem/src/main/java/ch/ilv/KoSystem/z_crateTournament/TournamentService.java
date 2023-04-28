package ch.ilv.KoSystem.z_crateTournament;

import ch.ilv.KoSystem.base.MessageResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TournamentService {

    private final TournamentRepository tournamentRepository;

    public TournamentService(TournamentRepository tournamentRepository) {
        this.tournamentRepository = tournamentRepository;
    }
    public List<Tournament> getTournament() {
        return tournamentRepository.findAll();
    }

    public Tournament getTournament(Long id) {
        return tournamentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException());
    }

    public Tournament insertTournament(Tournament tournament) {
        Tournament save = tournamentRepository.save(tournament);
        return save;
    }

    public Tournament updateTournament(Tournament tournament, Long id) {
        return tournamentRepository.findById(id)
                .map(tournamentOrig -> {
                    tournamentOrig.setVeranstaltungsOrt(tournament.getVeranstaltungsOrt());
                    tournamentOrig.setTurniername(tournament.getTurniername());
                    tournamentOrig.setToDate(tournament.getToDate());
                    return tournamentRepository.save(tournamentOrig);
                })
                .orElseGet(() -> tournamentRepository.save(tournament));
    }

    public MessageResponse deleteTournament(Long id) {
        tournamentRepository.deleteById(id);
        return new MessageResponse("Tournament " + id + " deleted");
    }


}
