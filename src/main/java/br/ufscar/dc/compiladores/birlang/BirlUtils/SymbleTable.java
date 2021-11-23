///*
// * SymbleTable.java is a class where all types from the LA Language are defined.
// * From simple types to user defined structured will be treated here.
// */
//
//package br.ufscar.dc.compiladores.birlang.BirlUtils;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//
//public class SymbleTable {
//
//    // Language types
//    public enum TipoLA {
//        LITERAL,
//        INTEIRO,
//        REAL,
//        LOGICO,
//        PONTEIRO,
//        REGISTRO,
//        FUNCAO,
//        INVALIDO
//
//    }
//
//    public class Symble {
//        public String nome;
//        public TipoLA tipo;
//
//        // Some complex type are a bit more difficult to implement
//        public TipoLA subtipo;
//
//        public Register register;
//        public Function func;
//        // Need to keep track of what add to registers
//        // List???
//        boolean isType;
//
//        // Symble constructor for simple types
//        public Symble(String nome, TipoLA tipo) {
//            this.nome = nome;
//            this.tipo = tipo;
//            this.subtipo = null;
//            this.register = null;
//            this.func = null;
//            this.isType = false;
//
//            if (tipo == TipoLA.REGISTRO) {
//                this.register = new Register();
//            } else if (tipo == TipoLA.FUNCAO) {
//                this.func = new Function();
//            }
//
//        }
//
//        // Symble constructor for pointers
//        private Symble(String nome, TipoLA tipo, TipoLA subtipo) {
//            this.nome = nome;
//            this.tipo = tipo;
//            this.subtipo = subtipo;
//            this.register = null;
//            this.isType = false;
//        }
//
//        // Register a new type (TODO: check if this is still necessary from the last changes)
//        private Symble(String nome, TipoLA tipo, boolean isType) {
//            this.nome = nome;
//            this.tipo = tipo;
//            this.subtipo = subtipo;
//            this.register = new Register();
//            this.isType = isType;
//        }
//    }
//
//    public class Register {
//        private ArrayList<Symble> variables;
//
//        // This type should hold another symble...
//        public Register () {
//            this.variables = new ArrayList<Symble>();
//        }
//
//        // Add a variable to the register
//        public void addVariable(String nome, TipoLA tipo) {
//            Symble var = new Symble(nome, tipo);
//            this.variables.add(var);
//        }
//
//        // Get variables from the register
//        public ArrayList<Symble> getVariables() {
//            return this.variables;
//        }
//
//        public boolean containsKey (String n) {
//            for (var v : variables) {
//                if (n.equals(v.nome)) {
//                    return true;
//                }
//
//            }
//            return false;
//        }
//
//        // Check the type of a register variable
//        public TipoLA variableType (String n) {
//            for (var v : variables)
//                if (n.equals(v.nome))
//                    return v.tipo;
//            return TipoLA.INVALIDO;
//        }
//
//    }
//
//    private final HashMap<String, Symble> tabela;
//    private static ArrayList<String> customTypes;
//    private Integer registerCounter = 0;
//
//    public SymbleTable() {
//        tabela = new HashMap<>();
//        customTypes = new ArrayList<>();
//    }
//
//    // Register Utils
//
//    // Add a symbol to the table
//    public void addSymbol(String nome, TipoLA tipo) {
//        tabela.put(nome, new Symble(nome, tipo));
//    }
//
//    // Add a symbol to the table
//    public void addSymbol(String nome, TipoLA tipo, TipoLA subtipo) {
//        tabela.put(nome, new Symble(nome, tipo, subtipo));
//    }
//
//    // register a new type
//    public void registerType(String nome, TipoLA tipo) {
//        customTypes.add(nome);
//        tabela.put(nome, new Symble(nome, tipo, true));
//
//    }
//
//    // Check if a type was created by the user
//    public static boolean isCustomType(String t) {
//        return customTypes.contains(t);
//    }
//
//    // This should create another variable from a custom type
//    public void instanceType(String nome, String tipo) {
//        Symble obj = tabela.get(tipo);
//        tabela.put(nome, new Symble(nome, TipoLA.REGISTRO));
//
//        for (var v : obj.register.getVariables()) {
//            // DEBUG: print when adding a variable to a register
//            //System.out.println("Registering: "+v.nome+" "+v.tipo);
//            addRegisterVariable(nome, v.nome, v.tipo);
//        }
//        // tabela.put(nome, new Symble(nome, tipo, true));
//
//    }
//
//    // I'll treat a function as an complex register
//    public class Function {
//        private ArrayList<Symble> parameters;
//        private SymbleTable.TipoLA returnValue;
//
//        // TODO: take it off
//        public Function () {
//            this.parameters = new ArrayList<Symble>();
//            this.returnValue = null;
//        }
//
//        // This should be the only cosntructor in the future
//        public Function (SymbleTable.TipoLA t) {
//            this.parameters = new ArrayList<Symble>();
//            this.returnValue = t;
//        }
//
//
//        // Add a parameter to a function
//        public void addParameter(String nome, TipoLA tipo) {
//            Symble var = new Symble(nome, tipo);
//            this.parameters.add(var);
//        }
//
//        // Get function parameters
//        public ArrayList<Symble> getVariables() {
//            return this.parameters;
//        }
//
//        // Check if it contains a parameter
////        public boolean containsKey (String n) {
////            for (var v : parameters) {
////                if (n.equals(v.nome)) {
////                    return true;
////                }
////
////            }
////            return false;
////        }
//
//        // return the type of a function parameter
//        // TODO: still need to check if this works
//        public TipoLA variableType (String n) {
//            for (var v : parameters)
//                if (n.equals(v.nome))
//                    return v.tipo;
//            return TipoLA.INVALIDO;
//        }
//
//    }
//
//    // Check if a symbol exists
//    public boolean existe(String nome) {
//
//        // Check if a symbol is composed
//        if (nome.contains("."))
//            if (tabela.get(nome.substring(0,nome.indexOf("."))) != null)
//                return tabela.get(nome.substring(0,nome.indexOf(".")))
//                    .register.containsKey(nome.substring(nome.indexOf(".")+1));
//            else
//                return false;
//
//        return tabela.containsKey(nome);
//    }
//
//    // verificar check a symble type
//    public TipoLA verificar(String nome) {
//
//        // Check if is a composed variable
//        if (nome.contains("."))
//            return tabela.get(nome.substring(0,nome.indexOf(".")))
//                    .register.variableType(nome.substring(nome.indexOf(".")+1));
//
//        if (tabela.get(nome) ==  null)
//            return TipoLA.INVALIDO;
//        return tabela.get(nome).tipo;
//    }
//
//
//    // Type functions
//
//    // May find a better way of doing it
//    // Remember to check if this language is case sensitive
//    public static boolean typeIsValid(String typename) {
//
//        // DEBUG: check if reached here
//        // System.out.println(customTypes);
//
//        if (typename.startsWith("^")) {
//
//            // Adding pointers support
//            typename = typename.replace("^", "");
//            TipoLA aux = TipoLA.valueOf(typename.toUpperCase());
//            if (aux == TipoLA.INVALIDO)
//                return false;
//            else
//                return true;
//
//        } else if (typename.startsWith("registro") && typename.endsWith("fim_registro")) {
//
//            // Adding support to registers
//            return true;
//        } else if (customTypes.contains(typename)) {
//
//            // Adding support to type declaration
//            return true;
//
//        }
//        try {
//            TipoLA type = TipoLA.valueOf(typename.toUpperCase());
//            return true;
//        } catch (IllegalArgumentException ex) {
//            return false;
//        }
//    }
//
//    // Get the type from a given type string
//    public static TipoLA getType(String typename) {
//
//        // Adding pointer support
//        if (typename.charAt(0) == '^') {
//            typename = typename.replace("^", "");
//            TipoLA aux = TipoLA.valueOf(typename.toUpperCase());
//
//            if (aux == TipoLA.INVALIDO)
//                return TipoLA.INVALIDO;
//            else
//                return TipoLA.PONTEIRO;
//        } else if (typename.startsWith("registro") && typename.endsWith("fim_registro")) {
//
//            // Adding register support
//            return TipoLA.REGISTRO;
//        } else if (customTypes.contains(typename)) {
//
//            // Adding custom types support
//            return TipoLA.REGISTRO;
//        }
////            typename = typename.replace("^", "");
//
//        // preliminar type
//        return TipoLA.valueOf(typename.toUpperCase());
//    }
//
//    public static TipoLA verifyTypeEquality (TipoLA lt, TipoLA rt) {
//        // DEBUG:
//        //System.out.println("Equality: "+lt+" : "+rt);
//        if (lt == rt) {
//            return lt;
//        } else if (lt == TipoLA.INTEIRO && rt == TipoLA.REAL ||
//            lt == TipoLA.REAL && rt == TipoLA.INTEIRO) {
//            return TipoLA.REAL;
//        }
//        return TipoLA.INVALIDO;
//    }
//
//    // Register associated
//
//    // Add a variable to a register
//    public void addRegisterVariable (String nr, String nv, TipoLA t) {
//        Symble s;
//        s = tabela.get(nr);
//
//        s.register.addVariable(nv, t);
//    }
//
//    // Register counter functions
//
//    // Increase the counter by 2
//    public void increseRegisterCounter() {
//        registerCounter += 2;
//    }
//
//    // Decrease the counter by 1
//    public void decreseRegisterCounter() {
//        registerCounter -= 1;
//    }
//
//    // Get counter value
//    public Integer getRegisterCounter() {
//        return registerCounter;
//    }
//
//    // Function type functions
//
//    // Add local parameters
//    // TODO: Check if this is the best way of doing it
//    public void addFunctionParameter (String nf, String np, TipoLA t) {
//        Symble s;
//        s = tabela.get(nf);
//
//        s.func.addParameter(np, t);
//    }
//
//}
