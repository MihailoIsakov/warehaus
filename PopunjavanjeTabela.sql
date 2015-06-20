INSERT INTO `xws`.`Invoice` (`acountNumber`, `buyerAddress`, `buyerName`, `currency`, `currencyDate`, `date`, `deleted`, `suplierAddress`, `suplierName`, `supplierPib`, `totalAmount`, `totalGoodsValue`, `totalRabate`, `totalServiceValue`, `totalTax`, `totalValue`, `version`) VALUES ('1226458', 'Skojevska 12', 'Home Brew INC', 'rsd', '7.1.2015.', '7.1.2015.', '0', 'Zorza Klemansoa 18', 'Majstor za pivo', '1232233', '5000', '5000', '0', '0', '20', '5000', '0');

INSERT INTO `xws`.`InvoiceItem` (`amount`, `deleted`, `goodsName`, `measureUnit`, `minusRabat`, `orderNumber`, `pricePerUnit`, `quantity`, `rabateAmount`, `rabatePercentage`, `totalTax`, `version`, `invoiceId`) VALUES ('3000', '0', 'Hmelj', 'kilogram', '0', '1', '1000', '3', '0', '0', '20', '0', (select id from `xws`.`Invoice` where acountNumber='1226458'));
INSERT INTO `xws`.`InvoiceItem` (`amount`, `deleted`, `goodsName`, `measureUnit`, `minusRabat`, `orderNumber`, `pricePerUnit`, `quantity`, `rabateAmount`, `rabatePercentage`, `totalTax`, `version`, `invoiceId`) VALUES ('2000', '0', 'Jecam', 'kilogram', '0', '2', '1000', '2', '0', '0', '20', '0', (select id from `xws`.`Invoice` where acountNumber='1226458'));

INSERT INTO `xws`.`User` (`deleted`, `password`, `username`, `version`) VALUES ('0', '21232f297a57a5a743894a0e4a801fc3', 'admin', '0');

INSERT INTO `xws`.`drzava` (`ID_DRZAVA`, `NAZIV_DRZAVE`, `SIFRA_DRZAVE`) VALUES ('1', 'Srbija', 'RS');
INSERT INTO `xws`.`drzava` (`ID_DRZAVA`, `NAZIV_DRZAVE`, `SIFRA_DRZAVE`) VALUES ('2', 'Crna Gora', 'CG');
INSERT INTO `xws`.`drzava` (`ID_DRZAVA`, `NAZIV_DRZAVE`, `SIFRA_DRZAVE`) VALUES ('3', 'Makedonija', 'MK');

INSERT INTO `xws`.`mesto` (`ID_MESTO`, `NAZIV_MESTA`, `PTT_BROJ`, `SIFRA_MESTA`, `ID_DRZAVA`) VALUES ('1', 'Sabac', '15000', 'SA', '1');
INSERT INTO `xws`.`mesto` (`ID_MESTO`, `NAZIV_MESTA`, `PTT_BROJ`, `SIFRA_MESTA`, `ID_DRZAVA`) VALUES ('2', 'Beograd', '11000', 'BG', '1');
INSERT INTO `xws`.`mesto` (`ID_MESTO`, `NAZIV_MESTA`, `PTT_BROJ`, `SIFRA_MESTA`, `ID_DRZAVA`) VALUES ('3', 'Podgorica', '40000', 'PG', '2');

INSERT INTO `xws`.`preduzece` (`ID_PREDUZECE`, `adresa`, `fax`, `NAZIV_PREDUZECA`, `SIFRA_PREDUZECA`, `telefon`, `ID_MESTO`) VALUES ('1', 'adresa1', 'fax1', 'Zorka', '322', '324342', '1');
INSERT INTO `xws`.`preduzece` (`ID_PREDUZECE`, `adresa`, `fax`, `NAZIV_PREDUZECA`, `SIFRA_PREDUZECA`, `telefon`, `ID_MESTO`) VALUES ('2', 'adresa2', 'fax2', 'preduzece2', '3424', '34243', '2');
INSERT INTO `xws`.`preduzece` (`ID_PREDUZECE`, `adresa`, `fax`, `NAZIV_PREDUZECA`, `SIFRA_PREDUZECA`, `telefon`, `ID_MESTO`) VALUES ('3', 'adresa3', 'fax3', 'preduzece3', '342', '432', '1');

INSERT INTO `xws`.`sektor` (`ID_SEKTOR`, `NAZIV_SEKTORA`, `SIFRA_SEKTORA`, `ID_PREDUZECE`) VALUES ('1', 'sektor1', '234', '1');
INSERT INTO `xws`.`sektor` (`ID_SEKTOR`, `NAZIV_SEKTORA`, `SIFRA_SEKTORA`, `ID_PREDUZECE`) VALUES ('2', 'sektor2', '43', '1');
INSERT INTO `xws`.`sektor` (`ID_SEKTOR`, `NAZIV_SEKTORA`, `SIFRA_SEKTORA`, `ID_PREDUZECE`) VALUES ('3', 'sektor3', '42', '2');
INSERT INTO `xws`.`sektor` (`ID_SEKTOR`, `NAZIV_SEKTORA`, `SIFRA_SEKTORA`, `ID_PREDUZECE`) VALUES ('4', 'sektor4', '55', '3');
INSERT INTO `xws`.`sektor` (`ID_SEKTOR`, `NAZIV_SEKTORA`, `SIFRA_SEKTORA`, `ID_PREDUZECE`) VALUES ('5', 'sektor5', '56', '3');

INSERT INTO `xws`.`magacin` (`ID_MAGACIN`, `ADRESA_MAGACINA`, `NAZIV_MAGACINA`, `SIFRA_MAGACINA`, `ID_MESTO`, `ID_SEKTOR`) VALUES ('1', 'adresa1', 'magacin1', '123', '1', '1');
INSERT INTO `xws`.`magacin` (`ID_MAGACIN`, `ADRESA_MAGACINA`, `NAZIV_MAGACINA`, `SIFRA_MAGACINA`, `ID_MESTO`, `ID_SEKTOR`) VALUES ('2', 'adresa2', 'magacin2', '423', '1', '2');
INSERT INTO `xws`.`magacin` (`ID_MAGACIN`, `ADRESA_MAGACINA`, `NAZIV_MAGACINA`, `SIFRA_MAGACINA`, `ID_MESTO`, `ID_SEKTOR`) VALUES ('3', 'adresa3', 'magacin3', '555', '2', '3');
INSERT INTO `xws`.`magacin` (`ID_MAGACIN`, `ADRESA_MAGACINA`, `NAZIV_MAGACINA`, `SIFRA_MAGACINA`, `ID_MESTO`, `ID_SEKTOR`) VALUES ('4', 'adresa4', 'magacin4', '99', '3', '4');

INSERT INTO `xws`.`jedinica_mere` (`ID_JEDINICA_MERE`, `NAZIV_JEDINICE_MERE`, `SIFRA_JEDINICE_MERE`) VALUES ('1', 'kg', '32');
INSERT INTO `xws`.`jedinica_mere` (`ID_JEDINICA_MERE`, `NAZIV_JEDINICE_MERE`, `SIFRA_JEDINICE_MERE`) VALUES ('2', 'kom', '22');

INSERT INTO `xws`.`artikal` (`ID_ARTIKAL`, `NAZIV_ARTIKLA`, `pakovanje`, `SIFRA_ARTIKLA`, `ID_JEDINICA_MERE`) VALUES ('1', 'artika1', '30', '123', '1');
INSERT INTO `xws`.`artikal` (`ID_ARTIKAL`, `NAZIV_ARTIKLA`, `pakovanje`, `SIFRA_ARTIKLA`, `ID_JEDINICA_MERE`) VALUES ('2', 'artika2', '50', '432', '1');

INSERT INTO `xws`.`poslovna_godina` (`ID_POSLOVNA_GODINA`, `BROJ_GODINE`, `ZAKLJUCENA_GODINA`, `ID_PREDUZECE`) VALUES ('1', '2014', '0', '1');
INSERT INTO `xws`.`poslovna_godina` (`ID_POSLOVNA_GODINA`, `BROJ_GODINE`, `ZAKLJUCENA_GODINA`, `ID_PREDUZECE`) VALUES ('2', '2013', '0', '1');

INSERT INTO `xws`.`magacinska_kartica` (`ID_MAGACINSKA_KARTICA`, `KOL__IZLAZA`, `KOL__ULAZA`, `POCETNO_STANJE_KOL`, `POCETNO_STANJE_VR`, `PROSECNA_CENA`, `REDNI_BROJ_KARTICE`, `VR__IZLAZA`, `VR__ULAZA`, `ID_ARTIKAL`, `ID_MAGACIN`, `ID_POSLOVNA_GODINA`) VALUES ('1', '231', '123', '123', '123', '43', '1', '55', '55', '1', '1', '1');
INSERT INTO `xws`.`magacinska_kartica` (`ID_MAGACINSKA_KARTICA`, `KOL__IZLAZA`, `KOL__ULAZA`, `POCETNO_STANJE_KOL`, `POCETNO_STANJE_VR`, `PROSECNA_CENA`, `REDNI_BROJ_KARTICE`, `VR__IZLAZA`, `VR__ULAZA`, `ID_ARTIKAL`, `ID_MAGACIN`, `ID_POSLOVNA_GODINA`) VALUES ('2', '44', '44', '54', '54', '66', '2', '545', '43', '1', '2', '2');

INSERT INTO `xws`.`vrsta_dokumenta` (`ID_VRSTA_DOKUMENTA`, `SIFRA_VRSTE`, `NAZIV_VRSTE`) VALUES ('1', '1', 'primka');
INSERT INTO `xws`.`vrsta_dokumenta` (`ID_VRSTA_DOKUMENTA`, `SIFRA_VRSTE`, `NAZIV_VRSTE`) VALUES ('2', '2', 'otpremnica');

INSERT INTO `xws`.`poslovni_partner` (`ID_POSLOVNI_PARTNER`, `ADRESA_PARTNERA`, `BROJ_TELEFONA_PARTNERA`, `KONTAKT_OSOBA`, `NAZIV_PARTNERA`) VALUES ('1', 'sdfds', 'fdsdf', 'fsfdsdf', 'partner1');

INSERT INTO `xws`.`prometni_dokument` (`ID_PROMETNI_DOKUMENT`, `ID_MAGACIN`, `ID_POSLOVNA_GODINA`, `ID_POSLOVNI_PARTNER`, `MAG_ID_MAGACIN`, `BROJ`, `DATUM_NASTANKA`, `DATUM_KNJIZENJA`, `STATUS_DOKUMENTA`) VALUES ('1', '1', '1', '1', '1', '1', '2011-10-10', '2011-10-10', 'proknjizen');

INSERT INTO `xws`.`stavka_prometnog_dokumenta` (`ID_STAVKA_PROMETNOG_DOKUMENTA`, `CENA_STAVKE`, `KOLICINA_PR_DOKUMENTA`, `rbr`, `VREDNOST_STAVKE`, `ID_ARTIKAL`, `ID_PROMETNI_DOKUMENT`) VALUES ('1', '123', '123', '1', '23', '1', '1');
