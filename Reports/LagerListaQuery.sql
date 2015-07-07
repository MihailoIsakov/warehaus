select * from xws.artikal join xws.magacinska_kartica on xws.artikal.ID_ARTIKAL = xws.magacinska_kartica.ID_ARTIKAL
join xws.poslovna_godina on xws.magacinska_kartica.ID_POSLOVNA_GODINA = xws.poslovna_godina.ID_POSLOVNA_GODINA 
join xws.jedinica_mere on xws.artikal.ID_JEDINICA_MERE = xws.jedinica_mere.ID_JEDINICA_MERE;