INSERT INTO materia VALUES (1,'Português'), (2,'Matemática'), (3,'Geografia'), (4,'História'), (5,'Filosofia');

INSERT INTO banca VALUES (1,'FUVEST'), (2,'Enem'), (3,'Unesp');

INSERT INTO pergunta(id, corpo, banca_id, materia_id) VALUES (1, ' Sed commodo porttitor mi, auctor lacinia velit interdum hendrerit', 1, 1),  (2, ' Cras semper, mauris in vulputate scelerisque, augue arcu auctor quam, vitae ultrices ex odio sed turpis', 1, 1),  (3, ' Phasellus vitae nisl volutpat, rutrum est in, laoreet sapien', 1, 1), (4, ' Ut quis porttitor nunc', 1, 2),  (5, ' Pellentesque faucibus tristique augue et pretium', 1, 2), (6, ' Mauris quis sodales justo', 2, 2),  (7, ' Donec condimentum pharetra quam eu mattis', 2, 3),  (8, ' Proin eu justo id leo pharetra lacinia', 2, 3),  (9, ' Fusce et aliquet elit', 2, 3),  (10, ' Maecenas egestas sapien augue, eu tincidunt ex dignissim sed', 3, 4),  (11, ' Phasellus diam erat, lobortis quis condimentum ut, euismod eget tortor', 3, 4),  (12, ' Sed iaculis orci nec nulla faucibus venenatis', 3, 4),  (13, ' In molestie vehicula condimentum', 3, 5),  (14, ' In accumsan augue vel facilisis tristique', 3, 5),  (15, ' Nam quis viverra tellus', 3, 5);


-- INSERT INTO usuario(id, email, senha, nome, tipo) VALUES (1, 'joao@email.com', '123456', 'João da Silva', 0), (2, 'administrador@email.com', '123456', 'Administrador da Silva', 1);