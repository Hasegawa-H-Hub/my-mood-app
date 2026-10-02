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

    // 日付検索
    List<Log> selectByDate(@Param("date")LocalDate date);

    // 気分で絞り込み
    List<Log> selectByMoodId(@Param("moodid") Integer moodId);

}
