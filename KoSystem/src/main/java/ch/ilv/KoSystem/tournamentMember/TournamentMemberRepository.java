package ch.ilv.KoSystem.tournamentMember;

import ch.ilv.KoSystem.Tournament.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TournamentMemberRepository extends JpaRepository<TournamentMember, Long> {
    public List<TournamentMember> findByTournament(Tournament tournament);


}

