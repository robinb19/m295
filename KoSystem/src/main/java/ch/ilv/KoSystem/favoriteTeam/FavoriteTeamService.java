package ch.ilv.KoSystem.favoriteTeam;

import ch.ilv.KoSystem.base.MessageResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteTeamService {
    private final FavoriteTeamRepository favoriteTeamRepository;
    public FavoriteTeamService(FavoriteTeamRepository favoriteTeamRepository){
        this.favoriteTeamRepository = favoriteTeamRepository;
    }

    public List<FavoriteTeam> getFavoriteTeam() {
        return favoriteTeamRepository.findAll();
    }

    public FavoriteTeam getFavoriteTeam(Long id) {
        return favoriteTeamRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException());
    }

    public FavoriteTeam insertFavoriteTeam( FavoriteTeam favoriteTeam) {
        FavoriteTeam save = favoriteTeamRepository.save(favoriteTeam);
        return save;
    }

    public FavoriteTeam updateFavoriteTeam(FavoriteTeam favoriteTeam, Long id) {
        return favoriteTeamRepository.findById(id)
                .map(favoriteTeamOrig -> {
                    favoriteTeamOrig.setName(favoriteTeam.getName());
                    favoriteTeamOrig.setFirstname(favoriteTeam.getFirstname());
                    favoriteTeamOrig.setTeam(favoriteTeam.getTeam());
                    return favoriteTeamRepository.save(favoriteTeamOrig);
                })
                .orElseGet(() -> favoriteTeamRepository.save(favoriteTeam));
    }

    public MessageResponse deleteFavoriteTeam(Long id) {
        favoriteTeamRepository.deleteById(id);
        return new MessageResponse("FavoriteTeam " + id + " deleted");
    }
}
