import { Component, OnInit, ViewChild } from '@angular/core';
import { MatMenuTrigger } from '@angular/material/menu';
import { Router } from '@angular/router';
import { ConfirmationData, ConfirmDialogComponent } from 'src/app/dialog/confirm-dialog/confirm-dialog.component';
import { TweetOperations, TweetOperationsData, TweetOperationsDialogComponent } from 'src/app/dialog/tweet-operations-dialog/tweet-operations-dialog.component';
import { TweetAppAuthService } from 'src/app/services/auth/tweet-app-auth.service';
import { PopupService } from 'src/app/services/popup.service';

@Component({
  selector: 'app-drawer',
  templateUrl: './drawer.component.html',
  styleUrls: ['./drawer.component.scss']
})
export class DrawerComponent implements OnInit {

  @ViewChild(MatMenuTrigger) trigger!: MatMenuTrigger;

  constructor(
    private authService: TweetAppAuthService,
    private popup: PopupService,
    private router: Router
  ) { }

  get username(): string {
    return this.authService.getCurrentUser();
  }

  ngOnInit(): void {
  }

  /**
 * Check if the router url contains the specified route
 *
 * @param {string} route
 * @returns
 * @memberof MyComponent
 */
  hasRoute(route: string) {
    return this.router.url.includes(route);
  }

  showTweetDialog() {
    this.popup.showDialog<TweetOperationsDialogComponent, TweetOperationsData>(
      TweetOperationsDialogComponent,
      { operationType: TweetOperations.tweet }
    );
  }

  openUserMenu() {
    this.trigger.openMenu();
  }

  logout() {
    this.popup.showDialog<ConfirmDialogComponent, ConfirmationData>(
      ConfirmDialogComponent,
      { title: `Logout from @${this.username}`, subtitle: 'Do you wish to logout?' }
    ).afterClosed().subscribe(data => {
      console.log(data);
      if (!!data?.result) {
        localStorage.removeItem('token');
        this.router.navigate(['/login']);
      }
    });
  }

  resetPassword() {
    this.router.navigate(['/reset']);
  }

}
