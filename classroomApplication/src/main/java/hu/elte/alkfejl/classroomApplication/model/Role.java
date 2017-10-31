package hu.elte.alkfejl.classroomApplication.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "ROLES")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode

public class Role {

    @Id
    private String userId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private Roles role;

    private enum Roles {
        GUEST, USER, ADMIN
    }
}


