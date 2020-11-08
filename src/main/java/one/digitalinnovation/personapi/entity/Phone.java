package one.digitalinnovation.personapi.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import one.digitalinnovation.personapi.enums.PhoneType;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Phone {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID genId;
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private PhoneType type;
    @Column(nullable = false)
    private String number;
    @ManyToOne
    private Person person;

    @PrePersist
    void prePersist() {
        genId = UUID.randomUUID();
    }

}
