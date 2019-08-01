/*     */ package lrg.common.abstractions.plugins.filters;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashSet;
/*     */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*     */ import lrg.common.abstractions.entities.GroupEntity;
/*     */ import lrg.common.abstractions.entities.ResultEntity;
/*     */ import lrg.common.abstractions.plugins.Descriptor;
/*     */ import lrg.common.abstractions.plugins.properties.PropertyComputer;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FilteringRule
/*     */   extends PropertyComputer
/*     */ {
/*     */   protected String propertyDescriptor;
/*     */   protected String filteringOperator;
/*     */   Object threshold;
/*     */   
/*  23 */   public FilteringRule(Descriptor theDescriptor) { super(theDescriptor.getName(), theDescriptor.getInfo(), theDescriptor.getAllEntityTypeNames(), "boolean"); }
/*     */ 
/*     */ 
/*     */   
/*     */   public FilteringRule(String propertyName, String filteringOperator, String appliesToEntityTypeName) {
/*  28 */     super(String.valueOf(appliesToEntityTypeName) + ": " + propertyName + " " + filteringOperator, "", appliesToEntityTypeName, "boolean");
/*  29 */     initializeMembers(propertyName, filteringOperator, null);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public FilteringRule(String propertyName, String filteringOperator, String appliesToEntityTypeName, Object threshold) {
/*  35 */     super(String.valueOf(appliesToEntityTypeName) + ": " + propertyName + " " + filteringOperator + " " + threshold, "", appliesToEntityTypeName, "boolean");
/*     */     
/*  37 */     initializeMembers(propertyName, filteringOperator, threshold);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FilteringRule(String propertyName, String filteringOperator, String appliesToEntityTypeName, double threshold) {
/*  44 */     super(String.valueOf(appliesToEntityTypeName) + ": " + propertyName + " " + filteringOperator + " " + threshold, "", appliesToEntityTypeName, "boolean");
/*     */     
/*  46 */     initializeMembers(propertyName, filteringOperator, new Double(threshold));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  53 */   public String createNameForFilteredGroup(GroupEntity groupEntity) { return String.valueOf(getDescriptorObject().getName()) + " filter on (" + groupEntity.getName() + ")"; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  59 */   public ResultEntity compute(AbstractEntityInterface anEntity) { return new ResultEntity(applyFilter(anEntity)); }
/*     */ 
/*     */   
/*     */   public boolean applyFilter(AbstractEntityInterface anEntity) {
/*  63 */     if (anEntity == null) return true; 
/*  64 */     ResultEntity theResult = anEntity.getProperty(this.propertyDescriptor);
/*  65 */     if (theResult == null) return true; 
/*  66 */     return theResult.applyFilter(this.filteringOperator, this.threshold);
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/*  71 */     if (getDescriptorObject().getName().indexOf("<html>") == -1) {
/*  72 */       return getDescriptorObject().getName();
/*     */     }
/*  74 */     return "<html>" + getDescriptorObject().getName();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void initializeMembers(String propertyName, String filteringOperator, Object threshold) {
/*  81 */     this.propertyDescriptor = propertyName;
/*     */     
/*  83 */     this.filteringOperator = filteringOperator;
/*     */     
/*  85 */     this.threshold = threshold;
/*     */   }
/*     */ 
/*     */   
/*     */   public String[] getIntersectionofEntityTypeNames(FilteringRule otherRule) {
/*  90 */     String[] thisETNames = getDescriptorObject().getAllEntityTypeNames();
/*  91 */     String[] otherETNames = getDescriptorObject().getAllEntityTypeNames();
/*  92 */     ArrayList<String> thisETNamesArray = new ArrayList<String>();
/*  93 */     ArrayList<String> otherETNamesArray = new ArrayList<String>();
/*     */     
/*  95 */     for (int i = 0; i < thisETNames.length; ) { thisETNamesArray.add(thisETNames[i]); i++; }
/*  96 */      for (int i = 0; i < otherETNames.length; ) { otherETNamesArray.add(otherETNames[i]); i++; }
/*     */     
/*  98 */     thisETNamesArray.retainAll(otherETNamesArray);
/*     */     
/* 100 */     if (thisETNamesArray.size() == 0) return new String[] { "" }; 
/* 101 */     return (String[])thisETNamesArray.toArray(new String[thisETNamesArray.size()]);
/*     */   }
/*     */   
/*     */   public String[] getUnionofEntityTypeNames(FilteringRule otherRule) {
/* 105 */     String[] thisETNames = getDescriptorObject().getAllEntityTypeNames();
/* 106 */     String[] otherETNames = getDescriptorObject().getAllEntityTypeNames();
/* 107 */     HashSet<String> allDistinctETNames = new HashSet<String>();
/*     */     
/* 109 */     for (int i = 0; i < thisETNames.length; ) { allDistinctETNames.add(thisETNames[i]); i++; }
/* 110 */      for (int i = 0; i < otherETNames.length; ) { allDistinctETNames.add(otherETNames[i]); i++; }
/*     */     
/* 112 */     if (allDistinctETNames.size() == 0) return new String[] { "" }; 
/* 113 */     return (String[])allDistinctETNames.toArray(new String[allDistinctETNames.size()]);
/*     */   }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\common.jar!\lrg\common\abstractions\plugins\filters\FilteringRule.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */