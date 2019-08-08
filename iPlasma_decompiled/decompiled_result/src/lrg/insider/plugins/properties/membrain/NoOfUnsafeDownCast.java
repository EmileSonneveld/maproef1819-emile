/*    */ package classes.lrg.insider.plugins.properties.membrain;
/*    */ import lrg.analyses.class_analysis.ClassComputedValue;
/*    */ import lrg.analyses.class_analysis.ClassFacts;
/*    */ import lrg.analyses.class_analysis.IsLocalVariableOrParameterOrField;
/*    */ import lrg.analyses.class_analysis.IsProducedByNewObject;
/*    */ import lrg.analyses.questions.IsComputableReference;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.common.abstractions.plugins.properties.PropertyComputer;
/*    */ import lrg.membrain.dfaCore.ComputedValue;
/*    */ import lrg.membrain.representation.BasicBlock;
/*    */ import lrg.membrain.representation.ControlFlowGraph;
/*    */ import lrg.membrain.representation.instructionSet.Abstractions.Instruction;
/*    */ import lrg.membrain.representation.instructionSet.Cast;
/*    */ import lrg.membrain.translation.UTypeReference;
/*    */ import lrg.memoria.core.Method;
/*    */ import lrg.memoria.core.XFunctionBody;
/*    */ 
/*    */ public class NoOfUnsafeDownCast extends PropertyComputer {
/* 20 */   public NoOfUnsafeDownCast() { super("NUD", "Number of Unsafe Downcasts", "method", "numerical"); }
/*    */ 
/*    */ 
/*    */   
/*    */   public ResultEntity compute(AbstractEntityInterface anEntity) {
/* 25 */     if (!(anEntity instanceof Method))
/*    */     {
/* 27 */       return new ResultEntity(-2.0D);
/*    */     }
/*    */     
/*    */     try {
/* 31 */       Method theMethod = (Method)anEntity;
/* 32 */       if (theMethod.isAbstract())
/*    */       {
/* 34 */         return new ResultEntity(-1.0D);
/*    */       }
/*    */ 
/*    */       
/* 38 */       ControlFlowGraph cfg = ((XFunctionBody)theMethod.getBody()).getControlFlowGraph();
/* 39 */       BasicBlock[] bList = cfg.getBasicBlocks();
/* 40 */       ClassFacts ca = new ClassFacts();
/* 41 */       cfg.fillFacts(ca);
/*    */ 
/*    */       
/* 44 */       int result = 0;
/* 45 */       for (int i = 0; i < bList.length; i++) {
/* 46 */         BasicBlock.InstructionsIterator it = bList[i].getForwardInstructionsIterator();
/* 47 */         while (it.hasNext()) {
/* 48 */           Instruction inst = it.getNext();
/* 49 */           if (inst instanceof Cast) {
/* 50 */             Cast castInst = (Cast)inst;
/* 51 */             UTypeReference type = castInst.getProducedUTypeReference();
/* 52 */             if (type.isArrayType() || type.isPrimitiveType())
/* 53 */               continue;  IsLocalVariableOrParameterOrField q1 = new IsLocalVariableOrParameterOrField();
/* 54 */             IsProducedByNewObject q2 = new IsProducedByNewObject();
/* 55 */             if (castInst.visitValueArgument(q1)) {
/* 56 */               ComputedValue[] compV = ca.getInstructionIn(castInst);
/* 57 */               for (int j = 0; j < compV.length; j++) {
/* 58 */                 if (((ClassComputedValue)compV[j]).referTheSameVariable(q1.getVariableAddress()) && 
/* 59 */                   !((ClassComputedValue)compV[j]).typeCanBeDowncasted(type)) {
/* 60 */                   result++; break;
/*    */                 } 
/*    */               }  continue;
/*    */             } 
/* 64 */             if (castInst.visitValueArgument(q2)) {
/* 65 */               if (!type.equals(q2.getInstanciatedType()) && !type.isSuperTypeFor(q2.getInstanciatedType()))
/* 66 */                 result++; 
/*    */               continue;
/*    */             } 
/* 69 */             IsComputableReference convertedType = new IsComputableReference();
/* 70 */             castInst.visitValueArgument(convertedType);
/* 71 */             if (!type.equals(convertedType.getType()) && !type.isSuperTypeFor(convertedType.getType())) {
/* 72 */               result++;
/*    */             }
/*    */           } 
/*    */         } 
/*    */       } 
/*    */ 
/*    */       
/* 79 */       return new ResultEntity(result);
/*    */     }
/* 81 */     catch (Exception e) {
/*    */       
/* 83 */       return new ResultEntity(-2.0D);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\properties\membrain\NoOfUnsafeDownCast.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */