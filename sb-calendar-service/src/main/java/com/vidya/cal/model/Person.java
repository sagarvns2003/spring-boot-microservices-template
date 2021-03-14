package com.vidya.cal.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Builder
@AllArgsConstructor
@Data
public class Person {

	private String id;
	private String name;
	
	@EqualsAndHashCode.Exclude
	private String avatar; // www.gravatar.com/avatar/88c3d17245bc58e95d3ed6df1479108e
	
	@EqualsAndHashCode.Exclude
	private String email;
	
	@EqualsAndHashCode.Exclude
	private String phone;
}