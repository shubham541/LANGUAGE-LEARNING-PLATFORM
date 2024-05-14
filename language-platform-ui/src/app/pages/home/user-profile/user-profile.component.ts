import {Component, OnInit} from '@angular/core';
import {ResourceService} from "../../../services/resource.service";
import {ActivatedRoute} from "@angular/router";
import {map, Observable, switchMap} from "rxjs";
import {Question} from "../../../model/learning_material";

interface QuestionGroup {
  language: string;
  correct: number;
  wrong: number;
  points: number;
  questions: Question[];
}

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrl: './user-profile.component.scss'
})
export class UserProfileComponent implements OnInit {

  groupedData: QuestionGroup[] = [];

  constructor(
    private languageService: ResourceService,
    private router: ActivatedRoute
  ) {
  }

  ngOnInit(): void {
    this.router.paramMap.pipe(
      map(params => params.get('username')!),
      switchMap(username => this.languageService.getQuizResults(username)),
      map<Question[], QuestionGroup[]>(results => {
        const groupedData: QuestionGroup[] = [];
        results.forEach(result => {
          const language = result.language;
          const group = groupedData.find(group => group.language === language);
          if (group) {
            group.questions.push(result);
            group.correct += result.correct ? 1 : 0;
            group.wrong += result.correct ? 0 : 1;
            group.points += result.correct ? 5 : -5;
          } else {
            groupedData.push({
              language, questions: [result],
              correct: result.correct ? 1 : 0,
              wrong: result.correct ? 0 : 1,
              points: result.correct ? 5 : -5
            });
          }
        });
        return groupedData;
      })
    ).subscribe(data => this.groupedData = data);
  }

}
