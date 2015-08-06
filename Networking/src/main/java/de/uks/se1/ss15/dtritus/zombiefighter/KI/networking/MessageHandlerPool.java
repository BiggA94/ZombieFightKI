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

package de.uks.se1.ss15.dtritus.zombiefighter.KI.networking;

import org.sdmlib.serialization.PropertyChangeInterface;

import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;
import java.io.ByteArrayOutputStream;

import de.uks.se1.ss15.dtritus.zombiefighter.KI.global.Mediator;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.global.ZombieFighter;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.handler.AbstractHandler;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.handler.ByteHandler;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.handler.ConnectionHandler;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.handler.CreateGameHandler;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.handler.DownloadMapHandler;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.handler.ExampleHandler;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.handler.GameListMessageHandler;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.handler.JoinGameHandler;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.handler.JsonCellHandler;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.handler.JsonDefaultHandler;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.handler.JsonDefenseDescriptionHandler;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.handler.JsonDefenseHandler;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.handler.JsonDefenseTypeToDefenseMappingHandler;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.handler.JsonFieldHandler;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.handler.JsonGuideHandler;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.handler.JsonMapHandler;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.handler.JsonServerMessageHandler;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.handler.JsonTeamHandler;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.handler.JsonUserAssetsHandler;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.handler.JsonUserHandler;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.handler.JsonWaypointHandler;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.handler.JsonZombieDescriptionHandler;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.handler.JsonZombieFightGameHandler;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.handler.JsonZombieHandler;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.handler.JsonZombieTypeToZombieMappingHandler;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.handler.KIErrorHandler;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.handler.LoginHandler;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.handler.MapHandler;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.handler.MessageSentHandler;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.handler.NOOPHandler;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.handler.UserListHandler;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.handler.UserMessageHandler;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.handler.util.AbstractHandlerSet;
import de.uniks.networkparser.json.JsonObject;

import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.concurrent.LinkedBlockingQueue;

import javax.management.RuntimeErrorException;

/**
 * Contains all MessageHandlers.
 * 
 * <p>
 * A Message-Handler is created in the NetworkModelCreator as follows: </br>
 * 
 * <pre>
 * Clazz exampleHandler = model.createClazz(&quot;de.uks.se1.ss15.dtritus.zombiefighter.networking.handler.ExampleHandler&quot;);
 * </pre>
 * 
 * <pre>
 * exampleHandler.withSuperClazz(abstractHandler);
 * </pre>
 * 
 * </p>
 * 
 * <p>
 * And then the Handler must be created in the Constructor of the
 * MessagHandlerPool as follows: </br>
 * 
 * <pre>
 * this.withHandler(this.createHandlerExampleHandler());
 * </pre>
 * 
 * </p>
 * 
 * @author Alexander
 *
 */
public class MessageHandlerPool implements PropertyChangeInterface {

	private boolean messageTrimmed = false;
	private static final int NEWLINE_INT_REPRESENTATION = 10;
	private static final int CARRIAGE_RETURN = 13;

	// ==========================================================================

	/**
	 * TODO Add every Handler that should handle Messages to this.Handler
	 */
	public MessageHandlerPool() {
		this.withInputBuffer(new LinkedBlockingQueue<String>());

		this.addHandler("String-UserMessage", this.createHandlerUserMessageHandler());
		this.addHandler("String-Login", this.createHandlerLoginHandler());
		this.addHandler("String-GameList", this.createHandlerGameListMessageHandler());
		this.addHandler("String-Connection", this.createHandlerConnectionHandler());
		this.addHandler("String-Map", this.createHandlerMapHandler());
		this.addHandler("String-CreateGame", this.createHandlerCreateGameHandler());
		this.addHandler("String-UserList", this.createHandlerUserListHandler());
		this.addHandler("String-MessageSent", this.createHandlerMessageSentHandler());
		this.addHandler("JoinGame", this.createHandlerJoinGameHandler());
		this.addHandler("Cell", this.createHandlerJsonCellHandler());
		this.addHandler("DefenseDescription", this.createHandlerJsonDefenseDescriptionHandler());
		this.addHandler("DefenseTypeToDefenseMapping", this.createHandlerJsonDefenseTypeToDefenseMappingHandler());
		this.addHandler("Field", this.createHandlerJsonFieldHandler());
		this.addHandler("Guide", this.createHandlerJsonGuideHandler());
		this.addHandler("Map", this.createHandlerJsonMapHandler());
		this.addHandler("Team", this.createHandlerJsonTeamHandler());
		this.addHandler("UserAssets", this.createHandlerJsonUserAssetsHandler());
		this.addHandler("User", this.createHandlerJsonUserHandler());
		this.addHandler("Waypoint", this.createHandlerJsonWaypointHandler());
		this.addHandler("ZombieDescription", this.createHandlerJsonZombieDescriptionHandler());
		this.addHandler("ZombieFightGame", this.createHandlerJsonZombieFightGameHandler());
		this.addHandler("ZombieTypeToZombieMapping", this.createHandlerJsonZombieTypeToZombieMappingHandler());
		this.addHandler("DownloadMap", this.createHandlerDownloadMapHandler());
		this.addHandler("Zombie", this.createHandlerJsonZombieHandler());
		this.addHandler("Defense", this.createHandlerJsonDefenseHandler());
		this.addHandler("ServerMessage", this.createHandlerJsonServerMessageHandler());

		this.addHandler("Default", this.createHandlerJsonDefaultHandler());
		this.addHandler("Error", this.createHandlerKIErrorHandler());
		this.addHandler("Noop", this.createHandlerNOOPHandler());
	}

