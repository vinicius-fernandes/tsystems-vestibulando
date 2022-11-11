INSERT INTO materia VALUES (1,'Português'), (2,'Matemática'), (3,'Geografia'), (4,'História'), (5,'Filosofia');

INSERT INTO banca VALUES
    (1, 'Fundação Universitária para o Vestibular', 'FUVEST'),
    (2, 'Exame Nacional do Ensino Médio', 'Enem'),
    (3, 'Universidade Estadual Paulista', 'Unesp');

INSERT INTO pergunta(id, corpo, banca_id, materia_id) VALUES (1, ' Sed commodo porttitor mi, auctor lacinia velit interdum hendrerit', 1, 1),  (2, ' Cras semper, mauris in vulputate scelerisque, augue arcu auctor quam, vitae ultrices ex odio sed turpis', 1, 1),  (3, ' Phasellus vitae nisl volutpat, rutrum est in, laoreet sapien', 1, 1), (4, ' Ut quis porttitor nunc', 1, 2),  (5, ' Pellentesque faucibus tristique augue et pretium', 1, 2), (6, ' Mauris quis sodales justo', 2, 2),  (7, ' Donec condimentum pharetra quam eu mattis', 2, 3),  (8, ' Proin eu justo id leo pharetra lacinia', 2, 3),  (9, ' Fusce et aliquet elit', 2, 3),  (10, ' Maecenas egestas sapien augue, eu tincidunt ex dignissim sed', 3, 4),  (11, ' Phasellus diam erat, lobortis quis condimentum ut, euismod eget tortor', 3, 4),  (12, ' Sed iaculis orci nec nulla faucibus venenatis', 3, 4),  (13, ' In molestie vehicula condimentum', 3, 5),  (14, ' In accumsan augue vel facilisis tristique', 3, 5),  (15, ' Nam quis viverra tellus', 3, 5);

INSERT INTO resposta(id, descricao, correta, pergunta_id) VALUES  (1, ' Sed commodo porttitor mi, auctor lacinia velit interdum hendrerit', 1, 1),  (2, ' Cras semper, mauris in vulputate scelerisque, augue arcu auctor quam, vitae ultrices ex odio sed turpis', 0, 1),  (3, ' Phasellus vitae nisl volutpat, rutrum est in, laoreet sapien', 0, 1),  (4, ' Ut quis porttitor nunc', 0, 1),  (5, ' Pellentesque faucibus tristique augue et pretium', 0, 1),  (6, ' Mauris quis sodales justo', 0, 2),  (7, ' Donec condimentum pharetra quam eu mattis', 1, 2),  (8, ' Proin eu justo id leo pharetra lacinia', 0, 2),  (9, ' Fusce et aliquet elit', 0, 2),  (10, ' Maecenas egestas sapien augue, eu tincidunt ex dignissim sed', 0, 2),  (11, ' Phasellus diam erat, lobortis quis condimentum ut, euismod eget tortor', 0, 3),  (12, ' Sed iaculis orci nec nulla faucibus venenatis', 0, 3),  (13, ' In molestie vehicula condimentum', 1, 3),  (14, ' In accumsan augue vel facilisis tristique', 0, 3),  (15, ' Nam quis viverra tellus', 0, 3),  (16, 'Curabitur posuere lacinia augue, sed fermentum enim aliquam eget', 0, 4),  (17, ' Aenean a ante eleifend, imperdiet dolor nec, imperdiet risus', 0, 4),  (18, ' Duis et arcu metus', 0, 4),  (19, ' Interdum et malesuada fames ac ante ipsum primis in faucibus', 1, 4),  (20, ' Donec fermentum viverra nunc in commodo', 0, 4),  (21, ' Morbi accumsan velit quis turpis pharetra, in luctus leo malesuada', 0, 5),  (22, ' Proin lobortis enim id dolor euismod hendrerit', 0, 5),  (23, ' Curabitur id pulvinar lorem', 0, 5),  (24, ' Vestibulum accumsan libero et velit ultricies, sit amet tristique elit placerat', 0, 5),  (25, ' Etiam vel felis porta, ornare libero sed, pharetra justo', 1, 5),  (26, ' Quisque suscipit, nibh id interdum molestie, est nisl pellentesque orci, eu porta eros enim vel nibh', 1, 6),  (27, ' Etiam tincidunt gravida augue convallis porta', 0, 6),  (28, ' Mauris eu sapien nec mi dictum suscipit', 0, 6),  (29, ' Vivamus id feugiat urna, vel ultrices eros', 0, 6),  (30, ' Aliquam eget convallis turpis', 0, 6),  (31, ' Nunc ut leo tincidunt, malesuada lectus sit amet, tincidunt ligula', 0, 7),  (32, ' Suspendisse dignissim nulla vitae dui lobortis, eget aliquam dui feugiat', 1, 7),  (33, ' Quisque ultrices magna ligula, eu aliquet lectus ultricies vitae', 0, 7),  (34, ' Etiam blandit semper nisl, a imperdiet felis commodo non', 0, 7),  (35, ' Maecenas rutrum malesuada augue eget semper', 0, 7),  (36, ' Morbi convallis lorem nec commodo posuere', 0, 8),  (37, ' Proin lobortis egestas ipsum, a finibus dolor auctor at', 0, 8),  (38, ' Aliquam viverra diam dapibus, molestie ligula a, consectetur neque', 1, 8),  (39, ' Donec eget magna laoreet, dictum nisi et, euismod erat', 0, 8),  (40, ' Mauris posuere at mauris vel faucibus', 0, 8),  (41, ' Praesent dictum suscipit metus in sagittis', 0, 9),  (42, ' Aliquam mauris neque, tincidunt a faucibus at, sollicitudin eu lacus', 0, 9),  (43, ' Praesent varius fringilla efficitur', 0, 9),  (44, ' Aliquam turpis ex, hendrerit a ex ut, cursus sagittis justo', 1, 9),  (45, ' Aenean a lobortis massa, in feugiat nisl', 0, 9),  (46, ' Nunc at elit diam', 0, 10),  (47, ' Mauris sagittis, eros vitae convallis scelerisque, ante mauris placerat magna, id tristique lacus elit a elit', 0, 10),  (48, ' Cras vitae tempor justo, sed gravida felis', 0, 10),  (49, ' Aenean egestas libero dui, et pretium sem dictum non', 0, 10),  (50, ' Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas', 1, 10),  (51, ' Nunc scelerisque enim odio, eget facilisis quam efficitur ut', 1, 11),  (52, ' Vivamus ex lacus, viverra sit amet sollicitudin vestibulum, faucibus vitae dui', 0, 11),  (53, ' Sed hendrerit leo ac velit vehicula commodo', 0, 11),  (54, ' Sed vitae erat at orci facilisis convallis non vitae turpis', 0, 11),  (55, ' Nulla cursus, ligula et placerat varius, quam leo ultrices purus, et eleifend augue enim id lacus', 0, 11),  (56, ' Sed molestie facilisis velit sed sodales', 0, 12),  (57, ' Donec scelerisque placerat velit, in laoreet quam feugiat ac', 1, 12),  (58, ' Curabitur vestibulum lacus nec porta blandit', 0, 12),  (59, ' Nam at sapien euismod ligula cursus rutrum', 0, 12),  (60, ' Vivamus ut tincidunt quam', 0, 12),  (61, ' Donec ac blandit ipsum, eu posuere tellus', 0, 13),  (62, ' Etiam ut rutrum quam', 0, 13),  (63, ' Donec posuere ligula augue, venenatis luctus tortor dignissim sed', 1, 13),  (64, ' Maecenas ut erat et lacus lacinia accumsan ut quis ligula', 0, 13),  (65, ' Aliquam sem tortor, viverra quis ultricies ut, laoreet quis dolor', 0, 13),  (66, ' In consectetur dolor id tellus malesuada elementum', 0, 14),  (67, ' Duis ut rhoncus magna, ac hendrerit orci', 0, 14),  (68, ' Morbi at purus dignissim, rutrum tellus in, dignissim libero', 0, 14),  (69, ' Nulla posuere a nisl vel aliquam', 1, 14),  (70, ' Donec blandit ante urna, eu vestibulum eros pretium ac', 0, 14),  (71, ' Morbi laoreet accumsan ipsum, a semper erat vestibulum ac', 0, 15),  (72, ' Suspendisse potenti', 0, 15),  (73, ' Pellentesque sed malesuada augue', 0, 15),  (74, ' Pellentesque vel ultrices dolor', 0, 15),  (75, ' Aliquam mattis vulputate ligula, condimentum elementum lacus vestibulum tempus', 1, 15);


-- INSERT INTO usuario(id, email, senha, nome, tipo) VALUES (1, 'joao@email.com', '123456', 'João da Silva', 0), (2, 'administrador@email.com', '123456', 'Administrador da Silva', 1);