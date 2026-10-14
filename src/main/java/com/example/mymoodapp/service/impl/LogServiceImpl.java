package com.example.mymoodapp.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.mymoodapp.entity.Log;
import com.example.mymoodapp.mapper.LogMapper;
import com.example.mymoodapp.service.LogService;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class LogServiceImpl implements LogService{

	//DI
    private final LogMapper logMapper;

    //全記録を取得
    @Override
    public List<Log> selectAll() {
        return logMapper.selectAll();
    }
    
    //IDから記録を取得
    @Override
    public Log selectById(Integer id) {
        return logMapper.selectById(id);
    }
   
    //新規登録
    @Override
    public void insert(Log log) {
        logMapper.insert(log);
    }
    
    // 更新
    @Override
    public void update(Log log) {
        logMapper.update(log);
    }

    //削除
    @Override
    public void delete(Integer id) {
        logMapper.delete(id);
    }
    
    //日付検索
    @Override
    public List<Log> selectByDate(LocalDate date) {
        return logMapper.selectByDate(date);
    }

    //気分で絞り込み
    @Override
    public List<Log> selectByMoodId(Integer moodId) {
        return logMapper.selectByMoodId(moodId);
    }
    //気分・期間・並び順で検索
    @Override
    public List<Log> search(
            List<Integer> moodIds,
            LocalDate startDate,
            LocalDate endDate,
            String sort) {
 
        // 気分が全解除なら、Mapperを呼ばず空の結果を返す
        if (moodIds == null || moodIds.isEmpty()) {
            return List.of();
        }
 
        return logMapper.search(moodIds, startDate, endDate, sort);
    }
 
}
 