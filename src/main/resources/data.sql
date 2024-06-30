INSERT INTO user(id, is_active, password, username, link) VALUES (1, true, 'sVAJjugOwwhJJSFdFRMa', 'Farid', '111111111');
INSERT INTO user(id, is_active, password, username, link) VALUES (2, false, 'dYauqOCHNSFlNwSdHRQ', 'Maxime', '111111112');
INSERT INTO user(id, is_active, password, username, link) VALUES (3, true, 'vHpNNigeBxwElLJnJrOO', 'Jacques', '111111113');
INSERT INTO user(id, is_active, password, username, link) VALUES (4, true, 'JBCJKvKFQcIKuTotidly', 'Jean', '111111114');
INSERT INTO user(id, is_active, password, username, link) VALUES (5, true, 'MXjdBWFLYYmAfvsWLrcz', 'Kim', '111111115');
INSERT INTO user(id, is_active, password, username, link) VALUES (6, false, 'prpYFrOXbblPMazuaYc', 'Carl', '111111116');
INSERT INTO user(id, is_active, password, username, link) VALUES (7, true, 'ShKYVcgJhPOsPGyTYNaW', 'Sam', '111111117');

INSERT INTO post (id, added_date, original_poster_id, title, description, category) VALUES (1, '2019-04-03 00:00:00', 1, 'Chat GPT 4', 'The new revolution...', 'PROGRAMMING');
INSERT INTO post (id, added_date, original_poster_id, title, description, category) VALUES (2, '2012-08-23 00:00:00', 1, 'React Hooks', 'The new hook...', 'PROGRAMMING');
INSERT INTO post (id, added_date, original_poster_id, title, description, category) VALUES (3, '2002-02-01 00:00:00', 1, 'How to make the compiler...', 'When the compiler...', 'PROGRAMMING');

INSERT INTO tag (id, content) VALUES (1, 'objective-c');
INSERT INTO tag (id, content) VALUES (2, 'c');
INSERT INTO tag (id, content) VALUES (3, 'compiler');
INSERT INTO tag (id, content) VALUES (4, 'hook');
INSERT INTO tag (id, content) VALUES (5, 'js');
INSERT INTO tag (id, content) VALUES (6, 'react');
INSERT INTO tag (id, content) VALUES (7, 'ai');
INSERT INTO tag (id, content) VALUES (8, 'tech');
INSERT INTO tag (id, content) VALUES (9, 'gpt');

INSERT INTO rel_post_tag (post_id, tags_id) VALUES (1, 1);
INSERT INTO rel_post_tag (post_id, tags_id) VALUES (1, 2);
INSERT INTO rel_post_tag (post_id, tags_id) VALUES (1, 3);
INSERT INTO rel_post_tag (post_id, tags_id) VALUES (2, 4);
INSERT INTO rel_post_tag (post_id, tags_id) VALUES (2, 5);
INSERT INTO rel_post_tag (post_id, tags_id) VALUES (2, 6);
INSERT INTO rel_post_tag (post_id, tags_id) VALUES (3, 7);
INSERT INTO rel_post_tag (post_id, tags_id) VALUES (3, 8);
INSERT INTO rel_post_tag (post_id, tags_id) VALUES (3, 9);

INSERT INTO comment (message, post_id, author_id) VALUES ('Good post!', 1, 1);
INSERT INTO comment (message, post_id, author_id) VALUES ('The best AI ever made!', 1, 1);
