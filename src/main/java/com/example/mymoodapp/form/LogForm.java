package com.example.mymoodapp.form;

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

    // 空欄は許可するが、DBのNOT NULL制約に合わせてnullは受け付けない
    @NotNull(message = "ひとことメモの入力値を確認してください")
    @Size(max = 200, message = "ひとことメモは200文字以内で入力してください")
    private String memo;

    // 新規判定
    private Boolean isNew;
}
