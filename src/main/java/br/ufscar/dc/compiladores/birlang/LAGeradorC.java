///*
// * LAGeradorC.java is a class where C equivalent code is generated from this parser.
// * The main purpose to build executable codes from LA algorithms.
// *
// */
//
//package br.ufscar.dc.compiladores.birlang;
//
//import br.ufscar.dc.compiladores.birlang.BirlUtils.Scopes;
//import br.ufscar.dc.compiladores.birlang.BirlUtils.SymbleTable;
//import br.ufscar.dc.compiladores.birlang.BirlUtils.TipoLA;
//
//public class LAGeradorC extends LABaseVisitor<Void>{
//    StringBuilder out;
//    Scopes scope;
//
//    // Constructor
//    public LAGeradorC() {
//        this.out = new StringBuilder();
//        this.scope = new Scopes();
//    }
//
//    @Override
//    public Void visitPrograma(LAParser.ProgramaContext ctx) {
//
//
//        // Creating new scope
//        scope = new Scopes();
//
//        // Declaring libs
//        out.append("#include <stdio.h>\n");
//        out.append("#include <stdlib.h>\n\n");
//
//        // Visiting declaration
//        if (ctx.declaracoes() != null)
//            visitDeclaracoes(ctx.declaracoes());
//
//        // Visiting body
//        visitCorpo(ctx.corpo());
//
//        return null;
//    }
//
//    @Override
//    public Void visitDeclaracoes(LAParser.DeclaracoesContext ctx) {
//
//        // DEBUG: reached visit Declaracoes
//        // System.out.println("visitDeclaracoes");
//
//        for (var d : ctx.decl_local_global()) {
//
//            visitDecl_local_global(d);
//        }
//        return null;
//    }
//
//    @Override
//    public Void visitDecl_local_global(LAParser.Decl_local_globalContext ctx) {
//
//        // DEBUG:
//        // System.out.println("visitDecl_local_global");
//
//        if (ctx.declaracao_global() != null) {
//            visitDeclaracao_global(ctx.declaracao_global());
//        } else if (ctx.declaracao_local() != null) {
//            visitDeclaracao_local(ctx.declaracao_local());
//        }
//        return null;
//    }
//
//    @Override
//    public Void visitDeclaracao_global(LAParser.Declaracao_globalContext ctx) {
//        // DEBUG:
//        // System.out.println("visitDeclaracao_global");
//        return null;
//    }
//
//    @Override
//    public Void visitDeclaracao_local(LAParser.Declaracao_localContext ctx) {
//        // DEBUG: check if it reached here
//        // System.out.println("visitDeclaracao_local");
//        if (ctx.DECL() != null) {
//
//            for (var v : ctx.variavel().identificador()) {
//                String typeC = "";
//
//                // Check the variable type
//                System.out.println("visitDeclaracao_local");
//                switch (ctx.variavel().tipo().getText()) {
//                    case "inteiro":
//                        typeC = "int";
//                        break;
//                    case "real":
//                        typeC = "float";
//                        break;
//                    case "literal":
//                        typeC = "char";
//                        break;
//                }
//
//                // If it is a character, make it an array
//                if (typeC.contains("char"))
//                    out.append(typeC + " " + v.getText()+"[100];\n");
//                else
//                    out.append(typeC + " " + v.getText()+";\n");
//
//                // Add it to the symbol table
//                scope.addSymbol(v.getText(), SymbleTable.getType(ctx.variavel().tipo().getText()));
//            }
//
//            // DEBUG: print result
//            //S ystem.out.println(ctx.variavel().getText());
//            visitVariavel(ctx.variavel());
//
//
//        } else if (ctx.CONSTANTE() != null) {
//            // TODO: define this fields
//        } else if (ctx.TIPO() != null) {
//            // TODO: define this fields
//        }
//
//        return null;
//    }
//
//    @Override
//    public Void visitCorpo(LAParser.CorpoContext ctx) {
//        // DEBUG: check if it is vising the body
//        // System.out.println("visitCorpo");
//
//
//        // Start the main function
//        out.append("\n");
//        out.append("int main() {\n\n");
//
//        // Declare local variables
//        for (var v : ctx.declaracao_local()) {
//            visitDeclaracao_local(v);
//        }
//
//        // Add all commands to the program
//        for (var c : ctx.cmd()) {
//            visitCmd(c);
//        }
//
//        // exit success
//        out.append("return 0;\n}\n");
//        return null;
//    }
//
//    @Override
//    public Void visitCmd(LAParser.CmdContext ctx) {
//
//        // Check which is the command in this context
//        if (ctx.cmdLeia() != null) {
//            visitCmdLeia(ctx.cmdLeia());
//        } else if (ctx.cmdEscreva() != null) {
//            visitCmdEscreva(ctx.cmdEscreva());
//        } else if (ctx.cmdAtribuicao() != null) {
//            visitCmdAtribuicao(ctx.cmdAtribuicao());
//        } else if (ctx.cmdSe() != null) {
//            visitCmdSe(ctx.cmdSe());
//        } else if (ctx.cmdCaso() != null) {
//            visitCmdCaso(ctx.cmdCaso());
//        }
//
//        return null;
//    }
//
//    @Override
//    public Void visitCmdLeia(LAParser.CmdLeiaContext ctx) {
//        // DEBUG: print leia
//        //System.out.println("reached visitCmdLeia");
//        String template = "";
//
//        // Adding scanf variables
//        out.append("scanf(\"");
//        for (var v : ctx.identificador()) {
//
//            // I'm using the SymbleTable to store the types, I think this is the best way
//            SymbleTable.TipoLA t = scope.getSymbolType(v.getText());
//
//            // Check the correct template between the types
//            if (t == SymbleTable.TipoLA.INTEIRO)
//                template = "%d";
//            else if (t == SymbleTable.TipoLA.REAL)
//                template = "%f";
//            else if (t == SymbleTable.TipoLA.LITERAL)
//                template = "%s";
//
//            out.append(template);
//        }
//        out.append("\",");
//
//        // Adding scanf parameters
//        for (int i = 0; i < ctx.identificador().size();) {
//            out.append("&"+ctx.identificador(i).getText());
//            if (++i < ctx.identificador().size())
//                out.append(", ");
//        }
//
//        out.append(");\n");
//        return null;
//    }
//
//    @Override
//    public Void visitCmdEscreva(LAParser.CmdEscrevaContext ctx) {
//        // DEBUG: print escreva
//        System.out.println(ctx.getText());
//
//        // Define the template string
//        String template = "";
//
//        // Adding printf variables
//        out.append("printf(\"");
//        for (var v : ctx.expressao()) {
//            //SymbleTable.TipoLA t = scope.getSymbolType(v.getText());
//
//            // Check the expression type
//            SymbleTable.TipoLA t = TipoLA.verifyType(scope, v);
//
//            // Same as read function
//            if (t == SymbleTable.TipoLA.INTEIRO)
//                template = "%d";
//            else if (t == SymbleTable.TipoLA.REAL)
//                template = "%f";
//            else if (t == SymbleTable.TipoLA.LITERAL)
//                template = "%s";
//
//            out.append(template);
//        }
//        out.append("\",");
//
//        // Adding printf parameters
//        for (int i = 0; i < ctx.expressao().size();) {
//            out.append(ctx.expressao(i).getText());
//            if (++i < ctx.expressao().size())
//                out.append(", ");
//        }
//
//
//        // Close the function
//        out.append(");\n");
//
//        return null;
//    }
//
//    // visitAtribuicao
//    @Override
//    public Void visitCmdAtribuicao(LAParser.CmdAtribuicaoContext ctx) {
//
//        out.append(ctx.identificador().getText()+" = "+ctx.expressao().getText()+";\n");
//        return null;
//    }
//
//    @Override
//    public Void visitCmdSe(LAParser.CmdSeContext ctx) {
//
//        // Start condition
//        out.append("if (");
//
//        // Expression to evaluate
//        // visitExpressao(ctx.expressao())
//        //out.append(ctx.expressao().getText());
//        visitExpressao(ctx.expressao());
//        // End of expression
//        out.append(") {\n");
//
//        for (var c : ctx.if_stmt) {
//            visitCmd(c);
//        }
//
//
//        // End of Condition block
//        out.append("}");
//
//        if (ctx.SENAO() != null) {
//            out.append(" else {\n");
//
//            for (var c : ctx.else_stmt) {
//                visitCmd(c);
//            }
//
//            out.append("}");
//        }
//        out.append("\n");
//        return null;
//    }
//
//    @Override
//    public Void visitCmdCaso(LAParser.CmdCasoContext ctx) {
//
//        // Start Switch statement
//        out.append("switch (");
//
//        // Visit Switch expression
//        visitExp_aritmetica(ctx.exp_aritmetica());
//
//        // End switch condition
//        out.append(") {\n");
//
//        visitSelecao(ctx.selecao());
//
//        out.append("}\n");
//        return null;
//    }
//
//
//    @Override
//    public Void visitExpressao(LAParser.ExpressaoContext ctx) {
//
//        visitTermo_logico(ctx.termo_logico(0));
//        for (int i = 1; i < ctx.termo_logico().size(); i++) {
//            visitOp_logico_1(ctx.op_logico_1(i-1));
//            visitTermo_logico(ctx.termo_logico(i));
//        }
//
//        return null;
//    }
//
//    @Override
//    public Void visitOp_logico_1(LAParser.Op_logico_1Context ctx) {
//
//        out.append(" || ");
//        return null;
//    }
//
//    @Override
//    public Void visitTermo_logico(LAParser.Termo_logicoContext ctx) {
//
//        visitFator_logico(ctx.fator_logico(0));
//        for (int i = 1; i < ctx.fator_logico().size(); i++) {
//            visitOp_logico_2(ctx.op_logico_2(i-1));
//            visitFator_logico(ctx.fator_logico(i));
//        }
//
//        return null;
//    }
//
//    @Override
//    public Void visitOp_logico_2(LAParser.Op_logico_2Context ctx) {
//
//        out.append(" && ");
//        return null;
//    }
//
//    @Override
//    public Void visitFator_logico(LAParser.Fator_logicoContext ctx) {
//
////        if (ctx.NAO() != null);
////            out.append("!");
//
//        visitParcela_logica(ctx.parcela_logica());
//        return null;
//    }
//
//    @Override
//    public Void visitParcela_logica(LAParser.Parcela_logicaContext ctx) {
//
//        if (ctx.FALSO() != null)
//            out.append("0");
//        else if (ctx.VERDADEIRO() != null)
//            out.append("1");
//        else
//            visitExp_relacional(ctx.exp_relacional());
//
//        return null;
//    }
//
//    @Override
//    public Void visitExp_relacional(LAParser.Exp_relacionalContext ctx) {
//
//        visitExp_aritmetica(ctx.exp_aritmetica(0));
//        for (int i = 1; i < ctx.exp_aritmetica().size(); i++) {
//            visitOp_relacional(ctx.op_relacional());
//            visitExp_aritmetica(ctx.exp_aritmetica(i));
//        }
//        return null;
//    }
//
//    @Override
//    public Void visitOp_relacional(LAParser.Op_relacionalContext ctx) {
//        if (ctx.DIFERENTE() != null) {
//            out.append(" != ");
//        } else if (ctx.MAIOR() != null) {
//            out.append(" > ");
//        } else if (ctx.MAIORIGUAL() != null) {
//            out.append(" >= ");
//        } else if (ctx.MENOR() != null) {
//            out.append(" < ");
//        } else if (ctx.MENORIGUAL() != null) {
//            out.append(" <= ");
//        } else {
//            out.append(" == ");
//        }
//        return null;
//    }
//
//    @Override
//    public Void visitExp_aritmetica(LAParser.Exp_aritmeticaContext ctx) {
//
//        visitTermo(ctx.termo(0));
//        for (int i = 1; i < ctx.termo().size(); i++) {
//            visitOp1(ctx.op1(i-1));
//            visitTermo(ctx.termo(i));
//        }
//        return null;
//    }
//
//    @Override
//    public Void visitOp1(LAParser.Op1Context ctx) {
//        if (ctx.MENOS() != null)
//            out.append(" - ");
//        else
//            out.append(" + ");
//
//        return null;
//    }
//
//    @Override
//    public Void visitTermo(LAParser.TermoContext ctx) {
//
//        visitFator(ctx.fator(0));
//        for (int i = 1; i < ctx.fator().size(); i++) {
//            visitOp2(ctx.op2(i-1));
//            visitFator(ctx.fator(i));
//        }
//        return null;
//    }
//
//    @Override
//    public Void visitOp2(LAParser.Op2Context ctx) {
//        if (ctx.DIV() != null)
//            out.append(" / ");
//        else
//            out.append(" * ");
//
//        return null;
//    }
//
//    @Override
//    public Void visitFator(LAParser.FatorContext ctx) {
//
//        visitParcela(ctx.parcela(0));
//        for (int i = 1; i < ctx.parcela().size(); i++) {
//            visitOp3(ctx.op3(i-1));
//            visitParcela(ctx.parcela(i));
//        }
//        return null;
//    }
//
//    @Override
//    public Void visitOp3(LAParser.Op3Context ctx) {
//        out.append(" % ");
//
//        return null;
//    }
//
//
//    @Override
//    public Void visitParcela(LAParser.ParcelaContext ctx) {
//
//        if (ctx.op_unario() != null) {
//            out.append("-");
//        }
//        if (ctx.parcela_unario() != null) {
//            visitParcela_unario(ctx.parcela_unario());
//        } else {
//            visitParcela_nao_unario(ctx.parcela_nao_unario());
//        }
//
//        return null;
//    }
//
//    @Override
//    public Void visitParcela_unario(LAParser.Parcela_unarioContext ctx) {
//        if (ctx.PONTEIRO() != null) {
//            out.append("*");
//        }
//
//        // TODO: populate other results
//        if (ctx.identificador() != null) {
//
//        } else if (ctx.IDENT() != null) {
//
//        } else if (ctx.NUM_INT() != null) {
//            out.append(ctx.NUM_INT().getText());
//        } else if (ctx.NUM_REAL() != null) {
//            out.append(ctx.NUM_REAL().getText());
//
//        } else if (ctx.expressao().size() == 1) {
//            out.append("(");
//            visitExpressao(ctx.expressao(0));
//            out.append(")");
//        } else {
//            out.append("(");
//            for (int i = 0; i<ctx.expressao().size(); i++) {
//                visitExpressao(ctx.expressao(0));
//                if (i == (ctx.expressao().size()-1)) {
//                    out.append(", ");
//                }
//
//            }
//
//            out.append(")");
//        }
//        return null;
//    }
//
//    @Override
//    public Void visitParcela_nao_unario(LAParser.Parcela_nao_unarioContext ctx) {
//
//        if (ctx.CADEIA() != null) {
//            out.append(ctx.CADEIA().getText());
//        }
//        return null;
//    }
//
//}
//
//
//
