/*     */ package lrg.memoria.importer.cdif;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public interface CDIFParserConstants
/*     */ {
/*     */   public static final int EOF = 0;
/*     */   public static final int INTEGER_NUMBER = 5;
/*     */   public static final int STRING = 6;
/*     */   public static final int UNIQUE_NAME = 7;
/*     */   public static final int INNER_CLASS_NAME = 8;
/*     */   public static final int DATE = 9;
/*     */   public static final int DEFAULT = 0;
/*     */   public static final String[] tokenImage = { 
/*  16 */       "<EOF>", 
/*  17 */       "\"\\\"\"", 
/*  18 */       "\"\\t\"", 
/*  19 */       "\" \"", 
/*  20 */       "\"\\n\"", 
/*  21 */       "<INTEGER_NUMBER>", 
/*  22 */       "<STRING>", 
/*  23 */       "<UNIQUE_NAME>", 
/*  24 */       "<INNER_CLASS_NAME>", 
/*  25 */       "<DATE>", 
/*  26 */       "\"(:MODEL\"", 
/*  27 */       "\")\"", 
/*  28 */       "\"02.00.00\"", 
/*  29 */       "\",\"", 
/*  30 */       "\"01.05.04\"", 
/*  31 */       "\"(:HEADER\"", 
/*  32 */       "\"(:SUMMARY\"", 
/*  33 */       "\"(ExporterName\"", 
/*  34 */       "\"(ExporterVersion\"", 
/*  35 */       "\"1.00\"", 
/*  36 */       "\"(ExporterDate\"", 
/*  37 */       "\"(ExporterTime\"", 
/*  38 */       "\"(PublisherName\"", 
/*  39 */       "\"(:META-MODEL\"", 
/*  40 */       "\"(:SUBJECTAREAREFERENCE\"", 
/*  41 */       "\"(:VERSIONNUMBER\"", 
/*  42 */       "\"01.00\"", 
/*  43 */       "\"1.0\"", 
/*  44 */       "\"(Package\"", 
/*  45 */       "\"(getName\"", 
/*  46 */       "\"(statute\"", 
/*  47 */       "\"(Namespace\"", 
/*  48 */       "\"(PrimitiveType\"", 
/*  49 */       "\"(uniqueName\"", 
/*  50 */       "\"(belongsToNamespace\"", 
/*  51 */       "\"(Class\"", 
/*  52 */       "\"(belongsToPackage\"", 
/*  53 */       "\"(belongsToClass\"", 
/*  54 */       "\"(belongsToBody\"", 
/*  55 */       "\"(file_name\"", 
/*  56 */       "\"(start_line\"", 
/*  57 */       "\"(start_char\"", 
/*  58 */       "\"(end_line\"", 
/*  59 */       "\"(end_char\"", 
/*  60 */       "\"(isAbstract\"", 
/*  61 */       "\"(isFinal\"", 
/*  62 */       "\"(isStatic\"", 
/*  63 */       "\"(isInterface\"", 
/*  64 */       "\"(access_mode\"", 
/*  65 */       "\"(ArrayDecorator\"", 
/*  66 */       "\"(decoratedType\"", 
/*  67 */       "\"(InheritanceDefinition\"", 
/*  68 */       "\"(subclass\"", 
/*  69 */       "\"(superclass\"", 
/*  70 */       "\"(ImplementsDefinition\"", 
/*  71 */       "\"(interface\"", 
/*  72 */       "\"(InitializerBody\"", 
/*  73 */       "\"(belongsTo\"", 
/*  74 */       "\"(LOC\"", 
/*  75 */       "\"(CYCLO\"", 
/*  76 */       "\"(NOS\"", 
/*  77 */       "\"(NODec\"", 
/*  78 */       "\"(NOCmt\"", 
/*  79 */       "\"(NOExc\"", 
/*  80 */       "\"(NOExits\"", 
/*  81 */       "\"(NOL\"", 
/*  82 */       "\"(Attribute\"", 
/*  83 */       "\"(type\"", 
/*  84 */       "\"(Method\"", 
/*  85 */       "\"(returnType\"", 
/*  86 */       "\"(kindOf\"", 
/*  87 */       "\"(ThrowsException\"", 
/*  88 */       "\"(method\"", 
/*  89 */       "\"(exception_name\"", 
/*  90 */       "\"(FunctionBody\"", 
/*  91 */       "\"(FormalParameter\"", 
/*  92 */       "\"(isExParam\"", 
/*  93 */       "\"(LocalVariable\"", 
/*  94 */       "\"(isBlock\"", 
/*  95 */       "\"(Access\"", 
/*  96 */       "\"(accessedIn\"", 
/*  97 */       "\"(accesses_number\"", 
/*  98 */       "\"(Call\"", 
/*  99 */       "\"(invokedIn\"", 
/* 100 */       "\"(invocations_number\"" };
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\importer\cdif\CDIFParserConstants.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */