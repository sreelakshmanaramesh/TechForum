package com.techforum.categorymicroservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AskQuestionDTO {
	private long userId;
	private String topic;
	private String question;
}
