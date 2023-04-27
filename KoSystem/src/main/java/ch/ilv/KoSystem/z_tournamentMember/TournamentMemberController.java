package ch.ilv.KoSystem.z_tournamentMember;

import ch.ilv.KoSystem.base.MessageResponse;
import ch.ilv.KoSystem.security.Roles;
import ch.ilv.KoSystem.z_favoriteTeam.FavoriteTeam;
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
public class TournamentMemberController {
    private final TournamentMemberService tournamentMemberService;

    TournamentMemberController(TournamentMemberService tournamentMemberService) {
        this.tournamentMemberService = tournamentMemberService;
    }

    @GetMapping("api/tournamentMember")
    @RolesAllowed(Roles.Read)
    public ResponseEntity<List<TournamentMember>> all() {
        List<TournamentMember> result = tournamentMemberService.getTournamentMember();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @GetMapping("api/tournamentMember/{id}")
    @RolesAllowed(Roles.Read)
    public ResponseEntity<TournamentMember> one(@PathVariable Long id) {
        TournamentMember tournamentMember = tournamentMemberService.getTournamentMember(id);
        return new ResponseEntity<>(tournamentMember, HttpStatus.OK);
    }
    @PostMapping("api/tournamentMember")
    @RolesAllowed(Roles.ReadWrite)
    public ResponseEntity<TournamentMember> newTournamentMember(@Valid @RequestBody TournamentMember tournamentMember) {
        TournamentMember savedTournamentMember = tournamentMemberService.insertTournamentMember(tournamentMember);
        return new ResponseEntity<>(savedTournamentMember, HttpStatus.OK);
    }

    @PutMapping("api/tournamentMember/{id}")
    @RolesAllowed(Roles.ReadWrite)
    public ResponseEntity<TournamentMember> updateTournamentMember(@Valid @RequestBody TournamentMember tournamentMember, @PathVariable Long id) {
        TournamentMember updateTournamentMember = tournamentMemberService.updateTournamentMember(tournamentMember, id);
        return new ResponseEntity<>(updateTournamentMember, HttpStatus.OK);
    }

    @DeleteMapping("api/tournamentMember/{id}")
    @RolesAllowed(Roles.ReadWrite)
    public ResponseEntity<MessageResponse> deleteTournamentMember(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(tournamentMemberService.deleteTournamentMember(id));
        } catch (Throwable t) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
