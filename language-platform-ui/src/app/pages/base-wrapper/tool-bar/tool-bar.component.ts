import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Subject } from 'rxjs';
import {AppConstants} from "../../../shared/constants";

@Component({
  selector: 'app-tool-bar',
  templateUrl: './tool-bar.component.html',
  styleUrls: ['./tool-bar.component.scss']
})
export class ToolBarComponent implements OnInit {

  subject = new Subject();

  get showBackButton(): boolean {
    return this.path !== 'home';
  }

  get path(): string {
    const path = this.loc.path();
    if (path.includes('tweet')) {
      return 'tweet';
    } else {
      const pathArr = path.split('/');
      const lastPath = pathArr[pathArr.length - 1];
      return lastPath.split('?')[0];
    }
  }

  get toolbarText(): string {
    const text = {
      'home': 'Learn a new language today!',
      ...{
        ...AppConstants.languages.reduce((acc: any, curr) => {
          acc[curr.code] = curr.name;
          return acc;
        }, {}),
      }
    }[this.path] ?? this.path;
    return `${text[0].toUpperCase()}${text.slice(1).toLowerCase()}`;
  }


  constructor(
    private loc: Location,
    private router: Router
  ) { }

  ngOnInit(): void {
  }

  goBack(): void {
    this.loc.back();
  }

}
