package ch.ilv.KoSystem.Tournament;

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
public class TournamentController {

    private final TournamentService tournamentService;
    TournamentController(TournamentService tournamentService) {
        this.tournamentService = tournamentService;
    }

    @GetMapping("api/tournament")
    @RolesAllowed(Roles.Read)
    public ResponseEntity<List<Tournament>> all() {
        List<Tournament> result = tournamentService.getTournament();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("api/tournament/{id}")
    @RolesAllowed(Roles.Read)
    public ResponseEntity<Tournament> one(@PathVariable Long id) {
        Tournament tournament = tournamentService.getTournament(id);
        return new ResponseEntity<>(tournament, HttpStatus.OK);
    }

    @PostMapping("api/tournament")
    @RolesAllowed(Roles.Read)
    public ResponseEntity<Tournament> newTournament(@Valid @RequestBody Tournament tournament) {
        Tournament savedTournament = tournamentService.insertTournament(tournament);
        return new ResponseEntity<>(savedTournament, HttpStatus.OK);
    }

    @PutMapping("api/tournament/{id}")
    @RolesAllowed(Roles.ReadWrite)
    public ResponseEntity<Tournament> updateTournament(@Valid @RequestBody Tournament tournament, @PathVariable Long id) {
        Tournament updateTournament = tournamentService.updateTournament(tournament, id);
        return new ResponseEntity<>(updateTournament, HttpStatus.OK);
    }

    @DeleteMapping("api/tournament/{id}")
    @RolesAllowed(Roles.ReadWrite)
    public ResponseEntity<MessageResponse> deleteTournament(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(tournamentService.deleteTournament(id));
        } catch (Throwable t) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
