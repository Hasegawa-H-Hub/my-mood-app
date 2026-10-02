package com.example.mymoodapp.service;

import java.time.LocalDate;
import java.util.List;

import com.example.mymoodapp.entity.Log;

public interface LogService {
	/**
	 *全記録を取得
	 */
	List<Log>findAllLog();
	
	/**
	 * 指定されたIDから記録を取得
	 */
	Log findByIdLog(Integer id);
	
	/**
	 * 新規登録します
	 */
	void insertLog(Log log);
	
	/**
	 * 更新します
	 */
	void updateLog(Log log);
	
	/**
	 * 指定されたIDの記録を削除します
	 */
	void deleteLog(Integer id);
	
	/**
	 * 日付検索
	 */
	List<Log> findByDate(LocalDate date);
	
	/**
	 * 気分で絞り込み
	 */
	List<Log> findByMoodId(Integer moodId);

}
