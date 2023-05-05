package be.moondevelopment.moonapi.framework.item.legacy;
/*
 * @author MoonDevelopment
 * @website https://www.moondevelopment.be/
 * @license GNU General Public License v3.0
 *
 * The GNU General Public License is a free, copyleft license for software and other kinds of works.
 *
 * The licenses for most software and other practical works are designed
 * to take away your freedom to share and change the works.  By contrast,
 * the GNU General Public License is intended to guarantee your freedom to
 * share and change all versions of a program--to make sure it remains free
 * software for all its users.  We, the Free Software Foundation, use the
 * GNU General Public License for most of our software; it applies also to
 * any other work released this way by its authors.  You can apply it to
 * your programs, too.
 *
 * When we speak of free software, we are referring to freedom, not
 * price.  Our General Public Licenses are designed to make sure that you
 * have the freedom to distribute copies of free software (and charge for
 * them if you wish), that you receive source code or can get it if you
 * want it, that you can change the software or use pieces of it in new
 * free programs, and that you know you can do these things.
 *
 * To protect your rights, we need to prevent others from denying you
 * these rights or asking you to surrender the rights.  Therefore, you have
 * certain responsibilities if you distribute copies of the software, or if
 * you modify it: responsibilities to respect the freedom of others.
 *
 * For example, if you distribute copies of such a program, whether
 * free or for a fee, you must pass on to the recipients the same
 * freedoms that you received.  You must make sure that they, too, receive
 * or can get the source code.  And you must show them these terms so they
 * know their rights.
 *
 * Developers that use the GNU GPL protect your rights with two steps:
 * (1) assert copyright on the software, and (2) offer you this License
 * giving you legal permission to copy, distribute and/or modify it.
 *
 * For the developers' and authors' protection, the GPL clearly explains
 * that there is no warranty for this free software.  For both users' and
 * authors' sake, the GPL requires that modified versions be marked as
 * changed, so that their problems will not be attributed erroneously to
 * authors of previous versions.
 *
 * Some devices are designed to deny users access to install or run
 * modified versions of the software inside them, although the manufacturer
 * can do so.  This is fundamentally incompatible with the aim of
 * protecting users' freedom to change the software.  The systematic
 * pattern of such abuse occurs in the area of products for individuals to
 * use, which is precisely where it is most unacceptable.  Therefore, we
 * have designed this version of the GPL to prohibit the practice for those
 * products.  If such problems arise substantially in other domains, we
 * stand ready to extend this provision to those domains in future versions
 * of the GPL, as needed to protect the freedom of users.
 *
 * Finally, every program is threatened constantly by software patents.
 * States should not allow patents to restrict development and use of
 * software on general-purpose computers, but in those that do, we wish to
 * avoid the special danger that patents applied to a free program could
 * make it effectively proprietary.  To prevent this, the GPL assures that
 * patents cannot be used to render the program non-free.

 * The precise terms and conditions for copying, distribution and
 * modification follow.
 */

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import be.moondevelopment.moonapi.framework.utils.ColorUtil;
import org.apache.commons.lang.Validate;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.material.MaterialData;

import javax.annotation.Nonnull;
import java.lang.reflect.Field;
import java.util.*;

public class LegacyItemBuilder {

	private final ItemStack item;
	private ItemMeta meta;
	private List<String> lore;
	private boolean colorize = true;

	/**
	 * Creates a new ItemBuilder Object for the given {@link Material}
	 *
	 * @param material The material used for creating the new Object
	 */

	@Deprecated
	public LegacyItemBuilder(@Nonnull Material material) {
		Validate.notNull(material, "Material must not be null");

		this.item = new ItemStack(material);
		this.meta = item.getItemMeta();
	}

	/**
	 * Creates a new ItemBuilder Object for the given {@link ItemStack}
	 *
	 * @param item The item used for creating the new Object
	 */
	@Deprecated
	public LegacyItemBuilder(@Nonnull ItemStack item) {
		Validate.notNull(item, "Item must not be null");

		this.item = item;
		this.meta = item.getItemMeta();
	}

	/**
	 * Creates a new ItemBuilder Object for the given {@link ItemStack} and {@link ItemMeta}
	 *
	 * @param item The item used for creating the new Object
	 * @param meta The ItemMeta used for creating the new Object
	 */
	public LegacyItemBuilder(@Nonnull ItemStack item, @Nonnull ItemMeta meta) {
		Validate.notNull(item, "Item must not be null");
		Validate.notNull(meta, "Meta must not be null");

		this.item = item;
		this.meta = meta;
	}

	/**
	 * Sets the amount of the item
	 *
	 * @param amount Amount
	 */
	public LegacyItemBuilder setAmount(int amount) {
		item.setAmount(amount);
		return this;
	}

