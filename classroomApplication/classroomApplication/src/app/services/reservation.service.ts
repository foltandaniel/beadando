import { Injectable } from '@angular/core';
import {Http} from '@angular/http';
import {Routes, Server} from '../utils/ServerRoutes';
import {Observable} from 'rxjs/Observable';
import {Reservation} from '../model/Reservation';
import {UserReservation} from '../model/UserReservation';
import "rxjs/add/operator/map";

@Injectable()
export class ReservationService {

  constructor(private http: Http) { }

  getReservations(): Observable<UserReservation[]> {
      return this.http.get(Server.routeTo(Routes.RESERVATION))
        .map(res => res.json());
  }

  create(reservation: UserReservation): Observable<UserReservation>{
      return this.http.post(Server.routeTo(Routes.RESERVATION), reservation)
        .map(res => res.json());
  }

  delete(id: number){
      return this.http.delete(Server.routeTo(Routes.RESERVATION) + '/' + id)
        .map(res => res.json());
  }

  read(id: number) {
    return this.http.get(Server.routeTo(Routes.ROOM) + '/' + id)
      .map(res => res.json())
  }

  check(id: number) {
    return this.http.get(Server.routeTo(Routes.ROOM) + '/check/' + id)
      .map(res => res.json())
  }

  getReservedRooms(): Observable<number[]> {
    return this.http.get(Server.routeTo(Routes.ROOM))
      .map(res => res.json())
  }

}
