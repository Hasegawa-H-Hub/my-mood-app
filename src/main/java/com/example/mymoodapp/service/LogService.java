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
    
    //日付検索
    List<Log> selectByDate(LocalDate date);

    //気分で絞り込み
    List<Log> selectByMoodId(Integer moodId);
}
