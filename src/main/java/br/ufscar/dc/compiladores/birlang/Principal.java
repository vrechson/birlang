/*
 * Princpal.java is the main class of the project.
 * This class is going to parse, build ast tree and run semantical parser in the generated code.
 *
 */

package br.ufscar.dc.compiladores.birlang;
import br.ufscar.dc.compiladores.birl.BirlLexer;
import br.ufscar.dc.compiladores.birl.BirlParser;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

//import br.ufscar.dc.compiladores.birlang.BirlUtils.TipoLA;
import br.ufscar.dc.compiladores.birlang.BirlUtils.BirlType;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

public class Principal {
    public static void main(String args[]) throws IOException {
        try(PrintWriter pw = new PrintWriter(new File(args[1]))) {
            CharStream cs = CharStreams.fromFileName(args[0]);

            // Instantiate Birl Lexer
            BirlLexer lexer = new BirlLexer(cs);
            CommonTokenStream tokens = new CommonTokenStream(lexer);

            // Instantiate LA Parser
            BirlParser parser = new BirlParser(tokens);
            
            // Instantiate our own custom handler
            parser.removeErrorListeners();
            ErrorHandler mcel = new ErrorHandler(pw);
            parser.addErrorListener(mcel);

            // Parse the code
            BirlParser.ProgramContext ast = parser.program();
            BirlSemantico las = new BirlSemantico();
            las.visitProgram(ast);
            BirlType.semanticError.forEach((s) -> pw.println(s));

            // generate code
            if (!BirlType.semanticError.isEmpty()) {
                pw.println("Fim da compilacao");
            } else {
//                LAGeradorC lac = new LAGeradorC();
//                lac.visitPrograma(ast);
//                try (PrintWriter pwc = new PrintWriter(args[1])) {
//                    pw.println(lac.out.toString());
//                }
            }
            pw.println("Fim da compilacao");

        }
    }
}
