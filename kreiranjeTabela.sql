/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     6/12/2015 20:24:12                           */
/*==============================================================*/


drop table if exists ANALITIKA_MAGACINSKE_KARTICE;

drop table if exists ARTIKAL;

drop table if exists DRZAVA;

drop table if exists GRUPA_ARTIKALA;

drop table if exists JEDINICA_MERE;

drop table if exists MAGACIN;

drop table if exists MAGACINSKA_KARTICA;

drop table if exists MESTO;

drop table if exists POPISNA_KOMISIJA;

drop table if exists POPISNI_DOKUMENT;

drop table if exists POSLOVNA_GODINA;

drop table if exists POSLOVNI_PARTNER;

drop table if exists PREDUZECE;

drop table if exists PROMETNI_DOKUMENT;

drop table if exists RADNO_MESTO;

drop table if exists SEKTOR;

drop table if exists STAVKA_POPISA;

drop table if exists STAVKA_PROMETNOG_DOKUMENTA;

drop table if exists VRSTA_DOKUMENTA;

drop table if exists ZAPOSLENI;

/*==============================================================*/
/* Table: ANALITIKA_MAGACINSKE_KARTICE                          */
/*==============================================================*/
create table ANALITIKA_MAGACINSKE_KARTICE
(
   ID_ANALITIKA_MAGACINSKE_KARTICE int not null,
   ID_VRSTA_DOKUMENTA   int,
   ID_STAVKA_PROMETNOG_DOKUMENTA int,
   ID_MAGACINSKA_KARTICA int,
   SIFRA_DOKUMENTA      varchar(12) not null,
   REDNI_BROJ           int default 1,
   DATUM_PROMENE        date,
   KOLICINA             decimal,
   CENA                 decimal,
   VREDNOST             decimal,
   SMER                 varchar(1) not null,
   primary key (ID_ANALITIKA_MAGACINSKE_KARTICE)
);

/*==============================================================*/
/* Table: ARTIKAL                                               */
/*==============================================================*/
create table ARTIKAL
(
   ID_ARTIKAL           int not null,
   ID_JEDINICA_MERE     int ,
   ID_GRUPA_ARTIKALA    int,
   SIFRA_ARTIKLA        varchar(12) not null,
   NAZIV_ARTIKLA        varchar(30) not null,
   PAKOVANJE            decimal,
   primary key (ID_ARTIKAL)
);

/*==============================================================*/
/* Table: DRZAVA                                                */
/*==============================================================*/
create table DRZAVA
(
   ID_DRZAVA            int not null,
   SIFRA_DRZAVE         varchar(3) not null,
   NAZIV_DRZAVE         varchar(30) not null,
   primary key (ID_DRZAVA)
);

/*==============================================================*/
/* Table: GRUPA_ARTIKALA                                        */
/*==============================================================*/
create table GRUPA_ARTIKALA
(
   ID_GRUPA_ARTIKALA    int not null,
   ID_PREDUZECE         int,
   SIFRA_GRUPE          varchar(12) not null,
   NAZIV_GRUPE          varchar(30) not null,
   primary key (ID_GRUPA_ARTIKALA)
);

/*==============================================================*/
/* Table: JEDINICA_MERE                                         */
/*==============================================================*/
create table JEDINICA_MERE
(
   ID_JEDINICA_MERE     int not null,
   SIFRA_JEDINICE_MERE  varchar(5) not null,
   NAZIV_JEDINICE_MERE  varchar(20) not null,
   primary key (ID_JEDINICA_MERE)
);

/*==============================================================*/
/* Table: MAGACIN                                               */
/*==============================================================*/
create table MAGACIN
(
   ID_MAGACIN           int not null,
   ID_MESTO             int,
   ID_SEKTOR            int,
   SIFRA_MAGACINA       varchar(12) not null,
   NAZIV_MAGACINA       varchar(30) not null,
   ADRESA_MAGACINA      varchar(50),
   primary key (ID_MAGACIN)
);