	/**
	 * Sets the name of the item
	 *
	 * @param name Name
	 */
	public LegacyItemBuilder setName(@Nonnull String name) {
		Validate.notNull(name, "Name must not be null");

		meta.setDisplayName(colorize(name));
		return this;
	}




	/**
	 * Adds an enchantment
	 *
	 * @param enchantment Enchantment to add
	 * @param level Enchantment Level
	 */
	public LegacyItemBuilder addEnchantment(@Nonnull Enchantment enchantment, int level) {
		Validate.notNull(enchantment, "Enchantment must not be null");

		meta.addEnchant(enchantment, level, false);
		return this;
	}

	/**
	 * Adds multiple enchantments at once
	 *
	 * @param enchantments The enchantments to add
	 */
	public LegacyItemBuilder addEnchantments(@Nonnull Map<Enchantment, Integer> enchantments) {
		Validate.notNull(enchantments, "Enchantments must not be null");

		enchantments.forEach(
				(e, l) -> meta.addEnchant(e, l, false)
		);
		return this;
	}

	/**
	 * Adds an unsafe enchantment
	 * <p>This method is unsafe and will ignore level restrictions or item type. Use at your own discretion.</p>
	 *
	 * @param enchantment The enchantment to add
	 * @param level Enchantment Level
	 * @
	 */
	public LegacyItemBuilder addUnsafeEnchantment(@Nonnull Enchantment enchantment, int level) {
		Validate.notNull(enchantment, "Enchantment must not be null");

		meta.addEnchant(enchantment, level, true);
		return this;
	}

	/**
	 * Adds multiple unsafe enchantments at once
	 * <p>This method is unsafe and will ignore level restrictions or item type. Use at your own discretion.</p>
	 *
	 * @param enchantments The enchantments to add
	 */
	public LegacyItemBuilder addUnsafeEnchantments(@Nonnull Map<Enchantment, Integer> enchantments) {
		Validate.notNull(enchantments, "Enchantments must not be null");

		enchantments.forEach(
				(e, l) -> meta.addEnchant(e, l, true)
		);
		return this;
	}

	/**
	 * Removes an enchantment
	 *
	 * @param enchantment The enchantment to remove
	 */
	public LegacyItemBuilder removeEnchantment(@Nonnull Enchantment enchantment) {
		Validate.notNull(enchantment, "Enchantment must not be null");

		meta.removeEnchant(enchantment);
		return this;
	}

	/**
	 * Adds the given string as a lore
	 *
	 * @param lore The string to add
	 */
	public LegacyItemBuilder addLore(@Nonnull String lore) {
		Validate.notNull(lore, "Lore must not be null");

		this.lore.add(colorize(lore));
		meta.setLore(this.lore);
		return this;
	}

	/**
	 * Adds the given strings as a lore
	 *
	 * @param lore The strings to add
	 */
	public LegacyItemBuilder setLore(@Nonnull String... lore) {
		Validate.notNull(lore, "Lore must not be null");

		this.lore = Arrays.asList(colorize(lore));
		return this;
	}

	/**
	 * Adds the given list as a lore
	 *
	 * @param lore The list to add
	 */
	public LegacyItemBuilder setLore(@Nonnull List<String> lore) {
		Validate.notNull(lore, "Lore must not be null");

		this.lore = colorize(lore);
		return this;
	}

	/**
	 * Removes a line from the lore based on the index
	 *
	 * @param index The line to remove
	 */
	public LegacyItemBuilder removeLoreLine(int index) {
		if (index < 0 || lore.size() < 0) return this;
		lore.remove(index);
		meta.setLore(lore);
		return this;
	}

	/**
	 * Removes a line from the lore based on the line string
	 *
	 * @param line The line to remove
	 */
	public LegacyItemBuilder removeLoreLine(@Nonnull String line) {
		Validate.notNull(line, "line must not be null");

		if (!lore.contains(line)) return this;
		lore.remove(line);
		meta.setLore(lore);
		return this;
	}

	/**
	 * Adds an {@link ItemFlag}
	 *
	 * @param itemFlags The ItemFlag to add
	 */
	public LegacyItemBuilder addItemFlags(@Nonnull ItemFlag... itemFlags) {
		Validate.notNull(item, "ItemFlag must not be null");

		meta.addItemFlags(itemFlags);
		return this;
	}

	/**
	 * Removes an ItemFlag
	 *
	 * @param itemFlags The ItemFlag to remove
	 */
	public LegacyItemBuilder removeItemFlags(@Nonnull ItemFlag... itemFlags) {
		Validate.notNull(itemFlags, "ItemFlag must not be null");

		meta.removeItemFlags(itemFlags);
		return this;
	}

