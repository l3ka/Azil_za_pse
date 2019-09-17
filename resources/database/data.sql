use azil;

-- Zaposleni
insert into zaposleni values (1234567891234, "Milica", "Matic", "milica", "f50ccb98908dd89ef400372d10750d4c506bebdc34c8141e11d2fb962ebb1b5d", "Srednja strucna sprema", "Banja Luka", 065123456);
insert into zaposleni values (1234567891235, "Stefan", "Erceg", "stefan", "52518386cc33022de894fa0af047bd62666a63c2a6a6e86650e26955058c5acf", "Srednja strucna sprema", "Banja Luka", 065123457);
insert into zaposleni values (1234567891236, "Rade", "Culum", "rade", "0d0bf087d6c879ac9aa426297b2e90528f25f005f82e2aed81e0e15066bfe322", "Srednja strucna sprema", "Banja Luka", 065123458);
insert into zaposleni values (1234567891237, "Milan", "Markovic", "milan", "efee614420c57ddd2a8e91eeef6f6b83d5356c2288155be0f273bef986e3b850", "Srednja strucna sprema", "Banja Luka", 065123459);
insert into zaposleni values (1234567891238, "Jelena", "Aleksic", "jelena", "358ccf83f867e8147663680f52d951dfa9b21a8b7d59b3b46eb917bdf5952c92", "Srednja strucna sprema", "Banja Luka", 065123455);
insert into zaposleni values (1234567891239, "Darko", "Lekic", "darko", "fe79e0ac4b7db16d59a67be682f0c2e85e24241cccbb7a6303446e7105362bcc", "Srednja strucna sprema", "Laktasi", 065123454);

-- Ugovor o Radu
insert into ugovororadu values (null, "Sluzbenik", 1, 1250);
insert into ugovororadu values (null, "Sluzbenik", 1, 1550);
insert into ugovororadu values (null, "Veterinar", 1, 1350);
insert into ugovororadu values (null, "Veterinar", 1, 1270);
insert into ugovororadu values (null, "Administrator", 1, 1294);
insert into ugovororadu values (null, "Administrator", 1, 2194);

-- Ugovori zaposlenika
insert into zaposleni_ugovor values ("2019-08-01", 1234567891234, 1, null);
insert into zaposleni_ugovor values ("2019-09-01", 1234567891235, 2, null);
insert into zaposleni_ugovor values ("2019-01-01", 1234567891236, 3, null);
insert into zaposleni_ugovor values ("2015-08-01", 1234567891237, 4, null);
insert into zaposleni_ugovor values ("2019-06-01", 1234567891238, 5, null);
insert into zaposleni_ugovor values ("2016-08-01", 1234567891239, 6, null);

-- Sluzbenici
insert into sluzbenik values (1234567891234);
insert into sluzbenik values (1234567891235);

-- Veterinari 
insert into veterinar values (1234567891236);
insert into veterinar values (1234567891237);

-- Administratori
insert into administrator values (1234567891238);
insert into administrator values (1234567891239);

-- Psi
insert into pas values (null, "Pas1", "M", "Terijer", '2017-01-07', 21, 19.3, null, default, 0);
insert into pas values (null, "Pas2", "M", "Buldog", '2018-02-10', 11, 12.5, null, default, 0);
insert into pas values (null, "Pas3", "Ž", "Terijer", '2019-07-20', 14, 17.3, null, default, 0);
insert into pas values (null, "Pas4", "M", "Buldog", '2017-10-20', 52, 37.3, null, default, 0);
insert into pas values (null, "Pas5", "Ž", "Terijer", '2018-11-1', 48, 26.3, null, default, 0);
insert into pas values (null, "Pas6", "M", "Haski", '2019-02-11', 25, 12.33, null, default, 0);
insert into pas values (null, "Pas7", "Ž", "Terijer", '2017-03-04', 45, 13.3, null, default, 0);
insert into pas values (null, "Pas8", "M", "Ovcar", '2018-04-21', 65, 67.3, null, default, 0);
insert into pas values (null, "Pas9", "Ž", "Terijer", '2017-05-28', 19, 9.3, null, default, 0);
insert into pas values (null, "Pas10", "M", "Ovcar", '2019-03-19', 64, 34.3, null, default, 0);
insert into pas values (null, "Pas11", "M", "Ovcar", '2019-03-19', 13, 7.32, null, 1, 0);
insert into pas values (null, "Pas12", "Ž", "Terijer", '2019-03-19', 13, 17.3, null, 1, 0);
insert into pas values (null, "Pas13", "M", "Haski", '2019-03-19', 24, 7.33, null, 1, 0);
insert into pas values (null, "Pas14", "M", "Labrador", '2019-03-19', 24, 7.33, null, default, 0);

