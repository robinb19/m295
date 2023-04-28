package ch.ilv.KoSystem.z_tournamentMember;

import ch.ilv.KoSystem.z_crateTournament.Tournament;
import ch.qos.logback.core.util.DatePatternToRegexUtil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TournamentMemberRepository extends JpaRepository<TournamentMember, Long> {
    public List<TournamentMember> findByTournament(Tournament tournament);


}

