/*    */ package classes.lrg.insider.plugins.core.properties.memoria.subsystems;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.insider.plugins.core.properties.AbstractDetail;
/*    */ import lrg.insider.plugins.core.properties.memoria.subsystems.Detail;
/*    */ import lrg.memoria.core.Subsystem;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Detail
/*    */   extends AbstractDetail
/*    */ {
/* 27 */   public Detail() { super("Detail", "Constructs a detailed HTML String to be shown in the browser.", "subsystem", "string"); }
/*    */ 
/*    */   
/*    */   public ResultEntity compute(AbstractEntityInterface aSubsystem) {
/* 31 */     String text = "";
/* 32 */     text = String.valueOf(text) + "<h1>Subsystem " + aSubsystem.getName() + "</h1><hr><br>";
/*    */     
/* 34 */     text = String.valueOf(text) + "consists of following packages:" + bulletedLinkList(((Subsystem)aSubsystem).getPackages());
/*    */     
/* 36 */     return new ResultEntity(text);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\core\properties\memoria\subsystems\Detail.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */