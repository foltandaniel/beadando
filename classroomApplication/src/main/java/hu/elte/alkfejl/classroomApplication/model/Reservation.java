package hu.elte.alkfejl.classroomApplication.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "RESERVATIONS")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Reservation extends BaseEntity {

    @JoinColumn
    @ManyToOne(targetEntity = Room.class)
    private Room room;

    private Day day;

    private Date startTime;

    private Date endTime;

    public enum Day {
        VASARNAP, HETFO, KEDD, SZERDA, CSUTORTOK, PENTEK, SZOMBAT;
    }
}
