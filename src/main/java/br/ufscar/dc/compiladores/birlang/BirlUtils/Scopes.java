///*
// * Scope.java is a class where different variables contexts are managed.
// * Since LA Language, such as C, separe different contexts of variables,
// * this class will mantain this structure working.
// */
//package br.ufscar.dc.compiladores.birlang.BirlUtils;
//
//import java.util.LinkedList;
//import java.util.List;
//
//public class Scopes {
//    private LinkedList<SymbleTable> tableStack;
//
//    public Scopes() {
//
//        // Each scope must create its own tableStack
//        tableStack = new LinkedList<>();
//        createNewScope();
//    }
//
//    // Scope management functions
//
//    // Create new scope
//    public void createNewScope() {
//        tableStack.push(new SymbleTable());
//    }
//
//    // Get the currently working context
//    public SymbleTable getCurrentContext() {
//        return tableStack.peek();
//    }
//
//    // I don't know if this function is really needed, may delete it in the future
//    // Maybe for debug and error reasons
//    public List<SymbleTable> walkNestedContext() {
//        return tableStack;
//    }
//
//    // Delete a context from stack
//    public void removeContext() { tableStack.pop();}
//
//    // Get in which level of stack we are currently working
//    // I have my questions if this is the better way of working with this stack context
//    public int getContextLevel() { return tableStack.size();}
//
//    // Current context utils
//
//    // Check if a variable exists localy
//    public boolean existsInCurrentScope(String v) {
//        SymbleTable curr = this.getCurrentContext();
//        return curr.existe(v);
//    }
//
//    // Get a local variable type
//    public SymbleTable.TipoLA getSymbolType(String v) {
//        SymbleTable curr = this.getCurrentContext();
//        return curr.verificar(v);
//    }
//
//    // Add a local variable
//    public void addSymbol(String n, SymbleTable.TipoLA t) {
//        SymbleTable curr = this.getCurrentContext();
//        curr.addSymbol(n, t);
//    }
//
//    // Pointer associated functions
//
//    // Add a pointer to the context
//    public void addSymbol(String n, SymbleTable.TipoLA t, SymbleTable.TipoLA s) {
//        SymbleTable curr = this.getCurrentContext();
//        curr.addSymbol(n, t, s);
//    }
//
//    // Register associated functions
//
//    // Register a new type
//    public void registerType(String n, SymbleTable.TipoLA t) {
//        SymbleTable curr = this.getCurrentContext();
//        curr.registerType(n, t);
//    }
//
//    // Instantiate a variable from a custom type
//    public void instanceType(String n, String t) {
//        SymbleTable curr = this.getCurrentContext();
//        curr.instanceType(n, t);
//    }
//
//    // Add a register variable
//    public void addRegisterVariable(String nr, String nv, SymbleTable.TipoLA t) {
//        SymbleTable curr = this.getCurrentContext();
//        curr.addRegisterVariable(nr, nv, t);
//    }
//
//    // Check if a variable exists in a register
//    public boolean existsInRegisterList(String v) {
//        SymbleTable curr = this.getCurrentContext();
//        //return curr.RegisterListCheck(v);
//        return true;
//    }
//
//    public void increseRegisterCounter() {
//        SymbleTable curr = this.getCurrentContext();
//        // DEBUG:
//        //System.out.println("increasing register count");
//        curr.increseRegisterCounter();
//    }
//
//    public void decreaseRegisterCounter() {
//        SymbleTable curr = this.getCurrentContext();
//        curr.decreseRegisterCounter();
//    }
//
//    public Integer getRegisterCounter() {
//        SymbleTable curr = this.getCurrentContext();
//        return curr.getRegisterCounter();
//    }
//
//    // Functions
//
//    // add a register
//    public void addFunctionParameter(String nf, String np, SymbleTable.TipoLA t) {
//        SymbleTable curr = this.getCurrentContext();
//        curr.addFunctionParameter(nf, np, t);
//    }
//
//}
