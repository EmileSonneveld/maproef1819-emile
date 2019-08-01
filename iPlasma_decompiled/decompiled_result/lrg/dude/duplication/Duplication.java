/*     */ package lrg.dude.duplication;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Duplication
/*     */ {
/*     */   private CodeFragment referenceCode;
/*     */   private CodeFragment duplicateCode;
/*     */   private DuplicationType type;
/*     */   private String signature;
/*     */   private int realLength;
/*     */   private int copiedLength;
/*     */   
/*     */   public Duplication(CodeFragment refCode, CodeFragment dupCode, DuplicationType type, String signature, int copiedLength) {
/*  30 */     this.signature = signature;
/*  31 */     this.referenceCode = refCode;
/*  32 */     this.duplicateCode = dupCode;
/*  33 */     this.type = type;
/*  34 */     this.realLength = (refCode.getLength() <= dupCode.getLength()) ? refCode.getLength() : dupCode.getLength();
/*  35 */     this.copiedLength = copiedLength;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  40 */   public CodeFragment getReferenceCode() { return this.referenceCode; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  45 */   public CodeFragment getDuplicateCode() { return this.duplicateCode; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  50 */   public DuplicationType getType() { return this.type; }
/*     */ 
/*     */ 
/*     */   
/*  54 */   public long copiedLength() { return this.copiedLength; }
/*     */ 
/*     */ 
/*     */   
/*  58 */   public long realLength() { return this.realLength; }
/*     */ 
/*     */   
/*     */   public String toString() {
/*  62 */     StringBuffer sb = new StringBuffer("[");
/*  63 */     sb.append(String.valueOf(this.referenceCode.getEntityName()) + 
/*  64 */         "," + this.referenceCode.getBeginLine() + 
/*  65 */         "," + this.referenceCode.getEndLine());
/*  66 */     sb.append("] - [");
/*  67 */     sb.append(String.valueOf(this.duplicateCode.getEntityName()) + 
/*  68 */         "," + this.duplicateCode.getBeginLine() + 
/*  69 */         "," + this.duplicateCode.getEndLine());
/*  70 */     sb.append("]");
/*  71 */     sb.append(" - copiedLength = " + this.copiedLength + 
/*  72 */         " (realLength = " + this.realLength + 
/*  73 */         ") - type = " + this.type + 
/*  74 */         " - signature = " + this.signature);
/*  75 */     return new String(sb);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  80 */   public String getSignature() { return this.signature; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Duplication makeInvert() {
/*     */     DuplicationType newType;
/*  90 */     StringBuffer invertSB = new StringBuffer(this.signature);
/*     */     
/*  92 */     if (this.type == DuplicationType.DELETE) {
/*  93 */       newType = DuplicationType.INSERT;
/*  94 */     } else if (this.type == DuplicationType.INSERT) {
/*  95 */       newType = DuplicationType.DELETE;
/*     */     } else {
/*  97 */       newType = this.type;
/*  98 */     }  for (int i = 0; i < invertSB.length(); i++) {
/*  99 */       if (invertSB.charAt(i) == 'D') {
/* 100 */         invertSB.setCharAt(i, 'I');
/* 101 */       } else if (invertSB.charAt(i) == 'I') {
/* 102 */         invertSB.setCharAt(i, 'D');
/*     */       } 
/* 104 */     }  String invertSignature = invertSB.toString();
/* 105 */     return new Duplication(this.duplicateCode, this.referenceCode, newType, invertSignature, this.copiedLength);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isSelfDuplication() {
/* 115 */     String refName = getReferenceCode().getEntityName();
/* 116 */     String dupName = getDuplicateCode().getEntityName();
/* 117 */     if (refName.compareTo(dupName) == 0) {
/* 118 */       return true;
/*     */     }
/* 120 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\dude.jar!\lrg\dude\duplication\Duplication.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */