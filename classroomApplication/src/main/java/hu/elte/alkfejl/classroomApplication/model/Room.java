package hu.elte.alkfejl.classroomApplication.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "ROOMS")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Room extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "RESERVATION.ROOM_ID")
    private String id;

    @Column(nullable = false, unique = true)
    private String name;
}
