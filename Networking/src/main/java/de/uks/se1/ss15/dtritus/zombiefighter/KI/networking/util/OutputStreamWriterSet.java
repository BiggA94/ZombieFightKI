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
import java.io.OutputStreamWriter;
import java.util.Collection;

public class OutputStreamWriterSet extends SDMSet<OutputStreamWriter>
{

   public static final OutputStreamWriterSet EMPTY_SET = new OutputStreamWriterSet().withReadOnly(true);


   public OutputStreamWriterPO hasOutputStreamWriterPO()
   {
      return new OutputStreamWriterPO(this.toArray(new OutputStreamWriter[this.size()]));
   }


   @Override
   public String getEntryType()
   {
      return "java.io.OutputStreamWriter";
   }


   @SuppressWarnings("unchecked")
   public OutputStreamWriterSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<OutputStreamWriter>)value);
      }
      else if (value != null)
      {
         this.add((OutputStreamWriter) value);
      }
      
      return this;
   }
   
   public OutputStreamWriterSet without(OutputStreamWriter value)
   {
      this.remove(value);
      return this;
   }

}
