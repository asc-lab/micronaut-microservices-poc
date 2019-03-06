import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";

@Injectable()
export class AuthService {
  static TOKEN_KEY: string = "jwt";
  static DETAILS_KEY: string = "auth-details";

  AUTH_URL = environment.auth_url ? environment.auth_url : "";
  LOGIN_URL = this.AUTH_URL + "/login";

  constructor(private http: HttpClient) {
  }

  login(credentials) {
    this.clearToken();
    return this.http.post(this.LOGIN_URL, credentials).subscribe(
      (response:any) => {
        localStorage.setItem(AuthService.TOKEN_KEY, response.access_token);
        localStorage.setItem(AuthService.DETAILS_KEY, JSON.stringify(response));
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
    localStorage.removeItem(AuthService.TOKEN_KEY);
    localStorage.removeItem(AuthService.DETAILS_KEY);
  }

  isAuthenticated() {
    return localStorage.getItem(AuthService.TOKEN_KEY) != null;
  }

  getAuthDetails() {
    if (!this.isAuthenticated())
      return null;

    return JSON.parse(localStorage.getItem(AuthService.DETAILS_KEY));
  }
}