/*==============================================================*/
/* Table: MAGACINSKA_KARTICA                                    */
/*==============================================================*/
create table MAGACINSKA_KARTICA
(
   ID_MAGACINSKA_KARTICA int not null,
   ID_ARTIKAL           int ,
   ID_POSLOVNA_GODINA   int,
   ID_MAGACIN           int ,
   REDNI_BROJ_KARTICE   int not null,
   PROSECNA_CENA        decimal,
   KOL__ULAZA           decimal,
   VR__ULAZA            decimal,
   KOL__IZLAZA          decimal,
   VR__IZLAZA           decimal,
   POCETNO_STANJE_KOL   decimal,
   POCETNO_STANJE_VR    decimal,
   primary key (ID_MAGACINSKA_KARTICA)
);

/*==============================================================*/
/* Table: MESTO                                                 */
/*==============================================================*/
create table MESTO
(
   ID_MESTO             int not null,
   ID_DRZAVA            int,
   SIFRA_MESTA          varchar(3) not null,
   NAZIV_MESTA          varchar(30) not null,
   PTT_BROJ             varchar(12),
   primary key (ID_MESTO)
);

/*==============================================================*/
/* Table: POPISNA_KOMISIJA                                      */
/*==============================================================*/
create table POPISNA_KOMISIJA
(
   ID_POPISNA_KOMISIJA  int not null,
   ID_ZAPOSLENI         int,
   ZAP_ID_ZAPOSLENI     int,
   ZAP_ID_ZAPOSLENI2    int,
   ID_POPISNI_DOKUMENT  int ,
   SIFRA_KOMISIJE       varchar(12) not null,
   primary key (ID_POPISNA_KOMISIJA)
);

/*==============================================================*/
/* Table: POPISNI_DOKUMENT                                      */
/*==============================================================*/
create table POPISNI_DOKUMENT
(
   ID_POPISNI_DOKUMENT  int not null,
   ID_POSLOVNA_GODINA   int ,
   ID_MAGACIN           int,
   SIFRA_POPISA         varchar(12) not null,
   DATUM_POPISA         date not null,
   BROJ_POPISA_U_GODNI  int default 1,
   STATUS_PREDAJE       ENUM('neproknjizen', 'proknjizen', 'zakljucen'),
   primary key (ID_POPISNI_DOKUMENT)
);

/*==============================================================*/
/* Table: POSLOVNA_GODINA                                       */
/*==============================================================*/
create table POSLOVNA_GODINA
(
   ID_POSLOVNA_GODINA   int not null,
   ID_PREDUZECE         int ,
   BROJ_GODINE          int not null default 1,
   ZAKLJUCENA_GODINA    bool default false,
   primary key (ID_POSLOVNA_GODINA)
);

/*==============================================================*/
/* Table: POSLOVNI_PARTNER                                      */
/*==============================================================*/
create table POSLOVNI_PARTNER
(
   ID_POSLOVNI_PARTNER  int not null,
   ID_MESTO             int,
   SIFRA_PARTNERA       varchar(12) not null,
   NAZIV_PARTNERA       varchar(30) not null,
   BROJ_TELEFONA_PARTNERA varchar(20),
   ADRESA_PARTNERA      varchar(50),
   VRSTA_PARTNERA       ENUM("kupac","dobavljac","kupac_i_dobavljac"),
   MAIL_ADRESA          varchar(30),
   KONTAKT_OSOBA        varchar(50),
   primary key (ID_POSLOVNI_PARTNER)
);

/*==============================================================*/
/* Table: PREDUZECE                                             */
/*==============================================================*/
create table PREDUZECE
(
   ID_PREDUZECE         int not null,
   ID_MESTO             int not null,
   SIFRA_PREDUZECA      varchar(12) not null,
   NAZIV_PREDUZECA      varchar(30) not null,
   ADRESA               varchar(50) not null,
   TELEFON              varchar(20) not null,
   FAX                  varchar(12),
   primary key (ID_PREDUZECE)
);

