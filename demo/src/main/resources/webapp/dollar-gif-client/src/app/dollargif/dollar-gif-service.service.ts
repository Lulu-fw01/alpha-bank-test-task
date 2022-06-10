import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DollarGifService {

  private url = 'http://localhost:8080/currency-gif/';

  constructor(private http: HttpClient) {
  }

  getRate(base: string): Observable<any> {
    return this.http.get(`${this.url}/gif/${base}`);
  }

  /*
  addBook(book: Object): Observable<Object> {
    return this.http.post(`${this.url}`, book);
  }

  deleteBook(id: number): Observable<any> {
    return this.http.delete(`${this.url}/${id}`, {responseType: 'text'});
  }*/
}