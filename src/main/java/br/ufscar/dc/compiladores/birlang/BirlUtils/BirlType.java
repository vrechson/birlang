package br.ufscar.dc.compiladores.birlang.BirlUtils;/*
 * TipoLA.java is a class where a lot of types checks and types registering are made.
 * Actually, all the program logic from LASemantico.java is treated here.
 */
;
import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.Token;

public class BirlType {
    public static List<String> semanticError = new ArrayList<>();

    public static void adicionarErroSemantico(Token t, String mensagem) {
        int linha = t.getLine();
        int coluna = t.getCharPositionInLine();

        semanticError.add(String.format("Linha %d: %s", linha, mensagem));
    }

 

}
