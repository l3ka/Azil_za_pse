use azil;

drop procedure if exists add_employee_contract;
delimiter $$
create procedure add_employee_contract(in pJMBG char(13), in pIme varchar(45), in pPrezime varchar(45), in pUsername varchar(255), in pPassword varchar(255), in pStrucnaSprema varchar(255), in pMjestoPrebivalista varchar(255), in pBrojTelefona varchar(255), in pPozicija varchar(45), in pPlata decimal(10, 0), in pOd date, in pDo date)
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