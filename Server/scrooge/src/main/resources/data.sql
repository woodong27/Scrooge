--Level data
INSERT INTO level (level, required_exp, gacha) VALUES (1, 100, 2);
INSERT INTO level (level, required_exp, gacha) VALUES (2, 100, 2);
INSERT INTO level (level, required_exp, gacha) VALUES (3, 100, 2);
INSERT INTO level (level, required_exp, gacha) VALUES (4, 100, 2);
INSERT INTO level (level, required_exp, gacha) VALUES (5, 100, 2);
INSERT INTO level (level, required_exp, gacha) VALUES (6, 150, 2);
INSERT INTO level (level, required_exp, gacha) VALUES (7, 150, 2);
INSERT INTO level (level, required_exp, gacha) VALUES (8, 150, 2);
INSERT INTO level (level, required_exp, gacha) VALUES (9, 150, 2);
INSERT INTO level (level, required_exp, gacha) VALUES (10, 150, 2);
INSERT INTO level (level, required_exp, gacha) VALUES (11, 200, 3);
INSERT INTO level (level, required_exp, gacha) VALUES (12, 200, 3);
INSERT INTO level (level, required_exp, gacha) VALUES (13, 200, 3);
INSERT INTO level (level, required_exp, gacha) VALUES (14, 200, 3);
INSERT INTO level (level, required_exp, gacha) VALUES (15, 200, 3);
INSERT INTO level (level, required_exp, gacha) VALUES (16, 250, 3);
INSERT INTO level (level, required_exp, gacha) VALUES (17, 250, 3);
INSERT INTO level (level, required_exp, gacha) VALUES (18, 250, 3);
INSERT INTO level (level, required_exp, gacha) VALUES (19, 250, 3);
INSERT INTO level (level, required_exp, gacha) VALUES (20, 250, 3);
INSERT INTO level (level, required_exp, gacha) VALUES (21, 300, 4);
INSERT INTO level (level, required_exp, gacha) VALUES (22, 300, 4);
INSERT INTO level (level, required_exp, gacha) VALUES (23, 300, 4);
INSERT INTO level (level, required_exp, gacha) VALUES (24, 300, 4);
INSERT INTO level (level, required_exp, gacha) VALUES (25, 300, 4);
INSERT INTO level (level, required_exp, gacha) VALUES (26, 350, 4);
INSERT INTO level (level, required_exp, gacha) VALUES (27, 350, 4);
INSERT INTO level (level, required_exp, gacha) VALUES (28, 350, 4);
INSERT INTO level (level, required_exp, gacha) VALUES (29, 350, 4);
INSERT INTO level (level, required_exp, gacha) VALUES (30, 350, 4);
INSERT INTO level (level, required_exp, gacha) VALUES (31, 400, 5);
INSERT INTO level (level, required_exp, gacha) VALUES (32, 400, 5);
INSERT INTO level (level, required_exp, gacha) VALUES (33, 400, 5);
INSERT INTO level (level, required_exp, gacha) VALUES (34, 400, 5);
INSERT INTO level (level, required_exp, gacha) VALUES (35, 400, 5);
INSERT INTO level (level, required_exp, gacha) VALUES (36, 450, 5);
INSERT INTO level (level, required_exp, gacha) VALUES (37, 450, 5);
INSERT INTO level (level, required_exp, gacha) VALUES (38, 450, 5);
INSERT INTO level (level, required_exp, gacha) VALUES (39, 450, 5);
INSERT INTO level (level, required_exp, gacha) VALUES (40, 450, 5);
INSERT INTO level (level, required_exp, gacha) VALUES (41, 500, 6);
INSERT INTO level (level, required_exp, gacha) VALUES (42, 500, 6);
INSERT INTO level (level, required_exp, gacha) VALUES (43, 500, 6);
INSERT INTO level (level, required_exp, gacha) VALUES (44, 500, 6);
INSERT INTO level (level, required_exp, gacha) VALUES (45, 500, 6);
INSERT INTO level (level, required_exp, gacha) VALUES (46, 550, 6);
INSERT INTO level (level, required_exp, gacha) VALUES (47, 550, 6);
INSERT INTO level (level, required_exp, gacha) VALUES (48, 550, 6);
INSERT INTO level (level, required_exp, gacha) VALUES (49, 550, 6);
INSERT INTO level (level, required_exp, gacha) VALUES (50, 550, 6);

-- Quest data
INSERT INTO quest (title, description, max_count) VALUES ('대중교통 이용하기', '자가용이나 택시 대신 대중교통을 이용해 출근해봐요', 1);
INSERT INTO quest (title, description, max_count) VALUES ('대중교통 이용하기', '자가용이나 택시 대신 대중교통을 이용해 출근해봐요', 1);
INSERT INTO quest (title, description, max_count) VALUES ('대중교통 이용하기', '자가용이나 택시 대신 대중교통을 이용해 출근해봐요', 1);
INSERT INTO quest (title, description, max_count) VALUES ('에어컨 대신 선풍기 틀기', '돈도 아끼고 환경도 지키고! 에어컨 대신 선풍기로 버텨봐요', 2);
INSERT INTO quest (title, description, max_count) VALUES ('에어컨 대신 선풍기 틀기', '돈도 아끼고 환경도 지키고! 에어컨 대신 선풍기로 버텨봐요', 2);
INSERT INTO quest (title, description, max_count) VALUES ('에어컨 대신 선풍기 틀기', '돈도 아끼고 환경도 지키고! 에어컨 대신 선풍기로 버텨봐요', 2);
INSERT INTO quest (title, description, max_count) VALUES ('커피스틱 타먹기', '비싼 카페 대신 맛있는 커피스틱을 이용해봐요', 3);
INSERT INTO quest (title, description, max_count) VALUES ('커피스틱 타먹기', '비싼 카페 대신 맛있는 커피스틱을 이용해봐요', 3);
INSERT INTO quest (title, description, max_count) VALUES ('커피스틱 타먹기', '비싼 카페 대신 맛있는 커피스틱을 이용해봐요', 3);

-- Avatar data
INSERT INTO avatar (name, img_address) VALUES ('Avatar 0', 'static/assets/avatar/avatar1.png');
INSERT INTO avatar (name, img_address) VALUES ('Avatar 1', 'static/assets/avatar/avatar2.png');
INSERT INTO avatar (name, img_address) VALUES ('Avatar 2', 'static/assets/avatar/avatar3.png');

-- Badge data
INSERT INTO badge (badge_name, badge_description, img_address) VALUES ('Badge 1', 'Description for Badge 1', 'static/assets/badge/badge1.png');
INSERT INTO badge (badge_name, badge_description, img_address) VALUES ('Badge 2', 'Description for Badge 2', 'static/assets/badge/badge2.png');
INSERT INTO badge (badge_name, badge_description, img_address) VALUES ('Badge 3', 'Description for Badge 3', 'static/assets/badge/badge3.png');