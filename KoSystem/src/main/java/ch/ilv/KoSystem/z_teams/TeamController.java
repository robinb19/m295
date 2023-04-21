package ch.ilv.KoSystem.z_teams;

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
public class TeamController {
    private final TeamService teamService;

    TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping("api/team")
    @RolesAllowed(Roles.Admin)
    public ResponseEntity<List<Team>> all() {
        List<Team> result = teamService.getTeam();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("api/team/{id}")
    @RolesAllowed(Roles.Admin)
    public ResponseEntity<Team> one(@PathVariable Long id) {
        Team team = teamService.getTeam(id);
        return new ResponseEntity<>(team, HttpStatus.OK);
    }

    @PostMapping("api/team")
    @RolesAllowed(Roles.Admin)
    public ResponseEntity<Team> newTeam(@Valid @RequestBody Team team) {
        Team savedTeam = teamService.insertTeam(team);
        return new ResponseEntity<>(savedTeam, HttpStatus.OK);
    }

    @PostMapping("api/team/{id}")
    @RolesAllowed(Roles.Admin)
    public ResponseEntity<Team> updateTeam(@Valid @RequestBody Team team, @PathVariable Long id) {
        Team updateTeam = teamService.updateTeam(team, id);
        return new ResponseEntity<>(updateTeam, HttpStatus.OK);
    }

    @DeleteMapping("api/employee/{id}")
    @RolesAllowed(Roles.Admin)
    public ResponseEntity<MessageResponse> deleteTeam(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(teamService.deleteTeam(id));
        } catch (Throwable t) {
            return ResponseEntity.internalServerError().build();
        }
    }


}
