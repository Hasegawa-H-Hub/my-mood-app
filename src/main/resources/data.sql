--気分テーブルに気分の情報を追加
INSERT INTO mood(id,mood_name,mood_ja) VALUES
(1,'happy','うれしい'),
(2,'fun','たのしい'),
(3,'normal','ふつう'),
(4,'sad','かなしい'),
(5,'angry','いらいら')
ON CONFLICT (id) DO NOTHING;