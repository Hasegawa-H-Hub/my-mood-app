package com.example.mymoodapp.service;

import java.time.LocalDate;
import java.util.List;

import com.example.mymoodapp.entity.Log;

//Log:サービス
public interface LogService {

    //全記録を取得
    List<Log> findAllLog();

    //指定されたIDの記録を取得
    Log findByIdLog(Integer id);

    //新規登録
    void insertLog(Log log);

    // 更新
    void updateLog(Log log);

    //削除
    void deleteLog(Integer id);
    
    //日付検索
    List<Log> findByDate(LocalDate date);

    //気分で絞り込み
    List<Log> findByMoodId(Integer moodId);
}
