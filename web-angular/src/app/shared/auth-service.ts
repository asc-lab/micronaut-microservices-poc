import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";

@Injectable()
export class AuthService {

  AUTH_URL = environment.auth_url ? environment.auth_url : "/";
  LOGIN_URL = this.AUTH_URL + "login";
  TOKEN_KEY: string = "jwt";
  DETAILS_KEY: string = "auth-details";

  constructor(private http: HttpClient) {
  }

  login(credentials) {
    this.clearToken();
    return this.http.post(this.LOGIN_URL, credentials).subscribe(
      (response:any) => {
        localStorage.setItem(this.TOKEN_KEY, response.access_token);
        localStorage.setItem(this.DETAILS_KEY, JSON.stringify(response));
        window.location.href = '/';
      }, error => {
        console.error(error);
      }
    );
  }

  logout() {
    this.clearToken();
  }

  clearToken() {
    localStorage.removeItem(this.TOKEN_KEY);
    localStorage.removeItem(this.DETAILS_KEY);
  }

  isAuthenticated() {
    return localStorage.getItem(this.TOKEN_KEY) != null;
  }

  getAuthDetails() {
    if (!this.isAuthenticated())
      return null;

    return JSON.parse(localStorage.getItem(this.DETAILS_KEY));
  }
}
