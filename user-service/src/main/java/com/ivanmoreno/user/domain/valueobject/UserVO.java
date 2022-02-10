package com.ivanmoreno.user.domain.valueobject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class UserVO {

	private String name;
	private String id;
	private String address;
	private String city;
	private String phoneNo;
	
}
