use azil;

drop procedure if exists add_employee_contract;
delimiter $$
create procedure add_employee_contract(in pJMBG char(13), in pIme varchar(255), in pPrezime varchar(255), in pUsername varchar(255), in pPassword varchar(255), in pStrucnaSprema varchar(255), in pMjestoPrebivalista varchar(255), in pBrojTelefona varchar(255), in pPozicija varchar(255), in pPlata decimal, in pOd date, in pDo date)
begin 
    declare vID int;
    DECLARE exit handler for sqlexception
	BEGIN
		ROLLBACK;
	END;
	START TRANSACTION;
        insert into zaposleni values (pJMBG, pIme, pPrezime, pUsername, pPassword, pStrucnaSprema, pMjestoPrebivalista, pBrojTelefona);
        if pPozicija = "Administrator"
        then
			insert into administrator values (pJMBG);
		elseif pPozicija = "Sluzbenik"
		then
			insert into sluzbenik values (pJMBG);
		elseif pPozicija = "Veterinar"
		then
			insert into veterinar values (pJMBG);
        end if;
        insert into ugovororadu values (null, pPozicija, 1, pPlata);
        set vID = LAST_INSERT_ID();
        insert into zaposleni_ugovor values (pOd, pJMBG, vID, pDo);
    COMMIT;
end$$
delimiter ;


drop procedure if exists add_dog_in_cage;
delimiter $$
create procedure add_dog_in_cage(in pOd date, in pIdKaveza int, in pIdPsa int, in pDo date)
begin 
    declare vID int;
    DECLARE exit handler for sqlexception
	BEGIN
		ROLLBACK;
	END;
	START TRANSACTION;
		UPDATE kavez_pas SET Do = current_date() WHERE IdPsa = pIdPsa AND Do IS NULL;
        INSERT INTO kavez_pas VALUES (pOd, pIdKaveza, pIdPsa, null);
        UPDATE kavez SET Kapacitet = Kapacitet + 1 WHERE IdKaveza = (select IdKaveza from kavez_pas WHERE IdPsa = pIdPsa AND Do IS NOT NULL LIMIT 1);
        UPDATE kavez SET Kapacitet = Kapacitet - 1 WHERE IdKaveza = pIdKaveza;
    COMMIT;
end$$
delimiter ;




/*
drop trigger if exists update_dog_in_cage;
delimiter $$
create trigger update_dog_in_cage before insert on kavez_pas
for each row 
begin
	if new.Do is null
    then
		update kavez_pas
		set Do = current_date()
		where IdPsa = new.IdPsa and Do is null and IdKaveza != new.IdKaveza;
    end if;
end$$
delimiter ;
*/

/*
drop trigger if exists update_cages_decrease;
delimiter $$
create trigger update_cages_decrease after insert on kavez_pas
for each row 
begin
	update kavez
    set kapacitet = kapacitet - 1
    where IdKaveza = new.IdKaveza;
end$$
delimiter ;


drop trigger if exists update_cages_increase;
delimiter $$
create trigger update_cages_increase after update on kavez_pas
for each row 
begin
	if new.Do is not null
    then
		update kavez
		set kapacitet = kapacitet + 1
		where IdKaveza = new.IdKaveza;
    end if;
end$$
delimiter ;
*/




