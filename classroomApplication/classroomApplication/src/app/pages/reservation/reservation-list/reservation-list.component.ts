import {Component} from '@angular/core';
import {DataSource} from "@angular/cdk/collections";
import {Observable} from "rxjs/Observable";
import "rxjs/add/observable/of";
import {UserReservation} from "../../../model/UserReservation";
import {ReservationService} from "../../../services/reservation.service";


@Component({
  selector: 'app-reservation-list',
  templateUrl: './reservation-list.component.html',
  styleUrls: ['./reservation-list.component.css']
})
export class ReservationListComponent {
  displayedColumns: String[] = ['user', 'room', 'start', 'end', 'edit'];
  userReservations: DataSource<any> = new ReservationDataSource(this.reservationService);

  constructor(private reservationService: ReservationService){
  }

  delete(id: number){
    this.reservationService.delete(id)
          .subscribe(
            res => console.log(res),
            err => console.log(err)
          );
    setTimeout(this.reload(), 1000);
  }

  reload() {
    this.userReservations = new ReservationDataSource(this.reservationService);
  }
}
/*
const data: UserReservation[] = [
  new UserReservation(undefined, new Room('Terem1',RoomStatus.FREE, 1), new Date(), new Date()),
  new UserReservation(undefined, new Room('Terem2',RoomStatus.RESERVED, 2), new Date(), new Date())
];
*/
export class ReservationDataSource extends DataSource<any> {

  constructor(private reservationService: ReservationService){
      super();
  }

  connect(): Observable<UserReservation[]> {
    return this.reservationService.getReservations();
  }

  disconnect() {
  }
}
