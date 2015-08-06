/*
   Copyright (c) 2015 Major 
   
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

package de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.util;

import org.sdmlib.models.modelsets.SDMSet;

import java.util.Collection;

import org.sdmlib.models.modelsets.StringList;

import de.uks.se1.ss15.dtritus.zombiefighter.KI.global.ZombieFighter;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.global.util.ZombieFighterSet;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.Message;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.User;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.util.UserSet;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util.TimestampSet;

import java.sql.Timestamp;

import org.sdmlib.models.modelsets.ObjectSet;

public class MessageSet extends SDMSet<Message> {

	public static final MessageSet EMPTY_SET = new MessageSet()
			.withReadOnly(true);

	public MessagePO hasMessagePO() {
		return new MessagePO(this.toArray(new Message[this.size()]));
	}

	@Override
	public String getEntryType() {
		return "de.uks.se1.ss15.dtritus.zombiefighter.model.Message";
	}

	@SuppressWarnings("unchecked")
	public MessageSet with(Object value) {
		if (value instanceof java.util.Collection) {
			this.addAll((Collection<Message>) value);
		} else if (value != null) {
			this.add((Message) value);
		}

		return this;
	}

	public MessageSet without(Message value) {
		this.remove(value);
		return this;
	}

	public StringList getText() {
		StringList result = new StringList();

		for (Message obj : this) {
			result.add(obj.getText());
		}

		return result;
	}

	public MessageSet hasText(String value) {
		MessageSet result = new MessageSet();

		for (Message obj : this) {
			if (value.equals(obj.getText())) {
				result.add(obj);
			}
		}

		return result;
	}

	public MessageSet hasText(String lower, String upper) {
		MessageSet result = new MessageSet();

		for (Message obj : this) {
			if (lower.compareTo(obj.getText()) <= 0
					&& obj.getText().compareTo(upper) <= 0) {
				result.add(obj);
			}
		}

		return result;
	}

	public MessageSet withText(String value) {
		for (Message obj : this) {
			obj.setText(value);
		}

		return this;
	}

	public TimestampSet getDate() {
		TimestampSet result = new TimestampSet();

		for (Message obj : this) {
			result.add(obj.getDate());
		}

		return result;
	}

	public MessageSet hasDate(Timestamp value) {
		MessageSet result = new MessageSet();

		for (Message obj : this) {
			if (value == obj.getDate()) {
				result.add(obj);
			}
		}

		return result;
	}

	public MessageSet withDate(Timestamp value) {
		for (Message obj : this) {
			obj.setDate(value);
		}

		return this;
	}

	public ZombieFighterSet getZombieFighter() {
		ZombieFighterSet result = new ZombieFighterSet();

		for (Message obj : this) {
			result.add(obj.getZombieFighter());
		}

		return result;
	}

	public MessageSet hasZombieFighter(Object value) {
		ObjectSet neighbors = new ObjectSet();

		if (value instanceof Collection) {
			neighbors.addAll((Collection<?>) value);
		} else {
			neighbors.add(value);
		}

		MessageSet answer = new MessageSet();

		for (Message obj : this) {
			if (neighbors.contains(obj.getZombieFighter())) {
				answer.add(obj);
			}
		}

		return answer;
	}

	public MessageSet withZombieFighter(ZombieFighter value) {
		for (Message obj : this) {
			obj.withZombieFighter(value);
		}

		return this;
	}

   public UserSet getToUser()
   {
      UserSet result = new UserSet();
      
      for (Message obj : this)
      {
         result.add(obj.getToUser());
      }
      
      return result;
   }

   public MessageSet hasToUser(Object value)
   {
      ObjectSet neighbors = new ObjectSet();

      if (value instanceof Collection)
      {
         neighbors.addAll((Collection<?>) value);
      }
      else
      {
         neighbors.add(value);
      }
      
      MessageSet answer = new MessageSet();
      
      for (Message obj : this)
      {
         if (neighbors.contains(obj.getToUser()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public MessageSet withToUser(User value)
   {
      for (Message obj : this)
      {
         obj.withToUser(value);
      }
      
      return this;
   }

   public UserSet getFromUser()
   {
      UserSet result = new UserSet();
      
      for (Message obj : this)
      {
         result.add(obj.getFromUser());
      }
      
      return result;
   }

   public MessageSet hasFromUser(Object value)
   {
      ObjectSet neighbors = new ObjectSet();

      if (value instanceof Collection)
      {
         neighbors.addAll((Collection<?>) value);
      }
      else
      {
         neighbors.add(value);
      }
      
      MessageSet answer = new MessageSet();
      
      for (Message obj : this)
      {
         if (neighbors.contains(obj.getFromUser()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public MessageSet withFromUser(User value)
   {
      for (Message obj : this)
      {
         obj.withFromUser(value);
      }
      
      return this;
   }

}
