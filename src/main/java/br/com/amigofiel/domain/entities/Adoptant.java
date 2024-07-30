package br.com.amigofiel.domain.entities;

import br.com.amigofiel.domain.enums.Gender;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor

@Entity
public class Adoptant {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "birth_date")
    private Date birthDate;

    @Column(name = "gender", nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    @OneToMany(mappedBy = "adopter")
    private List<Adoption> adoptions;

    @OneToOne(mappedBy = "adoptant")
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
