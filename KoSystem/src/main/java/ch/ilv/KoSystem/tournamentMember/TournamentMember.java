package ch.ilv.KoSystem.tournamentMember;

import ch.ilv.KoSystem.Tournament.Tournament;
import ch.ilv.KoSystem.teams.Team;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class TournamentMember {
    @Id
    @GeneratedValue
    private Long id;

 /*   @Column(length = 100, nullable = false)
    @Size(max = 100)
    @NotEmpty
    private String Name;*/
    @OneToOne()
    @JoinColumn(name = "tournament_id", unique = false)
    private Tournament tournament;
    @OneToOne()
    @JoinColumn(name = "team_id", unique = false)
    private Team team;
}
