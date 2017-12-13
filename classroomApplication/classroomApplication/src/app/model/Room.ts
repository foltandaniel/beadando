export class RoomStatus {
  static RESERVED: String = "RESERVED";
  static FREE: String = "FREE";
}

export class Room {

    id: number;
    name: String;
    status: RoomStatus;

    constructor(name?: String, status?: RoomStatus, id?: number){
        this.name = name;
        this.id = id;
        this.status = status || '';
    }
}
