DROP TABLE IF EXISTS MODEL_ROL;
DROP TABLE IF EXISTS MODEL_USUARI;
DROP TABLE IF EXISTS MODEL_USUARI_ROL;
 
CREATE TABLE MODEL_ROL
(
  IDROL         VARCHAR2(30)                          NOT NULL,
  MEMO          VARCHAR2(100)                         NOT NULL,
  ASSIGNABLESN  CHAR(1)                               NOT NULL,
  USERALTA      VARCHAR2(20)                          NOT NULL,
  DATAALTA      DATE                                  NOT NULL,
  USERMODI      VARCHAR2(20),
  DATAMODI      DATE
);
ALTER TABLE MODEL_ROL ADD CONSTRAINT PK_MODEL_ROL PRIMARY KEY (IDROL);
 
CREATE TABLE MODEL_USUARI
(
  NIS                            INT                  NOT NULL,
  NOMCOMPLET                     VARCHAR2(100)        NOT NULL,
  DEFAULT_IDIOMA                 CHAR(2),
  DEFAULT_ROL                    VARCHAR2(30),
  USERALTA                       INT                  NOT NULL,
  DATAALTA                       DATE                 NOT NULL,
  USERMODI                       INT,
  DATAMODI                       DATE
);
ALTER TABLE MODEL_USUARI ADD CONSTRAINT PK_MODEL_USUARI PRIMARY KEY (NIS);
 
CREATE TABLE MODEL_USUARI_ROL
(
  NIS       INT                                       NOT NULL,
  IDROL     VARCHAR2(30)                              NOT NULL,
  USERALTA  VARCHAR2(20)                              NOT NULL,
  DATAALTA  DATE                                      NOT NULL,
  USERMODI  VARCHAR2(20),
  DATAMODI  DATE
);
ALTER TABLE MODEL_USUARI_ROL ADD CONSTRAINT PK_MODEL_USUARI_ROL PRIMARY KEY (NIS, IDROL);