import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";

@Injectable()
export class PolicyService {
  constructor(private http: HttpClient) {
  }



  getURL() {
    return environment.backend_url ? environment.backend_url : "";
  }
}
