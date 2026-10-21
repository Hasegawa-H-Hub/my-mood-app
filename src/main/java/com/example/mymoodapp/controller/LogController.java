package com.example.mymoodapp.controller;

import java.util.List;

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
import com.example.mymoodapp.form.LogSearchForm;
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
    
    // 記録一覧を表示
    @GetMapping
    public String list(
            @ModelAttribute("searchForm") LogSearchForm form,
            BindingResult bindingResult,
            Model model) {
 
        List<Mood> moods = moodMapper.selectAll();
 
        // 初期表示（検索前）は全気分を選択状態にする
        if (!form.isSearched() && !bindingResult.hasErrors()) {
            form.setMoodIds(moods.stream().map(Mood::getId).toList());
            form.setStartDate(null);
            form.setEndDate(null);
            form.setSort("desc");
        }
 
        // 並び順は asc / desc のみ許可
        form.setSort("asc".equals(form.getSort()) ? "asc" : "desc");
 
        // 開始日が終了日より後ならエラー
        if (!bindingResult.hasFieldErrors("startDate")
                && !bindingResult.hasFieldErrors("endDate")
                && form.getStartDate() != null
                && form.getEndDate() != null
                && form.getStartDate().isAfter(form.getEndDate())) {
 
            bindingResult.rejectValue("endDate", "dateRange.invalid",
                    "開始日は終了日以前にしてください");
        }
 
        // エラー時はServiceを呼ばず、結果部分を非表示にする
        boolean searchPerformed = !bindingResult.hasErrors();
 
        List<Log> logs = List.of();
 
        if (searchPerformed) {
            logs = logService.search(
                    form.getMoodIds(), form.getStartDate(),
                    form.getEndDate(), form.getSort());
        }
 
        model.addAttribute("moods", moods);
        model.addAttribute("logs", logs);
        model.addAttribute("searchPerformed", searchPerformed);
 
        return "records";
    }
    
    // 新規登録フォームを表示
    @GetMapping("/new")
    public String newLog(
            @ModelAttribute LogForm form,
            Model model) {

        // 新規登録フォームの設定
        form.setNew(true);

        // 気分一覧をフォームに渡す
        model.addAttribute("moods", moodMapper.selectAll());

        return "form";
    }

    // 新規登録を実行
    @PostMapping
    public String create(
            @Validated @ModelAttribute("logForm") LogForm form,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes attributes) {

        // 気分の種類を固定せず、DBに存在するIDか確認する
        if (!bindingResult.hasFieldErrors("moodId")
                && form.getMoodId() != null
                && moodMapper.selectById(form.getMoodId()) == null) {
            bindingResult.rejectValue(
                    "moodId",
                    "mood.notFound",
                    "選択された気分がありません。選び直してください"
            );
        }

        // バリデーションチェック
        if (bindingResult.hasErrors()) {

            // 新規登録フォームの設定
            form.setNew(true);

            // 気分一覧をフォームに渡す
            model.addAttribute("moods", moodMapper.selectAll());

            return "form";
        }

        // FormからEntityへ変換
        Log log = LogHelper.convertToLog(form);

        // 登録実行
        logService.insert(log);

        // フラッシュメッセージ
        attributes.addFlashAttribute(
                "message",
                "新しい記録が作成されました"
        );

        // PRGパターン
        return "redirect:/records";
    }

    // 指定されたIDの更新フォームを表示
    @GetMapping("/edit/{id}")
    public String edit(
            @PathVariable Integer id,
            Model model,
            RedirectAttributes attributes) {

        // IDに対応する記録を取得
        Log target = logService.selectById(id);

        if (target != null) {

            // EntityからFormへ変換
            LogForm form = LogHelper.convertToLogForm(target);

            // モデルに格納
            model.addAttribute("logForm", form);

            // 気分一覧をフォームに渡す
            model.addAttribute("moods", moodMapper.selectAll());

            return "form";

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

    // 記録を更新
    @PostMapping("/update")
    public String update(
            @Validated @ModelAttribute("logForm") LogForm form,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes attributes) {

        // ID未指定・変換エラー・削除済みの記録は更新しない
        if (bindingResult.hasFieldErrors("id")
                || form.getId() == null
                || logService.selectById(form.getId()) == null) {
            attributes.addFlashAttribute(
                    "errorMessage",
                    "対象データがありません"
            );
            return "redirect:/records";
        }

        // 気分の種類を固定せず、DBに存在するIDか確認する
        if (!bindingResult.hasFieldErrors("moodId")
                && form.getMoodId() != null
                && moodMapper.selectById(form.getMoodId()) == null) {
            bindingResult.rejectValue(
                    "moodId",
                    "mood.notFound",
                    "選択された気分がありません。選び直してください"
            );
        }

        // バリデーションチェック
        if (bindingResult.hasErrors()) {

            // 更新フォームの設定
            form.setNew(false);

            // 気分一覧をフォームに渡す
            model.addAttribute("moods", moodMapper.selectAll());

            return "form";
        }

        // FormからEntityへ変換
        Log log = LogHelper.convertToLog(form);

        // 更新処理
        logService.update(log);

        // フラッシュメッセージ
        attributes.addFlashAttribute(
                "message",
                "記録が更新されました"
        );

        // PRGパターン
        return "redirect:/records";
    }

    // 指定されたIDの記録を削除
    @PostMapping("/delete")
    public String delete(
            @RequestParam(value = "id", required = false) Integer id,
            RedirectAttributes attributes) {

        // ID未指定・削除済みの記録は削除しない
        if (id == null || logService.selectById(id) == null) {
            attributes.addFlashAttribute(
                    "errorMessage",
                    "対象データがありません"
            );
            return "redirect:/records";
        }

        // 削除処理
        logService.delete(id);

        // フラッシュメッセージ
        attributes.addFlashAttribute(
                "message",
                "記録が削除されました"
        );

        // PRGパターン
        return "redirect:/records";
    }
}
