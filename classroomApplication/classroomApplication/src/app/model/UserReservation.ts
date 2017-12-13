import {Room} from './Room';
import {User} from './User';

export class UserReservation {

    id: number;
    user: User;
    room: Room;
    startTime: Date;
    endTime: Date;

    constructor(room?: Room, startTime?: Date, endTime?: Date , user?: User, id?: number){
        this.room = room;
        this.user = user;
        this.startTime = startTime;
        this.endTime = endTime;
        this.id = id;
    }

}
