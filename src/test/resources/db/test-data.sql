/* Al test-data.sql només posarem els inserts imprescindibles per poder fer login */
/* Després ja per cada test farem fitxers apart */
 
INSERT INTO MODEL_ROL VALUES ('ROL_TEST', 'Rol Test', 'N', 127681, '2018-04-19', null, null);
 
INSERT INTO MODEL_USUARI VALUES (127681, 'Marta', 'ca', 'ROL_TEST', 4537, '2018-04-19', null, null);
INSERT INTO MODEL_USUARI_ROL VALUES (127681, 'ROL_TEST', 127681, '2018-04-19', null, null);
