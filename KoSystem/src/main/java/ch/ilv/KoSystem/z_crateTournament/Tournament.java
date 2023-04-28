package ch.ilv.KoSystem.z_crateTournament;

import ch.ilv.KoSystem.z_tournamentMember.TournamentMember;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Entity
public class Tournament {
    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 100, nullable = false)
    @Size(max = 100)
    @NotEmpty
    private String turniername;
    @Column(length = 100, nullable = false)
    @NotEmpty
    private String veranstaltungsOrt;
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    @NotNull
    private Date toDate;
}

