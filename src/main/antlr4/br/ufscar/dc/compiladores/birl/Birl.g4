grammar Birl;

/* sintático */

program:
body EOF;

body: info (bloco_treino)*;

info:
'nome: ' NOME
'treino: ' NOME
'treinador: ' NOME
'data: ' DATA
;


bloco_treino:  'TREINO'
'tipo: ' DIA_TREINO
(treino)+
'END_TREINO' ;

treino: NOME_EX ',' NUMINT ',' NUMINT ',' NUMINT ';' ;


/* -------------------- LEXICO ------------*/

DIA_TREINO : ('A'..'Z');

NOME : '"' ( ~('"'|'\\'|'\n'|'\r') )* '"';

NUMINT	: ('+'|'-')?('0'..'9')+
	;

NUMINTPOS : ('0'..'9')+
	;

DATA : NUMINTPOS'/'NUMINTPOS'/'NUMINTPOS;

TIPO: 'tipo:';

NOME_EX : 'AGACHAMENTO' | 'SUPINO RETO' | 'ROSCA MARTELO' | 'TRICEPS CORDA'
            | 'TRICEPS TESTA' | 'LEG PRESS' | 'CADEIRA EXTENSORA' | 'CADEIRA FLEXORA'
            | 'VOADOR COSTAS' | 'PUXADA ABERTA' | 'REMADA' | 'PULL DOWN' | 'ABDOMINAL';


fragment
ESC_SEQ	: '\\\'';
COMENTARIO
    :   '%' ~('\n'|'\r')* '\r'? '\n' {skip();}
    ;
WS  :   ( ' '
        | '\t'
        | '\r'
        | '\n'
        ) {skip();}
    ;