/*==============================================================*/
/* Table: PROMETNI_DOKUMENT                                     */
/*==============================================================*/
create table PROMETNI_DOKUMENT
(
   ID_PROMETNI_DOKUMENT int not null,
   ID_MAGACIN           int,
   ID_POSLOVNA_GODINA   int ,
   ID_POSLOVNI_PARTNER  int,
   MAG_ID_MAGACIN       int ,
   BROJ                 int not null,
   DATUM_NASTANKA       date not null,
   DATUM_KNJIZENJA      date not null,
   STATUS_DOKUMENTA     varchar(1024),
   primary key (ID_PROMETNI_DOKUMENT)
);

/*==============================================================*/
/* Table: RADNO_MESTO                                           */
/*==============================================================*/
create table RADNO_MESTO
(
   SIFRA_RADNOG_MESTA   varchar(5) not null,
   NAZIV_RADNOG_MESTA   varchar(30) not null,
   ID_RADNO_MESTO       int not null,
   primary key (ID_RADNO_MESTO)
);

/*==============================================================*/
/* Table: SEKTOR                                                */
/*==============================================================*/
create table SEKTOR
(
   ID_SEKTOR            int not null,
   ID_PREDUZECE         int,
   SIFRA_SEKTORA        varchar(3) not null,
   NAZIV_SEKTORA        varchar(30) not null,
   primary key (ID_SEKTOR)
);

/*==============================================================*/
/* Table: STAVKA_POPISA                                         */
/*==============================================================*/
create table STAVKA_POPISA
(
   ID_STAVKA_POPISA     int not null,
   ID_ARTIKAL           int ,
   ID_POPISNI_DOKUMENT  int ,
   REDNI_BROJ_STAVKE    int not null,
   KOLICINA_PO_POPISU   decimal,
   KOLICINA_U_KARTICI   decimal,
   CENA_AKTUELNA_PRI_POPISU decimal,
   VREDNOST_PO_POPISU   decimal,
   VREDNOST_U_KARTICI   decimal,
   primary key (ID_STAVKA_POPISA)
);

/*==============================================================*/
/* Table: STAVKA_PROMETNOG_DOKUMENTA                            */
/*==============================================================*/
create table STAVKA_PROMETNOG_DOKUMENTA
(
   ID_STAVKA_PROMETNOG_DOKUMENTA int,
   ID_ARTIKAL           int ,
   ID_PROMETNI_DOKUMENT int ,
   RBR                  int not null default 1,
   KOLICINA_PR_DOKUMENTA decimal,
   CENA_STAVKE          decimal,
   VREDNOST_STAVKE      decimal,
   primary key (ID_STAVKA_PROMETNOG_DOKUMENTA)
);

/*==============================================================*/
/* Table: VRSTA_DOKUMENTA                                       */
/*==============================================================*/
create table VRSTA_DOKUMENTA
(
   ID_VRSTA_DOKUMENTA   int not null,
   SIFRA_VRSTE          varchar(3) not null,
   NAZIV_VRSTE          varchar(30) not null,
   primary key (ID_VRSTA_DOKUMENTA)
);

/*==============================================================*/
/* Table: ZAPOSLENI                                             */
/*==============================================================*/
create table ZAPOSLENI
(
   ID_ZAPOSLENI         int not null,
   ID_RADNO_MESTO       int,
   ID_PREDUZECE         int ,
   ID_MESTO             int,
   SIFRA_ZAPOSLENOG     varchar(12) not null,
   JMBG                 varchar(13) not null,
   IME                  varchar(30) not null,
   PREZIME              varchar(30) not null,
   DATUM_RODJENJA       date not null,
   ADRESA_ZAPOSLENOG    varchar(50) not null,
   BROJ_TELEFONA        varchar(20) not null,
   STATUS_ZAPOSLENOG    ENUM("aktivan","penzionisan","neaktivan","probni","odsutan") not null,
   primary key (ID_ZAPOSLENI)
);

alter table ANALITIKA_MAGACINSKE_KARTICE add constraint FK_ANALITIKA_ZA_MAGACINSKU_KARTICU foreign key (ID_MAGACINSKA_KARTICA)
      references MAGACINSKA_KARTICA (ID_MAGACINSKA_KARTICA) on delete restrict on update restrict;

alter table ANALITIKA_MAGACINSKE_KARTICE add constraint FK_STAVKA_DOKUMENTA foreign key (ID_STAVKA_PROMETNOG_DOKUMENTA)
      references STAVKA_PROMETNOG_DOKUMENTA (ID_STAVKA_PROMETNOG_DOKUMENTA) on delete restrict on update restrict;

alter table ANALITIKA_MAGACINSKE_KARTICE add constraint FK_VRSTA_DOKUMENTA_U_ANALITICI foreign key (ID_VRSTA_DOKUMENTA)
      references VRSTA_DOKUMENTA (ID_VRSTA_DOKUMENTA) on delete restrict on update restrict;

alter table ARTIKAL add constraint FK_MERA_ZA_ARTIKAL foreign key (ID_JEDINICA_MERE)
      references JEDINICA_MERE (ID_JEDINICA_MERE) on delete restrict on update restrict;

alter table ARTIKAL add constraint FK_PODELA_NA_GRUPE foreign key (ID_GRUPA_ARTIKALA)
      references GRUPA_ARTIKALA (ID_GRUPA_ARTIKALA) on delete restrict on update restrict;

alter table GRUPA_ARTIKALA add constraint FK_GRUPE_PREDUZECA foreign key (ID_PREDUZECE)
      references PREDUZECE (ID_PREDUZECE) on delete restrict on update restrict;

alter table MAGACIN add constraint FK_MAGACINI_SEKTORA foreign key (ID_SEKTOR)
      references SEKTOR (ID_SEKTOR) on delete restrict on update restrict;

alter table MAGACIN add constraint FK_MESTO_MAGACINA foreign key (ID_MESTO)
      references MESTO (ID_MESTO) on delete restrict on update restrict;

alter table MAGACINSKA_KARTICA add constraint FK_KARTICA_ZA_ARTIKAL foreign key (ID_ARTIKAL)
      references ARTIKAL (ID_ARTIKAL) on delete restrict on update restrict;

alter table MAGACINSKA_KARTICA add constraint FK_MAGACIN_ZA_KOJI_VAZI_KARTICA foreign key (ID_MAGACIN)
      references MAGACIN (ID_MAGACIN) on delete restrict on update restrict;

alter table MAGACINSKA_KARTICA add constraint FK_ZA_TEKUCU_GODINU foreign key (ID_POSLOVNA_GODINA)
      references POSLOVNA_GODINA (ID_POSLOVNA_GODINA) on delete restrict on update restrict;

alter table MESTO add constraint FK_DRZAVA_KOJOJ_PRIPADA_MESTO foreign key (ID_DRZAVA)
      references DRZAVA (ID_DRZAVA) on delete restrict on update restrict;

alter table POPISNA_KOMISIJA add constraint FK_CLAN_1 foreign key (ZAP_ID_ZAPOSLENI)
      references ZAPOSLENI (ID_ZAPOSLENI) on delete restrict on update restrict;

alter table POPISNA_KOMISIJA add constraint FK_CLAN_2 foreign key (ID_ZAPOSLENI)
      references ZAPOSLENI (ID_ZAPOSLENI) on delete restrict on update restrict;

alter table POPISNA_KOMISIJA add constraint FK_PREDAJE_DOKUMENT foreign key (ID_POPISNI_DOKUMENT)
      references POPISNI_DOKUMENT (ID_POPISNI_DOKUMENT) on delete restrict on update restrict;

alter table POPISNA_KOMISIJA add constraint FK_PREDSEDNIK_KOMISIJE foreign key (ZAP_ID_ZAPOSLENI2)
      references ZAPOSLENI (ID_ZAPOSLENI) on delete restrict on update restrict;

alter table POPISNI_DOKUMENT add constraint FK_TEKUCA_GODINA_ZA_POPIS foreign key (ID_POSLOVNA_GODINA)
      references POSLOVNA_GODINA (ID_POSLOVNA_GODINA) on delete restrict on update restrict;

alter table POPISNI_DOKUMENT add constraint FK_ZA_MAGACIN_U_KOJEM_JE_IZVRSEN_POPIS foreign key (ID_MAGACIN)
      references MAGACIN (ID_MAGACIN) on delete restrict on update restrict;

alter table POSLOVNA_GODINA add constraint FK_GODINE_POSLOVANJA foreign key (ID_PREDUZECE)
      references PREDUZECE (ID_PREDUZECE) on delete restrict on update restrict;

alter table POSLOVNI_PARTNER add constraint FK_MESTO_PARTNERA foreign key (ID_MESTO)
      references MESTO (ID_MESTO) on delete restrict on update restrict;

alter table PREDUZECE add constraint FK_RELATIONSHIP_22 foreign key (ID_MESTO)
      references MESTO (ID_MESTO) on delete restrict on update restrict;

alter table PROMETNI_DOKUMENT add constraint FK_KUPAC_ILI_DOBAVLJAC foreign key (ID_POSLOVNI_PARTNER)
      references POSLOVNI_PARTNER (ID_POSLOVNI_PARTNER) on delete restrict on update restrict;

alter table PROMETNI_DOKUMENT add constraint FK_MAGACIN_U_PROMETU foreign key (ID_MAGACIN)
      references MAGACIN (ID_MAGACIN) on delete restrict on update restrict;

alter table PROMETNI_DOKUMENT add constraint FK_TEKUCA_GODINA_ZA_DOKUMENT foreign key (ID_POSLOVNA_GODINA)
      references POSLOVNA_GODINA (ID_POSLOVNA_GODINA) on delete restrict on update restrict;

alter table PROMETNI_DOKUMENT add constraint FK_ZA_MEDJUMAGACINSKI_TRANSFER___ULAZNI foreign key (MAG_ID_MAGACIN)
      references MAGACIN (ID_MAGACIN) on delete restrict on update restrict;

alter table SEKTOR add constraint FK_SEKTORI_PREDUZECA foreign key (ID_PREDUZECE)
      references PREDUZECE (ID_PREDUZECE) on delete restrict on update restrict;

alter table STAVKA_POPISA add constraint FK_ARTIKAL_NA_POPISU foreign key (ID_ARTIKAL)
      references ARTIKAL (ID_ARTIKAL) on delete restrict on update restrict;

alter table STAVKA_POPISA add constraint FK_STAVKE_NA_POPISNOM_DOKUMENTU foreign key (ID_POPISNI_DOKUMENT)
      references POPISNI_DOKUMENT (ID_POPISNI_DOKUMENT) on delete restrict on update restrict;

alter table STAVKA_PROMETNOG_DOKUMENTA add constraint FK_STAVKA_ZA_ARTIKAL foreign key (ID_ARTIKAL)
      references ARTIKAL (ID_ARTIKAL) on delete restrict on update restrict;

alter table STAVKA_PROMETNOG_DOKUMENTA add constraint FK_STAVKE_ZA_DOKUMENT foreign key (ID_PROMETNI_DOKUMENT)
      references PROMETNI_DOKUMENT (ID_PROMETNI_DOKUMENT) on delete restrict on update restrict;

alter table ZAPOSLENI add constraint FK_RASPOREDJEN_NA_RADNO_MESTO foreign key (ID_RADNO_MESTO)
      references RADNO_MESTO (ID_RADNO_MESTO) on delete restrict on update restrict;

alter table ZAPOSLENI add constraint FK_RELATIONSHIP_19 foreign key (ID_MESTO)
      references MESTO (ID_MESTO) on delete restrict on update restrict;

alter table ZAPOSLENI add constraint FK_RUKOVODILAC foreign key (ID_PREDUZECE)
      references PREDUZECE (ID_PREDUZECE) on delete restrict on update restrict;

