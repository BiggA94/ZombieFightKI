package de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.util;

import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.PatternObject;

import de.uks.se1.ss15.dtritus.zombiefighter.KI.global.ZombieFighter;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.global.util.ZombieFighterPO;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.Game;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.Team;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.User;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.util.UserPO;

public class UserPO extends PatternObject<UserPO, User> {

	public UserSet allMatches() {
		this.setDoAllMatches(true);

		UserSet matches = new UserSet();

		while (this.getPattern().getHasMatch()) {
			matches.add((User) this.getCurrentMatch());

			this.getPattern().findMatch();
		}

		return matches;
	}

	public UserPO() {
		newInstance(de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util.CreatorCreator
				.createIdMap("PatternObjectType"));
	}

	public UserPO(User... hostGraphObject) {
		if (hostGraphObject == null || hostGraphObject.length < 1) {
			return;
		}
		newInstance(
				de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util.CreatorCreator
						.createIdMap("PatternObjectType"), hostGraphObject);
	}

	public UserPO hasNick(String value) {
		new AttributeConstraint().withAttrName(User.PROPERTY_NICK)
				.withTgtValue(value).withSrc(this)
				.withModifier(this.getPattern().getModifier())
				.withPattern(this.getPattern());

		super.hasAttr();

		return this;
	}

	public UserPO hasNick(String lower, String upper) {
		new AttributeConstraint().withAttrName(User.PROPERTY_NICK)
				.withTgtValue(lower).withUpperTgtValue(upper).withSrc(this)
				.withModifier(this.getPattern().getModifier())
				.withPattern(this.getPattern());

		super.hasAttr();

		return this;
	}

	public UserPO createNick(String value) {
		this.startCreate().hasNick(value).endCreate();
		return this;
	}

	public String getNick() {
		if (this.getPattern().getHasMatch()) {
			return ((User) getCurrentMatch()).getNick();
		}
		return null;
	}

	public UserPO withNick(String value) {
		if (this.getPattern().getHasMatch()) {
			((User) getCurrentMatch()).setNick(value);
		}
		return this;
	}

	public UserPO hasEmail(String value) {
		new AttributeConstraint().withAttrName(User.PROPERTY_EMAIL)
				.withTgtValue(value).withSrc(this)
				.withModifier(this.getPattern().getModifier())
				.withPattern(this.getPattern());

		super.hasAttr();

		return this;
	}

	public UserPO hasEmail(String lower, String upper) {
		new AttributeConstraint().withAttrName(User.PROPERTY_EMAIL)
				.withTgtValue(lower).withUpperTgtValue(upper).withSrc(this)
				.withModifier(this.getPattern().getModifier())
				.withPattern(this.getPattern());

		super.hasAttr();

		return this;
	}

	public UserPO createEmail(String value) {
		this.startCreate().hasEmail(value).endCreate();
		return this;
	}

	public String getEmail() {
		if (this.getPattern().getHasMatch()) {
			return ((User) getCurrentMatch()).getEmail();
		}
		return null;
	}

	public UserPO withEmail(String value) {
		if (this.getPattern().getHasMatch()) {
			((User) getCurrentMatch()).setEmail(value);
		}
		return this;
	}

	public UserPO hasRole(String value) {
		new AttributeConstraint().withAttrName(User.PROPERTY_ROLE)
				.withTgtValue(value).withSrc(this)
				.withModifier(this.getPattern().getModifier())
				.withPattern(this.getPattern());

		super.hasAttr();

		return this;
	}

	public UserPO hasRole(String lower, String upper) {
		new AttributeConstraint().withAttrName(User.PROPERTY_ROLE)
				.withTgtValue(lower).withUpperTgtValue(upper).withSrc(this)
				.withModifier(this.getPattern().getModifier())
				.withPattern(this.getPattern());

		super.hasAttr();

		return this;
	}

	public UserPO createRole(String value) {
		this.startCreate().hasRole(value).endCreate();
		return this;
	}

	public String getRole() {
		if (this.getPattern().getHasMatch()) {
			return ((User) getCurrentMatch()).getRole();
		}
		return null;
	}

	public UserPO withRole(String value) {
		if (this.getPattern().getHasMatch()) {
			((User) getCurrentMatch()).setRole(value);
		}
		return this;
	}

	public UserPO hasState(String value) {
		new AttributeConstraint().withAttrName(User.PROPERTY_STATE)
				.withTgtValue(value).withSrc(this)
				.withModifier(this.getPattern().getModifier())
				.withPattern(this.getPattern());

		super.hasAttr();

		return this;
	}

	public UserPO hasState(String lower, String upper) {
		new AttributeConstraint().withAttrName(User.PROPERTY_STATE)
				.withTgtValue(lower).withUpperTgtValue(upper).withSrc(this)
				.withModifier(this.getPattern().getModifier())
				.withPattern(this.getPattern());

		super.hasAttr();

		return this;
	}

	public UserPO createState(String value) {
		this.startCreate().hasState(value).endCreate();
		return this;
	}

	public String getState() {
		if (this.getPattern().getHasMatch()) {
			return ((User) getCurrentMatch()).getState();
		}
		return null;
	}

	public UserPO withState(String value) {
		if (this.getPattern().getHasMatch()) {
			((User) getCurrentMatch()).setState(value);
		}
		return this;
	}

	public GamePO hasGame() {
		GamePO result = new GamePO(
				new de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.Game[] {});

		result.setModifier(this.getPattern().getModifier());
		super.hasLink(User.PROPERTY_GAME, result);

		return result;
	}

	public GamePO createGame() {
		return this.startCreate().hasGame().endCreate();
	}

	public UserPO hasGame(GamePO tgt) {
		return hasLinkConstraint(tgt, User.PROPERTY_GAME);
	}

