import { Component, OnInit, ViewChild } from '@angular/core';
import { MatMenuTrigger } from '@angular/material/menu';
import { Router } from '@angular/router';
import { AuthService } from '../../../services/auth.service';
import { PopupService } from '../../../services/popup.service';
import { ConfirmDialogComponent, ConfirmationData } from '../../../shared/dialog/confirm-dialog/confirm-dialog.component';

@Component({
  selector: 'app-drawer',
  templateUrl: './drawer.component.html',
  styleUrls: ['./drawer.component.scss']
})
export class DrawerComponent implements OnInit {

  @ViewChild(MatMenuTrigger) trigger!: MatMenuTrigger;

  constructor(
    private authService: AuthService,
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
