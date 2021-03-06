/*
   Copyright (c) 2015 Alexander 
   
   Permission is hereby granted, free of charge, to any person obtaining a copy of this software 
   and associated documentation files (the "Software"), to deal in the Software without restriction, 
   including without limitation the rights to use, copy, modify, merge, publish, distribute, 
   sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is 
   furnished to do so, subject to the following conditions: 
   
   The above copyright notice and this permission notice shall be included in all copies or 
   substantial portions of the Software. 
   
   The Software shall be used for Good, not Evil. 
   
   THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING 
   BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND 
   NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, 
   DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, 
   OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */
   
package de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util;

import org.sdmlib.models.modelsets.SDMSet;
import java.io.PrintWriter;
import java.util.Collection;

public class PrintWriterSet extends SDMSet<PrintWriter>
{

   public static final PrintWriterSet EMPTY_SET = new PrintWriterSet().withReadOnly(true);


   public PrintWriterPO hasPrintWriterPO()
   {
      return new PrintWriterPO(this.toArray(new PrintWriter[this.size()]));
   }


   @Override
   public String getEntryType()
   {
      return "java.io.PrintWriter";
   }


   @SuppressWarnings("unchecked")
   public PrintWriterSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<PrintWriter>)value);
      }
      else if (value != null)
      {
         this.add((PrintWriter) value);
      }
      
      return this;
   }
   
   public PrintWriterSet without(PrintWriter value)
   {
      this.remove(value);
      return this;
   }

}