-- Nalazi
insert into nalaz values (null, "2019-08-08", "Alergija", "1234567891236", 1);
insert into nalaz values (null, "2019-09-08", "Alergija", "1234567891237", 2);
insert into nalaz values (null, "2019-05-08", "Alergija", "1234567891236", 3);
insert into nalaz values (null, "2019-08-05", "Alergija", "1234567891237", 4);
insert into nalaz values (null, "2019-06-08", "Alergija", "1234567891236", 5);
insert into nalaz values (null, "2019-08-06", "Alergija", "1234567891237", 6);
insert into nalaz values (null, "2019-01-08", "Alergija", "1234567891236", 7);
insert into nalaz values (null, "2019-08-01", "Alergija", "1234567891237", 8);
insert into nalaz values (null, "2019-02-08", "Infekcija oka", "1234567891236", 9);
insert into nalaz values (null, "2019-04-02", "Infekcija oka", "1234567891236", 10);
insert into nalaz values (null, "2019-03-03", "Infekcija oka", "1234567891236", 2);
insert into nalaz values (null, "2019-08-08", "Infekcija oka", "1234567891236", 3);
insert into nalaz values (null, "2019-09-09", "Infekcija oka", "1234567891236", 4);
insert into nalaz values (null, "2019-08-04", "Infekcija oka", "1234567891236", 5);
insert into nalaz values (null, "2019-04-08", "Infekcija oka", "1234567891236", 5);

-- Lijekovi
insert into lijek values (0, 'Paracetamol', null, 52);
insert into lijek values (0, 'Amoksicilin', null, 10);
insert into lijek values (0, 'Brufen', null, 5);
insert into lijek values (0, 'Aspirin', null, 13);
insert into lijek values (0, 'Diklofenak', null, 153);
insert into lijek values (0, 'Moloksin', null, 52);
insert into lijek values (0, 'Levaloks 5mg', null, 75);
insert into lijek values (0, 'Letizen', null, 53);
insert into lijek values (0, 'Alventa', null, 57);
insert into lijek values (0, 'Naklofen', null, 25);
insert into lijek values (0, 'Sirup', null, 52);
insert into lijek values (0, 'Nalgesin', null, 53);
insert into lijek values (0, 'Flebaven', null, 45);
insert into lijek values (0, 'Kalcijev karbona', null, 64);
insert into lijek values (0, 'Meglimid', null, 35);
insert into lijek values (0, 'Lanzum', null, 25);

-- Udomitelji 
insert into udomitelj values ('1507988102015', 'Jelena', 'Miletic', 'Banja Luka', '065/722-980');
insert into udomitelj values ('2001991107013', 'Marko', 'Markovic', 'Gradiska', '066/471-352');
insert into udomitelj values ('1507989512015', 'Jelena', 'Markovic', 'Banja Luka', '065/412-456');
insert into udomitelj values ('2001995677013', 'Marko', 'Erceg', 'Gradiska', '066/888-347');
insert into udomitelj values ('1507984502015', 'Ruza', 'Miletic', 'Banja Luka', '065/722-951');
insert into udomitelj values ('2003591107013', 'Nevena', 'Matic', 'Gradiska', '066/135-352');
insert into udomitelj values ('1507988582015', 'Ognjen', 'Djukic', 'Banja Luka', '065/258-159');
insert into udomitelj values ('2001992587853', 'Nikola', 'Pejic', 'Laktasi', '066/963-352');
insert into udomitelj values ('2001914707853', 'Zeljko', 'Lojic', 'Prijedor', '066/128-352');
insert into udomitelj values ('2001993697853', 'Bojan', 'Petrusic', 'Kotor Varos', '066/963-951');
insert into udomitelj values ('2001991101233', 'Nemanja', 'Vrljanovic', 'Kotor Varos', '066/147-654');

-- Psi kod udomitelja
insert into udomljavanjepsa values ("2019-09-09", 11, '1507988102015', null);
insert into udomljavanjepsa values ("2019-09-09", 12, '2001991107013', null);
insert into udomljavanjepsa values ("2019-09-09", 13, '1507989512015', null);

-- Kavezi
insert into kavez values (null, "Kavez1", 1, 3);
insert into kavez values (null, "Kavez2", 2, 4);
insert into kavez values (null, "Kavez3", 1, 3);
insert into kavez values (null, "Kavez4", 2, 4);
insert into kavez values (null, "Kavez5", 0, 3);

-- Psi u kavezima
insert into kavez_pas values ("2019-09-09", 1, 1, null);
insert into kavez_pas values ("2019-09-09", 1, 2, null);
insert into kavez_pas values ("2019-09-09", 2, 3, null);
insert into kavez_pas values ("2019-09-09", 2, 4, null);
insert into kavez_pas values ("2019-09-09", 3, 5, null);
insert into kavez_pas values ("2019-09-09", 3, 6, null);
insert into kavez_pas values ("2019-09-09", 4, 7, null);
insert into kavez_pas values ("2019-09-09", 4, 8, null);
insert into kavez_pas values ("2019-09-09", 5, 9, null);
insert into kavez_pas values ("2019-09-09", 5, 10, null);
insert into kavez_pas values ("2019-09-09", 5, 14, null);