	public UserPO createGame(GamePO tgt) {
		return this.startCreate().hasGame(tgt).endCreate();
	}

	public Game getGame() {
		if (this.getPattern().getHasMatch()) {
			return ((User) this.getCurrentMatch()).getGame();
		}
		return null;
	}

	public TeamPO hasTeam() {
		TeamPO result = new TeamPO(
				new de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.Team[] {});

		result.setModifier(this.getPattern().getModifier());
		super.hasLink(User.PROPERTY_TEAM, result);

		return result;
	}

	public TeamPO createTeam() {
		return this.startCreate().hasTeam().endCreate();
	}

	public UserPO hasTeam(TeamPO tgt) {
		return hasLinkConstraint(tgt, User.PROPERTY_TEAM);
	}

	public UserPO createTeam(TeamPO tgt) {
		return this.startCreate().hasTeam(tgt).endCreate();
	}

	public Team getTeam() {
		if (this.getPattern().getHasMatch()) {
			return ((User) this.getCurrentMatch()).getTeam();
		}
		return null;
	}

	public ZombieFighterPO hasZombieFighter() {
		ZombieFighterPO result = new ZombieFighterPO(
				new de.uks.se1.ss15.dtritus.zombiefighter.KI.global.ZombieFighter[] {});

		result.setModifier(this.getPattern().getModifier());
		super.hasLink(User.PROPERTY_ZOMBIEFIGHTER, result);

		return result;
	}

	public ZombieFighterPO createZombieFighter() {
		return this.startCreate().hasZombieFighter().endCreate();
	}

	public UserPO hasZombieFighter(ZombieFighterPO tgt) {
		return hasLinkConstraint(tgt, User.PROPERTY_ZOMBIEFIGHTER);
	}

	public UserPO createZombieFighter(ZombieFighterPO tgt) {
		return this.startCreate().hasZombieFighter(tgt).endCreate();
	}

	public ZombieFighter getZombieFighter() {
		if (this.getPattern().getHasMatch()) {
			return ((User) this.getCurrentMatch()).getZombieFighter();
		}
		return null;
	}

	public MessagePO hasMessagesInbox() {
		MessagePO result = new MessagePO(
				new de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.Message[] {});

		result.setModifier(this.getPattern().getModifier());
		super.hasLink(User.PROPERTY_MESSAGESINBOX, result);

		return result;
	}

	public MessagePO createMessagesInbox() {
		return this.startCreate().hasMessagesInbox().endCreate();
	}

	public UserPO hasMessagesInbox(MessagePO tgt) {
		return hasLinkConstraint(tgt, User.PROPERTY_MESSAGESINBOX);
	}

	public UserPO createMessagesInbox(MessagePO tgt) {
		return this.startCreate().hasMessagesInbox(tgt).endCreate();
	}

	public MessageSet getMessagesInbox() {
		if (this.getPattern().getHasMatch()) {
			return ((User) this.getCurrentMatch()).getMessagesInbox();
		}
		return null;
	}

	public MessagePO hasMessagesOutbox() {
		MessagePO result = new MessagePO(
				new de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.Message[] {});

		result.setModifier(this.getPattern().getModifier());
		super.hasLink(User.PROPERTY_MESSAGESOUTBOX, result);

		return result;
	}

	public MessagePO createMessagesOutbox() {
		return this.startCreate().hasMessagesOutbox().endCreate();
	}

	public UserPO hasMessagesOutbox(MessagePO tgt) {
		return hasLinkConstraint(tgt, User.PROPERTY_MESSAGESOUTBOX);
	}

	public UserPO createMessagesOutbox(MessagePO tgt) {
		return this.startCreate().hasMessagesOutbox(tgt).endCreate();
	}

	public MessageSet getMessagesOutbox() {
		if (this.getPattern().getHasMatch()) {
			return ((User) this.getCurrentMatch()).getMessagesOutbox();
		}
		return null;
	}

	// ==========================================================================

	public java.sql.Timestamp getLastInteraction() {
		if (this.getPattern().getHasMatch()) {
			return ((User) getCurrentMatch()).getLastInteraction();
		}
		return null;
	}

	public UserPO hasUnreadMessages(boolean value) {
		new AttributeConstraint().withAttrName(User.PROPERTY_UNREADMESSAGES)
				.withTgtValue(value).withSrc(this)
				.withModifier(this.getPattern().getModifier())
				.withPattern(this.getPattern());

		super.hasAttr();

		return this;
	}

	public UserPO createUnreadMessages(boolean value) {
		this.startCreate().hasUnreadMessages(value).endCreate();
		return this;
	}

	public boolean getUnreadMessages() {
		if (this.getPattern().getHasMatch()) {
			return ((User) getCurrentMatch()).isUnreadMessages();
		}
		return false;
	}

	public UserPO withUnreadMessages(boolean value) {
		if (this.getPattern().getHasMatch()) {
			((User) getCurrentMatch()).setUnreadMessages(value);
		}
		return this;
	}

	public UserPO hasIngame(boolean value) {
		new AttributeConstraint().withAttrName(User.PROPERTY_INGAME)
				.withTgtValue(value).withSrc(this)
				.withModifier(this.getPattern().getModifier())
				.withPattern(this.getPattern());

		super.hasAttr();

		return this;
	}

	public UserPO createIngame(boolean value) {
		this.startCreate().hasIngame(value).endCreate();
		return this;
	}

	public boolean getIngame() {
		if (this.getPattern().getHasMatch()) {
			return ((User) getCurrentMatch()).isIngame();
		}
		return false;
	}

	public UserPO withIngame(boolean value) {
		if (this.getPattern().getHasMatch()) {
			((User) getCurrentMatch()).setIngame(value);
		}
		return this;
	}   
}