	public boolean handle(byte[] byteMessage) {
		// String mode
		if (!this.isByteStreamExpected()) {
			String message = null;
			if (byteMessage.length > 2 && byteMessage[byteMessage.length - 1] == NEWLINE_INT_REPRESENTATION
					&& byteMessage[byteMessage.length - 2] == CARRIAGE_RETURN) {
				// Remove controll chars '\r\n'
				message = new String(byteMessage);
				message = message.substring(0, message.length() - 2);

				Mediator.printDebugln("Message from Server: > " + message + " <");

				// Add the Message Line to the Buffer
				this.getInputBuffer().add(message);
				this.getServerMessageHandler().inputBuffer = new ByteArrayOutputStream();

				// handle messages contained in the input buffer
				return this.handle();
			}
			// Byte stream mode
		} else {
			if (firstrun) {
				startTime = new Date();
				firstrun = false;
				// Reset propert change for timeout
				this.setDownloadTimeout(false);
			} else {
				Date currentTime = new Date();
				// If Timeout occurred
				if (currentTime.getTime() - startTime.getTime() >= timeout_millis) {
					// Interrupt server connection
					Mediator.getInstance().interrupt();
					// Set property change for timeout
					this.setDownloadTimeout(true);
					// Set firstrun flag back to true
					firstrun = true;

				}
			}
			if (byteMessage.length > 2) {
				// Set received bytes for the download progress bar
				this.getServerMessageHandler().setReceivedBytes(byteMessage.length);
				// Check if message contains 'EOT', so we know all needed bytes
				// are transfered
				String message = new String(byteMessage);
				message = message.substring(0, message.length() - 2);
				if (message.contains("EOT")) {
					if (!this.isDownloadTimeout()) {
						if (messageTrimmed || (byteMessage[byteMessage.length - 1] == NEWLINE_INT_REPRESENTATION
								&& byteMessage[byteMessage.length - 2] == CARRIAGE_RETURN)) {
							byte[] tmp_bytes = new byte[byteMessage.length - 2];
							// Remove controll chars '\r\n'
							if (!this.messageTrimmed) {
								for (int i = 0; i < tmp_bytes.length; ++i) {
									tmp_bytes[i] = byteMessage[i];
								}
								this.messageTrimmed = true;
							}
							// Pass to Handler
							boolean handled = ByteHandler.handle(tmp_bytes);
							if (handled) {
								// Set firstrun flag back to true
								firstrun = true;
								// Remove 'SENDING X BYTES' string from buffer
								this.getInputBuffer().poll();
								// Switch handling to string mode
								this.setByteStreamExpected(false);
								// Reset the smh input buffer
								this.getServerMessageHandler().inputBuffer = new ByteArrayOutputStream();
								this.getInputBuffer().clear();
								// Marks the received message as not fully
								// handled (expecting OK)
								Mediator.getInstance().getZombieFighter().getServerMessageHandler()
										.setLastMessageShouldBeHandled(false);
								// Reset the trimmed status
								this.messageTrimmed = false;
							}
						}
					} else {

					}
				}
			}

		}
		return false;
	}

