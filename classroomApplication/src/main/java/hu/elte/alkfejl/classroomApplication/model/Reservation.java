package hu.elte.alkfejl.classroomApplication.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "RESERVATIONS")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Reservation extends BaseEntity {

    private String roomId;

    private String timeInterval;
}
