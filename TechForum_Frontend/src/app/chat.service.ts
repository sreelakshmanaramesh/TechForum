import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { MessageDTO } from './messageDTO';

@Injectable({
  providedIn: 'root'
})
export class ChatService {

  constructor( private http:HttpClient) { }

  chatUrl:string="http://localhost:8084"

	sendMessage(messageDTO:any):Observable<MessageDTO> {
		return this.http.post<MessageDTO>(this.chatUrl + "/postMessage" ,messageDTO);
	}

	getMessage():Observable<MessageDTO[]> {
		return this.http.get<MessageDTO[]>(this.chatUrl+"/getMessages");
	}


}
