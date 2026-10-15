package com.example.mymoodapp.form;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//検索条件絞り込みのForm
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogSearchForm {
	private List<Integer> moodIds = new ArrayList<>();

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate startDate;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate endDate;

	private String sort = "desc";

	private boolean isSearched;

}