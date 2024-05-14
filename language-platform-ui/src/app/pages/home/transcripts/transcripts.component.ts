import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {map} from "rxjs";

@Component({
  selector: 'app-transcripts',
  templateUrl: './transcripts.component.html',
  styleUrl: './transcripts.component.scss'
})
export class TranscriptsComponent implements OnInit {
  languageCode: string = 'en';

  constructor(private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.route.params.pipe(
      map(params => params['languageCode'])
    ).subscribe(languageCode => {
        this.languageCode = languageCode;
      }
    );
  }


}
