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

import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;
import java.lang.Thread;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import org.sdmlib.StrUtil;

import de.uks.se1.ss15.dtritus.zombiefighter.KI.global.Mediator;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.global.ZombieFighter;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.ThreadObject;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.runnables.*;

import java.util.concurrent.LinkedBlockingDeque;

public class ServerMessageHandler extends ThreadObject {

	/**
	 * Don't call it! Only for SDMLib and Testing Purpose You have to call
	 * connect() afterwards
	 * 
	 */
	public ServerMessageHandler() {
		Socket socket;

		socket = new Socket();
		this.setServerConnection(socket);

		// Create needed Classes
		this.createMessageHandlerPool();
		this.createThreadRunner();

		// Give the ThreadObject the current Object and start the Thread
		this.setThread(this);
	}

	/**
	 * Creates the ServerMessageHandler and connects to the Server. If
	 * connection is established, a new Thread is started to fetch Messages from
	 * Server that are forwarded to the MessageHandlerPool
	 * 
	 * @param serverURL
	 * @param serverPort
	 */
	public ServerMessageHandler(String serverURL, int serverPort) throws ConnectException {
		// Create socket and try to connect to server
		try {
			Socket socket;

			socket = new Socket(serverURL, serverPort);
			if (!socket.isConnected()) {
				// Maybe Never reached.. ?
				socket.close();
				throw new UnknownHostException("Can't establish Connetion to server");
			}

			this.setServerConnection(socket);

			Mediator.getInstance();
			// Show if connection was successfully
			// TODO Use in a more appropriate manner.. ;)
			Mediator.printDebugln("Connected to the server");

			// Create needed Classes
			this.createMessageHandlerPool();
			this.createThreadRunner();

			// Initialize Readers
			initializeReaders(this.getServerConnection().getInputStream());

			// Give the ThreadObject the current Object and start the Thread
			this.setThread(this);
			this.start();

			// And Start the Sendthread
			this.getThreadRunner().createAndStartThread(new MessageSender(this));

			// Tell the TestSignal, SMH is started
			Mediator.printTestSignal("ServerMessageHandler started");
			Mediator.printDebugln("ServerMessageHandler started");

		} catch (IOException e) {
			Mediator.printDebugln("Problem with Serverconnection (Unknown Host)");
			try {
				if (this.ServerConnection != null) {
					this.ServerConnection.close();
				}
			} catch (IOException e1) {
				// ServerConnection already closed
			}
			throw new ConnectException();
		}
	}

	private void initializeReaders(InputStream inputStream) {
		inputReader = new InputStreamReader(inputStream);
		bufferedInput = new BufferedReader(inputReader);
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

	public void removeYou() {
		setMessageHandlerPool(null);
		setThreadRunner(null);
		setZombieFighter(null);
		getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
	}

	// ==========================================================================

	public static final String PROPERTY_SERVERCONNECTION = "ServerConnection";

	private Socket ServerConnection;

	public Socket getServerConnection() {
		return this.ServerConnection;
	}

	public void setServerConnection(Socket value) {
		if (this.ServerConnection != value) {
			Socket oldValue = this.ServerConnection;
			this.ServerConnection = value;
			getPropertyChangeSupport().firePropertyChange(PROPERTY_SERVERCONNECTION, oldValue, value);
		}
	}

	public ServerMessageHandler withServerConnection(Socket value) {
		setServerConnection(value);
		return this;
	}

	/********************************************************************
	 * <pre>
	 *              one                       one
	 * ServerMessageHandler ----------------------------------- MessageHandlerPool
	 *              ServerMessageHandler                   MessageHandlerPool
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
				oldValue.setServerMessageHandler(null);
			}

			this.MessageHandlerPool = value;

			if (value != null) {
				value.withServerMessageHandler(this);
			}

			getPropertyChangeSupport().firePropertyChange(PROPERTY_MESSAGEHANDLERPOOL, oldValue, value);
			changed = true;
		}

		return changed;
	}

	public ServerMessageHandler withMessageHandlerPool(MessageHandlerPool value) {
		setMessageHandlerPool(value);
		return this;
	}

	public MessageHandlerPool createMessageHandlerPool() {
		MessageHandlerPool value = new MessageHandlerPool();
		withMessageHandlerPool(value);
		return value;
	}

	public ByteArrayOutputStream inputBuffer = new ByteArrayOutputStream();

	@Override
	public void run() {
		while (!Thread.interrupted()) {
			try {// TODO try-catch block
				if (this.getServerConnection() != null && this.getServerConnection().isClosed()) {
					this.interrupt();
					break;
				}
				{
					// Check if InputReader has something to read...
					InputStream input = this.getServerConnection().getInputStream();
					int available = input.available();
					if (input != null && available > 0) {
						inputBuffer.write(input.read());
						boolean handle = this.getMessageHandlerPool().handle(inputBuffer.toByteArray());
						if (handle) {
							this.inputBuffer = new ByteArrayOutputStream();
							initializeReaders(this.getServerConnection().getInputStream());
						}
						if (handle && this.lastMessageShouldBeHandled) {
							this.setLastMessageHandled(true);
						}
						// Reset LMSBH to true, so that the next Message will be
						// Handled as usual
						this.setLastMessageShouldBeHandled(true);
					} else {
						// Got nothing in Buffer..
						Thread.sleep(1);
						continue;
					}
				}
			} catch (IOException e) {
				this.interrupt();
			} catch (InterruptedException e) {
				this.interrupt();
			}
		}

		// Tidy up after loop
		try {
			Mediator.getInstance();
			Mediator.printDebugln("Closing conection...");
			// Interrupt the Server and the keepAliveThread
			this.getThread().interrupt();
			if (this.getThreadRunner() != null) {
				this.getThreadRunner().shutdown();
			}
		} finally {
			if (this.ServerConnection != null && !this.ServerConnection.isClosed()) {
				try {
					this.ServerConnection.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					Mediator.printDebugln("Can't close socket-connection");
					e.printStackTrace();
				}
			}
			Mediator.printTestSignal("Connection closed");
			Mediator.printDebugln("Connection closed");
		}
	}

	// ==========================================================================

	/**
	 * 
	 * @param message
	 *            A single Line that will be transmitted to the Server
	 * @return returns whether the Transmission to the Server was successfully
	 *         or not
	 */
	public boolean sendString(String message) {
		if (this.isConnected()) {
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			// Append Line Seperators and put into SendBuffer
			try {
				output.write((message + "\r\n").getBytes());
				boolean offer = this.getSendBuffer().offer(output.toByteArray());
				return offer;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}

	public boolean sendByte(byte[] message) {
		if (this.isConnected()) {
			boolean offer = this.getSendBuffer().offer(message);

			return offer;
		}
		return false;
	}

	// ==========================================================================

	/**
	 * Creates a new Thread, that sends NOOP every Half of the Timeout-value
	 * Example to get to know, if Runnable already started:
	 * this.getThreadRunner().getThreads().getRunnableContent().has(runnable->
	 * runnable.getClass() == new KeepAliveRunnable(this).getClass())
	 * 
	 * @param timeoutValue
	 * @return
	 */
	public boolean keepAlive(int timeoutValue) {
		this.setTimeoutValue(timeoutValue);

		if (this.getThreadRunner().getThreads().getRunnableContent()
				.has(runnable -> runnable.getClass() == new KeepAliveRunnable(this).getClass()) != null) {
			KeepAliveRunnable keepAliveRunnable = new KeepAliveRunnable(this);

			this.getThreadRunner().createAndStartThread(keepAliveRunnable);
		}

		// Mediator.getInstance().printDebugln(this.getThreadRunner().getThreads().getRunnableContent().has(runnable->
		// runnable.getClass() == new
		// KeepAliveRunnable(this).getClass()).toString());

		return true;
	}

	// ==========================================================================

	public static final String PROPERTY_TIMEOUTVALUE = "timeoutValue";

	private int timeoutValue = 600000;

	public int getTimeoutValue() {
		return this.timeoutValue;
	}

	public void setTimeoutValue(int value) {
		if (this.timeoutValue != value) {
			int oldValue = this.timeoutValue;
			this.timeoutValue = value;
			getPropertyChangeSupport().firePropertyChange(PROPERTY_TIMEOUTVALUE, oldValue, value);
		}
	}

	public ServerMessageHandler withTimeoutValue(int value) {
		setTimeoutValue(value);
		return this;
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();

		result.append(" ").append(this.getTimeoutValue());
		result.append(" ").append(this.getLastMessage());
		return result.substring(1);
	}

	private boolean connectionHeaderReceived = false;

	public void setConnectionHeaderReceived(boolean value) {
		this.connectionHeaderReceived = value;
	}

	public boolean hasConnectionHeaderReceived() {
		return this.connectionHeaderReceived;
	}

	public boolean isConnected() {
		return (this.getServerConnection() != null && !this.ServerConnection.isClosed()
				&& this.ServerConnection.isConnected());
	}

	/********************************************************************
	 * <pre>
	 *              one                       one
	 * ServerMessageHandler ----------------------------------- ThreadRunner
	 *              ServerMessageHandler                   ThreadRunner
	 * </pre>
	 */

	public static final String PROPERTY_THREADRUNNER = "ThreadRunner";

	private ThreadRunner ThreadRunner = new ThreadRunner();

	public ThreadRunner getThreadRunner() {
		return this.ThreadRunner;
	}

	public boolean setThreadRunner(ThreadRunner value) {
		boolean changed = false;

		if (this.ThreadRunner != value) {
			ThreadRunner oldValue = this.ThreadRunner;

			if (this.ThreadRunner != null) {
				this.ThreadRunner = null;
				oldValue.setServerMessageHandler(null);
			}

			this.ThreadRunner = value;

			if (value != null) {
				value.withServerMessageHandler(this);
			}

			getPropertyChangeSupport().firePropertyChange(PROPERTY_THREADRUNNER, oldValue, value);
			changed = true;
		}

		return changed;
	}

	public ServerMessageHandler withThreadRunner(ThreadRunner value) {
		setThreadRunner(value);
		return this;
	}

	public ThreadRunner createThreadRunner() {
		ThreadRunner value = new ThreadRunner();
		withThreadRunner(value);
		return value;
	}

	/********************************************************************
	 * <pre>
	 *              one                       one
	 * ServerMessageHandler ----------------------------------- ZombieFighter
	 *              ServerMessageHandler                   ZombieFighter
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
				oldValue.setServerMessageHandler(null);
			}

			this.ZombieFighter = value;

			if (value != null) {
				value.withServerMessageHandler(this);
			}

			getPropertyChangeSupport().firePropertyChange(PROPERTY_ZOMBIEFIGHTER, oldValue, value);
			changed = true;
		}

		return changed;
	}

	public ServerMessageHandler withZombieFighter(ZombieFighter value) {
		setZombieFighter(value);
		return this;
	}

	public ZombieFighter createZombieFighter() {
		// ZombieFighter value = new ZombieFighter();
		// withZombieFighter(value);
		return null;
	}

	// ==========================================================================

	public static final String PROPERTY_LASTMESSAGE = "lastMessage";

	private String lastMessage = "";

	public String getLastMessage() {
		return this.lastMessage;
	}

	public void setLastMessage(String value) {
		if (!StrUtil.stringEquals(this.lastMessage, value)) {
			String oldValue = this.lastMessage;
			this.lastMessage = value;
			getPropertyChangeSupport().firePropertyChange(PROPERTY_LASTMESSAGE, oldValue, value);
		}
	}

	public ServerMessageHandler withLastMessage(String value) {
		setLastMessage(value);
		return this;
	}

	// ==========================================================================

	public static final String PROPERTY_LASTMESSAGEHANDLED = "lastMessageHandled";

	private boolean lastMessageHandled = true;

	public boolean isLastMessageHandled() {
		return this.lastMessageHandled;
	}

	public void setLastMessageHandled(boolean value) {
		if (this.lastMessageHandled != value) {
			boolean oldValue = this.lastMessageHandled;
			this.lastMessageHandled = value;
			getPropertyChangeSupport().firePropertyChange(PROPERTY_LASTMESSAGEHANDLED, oldValue, value);
		}

		/*
		 * If last message was handled, reset the saved lastMessageSent
		 */
		if (value = true) {
			this.setLastMessage("");
		}
	}

	public ServerMessageHandler withLastMessageHandled(boolean value) {
		setLastMessageHandled(value);
		return this;
	}

	@Override
	public void interrupt() {
		super.interrupt();
		this.getThreadRunner().shutdown();
		try {
			this.getServerConnection().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// ==========================================================================

	/**
	 * Don't use!!! Not Thread-Safe Only for ServerMessageHandler
	 * 
	 * @param message
	 * @return
	 */
	public boolean send(byte[] message) {
		if (this.isConnected()) {
			this.setLastMessageHandled(false);
			try {
				OutputStream outputStream = this.getServerConnection().getOutputStream();
				outputStream.write(message);
				outputStream.flush();

				// Make ByteArray to String (And remove \r\n)
				ByteArrayOutputStream output = new ByteArrayOutputStream();
				output.write(message);
				output.flush();
				if (output.size() > 2 && output.toByteArray()[output.size() - 1] == 10
						&& output.toByteArray()[output.size() - 2] == 13) {
					// Set last Message, if a String with line Seperators was
					// sent
					this.setLastMessage(output.toString().substring(0, output.size() - 2));
				}
				return true;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}

	// ==========================================================================

	/*
	 * public static final String PROPERTY_SENDBUFFER = "sendBuffer";
	 * 
	 * private LinkedBlockingDeque<String> sendBuffer = new
	 * LinkedBlockingDeque<String>();
	 * 
	 * public LinkedBlockingDeque<String> getSendBuffer() { return
	 * this.sendBuffer; }
	 * 
	 * public void setSendBuffer(LinkedBlockingDeque<String> value) { if
	 * (this.sendBuffer != value) { LinkedBlockingDeque<String> oldValue =
	 * this.sendBuffer; this.sendBuffer = value;
	 * getPropertyChangeSupport().firePropertyChange(PROPERTY_SENDBUFFER,
	 * oldValue, value); } }
	 * 
	 * public ServerMessageHandler withSendBuffer(LinkedBlockingDeque<String>
	 * value) { setSendBuffer(value); return this; }
	 */

	// ==========================================================================

	public static final String PROPERTY_SENDBUFFER = "sendBuffer";

	private LinkedBlockingDeque sendBuffer = new LinkedBlockingDeque<byte[]>();

	public LinkedBlockingDeque getSendBuffer() {
		return this.sendBuffer;
	}

	public void setSendBuffer(LinkedBlockingDeque value) {
		if (this.sendBuffer != value) {
			LinkedBlockingDeque oldValue = this.sendBuffer;
			this.sendBuffer = value;
			getPropertyChangeSupport().firePropertyChange(PROPERTY_SENDBUFFER, oldValue, value);
		}
	}

	public ServerMessageHandler withSendBuffer(LinkedBlockingDeque value) {
		setSendBuffer(value);
		return this;
	}

	// ==========================================================================

	public void connect(String url, int port) {
		try {
			Socket socket;

			socket = new Socket(url, port);
			if (!socket.isConnected()) {
				// Maybe Never reached.. ?
				socket.close();
				throw new UnknownHostException("Can't establish Connetion to server");
			}

			this.setServerConnection(socket);

			Mediator.getInstance();
			// Show if connection was successfully
			// TODO Use in a more appropriate manner.. ;)
			Mediator.printDebugln("Connected to the server");

			// Create needed Classes
			this.createMessageHandlerPool();
			this.createThreadRunner();

			// Give the ThreadObject the current Object and start the Thread
			this.setThread(this);
			this.start();

			// And Start the Sendthread
			this.getThreadRunner().createAndStartThread(new MessageSender(this));
			/*
			 * ThreadExtension MessageSenderThread = this.getThreadRunner()
			 * .getThreads().withRunnableContent(new MessageSender(this))
			 * .first();
			 */
			Mediator.printTestSignal("ServerMessageHandler started");
			// MessageSenderThread.run();

		} catch (IOException e) {
			Mediator.printDebugln("Problem with Serverconnection (Unknown Host)");
			try {
				if (this.ServerConnection != null) {
					this.ServerConnection.close();
				}
			} catch (IOException e1) {
				// ServerConnection already closed
			}
		}
	}

	// ==========================================================================

	public static final String PROPERTY_LASTMESSAGESHOULDBEHANDLED = "lastMessageShouldBeHandled";

	/**
	 * Here, you can tell the ServerMessageHandler to not handle the Last
	 * Message the usual way. if value is set to false, the ServerMessageHandler
	 * will not remove the last sent message, because it wasn't handled.
	 */
	private boolean lastMessageShouldBeHandled = true;

	public boolean isLastMessageShouldBeHandled() {
		return this.lastMessageShouldBeHandled;
	}

	/**
	 * Here, you can tell the ServerMessageHandler to not handle the Last
	 * Message the usual way. if value is set to false, the ServerMessageHandler
	 * will not remove the last sent message, because it wasn't handled.
	 * 
	 * @param value
	 *            default: value = true
	 * @return
	 */
	public void setLastMessageShouldBeHandled(boolean value) {
		if (this.lastMessageShouldBeHandled != value) {
			boolean oldValue = this.lastMessageShouldBeHandled;
			this.lastMessageShouldBeHandled = value;
			getPropertyChangeSupport().firePropertyChange(PROPERTY_LASTMESSAGESHOULDBEHANDLED, oldValue, value);
		}
	}

	public ServerMessageHandler withLastMessageShouldBeHandled(boolean value) {
		setLastMessageShouldBeHandled(value);
		return this;
	}

	// ==========================================================================

	public static final String PROPERTY_JSONPROTOCOL = "jsonProtocol";

	private boolean jsonProtocol;

	public boolean isJsonProtocol() {
		return this.jsonProtocol;
	}

	public void setJsonProtocol(boolean value) {
		if (this.jsonProtocol != value) {
			boolean oldValue = this.jsonProtocol;
			this.jsonProtocol = value;
			getPropertyChangeSupport().firePropertyChange(PROPERTY_JSONPROTOCOL, oldValue, value);
		}
	}

	public ServerMessageHandler withJsonProtocol(boolean value) {
		setJsonProtocol(value);
		return this;
	}

	public static final String PROPERTY_LASTDOWNLOADEDZIP = "lastDownloadedZip";

	private String lastDownloadedZip = "";

	public String getLastDownloadedZip() {
		return this.lastDownloadedZip;
	}

	public void setLastDownloadedZip(String value) {
		if (!this.lastDownloadedZip.equals(value)) {
			String oldValue = this.lastDownloadedZip;
			this.lastDownloadedZip = value;
			getPropertyChangeSupport().firePropertyChange(PROPERTY_LASTDOWNLOADEDZIP, oldValue, value);
		}
	}

	public ServerMessageHandler withLastDownloadedZip(String path) {
		this.setLastDownloadedZip(path);
		return this;
	}

	private String currentDownloadingMapName;

	public String getCurrentDownloadingMapName() {
		return this.currentDownloadingMapName;
	}

	public void setCurrentDownloadingMapName(String path) {
		this.currentDownloadingMapName = path;
	}

	public ServerMessageHandler withCurrentDownloadingMapName(String path) {
		this.setCurrentDownloadingMapName(path);
		return this;
	}

	public boolean downloadMap(String mapName) {
		String workingDirectory = Mediator.getWorkingDirectory();
		// Add path separator at the end of the path
		if (!workingDirectory.endsWith(System.getProperty("file.separator"))) {
			workingDirectory = workingDirectory + System.getProperty("file.separator");
		}
		// Check fo file extension
		if (mapName.endsWith(".zip")) {
			mapName = mapName.replace(".zip", "");
		}
		// Add name prefix, if it is missing
		if (!mapName.startsWith("map_")) {
			mapName = "map_" + mapName;
		}
		this.setCurrentDownloadingMapName(mapName + ".zip");
		return this.sendString("DOWNLOAD MAP " + mapName);
	}

	public static final String PROPERTY_RECEIVEDBYTES = "receivedBytes";

	private int receivedBytes;

	public int getReceivedBytes() {
		return this.receivedBytes;
	}

	public void setReceivedBytes(int value) {
		if (this.receivedBytes != value) {
			int oldValue = this.receivedBytes;
			this.receivedBytes = value;
			getPropertyChangeSupport().firePropertyChange(PROPERTY_RECEIVEDBYTES, oldValue, value);
		}
	}

	public ServerMessageHandler withReceivedBytes(int value) {
		this.setReceivedBytes(value);
		return this;
	}

	private int expectedBytes;

	public int getExpectedBytes() {
		return this.expectedBytes;
	}

	public void setExpectedBytes(int value) {
		this.expectedBytes = value;
	}

	public ServerMessageHandler withExpectedBytes(int value) {
		this.setExpectedBytes(value);
		return this;
	}

	// ==========================================================================

	public static final String PROPERTY_LASTUPLOADEDZIP = "lastUploadedZip";

	private String lastUploadedZip = "";

	public String getLastUploadedZip() {
		return this.lastUploadedZip;
	}

	public void setLastUploadedZip(String value) {
		if (!this.lastUploadedZip.equals(value)) {
			String oldValue = this.lastUploadedZip;
			this.lastUploadedZip = value;
			getPropertyChangeSupport().firePropertyChange(PROPERTY_LASTUPLOADEDZIP, oldValue, value);
		}
	}

	public ServerMessageHandler withLastUploadedZip(String path) {
		this.setLastUploadedZip(path);
		return this;
	}

	private String currentUploadingMapName;

	public String getCurrentUploadingMapName() {
		return this.currentUploadingMapName;
	}

	public void setCurrentUploadingMapName(String path) {
		this.currentUploadingMapName = path;
	}

	public ServerMessageHandler withCurrentUploadingMapName(String path) {
		this.setCurrentUploadingMapName(path);
		return this;
	}

	public static final String PROPERTY_SENDINGBYTES = "sendingBytes";

	private int sendingBytes;

	public int getSendingBytes() {
		return this.sendingBytes;
	}

	public void setSendingBytes(int value) {
		if (this.sendingBytes != value) {
			int oldValue = this.sendingBytes;
			this.sendingBytes = value;
			getPropertyChangeSupport().firePropertyChange(PROPERTY_SENDINGBYTES, oldValue, value);
		}
	}

	public ServerMessageHandler withSendingBytes(int value) {
		this.setSendingBytes(value);
		return this;
	}

	private int expectingBytes;

	private InputStreamReader inputReader;

	private BufferedReader bufferedInput;

	public int getExpectingBytes() {
		return this.expectingBytes;
	}

	public void setExpectingBytes(int value) {
		this.expectingBytes = value;
	}

	public ServerMessageHandler withExpectingBytes(int value) {
		this.setExpectingBytes(value);
		return this;
	}

	/**
	 * Check if Server is reachable
	 * 
	 * @param host
	 * @param port
	 * @return
	 */
	public static boolean checkServer(String host, int port) {
		try {
			Socket socket = new Socket();
			SocketAddress address = new InetSocketAddress(host, port);
			socket.connect(address, 5000);
			socket.close();
		} catch (IOException e) {
			return false;
		}
		return true;
	}
}
