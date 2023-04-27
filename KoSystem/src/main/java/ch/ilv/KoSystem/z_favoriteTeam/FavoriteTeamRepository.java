package ch.ilv.KoSystem.z_favoriteTeam;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteTeamRepository extends JpaRepository<FavoriteTeam, Long> {
}
