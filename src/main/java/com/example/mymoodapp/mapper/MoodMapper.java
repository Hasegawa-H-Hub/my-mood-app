package com.example.mymoodapp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.mymoodapp.entity.Mood;

@Mapper
public interface MoodMapper {

	//気分一覧を表示
	List<Mood>selectAll();
	// IDから気分を取得
	Mood selectById(Integer id);

}
