/*     */ package com.birosoft.liquid;
/*     */ 
/*     */ import java.awt.AWTEvent;
/*     */ import java.awt.Component;
/*     */ import java.awt.Container;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.FocusTraversalPolicy;
/*     */ import java.awt.KeyboardFocusManager;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.awt.event.MouseListener;
/*     */ import java.text.AttributedCharacterIterator;
/*     */ import java.text.DateFormat;
/*     */ import java.text.Format;
/*     */ import java.text.ParseException;
/*     */ import java.util.Map;
/*     */ import javax.swing.AbstractAction;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JFormattedTextField;
/*     */ import javax.swing.JSpinner;
/*     */ import javax.swing.SpinnerDateModel;
/*     */ import javax.swing.SwingUtilities;
/*     */ import javax.swing.Timer;
/*     */ import javax.swing.UIManager;
/*     */ import javax.swing.border.EmptyBorder;
/*     */ import javax.swing.plaf.ComponentUI;
/*     */ import javax.swing.plaf.basic.BasicSpinnerUI;
/*     */ import javax.swing.text.InternationalFormatter;
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
/*     */ public class LiquidSpinnerUI
/*     */   extends BasicSpinnerUI
/*     */ {
/*  54 */   private static final ArrowButtonHandler nextButtonHandler = new ArrowButtonHandler("increment", true);
/*  55 */   private static final ArrowButtonHandler previousButtonHandler = new ArrowButtonHandler("decrement", false);
/*  56 */   private static final Dimension zeroSize = new Dimension(0, 0);
/*     */ 
/*     */ 
/*     */   
/*  60 */   public static ComponentUI createUI(JComponent c) { return new LiquidSpinnerUI(); }
/*     */ 
/*     */ 
/*     */   
/*     */   protected Component createPreviousButton() {
/*  65 */     JButton b = new SpecialUIButton(new LiquidSpinnerButtonUI(5));
/*  66 */     b.addActionListener(previousButtonHandler);
/*  67 */     b.addMouseListener(previousButtonHandler);
/*  68 */     return b;
/*     */   }
/*     */ 
/*     */   
/*     */   protected Component createNextButton() {
/*  73 */     JButton b = new SpecialUIButton(new LiquidSpinnerButtonUI(1));
/*  74 */     b.addActionListener(nextButtonHandler);
/*  75 */     b.addMouseListener(nextButtonHandler);
/*  76 */     return b;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected JComponent createEditor() {
/*  84 */     JComponent editor = super.createEditor();
/*  85 */     if (editor instanceof JSpinner.DefaultEditor) {
/*     */       
/*  87 */       JSpinner.DefaultEditor de = (JSpinner.DefaultEditor)editor;
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  92 */       de.getTextField().setBorder(new EmptyBorder(0, 0, 0, 0));
/*     */       
/*  94 */       Dimension prefSize = de.getPreferredSize();
/*  95 */       int compHeight = prefSize.height;
/*     */       
/*  97 */       int height = LiquidSpinnerButtonUI.getSkin(1).getVsize() + LiquidSpinnerButtonUI.getSkin(5).getVsize();
/*  98 */       int diff = height - compHeight;
/*     */       
/* 100 */       if (diff > 0) {
/*     */         
/* 102 */         int half = diff / 2;
/* 103 */         de.getTextField().setBorder(new EmptyBorder(half, 0, diff - half, 0));
/*     */       } 
/*     */     } 
/* 106 */     return editor;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static class ArrowButtonHandler
/*     */     extends AbstractAction
/*     */     implements MouseListener
/*     */   {
/*     */     final Timer autoRepeatTimer;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     final boolean isNext;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 133 */     JSpinner spinner = null;
/*     */ 
/*     */     
/*     */     ArrowButtonHandler(String name, boolean isNext) {
/* 137 */       super(name);
/* 138 */       this.isNext = isNext;
/* 139 */       this.autoRepeatTimer = new Timer(60, this);
/* 140 */       this.autoRepeatTimer.setInitialDelay(300);
/*     */     }
/*     */ 
/*     */     
/*     */     private JSpinner eventToSpinner(AWTEvent e) {
/* 145 */       Object src = e.getSource();
/* 146 */       while (src instanceof Component && !(src instanceof JSpinner))
/*     */       {
/* 148 */         src = ((Component)src).getParent();
/*     */       }
/* 150 */       return (src instanceof JSpinner) ? (JSpinner)src : null;
/*     */     }
/*     */ 
/*     */     
/*     */     public void actionPerformed(ActionEvent e) {
/* 155 */       JSpinner spinner = this.spinner;
/*     */       
/* 157 */       if (!(e.getSource() instanceof Timer))
/*     */       {
/*     */         
/* 160 */         spinner = eventToSpinner(e);
/*     */       }
/* 162 */       if (spinner != null) {
/*     */         
/*     */         try {
/*     */           
/* 166 */           int calendarField = getCalendarField(spinner);
/* 167 */           spinner.commitEdit();
/* 168 */           if (calendarField != -1)
/*     */           {
/* 170 */             ((SpinnerDateModel)spinner.getModel()).setCalendarField(calendarField);
/*     */           }
/* 172 */           Object value = this.isNext ? spinner.getNextValue() : spinner.getPreviousValue();
/* 173 */           if (value != null) {
/*     */             
/* 175 */             spinner.setValue(value);
/* 176 */             select(spinner);
/*     */           } 
/* 178 */         } catch (IllegalArgumentException iae) {
/*     */           
/* 180 */           UIManager.getLookAndFeel().provideErrorFeedback(spinner);
/* 181 */         } catch (ParseException pe) {
/*     */           
/* 183 */           UIManager.getLookAndFeel().provideErrorFeedback(spinner);
/*     */         } 
/*     */       }
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private void select(JSpinner spinner) {
/* 194 */       JComponent editor = spinner.getEditor();
/*     */       
/* 196 */       if (editor instanceof JSpinner.DateEditor) {
/*     */         
/* 198 */         JSpinner.DateEditor dateEditor = (JSpinner.DateEditor)editor;
/* 199 */         JFormattedTextField ftf = dateEditor.getTextField();
/* 200 */         Format format = dateEditor.getFormat();
/*     */         
/*     */         Object value;
/* 203 */         if (format != null && (value = spinner.getValue()) != null) {
/*     */           
/* 205 */           SpinnerDateModel model = dateEditor.getModel();
/* 206 */           DateFormat.Field field = DateFormat.Field.ofCalendarField(model.getCalendarField());
/*     */           
/* 208 */           if (field != null) {
/*     */             
/*     */             try {
/*     */               
/* 212 */               AttributedCharacterIterator iterator = format.formatToCharacterIterator(value);
/* 213 */               if (!select(ftf, iterator, field) && field == DateFormat.Field.HOUR0)
/*     */               {
/* 215 */                 select(ftf, iterator, DateFormat.Field.HOUR1);
/*     */               }
/* 217 */             } catch (IllegalArgumentException iae) {}
/*     */           }
/*     */         } 
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private boolean select(JFormattedTextField ftf, AttributedCharacterIterator iterator, DateFormat.Field field) {
/* 231 */       int max = ftf.getDocument().getLength();
/*     */       
/* 233 */       iterator.first();
/*     */       
/*     */       do {
/* 236 */         Map attrs = iterator.getAttributes();
/*     */         
/* 238 */         if (attrs != null && attrs.containsKey(field)) {
/*     */           
/* 240 */           int start = iterator.getRunStart(field);
/* 241 */           int end = iterator.getRunLimit(field);
/*     */           
/* 243 */           if (start != -1 && end != -1 && start <= max && end <= max)
/*     */           {
/* 245 */             ftf.select(start, end);
/*     */           }
/* 247 */           return true;
/*     */         } 
/* 249 */       } while (iterator.next() != Character.MAX_VALUE);
/* 250 */       return false;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private int getCalendarField(JSpinner spinner) {
/* 260 */       JComponent editor = spinner.getEditor();
/*     */       
/* 262 */       if (editor instanceof JSpinner.DateEditor) {
/*     */         
/* 264 */         JSpinner.DateEditor dateEditor = (JSpinner.DateEditor)editor;
/* 265 */         JFormattedTextField ftf = dateEditor.getTextField();
/* 266 */         int start = ftf.getSelectionStart();
/* 267 */         JFormattedTextField.AbstractFormatter formatter = ftf.getFormatter();
/*     */         
/* 269 */         if (formatter instanceof InternationalFormatter) {
/*     */           
/* 271 */           Format.Field[] arrayOfField = ((InternationalFormatter)formatter).getFields(start);
/*     */           
/* 273 */           for (int counter = 0; counter < arrayOfField.length; counter++) {
/*     */             
/* 275 */             if (arrayOfField[counter] instanceof DateFormat.Field) {
/*     */               int calendarField;
/*     */ 
/*     */               
/* 279 */               if (arrayOfField[counter] == DateFormat.Field.HOUR1) {
/*     */                 
/* 281 */                 calendarField = 10;
/*     */               } else {
/*     */                 
/* 284 */                 calendarField = ((DateFormat.Field)arrayOfField[counter]).getCalendarField();
/*     */               } 
/* 286 */               if (calendarField != -1)
/*     */               {
/* 288 */                 return calendarField;
/*     */               }
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/* 294 */       return -1;
/*     */     }
/*     */ 
/*     */     
/*     */     public void mousePressed(MouseEvent e) {
/* 299 */       if (SwingUtilities.isLeftMouseButton(e) && e.getComponent().isEnabled()) {
/*     */         
/* 301 */         this.spinner = eventToSpinner(e);
/* 302 */         this.autoRepeatTimer.start();
/*     */         
/* 304 */         focusSpinnerIfNecessary();
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public void mouseReleased(MouseEvent e) {
/* 310 */       this.autoRepeatTimer.stop();
/* 311 */       this.spinner = null;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void mouseClicked(MouseEvent e) {}
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void mouseEntered(MouseEvent e) {}
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void mouseExited(MouseEvent e) {}
/*     */ 
/*     */ 
/*     */     
/*     */     private void focusSpinnerIfNecessary() {
/* 332 */       Component fo = KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusOwner();
/* 333 */       if (this.spinner.isRequestFocusEnabled() && (fo == null || !SwingUtilities.isDescendingFrom(fo, this.spinner))) {
/*     */         
/* 335 */         Container root = this.spinner;
/*     */         
/* 337 */         if (!root.isFocusCycleRoot())
/*     */         {
/* 339 */           root = root.getFocusCycleRootAncestor();
/*     */         }
/* 341 */         if (root != null) {
/*     */           
/* 343 */           FocusTraversalPolicy ftp = root.getFocusTraversalPolicy();
/* 344 */           Component child = ftp.getComponentAfter(root, this.spinner);
/*     */           
/* 346 */           if (child != null && SwingUtilities.isDescendingFrom(child, this.spinner))
/*     */           {
/* 348 */             child.requestFocus();
/*     */           }
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\liquidlnf.jar!\com\birosoft\liquid\LiquidSpinnerUI.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.0.7
 */