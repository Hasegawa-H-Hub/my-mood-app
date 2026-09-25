package com.example.mymoodapp.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//このクラスはホームの
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Log {
	//記録を識別
	private Integer id;
	//作成日時
	private LocalDateTime createdAt;
	//更新日時
	private LocalDateTime updatedAt;
	//メモ
	private String memo;
	//気分
	private String mood;

}



