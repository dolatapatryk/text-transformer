import { Component, OnInit, Input } from '@angular/core';

import { InputModel } from '../input-model';
import { TextService } from '../text.service';

@Component({
  selector: 'app-text',
  templateUrl: './text.component.html',
  styleUrls: ['./text.component.css']
})
export class TextComponent implements OnInit {

  transformatedText: string;

  constructor(private textService: TextService) { }

  ngOnInit() {
  }

  handleTransformButton(text: string, transformsString: string) {
    var transforms = this.splitAndTrimTransforms(transformsString);

    let userInput = {
      text: text,
      transforms: transforms
    }
    
    this.textService.transformText(userInput)
      .subscribe(output => {
        this.transformatedText = output.text;
      })
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
