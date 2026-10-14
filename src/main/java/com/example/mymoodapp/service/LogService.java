package com.example.mymoodapp.service;

import java.time.LocalDate;
import java.util.List;

import com.example.mymoodapp.entity.Log;

//Log:サービス
public interface LogService {

	//全記録を取得
	List<Log> selectAll();

	//指定されたIDの記録を取得
	Log selectById(Integer id);

	//新規登録
	void insert(Log log);

	// 更新
	void update(Log log);

	//削除
	void delete(Integer id);

	//条件を指定して検索
	List<Log> search(
			List<Integer> moodIds,
			LocalDate startDate,
			LocalDate endDate,
			String sort);
}