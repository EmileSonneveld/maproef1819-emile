/*    */ package lrg.common.abstractions.plugins.properties;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collection;
/*    */ import java.util.HashMap;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.GroupEntity;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.common.abstractions.plugins.Descriptor;
/*    */ import lrg.common.abstractions.plugins.groups.GroupBuilder;
/*    */ 
/*    */ public class PropertyComputer extends AbstractPlugin {
/*    */   private String resultEntityTypeName;
/*    */   private HashMap usedGroups;
/*    */   private ArrayList<Boolean> isDistinctList;
/*    */   
/* 17 */   public PropertyComputer(String name, String longName, String entityType, String resultEntityTypeName) { super(name, longName, entityType);
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 74 */     this.usedGroups = new HashMap();
/* 75 */     this.isDistinctList = new ArrayList(); this.resultEntityTypeName = resultEntityTypeName; } public PropertyComputer(String name, String longName, String[] entityTypes, String resultEntityTypeName) { super(new Descriptor(name, longName, entityTypes)); this.usedGroups = new HashMap(); this.isDistinctList = new ArrayList();
/*    */     this.resultEntityTypeName = resultEntityTypeName; }
/*    */ 
/*    */   
/*    */   public ResultEntity compute(AbstractEntityInterface anEntity) {
/*    */     double theResult = 0.0D;
/*    */     int crt = 0;
/*    */     Collection<GroupBuilder> allGroups = this.usedGroups.values();
/*    */     for (GroupBuilder crtGRoup : allGroups) {
/*    */       if (((Boolean)this.isDistinctList.get(crt++)).booleanValue()) {
/*    */         theResult += getGroup(crtGRoup.getDescriptorObject().getName(), anEntity).distinct().size();
/*    */         continue;
/*    */       } 
/*    */       theResult += getGroup(crtGRoup.getDescriptorObject().getName(), anEntity).size();
/*    */     } 
/*    */     return new ResultEntity(theResult);
/*    */   }
/*    */   
/*    */   public String getResultEntityTypeName() { return this.resultEntityTypeName; }
/*    */   
/*    */   protected void basedOnGroup(GroupBuilder aGroupBuilder) {
/*    */     this.usedGroups.put(aGroupBuilder.getDescriptorObject().getName(), aGroupBuilder);
/*    */     this.isDistinctList.add(new Boolean(false));
/*    */   }
/*    */   
/*    */   protected void basedOnDistinctGroup(GroupBuilder aGroupBuilder) {
/*    */     this.usedGroups.put(aGroupBuilder.getDescriptorObject().getName(), aGroupBuilder);
/*    */     this.isDistinctList.add(new Boolean(true));
/*    */   }
/*    */   
/*    */   public ArrayList getListOfGroupNames() { return (ArrayList)this.usedGroups.keySet(); }
/*    */   
/*    */   public GroupEntity getGroup(String groupName, AbstractEntityInterface anEntity) {
/*    */     GroupBuilder groupBuilder = (GroupBuilder)this.usedGroups.get(groupName);
/*    */     if (groupBuilder instanceof GroupEntityBuilder)
/*    */       return ((GroupEntityBuilder)groupBuilder).buildGroupEntity(anEntity); 
/*    */     return groupBuilder.buildGroupEntity(anEntity);
/*    */   }
/*    */   
/*    */   public int sizeOf(String groupName, AbstractEntityInterface anEntity) { return getGroup(groupName, anEntity).size(); }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\common.jar!\lrg\common\abstractions\plugins\properties\PropertyComputer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */