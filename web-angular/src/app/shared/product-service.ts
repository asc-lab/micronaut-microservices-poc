import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {Observable} from "rxjs/index";

@Injectable()
export class ProductService {
  constructor(private http: HttpClient) {
  }

  getProducts(): Observable<any> {
    return this.http.get(`${this.getURL()}/products`);
  }

  getProduct(productCode: string) {
    return this.http.get(`${this.getURL()}/products/${productCode}`);
  }

  getURL() {
    return environment.backend_url ? environment.backend_url : "";
  }
}
