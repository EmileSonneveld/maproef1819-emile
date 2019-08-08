/*     */ package lrg.common.abstractions.entities;
/*     */ 
/*     */ import java.text.DecimalFormat;
/*     */ import java.util.ArrayList;
/*     */ import lrg.common.abstractions.managers.EntityTypeManager;
/*     */ import lrg.common.abstractions.plugins.operators.AggregationOperator;
/*     */ import lrg.common.abstractions.plugins.operators.FilteringOperator;
/*     */ import lrg.common.abstractions.plugins.operators.FilteringOperatorWithThresholds;
/*     */ 
/*     */ public class ResultEntity
/*     */   extends AbstractEntity {
/*     */   private Object theValue;
/*     */   
/*     */   public ResultEntity(Object aValue) {
/*  15 */     super(EntityTypeManager.getEntityTypeForName("entity"));
/*  16 */     this.theValue = aValue;
/*     */   }
/*     */   
/*     */   public ResultEntity(String aValue) {
/*  20 */     super(EntityTypeManager.getEntityTypeForName("string"));
/*  21 */     this.theValue = aValue;
/*     */   }
/*     */   
/*     */   public ResultEntity(double aValue) {
/*  25 */     super(EntityTypeManager.getEntityTypeForName("numerical"));
/*  26 */     this.theValue = new Double(aValue);
/*     */   }
/*     */ 
/*     */   
/*     */   public ResultEntity(boolean aValue) {
/*  31 */     super(EntityTypeManager.getEntityTypeForName("boolean"));
/*  32 */     this.theValue = new Boolean(aValue);
/*     */   }
/*     */   
/*     */   public boolean applyFilter(String filteringOperator, Object threshold) {
/*  36 */     FilteringOperator theOperator = this.theType.findFilteringOperator(filteringOperator);
/*  37 */     if (theOperator == null) return false;
/*     */     
/*  39 */     if (theOperator instanceof FilteringOperatorWithThresholds) {
/*  40 */       return ((FilteringOperatorWithThresholds)theOperator).apply(this, threshold);
/*     */     }
/*  42 */     return theOperator.apply(this);
/*     */   }
/*     */ 
/*     */   
/*  46 */   public Object getValue() { return this.theValue; }
/*     */ 
/*     */   
/*     */   public String toString() {
/*  50 */     DecimalFormat twoDecimals = new DecimalFormat("#0.00");
/*  51 */     if (this.theValue instanceof Double) {
/*  52 */       String sValue = twoDecimals.format(this.theValue);
/*  53 */       if (sValue.endsWith(",00") || sValue.endsWith(".00")) return ((Double)this.theValue).intValue(); 
/*  54 */       return sValue;
/*     */     } 
/*  56 */     return this.theValue;
/*     */   }
/*     */   
/*     */   public int compareTo(ResultEntity aResultEntity) {
/*  60 */     if (aResultEntity == null) return 1; 
/*  61 */     if (this.theValue instanceof String && aResultEntity.theValue instanceof String) {
/*  62 */       String s1 = (String)this.theValue;
/*  63 */       String s2 = (String)aResultEntity.theValue;
/*  64 */       return s1.toLowerCase().compareTo(s2.toLowerCase());
/*     */     } 
/*     */     
/*  67 */     if (this.theValue instanceof ArrayList && aResultEntity.theValue instanceof ArrayList) {
/*  68 */       String s1 = ((ArrayList)this.theValue).get(0).toString();
/*  69 */       String s2 = ((ArrayList)aResultEntity.theValue).get(0).toString();
/*  70 */       return s1.toLowerCase().compareTo(s2.toLowerCase());
/*     */     } 
/*     */     
/*  73 */     if (this.theValue instanceof Double && aResultEntity.theValue instanceof Double) {
/*  74 */       double d1 = ((Double)this.theValue).doubleValue();
/*  75 */       double d2 = ((Double)aResultEntity.theValue).doubleValue();
/*  76 */       if (d1 < d2) return -1; 
/*  77 */       if (d1 > d2) return 1; 
/*  78 */       return 0;
/*     */     } 
/*     */     
/*  81 */     if (this.theValue instanceof Boolean && aResultEntity.theValue instanceof Boolean) {
/*  82 */       boolean b1 = ((Boolean)this.theValue).booleanValue();
/*  83 */       boolean b2 = ((Boolean)aResultEntity.theValue).booleanValue();
/*  84 */       if ((b1 && b2) || (!b1 && !b2)) return 0; 
/*  85 */       if (b1 && !b2) return -1;
/*     */       
/*  87 */       return 1;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  92 */     return compareTo(new ResultEntity(this.theValue.toString()));
/*     */   }
/*     */ 
/*     */   
/*  96 */   public boolean isCollectionResult() { return this.theValue instanceof ArrayList; }
/*     */ 
/*     */   
/*     */   public ArrayList getValueAsCollection() {
/* 100 */     if (isCollectionResult()) return (ArrayList)this.theValue; 
/* 101 */     return null;
/*     */   }
/*     */   
/*     */   public ResultEntity aggregate(String aggregatorName) {
/* 105 */     ArrayList groupOfResults = getValueAsCollection();
/* 106 */     if (groupOfResults == null) return this;
/*     */     
/* 108 */     if (groupOfResults.isEmpty()) return new ResultEntity(0.0D);
/*     */ 
/*     */     
/* 111 */     if (!(groupOfResults.get(0) instanceof ResultEntity)) return null; 
/* 112 */     if (aggregatorName.compareTo("size") == 0) return new ResultEntity(groupOfResults.size());
/*     */     
/* 114 */     ResultEntity firstResult = (ResultEntity)groupOfResults.get(0);
/* 115 */     EntityType typeOfElements = firstResult.getEntityType();
/* 116 */     if (typeOfElements == null) return null;
/*     */     
/* 118 */     AggregationOperator anAggregator = (AggregationOperator)typeOfElements.findFilteringRule(aggregatorName);
/* 119 */     if (anAggregator == null) return null;
/*     */     
/* 121 */     return anAggregator.aggregate(groupOfResults);
/*     */   }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\common.jar!\lrg\common\abstractions\entities\ResultEntity.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */