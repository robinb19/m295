package ch.ilv.KoSystem.teams;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
public class Team {
    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 100, nullable = false)
    @Size(max = 100)
    @NotEmpty
    private String teamName;

    public Team(){

    }

    public Team(String teamName) {
        this.teamName = teamName;
    }
}
