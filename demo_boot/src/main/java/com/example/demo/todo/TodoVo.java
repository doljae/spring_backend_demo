package com.example.demo.todo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class TodoVo {
	private Long id;
	private String item;
	private Boolean completed;

}
