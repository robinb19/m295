package ch.ilv.KoSystem.favoriteTeam;

import ch.ilv.KoSystem.teams.Team;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
public class FavoriteTeam {
    @Id
    @GeneratedValue
    private Long id;
    @Column(length = 100, nullable = false)
    @Size(max = 100)
    @NotEmpty
    private String name;
    @Column(length = 100, nullable = false)
    @Size(max = 100)
    @NotEmpty
    private String firstname;
    @ManyToOne(optional = false)
    @JoinColumn(name = "team_id")
    private Team team;
}
