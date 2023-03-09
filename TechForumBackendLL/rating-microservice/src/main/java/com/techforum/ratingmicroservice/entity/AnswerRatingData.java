package com.techforum.ratingmicroservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnswerRatingData implements Comparable<AnswerRatingData>{
	@Id
	private Long answerId;
	private Long qId;
	private Long upVotes;
	private Long downVotes;
	@Override
	public int compareTo(AnswerRatingData o) {
		if(this.upVotes==o.getUpVotes())  
			return 0;  
			else if(this.upVotes < o.getUpVotes())  
			return 1;  
			else  
			return -1;  
			}
}