	public boolean handle() {
		String message = this.getInputBuffer().peek();
		if (message != null && message.startsWith(AbstractHandler.JSON_IDENTIFIER_TIMESTAMP)) {
			// analyze the JSON message
			JsonObject event = new JsonObject().withValue(message);
			String source = event.getString("@src");
			String sourceClass = source.split("@")[0];
			if (sourceClass != null) {
				AbstractHandler abstractHandler = this.getHandlerMap().get(sourceClass.replace(" ", ""));
				if (abstractHandler != null) {
					boolean handled = abstractHandler.handle(message);
					if (handled) {
						this.getInputBuffer().poll();
						if (this.getInputBuffer().isEmpty()) {
							// If a JSON message is longer than 1 line, this
							// will cause a failure!
							return handled;
						}
					}
				}
			}
		}

		// Ask every Handler if he is the right one
		for (Iterator<Entry<String, AbstractHandler>> iterator = this.getHandlerMap().entrySet().iterator(); iterator
				.hasNext();) {
			Entry<String, AbstractHandler> abstractHandler = (Entry<String, AbstractHandler>) iterator.next();

			AbstractHandler handler = abstractHandler.getValue();

			boolean handled = false;

			String[] messageArray = this.inputBuffer.toArray(new String[this.getInputBuffer().size()]);
			if (messageArray.length > 0) {
				handled = handler.handle(messageArray);
			}
			if (handled) {
				this.getInputBuffer().clear();
				Mediator.printDebugln("Handled by " + abstractHandler);

				return handled;
			} else {
				continue;
			}
		}
		return false;
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
		withoutHandler(this.getHandler().toArray(new AbstractHandler[this.getHandler().size()]));
		setZombieFighter(null);
		getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
	}

	// ==========================================================================

	/********************************************************************
	 * <pre>
	 *              one                       one
	 * MessageHandlerPool ----------------------------------- ServerMessageHandler
	 *              MessageHandlerPool                   ServerMessageHandler
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
				oldValue.setMessageHandlerPool(null);
			}

			this.ServerMessageHandler = value;

			if (value != null) {
				value.withMessageHandlerPool(this);
			}

			getPropertyChangeSupport().firePropertyChange(PROPERTY_SERVERMESSAGEHANDLER, oldValue, value);
			changed = true;
		}

		return changed;
	}

	public MessageHandlerPool withServerMessageHandler(ServerMessageHandler value) {
		setServerMessageHandler(value);
		return this;
	}

	public ServerMessageHandler createServerMessageHandler() {
		ServerMessageHandler value = new ServerMessageHandler();
		withServerMessageHandler(value);
		return value;
	}

	private LinkedHashMap<String, AbstractHandler> handlerMap = new LinkedHashMap<>();

	public AbstractHandler addHandler(String key, AbstractHandler handler) {
		if (handlerMap == null)
			handlerMap = new LinkedHashMap<>();
		return handlerMap.put(key, handler);
	}

	public AbstractHandler removeHandler(String key) {
		return handlerMap.remove(key);
	}

	public LinkedHashMap<String, AbstractHandler> getHandlerMap() {
		return handlerMap;
	}

	/********************************************************************
	 * <pre>
	 *              one                       many
	 * MessageHandlerPool ----------------------------------- AbstractHandler
	 *              HandlerPool                   Handler
	 * </pre>
	 */

	public static final String PROPERTY_HANDLER = "Handler";

	private AbstractHandlerSet Handler = null;

	public AbstractHandlerSet getHandler() {
		if (this.Handler == null) {
			return AbstractHandlerSet.EMPTY_SET;
		}

		return this.Handler;
	}

	public MessageHandlerPool withHandler(AbstractHandler... value) {
		if (value == null) {
			return this;
		}
		for (AbstractHandler item : value) {
			if (item != null) {
				if (this.Handler == null) {
					this.Handler = new AbstractHandlerSet();
				}

				boolean changed = this.Handler.add(item);

				if (changed) {
					item.withHandlerPool(this);
					getPropertyChangeSupport().firePropertyChange(PROPERTY_HANDLER, null, item);
				}
			}
		}
		return this;
	}

	public MessageHandlerPool withoutHandler(AbstractHandler... value) {
		for (AbstractHandler item : value) {
			if ((this.Handler != null) && (item != null)) {
				if (this.Handler.remove(item)) {
					item.setHandlerPool(null);
					getPropertyChangeSupport().firePropertyChange(PROPERTY_HANDLER, item, null);
				}
			}
		}
		return this;
	}

	public ExampleHandler createHandlerExampleHandler() {
		ExampleHandler value = new ExampleHandler();
		withHandler(value);
		return value;
	}

	public ConnectionHandler createHandlerConnectionHandler() {
		ConnectionHandler value = new ConnectionHandler();
		withHandler(value);
		return value;
	}

	public NOOPHandler createHandlerNOOPHandler() {
		NOOPHandler value = new NOOPHandler();
		withHandler(value);
		return value;
	}

	/********************************************************************
	 * <pre>
	 *              one                       one
	 * MessageHandlerPool ----------------------------------- ZombieFighter
	 *              MessageHandlerPool                   ZombieFighter
	 * </pre>
	 */

	public static final String PROPERTY_ZOMBIEFIGHTER = "ZombieFighter";

	private ZombieFighter ZombieFighter = null;

	public ZombieFighter getZombieFighter() {
		return this.ZombieFighter;
	}

	public boolean setZombieFighter(ZombieFighter value) {
		boolean changed = false;

		if (this.ZombieFighter != value) {
			ZombieFighter oldValue = this.ZombieFighter;

			if (this.ZombieFighter != null) {
				this.ZombieFighter = null;
				oldValue.setMessageHandlerPool(null);
			}

			this.ZombieFighter = value;

			if (value != null) {
				value.withMessageHandlerPool(this);
			}

			getPropertyChangeSupport().firePropertyChange(PROPERTY_ZOMBIEFIGHTER, oldValue, value);
			changed = true;
		}

		return changed;
	}

	public MessageHandlerPool withZombieFighter(ZombieFighter value) {
		setZombieFighter(value);
		return this;
	}

	public ZombieFighter createZombieFighter() {
		// ZombieFighter value = new ZombieFighter();
		// withZombieFighter(value);
		return null;
	}

	public GameListMessageHandler createHandlerGameListMessageHandler() {
		GameListMessageHandler value = new GameListMessageHandler();
		withHandler(value);
		return value;
	}

	public LoginHandler createHandlerLoginHandler() {
		LoginHandler value = new LoginHandler();
		withHandler(value);
		return value;
	}

	public KIErrorHandler createHandlerKIErrorHandler() {
		KIErrorHandler value = new KIErrorHandler();
		withHandler(value);
		return value;
	}

	public CreateGameHandler createHandlerCreateGameHandler() {
		CreateGameHandler value = new CreateGameHandler();
		withHandler(value);
		return value;
	}

	public DownloadMapHandler createHandlerDownloadMapHandler() {
		DownloadMapHandler value = new DownloadMapHandler();
		withHandler(value);
		return value;
	}

	public MapHandler createHandlerMapHandler() {
		MapHandler value = new MapHandler();
		withHandler(value);
		return value;
	}

	public UserMessageHandler createHandlerUserMessageHandler() {
		UserMessageHandler value = new UserMessageHandler();
		withHandler(value);
		return value;
	}

	public UserListHandler createHandlerUserListHandler() {
		UserListHandler value = new UserListHandler();
		withHandler(value);
		return value;
	}

	public MessageSentHandler createHandlerMessageSentHandler() {
		MessageSentHandler value = new MessageSentHandler();
		withHandler(value);
		return value;
	}

	public AbstractHandler createHandler() {
		// had to manually adjust the method, because you can not create an
		// instance of an abstract class
		return null;
	}

	public JoinGameHandler createHandlerJoinGameHandler() {
		JoinGameHandler value = new JoinGameHandler();
		withHandler(value);
		return value;
	}

	public JsonZombieFightGameHandler createHandlerJsonZombieFightGameHandler() {
		JsonZombieFightGameHandler value = new JsonZombieFightGameHandler();
		withHandler(value);
		return value;
	}

	public JsonGuideHandler createHandlerJsonGuideHandler() {
		JsonGuideHandler value = new JsonGuideHandler();
		withHandler(value);
		return value;
	}

	public JsonZombieTypeToZombieMappingHandler createHandlerJsonZombieTypeToZombieMappingHandler() {
		JsonZombieTypeToZombieMappingHandler value = new JsonZombieTypeToZombieMappingHandler();
		withHandler(value);
		return value;
	}

	public JsonDefenseTypeToDefenseMappingHandler createHandlerJsonDefenseTypeToDefenseMappingHandler() {
		JsonDefenseTypeToDefenseMappingHandler value = new JsonDefenseTypeToDefenseMappingHandler();
		withHandler(value);
		return value;
	}

	public JsonZombieDescriptionHandler createHandlerJsonZombieDescriptionHandler() {
		JsonZombieDescriptionHandler value = new JsonZombieDescriptionHandler();
		withHandler(value);
		return value;
	}

