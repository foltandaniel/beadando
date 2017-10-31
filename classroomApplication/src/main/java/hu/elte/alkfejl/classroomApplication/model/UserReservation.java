package hu.elte.alkfejl.classroomApplication.model;

import com.fasterxml.jackson.databind.ser.Serializers;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USER_RESERVATIONS")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class UserReservation extends BaseEntity {

    private String userId;

    private String roomId;

    private String timeInterval;

}

