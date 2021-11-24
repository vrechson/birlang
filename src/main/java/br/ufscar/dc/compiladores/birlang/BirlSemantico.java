package br.ufscar.dc.compiladores.birlang;
import br.ufscar.dc.compiladores.birl.BirlBaseVisitor;
import br.ufscar.dc.compiladores.birl.BirlParser;
import br.ufscar.dc.compiladores.birlang.BirlUtils.BirlType;


public class BirlSemantico extends BirlBaseVisitor<Void> {

    // Main visitor
    @Override
    public Void visitProgram(BirlParser.ProgramContext ctx) {

        visitBody(ctx.body());
        return null;
    }

    @Override
    public Void visitBody(BirlParser.BodyContext ctx) {

        if (ctx.info() != null) {
            visitInfo(ctx.info());
        }

        if (ctx.bloco_treino().size() > 0) {
            for (var b : ctx.bloco_treino()) {
                visitBloco_treino(b);
            }
        }
        return null;
    }

    @Override
    public Void visitInfo(BirlParser.InfoContext ctx) {
        if (ctx.NOME().size() < 3) {
            BirlType.adicionarErroSemantico(ctx.stop, " numero de atribuicoes insuficientes para informacoes de treino proximo a: "+ctx.stop.getText());
        }

        return null;
    }

    @Override
    public Void visitBloco_treino(BirlParser.Bloco_treinoContext ctx) {

        if (ctx.DIA_TREINO() == null) {
            // add error
            BirlType.adicionarErroSemantico(ctx.stop, " dia de treino nao definido proximo a: "+ctx.stop.getText());
        } else if (ctx.treino() != null) {
            for (var t : ctx.treino()) {
                if (t.NOME_EX() == null ||
                t.NUMINT().size() < 3) {
                    // add error
                    BirlType.adicionarErroSemantico(ctx.stop, " treino definido de maneira incorreta proximo a: "+ctx.stop.getText());
                }
            }
        }
        for (var t: ctx.treino()) {
            for (var n : t.NUMINT()) {
                if (Integer.parseInt(n.getText()) < 0) {
                    BirlType.adicionarErroSemantico(ctx.stop, " valor " + n.getText() + " nao pode ser negativo.");
                }
            }
        }

        return null;
    }
}
