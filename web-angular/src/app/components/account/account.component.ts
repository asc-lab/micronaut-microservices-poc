import {Component} from '@angular/core';
import {FormGroup} from "@angular/forms";
import {FormlyFieldConfig, FormlyFormOptions} from "@ngx-formly/core";
import {AuthService} from "../../shared/auth-service";

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css']
})
export class AccountComponent {

  form = new FormGroup({});
  options: FormlyFormOptions = {};
  model = {
    username: '',
    password: ''
  };
  fields: FormlyFieldConfig[] = [
    {
      key: 'username',
      type: 'input',
      templateOptions: {
        label: 'Username',
        placeholder: 'Enter username',
        required: true
      }
    },
    {
      key: 'password',
      type: 'input',
      templateOptions: {
        label: 'Password',
        placeholder: 'Enter password',
        required: true,
        type: 'password'
      }
    }
  ];

  constructor(private authService: AuthService) {
  }

  submit(model) {
    this.authService.login(model);
  }

  isAuthenticated() {
    return this.authService.isAuthenticated();
  }

  logout() {
    this.authService.logout();
    window.location.reload();
  }
}