	/**
	 * Sets the material data
	 *
	 * @param materialData The material data to set
	 */
	@Deprecated
	public LegacyItemBuilder setMaterialData(@Nonnull MaterialData materialData) {
		Validate.notNull(materialData, "MaterialData must not be null");

		item.setData(materialData);
		return this;
	}

	/**
	 * Set as unbreakable
	 *
	 * @param unbreakable Whether the item should be unbreakable
	 */
	public LegacyItemBuilder setUnbreakable(boolean unbreakable) {
		meta.spigot().setUnbreakable(unbreakable);
		return this;
	}

	/**
	 * Set glowing
	 *
	 * @param b Whether the item should glow
	 */
	public LegacyItemBuilder setGlow(boolean b) {
		addUnsafeEnchantment(item.getType() != Material.BOW ? Enchantment.ARROW_INFINITE : Enchantment.LUCK, 1);
		addItemFlags(ItemFlag.HIDE_ENCHANTS);
		return this;
	}

	/**
	 * Set the owner as an {@link OfflinePlayer}
	 *
	 * @param owner The owner
	 */
	public LegacyItemBuilder setOwner(@Nonnull OfflinePlayer owner) {
		Validate.notNull(owner, "Owner must not be null");

		if (meta instanceof SkullMeta) {
			((SkullMeta) meta).setOwner(owner.getName());
		}
		return this;
	}

	/**
	 * Set the owner as an {@link String}
	 *
	 * @param owner The owner
	 */
	public LegacyItemBuilder setOwner(@Nonnull String owner) {
		Validate.notNull(owner, "Owner must not be null");

		if (meta instanceof SkullMeta) {
			((SkullMeta) meta).setOwner(owner);
		}
		return this;
	}

	/**
	 * Set item meta
	 *
	 * @param meta The item meta
	 */
	public LegacyItemBuilder setItemMeta(@Nonnull ItemMeta meta) {
		Validate.notNull(meta, "Meta must not be null");

		this.meta = meta;
		return this;
	}

	/**
	 * Set if strings should be colorized by the builder
	 *
	 * @param b Whether to colorize strings
	 */
	public LegacyItemBuilder setColorize(boolean b) {
		colorize = b;
		return this;
	}

	/**
	 * Build the item
	 *
	 * @return The finished item
	 */
	public ItemStack toItemStack() {
		meta.setLore(lore);
		item.setItemMeta(meta);
		return item;
	}

	/**
	 * Check if server is paper
	 *
	 * @return Whether the server is running paper
	 */
	private boolean isPaper() {
		try {
			Class.forName("com.destroystokyo.paper.profile");
			return true;
		} catch (ClassNotFoundException e) {
			return false;
		}
	}

	/**
	 * Colorize a list of strings
	 *
	 * @param string The list to colorize
	 * @return Colorized list of strings
	 */
	private List<String> colorize(List<String> string) {
		if (!colorize) return string;

		List<String> result = new ArrayList<>();
		string.forEach(
				(str) -> result.add(colorize(str))
		);
		return result;
	}

	/**
	 * Colorize an array of strings
	 *
	 * @param string The array to colorize
	 * @return Colorized array of strings
	 */
	private String[] colorize(String... string) {
		if (!colorize) return string;

		List<String> result = new ArrayList<>();
		Arrays.asList(string).forEach(
				(str) -> result.add(colorize(str))
		);
		return result.toArray(new String[0]);
	}

	/**
	 * Colorize a string
	 *
	 * @param string The string to colorize
	 * @return Colorized string
	 */
	private String colorize(String string) {
		if (!colorize) return string;
		return ColorUtil.CC(string);
	}

	public LegacyItemBuilder setNoName() {
		meta.setDisplayName(" ");
		return this;
	}

	public LegacyItemBuilder hideNBT() {
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		return this;
	}

	public LegacyItemBuilder setProfile(String string) {
		GameProfile profile = new GameProfile(UUID.randomUUID(), null);
		profile.getProperties().put("textures", new Property("textures", string));

		Field field;
		SkullMeta meta2 = (SkullMeta) meta;

		try {
			field = meta2.getClass().getDeclaredField("profile");
			field.setAccessible(true);
			field.set(meta2, profile);
		} catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return this;
	}

	public LegacyItemBuilder setDurability(short durability) {
		item.setDurability(durability);
		return this;
	}

	public LegacyItemBuilder setColor(Color c) {
		((LeatherArmorMeta) meta).setColor(c);
		return this;
	}

	public LegacyItemBuilder addLeatherColor(Color color) {
		((LeatherArmorMeta) meta).setColor( color );
		return this;
	}

	public LegacyItemBuilder setData(short data) {
		item.setDurability(data);
		return this;
	}


}