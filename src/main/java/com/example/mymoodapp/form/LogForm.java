package com.example.mymoodapp.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// 記録：Form
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogForm {

    // 記録ID
    private Integer id;

    // 気分ID
    @NotNull(message = "気分を選択してください")
    private Integer moodId;

    // ひとことメモは必須入力、最大200文字
    @NotBlank(message = "ひとことメモを入力してください")
    @Size(max = 200, message = "ひとことメモは200文字以内で入力してください")
    private String memo;

    // 新規判定
    private boolean isNew;
}
