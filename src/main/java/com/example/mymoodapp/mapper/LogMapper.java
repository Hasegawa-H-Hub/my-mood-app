package com.example.mymoodapp.mapper;

import java.time.LocalDate;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.mymoodapp.entity.Log;

@Mapper
public interface LogMapper {

	// 全記録を取得
	List<Log> selectAll();

	// IDから記録を取得
	Log selectById(@Param("id") Integer id);

	// 新規登録
	void insert(Log log);

	// 更新
	void update(Log log);

	// 削除
	void delete(@Param("id") Integer id);

	// 検索条件を指定して絞り込み
	List<Log> search(
			@Param("moodIds") List<Integer> moodIds,
			@Param("startDate") LocalDate startDate,
			@Param("endDate") LocalDate endDate,
			@Param("sort") String sort);
}