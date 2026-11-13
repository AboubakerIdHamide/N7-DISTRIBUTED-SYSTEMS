export interface Account {
  id?: string;
  balance?: number;
  createdAt?: string;
  type?: string;
  customerId?: number;
}

export interface AccountListState {
  accounts: Account[];
  status: RequestStatus;
  errorMessage?: string;
}

export enum RequestStatus {
  SUCCESS = 'SUCCESS',
  ERROR = 'ERROR'
}
