import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DollarGifService {

  private url = 'http://localhost:8080/currency-gif';

  constructor(private http: HttpClient) {
  }

  getRate(base: string): Observable<any> {
    console.log("Making getRate request.");
    return this.http.get(`${this.url}/gif/${base}`);
  }
}
