import { HttpClient } from '@angular/common/http';
import { AsyncPipe } from '@angular/common';
import { Component, inject } from '@angular/core';
import { Observable, catchError, map, of } from 'rxjs';
import { Account, AccountListState, RequestStatus } from '../model/account.model';

@Component({
  selector: 'app-accounts',
  imports: [AsyncPipe],
  templateUrl: './accounts.html',
  styleUrl: './accounts.css'
})
export class Accounts {
  protected readonly RequestStatus = RequestStatus;
  private readonly http = inject(HttpClient);

  readonly accounts$: Observable<AccountListState> = this.http
    .get<Account[]>('http://localhost:9999/EBANK-SERVICE/accounts')
    .pipe(
      map(accounts => ({ accounts, status: RequestStatus.SUCCESS })),
      catchError(error => of({
        accounts: [],
        status: RequestStatus.ERROR,
        errorMessage: error.message ?? 'Could not load accounts'
      }))
    );
}
