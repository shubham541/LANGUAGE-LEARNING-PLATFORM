import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Subject, takeUntil } from 'rxjs';
import { UserDto } from '../../../model/userDto';
import { ResourceService } from '../../../services/resource.service';
import { PopupService } from '../../../services/popup.service';

enum SearchStage {
  notStarted,
  started,
  loaded,
  notFound
}
@Component({
  selector: 'app-search-bar',
  templateUrl: './search-bar.component.html',
  styleUrls: ['./search-bar.component.scss']
})
export class SearchBarComponent implements OnInit {

 
  searchForm!: FormGroup;
  subject = new Subject();
  searchResult?: Array<UserDto>;
  stage = SearchStage.notStarted;
  stages = SearchStage;
  started = false;


  get searchbar(): AbstractControl | null {
    return this.searchForm.get('search');
  }

  get searchText(): string {
    return this.searchForm.get('search')?.value || '';
  }

  constructor(
    private route: ActivatedRoute,
    private fb: FormBuilder,
    private resourceService: ResourceService,
    private popup: PopupService
  ) { }

  ngOnInit(): void {
    this.searchForm = this.fb.group({
      search: ['']
    });
  }

  ngAfterViewInit(): void {
    setTimeout(() => {
      this.observeSearchBar();
    });
  }


  private observeSearchBar() {
    this.searchbar?.valueChanges
      .pipe(takeUntil(this.subject))
      .subscribe(_ => {
        if (this.searchText.length >= 3) {
          if (this.stage != SearchStage.started) {
            this.stage = SearchStage.started;
            // this.resourceService.searchByUserameUsingGET(this.searchText)
            //   .pipe(takeUntil(this.subject))
            //   .subscribe({
            //     next: (pld) => {
            //       this.searchResult = pld.data;
            //       this.stage = SearchStage.loaded;
            //     },
            //     error: (err) => {
            //       this.stage = SearchStage.notFound;
            //       if (err?.status != 404) {
            //         this.popup.showError(err);
            //       }
            //     }
            //   });
          }
        } else {
          this.resetSearch();
        }

      });
  }

  resetSearch() {
    if (this.stage != SearchStage.started) {
      this.searchResult = undefined;
      this.stage = SearchStage.notStarted;
    }
  }

}
