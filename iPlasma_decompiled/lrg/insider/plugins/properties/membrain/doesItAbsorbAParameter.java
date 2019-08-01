/*     */ package classes.lrg.insider.plugins.properties.membrain;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import lrg.analyses.questions.IsParameterAddress;
/*     */ import lrg.analyses.reaching_definitions.IsDirectCopyOfLValueFrom;
/*     */ import lrg.analyses.reaching_definitions.IsFieldAddressOfThis;
/*     */ import lrg.analyses.reaching_definitions.ReachingDefinitionsComputedValue;
/*     */ import lrg.analyses.reaching_definitions.ReachingDefinitionsFacts;
/*     */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*     */ import lrg.common.abstractions.entities.ResultEntity;
/*     */ import lrg.common.abstractions.plugins.properties.PropertyComputer;
/*     */ import lrg.insider.plugins.properties.membrain.doesItAbsorbAParameter;
/*     */ import lrg.membrain.dfaCore.ComputedValue;
/*     */ import lrg.membrain.representation.BasicBlock;
/*     */ import lrg.membrain.representation.ControlFlowGraph;
/*     */ import lrg.memoria.core.Method;
/*     */ import lrg.memoria.core.XFunctionBody;
/*     */ 
/*     */ public class doesItAbsorbAParameter extends PropertyComputer {
/*  20 */   public doesItAbsorbAParameter() { super("isParameterAbsorber", "Is there an invariant field = initial_value_of_a_parameter?", "method", "numerical"); }
/*     */ 
/*     */ 
/*     */   
/*     */   public ResultEntity compute(AbstractEntityInterface anEntity) {
/*  25 */     if (!(anEntity instanceof Method))
/*     */     {
/*  27 */       return new ResultEntity(-2.0D);
/*     */     }
/*     */ 
/*     */     
/*     */     try {
/*  32 */       ArrayList fieldRd = new ArrayList();
/*     */       
/*  34 */       Method theMethod = (Method)anEntity;
/*  35 */       if (theMethod.isAbstract())
/*     */       {
/*  37 */         return new ResultEntity(-1.0D);
/*     */       }
/*     */ 
/*     */       
/*  41 */       ControlFlowGraph cfg = ((XFunctionBody)theMethod.getBody()).getControlFlowGraph();
/*  42 */       BasicBlock[] list = cfg.getBasicBlocks();
/*  43 */       ReachingDefinitionsFacts rdf = new ReachingDefinitionsFacts(true);
/*  44 */       cfg.fillFacts(rdf);
/*  45 */       ComputedValue[] res = rdf.getBasicBlockIn(list[list.length - 1]);
/*     */ 
/*     */       
/*  48 */       for (int i = 0; i < res.length; i++) {
/*  49 */         ReachingDefinitionsComputedValue rd = (ReachingDefinitionsComputedValue)res[i];
/*  50 */         if (rd.askLValue(new IsFieldAddressOfThis())) {
/*  51 */           boolean found = false;
/*  52 */           for (int j = 0; j < fieldRd.size(); j++) {
/*  53 */             if (((ReachingDefinitionsComputedValue)((ArrayList)fieldRd.get(j)).get(0)).sameLValue(rd)) {
/*  54 */               found = true;
/*  55 */               ((ArrayList)fieldRd.get(j)).add(rd);
/*     */               break;
/*     */             } 
/*     */           } 
/*  59 */           if (!found) {
/*  60 */             ArrayList tmp = new ArrayList();
/*  61 */             tmp.add(rd);
/*  62 */             fieldRd.add(tmp);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/*  68 */       for (int i = 0; i < fieldRd.size(); i++) {
/*     */         
/*  70 */         boolean result = true;
/*     */ 
/*     */         
/*  73 */         ArrayList theFRD = (ArrayList)fieldRd.get(i);
/*  74 */         ReachingDefinitionsComputedValue rd = (ReachingDefinitionsComputedValue)theFRD.get(0);
/*     */         ReachingDefinitionsComputedValue initial;
/*  76 */         if ((initial = inDepth(rdf, rd)) != null) {
/*  77 */           for (int j = 1; j < theFRD.size(); j++) {
/*  78 */             rd = (ReachingDefinitionsComputedValue)theFRD.get(j);
/*  79 */             if (initial != inDepth(rdf, rd)) {
/*  80 */               result = false;
/*     */               break;
/*     */             } 
/*     */           } 
/*  84 */           if (result) {
/*  85 */             return new ResultEntity(1.0D);
/*     */           }
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/*  91 */       return new ResultEntity(0.0D);
/*     */     }
/*  93 */     catch (Exception e) {
/*     */       
/*  95 */       return new ResultEntity(-2.0D);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private ReachingDefinitionsComputedValue inDepth(ReachingDefinitionsFacts rdf, ReachingDefinitionsComputedValue rd) {
/* 102 */     if (rd.isImplicitAssignment() && rd.askLValue(new IsParameterAddress())) {
/* 103 */       return rd;
/*     */     }
/*     */     
/* 106 */     ComputedValue[] res = rd.getDefinitionInputs(rdf);
/* 107 */     ReachingDefinitionsComputedValue init = null;
/*     */     
/* 109 */     for (int i = 0; i < res.length; i++) {
/* 110 */       ReachingDefinitionsComputedValue tmp = (ReachingDefinitionsComputedValue)res[i];
/* 111 */       if (rd.askRValue(new IsDirectCopyOfLValueFrom(tmp))) {
/* 112 */         if (init == null) {
/* 113 */           if ((init = inDepth(rdf, tmp)) == null) {
/* 114 */             return null;
/*     */           }
/* 116 */         } else if (init != inDepth(rdf, tmp)) {
/* 117 */           return null;
/*     */         } 
/*     */       }
/*     */     } 
/*     */     
/* 122 */     return init;
/*     */   }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\properties\membrain\doesItAbsorbAParameter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */