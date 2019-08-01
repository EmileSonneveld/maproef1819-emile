/*     */ package lrg.memoria.importer.mcc.javacc;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.Reader;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SimpleCharStream
/*     */ {
/*     */   public static final boolean staticFlag = false;
/*     */   int bufsize;
/*     */   int available;
/*     */   int tokenBegin;
/*     */   public int bufpos;
/*     */   protected int[] bufline;
/*     */   protected int[] bufcolumn;
/*     */   protected int column;
/*     */   protected int line;
/*     */   protected boolean prevCharIsCR;
/*     */   protected boolean prevCharIsLF;
/*     */   protected Reader inputStream;
/*     */   protected char[] buffer;
/*     */   protected int maxNextCharInd;
/*     */   protected int inBuf;
/*     */   
/*     */   protected void ExpandBuff(boolean wrapAround) {
/*  33 */     char[] newbuffer = new char[this.bufsize + 2048];
/*  34 */     int[] newbufline = new int[this.bufsize + 2048];
/*  35 */     int[] newbufcolumn = new int[this.bufsize + 2048];
/*     */ 
/*     */     
/*     */     try {
/*  39 */       if (wrapAround)
/*     */       {
/*  41 */         System.arraycopy(this.buffer, this.tokenBegin, newbuffer, 0, this.bufsize - this.tokenBegin);
/*  42 */         System.arraycopy(this.buffer, 0, newbuffer, 
/*  43 */             this.bufsize - this.tokenBegin, this.bufpos);
/*  44 */         this.buffer = newbuffer;
/*     */         
/*  46 */         System.arraycopy(this.bufline, this.tokenBegin, newbufline, 0, this.bufsize - this.tokenBegin);
/*  47 */         System.arraycopy(this.bufline, 0, newbufline, this.bufsize - this.tokenBegin, this.bufpos);
/*  48 */         this.bufline = newbufline;
/*     */         
/*  50 */         System.arraycopy(this.bufcolumn, this.tokenBegin, newbufcolumn, 0, this.bufsize - this.tokenBegin);
/*  51 */         System.arraycopy(this.bufcolumn, 0, newbufcolumn, this.bufsize - this.tokenBegin, this.bufpos);
/*  52 */         this.bufcolumn = newbufcolumn;
/*     */         
/*  54 */         this.maxNextCharInd = this.bufpos += this.bufsize - this.tokenBegin;
/*     */       }
/*     */       else
/*     */       {
/*  58 */         System.arraycopy(this.buffer, this.tokenBegin, newbuffer, 0, this.bufsize - this.tokenBegin);
/*  59 */         this.buffer = newbuffer;
/*     */         
/*  61 */         System.arraycopy(this.bufline, this.tokenBegin, newbufline, 0, this.bufsize - this.tokenBegin);
/*  62 */         this.bufline = newbufline;
/*     */         
/*  64 */         System.arraycopy(this.bufcolumn, this.tokenBegin, newbufcolumn, 0, this.bufsize - this.tokenBegin);
/*  65 */         this.bufcolumn = newbufcolumn;
/*     */         
/*  67 */         this.maxNextCharInd = this.bufpos -= this.tokenBegin;
/*     */       }
/*     */     
/*  70 */     } catch (Throwable t) {
/*     */       
/*  72 */       throw new Error(t.getMessage());
/*     */     } 
/*     */ 
/*     */     
/*  76 */     this.bufsize += 2048;
/*  77 */     this.available = this.bufsize;
/*  78 */     this.tokenBegin = 0;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void FillBuff() throws IOException {
/*  83 */     if (this.maxNextCharInd == this.available)
/*     */     {
/*  85 */       if (this.available == this.bufsize) {
/*     */         
/*  87 */         if (this.tokenBegin > 2048) {
/*     */           
/*  89 */           this.bufpos = this.maxNextCharInd = 0;
/*  90 */           this.available = this.tokenBegin;
/*     */         }
/*  92 */         else if (this.tokenBegin < 0) {
/*  93 */           this.bufpos = this.maxNextCharInd = 0;
/*     */         } else {
/*  95 */           ExpandBuff(false);
/*     */         } 
/*  97 */       } else if (this.available > this.tokenBegin) {
/*  98 */         this.available = this.bufsize;
/*  99 */       } else if (this.tokenBegin - this.available < 2048) {
/* 100 */         ExpandBuff(true);
/*     */       } else {
/* 102 */         this.available = this.tokenBegin;
/*     */       } 
/*     */     }
/*     */     try {
/*     */       int i;
/* 107 */       if ((i = this.inputStream.read(this.buffer, this.maxNextCharInd, 
/* 108 */           this.available - this.maxNextCharInd)) == -1) {
/*     */         
/* 110 */         this.inputStream.close();
/* 111 */         throw new IOException();
/*     */       } 
/*     */       
/* 114 */       this.maxNextCharInd += i;
/*     */       
/*     */       return;
/* 117 */     } catch (IOException e) {
/* 118 */       this.bufpos--;
/* 119 */       backup(0);
/* 120 */       if (this.tokenBegin == -1)
/* 121 */         this.tokenBegin = this.bufpos; 
/* 122 */       throw e;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public char BeginToken() throws IOException {
/* 128 */     this.tokenBegin = -1;
/* 129 */     char c = readChar();
/* 130 */     this.tokenBegin = this.bufpos;
/*     */     
/* 132 */     return c;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void UpdateLineColumn(char c) {
/* 137 */     this.column++;
/*     */     
/* 139 */     if (this.prevCharIsLF) {
/*     */       
/* 141 */       this.prevCharIsLF = false;
/* 142 */       this.line += (this.column = 1);
/*     */     }
/* 144 */     else if (this.prevCharIsCR) {
/*     */       
/* 146 */       this.prevCharIsCR = false;
/* 147 */       if (c == '\n') {
/*     */         
/* 149 */         this.prevCharIsLF = true;
/*     */       } else {
/*     */         
/* 152 */         this.line += (this.column = 1);
/*     */       } 
/*     */     } 
/* 155 */     switch (c) {
/*     */       
/*     */       case '\r':
/* 158 */         this.prevCharIsCR = true;
/*     */         break;
/*     */       case '\n':
/* 161 */         this.prevCharIsLF = true;
/*     */         break;
/*     */       case '\t':
/* 164 */         this.column--;
/* 165 */         this.column += 8 - (this.column & 0x7);
/*     */         break;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 171 */     this.bufline[this.bufpos] = this.line;
/* 172 */     this.bufcolumn[this.bufpos] = this.column;
/*     */   }
/*     */ 
/*     */   
/*     */   public char readChar() throws IOException {
/* 177 */     if (this.inBuf > 0) {
/*     */       
/* 179 */       this.inBuf--;
/*     */       
/* 181 */       if (++this.bufpos == this.bufsize) {
/* 182 */         this.bufpos = 0;
/*     */       }
/* 184 */       return this.buffer[this.bufpos];
/*     */     } 
/*     */     
/* 187 */     if (++this.bufpos >= this.maxNextCharInd) {
/* 188 */       FillBuff();
/*     */     }
/* 190 */     char c = this.buffer[this.bufpos];
/*     */     
/* 192 */     UpdateLineColumn(c);
/* 193 */     return c;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 202 */   public int getColumn() { return this.bufcolumn[this.bufpos]; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 211 */   public int getLine() { return this.bufline[this.bufpos]; }
/*     */ 
/*     */ 
/*     */   
/* 215 */   public int getEndColumn() { return this.bufcolumn[this.bufpos]; }
/*     */ 
/*     */ 
/*     */   
/* 219 */   public int getEndLine() { return this.bufline[this.bufpos]; }
/*     */ 
/*     */ 
/*     */   
/* 223 */   public int getBeginColumn() { return this.bufcolumn[this.tokenBegin]; }
/*     */ 
/*     */ 
/*     */   
/* 227 */   public int getBeginLine() { return this.bufline[this.tokenBegin]; }
/*     */ 
/*     */ 
/*     */   
/*     */   public void backup(int amount) {
/* 232 */     this.inBuf += amount;
/* 233 */     if (this.bufpos -= amount < 0)
/* 234 */       this.bufpos += this.bufsize;  } public SimpleCharStream(Reader dstream, int startline, int startcolumn, int buffersize) { this.bufpos = -1; this.column = 0;
/*     */     this.line = 1;
/*     */     this.prevCharIsCR = false;
/*     */     this.prevCharIsLF = false;
/*     */     this.maxNextCharInd = 0;
/*     */     this.inBuf = 0;
/* 240 */     this.inputStream = dstream;
/* 241 */     this.line = startline;
/* 242 */     this.column = startcolumn - 1;
/*     */     
/* 244 */     this.available = this.bufsize = buffersize;
/* 245 */     this.buffer = new char[buffersize];
/* 246 */     this.bufline = new int[buffersize];
/* 247 */     this.bufcolumn = new int[buffersize]; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 253 */   public SimpleCharStream(Reader dstream, int startline, int startcolumn) { this(dstream, startline, startcolumn, 4096); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 258 */   public SimpleCharStream(Reader dstream) { this(dstream, 1, 1, 4096); }
/*     */ 
/*     */ 
/*     */   
/*     */   public void ReInit(Reader dstream, int startline, int startcolumn, int buffersize) {
/* 263 */     this.inputStream = dstream;
/* 264 */     this.line = startline;
/* 265 */     this.column = startcolumn - 1;
/*     */     
/* 267 */     if (this.buffer == null || buffersize != this.buffer.length) {
/*     */       
/* 269 */       this.available = this.bufsize = buffersize;
/* 270 */       this.buffer = new char[buffersize];
/* 271 */       this.bufline = new int[buffersize];
/* 272 */       this.bufcolumn = new int[buffersize];
/*     */     } 
/* 274 */     this.prevCharIsLF = this.prevCharIsCR = false;
/* 275 */     this.tokenBegin = this.inBuf = this.maxNextCharInd = 0;
/* 276 */     this.bufpos = -1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 282 */   public void ReInit(Reader dstream, int startline, int startcolumn) { ReInit(dstream, startline, startcolumn, 4096); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 287 */   public void ReInit(Reader dstream) { ReInit(dstream, 1, 1, 4096); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 292 */   public SimpleCharStream(InputStream dstream, int startline, int startcolumn, int buffersize) { this(new InputStreamReader(dstream), startline, startcolumn, 4096); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 298 */   public SimpleCharStream(InputStream dstream, int startline, int startcolumn) { this(dstream, startline, startcolumn, 4096); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 303 */   public SimpleCharStream(InputStream dstream) { this(dstream, 1, 1, 4096); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 309 */   public void ReInit(InputStream dstream, int startline, int startcolumn, int buffersize) { ReInit(new InputStreamReader(dstream), startline, startcolumn, 4096); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 314 */   public void ReInit(InputStream dstream) { ReInit(dstream, 1, 1, 4096); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 319 */   public void ReInit(InputStream dstream, int startline, int startcolumn) { ReInit(dstream, startline, startcolumn, 4096); }
/*     */ 
/*     */   
/*     */   public String GetImage() {
/* 323 */     if (this.bufpos >= this.tokenBegin) {
/* 324 */       return new String(this.buffer, this.tokenBegin, this.bufpos - this.tokenBegin + 1);
/*     */     }
/* 326 */     return String.valueOf(new String(this.buffer, this.tokenBegin, this.bufsize - this.tokenBegin)) + 
/* 327 */       new String(this.buffer, 0, this.bufpos + 1);
/*     */   }
/*     */ 
/*     */   
/*     */   public char[] GetSuffix(int len) {
/* 332 */     char[] ret = new char[len];
/*     */     
/* 334 */     if (this.bufpos + 1 >= len) {
/* 335 */       System.arraycopy(this.buffer, this.bufpos - len + 1, ret, 0, len);
/*     */     } else {
/*     */       
/* 338 */       System.arraycopy(this.buffer, this.bufsize - len - this.bufpos - 1, ret, 0, 
/* 339 */           len - this.bufpos - 1);
/* 340 */       System.arraycopy(this.buffer, 0, ret, len - this.bufpos - 1, this.bufpos + 1);
/*     */     } 
/*     */     
/* 343 */     return ret;
/*     */   }
/*     */ 
/*     */   
/*     */   public void Done() throws IOException {
/* 348 */     this.buffer = null;
/* 349 */     this.bufline = null;
/* 350 */     this.bufcolumn = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void adjustBeginLineColumn(int newLine, int newCol) {
/* 358 */     int len, start = this.tokenBegin;
/*     */ 
/*     */     
/* 361 */     if (this.bufpos >= this.tokenBegin) {
/*     */       
/* 363 */       len = this.bufpos - this.tokenBegin + this.inBuf + 1;
/*     */     }
/*     */     else {
/*     */       
/* 367 */       len = this.bufsize - this.tokenBegin + this.bufpos + 1 + this.inBuf;
/*     */     } 
/*     */     
/* 370 */     int i = 0, j = 0, k = 0;
/* 371 */     int nextColDiff = 0, columnDiff = 0;
/*     */     
/* 373 */     while (i < len && 
/* 374 */       this.bufline[j = start % this.bufsize] == this.bufline[k = ++start % this.bufsize]) {
/*     */       
/* 376 */       this.bufline[j] = newLine;
/* 377 */       nextColDiff = columnDiff + this.bufcolumn[k] - this.bufcolumn[j];
/* 378 */       this.bufcolumn[j] = newCol + columnDiff;
/* 379 */       columnDiff = nextColDiff;
/* 380 */       i++;
/*     */     } 
/*     */     
/* 383 */     if (i < len) {
/*     */       
/* 385 */       this.bufline[j] = newLine++;
/* 386 */       this.bufcolumn[j] = newCol + columnDiff;
/*     */       
/* 388 */       while (i++ < len) {
/*     */         
/* 390 */         if (this.bufline[j = start % this.bufsize] != this.bufline[++start % this.bufsize]) {
/* 391 */           this.bufline[j] = newLine++; continue;
/*     */         } 
/* 393 */         this.bufline[j] = newLine;
/*     */       } 
/*     */     } 
/*     */     
/* 397 */     this.line = this.bufline[j];
/* 398 */     this.column = this.bufcolumn[j];
/*     */   }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\importer\mcc\javacc\SimpleCharStream.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */