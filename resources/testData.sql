insert into zaposleni values (1234567891234, "Milica", "Matic", "milica", "f50ccb98908dd89ef400372d10750d4c506bebdc34c8141e11d2fb962ebb1b5d", "Srednja strucna sprema", "Banja Luka", 065123456);
insert into zaposleni values (1234567891235, "Stefan", "Erceg", "stefan", "52518386cc33022de894fa0af047bd62666a63c2a6a6e86650e26955058c5acf", "Srednja strucna sprema", "Banja Luka", 065123457);
insert into zaposleni values (1234567891236, "Rade", "Culum", "rade", "0d0bf087d6c879ac9aa426297b2e90528f25f005f82e2aed81e0e15066bfe322", "Srednja strucna sprema", "Banja Luka", 065123458);
insert into zaposleni values (1234567891237, "Milan", "Markovic", "milan", "efee614420c57ddd2a8e91eeef6f6b83d5356c2288155be0f273bef986e3b850", "Srednja strucna sprema", "Banja Luka", 065123459);
insert into zaposleni values (1234567891238, "Jelena", "Aleksic", "jelena", "358ccf83f867e8147663680f52d951dfa9b21a8b7d59b3b46eb917bdf5952c92", "Srednja strucna sprema", "Banja Luka", 065123455);
insert into zaposleni values (1234567891239, "Darko", "Lekic", "darko", "fe79e0ac4b7db16d59a67be682f0c2e85e24241cccbb7a6303446e7105362bcc", "Srednja strucna sprema", "Laktasi", 065123454);


insert into ugovororadu values (null, "Sluzbenik", 1, 1250);
insert into ugovororadu values (null, "Sluzbenik", 1, 1550);
insert into ugovororadu values (null, "Veterinar", 1, 1350);
insert into ugovororadu values (null, "Veterinar", 1, 1270);
insert into ugovororadu values (null, "Administrator", 1, 1294);
insert into ugovororadu values (null, "Administrator", 1, 2194);


insert into zaposleni_ugovor values ("2019-08-01", 1234567891234, 1, null);
insert into zaposleni_ugovor values ("2019-09-01", 1234567891235, 2, null);
insert into zaposleni_ugovor values ("2019-01-01", 1234567891236, 3, null);
insert into zaposleni_ugovor values ("2015-08-01", 1234567891237, 4, null);
insert into zaposleni_ugovor values ("2019-06-01", 1234567891238, 5, null);
insert into zaposleni_ugovor values ("2016-08-01", 1234567891239, 6, null);


insert into sluzbenik values (1234567891234);
insert into sluzbenik values (1234567891235);

insert into veterinar values (1234567891236);
insert into veterinar values (1234567891237);

insert into administrator values (1234567891238);
insert into administrator values (1234567891239);


insert into pas values (null, "Pas1", "M", "Terijer", null, 21, 19.3, null);
insert into pas values (null, "Pas2", "M", "Buldog", null, 11, 12.5, null);
insert into pas values (null, "Pas3", "Ž", "Terijer", null, 14, 17.3, null);
insert into pas values (null, "Pas4", "M", "Buldog", null, 52, 37.3, null);
insert into pas values (null, "Pas5", "Ž", "Terijer", null, 48, 26.3, null);
insert into pas values (null, "Pas6", "M", "Haski", null, 25, 12.33, null);
insert into pas values (null, "Pas7", "Ž", "Terijer", null, 45, 13.3, null);
insert into pas values (null, "Pas8", "M", "Ovcar", null, 65, 67.3, null);
insert into pas values (null, "Pas9", "Ž", "Terijer", null, 19, 9.3, null);
insert into pas values (null, "Pas10", "M", "Ovcar", null, 13, 7.3, null);
insert into pas values (null, "Pas11", "Ž", "Rasa1", null, 52, 27.3, null);
insert into pas values (null, "Pas12", "M", "Terijer", null, 83, 142.3, null);
insert into pas values (null, "Pas13", "Ž", "Pudlica", null, 23, 142.3, null);
insert into pas values (null, "Pas14", "M", "Ovcar", null, 54, 34.3, null);
insert into pas values (null, "Pas15", "M", "Haski", null, 63, 57.3, null);
insert into pas values (null, "Pas16", "Ž", "Buldog", null, 54, 13.3, null);
insert into pas values (null, "Pas17", "Ž", "Ovcar", null, 12, 72.3, null);
insert into pas values (null, "Pas18", "M", "Haski", null, 8, 12.3, null);
insert into pas values (null, "Pas19", "Ž", "Buldog", null, 4, 132.3, null);
insert into pas values (null, "Pas20", "Ž", "Haski", null, 15, 62.3, null);

alter table lijek add column Kolicina INT NOT NULL;

insert into lijek values (0, 'Paracetamol', null, 5);
insert into lijek values (0, 'Amoksicilin', null, 10);

insert into udomitelj values ('1507988102015', 'Jelena', 'Miletic', 'Banja Luka', '065/722-980');
insert into udomitelj values ('2001991107013', 'Marko', 'Markovic', 'Gradiska', '066/777-352');

alter table pas add column Udomljen BOOLEAN NOT NULL DEFAULT false;










