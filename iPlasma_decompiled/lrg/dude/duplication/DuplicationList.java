/*     */ package lrg.dude.duplication;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Comparator;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DuplicationList
/*     */ {
/*  16 */   private List list = new ArrayList();
/*     */ 
/*     */   
/*  19 */   public void add(Duplication d) { this.list.add(d); }
/*     */ 
/*     */   
/*     */   public void add(DuplicationList anotherList) {
/*  23 */     for (int i = 0; i < anotherList.size(); i++) {
/*  24 */       add(anotherList.get(i));
/*     */     }
/*     */   }
/*     */   
/*  28 */   public boolean contains(Duplication d) { return this.list.contains(d); }
/*     */ 
/*     */ 
/*     */   
/*  32 */   public Duplication get(int index) { return (Duplication)this.list.get(index); }
/*     */ 
/*     */ 
/*     */   
/*  36 */   public int size() { return this.list.size(); }
/*     */ 
/*     */   
/*     */   public Duplication[] toArray() {
/*  40 */     Duplication[] dups = new Duplication[this.list.size()];
/*  41 */     System.arraycopy(this.list.toArray(), 0, dups, 0, this.list.size());
/*  42 */     return dups;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DuplicationList getRedundantList() {
/*  52 */     DuplicationList redundantList = new DuplicationList();
/*  53 */     for (int i = 0; i < size(); i++) {
/*  54 */       redundantList.add(get(i));
/*  55 */       redundantList.add(get(i).makeInvert());
/*     */     } 
/*  57 */     return redundantList;
/*     */   }
/*     */   
/*     */   public static DuplicationList sortByNameAsc(DuplicationList unsorted) {
/*  61 */     DuplicationList sorted = new DuplicationList();
/*  62 */     for (int i = 0; i < unsorted.size(); i++)
/*  63 */       sorted.add(unsorted.get(i)); 
/*  64 */     Comparator c = new EntityNameComparator();
/*  65 */     Collections.sort(sorted.list, c);
/*  66 */     return sorted;
/*     */   }
/*     */   
/*     */   public static DuplicationList sortByLengthDesc(DuplicationList unsorted) {
/*  70 */     DuplicationList sorted = new DuplicationList();
/*  71 */     for (int i = 0; i < unsorted.size(); i++)
/*  72 */       sorted.add(unsorted.get(i)); 
/*  73 */     Comparator c = new DupLengthComparator();
/*  74 */     Collections.sort(sorted.list, c);
/*  75 */     Collections.reverse(sorted.list);
/*  76 */     return sorted;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static DuplicationList sort(DuplicationList unsorted) {
/*  86 */     if (unsorted == null || unsorted.size() == 0)
/*  87 */       return unsorted; 
/*  88 */     DuplicationList sorted = new DuplicationList();
/*  89 */     DuplicationList dupsForAnEntity = null;
/*  90 */     String lastEntityName = "";
/*  91 */     for (int i = 0; i < unsorted.size(); i++) {
/*  92 */       Duplication current = unsorted.get(i);
/*  93 */       String entityName = current.getReferenceCode().getEntityName();
/*  94 */       if (entityName.compareTo(lastEntityName) != 0) {
/*  95 */         lastEntityName = entityName;
/*     */         
/*  97 */         if (dupsForAnEntity != null) {
/*  98 */           Comparator c = new DupLengthComparator();
/*  99 */           Collections.sort(dupsForAnEntity.list, c);
/* 100 */           Collections.reverse(dupsForAnEntity.list);
/* 101 */           sorted.add(dupsForAnEntity);
/*     */         } 
/* 103 */         dupsForAnEntity = new DuplicationList();
/*     */       } 
/* 105 */       dupsForAnEntity.add(current);
/*     */     } 
/* 107 */     Comparator c = new DupLengthComparator();
/* 108 */     Collections.sort(dupsForAnEntity.list, c);
/* 109 */     Collections.reverse(dupsForAnEntity.list);
/* 110 */     sorted.add(dupsForAnEntity);
/* 111 */     return sorted;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 115 */     String str = "";
/* 116 */     for (int i = 0; i < size(); i++)
/* 117 */       str = String.valueOf(str) + get(i).toString() + "\n"; 
/* 118 */     return str;
/*     */   }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\dude.jar!\lrg\dude\duplication\DuplicationList.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */