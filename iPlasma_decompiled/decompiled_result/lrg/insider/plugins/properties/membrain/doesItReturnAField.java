/*    */ package classes.lrg.insider.plugins.properties.membrain;
/*    */ import lrg.analyses.questions.IsReturnVariableAddress;
/*    */ import lrg.analyses.reaching_definitions.IsDirectCopyOfLValueFrom;
/*    */ import lrg.analyses.reaching_definitions.IsFieldOfThisValue;
/*    */ import lrg.analyses.reaching_definitions.ReachingDefinitionsComputedValue;
/*    */ import lrg.analyses.reaching_definitions.ReachingDefinitionsFacts;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.common.abstractions.plugins.properties.PropertyComputer;
/*    */ import lrg.insider.plugins.properties.membrain.doesItReturnAField;
/*    */ import lrg.membrain.dfaCore.ComputedValue;
/*    */ import lrg.membrain.representation.BasicBlock;
/*    */ import lrg.membrain.representation.ControlFlowGraph;
/*    */ import lrg.memoria.core.Method;
/*    */ import lrg.memoria.core.XFunctionBody;
/*    */ 
/*    */ public class doesItReturnAField extends PropertyComputer {
/* 18 */   public doesItReturnAField() { super("isFieldExposer", "Is there a field returned as return value ?", "method", "numerical"); }
/*    */ 
/*    */ 
/*    */   
/*    */   public ResultEntity compute(AbstractEntityInterface anEntity) {
/* 23 */     if (!(anEntity instanceof Method))
/*    */     {
/* 25 */       return new ResultEntity(-2.0D);
/*    */     }
/*    */ 
/*    */     
/*    */     try {
/* 30 */       Method theMethod = (Method)anEntity;
/* 31 */       if (theMethod.isAbstract() || 
/* 32 */         theMethod.getReturnType() == null || 
/* 33 */         theMethod.getReturnType().getName().equals("void"))
/*    */       {
/* 35 */         return new ResultEntity(-1.0D);
/*    */       }
/*    */ 
/*    */       
/* 39 */       ControlFlowGraph cfg = ((XFunctionBody)theMethod.getBody()).getControlFlowGraph();
/* 40 */       BasicBlock[] list = cfg.getBasicBlocks();
/* 41 */       ReachingDefinitionsFacts rd = new ReachingDefinitionsFacts(false);
/* 42 */       cfg.fillFacts(rd);
/*    */ 
/*    */       
/* 45 */       int result = 0;
/* 46 */       ComputedValue[] res = rd.getBasicBlockIn(list[list.length - 1]);
/* 47 */       for (int i = 0; i < res.length; i++) {
/* 48 */         if (((ReachingDefinitionsComputedValue)res[i]).askLValue(new IsReturnVariableAddress())) {
/* 49 */           if (((ReachingDefinitionsComputedValue)res[i]).askRValue(new IsFieldOfThisValue())) {
/* 50 */             result = 1; break;
/*    */           } 
/* 52 */           if (inDepth(rd, (ReachingDefinitionsComputedValue)res[i])) {
/* 53 */             result = 1;
/*    */             
/*    */             break;
/*    */           } 
/*    */         } 
/*    */       } 
/* 59 */       return new ResultEntity(result);
/*    */     }
/* 61 */     catch (Exception e) {
/*    */       
/* 63 */       return new ResultEntity(-2.0D);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   private boolean inDepth(ReachingDefinitionsFacts rd, ReachingDefinitionsComputedValue c) {
/* 70 */     ComputedValue[] res = c.getDefinitionInputs(rd);
/* 71 */     boolean result = false;
/*    */ 
/*    */     
/* 74 */     for (int i = 0; i < res.length; i++) {
/* 75 */       ReachingDefinitionsComputedValue tmp = (ReachingDefinitionsComputedValue)res[i];
/* 76 */       if (c.askRValue(new IsDirectCopyOfLValueFrom(tmp))) {
/* 77 */         if (tmp.askRValue(new IsFieldOfThisValue())) {
/* 78 */           result = true; break;
/*    */         } 
/* 80 */         if (inDepth(rd, tmp)) {
/* 81 */           result = true;
/*    */           
/*    */           break;
/*    */         } 
/*    */       } 
/*    */     } 
/* 87 */     return result;
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\properties\membrain\doesItReturnAField.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */