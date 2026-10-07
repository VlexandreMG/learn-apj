ALTER TABLE AS_INGREDIENTS
ADD TYPECONSO VARCHAR2(50);

ALTER TABLE AS_INGREDIENTS
ADD CONSTRAINT FK_AS_INGREDIENT_TYPECONSO
FOREIGN KEY (TYPECONSO)
REFERENCES CATEGORIECONSOMMABLE(ID);

INSERT INTO CATEGORIECONSOMMABLE (ID, VAL, DESCE) VALUES('CTGC004', 'électricité Tranche 1 ', 'électricité Tranche 1 ');
INSERT INTO CATEGORIECONSOMMABLE (ID, VAL, DESCE) VALUES('CTGC005', 'électricité Tranche 2 ', 'électricité Tranche 2 ');
INSERT INTO CATEGORIECONSOMMABLE (ID, VAL, DESCE) VALUES('CTGC006', 'électricité Tranche 3 ', 'électricité Tranche 3 ');

CREATE OR REPLACE VIEW FABNONRATTACHE AS 
SELECT
	f.id AS idfabrication,
	f.daty AS daty,
	ffille.IDINGREDIENTS AS idproduit,
	ai.LIBELLE AS idproduitLib,
	ffille.qte AS qte,
	ffille.qte AS qtepetri,
	l.id AS idligne,
	l.VAL AS ligne
FROM 
FABRICATIONCPL f
LEFT JOIN FABRICATIONFILLE ffille ON f.ID =FFILLE.IDMERE
LEFT JOIN AS_INGREDIENTS ai ON ai.id=ffille.IDINGREDIENTS
LEFT JOIN AS_INGREDIENT_MAINTENANCE aim ON aim.id=ffille.IDMACHINE
LEFT JOIN LIGNE l ON l.id=aim.IDLIGNE
LEFT JOIN RELEVEFAB rfab ON RFAB.IDFAB = f.id
where ai.ispetri=1;

CREATE OR REPLACE VIEW FabricationRattache as
select 
    r.id as idReleveFab,
    r.idreleve as idcompteur,
    f.id as idFabrication,
    r.daty,
    ff.IDINGREDIENTS AS idproduit,
    ai.libelle AS nomProduit,
    ff.qte,
    ff.qte AS qtePetri,
    l.id AS idligne,
    l.val AS ligne
FROM
	ReleveFab r 
    left join Fabrication f on f.id = r.idFab
    left join Offille o on f.idOffille = o.id
	LEFT JOIN FABRICATIONFILLE ff ON ff.IDMERE =f.ID 
    LEFT JOIN AS_INGREDIENTS ai ON ai.id=ff.IDINGREDIENTS 
	LEFT JOIN AS_INGREDIENT_MAINTENANCE m ON m.id=ff.IDMACHINE 
	LEFT JOIN ligne l ON l.id=m.idligne
    where ai.ispetri=1;
