import {Routes} from '@angular/router';
import {LoginComponent} from "./pages/login/login.component";
import {RegisterComponent} from "./pages/register/register.component";
import {ReservationListComponent} from "./pages/reservation/reservation-list/reservation-list.component";
import {RoomsComponent} from './components/rooms/rooms.component';
import {NewReservationComponent} from "./pages/reservation/new-reservation/new-reservation.component";
import {ErrorComponent} from "./pages/error/error.component";
import {Role} from "./model/User";
import {RouteGuard} from "./route.guard";

export const appRoutes: Routes = [
  {
    path: '',
    canActivateChild: [RouteGuard],
    children: [
      {path: '', redirectTo: 'rooms', pathMatch: 'full'},
      {path: 'login', component: LoginComponent, data: {roles: [Role.GUEST]}},
      {path: 'register', component: RegisterComponent, data: {roles: [Role.GUEST]}},
      {path: 'reservation', component: ReservationListComponent, data: {roles: [Role.USER, Role.ADMIN]}},
      {path: 'reservation/:id', component: NewReservationComponent, data: {roles: [Role.USER, Role.ADMIN]}},
      {path: 'rooms', component: RoomsComponent, data: {roles: [Role.GUEST, Role.USER, Role.ADMIN]}}
    ]
  }];
