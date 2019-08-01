/*    */ package classes.lrg.insider.plugins.commands.memoria;
/*    */ 
/*    */ import java.util.Collection;
/*    */ import lrg.insider.plugins.commands.memoria.EntityName;
/*    */ import lrg.memoria.core.NamedModelElement;
/*    */ import lrg.norex.client.NamedCommand;
/*    */ import lrg.norex.common.commands.Command;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EntityName
/*    */   extends NamedCommand<NamedModelElement, String>
/*    */ {
/* 14 */   public EntityName() { super("EntityName", "Returns name of an named entity.", "type"); }
/*    */ 
/*    */ 
/*    */   
/* 18 */   public String execute(NamedModelElement cls) { return cls.getName(); }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\commands\memoria\EntityName.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */