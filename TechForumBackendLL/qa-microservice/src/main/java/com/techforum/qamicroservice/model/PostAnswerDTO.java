package com.techforum.qamicroservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostAnswerDTO {
	private long userId;
	private long questionId;
	private String answer;
}
