import {Room} from './Room';

export class Day {
    static VASARNAP: String = 'VASARNAP';
    static HETFO: String = 'HETFO';
    static KEDD: String = 'KEDD';
    static SZERDA: String = 'SZERDA';
    static CSUTORTOK: String = 'CSUTORTOK';
    static PENTEK: String = 'PENTEK';
    static SZOMBAT: String = 'SZOMBAT';
}

export class Reservation {
    id: number;
    room: Room;
    //room: String
    day: Day;
    startTime: Date;
    endTime: Date;

    constructor(room?: Room, startTime?: Date, endTime?: Date, id?: number){
        this.room = room;
        this.startTime = startTime;
        this.endTime = endTime;
        this.id = id;
    }
}
