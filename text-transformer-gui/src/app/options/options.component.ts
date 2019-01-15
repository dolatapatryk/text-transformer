import { Component, OnInit } from '@angular/core';

import { Option } from '../option-model';
import { OptionsService } from '../options.service'; 

@Component({
  selector: 'app-options',
  templateUrl: './options.component.html',
  styleUrls: ['./options.component.css']
})
export class OptionsComponent implements OnInit {

  options: Option[];

  constructor(private optionsService: OptionsService) { }

  ngOnInit() {
    this.getOptions();
  }

  getOptions(): void {
    this.optionsService.getOptions()
      .subscribe((options: Option[]) =>  {
        this.options = options;
      });
  }

}
