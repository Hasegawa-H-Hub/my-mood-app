package com.example.mymoodapp.helper;

import com.example.mymoodapp.entity.Log;
import com.example.mymoodapp.entity.Mood;
import com.example.mymoodapp.form.LogForm;

public class LogHelper {

	//Logへの変換
    public static Log convertToLog(LogForm form) {

        Log log = new Log();

        log.setId(form.getId());

        log.setMemo(form.getMemo());

        Mood mood = new Mood();	
        mood.setId(form.getMoodId());

        log.setMood(mood);

        return log;
    }

    //LogFormへの変換
    public static LogForm convertToLogForm(Log log) {

        LogForm form = new LogForm();

        form.setId(log.getId());

        form.setMemo(log.getMemo());

        form.setMoodId(log.getMood().getId());

       //更新画面の設定
        form.setIsNew(false);

        return form;  
    }
    
}

