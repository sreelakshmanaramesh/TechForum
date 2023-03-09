package com.techforum.categorymicroservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionRatingData implements Comparable<QuestionRatingData>{
	private Long questionId;
	private Long views;
	private Long intrest;
	
	@Override
	public int compareTo(QuestionRatingData o) {
		if(this.views==o.getViews())  
			return 0;  
			else if(this.views < o.getViews())  
			return 1;  
			else  
			return -1;  
			}
}
