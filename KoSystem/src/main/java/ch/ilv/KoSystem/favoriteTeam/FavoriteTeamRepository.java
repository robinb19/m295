package ch.ilv.KoSystem.favoriteTeam;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteTeamRepository extends JpaRepository<FavoriteTeam, Long> {
}
