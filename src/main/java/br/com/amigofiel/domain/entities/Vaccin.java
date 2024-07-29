package br.com.amigofiel.domain.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor

@Entity
public class Vaccin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "vaccination_date", nullable = false)
    private Date vaccinationDate;

    @Column(name = "manufacturer_lab", nullable = false)
    private String manufacturerLab;

    @Column(name = "total_doses", nullable = false)
    private int totalDoses;

    @Column(name = "doses_taken", nullable = false)
    private int dosesTaken;

    @ManyToOne
    @JoinColumn(name = "animal_id", nullable = false)
    private Animal animalId;

    public boolean hasNextDose(){
        return totalDoses > dosesTaken;
    }
}
