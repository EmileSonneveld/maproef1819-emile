/*    */ package classes.lrg.insider.plugins.tools;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Iterator;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.plugins.AbstractPlugin;
/*    */ import lrg.common.abstractions.plugins.properties.PropertyComputer;
/*    */ import lrg.common.abstractions.plugins.tools.AbstractEntityTool;
/*    */ import lrg.common.metamodel.Loader;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PluginsGenerator
/*    */   extends AbstractEntityTool
/*    */ {
/* 19 */   public PluginsGenerator() { super("PluginsGenerator", "Plugins Generator", "system"); }
/*    */ 
/*    */   
/*    */   public void run(AbstractEntityInterface anEntity, Object theToolParameters) {
/* 23 */     Loader loader = new Loader("classes");
/* 24 */     Iterator it = loader.getNames().iterator();
/* 25 */     int counter = 0;
/*    */     
/* 27 */     while (it.hasNext()) {
/* 28 */       AbstractPlugin someCommand = loader.buildFrom((String)it.next());
/* 29 */       if (!(someCommand instanceof PropertyComputer))
/* 30 */         continue;  PropertyComputer aComputer = (PropertyComputer)someCommand;
/* 31 */       System.out.println("public ResultEntity " + 
/* 32 */           aComputer.getDescriptorObject().getName() + 
/* 33 */           "() { return super.getProperty(\"" + aComputer.getDescriptorObject().getName() + 
/* 34 */           "\"); }");
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 40 */   public String getToolName() { return "PluginsGenerator"; }
/*    */ 
/*    */ 
/*    */   
/* 44 */   public ArrayList<String> getParameterList() { return new ArrayList<String>(); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 50 */   public ArrayList<String> getParameterExplanations() { return new ArrayList<String>(); }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\tools\PluginsGenerator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */