import {Component, OnInit} from '@angular/core';
import {ResourceService} from "../../../services/resource.service";
import {ActivatedRoute} from "@angular/router";
import {PopupService} from "../../../services/popup.service";
import {map, Observable, switchMap} from "rxjs";
import {Question} from "../../../model/learning_material";

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrl: './user-profile.component.scss'
})
export class UserProfileComponent implements OnInit {

  userName!: Observable<string>;

  quizResults!: Observable<Question[]>;

  chartOptions!: Observable<Record<string, any>>;

  constructor(
    private languageService: ResourceService,
    private router: ActivatedRoute
  ) {
  }

  ngOnInit(): void {
    this.userName = this.router.paramMap.pipe(
      map(params => params.get('username')!)
    );
    this.quizResults = this.userName.pipe(
      switchMap(username => this.languageService.getQuizResults(username))
    );
    this.chartOptions = this.quizResults.pipe(
      map<Question[], Record<string, any>>(results => {
        // group results by language
        const groupedResults = results.reduce((acc, result) => {
          if (!acc[result.language]) {
            acc[result.language] = [];
          }
          acc[result.language].push(result);
          return acc;
        }, {} as Record<string, Question[]>);
        // populate chart options for each language group
        let reduce = Object.entries(groupedResults).reduce((acc, [language, results]) => {
          acc[language] = this.getChartOption(language, this.getCorrect(results), this.getIncorrect(results));
          return acc;
        }, {} as Record<string, any>);
        console.log(reduce);
        return reduce;
      })
    );
  }

  private getIncorrect(results: Question[]) {
    return results.filter(result => !result.isCorrect).length;
  }

  private getCorrect(results: Question[]) {
    return results.filter(result => result.isCorrect).length;
  }

  getChartOption(language: string, correct: number, incorrect: number) {
    return {
      title: {
        text: `${language} Language Chart`
      },
      animationEnabled: true,
      axisY: {
        includeZero: true
      },
      data: [{
        type: "column",
        indexLabel: "{y}",
        indexLabelFontColor: "#5A5757",
        dataPoints: [
          {x: 60, y: correct * 10, indexLabel: "Correct Answers"},
          {x: 80, y: incorrect * 10, indexLabel: "Incorrect Answers"},
        ]
      }]
    };
  }

  protected readonly Object = Object;
}
