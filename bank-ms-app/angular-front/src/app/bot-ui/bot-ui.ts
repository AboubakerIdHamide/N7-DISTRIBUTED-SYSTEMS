import { HttpClient } from '@angular/common/http';
import { AsyncPipe } from '@angular/common';
import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MarkdownComponent } from 'ngx-markdown';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-bot-ui',
  imports: [AsyncPipe, FormsModule, MarkdownComponent],
  templateUrl: './bot-ui.html',
  styleUrl: './bot-ui.css'
})
export class BotUi {
  query = '';
  private readonly http = inject(HttpClient);
  responses!: Observable<string>;

  askAgent(): void {
    this.responses = this.http.get(
      'http://localhost:9999/EBANK-BOT/chat?query=' + this.query,
      { responseType: 'text' }
    );
  }
}
