import {MatButtonModule, MatFormFieldModule, MatInputModule, MatTableModule, MatToolbarModule, MatGridListModule, MatSelectModule, MatCardModule} from '@angular/material';
import {NgModule} from "@angular/core";

  @NgModule({
      imports: [MatFormFieldModule, MatInputModule, MatButtonModule, MatToolbarModule, MatTableModule, MatGridListModule, MatSelectModule, MatCardModule],
      exports: [MatFormFieldModule, MatInputModule, MatButtonModule, MatToolbarModule, MatTableModule, MatGridListModule, MatSelectModule, MatCardModule],
  })
export class MaterialItemsModule { }
