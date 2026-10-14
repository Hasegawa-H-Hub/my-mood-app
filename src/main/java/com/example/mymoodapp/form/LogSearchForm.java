package com.example.mymoodapp.form;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

public class LogSearchForm {
	private List<Integer> moodIds = new ArrayList<>();

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate startDate;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate endDate;

	private String sort = "desc";

	private boolean searched;

}
