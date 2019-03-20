import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {Observable} from "rxjs/index";

@Injectable()
export class PolicyService {
  constructor(private http: HttpClient) {
  }

  calculatePrice(request): Observable<any> {
    return this.http.post(`${this.getURL()}/offers`, request);
  }

  getURL() {
    return environment.backend_url ? environment.backend_url : "";
  }
}
