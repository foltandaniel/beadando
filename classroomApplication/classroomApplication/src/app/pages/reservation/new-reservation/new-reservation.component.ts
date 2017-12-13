import { Component, OnInit } from '@angular/core';
import {Room} from "../../../model/Room";
import {ReservationService} from "../../../services/reservation.service";
import {ActivatedRoute, Router} from "@angular/router";
import {UserReservation} from "../../../model/UserReservation";

class Intervals {
  static HALFHOUR : String = '30 minutes';
  static ONEHOUR : String = '1 Hour';
  static TWOHOUR : String = '2 Hour';
}

@Component({
  selector: 'app-new-reservation',
  templateUrl: './new-reservation.component.html',
  styleUrls: ['./new-reservation.component.scss']
})

export class NewReservationComponent implements OnInit {
  room: Room = new Room();
  id: number;
  date: Date;
  userReservation : UserReservation = new UserReservation();

  intervals : String[] = [Intervals.HALFHOUR, Intervals.ONEHOUR, Intervals.TWOHOUR];
  interval : String;

  constructor(private reservationService: ReservationService,
              private route: ActivatedRoute,
              private router: Router) {
    this.route.params.subscribe(
      params => this.id = params.id,
      err => console.log(err)
    );
  }

  create(): void{
    this.userReservation.startTime = new Date();
    this.userReservation.endTime = new Date();

    if(this.interval == Intervals.HALFHOUR){
      this.userReservation.endTime.setMinutes(this.userReservation.endTime.getMinutes() + 30);
    } else if(this.interval == Intervals.ONEHOUR){
      this.userReservation.endTime.setHours(this.userReservation.endTime.getHours() + 1);
    } else if(this.interval == Intervals.TWOHOUR){
      this.userReservation.endTime.setHours(this.userReservation.endTime.getHours() + 2);
    }

    this.userReservation.room = this.room;

    console.log(this.userReservation);
    this.reservationService.create(this.userReservation)
      .subscribe(
        res => this.router.navigate(['/rooms']),
        err => console.log(err)
      )
  }

  updateStatus(event) {
    this.interval = event.source.triggerValue;
  }


  ngOnInit() {
    this.reload();
  }

  private reload() {
    this.reservationService.read(this.id)
      .subscribe(
        room => this.room = room,
        err => console.log(err)
      )
    this.reservationService.check(this.id)
      .subscribe(
        room => this.room.status = room,
        err => console.log(err)
      );
    this.date = new Date();
    this.interval = Intervals.HALFHOUR;
  }


}
