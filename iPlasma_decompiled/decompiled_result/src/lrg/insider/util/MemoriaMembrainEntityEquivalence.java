/*    */ package classes.lrg.insider.util;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import lrg.common.abstractions.entities.GroupEntity;
/*    */ import lrg.common.metamodel.MetaModel;
/*    */ import lrg.insider.util.MemoriaMembrainEntityEquivalence;
/*    */ import lrg.membrain.translation.UMethodReference;
/*    */ import lrg.membrain.translation.UNameReference;
/*    */ import lrg.membrain.translation.UTypeReference;
/*    */ import lrg.memoria.core.Attribute;
/*    */ import lrg.memoria.core.Method;
/*    */ import lrg.memoria.core.Type;
/*    */ 
/*    */ 
/*    */ public class MemoriaMembrainEntityEquivalence
/*    */ {
/* 17 */   public static boolean typeEquivalence(Type memoria, UTypeReference membrain) { return memoria.getProperty("Address").getValue().equals(membrain.produceMemoriaAddress()); }
/*    */ 
/*    */ 
/*    */   
/* 21 */   public static Type convertMembrainTypeToMemoriaType(UTypeReference membrain) { return (Type)MetaModel.instance().findEntityByAddress(membrain.produceMemoriaAddress()); }
/*    */ 
/*    */ 
/*    */   
/* 25 */   public static Method convertMembrainMethodToMemoriaMethod(UMethodReference method) { return (Method)MetaModel.instance().findEntityByAddress(method.produceMemoriaAddress()); }
/*    */ 
/*    */ 
/*    */   
/* 29 */   public static Method getMemoriaMethod(String method) { return (Method)MetaModel.instance().findEntityByAddress(method); }
/*    */ 
/*    */ 
/*    */   
/* 33 */   public static Type getMemoriaType(String type) { return (Type)MetaModel.instance().findEntityByAddress(type); }
/*    */ 
/*    */ 
/*    */   
/* 37 */   public static Attribute convertMembrainFieldToMemoriaField(UNameReference field) { return (Attribute)MetaModel.instance().findEntityByAddress(field.produceMemoriaAddress()); }
/*    */ 
/*    */ 
/*    */   
/* 41 */   public static UTypeReference convertMemoriaTypeToMembrainType(Type t) { return UTypeReference.getUTypeReferenceFor((String)t.getProperty("Address").getValue()); }
/*    */ 
/*    */   
/*    */   public static GroupEntity convertToGroup(ArrayList<UTypeReference> l) {
/* 45 */     ArrayList result = new ArrayList();
/* 46 */     for (int i = 0; i < l.size(); i++) {
/* 47 */       Type tmp = convertMembrainTypeToMemoriaType((UTypeReference)l.get(i));
/* 48 */       if (tmp != null) {
/* 49 */         result.add(tmp);
/*    */       }
/*    */     } 
/* 52 */     return new GroupEntity("", result);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\inside\\util\MemoriaMembrainEntityEquivalence.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */