import { User } from "./user";
import { Question } from './question';

export class Answer {

  id:number=0
  answer:string=''
	isApproved :boolean= false
  answeredByUserId:number
  answeredByUser:string = ''
  question:any
}
