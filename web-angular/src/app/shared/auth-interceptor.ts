import {Injectable} from '@angular/core';
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HttpResponse} from '@angular/common/http';

import {Observable} from 'rxjs';
import {map} from 'rxjs/operators';
import {AuthService} from "./auth-service";
import {ErrorDialogService} from "../components/error-dialog/error-dialog.service";

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor(public errorDialogService: ErrorDialogService) {
  }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const token: string = localStorage.getItem(AuthService.TOKEN_KEY);

    if (token) {
      request = request.clone({headers: request.headers.set('Authorization', 'Bearer ' + token)});
    }

    if (!request.headers.has('Content-Type')) {
      request = request.clone({headers: request.headers.set('Content-Type', 'application/json')});
    }

    request = request.clone({headers: request.headers.set('Accept', 'application/json')});

    return next.handle(request).pipe(
      map((response: HttpEvent<any>) => {
        if (response instanceof HttpResponse) {
          if (response.status === 401 || response.status === 403) {
            console.error('response--->>>', response);
            localStorage.removeItem(AuthService.TOKEN_KEY);
            window.location.href = '/#/account';
          } else if (response.status !== 200) {
            console.error('response--->>>', response);
            this.errorDialogService.openDialog(response);
          }
        }
        return response;
      }));
  }
}
