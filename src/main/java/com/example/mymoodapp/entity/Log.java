package com.example.mymoodapp.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * 記録（Log）を表すEntityクラス。
 * 日付・ひとことメモ・気分IDなど、1件の記録に必要な情報を保持する。
 * データベースの「log」テーブルに対応する。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Log {
	//記録を一意に識別するID
	private Integer id;
	//新規の日付
	private LocalDateTime createdAt;
	//更新の日時
	private LocalDateTime updatedAt;
	//ひとことメモ（最大200文字）
	private String memo;
	//気分ID（moodテーブルへの外部キー）
	private Integer moodId;

}



