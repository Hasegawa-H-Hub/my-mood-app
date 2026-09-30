package com.example.mymoodapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class Mood {
	//気分を一意に識別するID
	private Integer id;
	//気分名（happy / fun /normal / sad angry）	
	private String moodName;
	//気分名（日本語表示）
	private String moodJa;
}
