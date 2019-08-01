/*     */ package lrg.memoria.importer.mcc.javacc;
/*     */ 
/*     */ public class TablesParserTokenManager implements TablesParserConstants {
/*     */   public PrintStream debugStream;
/*     */   
/*     */   static void CommonTokenAction(Token t) {
/*   7 */     if (t.image.equals("\"\"")) {
/*   8 */       t.image = "";
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  14 */   public void setDebugStream(PrintStream ds) { this.debugStream = ds; }
/*     */ 
/*     */   
/*     */   private final int jjStopStringLiteralDfa_0(int pos, long active0) {
/*  18 */     switch (pos) {
/*     */       case 0:
/*  20 */         if ((active0 & 0x2L) != 0L)
/*  21 */           return 1; 
/*  22 */         if ((active0 & 0x1FE0L) != 0L) {
/*  23 */           this.jjmatchedKind = 14;
/*  24 */           return 1;
/*     */         } 
/*  26 */         return -1;
/*     */       case 1:
/*  28 */         if ((active0 & 0x1FE0L) != 0L) {
/*  29 */           this.jjmatchedKind = 14;
/*  30 */           this.jjmatchedPos = 1;
/*  31 */           return 1;
/*     */         } 
/*  33 */         return -1;
/*     */       case 2:
/*  35 */         if ((active0 & 0x1FE0L) != 0L) {
/*  36 */           this.jjmatchedKind = 14;
/*  37 */           this.jjmatchedPos = 2;
/*  38 */           return 1;
/*     */         } 
/*  40 */         return -1;
/*     */       case 3:
/*  42 */         if ((active0 & 0x100L) != 0L)
/*  43 */           return 1; 
/*  44 */         if ((active0 & 0x1EE0L) != 0L) {
/*  45 */           this.jjmatchedKind = 14;
/*  46 */           this.jjmatchedPos = 3;
/*  47 */           return 1;
/*     */         } 
/*  49 */         return -1;
/*     */       case 4:
/*  51 */         if ((active0 & 0x1EE0L) != 0L) {
/*  52 */           this.jjmatchedKind = 14;
/*  53 */           this.jjmatchedPos = 4;
/*  54 */           return 1;
/*     */         } 
/*  56 */         return -1;
/*     */       case 5:
/*  58 */         if ((active0 & 0x1EE0L) != 0L) {
/*  59 */           this.jjmatchedKind = 14;
/*  60 */           this.jjmatchedPos = 5;
/*  61 */           return 1;
/*     */         } 
/*  63 */         return -1;
/*     */       case 6:
/*  65 */         if ((active0 & 0x1EC0L) != 0L) {
/*  66 */           this.jjmatchedKind = 14;
/*  67 */           this.jjmatchedPos = 6;
/*  68 */           return 1;
/*     */         } 
/*  70 */         if ((active0 & 0x20L) != 0L)
/*  71 */           return 1; 
/*  72 */         return -1;
/*     */       case 7:
/*  74 */         if ((active0 & 0x1E40L) != 0L) {
/*  75 */           this.jjmatchedKind = 14;
/*  76 */           this.jjmatchedPos = 7;
/*  77 */           return 1;
/*     */         } 
/*  79 */         if ((active0 & 0x80L) != 0L)
/*  80 */           return 1; 
/*  81 */         return -1;
/*     */       case 8:
/*  83 */         if ((active0 & 0xA40L) != 0L) {
/*  84 */           this.jjmatchedKind = 14;
/*  85 */           this.jjmatchedPos = 8;
/*  86 */           return 1;
/*     */         } 
/*  88 */         if ((active0 & 0x1400L) != 0L)
/*  89 */           return 1; 
/*  90 */         return -1;
/*     */       case 9:
/*  92 */         if ((active0 & 0x840L) != 0L) {
/*  93 */           this.jjmatchedKind = 14;
/*  94 */           this.jjmatchedPos = 9;
/*  95 */           return 1;
/*     */         } 
/*  97 */         if ((active0 & 0x200L) != 0L)
/*  98 */           return 1; 
/*  99 */         return -1;
/*     */       case 10:
/* 101 */         if ((active0 & 0x840L) != 0L) {
/* 102 */           this.jjmatchedKind = 14;
/* 103 */           this.jjmatchedPos = 10;
/* 104 */           return 1;
/*     */         } 
/* 106 */         return -1;
/*     */       case 11:
/* 108 */         if ((active0 & 0x840L) != 0L) {
/* 109 */           this.jjmatchedKind = 14;
/* 110 */           this.jjmatchedPos = 11;
/* 111 */           return 1;
/*     */         } 
/* 113 */         return -1;
/*     */       case 12:
/* 115 */         if ((active0 & 0x840L) != 0L) {
/* 116 */           this.jjmatchedKind = 14;
/* 117 */           this.jjmatchedPos = 12;
/* 118 */           return 1;
/*     */         } 
/* 120 */         return -1;
/*     */       case 13:
/* 122 */         if ((active0 & 0x840L) != 0L) {
/* 123 */           this.jjmatchedKind = 14;
/* 124 */           this.jjmatchedPos = 13;
/* 125 */           return 1;
/*     */         } 
/* 127 */         return -1;
/*     */       case 14:
/* 129 */         if ((active0 & 0x800L) != 0L) {
/* 130 */           this.jjmatchedKind = 14;
/* 131 */           this.jjmatchedPos = 14;
/* 132 */           return 1;
/*     */         } 
/* 134 */         if ((active0 & 0x40L) != 0L)
/* 135 */           return 1; 
/* 136 */         return -1;
/*     */     } 
/* 138 */     return -1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 143 */   private final int jjStartNfa_0(int pos, long active0) { return jjMoveNfa_0(jjStopStringLiteralDfa_0(pos, active0), pos + 1); }
/*     */ 
/*     */   
/*     */   private final int jjStopAtPos(int pos, int kind) {
/* 147 */     this.jjmatchedKind = kind;
/* 148 */     this.jjmatchedPos = pos;
/* 149 */     return pos + 1;
/*     */   }
/*     */   
/*     */   private final int jjStartNfaWithStates_0(int pos, int kind, int state) {
/* 153 */     this.jjmatchedKind = kind;
/* 154 */     this.jjmatchedPos = pos;
/*     */     try {
/* 156 */       this.curChar = this.input_stream.readChar();
/* 157 */     } catch (IOException e) {
/* 158 */       return pos + 1;
/*     */     } 
/* 160 */     return jjMoveNfa_0(state, pos + 1);
/*     */   }
/*     */   
/*     */   private final int jjMoveStringLiteralDfa0_0() {
/* 164 */     switch (this.curChar) {
/*     */       case '\n':
/* 166 */         return jjStopAtPos(0, 4);
/*     */       case '"':
/* 168 */         return jjStartNfaWithStates_0(0, 1, 1);
/*     */       case '<':
/* 170 */         return jjMoveStringLiteralDfa1_0(7904L);
/*     */       case 'N':
/* 172 */         return jjMoveStringLiteralDfa1_0(256L);
/*     */     } 
/* 174 */     return jjMoveNfa_0(2, 0);
/*     */   }
/*     */ 
/*     */   
/*     */   private final int jjMoveStringLiteralDfa1_0(long active0) {
/*     */     try {
/* 180 */       this.curChar = this.input_stream.readChar();
/* 181 */     } catch (IOException e) {
/* 182 */       jjStopStringLiteralDfa_0(0, active0);
/* 183 */       return 1;
/*     */     } 
/* 185 */     switch (this.curChar) {
/*     */       case 'E':
/* 187 */         return jjMoveStringLiteralDfa2_0(active0, 32L);
/*     */       case 'I':
/* 189 */         return jjMoveStringLiteralDfa2_0(active0, 2048L);
/*     */       case 'N':
/* 191 */         return jjMoveStringLiteralDfa2_0(active0, 1664L);
/*     */       case 'O':
/* 193 */         return jjMoveStringLiteralDfa2_0(active0, 64L);
/*     */       case 'U':
/* 195 */         return jjMoveStringLiteralDfa2_0(active0, 4352L);
/*     */     } 
/*     */ 
/*     */     
/* 199 */     return jjStartNfa_0(0, active0);
/*     */   }
/*     */   
/*     */   private final int jjMoveStringLiteralDfa2_0(long old0, long active0) {
/* 203 */     if ((active0 &= old0) == 0L)
/* 204 */       return jjStartNfa_0(0, old0); 
/*     */     try {
/* 206 */       this.curChar = this.input_stream.readChar();
/* 207 */     } catch (IOException e) {
/* 208 */       jjStopStringLiteralDfa_0(1, active0);
/* 209 */       return 2;
/*     */     } 
/* 211 */     switch (this.curChar) {
/*     */       case 'L':
/* 213 */         return jjMoveStringLiteralDfa3_0(active0, 256L);
/*     */       case 'N':
/* 215 */         return jjMoveStringLiteralDfa3_0(active0, 6208L);
/*     */       case 'O':
/* 217 */         return jjMoveStringLiteralDfa3_0(active0, 1664L);
/*     */       case 'R':
/* 219 */         return jjMoveStringLiteralDfa3_0(active0, 32L);
/*     */     } 
/*     */ 
/*     */     
/* 223 */     return jjStartNfa_0(1, active0);
/*     */   }
/*     */   
/*     */   private final int jjMoveStringLiteralDfa3_0(long old0, long active0) {
/* 227 */     if ((active0 &= old0) == 0L)
/* 228 */       return jjStartNfa_0(1, old0); 
/*     */     try {
/* 230 */       this.curChar = this.input_stream.readChar();
/* 231 */     } catch (IOException e) {
/* 232 */       jjStopStringLiteralDfa_0(2, active0);
/* 233 */       return 3;
/*     */     } 
/* 235 */     switch (this.curChar) {
/*     */       case 'I':
/* 237 */         return jjMoveStringLiteralDfa4_0(active0, 2048L);
/*     */       case 'K':
/* 239 */         return jjMoveStringLiteralDfa4_0(active0, 4096L);
/*     */       case 'L':
/* 241 */         if ((active0 & 0x100L) != 0L)
/* 242 */           return jjStartNfaWithStates_0(3, 8, 1); 
/* 243 */         return jjMoveStringLiteralDfa4_0(active0, 64L);
/*     */       case 'R':
/* 245 */         return jjMoveStringLiteralDfa4_0(active0, 32L);
/*     */       case 'T':
/* 247 */         return jjMoveStringLiteralDfa4_0(active0, 512L);
/*     */       case '_':
/* 249 */         return jjMoveStringLiteralDfa4_0(active0, 1152L);
/*     */     } 
/*     */ 
/*     */     
/* 253 */     return jjStartNfa_0(2, active0);
/*     */   }
/*     */   
/*     */   private final int jjMoveStringLiteralDfa4_0(long old0, long active0) {
/* 257 */     if ((active0 &= old0) == 0L)
/* 258 */       return jjStartNfa_0(2, old0); 
/*     */     try {
/* 260 */       this.curChar = this.input_stream.readChar();
/* 261 */     } catch (IOException e) {
/* 262 */       jjStopStringLiteralDfa_0(3, active0);
/* 263 */       return 4;
/*     */     } 
/* 265 */     switch (this.curChar) {
/*     */       case 'N':
/* 267 */         return jjMoveStringLiteralDfa5_0(active0, 5120L);
/*     */       case 'O':
/* 269 */         return jjMoveStringLiteralDfa5_0(active0, 160L);
/*     */       case 'T':
/* 271 */         return jjMoveStringLiteralDfa5_0(active0, 2048L);
/*     */       case 'Y':
/* 273 */         return jjMoveStringLiteralDfa5_0(active0, 64L);
/*     */       case '_':
/* 275 */         return jjMoveStringLiteralDfa5_0(active0, 512L);
/*     */     } 
/*     */ 
/*     */     
/* 279 */     return jjStartNfa_0(3, active0);
/*     */   }
/*     */   
/*     */   private final int jjMoveStringLiteralDfa5_0(long old0, long active0) {
/* 283 */     if ((active0 &= old0) == 0L)
/* 284 */       return jjStartNfa_0(3, old0); 
/*     */     try {
/* 286 */       this.curChar = this.input_stream.readChar();
/* 287 */     } catch (IOException e) {
/* 288 */       jjStopStringLiteralDfa_0(4, active0);
/* 289 */       return 5;
/*     */     } 
/* 291 */     switch (this.curChar) {
/*     */       case 'A':
/* 293 */         return jjMoveStringLiteralDfa6_0(active0, 1024L);
/*     */       case 'I':
/* 295 */         return jjMoveStringLiteralDfa6_0(active0, 512L);
/*     */       case 'N':
/* 297 */         return jjMoveStringLiteralDfa6_0(active0, 128L);
/*     */       case 'O':
/* 299 */         return jjMoveStringLiteralDfa6_0(active0, 4096L);
/*     */       case 'R':
/* 301 */         return jjMoveStringLiteralDfa6_0(active0, 32L);
/*     */       case '_':
/* 303 */         return jjMoveStringLiteralDfa6_0(active0, 2112L);
/*     */     } 
/*     */ 
/*     */     
/* 307 */     return jjStartNfa_0(4, active0);
/*     */   }
/*     */   
/*     */   private final int jjMoveStringLiteralDfa6_0(long old0, long active0) {
/* 311 */     if ((active0 &= old0) == 0L)
/* 312 */       return jjStartNfa_0(4, old0); 
/*     */     try {
/* 314 */       this.curChar = this.input_stream.readChar();
/* 315 */     } catch (IOException e) {
/* 316 */       jjStopStringLiteralDfa_0(5, active0);
/* 317 */       return 6;
/*     */     } 
/* 319 */     switch (this.curChar) {
/*     */       case '>':
/* 321 */         if ((active0 & 0x20L) != 0L)
/* 322 */           return jjStartNfaWithStates_0(6, 5, 1); 
/*     */         break;
/*     */       case 'D':
/* 325 */         return jjMoveStringLiteralDfa7_0(active0, 64L);
/*     */       case 'E':
/* 327 */         return jjMoveStringLiteralDfa7_0(active0, 128L);
/*     */       case 'M':
/* 329 */         return jjMoveStringLiteralDfa7_0(active0, 1024L);
/*     */       case 'N':
/* 331 */         return jjMoveStringLiteralDfa7_0(active0, 2560L);
/*     */       case 'W':
/* 333 */         return jjMoveStringLiteralDfa7_0(active0, 4096L);
/*     */     } 
/*     */ 
/*     */     
/* 337 */     return jjStartNfa_0(5, active0);
/*     */   }
/*     */   
/*     */   private final int jjMoveStringLiteralDfa7_0(long old0, long active0) {
/* 341 */     if ((active0 &= old0) == 0L)
/* 342 */       return jjStartNfa_0(5, old0); 
/*     */     try {
/* 344 */       this.curChar = this.input_stream.readChar();
/* 345 */     } catch (IOException e) {
/* 346 */       jjStopStringLiteralDfa_0(6, active0);
/* 347 */       return 7;
/*     */     } 
/* 349 */     switch (this.curChar) {
/*     */       case '>':
/* 351 */         if ((active0 & 0x80L) != 0L)
/* 352 */           return jjStartNfaWithStates_0(7, 7, 1); 
/*     */         break;
/*     */       case 'E':
/* 355 */         return jjMoveStringLiteralDfa8_0(active0, 1088L);
/*     */       case 'I':
/* 357 */         return jjMoveStringLiteralDfa8_0(active0, 512L);
/*     */       case 'N':
/* 359 */         return jjMoveStringLiteralDfa8_0(active0, 4096L);
/*     */       case 'U':
/* 361 */         return jjMoveStringLiteralDfa8_0(active0, 2048L);
/*     */     } 
/*     */ 
/*     */     
/* 365 */     return jjStartNfa_0(6, active0);
/*     */   }
/*     */   
/*     */   private final int jjMoveStringLiteralDfa8_0(long old0, long active0) {
/* 369 */     if ((active0 &= old0) == 0L)
/* 370 */       return jjStartNfa_0(6, old0); 
/*     */     try {
/* 372 */       this.curChar = this.input_stream.readChar();
/* 373 */     } catch (IOException e) {
/* 374 */       jjStopStringLiteralDfa_0(7, active0);
/* 375 */       return 8;
/*     */     } 
/* 377 */     switch (this.curChar) {
/*     */       case '>':
/* 379 */         if ((active0 & 0x400L) != 0L)
/* 380 */           return jjStartNfaWithStates_0(8, 10, 1); 
/* 381 */         if ((active0 & 0x1000L) != 0L)
/* 382 */           return jjStartNfaWithStates_0(8, 12, 1); 
/*     */         break;
/*     */       case 'C':
/* 385 */         return jjMoveStringLiteralDfa9_0(active0, 64L);
/*     */       case 'L':
/* 387 */         return jjMoveStringLiteralDfa9_0(active0, 2048L);
/*     */       case 'T':
/* 389 */         return jjMoveStringLiteralDfa9_0(active0, 512L);
/*     */     } 
/*     */ 
/*     */     
/* 393 */     return jjStartNfa_0(7, active0);
/*     */   }
/*     */   
/*     */   private final int jjMoveStringLiteralDfa9_0(long old0, long active0) {
/* 397 */     if ((active0 &= old0) == 0L)
/* 398 */       return jjStartNfa_0(7, old0); 
/*     */     try {
/* 400 */       this.curChar = this.input_stream.readChar();
/* 401 */     } catch (IOException e) {
/* 402 */       jjStopStringLiteralDfa_0(8, active0);
/* 403 */       return 9;
/*     */     } 
/* 405 */     switch (this.curChar) {
/*     */       case '>':
/* 407 */         if ((active0 & 0x200L) != 0L)
/* 408 */           return jjStartNfaWithStates_0(9, 9, 1); 
/*     */         break;
/*     */       case 'L':
/* 411 */         return jjMoveStringLiteralDfa10_0(active0, 2112L);
/*     */     } 
/*     */ 
/*     */     
/* 415 */     return jjStartNfa_0(8, active0);
/*     */   }
/*     */   
/*     */   private final int jjMoveStringLiteralDfa10_0(long old0, long active0) {
/* 419 */     if ((active0 &= old0) == 0L)
/* 420 */       return jjStartNfa_0(8, old0); 
/*     */     try {
/* 422 */       this.curChar = this.input_stream.readChar();
/* 423 */     } catch (IOException e) {
/* 424 */       jjStopStringLiteralDfa_0(9, active0);
/* 425 */       return 10;
/*     */     } 
/* 427 */     switch (this.curChar) {
/*     */       case 'A':
/* 429 */         return jjMoveStringLiteralDfa11_0(active0, 64L);
/*     */       case '_':
/* 431 */         return jjMoveStringLiteralDfa11_0(active0, 2048L);
/*     */     } 
/*     */ 
/*     */     
/* 435 */     return jjStartNfa_0(9, active0);
/*     */   }
/*     */   
/*     */   private final int jjMoveStringLiteralDfa11_0(long old0, long active0) {
/* 439 */     if ((active0 &= old0) == 0L)
/* 440 */       return jjStartNfa_0(9, old0); 
/*     */     try {
/* 442 */       this.curChar = this.input_stream.readChar();
/* 443 */     } catch (IOException e) {
/* 444 */       jjStopStringLiteralDfa_0(10, active0);
/* 445 */       return 11;
/*     */     } 
/* 447 */     switch (this.curChar) {
/*     */       case 'B':
/* 449 */         return jjMoveStringLiteralDfa12_0(active0, 2048L);
/*     */       case 'R':
/* 451 */         return jjMoveStringLiteralDfa12_0(active0, 64L);
/*     */     } 
/*     */ 
/*     */     
/* 455 */     return jjStartNfa_0(10, active0);
/*     */   }
/*     */   
/*     */   private final int jjMoveStringLiteralDfa12_0(long old0, long active0) {
/* 459 */     if ((active0 &= old0) == 0L)
/* 460 */       return jjStartNfa_0(10, old0); 
/*     */     try {
/* 462 */       this.curChar = this.input_stream.readChar();
/* 463 */     } catch (IOException e) {
/* 464 */       jjStopStringLiteralDfa_0(11, active0);
/* 465 */       return 12;
/*     */     } 
/* 467 */     switch (this.curChar) {
/*     */       case 'E':
/* 469 */         return jjMoveStringLiteralDfa13_0(active0, 64L);
/*     */       case 'O':
/* 471 */         return jjMoveStringLiteralDfa13_0(active0, 2048L);
/*     */     } 
/*     */ 
/*     */     
/* 475 */     return jjStartNfa_0(11, active0);
/*     */   }
/*     */   
/*     */   private final int jjMoveStringLiteralDfa13_0(long old0, long active0) {
/* 479 */     if ((active0 &= old0) == 0L)
/* 480 */       return jjStartNfa_0(11, old0); 
/*     */     try {
/* 482 */       this.curChar = this.input_stream.readChar();
/* 483 */     } catch (IOException e) {
/* 484 */       jjStopStringLiteralDfa_0(12, active0);
/* 485 */       return 13;
/*     */     } 
/* 487 */     switch (this.curChar) {
/*     */       case 'D':
/* 489 */         return jjMoveStringLiteralDfa14_0(active0, 2112L);
/*     */     } 
/*     */ 
/*     */     
/* 493 */     return jjStartNfa_0(12, active0);
/*     */   }
/*     */   
/*     */   private final int jjMoveStringLiteralDfa14_0(long old0, long active0) {
/* 497 */     if ((active0 &= old0) == 0L)
/* 498 */       return jjStartNfa_0(12, old0); 
/*     */     try {
/* 500 */       this.curChar = this.input_stream.readChar();
/* 501 */     } catch (IOException e) {
/* 502 */       jjStopStringLiteralDfa_0(13, active0);
/* 503 */       return 14;
/*     */     } 
/* 505 */     switch (this.curChar) {
/*     */       case '>':
/* 507 */         if ((active0 & 0x40L) != 0L)
/* 508 */           return jjStartNfaWithStates_0(14, 6, 1); 
/*     */         break;
/*     */       case 'Y':
/* 511 */         return jjMoveStringLiteralDfa15_0(active0, 2048L);
/*     */     } 
/*     */ 
/*     */     
/* 515 */     return jjStartNfa_0(13, active0);
/*     */   }
/*     */   
/*     */   private final int jjMoveStringLiteralDfa15_0(long old0, long active0) {
/* 519 */     if ((active0 &= old0) == 0L)
/* 520 */       return jjStartNfa_0(13, old0); 
/*     */     try {
/* 522 */       this.curChar = this.input_stream.readChar();
/* 523 */     } catch (IOException e) {
/* 524 */       jjStopStringLiteralDfa_0(14, active0);
/* 525 */       return 15;
/*     */     } 
/* 527 */     switch (this.curChar) {
/*     */       case '>':
/* 529 */         if ((active0 & 0x800L) != 0L) {
/* 530 */           return jjStartNfaWithStates_0(15, 11, 1);
/*     */         }
/*     */         break;
/*     */     } 
/*     */     
/* 535 */     return jjStartNfa_0(14, active0);
/*     */   }
/*     */   
/*     */   private final void jjCheckNAdd(int state) {
/* 539 */     if (this.jjrounds[state] != this.jjround) {
/* 540 */       this.jjstateSet[this.jjnewStateCnt++] = state;
/* 541 */       this.jjrounds[state] = this.jjround;
/*     */     } 
/*     */   }
/*     */   
/*     */   private final void jjAddStates(int start, int end) {
/*     */     do {
/* 547 */       this.jjstateSet[this.jjnewStateCnt++] = jjnextStates[start];
/* 548 */     } while (start++ != end);
/*     */   }
/*     */   
/*     */   private final void jjCheckNAddTwoStates(int state1, int state2) {
/* 552 */     jjCheckNAdd(state1);
/* 553 */     jjCheckNAdd(state2);
/*     */   }
/*     */   
/*     */   private final void jjCheckNAddStates(int start, int end) {
/*     */     do {
/* 558 */       jjCheckNAdd(jjnextStates[start]);
/* 559 */     } while (start++ != end);
/*     */   }
/*     */   
/*     */   private final void jjCheckNAddStates(int start) {
/* 563 */     jjCheckNAdd(jjnextStates[start]);
/* 564 */     jjCheckNAdd(jjnextStates[start + 1]);
/*     */   }
/*     */ 
/*     */   
/* 568 */   static final long[] jjbitVec0 = { 0, 0, -1L, -1L };
/*     */ 
/*     */ 
/*     */   
/*     */   private final int jjMoveNfa_0(int startState, int curPos) {
/* 573 */     int startsAt = 0;
/* 574 */     this.jjnewStateCnt = 2;
/* 575 */     int i = 1;
/* 576 */     this.jjstateSet[0] = startState;
/* 577 */     int kind = Integer.MAX_VALUE;
/*     */     while (true) {
/* 579 */       if (++this.jjround == Integer.MAX_VALUE)
/* 580 */         ReInitRounds(); 
/* 581 */       if (this.curChar < '@') {
/* 582 */         long l = 1L << this.curChar;
/*     */         do {
/* 584 */           switch (this.jjstateSet[--i]) {
/*     */             case 2:
/* 586 */               if ((0xFFFFFFFFFFFFD9FFL & l) != 0L) {
/* 587 */                 if (kind > 14)
/* 588 */                   kind = 14; 
/* 589 */                 jjCheckNAdd(1);
/*     */               } 
/* 591 */               if ((0x3FF000000000000L & l) != 0L) {
/* 592 */                 if (kind > 13)
/* 593 */                   kind = 13; 
/* 594 */                 jjCheckNAdd(0);
/*     */               } 
/*     */               break;
/*     */             case 0:
/* 598 */               if ((0x3FF000000000000L & l) == 0L)
/*     */                 break; 
/* 600 */               if (kind > 13)
/* 601 */                 kind = 13; 
/* 602 */               jjCheckNAdd(0);
/*     */               break;
/*     */             case 1:
/* 605 */               if ((0xFFFFFFFFFFFFD9FFL & l) == 0L)
/*     */                 break; 
/* 607 */               if (kind > 14)
/* 608 */                 kind = 14; 
/* 609 */               jjCheckNAdd(1);
/*     */               break;
/*     */           } 
/*     */ 
/*     */         
/* 614 */         } while (i != startsAt);
/* 615 */       } else if (this.curChar < '') {
/* 616 */         long l = 1L << (this.curChar & 0x3F);
/*     */         do {
/* 618 */           switch (this.jjstateSet[--i]) {
/*     */             case 1:
/*     */             case 2:
/* 621 */               kind = 14;
/* 622 */               jjCheckNAdd(1);
/*     */               break;
/*     */           } 
/*     */ 
/*     */         
/* 627 */         } while (i != startsAt);
/*     */       } else {
/* 629 */         int i2 = (this.curChar & 0xFF) >> '\006';
/* 630 */         long l2 = 1L << (this.curChar & 0x3F);
/*     */         do {
/* 632 */           switch (this.jjstateSet[--i]) {
/*     */             case 1:
/*     */             case 2:
/* 635 */               if ((jjbitVec0[i2] & l2) == 0L)
/*     */                 break; 
/* 637 */               if (kind > 14)
/* 638 */                 kind = 14; 
/* 639 */               jjCheckNAdd(1);
/*     */               break;
/*     */           } 
/*     */ 
/*     */         
/* 644 */         } while (i != startsAt);
/*     */       } 
/* 646 */       if (kind != Integer.MAX_VALUE) {
/* 647 */         this.jjmatchedKind = kind;
/* 648 */         this.jjmatchedPos = curPos;
/* 649 */         kind = Integer.MAX_VALUE;
/*     */       } 
/* 651 */       curPos++;
/* 652 */       if ((i = this.jjnewStateCnt) == (startsAt = 2 - (this.jjnewStateCnt = startsAt)))
/* 653 */         return curPos; 
/*     */       try {
/* 655 */         this.curChar = this.input_stream.readChar();
/* 656 */       } catch (IOException e) {
/* 657 */         return curPos;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/* 662 */   static final int[] jjnextStates = new int[0];
/*     */ 
/*     */   
/* 665 */   public static final String[] jjstrLiteralImages = { "", "\n", "<ERROR>", 
/* 666 */       "<ONLY_DECLARED>", "<NO_ONE>", "NULL", 
/* 667 */       "<NOT_INIT>", "<NO_NAME>", 
/* 668 */       "<INIT_NULL_BODY>", "<UNKNOWN>" };
/*     */   
/* 670 */   public static final String[] lexStateNames = { "DEFAULT" };
/*     */ 
/*     */   
/* 673 */   static final long[] jjtoToken = { 32753L }; protected SimpleCharStream input_stream; private final int[] jjrounds; private final int[] jjstateSet;
/*     */   protected char curChar;
/*     */   int curLexState;
/* 676 */   static final long[] jjtoSkip = { 14L }; int defaultLexState; int jjnewStateCnt;
/*     */   public TablesParserTokenManager(SimpleCharStream stream) {
/*     */     this.debugStream = System.out;
/* 679 */     this.jjrounds = new int[2];
/* 680 */     this.jjstateSet = new int[4];
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
/* 732 */     this.curLexState = 0;
/* 733 */     this.defaultLexState = 0;
/*     */     this.input_stream = stream;
/*     */   } int jjround; int jjmatchedPos; int jjmatchedKind;
/*     */   public TablesParserTokenManager(SimpleCharStream stream, int lexState) {
/*     */     this(stream);
/*     */     SwitchTo(lexState);
/*     */   }
/*     */   public Token getNextToken() {
/* 741 */     Token specialToken = null;
/*     */     
/* 743 */     int curPos = 0;
/*     */ 
/*     */     
/*     */     while (true) {
/*     */       try {
/* 748 */         this.curChar = this.input_stream.BeginToken();
/* 749 */       } catch (IOException e) {
/* 750 */         this.jjmatchedKind = 0;
/* 751 */         Token matchedToken = jjFillToken();
/* 752 */         CommonTokenAction(matchedToken);
/* 753 */         return matchedToken;
/*     */       } 
/*     */       
/*     */       try {
/* 757 */         this.input_stream.backup(0);
/* 758 */         while (this.curChar <= '\r' && (0x2200L & 1L << this.curChar) != 0L)
/* 759 */           this.curChar = this.input_stream.BeginToken(); 
/* 760 */       } catch (IOException e1) {
/*     */         continue;
/*     */       } 
/* 763 */       this.jjmatchedKind = Integer.MAX_VALUE;
/* 764 */       this.jjmatchedPos = 0;
/* 765 */       curPos = jjMoveStringLiteralDfa0_0();
/* 766 */       if (this.jjmatchedKind != Integer.MAX_VALUE) {
/* 767 */         if (this.jjmatchedPos + 1 < curPos)
/* 768 */           this.input_stream.backup(curPos - this.jjmatchedPos - 1); 
/* 769 */         if ((jjtoToken[this.jjmatchedKind >> 6] & 1L << (this.jjmatchedKind & 0x3F)) != 0L) {
/* 770 */           Token matchedToken = jjFillToken();
/* 771 */           CommonTokenAction(matchedToken);
/* 772 */           return matchedToken;
/*     */         }  continue;
/*     */       } 
/*     */       break;
/*     */     } 
/* 777 */     int error_line = this.input_stream.getEndLine();
/* 778 */     int error_column = this.input_stream.getEndColumn();
/* 779 */     String error_after = null;
/* 780 */     boolean EOFSeen = false;
/*     */     try {
/* 782 */       this.input_stream.readChar();
/* 783 */       this.input_stream.backup(1);
/* 784 */     } catch (IOException e1) {
/* 785 */       EOFSeen = true;
/* 786 */       error_after = (curPos <= 1) ? "" : this.input_stream.GetImage();
/* 787 */       if (this.curChar == '\n' || this.curChar == '\r') {
/* 788 */         error_line++;
/* 789 */         error_column = 0;
/*     */       } else {
/* 791 */         error_column++;
/*     */       } 
/* 793 */     }  if (!EOFSeen) {
/* 794 */       this.input_stream.backup(1);
/* 795 */       error_after = (curPos <= 1) ? "" : this.input_stream.GetImage();
/*     */     } 
/* 797 */     throw new TokenMgrError(EOFSeen, this.curLexState, error_line, error_column, error_after, this.curChar, 0);
/*     */   }
/*     */   
/*     */   public void ReInit(SimpleCharStream stream) {
/*     */     this.jjmatchedPos = this.jjnewStateCnt = 0;
/*     */     this.curLexState = this.defaultLexState;
/*     */     this.input_stream = stream;
/*     */     ReInitRounds();
/*     */   }
/*     */   
/*     */   private final void ReInitRounds() {
/*     */     this.jjround = -2147483647;
/*     */     for (int i = 2; i-- > 0;)
/*     */       this.jjrounds[i] = Integer.MIN_VALUE; 
/*     */   }
/*     */   
/*     */   public void ReInit(SimpleCharStream stream, int lexState) {
/*     */     ReInit(stream);
/*     */     SwitchTo(lexState);
/*     */   }
/*     */   
/*     */   public void SwitchTo(int lexState) {
/*     */     if (lexState >= 1 || lexState < 0)
/*     */       throw new TokenMgrError("Error: Ignoring invalid lexical state : " + lexState + ". State unchanged.", 2); 
/*     */     this.curLexState = lexState;
/*     */   }
/*     */   
/*     */   protected Token jjFillToken() {
/*     */     Token t = Token.newToken(this.jjmatchedKind);
/*     */     t.kind = this.jjmatchedKind;
/*     */     String im = jjstrLiteralImages[this.jjmatchedKind];
/*     */     t.image = (im == null) ? this.input_stream.GetImage() : im;
/*     */     t.beginLine = this.input_stream.getBeginLine();
/*     */     t.beginColumn = this.input_stream.getBeginColumn();
/*     */     t.endLine = this.input_stream.getEndLine();
/*     */     t.endColumn = this.input_stream.getEndColumn();
/*     */     return t;
/*     */   }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\importer\mcc\javacc\TablesParserTokenManager.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */