import {Component, OnInit} from '@angular/core';
import {ResourceService} from "../../../services/resource.service";
import {ActivatedRoute} from "@angular/router";
import {map, Observable, switchMap} from "rxjs";
import {LearningMaterial} from "../../../model/learning_material";

@Component({
  selector: 'app-language',
  templateUrl: './language.component.html',
  styleUrl: './language.component.scss'
})
export class LanguageComponent implements OnInit {

  learningMaterials!: Observable<LearningMaterial>;

  constructor(private service: ResourceService, private router: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.learningMaterials = this.router.paramMap.pipe(
      map(params => params.get('code')!),
      switchMap(languageCd => this.service.getLearningMaterials(languageCd))
    );
  }

  protected readonly Object = Object;
}
