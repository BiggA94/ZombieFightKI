package de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.util;

import org.sdmlib.models.pattern.PatternObject;

import de.uks.se1.ss15.dtritus.zombiefighter.KI.global.ZombieFighter;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.global.util.ZombieFighterPO;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.Message;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.User;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.util.MessagePO;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.util.UserPO;

import org.sdmlib.models.pattern.AttributeConstraint;

import java.sql.Timestamp;

public class MessagePO extends PatternObject<MessagePO, Message> {

	public MessageSet allMatches() {
		this.setDoAllMatches(true);

		MessageSet matches = new MessageSet();

		while (this.getPattern().getHasMatch()) {
			matches.add((Message) this.getCurrentMatch());

			this.getPattern().findMatch();
		}

		return matches;
	}

	public MessagePO() {
		newInstance(de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util.CreatorCreator
				.createIdMap("PatternObjectType"));
	}

	public MessagePO(Message... hostGraphObject) {
		if (hostGraphObject == null || hostGraphObject.length < 1) {
			return;
		}
		newInstance(
				de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util.CreatorCreator
						.createIdMap("PatternObjectType"), hostGraphObject);
	}

	public MessagePO hasText(String value) {
		new AttributeConstraint().withAttrName(Message.PROPERTY_TEXT)
				.withTgtValue(value).withSrc(this)
				.withModifier(this.getPattern().getModifier())
				.withPattern(this.getPattern());

		super.hasAttr();

		return this;
	}

	public MessagePO hasText(String lower, String upper) {
		new AttributeConstraint().withAttrName(Message.PROPERTY_TEXT)
				.withTgtValue(lower).withUpperTgtValue(upper).withSrc(this)
				.withModifier(this.getPattern().getModifier())
				.withPattern(this.getPattern());

		super.hasAttr();

		return this;
	}

	public MessagePO createText(String value) {
		this.startCreate().hasText(value).endCreate();
		return this;
	}

	public String getText() {
		if (this.getPattern().getHasMatch()) {
			return ((Message) getCurrentMatch()).getText();
		}
		return null;
	}

	public MessagePO withText(String value) {
		if (this.getPattern().getHasMatch()) {
			((Message) getCurrentMatch()).setText(value);
		}
		return this;
	}

	public MessagePO hasDate(Timestamp value) {
		new AttributeConstraint().withAttrName(Message.PROPERTY_DATE)
				.withTgtValue(value).withSrc(this)
				.withModifier(this.getPattern().getModifier())
				.withPattern(this.getPattern());

		super.hasAttr();

		return this;
	}

	public MessagePO createDate(Timestamp value) {
		this.startCreate().hasDate(value).endCreate();
		return this;
	}

	public Timestamp getDate() {
		if (this.getPattern().getHasMatch()) {
			return ((Message) getCurrentMatch()).getDate();
		}
		return null;
	}

	public MessagePO withDate(Timestamp value) {
		if (this.getPattern().getHasMatch()) {
			((Message) getCurrentMatch()).setDate(value);
		}
		return this;
	}

	public ZombieFighterPO hasZombieFighter() {
		ZombieFighterPO result = new ZombieFighterPO(
				new de.uks.se1.ss15.dtritus.zombiefighter.KI.global.ZombieFighter[] {});

		result.setModifier(this.getPattern().getModifier());
		super.hasLink(Message.PROPERTY_ZOMBIEFIGHTER, result);

		return result;
	}

	public ZombieFighterPO createZombieFighter() {
		return this.startCreate().hasZombieFighter().endCreate();
	}

	public MessagePO hasZombieFighter(ZombieFighterPO tgt) {
		return hasLinkConstraint(tgt, Message.PROPERTY_ZOMBIEFIGHTER);
	}

	public MessagePO createZombieFighter(ZombieFighterPO tgt) {
		return this.startCreate().hasZombieFighter(tgt).endCreate();
	}

	public ZombieFighter getZombieFighter() {
		if (this.getPattern().getHasMatch()) {
			return ((Message) this.getCurrentMatch()).getZombieFighter();
		}
		return null;
	}

	public MessagePO hasDate(String value) {
		new AttributeConstraint().withAttrName(Message.PROPERTY_DATE)
				.withTgtValue(value).withSrc(this)
				.withModifier(this.getPattern().getModifier())
				.withPattern(this.getPattern());

		super.hasAttr();

		return this;
	}

	public MessagePO hasDate(String lower, String upper) {
		new AttributeConstraint().withAttrName(Message.PROPERTY_DATE)
				.withTgtValue(lower).withUpperTgtValue(upper).withSrc(this)
				.withModifier(this.getPattern().getModifier())
				.withPattern(this.getPattern());

		super.hasAttr();

		return this;
	}

	public MessagePO createDate(String value) {
		this.startCreate().hasDate(value).endCreate();
		return this;
	}

   public UserPO hasToUser()
   {
      UserPO result = new UserPO(new de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.User[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Message.PROPERTY_TOUSER, result);
      
      return result;
   }

   public UserPO createToUser()
   {
      return this.startCreate().hasToUser().endCreate();
   }

   public MessagePO hasToUser(UserPO tgt)
   {
      return hasLinkConstraint(tgt, Message.PROPERTY_TOUSER);
   }

   public MessagePO createToUser(UserPO tgt)
   {
      return this.startCreate().hasToUser(tgt).endCreate();
   }

   public User getToUser()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Message) this.getCurrentMatch()).getToUser();
      }
      return null;
   }

   public UserPO hasFromUser()
   {
      UserPO result = new UserPO(new de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.User[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Message.PROPERTY_FROMUSER, result);
      
      return result;
   }

   public UserPO createFromUser()
   {
      return this.startCreate().hasFromUser().endCreate();
   }

   public MessagePO hasFromUser(UserPO tgt)
   {
      return hasLinkConstraint(tgt, Message.PROPERTY_FROMUSER);
   }

   public MessagePO createFromUser(UserPO tgt)
   {
      return this.startCreate().hasFromUser(tgt).endCreate();
   }

   public User getFromUser()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Message) this.getCurrentMatch()).getFromUser();
      }
      return null;
   }

}