	public JsonDefenseDescriptionHandler createHandlerJsonDefenseDescriptionHandler() {
		JsonDefenseDescriptionHandler value = new JsonDefenseDescriptionHandler();
		withHandler(value);
		return value;
	}

	public JsonFieldHandler createHandlerJsonFieldHandler() {
		JsonFieldHandler value = new JsonFieldHandler();
		withHandler(value);
		return value;
	}

	public JsonMapHandler createHandlerJsonMapHandler() {
		JsonMapHandler value = new JsonMapHandler();
		withHandler(value);
		return value;
	}

	public JsonCellHandler createHandlerJsonCellHandler() {
		JsonCellHandler value = new JsonCellHandler();
		withHandler(value);
		return value;
	}

	public JsonWaypointHandler createHandlerJsonWaypointHandler() {
		JsonWaypointHandler value = new JsonWaypointHandler();
		withHandler(value);
		return value;
	}

	public JsonUserAssetsHandler createHandlerJsonUserAssetsHandler() {
		JsonUserAssetsHandler value = new JsonUserAssetsHandler();
		withHandler(value);
		return value;
	}

	public JsonUserHandler createHandlerJsonUserHandler() {
		JsonUserHandler value = new JsonUserHandler();
		withHandler(value);
		return value;
	}

	public JsonTeamHandler createHandlerJsonTeamHandler() {
		JsonTeamHandler value = new JsonTeamHandler();
		withHandler(value);
		return value;
	}

	// ==========================================================================

	public static final String PROPERTY_INPUTBUFFER = "inputBuffer";

	private LinkedBlockingQueue<String> inputBuffer;

	public LinkedBlockingQueue<String> getInputBuffer() {
		return this.inputBuffer;
	}

	public void setInputBuffer(LinkedBlockingQueue<String> value) {
		if (this.inputBuffer != value) {
			LinkedBlockingQueue<String> oldValue = this.inputBuffer;
			this.inputBuffer = value;
			getPropertyChangeSupport().firePropertyChange(PROPERTY_INPUTBUFFER, oldValue, value);
		}
	}

	public MessageHandlerPool withInputBuffer(LinkedBlockingQueue<String> value) {
		setInputBuffer(value);
		return this;
	}

	public JsonDefaultHandler createHandlerJsonDefaultHandler() {
		JsonDefaultHandler value = new JsonDefaultHandler();
		withHandler(value);
		return value;
	}

	public JsonZombieHandler createHandlerJsonZombieHandler() {
		JsonZombieHandler value = new JsonZombieHandler();
		withHandler(value);
		return value;
	}

	public JsonDefenseHandler createHandlerJsonDefenseHandler() {
		JsonDefenseHandler value = new JsonDefenseHandler();
		withHandler(value);
		return value;
	}

	public JsonServerMessageHandler createHandlerJsonServerMessageHandler() {
		JsonServerMessageHandler value = new JsonServerMessageHandler();
		withHandler(value);
		return value;
	}

	private boolean byteStreamExpected = false;

	public boolean isByteStreamExpected() {
		return this.byteStreamExpected;
	}

	public void setByteStreamExpected(boolean value) {
		if (value) {
			Mediator.printDebugln("MessageHandlerPool: Switched MessageHandlerPool to ByteStream Mode");
		} else {
			Mediator.printDebugln("MessageHandlerPool: Switched MessageHandlerPool to String Mode");
		}
		this.byteStreamExpected = value;
	}

	public MessageHandlerPool withExpectByteStream(boolean value) {
		this.setByteStreamExpected(value);
		return this;
	}

	// ==========================================================================

	public static final String PROPERTY_DOWNLOADTIMEOUT = "downloadTimeout";

	private static int timeout_millis = 60000; // 60 seconds

	public static int getTimeout() {
		return timeout_millis;
	}

	public static void setTimeout(int value) {
		timeout_millis = value;
	}

	private boolean downloadTimeout = false;

	public boolean isDownloadTimeout() {
		return this.downloadTimeout;
	}

	public void setDownloadTimeout(boolean value) {
		if (this.downloadTimeout != value) {
			boolean oldValue = this.downloadTimeout;
			this.downloadTimeout = value;
			getPropertyChangeSupport().firePropertyChange(PROPERTY_DOWNLOADTIMEOUT, oldValue, value);
		}
	}

	private Date startTime;
	private boolean firstrun = true;
}
