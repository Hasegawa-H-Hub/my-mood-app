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

    //全記録を検索
    @Override
    public List<Log> findAllLog() {
        return logMapper.selectAll();
    }

    //指定されたIDの記録を検索
    @Override
    public Log findByIdLog(Integer id) {
        return logMapper.selectById(id);
    }

    //指定された日付の記録を検索
    @Override
    public List<Log> findByDate(LocalDate date) {
        return logMapper.selectByDate(date);
    }

    //指定された気分IDの記録を検索
    @Override
    public List<Log> findByMoodId(Integer moodId) {
        return logMapper.selectByMoodId(moodId);
    }

    //記録を新規登録します
    @Override
    public void insertLog(Log log) {
        logMapper.insert(log);
    }

    //記録を更新します
    @Override
    public void updateLog(Log log) {
        logMapper.update(log);
    }

    //指定されたIDの記録を削除します
    @Override
    public void deleteLog(Integer id) {
        logMapper.delete(id);
    }

}

