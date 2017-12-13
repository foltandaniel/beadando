package hu.elte.alkfejl.classroomApplication.model;

import com.fasterxml.jackson.databind.ser.Serializers;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "USER_RESERVATIONS")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserReservation extends BaseEntity {

    @JoinColumn
    @ManyToOne(targetEntity = User.class)
    private User user;

    @JoinColumn
    @ManyToOne(targetEntity = Room.class)
    private Room room;

    private Date startTime;

    private Date endTime;

}

