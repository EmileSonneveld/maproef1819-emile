/*     */ package lrg.memoria.core;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ 
/*     */ public class Location
/*     */   implements Serializable
/*     */ {
/*     */   private static Location unknownLocation;
/*  11 */   public static String unknow_file_name = "unknown_file"; private File file; private int startLine;
/*     */   
/*     */   public static Location getUnknownLocation() {
/*  14 */     if (unknownLocation == null) {
/*  15 */       unknownLocation = new Location(File.getUnknownFile());
/*  16 */       unknownLocation.setStartLine(-1);
/*  17 */       unknownLocation.setEndLine(-1);
/*  18 */       unknownLocation.setStartChar(-1);
/*  19 */       unknownLocation.setEndChar(-1);
/*     */     } 
/*  21 */     return unknownLocation;
/*     */   }
/*     */   private int startChar; private int endLine; private int endChar;
/*     */   static ArrayList<Location> extractLocationsWithin(Location range, ArrayList<Location> instances) {
/*  25 */     ArrayList<Location> inst = new ArrayList<Location>();
/*  26 */     for (Iterator<Location> itl = instances.iterator(); itl.hasNext(); ) {
/*  27 */       Location l = (Location)itl.next();
/*  28 */       if (range.contains(l)) {
/*  29 */         inst.add(range.relativeOf(l));
/*  30 */         itl.remove();
/*     */       } 
/*     */     } 
/*  33 */     return inst;
/*     */   }
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
/*     */   static int checkLocationsWithin(Location range, ArrayList<Location> instances) {
/*  47 */     int res = 0;
/*  48 */     for (Iterator<Location> itl = instances.iterator(); itl.hasNext(); ) {
/*  49 */       Location l = (Location)itl.next();
/*  50 */       if (range.contains(l)) {
/*  51 */         res = 1; continue;
/*  52 */       }  if (range.intersects(l))
/*  53 */         return -1; 
/*     */     } 
/*  55 */     return res;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static int checkIfAnyContain(Location range, ArrayList<Location> instances) {
/*  67 */     int res = 0;
/*  68 */     for (Iterator<Location> itl = instances.iterator(); itl.hasNext(); ) {
/*  69 */       Location l = (Location)itl.next();
/*  70 */       if (l.contains(range)) {
/*  71 */         res = 1; continue;
/*  72 */       }  if (range.intersects(l))
/*  73 */         return -1; 
/*     */     } 
/*  75 */     return res;
/*     */   }
/*     */   
/*     */   static void shiftLocations(int lines, ArrayList<Location> instances) {
/*  79 */     for (Location l : instances) {
/*  80 */       l.startLine += lines;
/*  81 */       l.endLine += lines;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  90 */   public Location(File file) { this.file = file; }
/*     */ 
/*     */ 
/*     */   
/*  94 */   public File getFile() { return this.file; }
/*     */ 
/*     */ 
/*     */   
/*  98 */   public void setStartLine(int startLine) { this.startLine = startLine; }
/*     */ 
/*     */ 
/*     */   
/* 102 */   public int getStartLine() { return this.startLine; }
/*     */ 
/*     */ 
/*     */   
/* 106 */   public void setStartChar(int startChar) { this.startChar = startChar; }
/*     */ 
/*     */ 
/*     */   
/* 110 */   public int getStartChar() { return this.startChar; }
/*     */ 
/*     */ 
/*     */   
/* 114 */   public void setEndLine(int endLine) { this.endLine = endLine; }
/*     */ 
/*     */ 
/*     */   
/* 118 */   public int getEndLine() { return this.endLine; }
/*     */ 
/*     */ 
/*     */   
/* 122 */   public void setEndChar(int endChar) { this.endChar = endChar; }
/*     */ 
/*     */ 
/*     */   
/* 126 */   public int getEndChar() { return this.endChar; }
/*     */ 
/*     */   
/*     */   public Location relativeOf(int absLine, int absChar) {
/* 130 */     Location nl = new Location(this.file);
/* 131 */     nl.setStartLine(absLine - this.startLine);
/* 132 */     if (absLine != this.startLine) {
/* 133 */       nl.setStartChar(absChar);
/*     */     } else {
/* 135 */       nl.setStartChar(absChar - this.startChar);
/*     */     } 
/* 137 */     nl.setEndLine(-2147483648);
/* 138 */     nl.setEndChar(-2147483648);
/* 139 */     return nl;
/*     */   }
/*     */   
/*     */   public Location absoluteOf(int relLine, int relChar) {
/* 143 */     Location nl = new Location(this.file);
/* 144 */     nl.setStartLine(this.startLine + relLine);
/* 145 */     if (relLine != 0) {
/* 146 */       nl.setStartChar(relChar);
/*     */     } else {
/* 148 */       nl.setStartChar(this.startChar + relChar);
/*     */     } 
/* 150 */     nl.setEndLine(-2147483648);
/* 151 */     nl.setEndChar(-2147483648);
/* 152 */     return nl;
/*     */   }
/*     */   
/*     */   public Location relativeOf(Location abs) {
/* 156 */     Location nl = relativeOf(abs.getStartLine(), abs.getStartChar());
/* 157 */     Location temp = relativeOf(abs.getEndLine(), abs.getEndChar());
/* 158 */     nl.setEndLine(temp.getStartLine());
/* 159 */     nl.setEndChar(temp.getStartChar());
/* 160 */     return nl;
/*     */   }
/*     */   
/*     */   public Location absoluteOf(Location rel) {
/* 164 */     Location nl = absoluteOf(rel.getStartLine(), rel.getStartChar());
/* 165 */     Location temp = absoluteOf(rel.getEndLine(), rel.getEndChar());
/* 166 */     nl.setEndLine(temp.getStartLine());
/* 167 */     nl.setEndChar(temp.getStartChar());
/* 168 */     return nl;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 173 */   boolean startsAfter(int line, int col) { return !(line >= this.startLine && (line != this.startLine || col >= this.startChar)); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean includes(int line, int col) {
/* 179 */     if ((line <= this.startLine || line >= this.endLine) && (
/* 180 */       line <= this.startLine || line != this.endLine || col >= this.endChar) && (
/* 181 */       line != this.startLine || line >= this.endLine || col <= this.startChar) && (
/* 182 */       line != this.startLine || line != this.endLine || col <= this.startChar || col >= this.endChar)) return false; 
/*     */     return true;
/*     */   }
/*     */   
/* 186 */   public boolean includes(Location l) { return (includes(l.startLine, l.startChar) && includes(l.endLine, l.endChar)); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean contains(int line, int col) {
/* 193 */     if ((line <= this.startLine || line >= this.endLine) && (
/* 194 */       line <= this.startLine || line != this.endLine || col > this.endChar) && (
/* 195 */       line != this.startLine || line >= this.endLine || col < this.startChar) && (
/* 196 */       line != this.startLine || line != this.endLine || col < this.startChar || col > this.endChar)) return false; 
/*     */     return true;
/*     */   }
/*     */   
/* 200 */   public boolean contains(Location l) { return (contains(l.startLine, l.startChar) && contains(l.endLine, l.endChar)); }
/*     */ 
/*     */   
/*     */   public boolean intersects(Location l) {
/* 204 */     if (!contains(l.startLine, l.startChar) && 
/* 205 */       !contains(l.endLine, l.endChar) && 
/* 206 */       !l.contains(this.startLine, this.startChar)) return false; 
/*     */     return true;
/*     */   }
/*     */   public String toString() {
/* 210 */     StringBuffer buf = new StringBuffer(this.file.getFullName());
/* 211 */     buf.append(":").append(this.startLine);
/* 212 */     return buf.toString();
/*     */   }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\core\Location.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */