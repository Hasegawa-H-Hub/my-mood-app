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

    //指定された日付の記録を検索
    List<Log> findByDate(LocalDate date);

    //指定された気分IDの記録を検索
    List<Log> findByMoodId(Integer moodId);

    //記録を新規登録します
    void insertLog(Log log);

    //記録を更新します
    void updateLog(Log log);

    //指定されたIDの記録を削除します
    void deleteLog(Integer id);
}
