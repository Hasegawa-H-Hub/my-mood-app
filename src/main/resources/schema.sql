--気分テーブルを作成
CREATE TABLE IF NOT EXISTS mood(
	--id(主キー)
	id serial PRIMARY KEY,
	--気分名
	mood_name text NOT NULL,
	--気分(日本語表示)
	mood_ja text NOT NULL
);

--メモターブルを作成
CREATE TABLE IF NOT EXISTS log(
	--id(主キー)
	id serial PRIMARY KEY,
	--気分id
	mood_id int NOT NULL REFERENCES mood(id),
	--ひとことメモ
	memo varchar(200) NOT NULL,
	--登録日付
	created_at timestamp without time zone,
	--更新日付
	updated_at timestamp without time zone
);