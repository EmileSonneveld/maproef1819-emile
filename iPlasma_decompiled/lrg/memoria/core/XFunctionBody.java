/*    */ package lrg.memoria.core;
/*    */ 
/*    */ import lrg.membrain.representation.ControlFlowGraph;
/*    */ 
/*    */ public class XFunctionBody
/*    */   extends FunctionBody {
/*  7 */   private ControlFlowGraph cfg = null;
/*    */ 
/*    */   
/* 10 */   public XFunctionBody(Method scope) { super(scope); }
/*    */ 
/*    */ 
/*    */   
/*    */   public XFunctionBody() {}
/*    */ 
/*    */ 
/*    */   
/* 18 */   public void setControlFlowGraph(ControlFlowGraph x) { this.cfg = x; }
/*    */ 
/*    */ 
/*    */   
/* 22 */   public ControlFlowGraph getControlFlowGraph() { return this.cfg; }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\core\XFunctionBody.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */