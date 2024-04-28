import {Component, OnInit} from '@angular/core';
import {AppConstants, Language} from "../../../shared/constants";
import {ResourceService} from "../../../services/resource.service";
import {Observable} from "rxjs";


@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrl: './home-page.component.scss'
})
export class HomePageComponent implements OnInit {

  languages!: Observable<Language[]>;

  constructor(private service: ResourceService) {
  }

  ngOnInit() {
    this.languages = this.service.getLanguages();
  }
}
