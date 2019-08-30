-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema azil
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `azil` ;

-- -----------------------------------------------------
-- Schema azil
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `azil` DEFAULT CHARACTER SET utf8 ;
USE `azil` ;

-- -----------------------------------------------------
-- Table `azil`.`Zaposleni`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `azil`.`Zaposleni` ;

CREATE TABLE IF NOT EXISTS `azil`.`Zaposleni` (
  `JMBG` CHAR(13) NOT NULL,
  `Ime` VARCHAR(45) NULL,
  `Prezime` VARCHAR(45) NULL,
  `Username` VARCHAR(255) NOT NULL UNIQUE,
  `Password` VARCHAR(255) NOT NULL,
  `StrucnaSprema` VARCHAR(255),
  `MjestoPrebivalista` VARCHAR(45) NULL,
  `BrojTelefona` VARCHAR(45) NULL,
  PRIMARY KEY (`JMBG`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `azil`.`UgovorORadu`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `azil`.`UgovorORadu` ;

CREATE TABLE IF NOT EXISTS `azil`.`UgovorORadu` (
  `IdUgovora` INT NOT NULL AUTO_INCREMENT,
  `Pozicija` VARCHAR(45) NULL,
  `aktivan` TINYINT(1),
  `Plata` DECIMAL NULL,
  PRIMARY KEY (`IdUgovora`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `azil`.`Zaposleni_Ugovor`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `azil`.`Zaposleni_Ugovor` ;

CREATE TABLE IF NOT EXISTS `azil`.`Zaposleni_Ugovor` (
  `Od` DATE NOT NULL,
  `Zaposlenik_JMBG` CHAR(13) NOT NULL,
  `UgovorORadu_IdUgovora` INT NOT NULL,
  `Do` DATE NULL,
  PRIMARY KEY (`Od`, `Zaposlenik_JMBG`, `UgovorORadu_IdUgovora`),
  INDEX `fk_Zaposlenik_Ugovor_Zaposlenik_idx` (`Zaposlenik_JMBG` ASC),
  INDEX `fk_Zaposlenik_Ugovor_UgovorORadu1_idx` (`UgovorORadu_IdUgovora` ASC),
  CONSTRAINT `fk_Zaposlenik_Ugovor_Zaposlenik`
    FOREIGN KEY (`Zaposlenik_JMBG`)
    REFERENCES `azil`.`Zaposleni` (`JMBG`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Zaposlenik_Ugovor_UgovorORadu1`
    FOREIGN KEY (`UgovorORadu_IdUgovora`)
    REFERENCES `azil`.`UgovorORadu` (`IdUgovora`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `azil`.`Sluzbenik`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `azil`.`Sluzbenik` ;

CREATE TABLE IF NOT EXISTS `azil`.`Sluzbenik` (
  `Zaposleni_JMBG` CHAR(13) NOT NULL,
  PRIMARY KEY (`Zaposleni_JMBG`),
  CONSTRAINT `fk_Sluzbenik_Zaposlenik1`
    FOREIGN KEY (`Zaposleni_JMBG`)
    REFERENCES `azil`.`Zaposleni` (`JMBG`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `azil`.`Veterinar`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `azil`.`Veterinar` ;

CREATE TABLE IF NOT EXISTS `azil`.`Veterinar` (
  `Zaposleni_JMBG` CHAR(13) NOT NULL,
  PRIMARY KEY (`Zaposleni_JMBG`),
  CONSTRAINT `fk_Veterinar_Zaposlenik1`
    FOREIGN KEY (`Zaposleni_JMBG`)
    REFERENCES `azil`.`Zaposleni` (`JMBG`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `azil`.`Administrator`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `azil`.`Administrator` ;

CREATE TABLE IF NOT EXISTS `azil`.`Administrator` (
  `Zaposleni_JMBG` CHAR(13) NOT NULL,
  PRIMARY KEY (`Zaposleni_JMBG`),
  CONSTRAINT `fk_Administrator_Zaposlenik1`
    FOREIGN KEY (`Zaposleni_JMBG`)
    REFERENCES `azil`.`Zaposleni` (`JMBG`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `azil`.`Pas`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `azil`.`Pas` ;

CREATE TABLE IF NOT EXISTS `azil`.`Pas` (
  `IdPsa` INT NOT NULL AUTO_INCREMENT,
  `Ime` VARCHAR(45) NULL,
  `Pol` CHAR(1) NULL,
  `Rasa` VARCHAR(45) NULL,
  `DatumRodjenja` DATE NULL,
  `Visina` INT NULL,
  `Tezina` DOUBLE NULL,
  `Fotografija` VARCHAR(255) NULL,
  PRIMARY KEY (`IdPsa`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `azil`.`Nalaz`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `azil`.`Nalaz` ;

CREATE TABLE IF NOT EXISTS `azil`.`Nalaz` (
  `IdNalaza` VARCHAR(45) NOT NULL,
  `DatumPregleda` DATE NULL,
  `Dijagnoza` LONGTEXT NULL,
  `Veterinar_Zaposleni_JMBG` CHAR(13) NOT NULL,
  `Pas_IdPsa` INT NOT NULL,
  PRIMARY KEY (`IdNalaza`),
  INDEX `fk_Nalaz_Veterinar1_idx` (`Veterinar_Zaposleni_JMBG` ASC),
  INDEX `fk_Nalaz_Pas1_idx` (`Pas_IdPsa` ASC),
  CONSTRAINT `fk_Nalaz_Veterinar1`
    FOREIGN KEY (`Veterinar_Zaposleni_JMBG`)
    REFERENCES `azil`.`Veterinar` (`Zaposleni_JMBG`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Nalaz_Pas1`
    FOREIGN KEY (`Pas_IdPsa`)
    REFERENCES `azil`.`Pas` (`IdPsa`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `azil`.`Lijek`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `azil`.`Lijek` ;

CREATE TABLE IF NOT EXISTS `azil`.`Lijek` (
  `IdLijeka` INT NOT NULL AUTO_INCREMENT,
  `NazivLijeka` VARCHAR(45) NULL,
  `UpotrebljivDo` DATE NULL,
  `Opis` VARCHAR(255) NULL,
  PRIMARY KEY (`IdLijeka`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `azil`.`DodavanjeLijeka`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `azil`.`DodavanjeLijeka` ;

CREATE TABLE IF NOT EXISTS `azil`.`DodavanjeLijeka` (
  `VrijemeDodavanja` DATETIME NOT NULL,
  `Sluzbenik_Zaposleni_JMBG` CHAR(13) NOT NULL,
  `Lijek_IdLijeka` INT NOT NULL,
  `Kolicina` INT NULL,
  PRIMARY KEY (`VrijemeDodavanja`, `Sluzbenik_Zaposleni_JMBG`, `Lijek_IdLijeka`),
  INDEX `fk_DodavanjeLijeka_Sluzbenik1_idx` (`Sluzbenik_Zaposleni_JMBG` ASC),
  INDEX `fk_DodavanjeLijeka_Lijek1_idx` (`Lijek_IdLijeka` ASC),
  CONSTRAINT `fk_DodavanjeLijeka_Sluzbenik1`
    FOREIGN KEY (`Sluzbenik_Zaposleni_JMBG`)
    REFERENCES `azil`.`Sluzbenik` (`Zaposleni_JMBG`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_DodavanjeLijeka_Lijek1`
    FOREIGN KEY (`Lijek_IdLijeka`)
    REFERENCES `azil`.`Lijek` (`IdLijeka`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `azil`.`UzimanjeLijeka`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `azil`.`UzimanjeLijeka` ;

CREATE TABLE IF NOT EXISTS `azil`.`UzimanjeLijeka` (
  `DatumUzimanja` DATETIME NOT NULL,
  `Sluzbenik_Zaposleni_JMBG` CHAR(13) NOT NULL,
  `Lijek_IdLijeka` INT NOT NULL,
  `Nalaz_IdNalaza` VARCHAR(45) NOT NULL,
  `Kolicina` VARCHAR(45) NULL,
  PRIMARY KEY (`DatumUzimanja`, `Sluzbenik_Zaposleni_JMBG`, `Lijek_IdLijeka`, `Nalaz_IdNalaza`),
  INDEX `fk_UzimanjeLijeka_Sluzbenik1_idx` (`Sluzbenik_Zaposleni_JMBG` ASC),
  INDEX `fk_UzimanjeLijeka_Lijek1_idx` (`Lijek_IdLijeka` ASC) ,
  INDEX `fk_UzimanjeLijeka_Nalaz1_idx` (`Nalaz_IdNalaza` ASC) ,
  CONSTRAINT `fk_UzimanjeLijeka_Sluzbenik1`
    FOREIGN KEY (`Sluzbenik_Zaposleni_JMBG`)
    REFERENCES `azil`.`Sluzbenik` (`Zaposleni_JMBG`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_UzimanjeLijeka_Lijek1`
    FOREIGN KEY (`Lijek_IdLijeka`)
    REFERENCES `azil`.`Lijek` (`IdLijeka`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_UzimanjeLijeka_Nalaz1`
    FOREIGN KEY (`Nalaz_IdNalaza`)
    REFERENCES `azil`.`Nalaz` (`IdNalaza`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `azil`.`Kavez`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `azil`.`Kavez` ;

CREATE TABLE IF NOT EXISTS `azil`.`Kavez` (
  `IdKaveza` INT NOT NULL AUTO_INCREMENT,
  `Kapacitet` INT NULL,
  PRIMARY KEY (`IdKaveza`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `azil`.`Kavez_Pas`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `azil`.`Kavez_Pas` ;

CREATE TABLE IF NOT EXISTS `azil`.`Kavez_Pas` (
  `Od` DATETIME NOT NULL,
  `Kavez_IdKaveza` INT NOT NULL,
  `Pas_IdPsa` INT NOT NULL,
  `Do` DATETIME NULL,
  PRIMARY KEY (`Kavez_IdKaveza`, `Pas_IdPsa`, `Od`),
  INDEX `fk_Kavez_Pas_Kavez1_idx` (`Kavez_IdKaveza` ASC) ,
  INDEX `fk_Kavez_Pas_Pas1_idx` (`Pas_IdPsa` ASC) ,
  CONSTRAINT `fk_Kavez_Pas_Kavez1`
    FOREIGN KEY (`Kavez_IdKaveza`)
    REFERENCES `azil`.`Kavez` (`IdKaveza`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Kavez_Pas_Pas1`
    FOREIGN KEY (`Pas_IdPsa`)
    REFERENCES `azil`.`Pas` (`IdPsa`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `azil`.`Udomitelj`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `azil`.`Udomitelj` ;

CREATE TABLE IF NOT EXISTS `azil`.`Udomitelj` (
  `JMBG` CHAR(13) NOT NULL,
  `Ime` VARCHAR(45) NULL,
  `Prezime` VARCHAR(45) NULL,
  `MjestoPrebivalista` VARCHAR(45) NULL,
  `BrojTelefona` VARCHAR(45) NULL,
  PRIMARY KEY (`JMBG`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `azil`.`UdomljavanjePsa`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `azil`.`UdomljavanjePsa` ;

CREATE TABLE IF NOT EXISTS `azil`.`UdomljavanjePsa` (
  `Datum` DATE NOT NULL,
  `Pas_IdPsa` INT NOT NULL,
  `Udomitelj_JMBG` CHAR(13) NOT NULL,
  PRIMARY KEY (`Datum`, `Pas_IdPsa`, `Udomitelj_JMBG`),
  INDEX `fk_UdomljavanjePsa_Pas1_idx` (`Pas_IdPsa` ASC) ,
  INDEX `fk_UdomljavanjePsa_Udomitelj1_idx` (`Udomitelj_JMBG` ASC) ,
  CONSTRAINT `fk_UdomljavanjePsa_Pas1`
    FOREIGN KEY (`Pas_IdPsa`)
    REFERENCES `azil`.`Pas` (`IdPsa`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_UdomljavanjePsa_Udomitelj1`
    FOREIGN KEY (`Udomitelj_JMBG`)
    REFERENCES `azil`.`Udomitelj` (`JMBG`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
