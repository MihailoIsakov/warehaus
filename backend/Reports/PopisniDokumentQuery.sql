SELECT (BROJ_POPISA_U_GODNI, SIFRA_POPISA, STATUS_PREDAJE, NAZIV_MAGACINA, BROJ_GODINE, DATUM_POPISA, ID_ZAPOSLENI1,
	REDNI_BROJ_STAVKE, NAZIV_ARTIKLA, CENA_AKTUELNA_PRI_POPISU)
FROM xws.stavka_popisa
join xws.artikal on xws.stavka_popisa.ID_ARTIKAL = xws.artikal.ID_ARTIKAL
join xws.popisni_dokument on xws.stavka_popisa.ID_POPISNI_DOKUMENT = xws.popisni_dokument.ID_POPISNI_DOKUMENT
join xws.magacin on xws.popisni_dokument.ID_MAGACIN = xws.magacin.ID_MAGACIN
join xws.poslovna_godina on xws.popisni_dokument.ID_POSLOVNA_GODINA = xws.poslovna_godina.ID_POSLOVNA_GODINA;