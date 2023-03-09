import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Answer } from './answer';
import { Question } from './question';
import { User } from './user';
import { Admin } from './admin';

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  constructor(private http:HttpClient) {

   }

   admin= new Admin()

   adminUrl:string="http://localhost:8080/admin"
   qaUrl:string = "http://localhost:8082"

   sendAdminData(admin:Admin){
    console.log("admin data coming from "+admin.id)
    this.admin.id=admin.id
    this.admin.email=admin.email
    this.admin.name=admin.name
   }

   giveAdminData(){
    return this.admin
   }

   adminLogin(email:string,password:string):Observable<Admin>{
    return this.http.get<Admin>(this.adminUrl+"/login/"+email+"/"+password)
   }

   adminLogout(adminId:number):Observable<string>{
    return this.http.get<string>(this.adminUrl+"/logout/"+adminId)
   }

   adminRegister(admin:Admin):Observable<Admin>{
    return this.http.post<Admin>(this.adminUrl+"/register",admin)
   }

   getUnApprovedQuestions():Observable<Question[]>{
     return this.http.get<Question[]>(this.qaUrl+"/getUnApprovedQuestions")
   }
   getUnApprovedAnswers():Observable<Answer[]>{
     return this.http.get<Answer[]>(this.qaUrl+"/getUnApprovedAnswers")
   }

   question={
  }
   approveQuestion(questionId:number):Observable<Question>{
     return this.http.put<Question>(this.qaUrl+"/approveQuestion/"+questionId,this.question)

   }

   approveAnswer(answerId:number):Observable<Answer>{
     return this.http.put<Answer>(this.qaUrl+"/approveAnswer/"+answerId,Answer)
   }
   deleteQuestion(questionId:number){
     return this.http.delete(this.qaUrl+"/deleteQuestion/"+questionId)

   }
   deleteAnswer(answerId:number){
     return this.http.delete(this.qaUrl+"/deleteAnswer/"+answerId)

   }
   getUser(email:string):Observable<User> {
     return this.http.get<User>(this.qaUrl+"/getUser/"+email);
   }

   getAllUsers():Observable<User[]>{
    return this.http.get<User[]>(this.qaUrl+"/getAllUsers")
   }

}
