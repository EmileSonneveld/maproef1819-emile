/*     */ package lrg.memoria.importer.cdif;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.Reader;
/*     */ 
/*     */ 
/*     */ public class SimpleCharStream
/*     */ {
/*     */   public static final boolean staticFlag = true;
/*     */   static int bufsize;
/*     */   static int available;
/*     */   static int tokenBegin;
/*  15 */   public static int bufpos = -1;
/*     */   
/*     */   protected static int[] bufline;
/*     */   protected static int[] bufcolumn;
/*  19 */   protected static int column = 0;
/*  20 */   protected static int line = 1;
/*     */   
/*     */   protected static boolean prevCharIsCR = false;
/*     */   
/*     */   protected static boolean prevCharIsLF = false;
/*     */   
/*     */   protected static Reader inputStream;
/*     */   protected static char[] buffer;
/*  28 */   protected static int maxNextCharInd = 0;
/*  29 */   protected static int inBuf = 0;
/*     */ 
/*     */   
/*     */   protected static void ExpandBuff(boolean wrapAround) {
/*  33 */     char[] newbuffer = new char[bufsize + 2048];
/*  34 */     int[] newbufline = new int[bufsize + 2048];
/*  35 */     int[] newbufcolumn = new int[bufsize + 2048];
/*     */ 
/*     */     
/*     */     try {
/*  39 */       if (wrapAround)
/*     */       {
/*  41 */         System.arraycopy(buffer, tokenBegin, newbuffer, 0, bufsize - tokenBegin);
/*  42 */         System.arraycopy(buffer, 0, newbuffer, 
/*  43 */             bufsize - tokenBegin, bufpos);
/*  44 */         buffer = newbuffer;
/*     */         
/*  46 */         System.arraycopy(bufline, tokenBegin, newbufline, 0, bufsize - tokenBegin);
/*  47 */         System.arraycopy(bufline, 0, newbufline, bufsize - tokenBegin, bufpos);
/*  48 */         bufline = newbufline;
/*     */         
/*  50 */         System.arraycopy(bufcolumn, tokenBegin, newbufcolumn, 0, bufsize - tokenBegin);
/*  51 */         System.arraycopy(bufcolumn, 0, newbufcolumn, bufsize - tokenBegin, bufpos);
/*  52 */         bufcolumn = newbufcolumn;
/*     */         
/*  54 */         maxNextCharInd = bufpos += bufsize - tokenBegin;
/*     */       }
/*     */       else
/*     */       {
/*  58 */         System.arraycopy(buffer, tokenBegin, newbuffer, 0, bufsize - tokenBegin);
/*  59 */         buffer = newbuffer;
/*     */         
/*  61 */         System.arraycopy(bufline, tokenBegin, newbufline, 0, bufsize - tokenBegin);
/*  62 */         bufline = newbufline;
/*     */         
/*  64 */         System.arraycopy(bufcolumn, tokenBegin, newbufcolumn, 0, bufsize - tokenBegin);
/*  65 */         bufcolumn = newbufcolumn;
/*     */         
/*  67 */         maxNextCharInd = bufpos -= tokenBegin;
/*     */       }
/*     */     
/*  70 */     } catch (Throwable t) {
/*     */       
/*  72 */       throw new Error(t.getMessage());
/*     */     } 
/*     */ 
/*     */     
/*  76 */     bufsize += 2048;
/*  77 */     available = bufsize;
/*  78 */     tokenBegin = 0;
/*     */   }
/*     */ 
/*     */   
/*     */   protected static void FillBuff() throws IOException {
/*  83 */     if (maxNextCharInd == available)
/*     */     {
/*  85 */       if (available == bufsize) {
/*     */         
/*  87 */         if (tokenBegin > 2048) {
/*     */           
/*  89 */           bufpos = maxNextCharInd = 0;
/*  90 */           available = tokenBegin;
/*     */         }
/*  92 */         else if (tokenBegin < 0) {
/*  93 */           bufpos = maxNextCharInd = 0;
/*     */         } else {
/*  95 */           ExpandBuff(false);
/*     */         } 
/*  97 */       } else if (available > tokenBegin) {
/*  98 */         available = bufsize;
/*  99 */       } else if (tokenBegin - available < 2048) {
/* 100 */         ExpandBuff(true);
/*     */       } else {
/* 102 */         available = tokenBegin;
/*     */       } 
/*     */     }
/*     */     
/*     */     try {
/* 107 */       if ((i = inputStream.read(buffer, maxNextCharInd, 
/* 108 */           available - maxNextCharInd)) == -1) {
/*     */         
/* 110 */         inputStream.close();
/* 111 */         throw new IOException();
/*     */       } 
/*     */       
/* 114 */       maxNextCharInd += i;
/*     */       
/*     */       return;
/* 117 */     } catch (IOException e) {
/* 118 */       bufpos--;
/* 119 */       backup(0);
/* 120 */       if (tokenBegin == -1)
/* 121 */         tokenBegin = bufpos; 
/* 122 */       throw e;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static char BeginToken() throws IOException {
/* 128 */     tokenBegin = -1;
/* 129 */     c = readChar();
/* 130 */     tokenBegin = bufpos;
/*     */     
/* 132 */     return c;
/*     */   }
/*     */ 
/*     */   
/*     */   protected static void UpdateLineColumn(char c) {
/* 137 */     column++;
/*     */     
/* 139 */     if (prevCharIsLF) {
/*     */       
/* 141 */       prevCharIsLF = false;
/* 142 */       line += (column = 1);
/*     */     }
/* 144 */     else if (prevCharIsCR) {
/*     */       
/* 146 */       prevCharIsCR = false;
/* 147 */       if (c == '\n') {
/*     */         
/* 149 */         prevCharIsLF = true;
/*     */       } else {
/*     */         
/* 152 */         line += (column = 1);
/*     */       } 
/*     */     } 
/* 155 */     switch (c) {
/*     */       
/*     */       case '\r':
/* 158 */         prevCharIsCR = true;
/*     */         break;
/*     */       case '\n':
/* 161 */         prevCharIsLF = true;
/*     */         break;
/*     */       case '\t':
/* 164 */         column--;
/* 165 */         column += 8 - (column & 0x7);
/*     */         break;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 171 */     bufline[bufpos] = line;
/* 172 */     bufcolumn[bufpos] = column;
/*     */   }
/*     */ 
/*     */   
/*     */   public static char readChar() throws IOException {
/* 177 */     if (inBuf > 0) {
/*     */       
/* 179 */       inBuf--;
/*     */       
/* 181 */       if (++bufpos == bufsize) {
/* 182 */         bufpos = 0;
/*     */       }
/* 184 */       return buffer[bufpos];
/*     */     } 
/*     */     
/* 187 */     if (++bufpos >= maxNextCharInd) {
/* 188 */       FillBuff();
/*     */     }
/* 190 */     c = buffer[bufpos];
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
/* 202 */   public static int getColumn() { return bufcolumn[bufpos]; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 211 */   public static int getLine() { return bufline[bufpos]; }
/*     */ 
/*     */ 
/*     */   
/* 215 */   public static int getEndColumn() { return bufcolumn[bufpos]; }
/*     */ 
/*     */ 
/*     */   
/* 219 */   public static int getEndLine() { return bufline[bufpos]; }
/*     */ 
/*     */ 
/*     */   
/* 223 */   public static int getBeginColumn() { return bufcolumn[tokenBegin]; }
/*     */ 
/*     */ 
/*     */   
/* 227 */   public static int getBeginLine() { return bufline[tokenBegin]; }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void backup(int amount) {
/* 232 */     inBuf += amount;
/* 233 */     if (bufpos -= amount < 0) {
/* 234 */       bufpos += bufsize;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public SimpleCharStream(Reader dstream, int startline, int startcolumn, int buffersize) {
/* 240 */     if (inputStream != null) {
/* 241 */       throw new Error("\n   ERROR: Second call to the constructor of a static SimpleCharStream.  You must\n       either use ReInit() or set the JavaCC option STATIC to false\n       during the generation of this class.");
/*     */     }
/*     */     
/* 244 */     inputStream = dstream;
/* 245 */     line = startline;
/* 246 */     column = startcolumn - 1;
/*     */     
/* 248 */     available = bufsize = buffersize;
/* 249 */     buffer = new char[buffersize];
/* 250 */     bufline = new int[buffersize];
/* 251 */     bufcolumn = new int[buffersize];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 257 */   public SimpleCharStream(Reader dstream, int startline, int startcolumn) { this(dstream, startline, startcolumn, 4096); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 262 */   public SimpleCharStream(Reader dstream) { this(dstream, 1, 1, 4096); }
/*     */ 
/*     */ 
/*     */   
/*     */   public void ReInit(Reader dstream, int startline, int startcolumn, int buffersize) {
/* 267 */     inputStream = dstream;
/* 268 */     line = startline;
/* 269 */     column = startcolumn - 1;
/*     */     
/* 271 */     if (buffer == null || buffersize != buffer.length) {
/*     */       
/* 273 */       available = bufsize = buffersize;
/* 274 */       buffer = new char[buffersize];
/* 275 */       bufline = new int[buffersize];
/* 276 */       bufcolumn = new int[buffersize];
/*     */     } 
/* 278 */     prevCharIsLF = prevCharIsCR = false;
/* 279 */     tokenBegin = inBuf = maxNextCharInd = 0;
/* 280 */     bufpos = -1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 286 */   public void ReInit(Reader dstream, int startline, int startcolumn) { ReInit(dstream, startline, startcolumn, 4096); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 291 */   public void ReInit(Reader dstream) { ReInit(dstream, 1, 1, 4096); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 296 */   public SimpleCharStream(InputStream dstream, int startline, int startcolumn, int buffersize) { this(new InputStreamReader(dstream), startline, startcolumn, 4096); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 302 */   public SimpleCharStream(InputStream dstream, int startline, int startcolumn) { this(dstream, startline, startcolumn, 4096); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 307 */   public SimpleCharStream(InputStream dstream) { this(dstream, 1, 1, 4096); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 313 */   public void ReInit(InputStream dstream, int startline, int startcolumn, int buffersize) { ReInit(new InputStreamReader(dstream), startline, startcolumn, 4096); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 318 */   public void ReInit(InputStream dstream) { ReInit(dstream, 1, 1, 4096); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 323 */   public void ReInit(InputStream dstream, int startline, int startcolumn) { ReInit(dstream, startline, startcolumn, 4096); }
/*     */ 
/*     */   
/*     */   public static String GetImage() {
/* 327 */     if (bufpos >= tokenBegin) {
/* 328 */       return new String(buffer, tokenBegin, bufpos - tokenBegin + 1);
/*     */     }
/* 330 */     return String.valueOf(new String(buffer, tokenBegin, bufsize - tokenBegin)) + 
/* 331 */       new String(buffer, 0, bufpos + 1);
/*     */   }
/*     */ 
/*     */   
/*     */   public static char[] GetSuffix(int len) {
/* 336 */     char[] ret = new char[len];
/*     */     
/* 338 */     if (bufpos + 1 >= len) {
/* 339 */       System.arraycopy(buffer, bufpos - len + 1, ret, 0, len);
/*     */     } else {
/*     */       
/* 342 */       System.arraycopy(buffer, bufsize - len - bufpos - 1, ret, 0, 
/* 343 */           len - bufpos - 1);
/* 344 */       System.arraycopy(buffer, 0, ret, len - bufpos - 1, bufpos + 1);
/*     */     } 
/*     */     
/* 347 */     return ret;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void Done() throws IOException {
/* 352 */     buffer = null;
/* 353 */     bufline = null;
/* 354 */     bufcolumn = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void adjustBeginLineColumn(int newLine, int newCol) {
/* 362 */     int len, start = tokenBegin;
/*     */ 
/*     */     
/* 365 */     if (bufpos >= tokenBegin) {
/*     */       
/* 367 */       len = bufpos - tokenBegin + inBuf + 1;
/*     */     }
/*     */     else {
/*     */       
/* 371 */       len = bufsize - tokenBegin + bufpos + 1 + inBuf;
/*     */     } 
/*     */     
/* 374 */     int i = 0, j = 0, k = 0;
/* 375 */     int nextColDiff = 0, columnDiff = 0;
/*     */     
/* 377 */     while (i < len && 
/* 378 */       bufline[j = start % bufsize] == bufline[k = ++start % bufsize]) {
/*     */       
/* 380 */       bufline[j] = newLine;
/* 381 */       nextColDiff = columnDiff + bufcolumn[k] - bufcolumn[j];
/* 382 */       bufcolumn[j] = newCol + columnDiff;
/* 383 */       columnDiff = nextColDiff;
/* 384 */       i++;
/*     */     } 
/*     */     
/* 387 */     if (i < len) {
/*     */       
/* 389 */       bufline[j] = newLine++;
/* 390 */       bufcolumn[j] = newCol + columnDiff;
/*     */       
/* 392 */       while (i++ < len) {
/*     */         
/* 394 */         if (bufline[j = start % bufsize] != bufline[++start % bufsize]) {
/* 395 */           bufline[j] = newLine++; continue;
/*     */         } 
/* 397 */         bufline[j] = newLine;
/*     */       } 
/*     */     } 
/*     */     
/* 401 */     line = bufline[j];
/* 402 */     column = bufcolumn[j];
/*     */   }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\importer\cdif\SimpleCharStream.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */