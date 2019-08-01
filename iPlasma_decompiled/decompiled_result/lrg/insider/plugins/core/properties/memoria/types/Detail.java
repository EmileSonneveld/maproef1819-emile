/*    */ package classes.lrg.insider.plugins.core.properties.memoria.types;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.insider.plugins.core.properties.AbstractDetail;
/*    */ import lrg.insider.plugins.core.properties.memoria.types.Detail;
/*    */ import lrg.memoria.core.ExplicitlyDefinedType;
/*    */ import lrg.memoria.core.Primitive;
/*    */ import lrg.memoria.core.TemplateInstance;
/*    */ import lrg.memoria.core.TypeDecorator;
/*    */ 
/*    */ public class Detail extends AbstractDetail {
/* 13 */   public Detail() { super("Detail", "Constructs a detailed HTML String to be shown in the browser.", "type", "string"); }
/*    */ 
/*    */   
/*    */   public ResultEntity compute(AbstractEntityInterface anEntity) {
/* 17 */     if (anEntity instanceof Primitive) {
/* 18 */       Primitive aPrimitive = (Primitive)anEntity;
/* 19 */       String text = "<h1>Primitive Detail: " + linkTo(aPrimitive) + "</h1><hr><br>";
/* 20 */       return new ResultEntity(text);
/*    */     } 
/*    */     
/* 23 */     if (anEntity instanceof TypeDecorator) {
/* 24 */       TypeDecorator aTypeDecorator = (TypeDecorator)anEntity;
/* 25 */       String text = "<h1>Type Decorator Detail: " + linkTo(aTypeDecorator) + "</h1><hr><br>";
/* 26 */       text = String.valueOf(text) + "Full Name: " + aTypeDecorator.getFullName() + "<br>";
/* 27 */       text = String.valueOf(text) + "Decorated Type: " + linkTo(aTypeDecorator.getDecoratedType()) + "<br>";
/* 28 */       if (anEntity instanceof ExplicitlyDefinedType)
/* 29 */         text = String.valueOf(text) + "Location: " + ((ExplicitlyDefinedType)anEntity).getLocation().getFile().getFullName() + "<br>"; 
/* 30 */       return new ResultEntity(text);
/*    */     } 
/*    */     
/* 33 */     if (anEntity instanceof TemplateInstance) {
/* 34 */       TemplateInstance aTemplateInstance = (TemplateInstance)anEntity;
/* 35 */       String text = "<h1>Template Instance Detail: " + linkTo(aTemplateInstance) + "</h1><hr><br>";
/* 36 */       text = String.valueOf(text) + "Full Name: " + aTemplateInstance.getFullName() + "<br>";
/* 37 */       text = String.valueOf(text) + "Template: " + linkTo(aTemplateInstance.getTemplateType()) + "<br>";
/* 38 */       text = String.valueOf(text) + "Type instantiations: " + aTemplateInstance.getTypeInstantiations().size() + "<br>";
/* 39 */       text = String.valueOf(text) + bulletedLinkList(aTemplateInstance.getTypeInstantiations());
/* 40 */       return new ResultEntity(text);
/*    */     } 
/*    */     
/* 43 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\core\properties\memoria\types\Detail.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */