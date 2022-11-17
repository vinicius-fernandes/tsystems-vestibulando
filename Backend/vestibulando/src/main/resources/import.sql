INSERT INTO materia (nome) VALUES ('Português'), ('Matemática'), ('Geografia'), ('História'), ('Filosofia');

INSERT INTO banca(nome, sigla) VALUES ('Fundação Universitária para o Vestibular', 'FUVEST');
INSERT INTO banca(nome, sigla) VALUES ('Exame Nacional do Ensino Médio', 'Enem');
INSERT INTO banca(nome, sigla) VALUES ('Universidade Estadual Paulista', 'Unesp');

INSERT INTO pergunta(corpo, banca_id, materia_id) VALUES ( ' Sed commodo porttitor mi, auctor lacinia velit interdum hendrerit', 1, 1);
INSERT INTO pergunta(corpo, banca_id, materia_id) VALUES ( ' Cras semper, mauris in vulputate scelerisque, augue arcu auctor quam, vitae ultrices ex odio sed turpis', 1, 1);
INSERT INTO pergunta(corpo, banca_id, materia_id) VALUES ( ' Phasellus vitae nisl volutpat, rutrum est in, laoreet sapien', 1, 1);
INSERT INTO pergunta(corpo, banca_id, materia_id) VALUES ( ' Ut quis porttitor nunc', 1, 2);
INSERT INTO pergunta(corpo, banca_id, materia_id) VALUES ( ' Pellentesque faucibus tristique augue et pretium', 1, 2);
INSERT INTO pergunta(corpo, banca_id, materia_id) VALUES ( ' Mauris quis sodales justo', 2, 2);
INSERT INTO pergunta(corpo, banca_id, materia_id) VALUES ( ' Donec condimentum pharetra quam eu mattis', 2, 3);
INSERT INTO pergunta(corpo, banca_id, materia_id) VALUES ( ' Proin eu justo id leo pharetra lacinia', 2, 3);
INSERT INTO pergunta(corpo, banca_id, materia_id) VALUES ( ' Fusce et aliquet elit', 2, 3);
INSERT INTO pergunta(corpo, banca_id, materia_id) VALUES ( ' Maecenas egestas sapien augue, eu tincidunt ex dignissim sed', 3, 4);
INSERT INTO pergunta(corpo, banca_id, materia_id) VALUES ( ' Phasellus diam erat, lobortis quis condimentum ut, euismod eget tortor', 3, 4);
INSERT INTO pergunta(corpo, banca_id, materia_id) VALUES ( ' Sed iaculis orci nec nulla faucibus venenatis', 3, 4);
INSERT INTO pergunta(corpo, banca_id, materia_id) VALUES ( ' In molestie vehicula condimentum', 3, 5);
INSERT INTO pergunta(corpo, banca_id, materia_id) VALUES ( ' In accumsan augue vel facilisis tristique', 3, 5);
INSERT INTO pergunta(corpo, banca_id, materia_id) VALUES ( ' Nam quis viverra tellus', 3, 5);

INSERT INTO resposta( descricao, correta, pergunta_id) VALUES (' Sed commodo porttitor mi, auctor lacinia velit interdum hendrerit', 1, 1);
INSERT INTO resposta( descricao, correta, pergunta_id) VALUES (' Cras semper, mauris in vulputate scelerisque, augue arcu auctor quam, vitae ultrices ex odio sed turpis', 0, 1);
INSERT INTO resposta( descricao, correta, pergunta_id) VALUES (' Phasellus vitae nisl volutpat, rutrum est in, laoreet sapien', 0, 1);
INSERT INTO resposta( descricao, correta, pergunta_id) VALUES (' Ut quis porttitor nunc', 0, 1);
INSERT INTO resposta( descricao, correta, pergunta_id) VALUES (' Pellentesque faucibus tristique augue et pretium', 0, 1);
INSERT INTO resposta( descricao, correta, pergunta_id) VALUES (' Mauris quis sodales justo', 0, 2);
INSERT INTO resposta( descricao, correta, pergunta_id) VALUES (' Donec condimentum pharetra quam eu mattis', 1, 2);
INSERT INTO resposta( descricao, correta, pergunta_id) VALUES (' Proin eu justo id leo pharetra lacinia', 0, 2);
INSERT INTO resposta( descricao, correta, pergunta_id) VALUES (' Fusce et aliquet elit', 0, 2);
INSERT INTO resposta( descricao, correta, pergunta_id) VALUES ( ' Maecenas egestas sapien augue, eu tincidunt ex dignissim sed', 0, 2);
INSERT INTO resposta( descricao, correta, pergunta_id) VALUES ( ' Phasellus diam erat, lobortis quis condimentum ut, euismod eget tortor', 0, 3);
INSERT INTO resposta( descricao, correta, pergunta_id) VALUES ( ' Sed iaculis orci nec nulla faucibus venenatis', 0, 3);
INSERT INTO resposta( descricao, correta, pergunta_id) VALUES ( ' In molestie vehicula condimentum', 1, 3);
INSERT INTO resposta( descricao, correta, pergunta_id) VALUES ( ' In accumsan augue vel facilisis tristique', 0, 3);
INSERT INTO resposta( descricao, correta, pergunta_id) VALUES ( ' Nam quis viverra tellus', 0, 3);
INSERT INTO resposta( descricao, correta, pergunta_id) VALUES ( 'Curabitur posuere lacinia augue, sed fermentum enim aliquam eget', 0, 4);
INSERT INTO resposta( descricao, correta, pergunta_id) VALUES ( ' Aenean a ante eleifend, imperdiet dolor nec, imperdiet risus', 0, 4);
INSERT INTO resposta( descricao, correta, pergunta_id) VALUES ( ' Duis et arcu metus', 0, 4);
INSERT INTO resposta( descricao, correta, pergunta_id) VALUES ( ' Interdum et malesuada fames ac ante ipsum primis in faucibus', 1, 4);
INSERT INTO resposta( descricao, correta, pergunta_id) VALUES ( ' Donec fermentum viverra nunc in commodo', 0, 4);
INSERT INTO resposta( descricao, correta, pergunta_id) VALUES ( ' Morbi accumsan velit quis turpis pharetra, in luctus leo malesuada', 0, 5);
INSERT INTO resposta( descricao, correta, pergunta_id) VALUES ( ' Proin lobortis enim id dolor euismod hendrerit', 0, 5);
INSERT INTO resposta( descricao, correta, pergunta_id) VALUES ( ' Curabitur id pulvinar lorem', 0, 5);
INSERT INTO resposta( descricao, correta, pergunta_id) VALUES ( ' Vestibulum accumsan libero et velit ultricies, sit amet tristique elit placerat', 0, 5);
INSERT INTO resposta( descricao, correta, pergunta_id) VALUES ( ' Etiam vel felis porta, ornare libero sed, pharetra justo', 1, 5);
INSERT INTO resposta( descricao, correta, pergunta_id) VALUES ( ' Quisque suscipit, nibh id interdum molestie, est nisl pellentesque orci, eu porta eros enim vel nibh', 1, 6);
INSERT INTO resposta( descricao, correta, pergunta_id) VALUES ( ' Etiam tincidunt gravida augue convallis porta', 0, 6);
INSERT INTO resposta( descricao, correta, pergunta_id) VALUES ( ' Mauris eu sapien nec mi dictum suscipit', 0, 6);
INSERT INTO resposta( descricao, correta, pergunta_id) VALUES ( ' Vivamus id feugiat urna, vel ultrices eros', 0, 6);
INSERT INTO resposta( descricao, correta, pergunta_id) VALUES ( ' Aliquam eget convallis turpis', 0, 6);
INSERT INTO resposta( descricao, correta, pergunta_id) VALUES ( ' Nunc ut leo tincidunt, malesuada lectus sit amet, tincidunt ligula', 0, 7);
INSERT INTO resposta( descricao, correta, pergunta_id) VALUES ( ' Suspendisse dignissim nulla vitae dui lobortis, eget aliquam dui feugiat', 1, 7);
INSERT INTO resposta( descricao, correta, pergunta_id) VALUES ( ' Quisque ultrices magna ligula, eu aliquet lectus ultricies vitae', 0, 7);
INSERT INTO resposta( descricao, correta, pergunta_id) VALUES ( ' Etiam blandit semper nisl, a imperdiet felis commodo non', 0, 7);
INSERT INTO resposta( descricao, correta, pergunta_id) VALUES ( ' Maecenas rutrum malesuada augue eget semper', 0, 7);
INSERT INTO resposta( descricao, correta, pergunta_id) VALUES ( ' Morbi convallis lorem nec commodo posuere', 0, 8);
INSERT INTO resposta( descricao, correta, pergunta_id) VALUES ( ' Proin lobortis egestas ipsum, a finibus dolor auctor at', 0, 8);
INSERT INTO resposta( descricao, correta, pergunta_id) VALUES ( ' Aliquam viverra diam dapibus, molestie ligula a, consectetur neque', 1, 8);
INSERT INTO resposta( descricao, correta, pergunta_id) VALUES ( ' Donec eget magna laoreet, dictum nisi et, euismod erat', 0, 8);
INSERT INTO resposta( descricao, correta, pergunta_id) VALUES ( ' Mauris posuere at mauris vel faucibus', 0, 8);
INSERT INTO resposta( descricao, correta, pergunta_id) VALUES ( ' Praesent dictum suscipit metus in sagittis', 0, 9);
INSERT INTO resposta( descricao, correta, pergunta_id) VALUES ( ' Aliquam mauris neque, tincidunt a faucibus at, sollicitudin eu lacus', 0, 9);
INSERT INTO resposta( descricao, correta, pergunta_id) VALUES ( ' Praesent varius fringilla efficitur', 0, 9);
INSERT INTO resposta( descricao, correta, pergunta_id) VALUES ( ' Aliquam turpis ex, hendrerit a ex ut, cursus sagittis justo', 1, 9);
INSERT INTO resposta( descricao, correta, pergunta_id) VALUES ( ' Aenean a lobortis massa, in feugiat nisl', 0, 9);
INSERT INTO resposta( descricao, correta, pergunta_id) VALUES ( ' Nunc at elit diam', 0, 10);
INSERT INTO resposta( descricao, correta, pergunta_id) VALUES ( ' Mauris sagittis, eros vitae convallis scelerisque, ante mauris placerat magna, id tristique lacus elit a elit', 0, 10);
INSERT INTO resposta( descricao, correta, pergunta_id) VALUES ( ' Cras vitae tempor justo, sed gravida felis', 0, 10);
INSERT INTO resposta( descricao, correta, pergunta_id) VALUES ( ' Aenean egestas libero dui, et pretium sem dictum non', 0, 10);
INSERT INTO resposta( descricao, correta, pergunta_id) VALUES ( ' Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas', 1, 10);
INSERT INTO resposta( descricao, correta, pergunta_id) VALUES ( ' Nunc scelerisque enim odio, eget facilisis quam efficitur ut', 1, 11);
INSERT INTO resposta( descricao, correta, pergunta_id) VALUES ( ' Vivamus ex lacus, viverra sit amet sollicitudin vestibulum, faucibus vitae dui', 0, 11);
INSERT INTO resposta( descricao, correta, pergunta_id) VALUES ( ' Sed hendrerit leo ac velit vehicula commodo', 0, 11);
INSERT INTO resposta( descricao, correta, pergunta_id) VALUES ( ' Sed vitae erat at orci facilisis convallis non vitae turpis', 0, 11);
INSERT INTO resposta( descricao, correta, pergunta_id) VALUES ( ' Nulla cursus, ligula et placerat varius, quam leo ultrices purus, et eleifend augue enim id lacus', 0, 11);
INSERT INTO resposta( descricao, correta, pergunta_id) VALUES ( ' Sed molestie facilisis velit sed sodales', 0, 12);
INSERT INTO resposta( descricao, correta, pergunta_id) VALUES ( ' Donec scelerisque placerat velit, in laoreet quam feugiat ac', 1, 12);
INSERT INTO resposta( descricao, correta, pergunta_id) VALUES ( ' Curabitur vestibulum lacus nec porta blandit', 0, 12);
INSERT INTO resposta( descricao, correta, pergunta_id) VALUES ( ' Nam at sapien euismod ligula cursus rutrum', 0, 12);
INSERT INTO resposta( descricao, correta, pergunta_id) VALUES ( ' Vivamus ut tincidunt quam', 0, 12);
INSERT INTO resposta( descricao, correta, pergunta_id) VALUES ( ' Donec ac blandit ipsum, eu posuere tellus', 0, 13);
INSERT INTO resposta( descricao, correta, pergunta_id) VALUES ( ' Etiam ut rutrum quam', 0, 13);
INSERT INTO resposta( descricao, correta, pergunta_id) VALUES ( ' Donec posuere ligula augue, venenatis luctus tortor dignissim sed', 1, 13);
INSERT INTO resposta( descricao, correta, pergunta_id) VALUES ( ' Maecenas ut erat et lacus lacinia accumsan ut quis ligula', 0, 13);
INSERT INTO resposta( descricao, correta, pergunta_id) VALUES ( ' Aliquam sem tortor, viverra quis ultricies ut, laoreet quis dolor', 0, 13);
INSERT INTO resposta( descricao, correta, pergunta_id) VALUES ( ' In consectetur dolor id tellus malesuada elementum', 0, 14);
INSERT INTO resposta( descricao, correta, pergunta_id) VALUES ( ' Duis ut rhoncus magna, ac hendrerit orci', 0, 14);
INSERT INTO resposta( descricao, correta, pergunta_id) VALUES ( ' Morbi at purus dignissim, rutrum tellus in, dignissim libero', 0, 14);
INSERT INTO resposta( descricao, correta, pergunta_id) VALUES ( ' Nulla posuere a nisl vel aliquam', 1, 14);
INSERT INTO resposta( descricao, correta, pergunta_id) VALUES ( ' Donec blandit ante urna, eu vestibulum eros pretium ac', 0, 14);
INSERT INTO resposta( descricao, correta, pergunta_id) VALUES ( ' Morbi laoreet accumsan ipsum, a semper erat vestibulum ac', 0, 15);
INSERT INTO resposta( descricao, correta, pergunta_id) VALUES ( ' Suspendisse potenti', 0, 15);
INSERT INTO resposta( descricao, correta, pergunta_id) VALUES ( ' Pellentesque sed malesuada augue', 0, 15);
INSERT INTO resposta( descricao, correta, pergunta_id) VALUES ( ' Pellentesque vel ultrices dolor', 0, 15);
INSERT INTO resposta( descricao, correta, pergunta_id) VALUES ( ' Aliquam mattis vulputate ligula, condimentum elementum lacus vestibulum tempus', 1, 15);



INSERT INTO simulado (created_at) values ('2022-11-17T14:02:01.077Z');

INSERT INTO simulado_pergunta (simulado_id,pergunta_id) values (1,1);
INSERT INTO simulado_pergunta (simulado_id,pergunta_id) values (1,2);
INSERT INTO simulado_pergunta (simulado_id,pergunta_id) values (1,3);
INSERT INTO simulado_pergunta (simulado_id,pergunta_id) values (1,4);
INSERT INTO simulado_pergunta (simulado_id,pergunta_id) values (1,5);
INSERT INTO simulado_pergunta (simulado_id,pergunta_id) values (1,6);
INSERT INTO simulado_pergunta (simulado_id,pergunta_id) values (1,7);
INSERT INTO simulado_pergunta (simulado_id,pergunta_id) values (1,8);
INSERT INTO simulado_pergunta (simulado_id,pergunta_id) values (1,9);
INSERT INTO simulado_pergunta (simulado_id,pergunta_id) values (1,10);


INSERT INTO usuario(email, senha, nome, tipo) VALUES ('joao@email.com', '123456', 'João da Silva', 0);
INSERT INTO usuario(email, senha, nome, tipo) VALUES ('ana@email.com', '123456', 'Ana da Silva', 1);

INSERT INTO respostas_usuarios (simulado_id,usuario_id) values (1,1);
INSERT INTO respostas_usuarios (simulado_id,usuario_id) values (1,2);


INSERT INTO respostas_has_resultados_simulados (resultadossimulados_id,resposta_id) values (1,3);
INSERT INTO respostas_has_resultados_simulados (resultadossimulados_id,resposta_id) values (1,6);
INSERT INTO respostas_has_resultados_simulados (resultadossimulados_id,resposta_id) values (1,12);
INSERT INTO respostas_has_resultados_simulados (resultadossimulados_id,resposta_id) values (1,17);
INSERT INTO respostas_has_resultados_simulados (resultadossimulados_id,resposta_id) values (1,21);
INSERT INTO respostas_has_resultados_simulados (resultadossimulados_id,resposta_id) values (1,28);
INSERT INTO respostas_has_resultados_simulados (resultadossimulados_id,resposta_id) values (1,32);
INSERT INTO respostas_has_resultados_simulados (resultadossimulados_id,resposta_id) values (1,36);
INSERT INTO respostas_has_resultados_simulados (resultadossimulados_id,resposta_id) values (1,41);
INSERT INTO respostas_has_resultados_simulados (resultadossimulados_id,resposta_id) values (1,47);

INSERT INTO respostas_has_resultados_simulados (resultadossimulados_id,resposta_id) values (2,4);
INSERT INTO respostas_has_resultados_simulados (resultadossimulados_id,resposta_id) values (2,6);
INSERT INTO respostas_has_resultados_simulados (resultadossimulados_id,resposta_id) values (2,13);
INSERT INTO respostas_has_resultados_simulados (resultadossimulados_id,resposta_id) values (2,19);
INSERT INTO respostas_has_resultados_simulados (resultadossimulados_id,resposta_id) values (2,21);
INSERT INTO respostas_has_resultados_simulados (resultadossimulados_id,resposta_id) values (2,29);
INSERT INTO respostas_has_resultados_simulados (resultadossimulados_id,resposta_id) values (2,34);
INSERT INTO respostas_has_resultados_simulados (resultadossimulados_id,resposta_id) values (2,38);
INSERT INTO respostas_has_resultados_simulados (resultadossimulados_id,resposta_id) values (2,41);
INSERT INTO respostas_has_resultados_simulados (resultadossimulados_id,resposta_id) values (2,49);

-- INSERT INTO pergunta(id, corpo, banca_id, materia_id) VALUES (1, ' Sed commodo porttitor mi, auctor lacinia velit interdum hendrerit', 1, 1),  (2, ' Cras semper, mauris in vulputate scelerisque, augue arcu auctor quam, vitae ultrices ex odio sed turpis', 1, 1),  (3, ' Phasellus vitae nisl volutpat, rutrum est in, laoreet sapien', 1, 1), (4, ' Ut quis porttitor nunc', 1, 2),  (5, ' Pellentesque faucibus tristique augue et pretium', 1, 2), (6, ' Mauris quis sodales justo', 2, 2),  (7, ' Donec condimentum pharetra quam eu mattis', 2, 3),  (8, ' Proin eu justo id leo pharetra lacinia', 2, 3),  (9, ' Fusce et aliquet elit', 2, 3),  (10, ' Maecenas egestas sapien augue, eu tincidunt ex dignissim sed', 3, 4),  (11, ' Phasellus diam erat, lobortis quis condimentum ut, euismod eget tortor', 3, 4),  (12, ' Sed iaculis orci nec nulla faucibus venenatis', 3, 4),  (13, ' In molestie vehicula condimentum', 3, 5),  (14, ' In accumsan augue vel facilisis tristique', 3, 5),  (15, ' Nam quis viverra tellus', 3, 5);

-- INSERT INTO resposta(id, descricao, correta, pergunta_id) VALUES  (1, ' Sed commodo porttitor mi, auctor lacinia velit interdum hendrerit', 1, 1),  (2, ' Cras semper, mauris in vulputate scelerisque, augue arcu auctor quam, vitae ultrices ex odio sed turpis', 0, 1),  (3, ' Phasellus vitae nisl volutpat, rutrum est in, laoreet sapien', 0, 1),  (4, ' Ut quis porttitor nunc', 0, 1),  (5, ' Pellentesque faucibus tristique augue et pretium', 0, 1),  (6, ' Mauris quis sodales justo', 0, 2),  (7, ' Donec condimentum pharetra quam eu mattis', 1, 2),  (8, ' Proin eu justo id leo pharetra lacinia', 0, 2),  (9, ' Fusce et aliquet elit', 0, 2),  (10, ' Maecenas egestas sapien augue, eu tincidunt ex dignissim sed', 0, 2),  (11, ' Phasellus diam erat, lobortis quis condimentum ut, euismod eget tortor', 0, 3),  (12, ' Sed iaculis orci nec nulla faucibus venenatis', 0, 3),  (13, ' In molestie vehicula condimentum', 1, 3),  (14, ' In accumsan augue vel facilisis tristique', 0, 3),  (15, ' Nam quis viverra tellus', 0, 3),  (16, 'Curabitur posuere lacinia augue, sed fermentum enim aliquam eget', 0, 4),  (17, ' Aenean a ante eleifend, imperdiet dolor nec, imperdiet risus', 0, 4),  (18, ' Duis et arcu metus', 0, 4),  (19, ' Interdum et malesuada fames ac ante ipsum primis in faucibus', 1, 4),  (20, ' Donec fermentum viverra nunc in commodo', 0, 4),  (21, ' Morbi accumsan velit quis turpis pharetra, in luctus leo malesuada', 0, 5),  (22, ' Proin lobortis enim id dolor euismod hendrerit', 0, 5),  (23, ' Curabitur id pulvinar lorem', 0, 5),  (24, ' Vestibulum accumsan libero et velit ultricies, sit amet tristique elit placerat', 0, 5),  (25, ' Etiam vel felis porta, ornare libero sed, pharetra justo', 1, 5),  (26, ' Quisque suscipit, nibh id interdum molestie, est nisl pellentesque orci, eu porta eros enim vel nibh', 1, 6),  (27, ' Etiam tincidunt gravida augue convallis porta', 0, 6),  (28, ' Mauris eu sapien nec mi dictum suscipit', 0, 6),  (29, ' Vivamus id feugiat urna, vel ultrices eros', 0, 6),  (30, ' Aliquam eget convallis turpis', 0, 6),  (31, ' Nunc ut leo tincidunt, malesuada lectus sit amet, tincidunt ligula', 0, 7),  (32, ' Suspendisse dignissim nulla vitae dui lobortis, eget aliquam dui feugiat', 1, 7),  (33, ' Quisque ultrices magna ligula, eu aliquet lectus ultricies vitae', 0, 7),  (34, ' Etiam blandit semper nisl, a imperdiet felis commodo non', 0, 7),  (35, ' Maecenas rutrum malesuada augue eget semper', 0, 7),  (36, ' Morbi convallis lorem nec commodo posuere', 0, 8),  (37, ' Proin lobortis egestas ipsum, a finibus dolor auctor at', 0, 8),  (38, ' Aliquam viverra diam dapibus, molestie ligula a, consectetur neque', 1, 8),  (39, ' Donec eget magna laoreet, dictum nisi et, euismod erat', 0, 8),  (40, ' Mauris posuere at mauris vel faucibus', 0, 8),  (41, ' Praesent dictum suscipit metus in sagittis', 0, 9),  (42, ' Aliquam mauris neque, tincidunt a faucibus at, sollicitudin eu lacus', 0, 9),  (43, ' Praesent varius fringilla efficitur', 0, 9),  (44, ' Aliquam turpis ex, hendrerit a ex ut, cursus sagittis justo', 1, 9),  (45, ' Aenean a lobortis massa, in feugiat nisl', 0, 9),  (46, ' Nunc at elit diam', 0, 10),  (47, ' Mauris sagittis, eros vitae convallis scelerisque, ante mauris placerat magna, id tristique lacus elit a elit', 0, 10),  (48, ' Cras vitae tempor justo, sed gravida felis', 0, 10),  (49, ' Aenean egestas libero dui, et pretium sem dictum non', 0, 10),  (50, ' Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas', 1, 10),  (51, ' Nunc scelerisque enim odio, eget facilisis quam efficitur ut', 1, 11),  (52, ' Vivamus ex lacus, viverra sit amet sollicitudin vestibulum, faucibus vitae dui', 0, 11),  (53, ' Sed hendrerit leo ac velit vehicula commodo', 0, 11),  (54, ' Sed vitae erat at orci facilisis convallis non vitae turpis', 0, 11),  (55, ' Nulla cursus, ligula et placerat varius, quam leo ultrices purus, et eleifend augue enim id lacus', 0, 11),  (56, ' Sed molestie facilisis velit sed sodales', 0, 12),  (57, ' Donec scelerisque placerat velit, in laoreet quam feugiat ac', 1, 12),  (58, ' Curabitur vestibulum lacus nec porta blandit', 0, 12),  (59, ' Nam at sapien euismod ligula cursus rutrum', 0, 12),  (60, ' Vivamus ut tincidunt quam', 0, 12),  (61, ' Donec ac blandit ipsum, eu posuere tellus', 0, 13),  (62, ' Etiam ut rutrum quam', 0, 13),  (63, ' Donec posuere ligula augue, venenatis luctus tortor dignissim sed', 1, 13),  (64, ' Maecenas ut erat et lacus lacinia accumsan ut quis ligula', 0, 13),  (65, ' Aliquam sem tortor, viverra quis ultricies ut, laoreet quis dolor', 0, 13),  (66, ' In consectetur dolor id tellus malesuada elementum', 0, 14),  (67, ' Duis ut rhoncus magna, ac hendrerit orci', 0, 14),  (68, ' Morbi at purus dignissim, rutrum tellus in, dignissim libero', 0, 14),  (69, ' Nulla posuere a nisl vel aliquam', 1, 14),  (70, ' Donec blandit ante urna, eu vestibulum eros pretium ac', 0, 14),  (71, ' Morbi laoreet accumsan ipsum, a semper erat vestibulum ac', 0, 15),  (72, ' Suspendisse potenti', 0, 15),  (73, ' Pellentesque sed malesuada augue', 0, 15),  (74, ' Pellentesque vel ultrices dolor', 0, 15),  (75, ' Aliquam mattis vulputate ligula, condimentum elementum lacus vestibulum tempus', 1, 15);


-- INSERT INTO usuario(id, email, senha, nome, tipo) VALUES (1, 'joao@email.com', '123456', 'João da Silva', 0), (2, 'administrador@email.com', '123456', 'Administrador da Silva', 1);