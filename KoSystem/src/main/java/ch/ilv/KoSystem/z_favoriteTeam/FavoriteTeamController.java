package ch.ilv.KoSystem.z_favoriteTeam;

import ch.ilv.KoSystem.base.MessageResponse;
import ch.ilv.KoSystem.security.Roles;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@SecurityRequirement(name = "bearerAuth")
@Validated
public class FavoriteTeamController {
    private final FavoriteTeamService favoriteTeamService;

    FavoriteTeamController(FavoriteTeamService favoriteTeamService) {
        this.favoriteTeamService = favoriteTeamService;
    }

    @GetMapping("api/favoriteTeam")
    @RolesAllowed(Roles.Read)
    public ResponseEntity<List<FavoriteTeam>> all() {
        List<FavoriteTeam> result = favoriteTeamService.getFavoriteTeam();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("api/favoriteTeam/{id}")
    @RolesAllowed(Roles.Read)
    public ResponseEntity<FavoriteTeam> one(@PathVariable Long id) {
        FavoriteTeam favoriteTeam = favoriteTeamService.getFavoriteTeam(id);
        return new ResponseEntity<>(favoriteTeam, HttpStatus.OK);
    }

    @PostMapping("api/favoriteTeam")
    @RolesAllowed(Roles.ReadWrite)
    public ResponseEntity<FavoriteTeam> newFavoriteTeam(@Valid @RequestBody FavoriteTeam favoriteTeam) {
        FavoriteTeam savedfavoriteTeam = favoriteTeamService.insertFavoriteTeam(favoriteTeam);
        return new ResponseEntity<>(savedfavoriteTeam, HttpStatus.OK);
    }

    @PutMapping("api/favoriteTeam/{id}")
    @RolesAllowed(Roles.ReadWrite)
    public ResponseEntity<FavoriteTeam> updateFavoriteTeam(@Valid @RequestBody FavoriteTeam favoriteTeam, @PathVariable Long id) {
        FavoriteTeam updateFavoriteTeam = favoriteTeamService.updateFavoriteTeam(favoriteTeam, id);
        return new ResponseEntity<>(updateFavoriteTeam, HttpStatus.OK);
    }

    @DeleteMapping("api/favoriteTeam/{id}")
    @RolesAllowed(Roles.ReadWrite)
    public ResponseEntity<MessageResponse> deleteFavoriteTeam(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(favoriteTeamService.deleteFavoriteTeam(id));
        } catch (Throwable t) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
