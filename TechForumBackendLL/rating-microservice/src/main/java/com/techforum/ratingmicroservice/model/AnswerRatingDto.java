package com.techforum.ratingmicroservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnswerRatingDto {
	private Long upVotes;
	private Long downVotes;
}
