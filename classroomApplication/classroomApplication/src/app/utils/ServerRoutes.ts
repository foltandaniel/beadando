export class Routes {
  static LOGIN: String = 'user/login';
  static REGISTER: String = 'user/register';
  static RESERVATION: String = 'reservation';
  static ROOM: String = 'room';
  static LOGOUT: String = 'user/logout';
}

export class Server {
  private static address: String = 'localhost';
  private static port: String = '4200';
  private static prefix: String = 'api';

  static routeTo(route: String) {
    return `http://${Server.address}:${Server.port}/${Server.prefix}/${route}`
  }
}


