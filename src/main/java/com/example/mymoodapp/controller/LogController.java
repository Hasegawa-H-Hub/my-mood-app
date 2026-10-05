package com.example.mymoodapp.controller;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.mymoodapp.entity.Log;
import com.example.mymoodapp.entity.Mood;
import com.example.mymoodapp.form.LogForm;
import com.example.mymoodapp.helper.LogHelper;
import com.example.mymoodapp.mapper.MoodMapper;
import com.example.mymoodapp.service.LogService;

import lombok.RequiredArgsConstructor;

/**
 * 記録（Log）を操作するControllerクラス。
 */
@Controller
@RequestMapping("/records")
@RequiredArgsConstructor
public class LogController {

    // DI
    private final LogService logService;
    private final MoodMapper moodMapper;

    // 記録の一覧を表示する
    @GetMapping
    public String list(
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate date,

            @RequestParam(required = false)
            Mood mood,

            @RequestParam(defaultValue = "desc")
            String sort,

            Model model) {

        // 記録を取得する
        List<Log> logs;

        // 日付で検索
        if (date != null) {
            logs = logService.findByDate(date);

        // 気分で検索
        } else if (mood != null) {
            logs = logService.findByMood(mood);

        // 全件検索
        } else {
            logs = logService.findAllLog();
        }

        // 並び順を設定
        if ("asc".equals(sort)) {
            logs.sort(
                    Comparator.comparing(
                            Log::getCreatedAt,
                            Comparator.nullsLast(
                                    Comparator.naturalOrder()
                            )
                    )
            );
        } else {
            logs.sort(
                    Comparator.comparing(
                            Log::getCreatedAt,
                            Comparator.nullsLast(
                                    Comparator.reverseOrder()
                            )
                    )
            );
        }

        // モデルに格納
        model.addAttribute("logs", logs);
        model.addAttribute("moods", moodMapper.selectAll());
        model.addAttribute("date", date);
        model.addAttribute("mood", mood);
        model.addAttribute("sort", sort);

        return "records";
    }

    // 新規登録画面を表示
    @GetMapping("/form")
    public String newLog(
            @ModelAttribute LogForm form,
            Model model) {

        // 新規登録画面の設定
        form.setIsNew(true);

        // 気分一覧を画面に渡す
        model.addAttribute("moods", moodMapper.selectAll());

        return "record-form";
    }

    // 新規登録を実行
    @PostMapping("/save")
    public String create(
            @Validated @ModelAttribute("logForm") LogForm form,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes attributes) {

        // バリデーションチェック
        if (bindingResult.hasErrors()) {

            // 新規登録画面の設定
            form.setIsNew(true);

            // 気分一覧を画面に渡す
            model.addAttribute("moods", moodMapper.selectAll());

            return "record-form";
        }

        // FormからEntityへ変換
        Log log = LogHelper.convertToLog(form);

        // 登録実行
        logService.insertLog(log);

        // フラッシュメッセージ
        attributes.addFlashAttribute(
                "message",
                "新しい記録が作成されました"
        );

        // PRGパターン
        return "redirect:/records";
    }

    // 指定されたIDの修正画面を表示
    @GetMapping("/edit/{id}")
    public String edit(
            @PathVariable Integer id,
            Model model,
            RedirectAttributes attributes) {

        // IDに対応する記録を取得
        Log target = logService.findByIdLog(id);

        if (target != null) {

            // EntityからFormへ変換
            LogForm form = LogHelper.convertToLog(target);

            // モデルに格納
            model.addAttribute("logForm", form);

            // 気分一覧を画面に渡す
            model.addAttribute("moods", moodMapper.selectAll());

            return "record-form";

        } else {

            // 対象データがない場合
            attributes.addFlashAttribute(
                    "errorMessage",
                    "対象データがありません"
            );

            // 一覧画面へリダイレクト
            return "redirect:/records";
        }
    }

    // 記録の情報を更新
    @PostMapping("/update")
    public String update(
            @Validated @ModelAttribute("logForm") LogForm form,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes attributes) {

        // バリデーションチェック
        if (bindingResult.hasErrors()) {

            // 更新画面の設定
            form.setIsNew(false);

            // 気分一覧を画面に渡す
            model.addAttribute("moods", moodMapper.selectAll());

            return "record-form";
        }

        // FormからEntityへ変換
        Log log = LogHelper.convertToLog(form);

        // 更新処理
        logService.updateLog(log);

        // フラッシュメッセージ
        attributes.addFlashAttribute(
                "message",
                "記録が更新されました"
        );

        // PRGパターン
        return "redirect:/records";
    }

    // 指定されたIDの記録を削除
    @PostMapping("/delete/{id}")
    public String delete(
            @PathVariable Integer id,
            RedirectAttributes attributes) {

        // 削除処理
        logService.deleteLog(id);

        // フラッシュメッセージ
        attributes.addFlashAttribute(
                "message",
                "記録が削除されました"
        );

        // PRGパターン
        return "redirect:/records";
    }
}