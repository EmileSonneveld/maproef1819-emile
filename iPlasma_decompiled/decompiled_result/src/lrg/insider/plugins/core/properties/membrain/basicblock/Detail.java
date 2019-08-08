/*    */ package classes.lrg.insider.plugins.core.properties.membrain.basicblock;
/*    */ 
/*    */ import java.util.List;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.insider.plugins.core.properties.AbstractDetail;
/*    */ import lrg.membrain.representation.BasicBlock;
/*    */ import lrg.membrain.translation.StatementsCodeMapper;
/*    */ import lrg.memoria.core.Function;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Detail
/*    */   extends AbstractDetail
/*    */ {
/* 18 */   public Detail() { super("Detail", "The detailed information for a basic block", "basic block", "string"); }
/*    */ 
/*    */ 
/*    */   
/*    */   public ResultEntity compute(AbstractEntityInterface anEntity) {
/* 23 */     if (anEntity instanceof BasicBlock) {
/* 24 */       String text = "<h1>Basic block view </h1><hr><br>";
/* 25 */       List<StatementsCodeMapper.Stripe> list = StatementsCodeMapper.instance().find((BasicBlock)anEntity);
/* 26 */       int lineBias = ((Function)anEntity.belongsTo("method")).getBody().getLocation().getStartLine();
/*    */       
/* 28 */       String code = ((Function)anEntity.belongsTo("method")).getBody().getSourceCode();
/* 29 */       int i = 0;
/* 30 */       int j = 0;
/* 31 */       for (int k = 0; k < code.length(); k++) {
/* 32 */         char c = code.charAt(k);
/* 33 */         j++;
/* 34 */         if (c == '\n')
/* 35 */         { i++;
/* 36 */           j = 0;
/* 37 */           text = String.valueOf(text) + '\n'; }
/* 38 */         else if (c != ' ')
/* 39 */         { int t = 0; while (true) { if (t >= list.size())
/*    */             
/*    */             { 
/*    */ 
/*    */ 
/*    */               
/* 45 */               text = String.valueOf(text) + c; break; }  if (((StatementsCodeMapper.Stripe)list.get(t)).inInStripe(i + lineBias, j)) { text = String.valueOf(text) + "<font bgcolor=#FFE0E0>" + c + "</font>"; break; }  t++; }
/*    */            }
/* 47 */         else { text = String.valueOf(text) + c; }
/*    */       
/*    */       } 
/*    */       
/* 51 */       return new ResultEntity(text);
/*    */     } 
/* 53 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\core\properties\membrain\basicblock\Detail.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */