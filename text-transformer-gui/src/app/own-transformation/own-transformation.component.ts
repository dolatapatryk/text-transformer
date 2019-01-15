import { Component, OnInit } from '@angular/core';

import { UserTransform } from '../user-transform-model';
import { OwnTransformationService } from '../own-transformation.service'; 

@Component({
  selector: 'app-own-transformation',
  templateUrl: './own-transformation.component.html',
  styleUrls: ['./own-transformation.component.css']
})
export class OwnTransformationComponent implements OnInit {

  userTransforms: UserTransform[];

  selected: UserTransform;

  constructor(private ownTransformationService: OwnTransformationService) { }

  ngOnInit() {
    this.getUserTransforms();
  }

  onSelect(userTransform: UserTransform): void {
    this.selected = userTransform;
  }

  handleCreateButton(name: string, transformsString: string) {
    var transforms = this.splitAndTrimTransforms(transformsString);

    let userTransform = {
      name: name,
      transforms: transforms
    }
    
    this.ownTransformationService.addUserTransform(userTransform)
      .subscribe(output => {
        console.log(output);
        if(output['status'] === 'OK') {
          this.userTransforms.push(userTransform);
        }
      });

    this.getUserTransforms();
  }

  getUserTransforms() {
    this.ownTransformationService.getUserTransforms()
      .subscribe(tr => this.userTransforms = tr);
  }

  splitAndTrimTransforms(transforms: string) : string[] {
    var splitted = transforms.split(",");
    var splittedTrim = [];
    var i = 0;
    splitted.forEach(function(value){
      splittedTrim[i] = value.trim();
      i++;
    })

    return splittedTrim;
  }

}
