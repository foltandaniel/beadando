import {Component, OnDestroy, OnInit} from '@angular/core';
import {ReservationService} from "../../services/reservation.service";


@Component({
  selector: 'app-grid-list',
  templateUrl: './rooms.component.html',
  styleUrls: ['./rooms.component.scss']
})

export class RoomsComponent implements OnInit, OnDestroy {

  tiles = [];
  reservedRooms = [];

  RESERVED: String = "#f44336";
  GREY: String = "#C0C0C0";
  ROOM: String = "#8bc34a";
  STAIRS: String = "#A0A0A0";
  INACTIVE: String = "#A0A0A0";
  WHITE: String = "white";

  refreshRooms; //Room refresh setInterval

  constructor(private reservationService: ReservationService){
  }

  getReservedRooms(): void {
    this.reservationService.getReservedRooms()
      .subscribe(rooms => this.reservedRooms = rooms);
  }

  ngOnInit(){
    this.getReservedRooms();
    this.refreshRooms = setInterval(() => {
      this.getReservedRooms();
    }, 5000);
    this.build();
  }
  ngOnDestroy() {
    if (this.refreshRooms) {
      clearInterval(this.refreshRooms);
    }
  }

  build():void {
    this.tiles[0] = {id:1, text: '2-402 - Multimédiás labor', cols: 15, rows: 2, color: this.ROOM, link:'/reservation/1'};
    this.tiles[1] = {id:-1, text: '', cols: 2, rows: 18, color: this.GREY};
    this.tiles[2] = {id:2, text: '2-502', cols: 15, rows: 2, color: this.ROOM, link:'/reservation/2'};
    this.tiles[3] = {id:-1, text: '', cols: 15, rows: 1, color: this.GREY};
    this.tiles[4] = {id:-1, text: '', cols: 15, rows: 1, color: this.GREY};
    this.tiles[5] = {id:-1, text: '', cols: 2, rows: 12, color: this.INACTIVE};
    this.tiles[6] = {id:-1, text: '', cols: 1, rows: 12, color: this.GREY};
    this.tiles[7] = {id:-1, text: '', cols: 10, rows: 2, color: this.INACTIVE};
    this.tiles[8] = {id:-1, text: 'LEPCSO', cols: 2, rows: 2, color: this.STAIRS};
    this.tiles[9] = {id:-1, text: 'LIFT', cols: 2, rows: 2, color: this.STAIRS};
    this.tiles[10] = {id:3, text: '2-520 - Mesterséges Intelligencia labor', cols: 8, rows: 2, color: this.ROOM, link:'/reservation/3'};
    this.tiles[11] = {id:-1, text: 'LEPCSO', cols: 2, rows: 2, color: this.STAIRS};
    this.tiles[12] = {id:-1, text: '', cols: 1, rows: 12, color: this.GREY};
    this.tiles[13] = {id:-1, text: '', cols: 2, rows: 12, color: this.INACTIVE};
    this.tiles[14] = {id:-1, text: 'X', cols: 10, rows: 8, color: this.WHITE};
    this.tiles[15] = {id:4, text: '2-123 (PC1)', cols: 2, rows: 4, color: this.ROOM, link:'/reservation/4'};
    this.tiles[16] = {id:5, text: '2-108 (PC5)', cols: 2, rows: 4, color: this.ROOM, link:'/reservation/5'};
    this.tiles[17] = {id:-1, text: 'X', cols: 10, rows: 8, color: this.WHITE};
    this.tiles[18] = {id:6, text: '2-124 Microsoft Labor', cols: 2, rows: 4, color: this.ROOM, link:'/reservation/6'};
    this.tiles[19] = {id: 7, text: '2-107 (PC2) Macintosh', cols: 2, rows: 4, color: this.ROOM, link:'/reservation/7'};
    this.tiles[20] = {id: 8, text: '2-218 - komp. alg.', cols: 5, rows: 2, color: this.ROOM, link:'/reservation/8'};
    this.tiles[21] = {id: 9, text: '2-219 - Grafika labor', cols: 5, rows: 2, color: this.ROOM, link:'/reservation/9'};
    this.tiles[22] = {id:-1, text: 'LEPCSO', cols: 2, rows: 2, color: this.STAIRS};
    this.tiles[23] = {id:-1, text: 'LIFT', cols: 2, rows: 2, color: this.STAIRS};
    this.tiles[24] = {id: 10, text: '2-712', cols: 8, rows: 2, color: this.ROOM, link:'/reservation/10'};
    this.tiles[25] = {id:-1, text: 'LEPCSO', cols: 2, rows: 2, color: this.STAIRS};
    this.tiles[26] = {id:-1, text: '', cols: 15, rows: 1, color: this.GREY};
    this.tiles[27] = {id:-1, text: '', cols: 15, rows: 1, color: this.GREY};
    this.tiles[28] = {id:11, text: '2-202 (PC3)', cols: 15, rows: 2, color: this.ROOM, link:'/reservation/11'};
    this.tiles[29] = {id:12, text: '2-710 (PC4)', cols: 7, rows: 2, color: this.ROOM, link:'/reservation/12'};
    this.tiles[30] = {id:13, text: '2-709 (PC 9)', cols: 8, rows: 2, color: this.ROOM, link:'/reservation/13'};
  }
}
