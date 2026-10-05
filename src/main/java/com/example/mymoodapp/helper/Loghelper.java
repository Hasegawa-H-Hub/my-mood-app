package com.example.mymoodapp.helper;

import com.example.mymoodapp.entity.Log;
import com.example.mymoodapp.form.LogForm;

public class Loghelper {

	 //Logへの変換
    public static Log convertToLog(LogForm form) {

        Log log = new Log();

        log.setId(form.getId());

        log.setMemo(form.getMemo());

        log.setMoodId(form.getMoodId());

        return log;
    }

    //LogFormへの変換
    public static LogForm convertToLogForm(Log log) {

        LogForm form = new LogForm();

        form.setId(log.getId());

        form.setMemo(log.getMemo());

        form.setMoodId(log.getMoodId());

        //更新画面の設定
        form.setIsNew(false);

        return form;
    }

}

