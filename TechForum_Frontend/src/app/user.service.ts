import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from './user';
import { Observable } from 'rxjs';
import { Question } from './question';
import { Answer } from './answer';


@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http:HttpClient) {

   }

questionId:number=0
   user = new User()

   usrMgmtUrl: string="http://localhost:8080";
   qaUrl:string="http://localhost:8082";
   catUrl: string="http://localhost:8088";
   ratingtUrl: string="http://localhost:8086";


  sendUserData(user:User){
    this.user.userId=user.userId
	this.user.name=user.name
  this.user.email=user.email
  }

  giveUserData(){
    return this.user
   }

   getQuestionId(questionId:number){
    this.questionId=questionId
   }
   sendQuestionId(){
    return this.questionId

   }

	userLogin(email:string, password:string):Observable<User> {
		return this.http.get<User>(this.usrMgmtUrl+"/login/"+email+"/"+password);
	}

	userLogout( userId:number) {
		return this.http.get(this.usrMgmtUrl+"/logout/"+userId);
	}

	userRegister(user:User):Observable<User> {
		return this.http.post<User>(this.usrMgmtUrl+"/register",user)
	}

  askQuestion(askQuestionDTO:any):Observable<Question> {
	  return this.http.post<Question>(this.catUrl+"/postQuestionByCategory/" + askQuestionDTO.topic, askQuestionDTO);
	}

  giveAnswer(postAnswerDTO:any):Observable<Answer> {
	  return this.http.post<Answer>(this.qaUrl+"/postAnswer",postAnswerDTO);
	}

	searchQuestion(question:string):Observable<Question[]> {
	  return this.http.get<Question[]>(this.qaUrl+"/searchQuestionByKeyword/"+question);
	}

  getAnswers(questionId:number):Observable<Answer[]> {
		return this.http.get<Answer[]>(this.qaUrl+"/getAnswers/"+questionId);
	}

	getQuestions(topic:string):Observable<Question[]> {
		return this.http.get<Question[]>(this.catUrl+"/getQuestionsByCategory/"+topic);
	}



}
