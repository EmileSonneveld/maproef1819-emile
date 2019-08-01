/*     */ package classes.lrg.insider.plugins.properties.membrain;
/*     */ 
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.Set;
/*     */ import lrg.analyses.class_analysis.ClassComputedValue;
/*     */ import lrg.analyses.class_analysis.ClassFacts;
/*     */ import lrg.analyses.class_analysis.IsLocalVariableOrParameterOrField;
/*     */ import lrg.analyses.class_analysis.IsProducedByCall;
/*     */ import lrg.analyses.class_analysis.IsProducedByCastOfCall;
/*     */ import lrg.analyses.class_analysis.IsProducedByCastOfVariable;
/*     */ import lrg.analyses.reaching_definitions.IsDirectCopyOfLValueFrom;
/*     */ import lrg.analyses.reaching_definitions.IsThis;
/*     */ import lrg.analyses.reaching_definitions.ReachingDefinitionsComputedValue;
/*     */ import lrg.analyses.reaching_definitions.ReachingDefinitionsFacts;
/*     */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*     */ import lrg.common.abstractions.entities.ResultEntity;
/*     */ import lrg.common.abstractions.plugins.properties.PropertyComputer;
/*     */ import lrg.insider.plugins.properties.membrain.CallGenerality;
/*     */ import lrg.membrain.dfaCore.ComputedValue;
/*     */ import lrg.membrain.representation.BasicBlock;
/*     */ import lrg.membrain.representation.ControlFlowGraph;
/*     */ import lrg.membrain.representation.instructionSet.Abstractions.Instruction;
/*     */ import lrg.membrain.representation.instructionSet.Abstractions.MCall;
/*     */ import lrg.membrain.representation.instructionSet.Target;
/*     */ import lrg.membrain.translation.UTypeReference;
/*     */ 
/*     */ public class CallGenerality extends PropertyComputer {
/*  29 */   public CallGenerality() { super("call generality", "The call generality", "membrain call", "numeric"); }
/*     */ 
/*     */ 
/*     */   
/*     */   public ResultEntity compute(AbstractEntityInterface anEntity) {
/*  34 */     if (!(anEntity instanceof MCall))
/*     */     {
/*  36 */       return new ResultEntity(-2.0D);
/*     */     }
/*     */     
/*     */     try {
/*  40 */       BasicBlock bb = ((MCall)anEntity).getBasicBlock();
/*     */ 
/*     */       
/*  43 */       ControlFlowGraph cfg = bb.getControlFlowGraph();
/*  44 */       BasicBlock[] list = cfg.getBasicBlocks();
/*  45 */       ReachingDefinitionsFacts rdf = new ReachingDefinitionsFacts(true);
/*  46 */       ClassFacts sca = new ClassFacts();
/*  47 */       cfg.fillFacts(rdf);
/*  48 */       cfg.fillFacts(sca);
/*     */ 
/*     */       
/*  51 */       Target target = null;
/*  52 */       Instruction inst = null; int i;
/*  53 */       label76: for (i = 0; i < list.length; i++) {
/*  54 */         BasicBlock.InstructionsIterator it = list[i].getForwardInstructionsIterator();
/*  55 */         while (it.hasNext()) {
/*  56 */           inst = it.getNext();
/*  57 */           if (inst instanceof Target) {
/*  58 */             target = (Target)inst;
/*     */             do {
/*  60 */               inst = it.getNext();
/*  61 */             } while (inst instanceof lrg.membrain.representation.instructionSet.Param);
/*  62 */             if (inst == anEntity) {
/*     */               break label76;
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*  68 */       if ((inst instanceof lrg.membrain.representation.instructionSet.VirtualCall || inst instanceof lrg.membrain.representation.instructionSet.InterfaceCall) && !target.visitValueArgument(new IsThis()))
/*     */       
/*  70 */       { IsLocalVariableOrParameterOrField q1 = new IsLocalVariableOrParameterOrField();
/*  71 */         IsProducedByCastOfVariable q2 = new IsProducedByCastOfVariable();
/*  72 */         IsProducedByCall q3 = new IsProducedByCall();
/*  73 */         IsProducedByCastOfCall q4 = new IsProducedByCastOfCall();
/*     */         
/*  75 */         if (target.visitValueArgument(q1))
/*     */         
/*  77 */         { ComputedValue[] cv = rdf.getInstructionIn(target);
/*  78 */           Set theTypes = new HashSet();
/*  79 */           for (int j = 0; j < cv.length; j++) {
/*  80 */             if (((ReachingDefinitionsComputedValue)cv[j]).hasLReference(q1.getVariableAddress())) {
/*  81 */               theTypes.addAll(itMayContainInitialValue((ReachingDefinitionsComputedValue)cv[j], rdf));
/*     */             }
/*     */           } 
/*  84 */           if (theTypes.size() != 0) {
/*  85 */             int k = 0;
/*  86 */             Set tmpScaSet = ClassComputedValue.findAll(sca.getInstructionIn(target), q1.getVariableAddress());
/*  87 */             Iterator myIt = tmpScaSet.iterator();
/*  88 */             while (myIt.hasNext()) {
/*  89 */               if (theTypes.contains(((ClassComputedValue)myIt.next()).getAssociatedType())) {
/*  90 */                 k++;
/*     */               }
/*     */             } 
/*  93 */             return new ResultEntity(k * 1.0D / theTypes.size());
/*     */           }  }
/*  95 */         else if (target.visitValueArgument(q2))
/*     */         
/*  97 */         { ComputedValue[] cv = rdf.getInstructionIn(target);
/*  98 */           Set theTypes = new HashSet();
/*  99 */           for (int j = 0; j < cv.length; j++) {
/* 100 */             if (((ReachingDefinitionsComputedValue)cv[j]).hasLReference(q2.getConvertedReference())) {
/* 101 */               theTypes.addAll(itMayContainInitialValue((ReachingDefinitionsComputedValue)cv[j], rdf));
/*     */             }
/*     */           } 
/* 104 */           if (theTypes.size() != 0) {
/* 105 */             int k = 0;
/* 106 */             Set scaSet = ClassComputedValue.findAll(sca.getInstructionIn(target), q2.getConvertedReference());
/* 107 */             Iterator myIt = scaSet.iterator();
/* 108 */             while (myIt.hasNext()) {
/* 109 */               ClassComputedValue tmp = (ClassComputedValue)myIt.next();
/* 110 */               if (tmp.typeCanBeDowncasted(q2.getConversionType()) && theTypes.contains(tmp.getAssociatedType())) {
/* 111 */                 k++;
/*     */               }
/*     */             } 
/* 114 */             return new ResultEntity(k * 1.0D / theTypes.size());
/*     */           }  }
/* 116 */         else { if (target.visitValueArgument(q3))
/*     */           {
/* 118 */             return new ResultEntity(1.0D); } 
/* 119 */           if (target.visitValueArgument(q4)) {
/*     */             
/* 121 */             int k = 0;
/* 122 */             UTypeReference type = q4.getConversionType();
/* 123 */             UTypeReference[] possible = q4.getConvertedType().cone();
/* 124 */             for (int t = 0; t < possible.length; t++) {
/* 125 */               if (type.equals(possible[t]) || type.isSuperTypeFor(possible[t])) {
/* 126 */                 k++;
/*     */               }
/*     */             } 
/* 129 */             return new ResultEntity(k * 1.0D / possible.length);
/*     */           }  }
/*     */          }
/* 132 */       else { return new ResultEntity(-1.0D); }
/*     */     
/* 134 */     } catch (Exception e) {
/* 135 */       return new ResultEntity(-2.0D);
/*     */     } 
/* 137 */     return new ResultEntity(-2.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private Set itMayContainInitialValue(ReachingDefinitionsComputedValue rd, ReachingDefinitionsFacts rdf) {
/* 143 */     Set res = new HashSet();
/* 144 */     IsProducedByCall q1 = new IsProducedByCall();
/* 145 */     IsProducedByCastOfCall q2 = new IsProducedByCastOfCall();
/*     */ 
/*     */     
/* 148 */     if (rd.isImplicitAssignment()) {
/* 149 */       UTypeReference[] t = rd.getType().cone();
/* 150 */       for (int i = 0; i < t.length; i++) {
/* 151 */         res.add(t[i]);
/*     */       }
/* 153 */     } else if (rd.askRValue(q1)) {
/* 154 */       UTypeReference[] t = q1.getReturnedType().cone();
/* 155 */       for (int i = 0; i < t.length; i++) {
/* 156 */         res.add(t[i]);
/*     */       }
/* 158 */     } else if (rd.askRValue(q2)) {
/* 159 */       UTypeReference[] t = q2.getConvertedType().cone();
/* 160 */       for (int i = 0; i < t.length; i++) {
/* 161 */         res.add(t[i]);
/*     */       }
/*     */     } else {
/* 164 */       ComputedValue[] tmpRes = rd.getDefinitionInputs(rdf);
/* 165 */       for (int i = 0; i < tmpRes.length; i++) {
/* 166 */         ReachingDefinitionsComputedValue tmp = (ReachingDefinitionsComputedValue)tmpRes[i];
/* 167 */         if (rd.askRValue(new IsDirectCopyOfLValueFrom(tmp))) {
/* 168 */           res.addAll(itMayContainInitialValue(tmp, rdf));
/*     */         }
/*     */       } 
/*     */     } 
/* 172 */     return res;
/*     */   }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\properties\membrain\CallGenerality.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */