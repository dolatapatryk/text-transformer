import { Component, OnInit, Input } from '@angular/core';
import { UserTransform } from '../user-transform-model';
import { OwnTransformationService } from '../own-transformation.service';

@Component({
  selector: 'app-own-detail',
  templateUrl: './own-detail.component.html',
  styleUrls: ['./own-detail.component.css']
})
export class OwnDetailComponent implements OnInit {

  @Input() userTransform: UserTransform;

  constructor(private ownTransformationService: OwnTransformationService) { }

  ngOnInit() {
  }


  updateUserTransform(transforms: string) {
    var splitted = this.splitAndTrimTransforms(transforms);
    this.ownTransformationService.updateUserTransform(this.userTransform.name, splitted)
      .subscribe(output => console.log(output));
    this.userTransform = null;
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
