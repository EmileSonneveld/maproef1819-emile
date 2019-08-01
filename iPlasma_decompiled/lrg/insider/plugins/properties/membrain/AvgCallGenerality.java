/*     */ package classes.lrg.insider.plugins.properties.membrain;
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
/*     */ import lrg.analyses.reaching_definitions.ReachingDefinitionsComputedValue;
/*     */ import lrg.analyses.reaching_definitions.ReachingDefinitionsFacts;
/*     */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*     */ import lrg.common.abstractions.entities.ResultEntity;
/*     */ import lrg.common.abstractions.plugins.properties.PropertyComputer;
/*     */ import lrg.insider.plugins.properties.membrain.AvgCallGenerality;
/*     */ import lrg.membrain.dfaCore.ComputedValue;
/*     */ import lrg.membrain.representation.BasicBlock;
/*     */ import lrg.membrain.representation.ControlFlowGraph;
/*     */ import lrg.membrain.representation.instructionSet.Abstractions.Instruction;
/*     */ import lrg.membrain.representation.instructionSet.Target;
/*     */ import lrg.membrain.translation.UTypeReference;
/*     */ import lrg.memoria.core.Method;
/*     */ 
/*     */ public class AvgCallGenerality extends PropertyComputer {
/*  27 */   public AvgCallGenerality() { super("ACG", "Average call Generality", "method", "numerical"); }
/*     */ 
/*     */ 
/*     */   
/*     */   public ResultEntity compute(AbstractEntityInterface anEntity) {
/*  32 */     if (!(anEntity instanceof Method))
/*     */     {
/*  34 */       return new ResultEntity(-2.0D);
/*     */     }
/*     */ 
/*     */     
/*     */     try {
/*  39 */       Method theMethod = (Method)anEntity;
/*  40 */       if (theMethod.isAbstract())
/*     */       {
/*  42 */         return new ResultEntity(-1.0D);
/*     */       }
/*     */ 
/*     */       
/*  46 */       ControlFlowGraph cfg = ((XFunctionBody)theMethod.getBody()).getControlFlowGraph();
/*  47 */       BasicBlock[] list = cfg.getBasicBlocks();
/*  48 */       ReachingDefinitionsFacts rdf = new ReachingDefinitionsFacts(true);
/*  49 */       ClassFacts sca = new ClassFacts();
/*  50 */       cfg.fillFacts(rdf);
/*  51 */       cfg.fillFacts(sca);
/*     */ 
/*     */       
/*  54 */       double result = 0.0D;
/*  55 */       double count = 0.0D;
/*  56 */       for (int i = 0; i < list.length; i++) {
/*  57 */         BasicBlock.InstructionsIterator it = list[i].getForwardInstructionsIterator();
/*  58 */         while (it.hasNext()) {
/*  59 */           Instruction inst = it.getNext();
/*     */           
/*  61 */           if (inst instanceof Target) {
/*  62 */             Target target = (Target)inst;
/*     */             do {
/*  64 */               inst = it.getNext();
/*  65 */             } while (inst instanceof lrg.membrain.representation.instructionSet.Param);
/*  66 */             if ((inst instanceof lrg.membrain.representation.instructionSet.VirtualCall || inst instanceof lrg.membrain.representation.instructionSet.InterfaceCall) && !target.visitValueArgument(new IsThis())) {
/*     */ 
/*     */               
/*  69 */               IsLocalVariableOrParameterOrField q1 = new IsLocalVariableOrParameterOrField();
/*  70 */               IsProducedByCastOfVariable q2 = new IsProducedByCastOfVariable();
/*  71 */               IsProducedByCall q3 = new IsProducedByCall();
/*  72 */               IsProducedByCastOfCall q4 = new IsProducedByCastOfCall();
/*     */               
/*  74 */               if (target.visitValueArgument(q1)) {
/*     */                 
/*  76 */                 ComputedValue[] cv = rdf.getInstructionIn(target);
/*  77 */                 Set theTypes = new HashSet();
/*  78 */                 for (int j = 0; j < cv.length; j++) {
/*  79 */                   if (((ReachingDefinitionsComputedValue)cv[j]).hasLReference(q1.getVariableAddress())) {
/*  80 */                     theTypes.addAll(itMayContainInitialValue((ReachingDefinitionsComputedValue)cv[j], rdf));
/*     */                   }
/*     */                 } 
/*  83 */                 if (theTypes.size() != 0) {
/*  84 */                   count++;
/*  85 */                   int k = 0;
/*  86 */                   Set tmpScaSet = ClassComputedValue.findAll(sca.getInstructionIn(target), q1.getVariableAddress());
/*  87 */                   Iterator myIt = tmpScaSet.iterator();
/*  88 */                   while (myIt.hasNext()) {
/*  89 */                     if (theTypes.contains(((ClassComputedValue)myIt.next()).getAssociatedType())) {
/*  90 */                       k++;
/*     */                     }
/*     */                   } 
/*  93 */                   result += k * 1.0D / theTypes.size();
/*     */                 }  continue;
/*  95 */               }  if (target.visitValueArgument(q2)) {
/*     */                 
/*  97 */                 ComputedValue[] cv = rdf.getInstructionIn(target);
/*  98 */                 Set theTypes = new HashSet();
/*  99 */                 for (int j = 0; j < cv.length; j++) {
/* 100 */                   if (((ReachingDefinitionsComputedValue)cv[j]).hasLReference(q2.getConvertedReference())) {
/* 101 */                     theTypes.addAll(itMayContainInitialValue((ReachingDefinitionsComputedValue)cv[j], rdf));
/*     */                   }
/*     */                 } 
/* 104 */                 if (theTypes.size() != 0) {
/* 105 */                   int k = 0;
/* 106 */                   Set scaSet = ClassComputedValue.findAll(sca.getInstructionIn(target), q2.getConvertedReference());
/* 107 */                   Iterator myIt = scaSet.iterator();
/* 108 */                   while (myIt.hasNext()) {
/* 109 */                     ClassComputedValue tmp = (ClassComputedValue)myIt.next();
/* 110 */                     if (tmp.typeCanBeDowncasted(q2.getConversionType()) && theTypes.contains(tmp.getAssociatedType())) {
/* 111 */                       k++;
/*     */                     }
/*     */                   } 
/* 114 */                   count++;
/* 115 */                   result += k * 1.0D / theTypes.size();
/*     */                 }  continue;
/* 117 */               }  if (target.visitValueArgument(q3)) {
/*     */                 
/* 119 */                 count++;
/* 120 */                 result++; continue;
/* 121 */               }  if (target.visitValueArgument(q4)) {
/*     */                 
/* 123 */                 int k = 0;
/* 124 */                 UTypeReference type = q4.getConversionType();
/* 125 */                 UTypeReference[] possible = q4.getConvertedType().cone();
/* 126 */                 for (int t = 0; t < possible.length; t++) {
/* 127 */                   if (type.equals(possible[t]) || type.isSuperTypeFor(possible[t])) {
/* 128 */                     k++;
/*     */                   }
/*     */                 } 
/* 131 */                 count++;
/* 132 */                 result += k * 1.0D / possible.length;
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 139 */       return new ResultEntity((count == 0.0D) ? 1.0D : (result / count));
/*     */     }
/* 141 */     catch (Exception e) {
/*     */       
/* 143 */       return new ResultEntity(-2.0D);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private Set itMayContainInitialValue(ReachingDefinitionsComputedValue rd, ReachingDefinitionsFacts rdf) {
/* 150 */     Set res = new HashSet();
/* 151 */     IsProducedByCall q1 = new IsProducedByCall();
/* 152 */     IsProducedByCastOfCall q2 = new IsProducedByCastOfCall();
/*     */ 
/*     */     
/* 155 */     if (rd.isImplicitAssignment()) {
/* 156 */       UTypeReference[] t = rd.getType().cone();
/* 157 */       for (int i = 0; i < t.length; i++) {
/* 158 */         res.add(t[i]);
/*     */       }
/* 160 */     } else if (rd.askRValue(q1)) {
/* 161 */       UTypeReference[] t = q1.getReturnedType().cone();
/* 162 */       for (int i = 0; i < t.length; i++) {
/* 163 */         res.add(t[i]);
/*     */       }
/* 165 */     } else if (rd.askRValue(q2)) {
/* 166 */       UTypeReference[] t = q2.getConvertedType().cone();
/* 167 */       for (int i = 0; i < t.length; i++) {
/* 168 */         res.add(t[i]);
/*     */       }
/*     */     } else {
/* 171 */       ComputedValue[] tmpRes = rd.getDefinitionInputs(rdf);
/* 172 */       for (int i = 0; i < tmpRes.length; i++) {
/* 173 */         ReachingDefinitionsComputedValue tmp = (ReachingDefinitionsComputedValue)tmpRes[i];
/* 174 */         if (rd.askRValue(new IsDirectCopyOfLValueFrom(tmp))) {
/* 175 */           res.addAll(itMayContainInitialValue(tmp, rdf));
/*     */         }
/*     */       } 
/*     */     } 
/* 179 */     return res;
/*     */   }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\properties\membrain\AvgCallGenerality.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */