/*      */ package lrg.memoria.importer.cdif;
/*      */ import java.io.IOException;
/*      */ import java.io.PrintStream;
/*      */ 
/*      */ public class CDIFParserTokenManager implements CDIFParserConstants {
/*      */   static void CommonTokenAction(Token t) {
/*    7 */     if (t.image.equals("\"\""))
/*    8 */       t.image = ""; 
/*      */   }
/*      */   
/*   11 */   public static PrintStream debugStream = System.out;
/*      */ 
/*      */   
/*   14 */   public static void setDebugStream(PrintStream ds) { debugStream = ds; }
/*      */ 
/*      */   
/*      */   private static final int jjStopStringLiteralDfa_0(int pos, long active0, long active1) {
/*   18 */     switch (pos) {
/*      */       case 0:
/*   20 */         if ((active0 & 0x4005000L) != 0L) {
/*   21 */           jjmatchedKind = 5;
/*   22 */           return 13;
/*      */         } 
/*   24 */         if ((active0 & 0x8080000L) != 0L) {
/*   25 */           jjmatchedKind = 5;
/*   26 */           return 14;
/*      */         } 
/*   28 */         if ((active0 & 0x2L) != 0L)
/*   29 */           return 11; 
/*   30 */         return -1;
/*      */       case 1:
/*   32 */         if ((active0 & 0x8080000L) != 0L) {
/*   33 */           if (jjmatchedPos == 0) {
/*   34 */             jjmatchedKind = 5;
/*   35 */             jjmatchedPos = 0;
/*      */           } 
/*   37 */           return -1;
/*      */         } 
/*   39 */         if ((active0 & 0x4005000L) != 0L) {
/*   40 */           jjmatchedKind = 5;
/*   41 */           jjmatchedPos = 1;
/*   42 */           return 13;
/*      */         } 
/*   44 */         return -1;
/*      */       case 2:
/*   46 */         if ((active0 & 0x8080000L) != 0L) {
/*   47 */           if (jjmatchedPos == 0) {
/*   48 */             jjmatchedKind = 5;
/*   49 */             jjmatchedPos = 0;
/*      */           } 
/*   51 */           return -1;
/*      */         } 
/*   53 */         if ((active0 & 0x4005000L) != 0L) {
/*   54 */           if (jjmatchedPos < 1) {
/*   55 */             jjmatchedKind = 5;
/*   56 */             jjmatchedPos = 1;
/*      */           } 
/*   58 */           return -1;
/*      */         } 
/*   60 */         return -1;
/*      */       case 3:
/*   62 */         if ((active0 & 0x80000L) != 0L) {
/*   63 */           if (jjmatchedPos == 0) {
/*   64 */             jjmatchedKind = 5;
/*   65 */             jjmatchedPos = 0;
/*      */           } 
/*   67 */           return -1;
/*      */         } 
/*   69 */         if ((active0 & 0x4005000L) != 0L) {
/*   70 */           if (jjmatchedPos < 1) {
/*   71 */             jjmatchedKind = 5;
/*   72 */             jjmatchedPos = 1;
/*      */           } 
/*   74 */           return -1;
/*      */         } 
/*   76 */         return -1;
/*      */       case 4:
/*   78 */         if ((active0 & 0x4005000L) != 0L) {
/*   79 */           if (jjmatchedPos < 1) {
/*   80 */             jjmatchedKind = 5;
/*   81 */             jjmatchedPos = 1;
/*      */           } 
/*   83 */           return -1;
/*      */         } 
/*   85 */         return -1;
/*      */       case 5:
/*   87 */         if ((active0 & 0x5000L) != 0L) {
/*   88 */           if (jjmatchedPos < 1) {
/*   89 */             jjmatchedKind = 5;
/*   90 */             jjmatchedPos = 1;
/*      */           } 
/*   92 */           return -1;
/*      */         } 
/*   94 */         return -1;
/*      */       case 6:
/*   96 */         if ((active0 & 0x5000L) != 0L) {
/*   97 */           if (jjmatchedPos < 1) {
/*   98 */             jjmatchedKind = 5;
/*   99 */             jjmatchedPos = 1;
/*      */           } 
/*  101 */           return -1;
/*      */         } 
/*  103 */         return -1;
/*      */       case 7:
/*  105 */         if ((active0 & 0x5000L) != 0L) {
/*  106 */           if (jjmatchedPos < 1) {
/*  107 */             jjmatchedKind = 5;
/*  108 */             jjmatchedPos = 1;
/*      */           } 
/*  110 */           return -1;
/*      */         } 
/*  112 */         return -1;
/*      */     } 
/*  114 */     return -1;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*  119 */   private static final int jjStartNfa_0(int pos, long active0, long active1) { return jjMoveNfa_0(jjStopStringLiteralDfa_0(pos, active0, active1), pos + 1); }
/*      */ 
/*      */   
/*      */   private static final int jjStopAtPos(int pos, int kind) {
/*  123 */     jjmatchedKind = kind;
/*  124 */     jjmatchedPos = pos;
/*  125 */     return pos + 1;
/*      */   }
/*      */   
/*      */   private static final int jjStartNfaWithStates_0(int pos, int kind, int state) {
/*  129 */     jjmatchedKind = kind;
/*  130 */     jjmatchedPos = pos;
/*      */     try {
/*  132 */       curChar = SimpleCharStream.readChar();
/*  133 */     } catch (IOException e) {
/*  134 */       return pos + 1;
/*      */     } 
/*  136 */     return jjMoveNfa_0(state, pos + 1);
/*      */   }
/*      */   
/*      */   private static final int jjMoveStringLiteralDfa0_0() {
/*  140 */     switch (curChar) {
/*      */       case '"':
/*  142 */         return jjStartNfaWithStates_0(0, 1, 11);
/*      */       case '(':
/*  144 */         return jjMoveStringLiteralDfa1_0(-201882624L, 2097151L);
/*      */       case ')':
/*  146 */         return jjStopAtPos(0, 11);
/*      */       case ',':
/*  148 */         return jjStopAtPos(0, 13);
/*      */       case '0':
/*  150 */         return jjMoveStringLiteralDfa1_0(67129344L, 0L);
/*      */       case '1':
/*  152 */         return jjMoveStringLiteralDfa1_0(134742016L, 0L);
/*      */     } 
/*  154 */     return jjMoveNfa_0(0, 0);
/*      */   }
/*      */ 
/*      */   
/*      */   private static final int jjMoveStringLiteralDfa1_0(long active0, long active1) {
/*      */     try {
/*  160 */       curChar = SimpleCharStream.readChar();
/*  161 */     } catch (IOException e) {
/*  162 */       jjStopStringLiteralDfa_0(0, active0, active1);
/*  163 */       return 1;
/*      */     } 
/*  165 */     switch (curChar) {
/*      */       case '.':
/*  167 */         return jjMoveStringLiteralDfa2_0(active0, 134742016L, active1, 0L);
/*      */       case '1':
/*  169 */         return jjMoveStringLiteralDfa2_0(active0, 67125248L, active1, 0L);
/*      */       case '2':
/*  171 */         return jjMoveStringLiteralDfa2_0(active0, 4096L, active1, 0L);
/*      */       case ':':
/*  173 */         return jjMoveStringLiteralDfa2_0(active0, 58819584L, active1, 0L);
/*      */       case 'A':
/*  175 */         return jjMoveStringLiteralDfa2_0(active0, 562949953421312L, active1, 32772L);
/*      */       case 'C':
/*  177 */         return jjMoveStringLiteralDfa2_0(active0, 576460786663161856L, active1, 262144L);
/*      */       case 'E':
/*  179 */         return jjMoveStringLiteralDfa2_0(active0, 3538944L, active1, 0L);
/*      */       case 'F':
/*  181 */         return jjMoveStringLiteralDfa2_0(active0, 0L, active1, 2048L);
/*      */       case 'I':
/*  183 */         return jjMoveStringLiteralDfa2_0(active0, 92323792361095168L, active1, 0L);
/*      */       case 'L':
/*  185 */         return jjMoveStringLiteralDfa2_0(active0, 288230376151711744L, active1, 8192L);
/*      */       case 'M':
/*  187 */         return jjMoveStringLiteralDfa2_0(active0, 0L, active1, 1040L);
/*      */       case 'N':
/*  189 */         return jjMoveStringLiteralDfa2_0(active0, -1152921502459363328L, active1, 3L);
/*      */       case 'P':
/*  191 */         return jjMoveStringLiteralDfa2_0(active0, 4567597056L, active1, 0L);
/*      */       case 'T':
/*  193 */         return jjMoveStringLiteralDfa2_0(active0, 0L, active1, 128L);
/*      */       case 'a':
/*  195 */         return jjMoveStringLiteralDfa2_0(active0, 281474976710656L, active1, 196608L);
/*      */       case 'b':
/*  197 */         return jjMoveStringLiteralDfa2_0(active0, 144115686292062208L, active1, 0L);
/*      */       case 'd':
/*  199 */         return jjMoveStringLiteralDfa2_0(active0, 1125899906842624L, active1, 0L);
/*      */       case 'e':
/*  201 */         return jjMoveStringLiteralDfa2_0(active0, 13194139533312L, active1, 512L);
/*      */       case 'f':
/*  203 */         return jjMoveStringLiteralDfa2_0(active0, 549755813888L, active1, 0L);
/*      */       case 'i':
/*  205 */         return jjMoveStringLiteralDfa2_0(active0, 36292679809630208L, active1, 1593344L);
/*      */       case 'k':
/*  207 */         return jjMoveStringLiteralDfa2_0(active0, 0L, active1, 64L);
/*      */       case 'm':
/*  209 */         return jjMoveStringLiteralDfa2_0(active0, 0L, active1, 256L);
/*      */       case 'n':
/*  211 */         return jjMoveStringLiteralDfa2_0(active0, 536870912L, active1, 0L);
/*      */       case 'r':
/*  213 */         return jjMoveStringLiteralDfa2_0(active0, 0L, active1, 32L);
/*      */       case 's':
/*  215 */         return jjMoveStringLiteralDfa2_0(active0, 13514098490736640L, active1, 0L);
/*      */       case 't':
/*  217 */         return jjMoveStringLiteralDfa2_0(active0, 0L, active1, 8L);
/*      */       case 'u':
/*  219 */         return jjMoveStringLiteralDfa2_0(active0, 8589934592L, active1, 0L);
/*      */     } 
/*      */ 
/*      */     
/*  223 */     return jjStartNfa_0(0, active0, active1);
/*      */   }
/*      */   
/*      */   private static final int jjMoveStringLiteralDfa2_0(long old0, long active0, long old1, long active1) {
/*  227 */     if ((active0 &= old0 | active1 &= old1) == 0L)
/*  228 */       return jjStartNfa_0(0, old0, old1); 
/*      */     try {
/*  230 */       curChar = SimpleCharStream.readChar();
/*  231 */     } catch (IOException e) {
/*  232 */       jjStopStringLiteralDfa_0(1, active0, active1);
/*  233 */       return 2;
/*      */     } 
/*  235 */     switch (curChar) {
/*      */       case '.':
/*  237 */         return jjMoveStringLiteralDfa3_0(active0, 67129344L, active1, 0L);
/*      */       case '0':
/*  239 */         if ((active0 & 0x8000000L) != 0L) {
/*  240 */           jjmatchedKind = 27;
/*  241 */           jjmatchedPos = 2;
/*      */         } 
/*  243 */         return jjMoveStringLiteralDfa3_0(active0, 524288L, active1, 0L);
/*      */       case 'H':
/*  245 */         return jjMoveStringLiteralDfa3_0(active0, 32768L, active1, 0L);
/*      */       case 'M':
/*  247 */         return jjMoveStringLiteralDfa3_0(active0, 8389632L, active1, 0L);
/*      */       case 'O':
/*  249 */         return jjMoveStringLiteralDfa3_0(active0, -864691128455135232L, active1, 3L);
/*      */       case 'S':
/*  251 */         return jjMoveStringLiteralDfa3_0(active0, 16842752L, active1, 0L);
/*      */       case 'V':
/*  253 */         return jjMoveStringLiteralDfa3_0(active0, 33554432L, active1, 0L);
/*      */       case 'Y':
/*  255 */         return jjMoveStringLiteralDfa3_0(active0, 576460752303423488L, active1, 0L);
/*      */       case 'a':
/*  257 */         return jjMoveStringLiteralDfa3_0(active0, 2952790016L, active1, 262144L);
/*      */       case 'c':
/*  259 */         return jjMoveStringLiteralDfa3_0(active0, 281474976710656L, active1, 229376L);
/*      */       case 'e':
/*  261 */         return jjMoveStringLiteralDfa3_0(active0, 145241586198904832L, active1, 1328L);
/*      */       case 'h':
/*  263 */         return jjMoveStringLiteralDfa3_0(active0, 0L, active1, 128L);
/*      */       case 'i':
/*  265 */         return jjMoveStringLiteralDfa3_0(active0, 549755813888L, active1, 64L);
/*      */       case 'l':
/*  267 */         return jjMoveStringLiteralDfa3_0(active0, 34359738368L, active1, 0L);
/*      */       case 'm':
/*  269 */         return jjMoveStringLiteralDfa3_0(active0, 18014398509481984L, active1, 0L);
/*      */       case 'n':
/*  271 */         return jjMoveStringLiteralDfa3_0(active0, 110351393600045056L, active1, 1572864L);
/*      */       case 'o':
/*  273 */         return jjMoveStringLiteralDfa3_0(active0, 0L, active1, 10240L);
/*      */       case 'r':
/*  275 */         return jjMoveStringLiteralDfa3_0(active0, 562954248388608L, active1, 0L);
/*      */       case 's':
/*  277 */         return jjMoveStringLiteralDfa3_0(active0, 263882790666240L, active1, 20480L);
/*      */       case 't':
/*  279 */         return jjMoveStringLiteralDfa3_0(active0, 3299608625152L, active1, 4L);
/*      */       case 'u':
/*  281 */         return jjMoveStringLiteralDfa3_0(active0, 13510798886305792L, active1, 0L);
/*      */       case 'x':
/*  283 */         return jjMoveStringLiteralDfa3_0(active0, 3538944L, active1, 512L);
/*      */       case 'y':
/*  285 */         return jjMoveStringLiteralDfa3_0(active0, 0L, active1, 8L);
/*      */     } 
/*      */ 
/*      */     
/*  289 */     return jjStartNfa_0(1, active0, active1);
/*      */   }
/*      */   
/*      */   private static final int jjMoveStringLiteralDfa3_0(long old0, long active0, long old1, long active1) {
/*  293 */     if ((active0 &= old0 | active1 &= old1) == 0L)
/*  294 */       return jjStartNfa_0(1, old0, old1); 
/*      */     try {
/*  296 */       curChar = SimpleCharStream.readChar();
/*  297 */     } catch (IOException e) {
/*  298 */       jjStopStringLiteralDfa_0(2, active0, active1);
/*  299 */       return 3;
/*      */     } 
/*  301 */     switch (curChar) {
/*      */       case '0':
/*  303 */         if ((active0 & 0x80000L) != 0L)
/*  304 */           return jjStopAtPos(3, 19); 
/*  305 */         return jjMoveStringLiteralDfa4_0(active0, 67129344L, active1, 0L);
/*      */       case 'A':
/*  307 */         return jjMoveStringLiteralDfa4_0(active0, 17592186044416L, active1, 0L);
/*      */       case 'B':
/*  309 */         return jjMoveStringLiteralDfa4_0(active0, 0L, active1, 16384L);
/*      */       case 'C':
/*  311 */         if ((active0 & 0x400000000000000L) != 0L)
/*  312 */           return jjStopAtPos(3, 58); 
/*  313 */         return jjMoveStringLiteralDfa4_0(active0, 5188146770730811392L, active1, 0L);
/*      */       case 'D':
/*  315 */         return jjMoveStringLiteralDfa4_0(active0, 2305843009213693952L, active1, 0L);
/*      */       case 'E':
/*  317 */         return jjMoveStringLiteralDfa4_0(active0, -9223372036812800000L, active1, 4097L);
/*      */       case 'F':
/*  319 */         return jjMoveStringLiteralDfa4_0(active0, 35184372088832L, active1, 0L);
/*      */       case 'I':
/*  321 */         return jjMoveStringLiteralDfa4_0(active0, 140737488355328L, active1, 0L);
/*      */       case 'L':
/*  323 */         if ((active1 & 0x2L) != 0L)
/*  324 */           return jjStopAtPos(3, 65); 
/*      */         break;
/*      */       case 'O':
/*  327 */         return jjMoveStringLiteralDfa4_0(active0, 1024L, active1, 0L);
/*      */       case 'S':
/*  329 */         if ((active0 & 0x1000000000000000L) != 0L)
/*  330 */           return jjStopAtPos(3, 60); 
/*  331 */         return jjMoveStringLiteralDfa4_0(active0, 70368744177664L, active1, 0L);
/*      */       case 'U':
/*  333 */         return jjMoveStringLiteralDfa4_0(active0, 16842752L, active1, 0L);
/*      */       case 'a':
/*  335 */         return jjMoveStringLiteralDfa4_0(active0, 3333968363520L, active1, 0L);
/*      */       case 'b':
/*  337 */         return jjMoveStringLiteralDfa4_0(active0, 4503599631564800L, active1, 0L);
/*      */       case 'c':
/*  339 */         return jjMoveStringLiteralDfa4_0(active0, 1407375151988736L, active1, 238080L);
/*      */       case 'd':
/*  341 */         return jjMoveStringLiteralDfa4_0(active0, 13194139533312L, active1, 0L);
/*      */       case 'h':
/*  343 */         return jjMoveStringLiteralDfa4_0(active0, 2251799813685248L, active1, 0L);
/*      */       case 'i':
/*  345 */         return jjMoveStringLiteralDfa4_0(active0, 72057606922829824L, active1, 0L);
/*      */       case 'l':
/*  347 */         return jjMoveStringLiteralDfa4_0(active0, 144116236047876096L, active1, 262144L);
/*      */       case 'm':
/*  349 */         return jjMoveStringLiteralDfa4_0(active0, 2684354560L, active1, 0L);
/*      */       case 'n':
/*  351 */         return jjMoveStringLiteralDfa4_0(active0, 0L, active1, 64L);
/*      */       case 'p':
/*  353 */         return jjMoveStringLiteralDfa4_0(active0, 27021597767761920L, active1, 8L);
/*      */       case 'r':
/*  355 */         return jjMoveStringLiteralDfa4_0(active0, 562949953421312L, active1, 2176L);
/*      */       case 't':
/*  357 */         return jjMoveStringLiteralDfa4_0(active0, 36028797018963968L, active1, 1332L);
/*      */       case 'v':
/*  359 */         return jjMoveStringLiteralDfa4_0(active0, 0L, active1, 1572864L);
/*      */     } 
/*      */ 
/*      */     
/*  363 */     return jjStartNfa_0(2, active0, active1);
/*      */   }
/*      */   
/*      */   private static final int jjMoveStringLiteralDfa4_0(long old0, long active0, long old1, long active1) {
/*  367 */     if ((active0 &= old0 | active1 &= old1) == 0L)
/*  368 */       return jjStartNfa_0(2, old0, old1); 
/*      */     try {
/*  370 */       curChar = SimpleCharStream.readChar();
/*  371 */     } catch (IOException e) {
/*  372 */       jjStopStringLiteralDfa_0(3, active0, active1);
/*  373 */       return 4;
/*      */     } 
/*  375 */     switch (curChar) {
/*      */       case '0':
/*  377 */         if ((active0 & 0x4000000L) != 0L)
/*  378 */           return jjStopAtPos(4, 26); 
/*  379 */         return jjMoveStringLiteralDfa5_0(active0, 4096L, active1, 0L);
/*      */       case '5':
/*  381 */         return jjMoveStringLiteralDfa5_0(active0, 16384L, active1, 0L);
/*      */       case 'A':
/*  383 */         return jjMoveStringLiteralDfa5_0(active0, 32768L, active1, 0L);
/*      */       case 'B':
/*  385 */         return jjMoveStringLiteralDfa5_0(active0, 16777216L, active1, 0L);
/*      */       case 'D':
/*  387 */         return jjMoveStringLiteralDfa5_0(active0, 1024L, active1, 0L);
/*      */       case 'L':
/*  389 */         return jjMoveStringLiteralDfa5_0(active0, 576460752303423488L, active1, 0L);
/*      */       case 'M':
/*  391 */         return jjMoveStringLiteralDfa5_0(active0, 65536L, active1, 0L);
/*      */       case 'R':
/*  393 */         return jjMoveStringLiteralDfa5_0(active0, 33554432L, active1, 0L);
/*      */       case 'T':
/*  395 */         return jjMoveStringLiteralDfa5_0(active0, 8388608L, active1, 0L);
/*      */       case '_':
/*  397 */         return jjMoveStringLiteralDfa5_0(active0, 13194139533312L, active1, 0L);
/*      */       case 'a':
/*  399 */         return jjMoveStringLiteralDfa5_0(active0, 562949953421312L, active1, 8192L);
/*      */       case 'b':
/*  401 */         return jjMoveStringLiteralDfa5_0(active0, 17592186044416L, active1, 0L);
/*      */       case 'c':
/*  403 */         return jjMoveStringLiteralDfa5_0(active0, 4503599627370496L, active1, 0L);
/*      */       case 'd':
/*  405 */         return jjMoveStringLiteralDfa5_0(active0, 0L, active1, 64L);
/*      */       case 'e':
/*  407 */         if ((active0 & 0x20000000L) != 0L)
/*  408 */           return jjStopAtPos(4, 29); 
/*  409 */         if ((active1 & 0x8L) != 0L)
/*  410 */           return jjStopAtPos(4, 67); 
/*  411 */         return jjMoveStringLiteralDfa5_0(active0, 2353412832181092352L, active1, 229888L);
/*      */       case 'h':
/*  413 */         return jjMoveStringLiteralDfa5_0(active0, 0L, active1, 1296L);
/*      */       case 'i':
/*  415 */         return jjMoveStringLiteralDfa5_0(active0, 35184372088832L, active1, 0L);
/*      */       case 'k':
/*  417 */         return jjMoveStringLiteralDfa5_0(active0, 268435456L, active1, 0L);
/*      */       case 'l':
/*  419 */         if ((active1 & 0x40000L) != 0L)
/*  420 */           return jjStopAtPos(4, 82); 
/*  421 */         return jjMoveStringLiteralDfa5_0(active0, 18014398513676288L, active1, 16384L);
/*      */       case 'm':
/*  423 */         return jjMoveStringLiteralDfa5_0(active0, 4611686022722355200L, active1, 2048L);
/*      */       case 'n':
/*  425 */         return jjMoveStringLiteralDfa5_0(active0, 140737488355328L, active1, 0L);
/*      */       case 'o':
/*  427 */         return jjMoveStringLiteralDfa5_0(active0, 145241586202443776L, active1, 1572992L);
/*      */       case 'q':
/*  429 */         return jjMoveStringLiteralDfa5_0(active0, 8589934592L, active1, 0L);
/*      */       case 'r':
/*  431 */         return jjMoveStringLiteralDfa5_0(active0, 3298534883328L, active1, 4L);
/*      */       case 's':
/*  433 */         return jjMoveStringLiteralDfa5_0(active0, 34359738368L, active1, 0L);
/*      */       case 't':
/*  435 */         return jjMoveStringLiteralDfa5_0(active0, 72127963855847424L, active1, 0L);
/*      */       case 'u':
/*  437 */         return jjMoveStringLiteralDfa5_0(active0, 0L, active1, 32L);
/*      */       case 'x':
/*  439 */         return jjMoveStringLiteralDfa5_0(active0, Float.MIN_VALUE, active1, 4097L);
/*      */     } 
/*      */ 
/*      */     
/*  443 */     return jjStartNfa_0(3, active0, active1);
/*      */   }
/*      */   
/*      */   private static final int jjMoveStringLiteralDfa5_0(long old0, long active0, long old1, long active1) {
/*  447 */     if ((active0 &= old0 | active1 &= old1) == 0L)
/*  448 */       return jjStartNfa_0(3, old0, old1); 
/*      */     try {
/*  450 */       curChar = SimpleCharStream.readChar();
/*  451 */     } catch (IOException e) {
/*  452 */       jjStopStringLiteralDfa_0(4, active0, active1);
/*  453 */       return 5;
/*      */     } 
/*  455 */     switch (curChar) {
/*      */       case '.':
/*  457 */         return jjMoveStringLiteralDfa6_0(active0, 20480L, active1, 0L);
/*      */       case 'A':
/*  459 */         return jjMoveStringLiteralDfa6_0(active0, 8388608L, active1, 0L);
/*      */       case 'D':
/*  461 */         return jjMoveStringLiteralDfa6_0(active0, 32768L, active1, 0L);
/*      */       case 'E':
/*  463 */         return jjMoveStringLiteralDfa6_0(active0, 1024L, active1, 0L);
/*      */       case 'J':
/*  465 */         return jjMoveStringLiteralDfa6_0(active0, 16777216L, active1, 0L);
/*      */       case 'M':
/*  467 */         return jjMoveStringLiteralDfa6_0(active0, 65536L, active1, 0L);
/*      */       case 'O':
/*  469 */         if ((active0 & 0x800000000000000L) != 0L)
/*  470 */           return jjStopAtPos(5, 59); 
/*  471 */         return jjMoveStringLiteralDfa6_0(active0, 0L, active1, 64L);
/*      */       case 'P':
/*  473 */         return jjMoveStringLiteralDfa6_0(active0, 0L, active1, 4096L);
/*      */       case 'S':
/*  475 */         return jjMoveStringLiteralDfa6_0(active0, 33554432L, active1, 0L);
/*      */       case '_':
/*  477 */         return jjMoveStringLiteralDfa6_0(active0, 549755813888L, active1, 0L);
/*      */       case 'a':
/*  479 */         return jjMoveStringLiteralDfa6_0(active0, 70369012613120L, active1, 2048L);
/*      */       case 'c':
/*  481 */         if ((active0 & 0x2000000000000000L) != 0L)
/*  482 */           return jjStopAtPos(5, 61); 
/*  483 */         if ((active0 & Float.MIN_VALUE) != 0L)
/*  484 */           return jjStopAtPos(5, 63); 
/*  485 */         return jjMoveStringLiteralDfa6_0(active0, 8796093022208L, active1, 1048576L);
/*      */       case 'e':
/*  487 */         return jjMoveStringLiteralDfa6_0(active0, 18014398509481984L, active1, 0L);
/*      */       case 'i':
/*  489 */         return jjMoveStringLiteralDfa6_0(active0, 72057598337089536L, active1, 5L);
/*      */       case 'k':
/*  491 */         return jjMoveStringLiteralDfa6_0(active0, 0L, active1, 524288L);
/*      */       case 'l':
/*  493 */         return jjMoveStringLiteralDfa6_0(active0, 4507997673881600L, active1, 8192L);
/*      */       case 'n':
/*  495 */         return jjMoveStringLiteralDfa6_0(active0, 144150870664151040L, active1, 0L);
/*      */       case 'o':
/*  497 */         return jjMoveStringLiteralDfa6_0(active0, 0L, active1, 17680L);
/*      */       case 'p':
/*  499 */         return jjMoveStringLiteralDfa6_0(active0, 0L, active1, 512L);
/*      */       case 'r':
/*  501 */         return jjMoveStringLiteralDfa6_0(active0, 48413695997771776L, active1, 32L);
/*      */       case 's':
/*  503 */         if ((active0 & 0x800000000L) != 0L)
/*  504 */           return jjStopAtPos(5, 35); 
/*  505 */         return jjMoveStringLiteralDfa6_0(active0, 299069310238720L, active1, 229376L);
/*      */       case 't':
/*  507 */         if ((active0 & 0x4000000000000000L) != 0L)
/*  508 */           return jjStopAtPos(5, 62); 
/*  509 */         return jjMoveStringLiteralDfa6_0(active0, 144036023238656L, active1, 0L);
/*      */       case 'u':
/*  511 */         return jjMoveStringLiteralDfa6_0(active0, 9663676416L, active1, 0L);
/*      */       case 'w':
/*  513 */         return jjMoveStringLiteralDfa6_0(active0, 0L, active1, 128L);
/*      */       case 'y':
/*  515 */         return jjMoveStringLiteralDfa6_0(active0, 562949953421312L, active1, 0L);
/*      */     } 
/*      */ 
/*      */     
/*  519 */     return jjStartNfa_0(4, active0, active1);
/*      */   }
/*      */   
/*      */   private static final int jjMoveStringLiteralDfa6_0(long old0, long active0, long old1, long active1) {
/*  523 */     if ((active0 &= old0 | active1 &= old1) == 0L)
/*  524 */       return jjStartNfa_0(4, old0, old1); 
/*      */     try {
/*  526 */       curChar = SimpleCharStream.readChar();
/*  527 */     } catch (IOException e) {
/*  528 */       jjStopStringLiteralDfa_0(5, active0, active1);
/*  529 */       return 6;
/*      */     } 
/*  531 */     switch (curChar) {
/*      */       case '-':
/*  533 */         return jjMoveStringLiteralDfa7_0(active0, 8388608L, active1, 0L);
/*      */       case '0':
/*  535 */         return jjMoveStringLiteralDfa7_0(active0, 20480L, active1, 0L);
/*      */       case 'A':
/*  537 */         return jjMoveStringLiteralDfa7_0(active0, 65536L, active1, 0L);
/*      */       case 'D':
/*  539 */         return jjMoveStringLiteralDfa7_0(active0, 562949953421312L, active1, 0L);
/*      */       case 'E':
/*  541 */         return jjMoveStringLiteralDfa7_0(active0, 16809984L, active1, 0L);
/*      */       case 'I':
/*  543 */         return jjMoveStringLiteralDfa7_0(active0, 33554432L, active1, 0L);
/*      */       case 'L':
/*  545 */         if ((active0 & 0x400L) != 0L)
/*  546 */           return jjStopAtPos(6, 10); 
/*      */         break;
/*      */       case 'V':
/*  549 */         return jjMoveStringLiteralDfa7_0(active0, 0L, active1, 8192L);
/*      */       case '_':
/*  551 */         return jjMoveStringLiteralDfa7_0(active0, 3298534883328L, active1, 0L);
/*      */       case 'a':
/*  553 */         return jjMoveStringLiteralDfa7_0(active0, 77722277944229888L, active1, 1052672L);
/*      */       case 'b':
/*  555 */         return jjMoveStringLiteralDfa7_0(active0, 0L, active1, 4L);
/*      */       case 'c':
/*  557 */         return jjMoveStringLiteralDfa7_0(active0, 9007199254740992L, active1, 16384L);
/*      */       case 'd':
/*  559 */         if ((active1 & 0x10L) != 0L) {
/*  560 */           jjmatchedKind = 68;
/*  561 */           jjmatchedPos = 6;
/*  562 */         } else if ((active1 & 0x100L) != 0L) {
/*  563 */           return jjStopAtPos(6, 72);
/*  564 */         }  return jjMoveStringLiteralDfa7_0(active0, 0L, active1, 1024L);
/*      */       case 'e':
/*  566 */         return jjMoveStringLiteralDfa7_0(active0, 140746078289920L, active1, 524288L);
/*      */       case 'f':
/*  568 */         if ((active1 & 0x40L) != 0L)
/*  569 */           return jjStopAtPos(6, 70); 
/*  570 */         return jjMoveStringLiteralDfa7_0(active0, 36028797018963968L, active1, 0L);
/*      */       case 'g':
/*  572 */         return jjMoveStringLiteralDfa7_0(active0, 144115686560497664L, active1, 0L);
/*      */       case 'h':
/*  574 */         return jjMoveStringLiteralDfa7_0(active0, 8796093022208L, active1, 0L);
/*      */       case 'i':
/*  576 */         return jjMoveStringLiteralDfa7_0(active0, 2256197860196352L, active1, 0L);
/*      */       case 'l':
/*  578 */         return jjMoveStringLiteralDfa7_0(active0, 0L, active1, 2048L);
/*      */       case 'm':
/*  580 */         return jjMoveStringLiteralDfa7_0(active0, 18014398509481984L, active1, 0L);
/*      */       case 'n':
/*  582 */         return jjMoveStringLiteralDfa7_0(active0, 549755813888L, active1, 32L);
/*      */       case 'p':
/*  584 */         return jjMoveStringLiteralDfa7_0(active0, 2147483648L, active1, 0L);
/*      */       case 's':
/*  586 */         if ((active1 & 0x8000L) != 0L)
/*  587 */           return jjStopAtPos(6, 79); 
/*  588 */         return jjMoveStringLiteralDfa7_0(active0, 281474980904960L, active1, 196736L);
/*      */       case 't':
/*  590 */         return jjMoveStringLiteralDfa7_0(active0, 87966302470144L, active1, 513L);
/*      */     } 
/*      */ 
/*      */     
/*  594 */     return jjStartNfa_0(5, active0, active1);
/*      */   }
/*      */   
/*      */   private static final int jjMoveStringLiteralDfa7_0(long old0, long active0, long old1, long active1) {
/*  598 */     if ((active0 &= old0 | active1 &= old1) == 0L)
/*  599 */       return jjStartNfa_0(5, old0, old1); 
/*      */     try {
/*  601 */       curChar = SimpleCharStream.readChar();
/*  602 */     } catch (IOException e) {
/*  603 */       jjStopStringLiteralDfa_0(6, active0, active1);
/*  604 */       return 7;
/*      */     } 
/*  606 */     switch (curChar) {
/*      */       case '0':
/*  608 */         if ((active0 & 0x1000L) != 0L)
/*  609 */           return jjStopAtPos(7, 12); 
/*      */         break;
/*      */       case '4':
/*  612 */         if ((active0 & 0x4000L) != 0L)
/*  613 */           return jjStopAtPos(7, 14); 
/*      */         break;
/*      */       case 'B':
/*  616 */         return jjMoveStringLiteralDfa8_0(active0, 0L, active1, 1024L);
/*      */       case 'C':
/*  618 */         return jjMoveStringLiteralDfa8_0(active0, 16777216L, active1, 0L);
/*      */       case 'E':
/*  620 */         return jjMoveStringLiteralDfa8_0(active0, 0L, active1, 128L);
/*      */       case 'M':
/*  622 */         return jjMoveStringLiteralDfa8_0(active0, 8388608L, active1, 0L);
/*      */       case 'N':
/*  624 */         return jjMoveStringLiteralDfa8_0(active0, 8589934592L, active1, 0L);
/*      */       case 'O':
/*  626 */         return jjMoveStringLiteralDfa8_0(active0, 33554432L, active1, 0L);
/*      */       case 'P':
/*  628 */         return jjMoveStringLiteralDfa8_0(active0, 0L, active1, 2048L);
/*      */       case 'R':
/*  630 */         if ((active0 & 0x8000L) != 0L)
/*  631 */           return jjStopAtPos(7, 15); 
/*  632 */         return jjMoveStringLiteralDfa8_0(active0, 65536L, active1, 0L);
/*      */       case 'T':
/*  634 */         return jjMoveStringLiteralDfa8_0(active0, 0L, active1, 32L);
/*      */       case '_':
/*  636 */         return jjMoveStringLiteralDfa8_0(active0, 281474976710656L, active1, 0L);
/*      */       case 'a':
/*  638 */         return jjMoveStringLiteralDfa8_0(active0, 36038145015283712L, active1, 8192L);
/*      */       case 'c':
/*  640 */         return jjMoveStringLiteralDfa8_0(active0, 2199023255552L, active1, 0L);
/*      */       case 'd':
/*  642 */         return jjMoveStringLiteralDfa8_0(active0, 0L, active1, 524288L);
/*      */       case 'e':
/*  644 */         if ((active0 & 0x10000000L) != 0L)
/*  645 */           return jjStopAtPos(7, 28); 
/*  646 */         if ((active0 & 0x40000000L) != 0L)
/*  647 */           return jjStopAtPos(7, 30); 
/*  648 */         return jjMoveStringLiteralDfa8_0(active0, 18577348466442240L, active1, 196608L);
/*      */       case 'h':
/*  650 */         return jjMoveStringLiteralDfa8_0(active0, 4194304L, active1, 0L);
/*      */       case 'i':
/*  652 */         return jjMoveStringLiteralDfa8_0(active0, 70373039144960L, active1, 512L);
/*      */       case 'k':
/*  654 */         if ((active1 & 0x4000L) != 0L)
/*  655 */           return jjStopAtPos(7, 78); 
/*      */         break;
/*      */       case 'l':
/*  658 */         if ((active0 & 0x200000000000L) != 0L)
/*  659 */           return jjStopAtPos(7, 45); 
/*  660 */         return jjMoveStringLiteralDfa8_0(active0, 81065892804296704L, active1, 0L);
/*      */       case 'n':
/*  662 */         return jjMoveStringLiteralDfa8_0(active0, 4398046511104L, active1, 0L);
/*      */       case 'r':
/*  664 */         return jjMoveStringLiteralDfa8_0(active0, 158329674399744L, active1, 4096L);
/*      */       case 's':
/*  666 */         if ((active1 & 0x1L) != 0L)
/*  667 */           return jjStopAtPos(7, 64); 
/*  668 */         return jjMoveStringLiteralDfa8_0(active0, 148619285919432704L, active1, 0L);
/*      */       case 't':
/*  670 */         return jjMoveStringLiteralDfa8_0(active0, 3377699720527872L, active1, 1048576L);
/*      */       case 'u':
/*  672 */         return jjMoveStringLiteralDfa8_0(active0, 0L, active1, 4L);
/*      */     } 
/*      */ 
/*      */     
/*  676 */     return jjStartNfa_0(6, active0, active1);
/*      */   }
/*      */   
/*      */   private static final int jjMoveStringLiteralDfa8_0(long old0, long active0, long old1, long active1) {
/*  680 */     if ((active0 &= old0 | active1 &= old1) == 0L)
/*  681 */       return jjStartNfa_0(6, old0, old1); 
/*      */     try {
/*  683 */       curChar = SimpleCharStream.readChar();
/*  684 */     } catch (IOException e) {
/*  685 */       jjStopStringLiteralDfa_0(7, active0, active1);
/*  686 */       return 8;
/*      */     } 
/*  688 */     switch (curChar) {
/*      */       case 'I':
/*  690 */         return jjMoveStringLiteralDfa9_0(active0, 0L, active1, 524288L);
/*      */       case 'N':
/*  692 */         return jjMoveStringLiteralDfa9_0(active0, 33554432L, active1, 0L);
/*      */       case 'O':
/*  694 */         return jjMoveStringLiteralDfa9_0(active0, 8388608L, active1, 0L);
/*      */       case 'T':
/*  696 */         return jjMoveStringLiteralDfa9_0(active0, 144115686308839424L, active1, 0L);
/*      */       case 'Y':
/*  698 */         if ((active0 & 0x10000L) != 0L)
/*  699 */           return jjStopAtPos(8, 16); 
/*      */         break;
/*      */       case 'a':
/*  702 */         return jjMoveStringLiteralDfa9_0(active0, 11276599844405248L, active1, 6144L);
/*      */       case 'c':
/*  704 */         if ((active0 & 0x400000000000L) != 0L)
/*  705 */           return jjStopAtPos(8, 46); 
/*  706 */         return jjMoveStringLiteralDfa9_0(active0, 36591749119868928L, active1, 0L);
/*      */       case 'd':
/*  708 */         return jjMoveStringLiteralDfa9_0(active0, 0L, active1, 65536L);
/*      */       case 'e':
/*  710 */         if ((active0 & 0x40000000000L) != 0L)
/*  711 */           return jjStopAtPos(8, 42); 
/*  712 */         return jjMoveStringLiteralDfa9_0(active0, 1125899911036928L, active1, 0L);
/*      */       case 'f':
/*  714 */         return jjMoveStringLiteralDfa9_0(active0, 140737488355328L, active1, 0L);
/*      */       case 'h':
/*  716 */         return jjMoveStringLiteralDfa9_0(active0, 2199023255552L, active1, 0L);
/*      */       case 'i':
/*  718 */         return jjMoveStringLiteralDfa9_0(active0, 72058693549555712L, active1, 1048576L);
/*      */       case 'm':
/*  720 */         return jjMoveStringLiteralDfa9_0(active0, 282024732524544L, active1, 0L);
/*      */       case 'n':
/*  722 */         return jjMoveStringLiteralDfa9_0(active0, 18014398509481984L, active1, 0L);
/*      */       case 'o':
/*  724 */         return jjMoveStringLiteralDfa9_0(active0, 0L, active1, 1536L);
/*      */       case 'r':
/*  726 */         if ((active0 & 0x80000000000L) != 0L)
/*  727 */           return jjStopAtPos(8, 43); 
/*  728 */         return jjMoveStringLiteralDfa9_0(active0, 3538944L, active1, 8192L);
/*      */       case 's':
/*  730 */         if ((active0 & 0x10000000000000L) != 0L)
/*  731 */           return jjStopAtPos(8, 52); 
/*  732 */         return jjMoveStringLiteralDfa9_0(active0, 0L, active1, 131072L);
/*      */       case 't':
/*  734 */         return jjMoveStringLiteralDfa9_0(active0, 0L, active1, 4L);
/*      */       case 'v':
/*  736 */         return jjMoveStringLiteralDfa9_0(active0, 4294967296L, active1, 0L);
/*      */       case 'x':
/*  738 */         return jjMoveStringLiteralDfa9_0(active0, 0L, active1, 128L);
/*      */       case 'y':
/*  740 */         return jjMoveStringLiteralDfa9_0(active0, 0L, active1, 32L);
/*      */     } 
/*      */ 
/*      */     
/*  744 */     return jjStartNfa_0(7, active0, active1);
/*      */   }
/*      */   
/*      */   private static final int jjMoveStringLiteralDfa9_0(long old0, long active0, long old1, long active1) {
/*  748 */     if ((active0 &= old0 | active1 &= old1) == 0L)
/*  749 */       return jjStartNfa_0(7, old0, old1); 
/*      */     try {
/*  751 */       curChar = SimpleCharStream.readChar();
/*  752 */     } catch (IOException e) {
/*  753 */       jjStopStringLiteralDfa_0(8, active0, active1);
/*  754 */       return 9;
/*      */     } 
/*  756 */     switch (curChar) {
/*      */       case 'A':
/*  758 */         return jjMoveStringLiteralDfa10_0(active0, 16777216L, active1, 0L);
/*      */       case 'D':
/*  760 */         return jjMoveStringLiteralDfa10_0(active0, 9437184L, active1, 0L);
/*      */       case 'I':
/*  762 */         return jjMoveStringLiteralDfa10_0(active0, 0L, active1, 65536L);
/*      */       case 'N':
/*  764 */         return jjMoveStringLiteralDfa10_0(active0, 33685504L, active1, 0L);
/*      */       case 'T':
/*  766 */         return jjMoveStringLiteralDfa10_0(active0, 2097152L, active1, 0L);
/*      */       case 'V':
/*  768 */         return jjMoveStringLiteralDfa10_0(active0, 262144L, active1, 0L);
/*      */       case '_':
/*  770 */         return jjMoveStringLiteralDfa10_0(active0, 0L, active1, 131072L);
/*      */       case 'a':
/*  772 */         return jjMoveStringLiteralDfa10_0(active0, 142936511610880L, active1, 0L);
/*      */       case 'c':
/*  774 */         return jjMoveStringLiteralDfa10_0(active0, 17592186044416L, active1, 128L);
/*      */       case 'd':
/*  776 */         return jjMoveStringLiteralDfa10_0(active0, 1125899906842624L, active1, 1024L);
/*      */       case 'e':
/*  778 */         if ((active0 & 0x80000000L) != 0L)
/*  779 */           return jjStopAtPos(9, 31); 
/*  780 */         if ((active0 & 0x8000000000L) != 0L)
/*  781 */           return jjStopAtPos(9, 39); 
/*  782 */         if ((active0 & 0x80000000000000L) != 0L)
/*  783 */           return jjStopAtPos(9, 55); 
/*  784 */         if ((active1 & 0x4L) != 0L)
/*  785 */           return jjStopAtPos(9, 66); 
/*  786 */         return jjMoveStringLiteralDfa10_0(active0, 4294967296L, active1, 0L);
/*      */       case 'i':
/*  788 */         return jjMoveStringLiteralDfa10_0(active0, 0L, active1, 8192L);
/*      */       case 'm':
/*  790 */         if ((active1 & 0x1000L) != 0L)
/*  791 */           return jjStopAtPos(9, 76); 
/*  792 */         return jjMoveStringLiteralDfa10_0(active0, 8589934592L, active1, 0L);
/*      */       case 'n':
/*  794 */         if ((active1 & 0x80000L) != 0L)
/*  795 */           return jjStopAtPos(9, 83); 
/*  796 */         return jjMoveStringLiteralDfa10_0(active0, 2252899325313024L, active1, 512L);
/*      */       case 'o':
/*  798 */         if ((active0 & 0x200000000000000L) != 0L) {
/*  799 */           jjmatchedKind = 57;
/*  800 */           jjmatchedPos = 9;
/*      */         } 
/*  802 */         return jjMoveStringLiteralDfa10_0(active0, 844923146338304L, active1, 1048576L);
/*      */       case 'p':
/*  804 */         return jjMoveStringLiteralDfa10_0(active0, 0L, active1, 32L);
/*      */       case 'r':
/*  806 */         return jjMoveStringLiteralDfa10_0(active0, 4194304L, active1, 2048L);
/*      */       case 's':
/*  808 */         return jjMoveStringLiteralDfa10_0(active0, 9007199254740992L, active1, 0L);
/*      */       case 't':
/*  810 */         return jjMoveStringLiteralDfa10_0(active0, 18014398509481984L, active1, 0L);
/*      */       case 'z':
/*  812 */         return jjMoveStringLiteralDfa10_0(active0, 72057594037927936L, active1, 0L);
/*      */     } 
/*      */ 
/*      */     
/*  816 */     return jjStartNfa_0(8, active0, active1);
/*      */   }
/*      */   
/*      */   private static final int jjMoveStringLiteralDfa10_0(long old0, long active0, long old1, long active1) {
/*  820 */     if ((active0 &= old0 | active1 &= old1) == 0L)
/*  821 */       return jjStartNfa_0(8, old0, old1); 
/*      */     try {
/*  823 */       curChar = SimpleCharStream.readChar();
/*  824 */     } catch (IOException e) {
/*  825 */       jjStopStringLiteralDfa_0(9, active0, active1);
/*  826 */       return 10;
/*      */     } 
/*  828 */     switch (curChar) {
/*      */       case 'B':
/*  830 */         return jjMoveStringLiteralDfa11_0(active0, 274877906944L, active1, 0L);
/*      */       case 'C':
/*  832 */         return jjMoveStringLiteralDfa11_0(active0, 137438953472L, active1, 0L);
/*      */       case 'E':
/*  834 */         return jjMoveStringLiteralDfa11_0(active0, 8388608L, active1, 0L);
/*      */       case 'N':
/*  836 */         return jjMoveStringLiteralDfa11_0(active0, 17184063488L, active1, 0L);
/*      */       case 'P':
/*  838 */         return jjMoveStringLiteralDfa11_0(active0, 68719476736L, active1, 0L);
/*      */       case 'R':
/*  840 */         return jjMoveStringLiteralDfa11_0(active0, 16777216L, active1, 0L);
/*      */       case 'T':
/*  842 */         return jjMoveStringLiteralDfa11_0(active0, 1125904201809920L, active1, 0L);
/*      */       case 'U':
/*  844 */         return jjMoveStringLiteralDfa11_0(active0, 33554432L, active1, 0L);
/*      */       case '_':
/*  846 */         return jjMoveStringLiteralDfa11_0(active0, 0L, active1, 512L);
/*      */       case 'a':
/*  848 */         return jjMoveStringLiteralDfa11_0(active0, 1179648L, active1, 10240L);
/*      */       case 'c':
/*  850 */         return jjMoveStringLiteralDfa11_0(active0, 2392537302040576L, active1, 0L);
/*      */       case 'd':
/*  852 */         return jjMoveStringLiteralDfa11_0(active0, 281474976710656L, active1, 0L);
/*      */       case 'e':
/*  854 */         if ((active0 & 0x200000000L) != 0L)
/*  855 */           return jjStopAtPos(10, 33); 
/*  856 */         if ((active0 & 0x10000000000L) != 0L)
/*  857 */           return jjStopAtPos(10, 40); 
/*  858 */         if ((active1 & 0x20L) != 0L)
/*  859 */           return jjStopAtPos(10, 69); 
/*  860 */         return jjMoveStringLiteralDfa11_0(active0, 72057594038190080L, active1, 128L);
/*      */       case 'i':
/*  862 */         return jjMoveStringLiteralDfa11_0(active0, 2097152L, active1, 0L);
/*      */       case 'n':
/*  864 */         if ((active1 & 0x10000L) != 0L)
/*  865 */           return jjStopAtPos(10, 80); 
/*  866 */         return jjMoveStringLiteralDfa11_0(active0, 0L, active1, 1179648L);
/*      */       case 'r':
/*  868 */         if ((active0 & 0x20000000000L) != 0L)
/*  869 */           return jjStopAtPos(10, 41); 
/*  870 */         return jjMoveStringLiteralDfa11_0(active0, 562949953421312L, active1, 0L);
/*      */       case 's':
/*  872 */         if ((active0 & 0x20000000000000L) != 0L)
/*  873 */           return jjStopAtPos(10, 53); 
/*  874 */         return jjMoveStringLiteralDfa11_0(active0, 18014398509481984L, active1, 0L);
/*      */       case 't':
/*  876 */         if ((active0 & 0x100000000000L) != 0L)
/*  877 */           return jjStopAtPos(10, 44); 
/*      */         break;
/*      */       case 'y':
/*  880 */         if ((active1 & 0x400L) != 0L) {
/*  881 */           return jjStopAtPos(10, 74);
/*      */         }
/*      */         break;
/*      */     } 
/*      */     
/*  886 */     return jjStartNfa_0(9, active0, active1);
/*      */   }
/*      */   
/*      */   private static final int jjMoveStringLiteralDfa11_0(long old0, long active0, long old1, long active1) {
/*  890 */     if ((active0 &= old0 | active1 &= old1) == 0L)
/*  891 */       return jjStartNfa_0(9, old0, old1); 
/*      */     try {
/*  893 */       curChar = SimpleCharStream.readChar();
/*  894 */     } catch (IOException e) {
/*  895 */       jjStopStringLiteralDfa_0(10, active0, active1);
/*  896 */       return 11;
/*      */     } 
/*  898 */     switch (curChar) {
/*      */       case 'D':
/*  900 */         return jjMoveStringLiteralDfa12_0(active0, 18014398509481984L, active1, 0L);
/*      */       case 'E':
/*  902 */         return jjMoveStringLiteralDfa12_0(active0, 16777216L, active1, 0L);
/*      */       case 'L':
/*  904 */         if ((active0 & 0x800000L) != 0L)
/*  905 */           return jjStopAtPos(11, 23); 
/*      */         break;
/*      */       case 'M':
/*  908 */         return jjMoveStringLiteralDfa12_0(active0, 33554432L, active1, 0L);
/*      */       case 'a':
/*  910 */         return jjMoveStringLiteralDfa12_0(active0, 563035856961536L, active1, 0L);
/*      */       case 'b':
/*  912 */         return jjMoveStringLiteralDfa12_0(active0, 0L, active1, 8192L);
/*      */       case 'e':
/*  914 */         if ((active0 & 0x800000000000L) != 0L)
/*  915 */           return jjStopAtPos(11, 47); 
/*  916 */         if ((active0 & 0x1000000000000L) != 0L)
/*  917 */           return jjStopAtPos(11, 48); 
/*  918 */         return jjMoveStringLiteralDfa12_0(active0, 2251799813685248L, active1, 0L);
/*      */       case 'l':
/*  920 */         return jjMoveStringLiteralDfa12_0(active0, 137438953472L, active1, 0L);
/*      */       case 'm':
/*  922 */         return jjMoveStringLiteralDfa12_0(active0, 2228224L, active1, 2048L);
/*      */       case 'n':
/*  924 */         return jjMoveStringLiteralDfa12_0(active0, 0L, active1, 512L);
/*      */       case 'o':
/*  926 */         return jjMoveStringLiteralDfa12_0(active0, 274877906944L, active1, 0L);
/*      */       case 'p':
/*  928 */         return jjMoveStringLiteralDfa12_0(active0, 0L, active1, 128L);
/*      */       case 'r':
/*  930 */         return jjMoveStringLiteralDfa12_0(active0, 72057594038190080L, active1, 0L);
/*      */       case 's':
/*  932 */         return jjMoveStringLiteralDfa12_0(active0, 0L, active1, 1048576L);
/*      */       case 't':
/*  934 */         return jjMoveStringLiteralDfa12_0(active0, 1048576L, active1, 0L);
/*      */       case 'u':
/*  936 */         return jjMoveStringLiteralDfa12_0(active0, 0L, active1, 131072L);
/*      */       case 'y':
/*  938 */         return jjMoveStringLiteralDfa12_0(active0, 1125904201809920L, active1, 0L);
/*      */     } 
/*      */ 
/*      */     
/*  942 */     return jjStartNfa_0(10, active0, active1);
/*      */   }
/*      */   
/*      */   private static final int jjMoveStringLiteralDfa12_0(long old0, long active0, long old1, long active1) {
/*  946 */     if ((active0 &= old0 | active1 &= old1) == 0L)
/*  947 */       return jjStartNfa_0(10, old0, old1); 
/*      */     try {
/*  949 */       curChar = SimpleCharStream.readChar();
/*  950 */     } catch (IOException e) {
/*  951 */       jjStopStringLiteralDfa_0(11, active0, active1);
/*  952 */       return 12;
/*      */     } 
/*  954 */     switch (curChar) {
/*      */       case 'A':
/*  956 */         return jjMoveStringLiteralDfa13_0(active0, 16777216L, active1, 0L);
/*      */       case 'B':
/*  958 */         return jjMoveStringLiteralDfa13_0(active0, 72057594071482368L, active1, 0L);
/*      */       case 'D':
/*  960 */         return jjMoveStringLiteralDfa13_0(active0, 2251799813685248L, active1, 0L);
/*      */       case '_':
/*  962 */         return jjMoveStringLiteralDfa13_0(active0, 0L, active1, 1048576L);
/*      */       case 'a':
/*  964 */         return jjMoveStringLiteralDfa13_0(active0, 137438953472L, active1, 512L);
/*      */       case 'c':
/*  966 */         return jjMoveStringLiteralDfa13_0(active0, 68719476736L, active1, 0L);
/*      */       case 'd':
/*  968 */         return jjMoveStringLiteralDfa13_0(active0, 274877906944L, active1, 0L);
/*      */       case 'e':
/*  970 */         if ((active0 & 0x20000L) != 0L)
/*  971 */           return jjStopAtPos(12, 17); 
/*  972 */         if ((active0 & 0x100000L) != 0L)
/*  973 */           return jjStopAtPos(12, 20); 
/*  974 */         if ((active0 & 0x200000L) != 0L)
/*  975 */           return jjStopAtPos(12, 21); 
/*  976 */         return jjMoveStringLiteralDfa13_0(active0, 18014398509481984L, active1, 2048L);
/*      */       case 'l':
/*  978 */         return jjMoveStringLiteralDfa13_0(active0, 0L, active1, 8192L);
/*      */       case 'm':
/*  980 */         return jjMoveStringLiteralDfa13_0(active0, 17184063488L, active1, 131072L);
/*      */       case 'p':
/*  982 */         return jjMoveStringLiteralDfa13_0(active0, 1125904201809920L, active1, 0L);
/*      */       case 's':
/*  984 */         return jjMoveStringLiteralDfa13_0(active0, 262144L, active1, 0L);
/*      */       case 't':
/*  986 */         return jjMoveStringLiteralDfa13_0(active0, 562949953421312L, active1, 128L);
/*      */     } 
/*      */ 
/*      */     
/*  990 */     return jjStartNfa_0(11, active0, active1);
/*      */   }
/*      */   
/*      */   private static final int jjMoveStringLiteralDfa13_0(long old0, long active0, long old1, long active1) {
/*  994 */     if ((active0 &= old0 | active1 &= old1) == 0L)
/*  995 */       return jjStartNfa_0(11, old0, old1); 
/*      */     try {
/*  997 */       curChar = SimpleCharStream.readChar();
/*  998 */     } catch (IOException e) {
/*  999 */       jjStopStringLiteralDfa_0(12, active0, active1);
/* 1000 */       return 13;
/*      */     } 
/* 1002 */     switch (curChar) {
/*      */       case 'E':
/* 1004 */         return jjMoveStringLiteralDfa14_0(active0, 33554432L, active1, 0L);
/*      */       case 'R':
/* 1006 */         return jjMoveStringLiteralDfa14_0(active0, 16777216L, active1, 0L);
/*      */       case 'b':
/* 1008 */         return jjMoveStringLiteralDfa14_0(active0, 0L, active1, 131072L);
/*      */       case 'e':
/* 1010 */         if ((active0 & 0x400000L) != 0L)
/* 1011 */           return jjStopAtPos(13, 22); 
/* 1012 */         if ((active0 & 0x100000000L) != 0L)
/* 1013 */           return jjStopAtPos(13, 32); 
/* 1014 */         if ((active0 & 0x4000000000000L) != 0L)
/* 1015 */           return jjStopAtPos(13, 50); 
/* 1016 */         if ((active1 & 0x2000L) != 0L)
/* 1017 */           return jjStopAtPos(13, 77); 
/* 1018 */         return jjMoveStringLiteralDfa14_0(active0, 2251816993554432L, active1, 0L);
/*      */       case 'f':
/* 1020 */         return jjMoveStringLiteralDfa14_0(active0, 18014398509481984L, active1, 0L);
/*      */       case 'i':
/* 1022 */         return jjMoveStringLiteralDfa14_0(active0, 262144L, active1, 128L);
/*      */       case 'k':
/* 1024 */         return jjMoveStringLiteralDfa14_0(active0, 68719476736L, active1, 0L);
/*      */       case 'm':
/* 1026 */         return jjMoveStringLiteralDfa14_0(active0, 0L, active1, 512L);
/*      */       case 'n':
/* 1028 */         return jjMoveStringLiteralDfa14_0(active0, 0L, active1, 1048576L);
/*      */       case 'o':
/* 1030 */         return jjMoveStringLiteralDfa14_0(active0, 72620543991349248L, active1, 0L);
/*      */       case 's':
/* 1032 */         return jjMoveStringLiteralDfa14_0(active0, 137438953472L, active1, 0L);
/*      */       case 't':
/* 1034 */         return jjMoveStringLiteralDfa14_0(active0, 0L, active1, 2048L);
/*      */       case 'y':
/* 1036 */         if ((active0 & 0x4000000000L) != 0L) {
/* 1037 */           return jjStopAtPos(13, 38);
/*      */         }
/*      */         break;
/*      */     } 
/*      */     
/* 1042 */     return jjStartNfa_0(12, active0, active1);
/*      */   }
/*      */   
/*      */   private static final int jjMoveStringLiteralDfa14_0(long old0, long active0, long old1, long active1) {
/* 1046 */     if ((active0 &= old0 | active1 &= old1) == 0L)
/* 1047 */       return jjStartNfa_0(12, old0, old1); 
/*      */     try {
/* 1049 */       curChar = SimpleCharStream.readChar();
/* 1050 */     } catch (IOException e) {
/* 1051 */       jjStopStringLiteralDfa_0(13, active0, active1);
/* 1052 */       return 14;
/*      */     } 
/* 1054 */     switch (curChar) {
/*      */       case 'E':
/* 1056 */         return jjMoveStringLiteralDfa15_0(active0, 16777216L, active1, 0L);
/*      */       case 'R':
/* 1058 */         if ((active0 & 0x2000000L) != 0L)
/* 1059 */           return jjStopAtPos(14, 25); 
/*      */         break;
/*      */       case 'a':
/* 1062 */         return jjMoveStringLiteralDfa15_0(active0, 68719476736L, active1, 0L);
/*      */       case 'd':
/* 1064 */         return jjMoveStringLiteralDfa15_0(active0, 72057594037927936L, active1, 0L);
/*      */       case 'e':
/* 1066 */         if ((active1 & 0x200L) != 0L)
/* 1067 */           return jjStopAtPos(14, 73); 
/* 1068 */         return jjMoveStringLiteralDfa15_0(active0, 0L, active1, 133120L);
/*      */       case 'f':
/* 1070 */         return jjMoveStringLiteralDfa15_0(active0, 2251799813685248L, active1, 0L);
/*      */       case 'i':
/* 1072 */         return jjMoveStringLiteralDfa15_0(active0, 18014398509481984L, active1, 0L);
/*      */       case 'o':
/* 1074 */         return jjMoveStringLiteralDfa15_0(active0, 262144L, active1, 128L);
/*      */       case 'r':
/* 1076 */         if ((active0 & 0x2000000000000L) != 0L)
/* 1077 */           return jjStopAtPos(14, 49); 
/*      */         break;
/*      */       case 's':
/* 1080 */         if ((active0 & 0x2000000000L) != 0L)
/* 1081 */           return jjStopAtPos(14, 37); 
/* 1082 */         return jjMoveStringLiteralDfa15_0(active0, 17179869184L, active1, 0L);
/*      */       case 'u':
/* 1084 */         return jjMoveStringLiteralDfa15_0(active0, 0L, active1, 1048576L);
/*      */     } 
/*      */ 
/*      */     
/* 1088 */     return jjStartNfa_0(13, active0, active1);
/*      */   }
/*      */   
/*      */   private static final int jjMoveStringLiteralDfa15_0(long old0, long active0, long old1, long active1) {
/* 1092 */     if ((active0 &= old0 | active1 &= old1) == 0L)
/* 1093 */       return jjStartNfa_0(13, old0, old1); 
/*      */     try {
/* 1095 */       curChar = SimpleCharStream.readChar();
/* 1096 */     } catch (IOException e) {
/* 1097 */       jjStopStringLiteralDfa_0(14, active0, active1);
/* 1098 */       return 15;
/*      */     } 
/* 1100 */     switch (curChar) {
/*      */       case 'F':
/* 1102 */         return jjMoveStringLiteralDfa16_0(active0, 16777216L, active1, 0L);
/*      */       case 'g':
/* 1104 */         return jjMoveStringLiteralDfa16_0(active0, 68719476736L, active1, 0L);
/*      */       case 'i':
/* 1106 */         return jjMoveStringLiteralDfa16_0(active0, 2251799813685248L, active1, 0L);
/*      */       case 'm':
/* 1108 */         return jjMoveStringLiteralDfa16_0(active0, 0L, active1, 1048576L);
/*      */       case 'n':
/* 1110 */         if ((active0 & 0x40000L) != 0L)
/* 1111 */           return jjStopAtPos(15, 18); 
/* 1112 */         if ((active1 & 0x80L) != 0L)
/* 1113 */           return jjStopAtPos(15, 71); 
/* 1114 */         return jjMoveStringLiteralDfa16_0(active0, 18014398509481984L, active1, 0L);
/*      */       case 'p':
/* 1116 */         return jjMoveStringLiteralDfa16_0(active0, 17179869184L, active1, 0L);
/*      */       case 'r':
/* 1118 */         if ((active1 & 0x800L) != 0L)
/* 1119 */           return jjStopAtPos(15, 75); 
/* 1120 */         if ((active1 & 0x20000L) != 0L)
/* 1121 */           return jjStopAtPos(15, 81); 
/*      */         break;
/*      */       case 'y':
/* 1124 */         if ((active0 & 0x100000000000000L) != 0L) {
/* 1125 */           return jjStopAtPos(15, 56);
/*      */         }
/*      */         break;
/*      */     } 
/*      */     
/* 1130 */     return jjStartNfa_0(14, active0, active1);
/*      */   }
/*      */   
/*      */   private static final int jjMoveStringLiteralDfa16_0(long old0, long active0, long old1, long active1) {
/* 1134 */     if ((active0 &= old0 | active1 &= old1) == 0L)
/* 1135 */       return jjStartNfa_0(14, old0, old1); 
/*      */     try {
/* 1137 */       curChar = SimpleCharStream.readChar();
/* 1138 */     } catch (IOException e) {
/* 1139 */       jjStopStringLiteralDfa_0(15, active0, active1);
/* 1140 */       return 16;
/*      */     } 
/* 1142 */     switch (curChar) {
/*      */       case 'E':
/* 1144 */         return jjMoveStringLiteralDfa17_0(active0, 16777216L, active1, 0L);
/*      */       case 'a':
/* 1146 */         return jjMoveStringLiteralDfa17_0(active0, 17179869184L, active1, 0L);
/*      */       case 'b':
/* 1148 */         return jjMoveStringLiteralDfa17_0(active0, 0L, active1, 1048576L);
/*      */       case 'e':
/* 1150 */         if ((active0 & 0x1000000000L) != 0L)
/* 1151 */           return jjStopAtPos(16, 36); 
/*      */         break;
/*      */       case 'i':
/* 1154 */         return jjMoveStringLiteralDfa17_0(active0, 18014398509481984L, active1, 0L);
/*      */       case 'n':
/* 1156 */         return jjMoveStringLiteralDfa17_0(active0, 2251799813685248L, active1, 0L);
/*      */     } 
/*      */ 
/*      */     
/* 1160 */     return jjStartNfa_0(15, active0, active1);
/*      */   }
/*      */   
/*      */   private static final int jjMoveStringLiteralDfa17_0(long old0, long active0, long old1, long active1) {
/* 1164 */     if ((active0 &= old0 | active1 &= old1) == 0L)
/* 1165 */       return jjStartNfa_0(15, old0, old1); 
/*      */     try {
/* 1167 */       curChar = SimpleCharStream.readChar();
/* 1168 */     } catch (IOException e) {
/* 1169 */       jjStopStringLiteralDfa_0(16, active0, active1);
/* 1170 */       return 17;
/*      */     } 
/* 1172 */     switch (curChar) {
/*      */       case 'R':
/* 1174 */         return jjMoveStringLiteralDfa18_0(active0, 16777216L, active1, 0L);
/*      */       case 'c':
/* 1176 */         return jjMoveStringLiteralDfa18_0(active0, 17179869184L, active1, 0L);
/*      */       case 'e':
/* 1178 */         return jjMoveStringLiteralDfa18_0(active0, 0L, active1, 1048576L);
/*      */       case 'i':
/* 1180 */         return jjMoveStringLiteralDfa18_0(active0, 2251799813685248L, active1, 0L);
/*      */       case 't':
/* 1182 */         return jjMoveStringLiteralDfa18_0(active0, 18014398509481984L, active1, 0L);
/*      */     } 
/*      */ 
/*      */     
/* 1186 */     return jjStartNfa_0(16, active0, active1);
/*      */   }
/*      */   
/*      */   private static final int jjMoveStringLiteralDfa18_0(long old0, long active0, long old1, long active1) {
/* 1190 */     if ((active0 &= old0 | active1 &= old1) == 0L)
/* 1191 */       return jjStartNfa_0(16, old0, old1); 
/*      */     try {
/* 1193 */       curChar = SimpleCharStream.readChar();
/* 1194 */     } catch (IOException e) {
/* 1195 */       jjStopStringLiteralDfa_0(17, active0, active1);
/* 1196 */       return 18;
/*      */     } 
/* 1198 */     switch (curChar) {
/*      */       case 'E':
/* 1200 */         return jjMoveStringLiteralDfa19_0(active0, 16777216L, active1, 0L);
/*      */       case 'e':
/* 1202 */         if ((active0 & 0x400000000L) != 0L)
/* 1203 */           return jjStopAtPos(18, 34); 
/*      */         break;
/*      */       case 'i':
/* 1206 */         return jjMoveStringLiteralDfa19_0(active0, 18014398509481984L, active1, 0L);
/*      */       case 'r':
/* 1208 */         if ((active1 & 0x100000L) != 0L)
/* 1209 */           return jjStopAtPos(18, 84); 
/*      */         break;
/*      */       case 't':
/* 1212 */         return jjMoveStringLiteralDfa19_0(active0, 2251799813685248L, active1, 0L);
/*      */     } 
/*      */ 
/*      */     
/* 1216 */     return jjStartNfa_0(17, active0, active1);
/*      */   }
/*      */   
/*      */   private static final int jjMoveStringLiteralDfa19_0(long old0, long active0, long old1, long active1) {
/* 1220 */     if ((active0 &= old0 | active1 &= old1) == 0L)
/* 1221 */       return jjStartNfa_0(17, old0, old1); 
/*      */     try {
/* 1223 */       curChar = SimpleCharStream.readChar();
/* 1224 */     } catch (IOException e) {
/* 1225 */       jjStopStringLiteralDfa_0(18, active0, 0L);
/* 1226 */       return 19;
/*      */     } 
/* 1228 */     switch (curChar) {
/*      */       case 'N':
/* 1230 */         return jjMoveStringLiteralDfa20_0(active0, 16777216L);
/*      */       case 'i':
/* 1232 */         return jjMoveStringLiteralDfa20_0(active0, 2251799813685248L);
/*      */       case 'o':
/* 1234 */         return jjMoveStringLiteralDfa20_0(active0, 18014398509481984L);
/*      */     } 
/*      */ 
/*      */     
/* 1238 */     return jjStartNfa_0(18, active0, 0L);
/*      */   }
/*      */   
/*      */   private static final int jjMoveStringLiteralDfa20_0(long old0, long active0) {
/* 1242 */     if ((active0 &= old0) == 0L)
/* 1243 */       return jjStartNfa_0(18, old0, 0L); 
/*      */     try {
/* 1245 */       curChar = SimpleCharStream.readChar();
/* 1246 */     } catch (IOException e) {
/* 1247 */       jjStopStringLiteralDfa_0(19, active0, 0L);
/* 1248 */       return 20;
/*      */     } 
/* 1250 */     switch (curChar) {
/*      */       case 'C':
/* 1252 */         return jjMoveStringLiteralDfa21_0(active0, 16777216L);
/*      */       case 'n':
/* 1254 */         if ((active0 & 0x40000000000000L) != 0L)
/* 1255 */           return jjStopAtPos(20, 54); 
/*      */         break;
/*      */       case 'o':
/* 1258 */         return jjMoveStringLiteralDfa21_0(active0, 2251799813685248L);
/*      */     } 
/*      */ 
/*      */     
/* 1262 */     return jjStartNfa_0(19, active0, 0L);
/*      */   }
/*      */   
/*      */   private static final int jjMoveStringLiteralDfa21_0(long old0, long active0) {
/* 1266 */     if ((active0 &= old0) == 0L)
/* 1267 */       return jjStartNfa_0(19, old0, 0L); 
/*      */     try {
/* 1269 */       curChar = SimpleCharStream.readChar();
/* 1270 */     } catch (IOException e) {
/* 1271 */       jjStopStringLiteralDfa_0(20, active0, 0L);
/* 1272 */       return 21;
/*      */     } 
/* 1274 */     switch (curChar) {
/*      */       case 'E':
/* 1276 */         if ((active0 & 0x1000000L) != 0L)
/* 1277 */           return jjStopAtPos(21, 24); 
/*      */         break;
/*      */       case 'n':
/* 1280 */         if ((active0 & 0x8000000000000L) != 0L) {
/* 1281 */           return jjStopAtPos(21, 51);
/*      */         }
/*      */         break;
/*      */     } 
/*      */     
/* 1286 */     return jjStartNfa_0(20, active0, 0L);
/*      */   }
/*      */   
/*      */   private static final void jjCheckNAdd(int state) {
/* 1290 */     if (jjrounds[state] != jjround) {
/* 1291 */       jjstateSet[jjnewStateCnt++] = state;
/* 1292 */       jjrounds[state] = jjround;
/*      */     } 
/*      */   }
/*      */   
/*      */   private static final void jjAddStates(int start, int end) {
/*      */     do {
/* 1298 */       jjstateSet[jjnewStateCnt++] = jjnextStates[start];
/* 1299 */     } while (start++ != end);
/*      */   }
/*      */   
/*      */   private static final void jjCheckNAddTwoStates(int state1, int state2) {
/* 1303 */     jjCheckNAdd(state1);
/* 1304 */     jjCheckNAdd(state2);
/*      */   }
/*      */   
/*      */   private static final void jjCheckNAddStates(int start, int end) {
/*      */     do {
/* 1309 */       jjCheckNAdd(jjnextStates[start]);
/* 1310 */     } while (start++ != end);
/*      */   }
/*      */   
/*      */   private static final void jjCheckNAddStates(int start) {
/* 1314 */     jjCheckNAdd(jjnextStates[start]);
/* 1315 */     jjCheckNAdd(jjnextStates[start + 1]);
/*      */   }
/*      */ 
/*      */   
/*      */   private static final int jjMoveNfa_0(int startState, int curPos) {
/* 1320 */     int startsAt = 0;
/* 1321 */     jjnewStateCnt = 13;
/* 1322 */     int i = 1;
/* 1323 */     jjstateSet[0] = startState;
/* 1324 */     int kind = Integer.MAX_VALUE;
/*      */     while (true) {
/* 1326 */       if (++jjround == Integer.MAX_VALUE)
/* 1327 */         ReInitRounds(); 
/* 1328 */       if (curChar < '@') {
/* 1329 */         long l = 1L << curChar;
/*      */         do {
/* 1331 */           switch (jjstateSet[--i]) {
/*      */             case 11:
/* 1333 */               if (curChar == '"' && 
/* 1334 */                 kind > 7) {
/* 1335 */                 kind = 7;
/*      */               }
/* 1337 */               if (curChar == '"' && 
/* 1338 */                 kind > 6) {
/* 1339 */                 kind = 6;
/*      */               }
/*      */               break;
/*      */             case 14:
/* 1343 */               if ((0x3FF800000000000L & l) != 0L) {
/* 1344 */                 if (kind > 9)
/* 1345 */                   kind = 9; 
/* 1346 */                 jjCheckNAdd(9);
/*      */               } 
/* 1348 */               if ((0x3FF000000000000L & l) != 0L) {
/* 1349 */                 if (kind > 8)
/* 1350 */                   kind = 8; 
/* 1351 */                 jjCheckNAdd(7);
/*      */               } 
/* 1353 */               if ((0x3FF000000000000L & l) != 0L) {
/* 1354 */                 if (kind > 5)
/* 1355 */                   kind = 5; 
/* 1356 */                 jjCheckNAdd(1);
/*      */               } 
/*      */               break;
/*      */             case 13:
/* 1360 */               if ((0x3FF800000000000L & l) != 0L) {
/* 1361 */                 if (kind > 9)
/* 1362 */                   kind = 9; 
/* 1363 */                 jjCheckNAdd(9);
/*      */               } 
/* 1365 */               if ((0x3FF000000000000L & l) != 0L) {
/* 1366 */                 if (kind > 5)
/* 1367 */                   kind = 5; 
/* 1368 */                 jjCheckNAdd(1);
/*      */               } 
/*      */               break;
/*      */             case 0:
/* 1372 */               if ((0x3FF200000000000L & l) != 0L) {
/* 1373 */                 if (kind > 5)
/* 1374 */                   kind = 5; 
/* 1375 */                 jjCheckNAdd(1);
/* 1376 */               } else if (curChar == '"') {
/* 1377 */                 jjAddStates(0, 1);
/* 1378 */               }  if ((0x3FF000000000000L & l) != 0L) {
/* 1379 */                 if (kind > 9)
/* 1380 */                   kind = 9; 
/* 1381 */                 jjCheckNAdd(9);
/* 1382 */               } else if ((0x1000200800000000L & l) != 0L) {
/* 1383 */                 if (kind > 6)
/* 1384 */                   kind = 6; 
/* 1385 */                 jjCheckNAdd(3);
/*      */               } 
/* 1387 */               if ((0x3FE000000000000L & l) != 0L) {
/* 1388 */                 if (kind > 8)
/* 1389 */                   kind = 8; 
/* 1390 */                 jjCheckNAdd(7); break;
/* 1391 */               }  if ((0x1000200000000000L & l) != 0L) {
/* 1392 */                 if (kind > 7)
/* 1393 */                   kind = 7; 
/* 1394 */                 jjCheckNAdd(5);
/*      */               } 
/*      */               break;
/*      */             case 1:
/* 1398 */               if ((0x3FF000000000000L & l) == 0L)
/*      */                 break; 
/* 1400 */               if (kind > 5)
/* 1401 */                 kind = 5; 
/* 1402 */               jjCheckNAdd(1);
/*      */               break;
/*      */             case 2:
/* 1405 */               if ((0x1000200800000000L & l) == 0L)
/*      */                 break; 
/* 1407 */               if (kind > 6)
/* 1408 */                 kind = 6; 
/* 1409 */               jjCheckNAdd(3);
/*      */               break;
/*      */             case 3:
/* 1412 */               if ((0x57FFF01000000000L & l) == 0L)
/*      */                 break; 
/* 1414 */               if (kind > 6)
/* 1415 */                 kind = 6; 
/* 1416 */               jjCheckNAdd(3);
/*      */               break;
/*      */             case 4:
/* 1419 */               if ((0x1000200000000000L & l) == 0L)
/*      */                 break; 
/* 1421 */               if (kind > 7)
/* 1422 */                 kind = 7; 
/* 1423 */               jjCheckNAdd(5);
/*      */               break;
/*      */             case 5:
/* 1426 */               if ((0x57FF731000000000L & l) == 0L)
/*      */                 break; 
/* 1428 */               if (kind > 7)
/* 1429 */                 kind = 7; 
/* 1430 */               jjCheckNAdd(5);
/*      */               break;
/*      */             case 6:
/* 1433 */               if ((0x3FE000000000000L & l) == 0L)
/*      */                 break; 
/* 1435 */               if (kind > 8)
/* 1436 */                 kind = 8; 
/* 1437 */               jjCheckNAdd(7);
/*      */               break;
/*      */             case 7:
/* 1440 */               if ((0x3FF000000000000L & l) == 0L)
/*      */                 break; 
/* 1442 */               if (kind > 8)
/* 1443 */                 kind = 8; 
/* 1444 */               jjCheckNAdd(7);
/*      */               break;
/*      */             case 8:
/* 1447 */               if ((0x3FF000000000000L & l) == 0L)
/*      */                 break; 
/* 1449 */               if (kind > 9)
/* 1450 */                 kind = 9; 
/* 1451 */               jjCheckNAdd(9);
/*      */               break;
/*      */             case 9:
/* 1454 */               if ((0x3FF800000000000L & l) == 0L)
/*      */                 break; 
/* 1456 */               if (kind > 9)
/* 1457 */                 kind = 9; 
/* 1458 */               jjCheckNAdd(9);
/*      */               break;
/*      */             case 10:
/* 1461 */               if (curChar == '"')
/* 1462 */                 jjAddStates(0, 1); 
/*      */               break;
/*      */             case 12:
/* 1465 */               if (curChar == '"' && kind > 7) {
/* 1466 */                 kind = 7;
/*      */               }
/*      */               break;
/*      */           } 
/*      */         
/* 1471 */         } while (i != startsAt);
/* 1472 */       } else if (curChar < '') {
/* 1473 */         long l = 1L << (curChar & 0x3F);
/*      */         do {
/* 1475 */           switch (jjstateSet[--i]) {
/*      */             case 7:
/*      */             case 14:
/* 1478 */               if ((0x7FFFFFE87FFFFFEL & l) == 0L)
/*      */                 break; 
/* 1480 */               if (kind > 8)
/* 1481 */                 kind = 8; 
/* 1482 */               jjCheckNAdd(7);
/*      */               break;
/*      */             case 0:
/* 1485 */               if ((0x7FFFFFE87FFFFFEL & l) != 0L) {
/* 1486 */                 if (kind > 7)
/* 1487 */                   kind = 7; 
/* 1488 */                 jjCheckNAdd(5);
/*      */               } 
/* 1490 */               if ((0x7FFFFFE87FFFFFEL & l) != 0L) {
/* 1491 */                 if (kind > 6)
/* 1492 */                   kind = 6; 
/* 1493 */                 jjCheckNAdd(3);
/*      */               } 
/*      */               break;
/*      */             case 2:
/* 1497 */               if ((0x7FFFFFE87FFFFFEL & l) == 0L)
/*      */                 break; 
/* 1499 */               if (kind > 6)
/* 1500 */                 kind = 6; 
/* 1501 */               jjCheckNAdd(3);
/*      */               break;
/*      */             case 3:
/* 1504 */               if ((0x7FFFFFEBFFFFFFEL & l) == 0L)
/*      */                 break; 
/* 1506 */               if (kind > 6)
/* 1507 */                 kind = 6; 
/* 1508 */               jjCheckNAdd(3);
/*      */               break;
/*      */             case 4:
/* 1511 */               if ((0x7FFFFFE87FFFFFEL & l) == 0L)
/*      */                 break; 
/* 1513 */               if (kind > 7)
/* 1514 */                 kind = 7; 
/* 1515 */               jjCheckNAdd(5);
/*      */               break;
/*      */             case 5:
/* 1518 */               if ((0x7FFFFFEAFFFFFFEL & l) == 0L)
/*      */                 break; 
/* 1520 */               if (kind > 7)
/* 1521 */                 kind = 7; 
/* 1522 */               jjCheckNAdd(5);
/*      */               break;
/*      */           } 
/*      */ 
/*      */         
/* 1527 */         } while (i != startsAt);
/*      */       } else {
/* 1529 */         int i2 = (curChar & 0xFF) >> '\006';
/* 1530 */         long l2 = 1L << (curChar & 0x3F);
/*      */         do {
/* 1532 */           jjstateSet[--i];
/*      */ 
/*      */         
/*      */         }
/* 1536 */         while (i != startsAt);
/*      */       } 
/* 1538 */       if (kind != Integer.MAX_VALUE) {
/* 1539 */         jjmatchedKind = kind;
/* 1540 */         jjmatchedPos = curPos;
/* 1541 */         kind = Integer.MAX_VALUE;
/*      */       } 
/* 1543 */       curPos++;
/* 1544 */       if ((i = jjnewStateCnt) == (startsAt = 13 - (jjnewStateCnt = startsAt)))
/* 1545 */         return curPos; 
/*      */       try {
/* 1547 */         curChar = SimpleCharStream.readChar();
/* 1548 */       } catch (IOException e) {
/* 1549 */         return curPos;
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/* 1555 */   static final int[] jjnextStates = { 11, 12 };
/*      */   
/*      */   public static final String[] jjstrLiteralImages = { 
/* 1558 */       "", 
/* 1559 */       "(:MODEL", ")", "02.00.00", ",", "01.05.04", 
/* 1560 */       "(:HEADER", "(:SUMMARY", 
/* 1561 */       "(ExporterName", "(ExporterVersion", 
/* 1562 */       "1.00", "(ExporterDate", 
/* 1563 */       "(ExporterTime", "(PublisherName", 
/* 1564 */       "(:META-MODEL", 
/* 1565 */       "(:SUBJECTAREAREFERENCE", "(:VERSIONNUMBER", 
/* 1566 */       "01.00", "1.0", "(Package", "(name", 
/* 1567 */       "(statute", "(Namespace", 
/* 1568 */       "(PrimitiveType", "(uniqueName", 
/* 1569 */       "(belongsToNamespace", "(Class", 
/* 1570 */       "(belongsToPackage", "(belongsToClass", 
/* 1571 */       "(belongsToBody", "(file_name", 
/* 1572 */       "(start_line", "(start_char", 
/* 1573 */       "(end_line", "(end_char", 
/* 1574 */       "(isAbstract", "(isFinal", "(isStatic", 
/* 1575 */       "(isInterface", "(access_mode", 
/* 1576 */       "(ArrayDecorator", "(decoratedType", 
/* 1577 */       "(InheritanceDefinition", "(subclass", 
/* 1578 */       "(superclass", 
/* 1579 */       "(ImplementsDefinition", "(interface", 
/* 1580 */       "(InitializerBody", "(belongsTo", "(LOC", 
/* 1581 */       "(CYCLO", "(NOS", "(NODec", "(NOCmt", 
/* 1582 */       "(NOExc", "(NOExits", "(NOL", 
/* 1583 */       "(Attribute", "(type", "(Method", 
/* 1584 */       "(returnType", "(kindOf", 
/* 1585 */       "(ThrowsException", "(method", 
/* 1586 */       "(exception_name", "(MethodBody", 
/* 1587 */       "(FormalParameter", "(isExParam", 
/* 1588 */       "(LocalVariable", "(isBlock", "(Access", 
/* 1589 */       "(accessedIn", "(accesses_number", 
/* 1590 */       "(Call", "(invokedIn", 
/* 1591 */       "(invocations_number" };
/*      */   
/* 1593 */   public static final String[] lexStateNames = { "DEFAULT" };
/*      */ 
/*      */   
/* 1596 */   static final long[] jjtoToken = { -31L, 2097151L };
/*      */ 
/*      */   
/* 1599 */   static final long[] jjtoSkip = { 30L };
/*      */   
/*      */   protected static SimpleCharStream input_stream;
/* 1602 */   private static final int[] jjrounds = new int[13];
/* 1603 */   private static final int[] jjstateSet = new int[26];
/*      */   protected static char curChar;
/*      */   
/*      */   public CDIFParserTokenManager(SimpleCharStream stream) {
/* 1607 */     if (input_stream != null)
/* 1608 */       throw new TokenMgrError("ERROR: Second call to constructor of static lexer. You must use ReInit() to initialize the static variables.", 1); 
/* 1609 */     input_stream = stream;
/*      */   }
/*      */   
/*      */   public CDIFParserTokenManager(SimpleCharStream stream, int lexState) {
/* 1613 */     this(stream);
/* 1614 */     SwitchTo(lexState);
/*      */   }
/*      */   
/*      */   public static void ReInit(SimpleCharStream stream) {
/* 1618 */     jjmatchedPos = jjnewStateCnt = 0;
/* 1619 */     curLexState = defaultLexState;
/* 1620 */     input_stream = stream;
/* 1621 */     ReInitRounds();
/*      */   }
/*      */ 
/*      */   
/*      */   private static final void ReInitRounds() {
/* 1626 */     jjround = -2147483647;
/* 1627 */     for (i = 13; i-- > 0;)
/* 1628 */       jjrounds[i] = Integer.MIN_VALUE; 
/*      */   }
/*      */   
/*      */   public static void ReInit(SimpleCharStream stream, int lexState) {
/* 1632 */     ReInit(stream);
/* 1633 */     SwitchTo(lexState);
/*      */   }
/*      */   
/*      */   public static void SwitchTo(int lexState) {
/* 1637 */     if (lexState >= 1 || lexState < 0) {
/* 1638 */       throw new TokenMgrError("Error: Ignoring invalid lexical state : " + lexState + ". State unchanged.", 2);
/*      */     }
/* 1640 */     curLexState = lexState;
/*      */   }
/*      */   
/*      */   protected static Token jjFillToken() {
/* 1644 */     t = Token.newToken(jjmatchedKind);
/* 1645 */     t.kind = jjmatchedKind;
/* 1646 */     String im = jjstrLiteralImages[jjmatchedKind];
/* 1647 */     t.image = (im == null) ? SimpleCharStream.GetImage() : im;
/* 1648 */     t.beginLine = SimpleCharStream.getBeginLine();
/* 1649 */     t.beginColumn = SimpleCharStream.getBeginColumn();
/* 1650 */     t.endLine = SimpleCharStream.getEndLine();
/* 1651 */     t.endColumn = SimpleCharStream.getEndColumn();
/* 1652 */     return t;
/*      */   }
/*      */   
/* 1655 */   static int curLexState = 0;
/* 1656 */   static int defaultLexState = 0;
/*      */   
/*      */   static int jjnewStateCnt;
/*      */   static int jjround;
/*      */   static int jjmatchedPos;
/*      */   static int jjmatchedKind;
/*      */   
/*      */   public static Token getNextToken() {
/* 1664 */     Token specialToken = null;
/*      */     
/* 1666 */     int curPos = 0;
/*      */ 
/*      */     
/*      */     while (true) {
/*      */       try {
/* 1671 */         curChar = SimpleCharStream.BeginToken();
/* 1672 */       } catch (IOException e) {
/* 1673 */         jjmatchedKind = 0;
/* 1674 */         Token matchedToken = jjFillToken();
/* 1675 */         CommonTokenAction(matchedToken);
/* 1676 */         return matchedToken;
/*      */       } 
/*      */       
/*      */       try {
/* 1680 */         SimpleCharStream.backup(0);
/* 1681 */         while (curChar <= ' ' && (0x100000600L & 1L << curChar) != 0L)
/* 1682 */           curChar = SimpleCharStream.BeginToken(); 
/* 1683 */       } catch (IOException e1) {
/*      */         continue;
/*      */       } 
/* 1686 */       jjmatchedKind = Integer.MAX_VALUE;
/* 1687 */       jjmatchedPos = 0;
/* 1688 */       curPos = jjMoveStringLiteralDfa0_0();
/* 1689 */       if (jjmatchedKind != Integer.MAX_VALUE) {
/* 1690 */         if (jjmatchedPos + 1 < curPos)
/* 1691 */           SimpleCharStream.backup(curPos - jjmatchedPos - 1); 
/* 1692 */         if ((jjtoToken[jjmatchedKind >> 6] & 1L << (jjmatchedKind & 0x3F)) != 0L) {
/* 1693 */           Token matchedToken = jjFillToken();
/* 1694 */           CommonTokenAction(matchedToken);
/* 1695 */           return matchedToken;
/*      */         }  continue;
/*      */       } 
/*      */       break;
/*      */     } 
/* 1700 */     int error_line = SimpleCharStream.getEndLine();
/* 1701 */     int error_column = SimpleCharStream.getEndColumn();
/* 1702 */     String error_after = null;
/* 1703 */     boolean EOFSeen = false;
/*      */     try {
/* 1705 */       SimpleCharStream.readChar();
/* 1706 */       SimpleCharStream.backup(1);
/* 1707 */     } catch (IOException e1) {
/* 1708 */       EOFSeen = true;
/* 1709 */       error_after = (curPos <= 1) ? "" : SimpleCharStream.GetImage();
/* 1710 */       if (curChar == '\n' || curChar == '\r') {
/* 1711 */         error_line++;
/* 1712 */         error_column = 0;
/*      */       } else {
/* 1714 */         error_column++;
/*      */       } 
/* 1716 */     }  if (!EOFSeen) {
/* 1717 */       SimpleCharStream.backup(1);
/* 1718 */       error_after = (curPos <= 1) ? "" : SimpleCharStream.GetImage();
/*      */     } 
/* 1720 */     throw new TokenMgrError(EOFSeen, curLexState, error_line, error_column, error_after, curChar, 0);
/*      */   }
/*      */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\importer\cdif\CDIFParserTokenManager.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */