package ch.ilv.KoSystem.z_tournamentMember;

import ch.qos.logback.core.util.DatePatternToRegexUtil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TournamentMemberRepository extends JpaRepository<TournamentMember, Long> {
}
