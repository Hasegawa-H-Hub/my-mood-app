package com.example.mymoodapp.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//記録：Form
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogForm {

  //記録ID
  private Integer id;

  //気分ID
  private Integer moodId;
  
  //ひとことメモ
  private String memo;

  //新規判定
  private Boolean isNew;

}