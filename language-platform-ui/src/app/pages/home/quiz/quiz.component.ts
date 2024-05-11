import {Component, OnInit} from '@angular/core';
import {ResourceService} from '../../../services/resource.service';
import {Question} from "../../../model/learning_material";
import {map, Observable, switchMap} from "rxjs";
import {ActivatedRoute} from "@angular/router";
import {PopupService} from "../../../services/popup.service";
import {FormControl, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-quiz',
  templateUrl: './quiz.component.html',
  styleUrl: './quiz.component.scss'
})
export class QuizComponent implements OnInit {

  questions!: Observable<Question[]>;

  languageCd!: Observable<string>;

  currentQuestion = 0;

  constructor(
    private languageService: ResourceService,
    private router: ActivatedRoute,
    private popupService: PopupService
  ) {
  }

  ngOnInit(): void {
    this.languageCd = this.router.paramMap.pipe(
      map(params => params.get('code')!)
    );
    this.questions = this.languageCd.pipe(
      switchMap(languageCd => this.languageService.getQuizQuestions(languageCd)),
      map(questions => {
        questions.forEach(question => {
          question.options = this.shuffle([...question.options]);
        });
        return questions;
      })
    );
  }

  protected readonly Object = Object;
  formGroup: FormGroup = new FormGroup({
    answer: new FormControl()
  });

  onCheck(data: Question) {
    const answer = this.formGroup.controls['answer'];
    const result = data.correctAnswer === answer?.value;
    answer.reset();
    this.languageService.updateQuizScore(data.id, result).subscribe(
      () => {
        if (result) {
          this.currentQuestion++;
          this.popupService.showMessage('Correct Answer, try the next one...');
        } else {
          this.popupService.showMessage('Wrong Answer');
        }
      }
    );

  }

  shuffle(options: string[]) {
    return options.sort(() => Math.random() - 0.5);
  }
}
