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
@EqualsAndHashCode(callSuper = true)
public class Room extends BaseEntity{

    @Column(nullable = false, unique = true)
    private String name;

    public enum RoomStatus {
        RESERVED, FREE
    }
}
