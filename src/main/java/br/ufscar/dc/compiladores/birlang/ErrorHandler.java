/*
 * ErrorHandler.java is a class where some ANTLR default errors are overwritten.
 * Instead of them, I defined my set of error messages
 */


package br.ufscar.dc.compiladores.birlang;

import java.io.PrintWriter;
import java.util.BitSet;
import org.antlr.v4.runtime.ANTLRErrorListener;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.dfa.DFA;

public class ErrorHandler implements ANTLRErrorListener {
    
    private PrintWriter pw;
    public ErrorHandler(PrintWriter pw) {
        this.pw = pw;
    }

    @Override
    public void	reportAmbiguity(Parser recognizer, DFA dfa, int startIndex, int stopIndex, boolean exact, BitSet ambigAlts, ATNConfigSet configs) {
        // Não será necessário para o T2, pode deixar vazio
    }

    @Override
    public void reportAttemptingFullContext(Parser recognizer, DFA dfa, int startIndex, int stopIndex, BitSet conflictingAlts, ATNConfigSet configs) {
        // Não será necessário para o T2, pode deixar vazio
    }

    @Override
    public void reportContextSensitivity(Parser recognizer, DFA dfa, int startIndex, int stopIndex, int prediction, ATNConfigSet configs) {
        // Não será necessário para o T2, pode deixar vazio
    }

    @Override
    public void	syntaxError(Recognizer<?,?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {

        Token t = (Token) offendingSymbol;

        // Define a set of error messages
        if ("\"".equals(t.getText()))
            msg = "Linha "+line+": cadeia literal nao fechada";
        else if ("@".equals(t.getText()) || "|".equals(t.getText()) || "!".equals(t.getText()))
            msg = "Linha "+line+": "+t.getText()+" - simbolo nao identificado";
        else if ("{".equals(t.getText()))
            msg = "Linha "+line+": comentario nao fechado";
        else if ("<EOF>".equals(t.getText()))
            msg = "Linha "+line+": erro sintatico proximo a EOF";
        else
            msg = "Linha "+line+": erro sintatico proximo a "+t.getText();

        // Print results in terminal and in output file
        System.out.println(msg);
        pw.println(msg);
        System.out.println("Fim da compilacao");
        pw.println("Fim da compilacao");

        // Close output file before exit
        pw.close();
        System.exit(0);
        
    }

}