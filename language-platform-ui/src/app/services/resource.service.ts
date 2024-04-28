import { Injectable } from '@angular/core';
import {map, Observable, switchMap} from "rxjs";
import {environment} from "../env/environment";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Configuration} from "../configuration";
import {JwtHelperService} from "@auth0/angular-jwt";
import {AppConstants, Language} from "../shared/constants";

@Injectable({
  providedIn: 'root'
})
export class ResourceService {

  protected basePath = environment.resourceBasePath;
  public defaultHeaders = new HttpHeaders();
  public configuration = new Configuration();

  constructor(
    protected httpClient: HttpClient,
    public jwtHelper: JwtHelperService
  ) { }

  getLanguages(): Observable<Language[]> {
    return this.httpClient.get<Language[]>(`${this.basePath}/languages`).pipe(
      map(languages => AppConstants.languages = languages)
    );
  }

  getLearningMaterials(): Observable<any> {
    return new Observable<any>(observer => {
    });
  }
}
