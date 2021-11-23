/*
 * LASemantico.java is a class where LABaseVisitor methods are overwritten.
 * The main purpose to overwrite is to deal with uncompatible types and define other code behavior,
 * such as define variables.
 */
//
//package br.ufscar.dc.compiladores.birlang;
//
//import br.ufscar.dc.compiladores.birlang.BirlUtils.Scopes;
//import br.ufscar.dc.compiladores.birlang.BirlUtils.SymbleTable;
//import br.ufscar.dc.compiladores.birlang.BirlUtils.TipoLA;
//
//public class LASemantico extends LABaseVisitor<Void> {
//    //SymbleTable tabela;
//    Scopes scope;
//
//    // Main visitor
//    @Override
//    public Void visitPrograma(LAParser.ProgramaContext ctx) {
//
//        // Creating new scope
//        scope = new Scopes();
//
//        // This isn't needed anymore since I'm adding a stack for each table
//        // tabela = new TabelaDeSimbolos();
//        return super.visitPrograma(ctx);
//    }
//
//    // Local variables and registers declaration visitor
//    @Override
//    public Void visitDeclaracao_local(LAParser.Declaracao_localContext ctx) {
//
//        // Check variable definitions
//        TipoLA.verifyType(scope, ctx);
//
//        return super.visitDeclaracao_local(ctx);
//    }
//
//    // Variavel visitor
//    @Override
//    public Void visitVariavel(LAParser.VariavelContext ctx) {
//
//        // Moving everything into verifyType func
//        TipoLA.verifyType(scope, ctx);
//
//        return super.visitVariavel(ctx);
//    }
//
//    // Global variables and functions declaration visitor
//    @Override
//    public Void visitDeclaracao_global(LAParser.Declaracao_globalContext ctx) {
//
//        // Remove last global context before proceed
//        if (scope.getContextLevel() > 1)
//            scope.removeContext();
//
//        // Check global structures definitions
//        TipoLA.verifyType(scope, ctx);
//
//        return super.visitDeclaracao_global(ctx);
//    }
//
//    @Override
//    public Void visitCmdAtribuicao(LAParser.CmdAtribuicaoContext ctx) {
//
//        SymbleTable.TipoLA typeAssign;
//
//        // Check assign compability
//        typeAssign = TipoLA.verifyType(scope, ctx);
//        if (typeAssign == SymbleTable.TipoLA.INVALIDO) {
//            String v = ctx.identificador().getText();
//
//            // special case for pointers
//            // TODO: improve this check and pointers compability
//            if (ctx.PONTEIRO() != null)
//                v = "^"+v;
//            TipoLA.adicionarErroSemantico(ctx.expressao().stop, "atribuicao nao compativel para "+v);
//        }
//
//        return super.visitCmdAtribuicao(ctx);
//    }
//
//    @Override
//    public Void visitCmdChamada(LAParser.CmdChamadaContext ctx) {
//
//        // DEBUG:
//        // System.out.println("func: "+ctx.IDENT());
//
//        return super.visitCmdChamada(ctx);
//    }
//
//    // The better way to detect the use of undeclared variables is directly in visitIdentificador.
//    // TODO: check if there is better ways of doing that
//    @Override
//    public Void visitIdentificador(LAParser.IdentificadorContext ctx) {
//
//        // Get identificator type
//        SymbleTable.TipoLA typeId = SymbleTable.TipoLA.INVALIDO;
//        typeId = TipoLA.verifyType(scope, ctx);
//
//        // DEBUG: check if variable already exists in this context
//        // System.out.println("retrieving: "+ctx.getText());
//
//        // Check if variable already exists in this context
//        if (!scope.existsInCurrentScope(ctx.getText())) {
//
//            // Register count to not instatiate locally register variables
//            if (scope.getRegisterCounter() == 0) {
//                // Semantic error! using a undeclared var
//                System.out.println("error not defined in this context: " + ctx.getText());
//                TipoLA.adicionarErroSemantico(ctx.stop, "identificador " + ctx.getText() + " nao declarado");
//            } else {
//
//                // Decrease the counter
//                scope.decreaseRegisterCounter();
//            }
//        }
//
//        return super.visitIdentificador(ctx);
//    }
//
//    @Override
//    public Void visitFator(LAParser.FatorContext ctx) {
//        // DEBUG: Just for debug purposes
//        // System.out.println("visitFator");
//
//        // Verify types compability recursively
//        TipoLA.verifyType(scope, ctx);
//        return super.visitFator(ctx);
//    }
//
//
//}