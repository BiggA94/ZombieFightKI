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

package de.uks.se1.ss15.dtritus.zombiefighter.KI.global;

import org.sdmlib.serialization.PropertyChangeInterface;

import de.uks.se1.ss15.dtritus.zombiefighter.KI.gameClient.model.ZombieFightGame;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.global.util.ZFState;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.Game;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.Map;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.Message;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.Team;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.User;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.util.GameSet;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.util.MapSet;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.util.MessageSet;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.util.TeamSet;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.util.UserSet;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.MessageHandlerPool;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.ServerMessageHandler;

import java.beans.PropertyChangeSupport;
import java.io.File;
import java.net.ConnectException;
import java.beans.PropertyChangeListener;

public class ZombieFighter implements PropertyChangeInterface {

	public ZombieFighter() {
		this.setMessageHandlerPool(new MessageHandlerPool());
	}

	public void connect(String URL, int Port) throws ConnectException {
		this.setServerMessageHandler(new ServerMessageHandler(URL, Port));
		this.setMessageHandlerPool(this.ServerMessageHandler
				.getMessageHandlerPool());
	}

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
		setServerMessageHandler(null);
		setMessageHandlerPool(null);
		withoutGames(this.getGames().toArray(new Game[this.getGames().size()]));
		withoutMaps(this.getMaps().toArray(new Map[this.getMaps().size()]));
		withoutUsers(this.getUsers().toArray(new User[this.getUsers().size()]));
		withoutTeams(this.getTeams().toArray(new Team[this.getTeams().size()]));
		withoutMessages(this.getMessages().toArray(new Message[this.getMessages().size()]));
		setCurrentUser(null);
		getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
	}

	/********************************************************************
	 * <pre>
	 *              one                       one
	 * ZombieFighter ----------------------------------- ServerMessageHandler
	 *              ZombieFighter                   ServerMessageHandler
	 * </pre>
	 */

	public static final String PROPERTY_SERVERMESSAGEHANDLER = "ServerMessageHandler";

	private ServerMessageHandler ServerMessageHandler = null;

	public ServerMessageHandler getServerMessageHandler() {
		return this.ServerMessageHandler;
	}

	public boolean setServerMessageHandler(ServerMessageHandler value) {
		boolean changed = false;

		if (this.ServerMessageHandler != value) {
			ServerMessageHandler oldValue = this.ServerMessageHandler;

			if (this.ServerMessageHandler != null) {
				this.ServerMessageHandler = null;
				oldValue.setZombieFighter(null);
			}

			this.ServerMessageHandler = value;

			if (value != null) {
				value.withZombieFighter(this);
			}

			getPropertyChangeSupport().firePropertyChange(PROPERTY_SERVERMESSAGEHANDLER, oldValue, value);
			changed = true;
		}

		return changed;
	}

	public ZombieFighter withServerMessageHandler(ServerMessageHandler value) {
		setServerMessageHandler(value);
		return this;
	}

	public ServerMessageHandler createServerMessageHandler() {
		ServerMessageHandler value = new ServerMessageHandler();
		withServerMessageHandler(value);
		return value;
	}

	/********************************************************************
	 * <pre>
	 *              one                       one
	 * ZombieFighter ----------------------------------- MessageHandlerPool
	 *              ZombieFighter                   MessageHandlerPool
	 * </pre>
	 */

	public static final String PROPERTY_MESSAGEHANDLERPOOL = "MessageHandlerPool";

	private MessageHandlerPool MessageHandlerPool = null;

	public MessageHandlerPool getMessageHandlerPool() {
		return this.MessageHandlerPool;
	}

	public boolean setMessageHandlerPool(MessageHandlerPool value) {
		boolean changed = false;

		if (this.MessageHandlerPool != value) {
			MessageHandlerPool oldValue = this.MessageHandlerPool;

			if (this.MessageHandlerPool != null) {
				this.MessageHandlerPool = null;
				oldValue.setZombieFighter(null);
			}

			this.MessageHandlerPool = value;

			if (value != null) {
				value.withZombieFighter(this);
			}

			getPropertyChangeSupport().firePropertyChange(PROPERTY_MESSAGEHANDLERPOOL, oldValue, value);
			changed = true;
		}

		return changed;
	}

	public ZombieFighter withMessageHandlerPool(MessageHandlerPool value) {
		setMessageHandlerPool(value);
		return this;
	}

	public MessageHandlerPool createMessageHandlerPool() {
		MessageHandlerPool value = new MessageHandlerPool();
		withMessageHandlerPool(value);
		return value;
	}

	/********************************************************************
	 * <pre>
	 *              one                       many
	 * ZombieFighter ----------------------------------- Game
	 *              zombieFighter                   games
	 * </pre>
	 */

	public static final String PROPERTY_GAMES = "games";

	private GameSet games = null;

	public GameSet getGames() {
		if (this.games == null) {
			return GameSet.EMPTY_SET;
		}

		return this.games;
	}

	public ZombieFighter withGames(Game... value) {
		if (value == null) {
			return this;
		}
		for (Game item : value) {
			if (item != null) {
				if (this.games == null) {
					this.games = new GameSet();
				}

				boolean changed = this.games.add(item);

				if (changed) {
					item.withZombieFighter(this);
					getPropertyChangeSupport().firePropertyChange(PROPERTY_GAMES, null, item);
				}
			}
		}
		return this;
	}

	public ZombieFighter withoutGames(Game... value) {
		for (Game item : value) {
			if ((this.games != null) && (item != null)) {
				if (this.games.remove(item)) {
					item.setZombieFighter(null);
					getPropertyChangeSupport().firePropertyChange(PROPERTY_GAMES, item, null);
				}
			}
		}
		return this;
	}

	public Game createGames() {
		Game value = new Game();
		withGames(value);
		return value;
	}

	/********************************************************************
	 * <pre>
	 *              one                       many
	 * ZombieFighter ----------------------------------- Map
	 *              zombieFighter                   maps
	 * </pre>
	 */

	public static final String PROPERTY_MAPS = "maps";

	private MapSet maps = null;

	public MapSet getMaps() {
		if (this.maps == null) {
			return MapSet.EMPTY_SET;
		}

		return this.maps;
	}

	public ZombieFighter withMaps(Map... value) {
		if (value == null) {
			return this;
		}
		for (Map item : value) {
			if (item != null) {
				if (this.maps == null) {
					this.maps = new MapSet();
				}

				boolean changed = this.maps.add(item);

				if (changed) {
					item.withZombieFighter(this);
					getPropertyChangeSupport().firePropertyChange(PROPERTY_MAPS, null, item);
				}
			}
		}
		return this;
	}

	public ZombieFighter withoutMaps(Map... value) {
		for (Map item : value) {
			if ((this.maps != null) && (item != null)) {
				if (this.maps.remove(item)) {
					item.setZombieFighter(null);
					getPropertyChangeSupport().firePropertyChange(PROPERTY_MAPS, item, null);
				}
			}
		}
		return this;
	}

	public Map createMaps() {
		Map value = new Map();
		withMaps(value);
		return value;
	}

	/********************************************************************
	 * <pre>
	 *              one                       many
	 * ZombieFighter ----------------------------------- User
	 *              zombieFighter                   users
	 * </pre>
	 */

	public static final String PROPERTY_USERS = "users";

	private UserSet users = null;

	public UserSet getUsers() {
		if (this.users == null) {
			return UserSet.EMPTY_SET;
		}

		return this.users;
	}

	public ZombieFighter withUsers(User... value) {
		if (value == null) {
			return this;
		}
		for (User item : value) {
			if (item != null) {
				if (this.users == null) {
					this.users = new UserSet();
				}

				boolean changed = this.users.add(item);

				if (changed) {
					item.withZombieFighter(this);
					getPropertyChangeSupport().firePropertyChange(PROPERTY_USERS, null, item);
				}
			}
		}
		return this;
	}

	public ZombieFighter withoutUsers(User... value) {
		for (User item : value) {
			if ((this.users != null) && (item != null)) {
				if (this.users.remove(item)) {
					item.setZombieFighter(null);
					getPropertyChangeSupport().firePropertyChange(PROPERTY_USERS, item, null);
				}
			}
		}
		return this;
	}

	public User createUsers() {
		User value = new User();
		withUsers(value);
		return value;
	}

	/********************************************************************
	 * <pre>
	 *              one                       many
	 * ZombieFighter ----------------------------------- Team
	 *              zombieFighter                   teams
	 * </pre>
	 */

	public static final String PROPERTY_TEAMS = "teams";

	private TeamSet teams = null;

	public TeamSet getTeams() {
		if (this.teams == null) {
			return TeamSet.EMPTY_SET;
		}

		return this.teams;
	}

	public ZombieFighter withTeams(Team... value) {
		if (value == null) {
			return this;
		}
		for (Team item : value) {
			if (item != null) {
				if (this.teams == null) {
					this.teams = new TeamSet();
				}

				boolean changed = this.teams.add(item);

				if (changed) {
					item.withZombieFighter(this);
					getPropertyChangeSupport().firePropertyChange(PROPERTY_TEAMS, null, item);
				}
			}
		}
		return this;
	}

	public ZombieFighter withoutTeams(Team... value) {
		for (Team item : value) {
			if ((this.teams != null) && (item != null)) {
				if (this.teams.remove(item)) {
					item.setZombieFighter(null);
					getPropertyChangeSupport().firePropertyChange(PROPERTY_TEAMS, item, null);
				}
			}
		}
		return this;
	}

	public Team createTeams() {
		Team value = new Team();
		withTeams(value);
		return value;
	}

	/********************************************************************
	 * <pre>
	 *              one                       many
	 * ZombieFighter ----------------------------------- Message
	 *              zombieFighter                   messages
	 * </pre>
	 */

	public static final String PROPERTY_MESSAGES = "messages";

	private MessageSet messages = null;

	public MessageSet getMessages() {
		if (this.messages == null) {
			return MessageSet.EMPTY_SET;
		}

		return this.messages;
	}

	public ZombieFighter withMessages(Message... value) {
		if (value == null) {
			return this;
		}
		for (Message item : value) {
			if (item != null) {
				if (this.messages == null) {
					this.messages = new MessageSet();
				}

				boolean changed = this.messages.add(item);

				if (changed) {
					item.withZombieFighter(this);
					// Add Message to ChatObject
					/*
					 * Possible at MessageListListener or at
					 * Zombiefighter.withMessages
					 */
					// chat.addMessages(item);
					getPropertyChangeSupport().firePropertyChange(PROPERTY_MESSAGES, null, item);
				}
			}
		}
		return this;
	}

	public ZombieFighter withoutMessages(Message... value) {
		for (Message item : value) {
			if ((this.messages != null) && (item != null)) {
				if (this.messages.remove(item)) {
					item.setZombieFighter(null);
					getPropertyChangeSupport().firePropertyChange(PROPERTY_MESSAGES, item, null);
				}
			}
		}
		return this;
	}

	public Message createMessages() {
		Message value = new Message();
		withMessages(value);
		return value;
	}

	/********************************************************************
	 * <pre>
	 *              one                       one
	 * ZombieFighter ----------------------------------- User
	 *              zombieFighter                   currentUser
	 * </pre>
	 */

	public static final String PROPERTY_CURRENTUSER = "currentUser";

	private User currentUser = null;

	public User getCurrentUser() {
		return this.currentUser;
	}

	public boolean setCurrentUser(User value) {
		boolean changed = false;

		if (this.currentUser != value) {
			User oldValue = this.currentUser;

			if (this.currentUser != null) {
				this.currentUser = null;
				oldValue.setZombieFighter(null);
			}

			this.currentUser = value;

			if (value != null) {
				value.withZombieFighter(this);
			}

			
			getPropertyChangeSupport().firePropertyChange(PROPERTY_CURRENTUSER, oldValue, value);
			changed = true;
			
			if(value != null){
				Mediator.getInstance().setState(ZFState.LOGGED_IN);
			}
		}

		return changed;
	}

	public ZombieFighter withCurrentUser(User value) {
		setCurrentUser(value);
		return this;
	}

	public User createCurrentUser() {
		User value = new User();
		withCurrentUser(value);
		return value;
	}	

	// ==========================================================================

	public static final String PROPERTY_CURRENTGAME = "currentGame";

	private ZombieFightGame currentGame;

	public ZombieFightGame getCurrentGame() {
		return this.currentGame;
	}

	public void setCurrentGame(ZombieFightGame value) {
		if (this.currentGame != value) {
			ZombieFightGame oldValue = this.currentGame;
			this.currentGame = value;
			getPropertyChangeSupport().firePropertyChange(PROPERTY_CURRENTGAME, oldValue, value);
		}
	}

	public ZombieFighter withCurrentGame(ZombieFightGame value) {
		setCurrentGame(value);
		return this;
	}

	public ZombieFightGame createCurrentGame() {
		ZombieFightGame newValue = new ZombieFightGame();
		this.setCurrentGame(newValue);
		return newValue;
	}
	
	private File currentGameMapFile;
	
	public File getCurrentGameMapFile(){
		return this.currentGameMapFile;
	}
	
	public void setCurrentGameMapFile(File value) {
		this.currentGameMapFile = value;
	}
	
	public ZombieFighter withCurrentGameMapFile(File value) {
		this.setCurrentGameMapFile(value);
		return this;
	}

   
   //==========================================================================
   
   public static final String PROPERTY_GAMESCENESCALING = "gameSceneScaling";
   
   private double gameSceneScaling = 1;

   public double getGameSceneScaling()
   {
      return this.gameSceneScaling;
   }
   
   public void setGameSceneScaling(double value)
   {
      if (this.gameSceneScaling != value)
      {
         double oldValue = this.gameSceneScaling;
         this.gameSceneScaling = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_GAMESCENESCALING, oldValue, value);
      }
   }
   
   public ZombieFighter withGameSceneScaling(double value)
   {
      setGameSceneScaling(value);
      return this;
   } 


   @Override
   public String toString()
   {
      StringBuilder result = new StringBuilder();
      
      result.append(" ").append(this.getGameSceneScaling());
      return result.substring(1);
   }

}
