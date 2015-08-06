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

package de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model;

import org.sdmlib.serialization.PropertyChangeInterface;

import de.uks.se1.ss15.dtritus.zombiefighter.KI.global.ZombieFighter;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.Message;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.util.MessageSet;

import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;
import java.sql.Timestamp;

import org.sdmlib.StrUtil;

public class User implements PropertyChangeInterface {

	// ==========================================================================

	protected PropertyChangeSupport listeners = new PropertyChangeSupport(this);

	@Override
	public PropertyChangeSupport getPropertyChangeSupport() {
		return listeners;
	}

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		getPropertyChangeSupport().addPropertyChangeListener(listener);
	}

	// ==========================================================================

	public void removeYou() {
		setGame(null);
		setTeam(null);
		setZombieFighter(null);
		withoutMessagesInbox(this.getMessagesInbox().toArray(
				new Message[this.getMessagesInbox().size()]));
		withoutMessagesOutbox(this.getMessagesOutbox().toArray(
				new Message[this.getMessagesOutbox().size()]));
		getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
	}

	// ==========================================================================

	public static final String PROPERTY_NICK = "nick";

	private String nick;

	public String getNick() {
		return this.nick;
	}

	public void setNick(String value) {
		if (!StrUtil.stringEquals(this.nick, value)) {
			String oldValue = this.nick;
			this.nick = value;
			getPropertyChangeSupport().firePropertyChange(PROPERTY_NICK,
					oldValue, value);
		}
	}

	public User withNick(String value) {
		setNick(value);
		return this;
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();

		result.append(" ").append(this.getNick());
		result.append(" ").append(this.getEmail());
		result.append(" ").append(this.getRole());
		result.append(" ").append(this.getState());
		return result.substring(1);
	}

	// ==========================================================================

	public static final String PROPERTY_EMAIL = "email";

	private String email;

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String value) {
		if (!StrUtil.stringEquals(this.email, value)) {
			String oldValue = this.email;
			this.email = value;
			getPropertyChangeSupport().firePropertyChange(PROPERTY_EMAIL,
					oldValue, value);
		}
	}

	public User withEmail(String value) {
		setEmail(value);
		return this;
	}

	// ==========================================================================

	public static final String PROPERTY_ROLE = "role";

	private String role;

	public String getRole() {
		return this.role;
	}

	public void setRole(String value) {
		if (!StrUtil.stringEquals(this.role, value)) {
			String oldValue = this.role;
			this.role = value;
			getPropertyChangeSupport().firePropertyChange(PROPERTY_ROLE,
					oldValue, value);
		}
	}

	public User withRole(String value) {
		setRole(value);
		return this;
	}

	// ==========================================================================

	public static final String PROPERTY_STATE = "state";

	private String state;

	public String getState() {
		return this.state;
	}

	public void setState(String value) {
		if (!StrUtil.stringEquals(this.state, value)) {
			String oldValue = this.state;
			this.state = value;
			getPropertyChangeSupport().firePropertyChange(PROPERTY_STATE,
					oldValue, value);
		}
	}

	public User withState(String value) {
		setState(value);
		return this;
	}

	/********************************************************************
	 * <pre>
	 *              many                       one
	 * User ----------------------------------- Game
	 *              user                   game
	 * </pre>
	 */

	public static final String PROPERTY_GAME = "game";

	private Game game = null;

	public Game getGame() {
		return this.game;
	}

	public boolean setGame(Game value) {
		boolean changed = false;

		if (this.game != value) {
			Game oldValue = this.game;

			if (this.game != null) {
				this.game = null;
				oldValue.withoutUser(this);
			}

			this.game = value;

			if (value != null) {
				value.withUser(this);
			}

			getPropertyChangeSupport().firePropertyChange(PROPERTY_GAME,
					oldValue, value);
			changed = true;
		}

		return changed;
	}

	public User withGame(Game value) {
		setGame(value);
		return this;
	}

	public Game createGame() {
		Game value = new Game();
		withGame(value);
		return value;
	}

	/********************************************************************
	 * <pre>
	 *              many                       one
	 * User ----------------------------------- Team
	 *              members                   team
	 * </pre>
	 */

	public static final String PROPERTY_TEAM = "team";

	private Team team = null;

	public Team getTeam() {
		return this.team;
	}

	public boolean setTeam(Team value) {
		boolean changed = false;

		if (this.team != value) {
			Team oldValue = this.team;

			if (this.team != null) {
				this.team = null;
				oldValue.withoutMembers(this);
			}

			this.team = value;

			if (value != null) {
				value.withMembers(this);
			}

			getPropertyChangeSupport().firePropertyChange(PROPERTY_TEAM,
					oldValue, value);
			changed = true;
		}

		return changed;
	}

	public User withTeam(Team value) {
		setTeam(value);
		return this;
	}

	public Team createTeam() {
		Team value = new Team();
		withTeam(value);
		return value;
	}

	/********************************************************************
	 * <pre>
	 *              many                       one
	 * User ----------------------------------- ZombieFighter
	 *              users                   zombieFighter
	 * </pre>
	 */

	public static final String PROPERTY_ZOMBIEFIGHTER = "zombieFighter";

	private ZombieFighter zombieFighter = null;

	public ZombieFighter getZombieFighter() {
		return this.zombieFighter;
	}

	public boolean setZombieFighter(ZombieFighter value) {
		boolean changed = false;

		if (this.zombieFighter != value) {
			ZombieFighter oldValue = this.zombieFighter;

			if (this.zombieFighter != null) {
				this.zombieFighter = null;
				oldValue.withoutUsers(this);
			}

			this.zombieFighter = value;

			if (value != null) {
				value.withUsers(this);
			}

			getPropertyChangeSupport().firePropertyChange(
					PROPERTY_ZOMBIEFIGHTER, oldValue, value);
			changed = true;
		}

		return changed;
	}

	public User withZombieFighter(ZombieFighter value) {
		setZombieFighter(value);
		return this;
	}

	public ZombieFighter createZombieFighter() {
		ZombieFighter value = new ZombieFighter();
		withZombieFighter(value);
		return value;
	}

	/********************************************************************
	 * <pre>
	 *              one                       many
	 * User ----------------------------------- Message
	 *              toUser                   messagesInbox
	 * </pre>
	 */

	public static final String PROPERTY_MESSAGESINBOX = "messagesInbox";

	private MessageSet messagesInbox = null;

	public MessageSet getMessagesInbox() {
		if (this.messagesInbox == null) {
			return MessageSet.EMPTY_SET;
		}

		return this.messagesInbox;
	}

	public User withMessagesInbox(Message... value) {
		if (value == null) {
			return this;
		}
		for (Message item : value) {
			if (item != null) {
				if (this.messagesInbox == null) {
					this.messagesInbox = new MessageSet();
				}

				boolean changed = this.messagesInbox.add(item);

				if (changed) {
					item.withToUser(this);
					getPropertyChangeSupport().firePropertyChange(
							PROPERTY_MESSAGESINBOX, null, item);
				}
			}
		}
		return this;
	}

	public User withoutMessagesInbox(Message... value) {
		for (Message item : value) {
			if ((this.messagesInbox != null) && (item != null)) {
				if (this.messagesInbox.remove(item)) {
					item.setToUser(null);
					getPropertyChangeSupport().firePropertyChange(
							PROPERTY_MESSAGESINBOX, item, null);
				}
			}
		}
		return this;
	}

	public Message createMessagesInbox() {
		Message value = new Message();
		withMessagesInbox(value);
		return value;
	}

	/********************************************************************
	 * <pre>
	 *              one                       many
	 * User ----------------------------------- Message
	 *              fromUser                   messagesOutbox
	 * </pre>
	 */

	public static final String PROPERTY_MESSAGESOUTBOX = "messagesOutbox";

	private MessageSet messagesOutbox = null;

	public MessageSet getMessagesOutbox() {
		if (this.messagesOutbox == null) {
			return MessageSet.EMPTY_SET;
		}

		return this.messagesOutbox;
	}

	public User withMessagesOutbox(Message... value) {
		if (value == null) {
			return this;
		}
		for (Message item : value) {
			if (item != null) {
				if (this.messagesOutbox == null) {
					this.messagesOutbox = new MessageSet();
				}

				boolean changed = this.messagesOutbox.add(item);

				if (changed) {
					item.withFromUser(this);
					getPropertyChangeSupport().firePropertyChange(
							PROPERTY_MESSAGESOUTBOX, null, item);
				}
			}
		}
		return this;
	}

	public User withoutMessagesOutbox(Message... value) {
		for (Message item : value) {
			if ((this.messagesOutbox != null) && (item != null)) {
				if (this.messagesOutbox.remove(item)) {
					item.setFromUser(null);
					getPropertyChangeSupport().firePropertyChange(
							PROPERTY_MESSAGESOUTBOX, item, null);
				}
			}
		}
		return this;
	}

	public Message createMessagesOutbox() {
		Message value = new Message();
		withMessagesOutbox(value);
		return value;
	}

	// ==========================================================================

	public Timestamp getLastInteraction() {
		// AllUser
		if (this.getNick().equals("AllUsers"))
			return new Timestamp(Long.MAX_VALUE);
		else if (this.getNick().equals("Team"))
			return new Timestamp(Long.MAX_VALUE - 1);
		// inbox and outbox is empty
		else if (this.getMessagesInbox().isEmpty()
				&& this.getMessagesOutbox().isEmpty()) {
			return new Timestamp(0);
		}
		// get the timestamp of the latest message
		long buffer = 0;
		for (Message message : this.getMessagesInbox()) {
			if (message.getDate() != null
					&& buffer < message.getDate().getTime()) {
				buffer = message.getDate().getTime();
			}
		}
		for (Message message : this.getMessagesOutbox()) {
			if (message.getDate() != null
					&& buffer < message.getDate().getTime()) {
				buffer = message.getDate().getTime();
			}
		}
		return new Timestamp(buffer);
	}

	// ==========================================================================

	public static final String PROPERTY_UNREADMESSAGES = "unreadMessages";

	private boolean unreadMessages = false;

	public boolean isUnreadMessages() {
		return this.unreadMessages;
	}

	public void setUnreadMessages(boolean value) {
		if (this.unreadMessages != value) {
			boolean oldValue = this.unreadMessages;
			this.unreadMessages = value;
			getPropertyChangeSupport().firePropertyChange(
					PROPERTY_UNREADMESSAGES, oldValue, value);
		}
	}

	public User withUnreadMessages(boolean value) {
		setUnreadMessages(value);
		return this;
	}

	public static final String PROPERTY_INGAME = "ingame";

	private boolean ingame = false;

	public boolean isIngame() {
		return this.ingame;
	}

	public void setIngame(boolean value) {
		if (this.ingame != value) {
			boolean oldValue = this.ingame;
			this.ingame = value;
			getPropertyChangeSupport().firePropertyChange(PROPERTY_INGAME,
					oldValue, value);
		}
	}

	public User withIngame(boolean value) {
		setIngame(value);
		return this;
	}

}
