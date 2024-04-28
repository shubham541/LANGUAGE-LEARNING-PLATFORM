import {Component, OnInit} from '@angular/core';
import {ResourceService} from "../../../services/resource.service";

@Component({
  selector: 'app-language',
  templateUrl: './language.component.html',
  styleUrl: './language.component.scss'
})
export class LanguageComponent implements OnInit {

  constructor(private service: ResourceService) {
  }

  ngOnInit(): void {
    this.service.getLearningMaterials()
  }

}
