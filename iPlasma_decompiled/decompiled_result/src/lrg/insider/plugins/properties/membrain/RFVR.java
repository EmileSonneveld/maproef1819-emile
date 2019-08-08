/*    */ package classes.lrg.insider.plugins.properties.membrain;
/*    */ 
/*    */ import lrg.analyses.questions.IsReturnVariableAddress;
/*    */ import lrg.analyses.reaching_definitions.IsDirectCopyOfLValueFrom;
/*    */ import lrg.analyses.reaching_definitions.IsFieldOfThisValue;
/*    */ import lrg.analyses.reaching_definitions.ReachingDefinitionsComputedValue;
/*    */ import lrg.analyses.reaching_definitions.ReachingDefinitionsFacts;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.common.abstractions.plugins.properties.PropertyComputer;
/*    */ import lrg.insider.plugins.properties.membrain.RFVR;
/*    */ import lrg.membrain.dfaCore.ComputedValue;
/*    */ import lrg.membrain.representation.BasicBlock;
/*    */ import lrg.membrain.representation.ControlFlowGraph;
/*    */ import lrg.memoria.core.Method;
/*    */ import lrg.memoria.core.XFunctionBody;
/*    */ 
/*    */ class RFVR extends PropertyComputer {
/* 19 */   public RFVR() { super("RFVR", "Returned Field Value Ratio", "method", "numerical"); }
/*    */ 
/*    */ 
/*    */   
/*    */   public ResultEntity compute(AbstractEntityInterface anEntity) {
/* 24 */     if (!(anEntity instanceof Method))
/*    */     {
/* 26 */       return new ResultEntity(-2.0D);
/*    */     }
/*    */ 
/*    */     
/*    */     try {
/* 31 */       double allRDofReturneVariable = 0.0D;
/* 32 */       double RDofReturneVariableWhichAssignAFieldValue = 0.0D;
/*    */       
/* 34 */       Method theMethod = (Method)anEntity;
/* 35 */       if (theMethod.isAbstract() || 
/* 36 */         theMethod.getReturnType() == null || 
/* 37 */         theMethod.getReturnType().getName().equals("void"))
/*    */       {
/* 39 */         return new ResultEntity(-1.0D);
/*    */       }
/*    */ 
/*    */       
/* 43 */       ControlFlowGraph cfg = ((XFunctionBody)theMethod.getBody()).getControlFlowGraph();
/* 44 */       BasicBlock[] list = cfg.getBasicBlocks();
/* 45 */       ReachingDefinitionsFacts rd = new ReachingDefinitionsFacts(false);
/* 46 */       cfg.fillFacts(rd);
/*    */ 
/*    */       
/* 49 */       ComputedValue[] res = rd.getBasicBlockIn(list[list.length - 1]);
/* 50 */       for (int i = 0; i < res.length; i++) {
/* 51 */         if (((ReachingDefinitionsComputedValue)res[i]).askLValue(new IsReturnVariableAddress())) {
/* 52 */           if (((ReachingDefinitionsComputedValue)res[i]).askRValue(new IsFieldOfThisValue())) {
/*    */             
/* 54 */             allRDofReturneVariable++;
/* 55 */             RDofReturneVariableWhichAssignAFieldValue++;
/*    */           } else {
/*    */             
/* 58 */             int[] tab = inDepth(rd, (ReachingDefinitionsComputedValue)res[i]);
/* 59 */             RDofReturneVariableWhichAssignAFieldValue += tab[0];
/* 60 */             allRDofReturneVariable += tab[1];
/*    */           } 
/*    */         }
/*    */       } 
/*    */       
/* 65 */       return new ResultEntity(RDofReturneVariableWhichAssignAFieldValue / allRDofReturneVariable);
/*    */     }
/* 67 */     catch (Exception e) {
/*    */       
/* 69 */       return new ResultEntity(-2.0D);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   private int[] inDepth(ReachingDefinitionsFacts rd, ReachingDefinitionsComputedValue c) {
/* 75 */     ComputedValue[] res = c.getDefinitionInputs(rd);
/* 76 */     int[] result = new int[2];
/*    */     
/* 78 */     result[1] = 0; result[0] = 0;
/*    */     
/* 80 */     for (int i = 0; i < res.length; i++) {
/* 81 */       ReachingDefinitionsComputedValue tmp = (ReachingDefinitionsComputedValue)res[i];
/* 82 */       if (c.askRValue(new IsDirectCopyOfLValueFrom(tmp))) {
/* 83 */         if (tmp.askRValue(new IsFieldOfThisValue())) {
/* 84 */           result[0] = result[0] + 1;
/* 85 */           result[1] = result[1] + 1;
/*    */         } else {
/* 87 */           int[] rec = inDepth(rd, tmp);
/* 88 */           result[0] = result[0] + rec[0];
/* 89 */           result[1] = result[1] + rec[1];
/*    */         } 
/*    */       }
/*    */     } 
/*    */     
/* 94 */     if (result[0] == 0) {
/* 95 */       result[1] = 1;
/*    */     }
/* 97 */     return result;
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\properties\membrain\RFVR.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */