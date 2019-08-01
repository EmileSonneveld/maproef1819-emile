/*     */ package classes.lrg.insider.plugins.properties.membrain;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import lrg.analyses.questions.IsParameterValue;
/*     */ import lrg.analyses.reaching_definitions.IsFieldAddressOfThis;
/*     */ import lrg.analyses.reaching_definitions.ReachingDefinitionsComputedValue;
/*     */ import lrg.analyses.reaching_definitions.ReachingDefinitionsFacts;
/*     */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*     */ import lrg.common.abstractions.entities.ResultEntity;
/*     */ import lrg.common.abstractions.plugins.properties.PropertyComputer;
/*     */ import lrg.membrain.dfaCore.ComputedValue;
/*     */ import lrg.membrain.representation.BasicBlock;
/*     */ import lrg.membrain.representation.ControlFlowGraph;
/*     */ import lrg.memoria.core.Method;
/*     */ import lrg.memoria.core.XFunctionBody;
/*     */ 
/*     */ 
/*     */ public class RPF
/*     */   extends PropertyComputer
/*     */ {
/*  23 */   public RPF() { super("RPF", "Parameters Responsabilty for Fields", "method", "numerical"); }
/*     */ 
/*     */ 
/*     */   
/*     */   public ResultEntity compute(AbstractEntityInterface anEntity) {
/*  28 */     if (!(anEntity instanceof Method)) {
/*  29 */       return new ResultEntity(-2.0D);
/*     */     }
/*     */ 
/*     */     
/*     */     try {
/*  34 */       Method theMethod = (Method)anEntity;
/*  35 */       if (theMethod.isAbstract()) {
/*  36 */         return new ResultEntity(-1.0D);
/*     */       }
/*     */ 
/*     */       
/*  40 */       ControlFlowGraph cfg = ((XFunctionBody)theMethod.getBody()).getControlFlowGraph();
/*  41 */       BasicBlock[] list = cfg.getBasicBlocks();
/*  42 */       ReachingDefinitionsFacts rdf = new ReachingDefinitionsFacts(false);
/*  43 */       cfg.fillFacts(rdf);
/*  44 */       ComputedValue[] res = rdf.getBasicBlockIn(list[list.length - 1]);
/*     */ 
/*     */       
/*  47 */       ArrayList parList = new ArrayList();
/*  48 */       ArrayList parToField = new ArrayList();
/*  49 */       ArrayList fieldList = new ArrayList();
/*  50 */       ArrayList fieldInParm = new ArrayList();
/*  51 */       ArrayList fieldIn = new ArrayList();
/*     */       int i;
/*  53 */       for (i = 0; i < res.length; i++) {
/*  54 */         ReachingDefinitionsComputedValue rd = (ReachingDefinitionsComputedValue)res[i];
/*  55 */         if (rd.askLValue(new IsFieldAddressOfThis())) {
/*  56 */           boolean found = false; int j;
/*  57 */           for (j = 0; j < fieldList.size(); j++) {
/*  58 */             if (((ReachingDefinitionsComputedValue)fieldList.get(j)).sameLValue(rd)) {
/*  59 */               found = true;
/*     */               break;
/*     */             } 
/*     */           } 
/*  63 */           if (!found) {
/*  64 */             fieldList.add(rd);
/*  65 */             fieldIn.add(Integer.valueOf(0));
/*  66 */             fieldInParm.add(new ArrayList());
/*     */           } 
/*  68 */           fieldIn.set(j, Integer.valueOf(((Integer)fieldIn.get(j)).intValue() + 1));
/*  69 */           if (rd.askRValue(new IsParameterValue())) {
/*  70 */             int fieldId = j;
/*  71 */             found = false;
/*  72 */             for (j = 0; j < parList.size(); j++) {
/*  73 */               if (((ReachingDefinitionsComputedValue)parList.get(j)).sameRValue(rd)) {
/*  74 */                 found = true;
/*     */                 break;
/*     */               } 
/*     */             } 
/*  78 */             if (!found) {
/*  79 */               parList.add(rd);
/*  80 */               parToField.add(new HashSet());
/*     */             } 
/*  82 */             ((ArrayList)fieldInParm.get(fieldId)).add(Integer.valueOf(j));
/*  83 */             ((HashSet)parToField.get(j)).add(Integer.valueOf(fieldId));
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/*  88 */       if (parList.size() == 0) {
/*  89 */         return new ResultEntity(0.0D);
/*     */       }
/*     */ 
/*     */       
/*  93 */       double M = 0.0D;
/*  94 */       for (i = 0; i < parList.size(); i++) {
/*  95 */         double m = 0.0D, disp = 0.0D;
/*  96 */         Iterator it = ((HashSet)parToField.get(i)).iterator();
/*  97 */         while (it.hasNext()) {
/*  98 */           Integer tmp = (Integer)it.next();
/*  99 */           double resp = 0.0D;
/* 100 */           for (int j = 0; j < ((ArrayList)fieldInParm.get(tmp.intValue())).size(); j++) {
/* 101 */             if (((Integer)((ArrayList)fieldInParm.get(tmp.intValue())).get(j)).intValue() == i) {
/* 102 */               resp++;
/*     */             }
/*     */           } 
/* 105 */           m += resp / ((Integer)fieldIn.get(tmp.intValue())).intValue();
/* 106 */           disp++;
/*     */         } 
/* 108 */         M += m / disp;
/*     */       } 
/*     */       
/* 111 */       return new ResultEntity(M / parList.size());
/*     */     }
/* 113 */     catch (Exception e) {
/* 114 */       return new ResultEntity(-2.0D);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\properties\membrain\RPF.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */