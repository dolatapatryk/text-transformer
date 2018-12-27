import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { OptionsComponent } from './options/options.component';
import { TextComponent } from './text/text.component';
import { OwnTransformationComponent } from './own-transformation/own-transformation.component';
import { OwnDetailComponent } from './own-detail/own-detail.component';

@NgModule({
  declarations: [
    AppComponent,
    OptionsComponent,
    TextComponent,
    OwnTransformationComponent,
    OwnDetailComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
