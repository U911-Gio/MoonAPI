package be.moondevelopment.moonapi.spigot.framework.gui.legacy;
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

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

@Deprecated
public abstract class LegacyGUI implements InventoryHolder {

    /**
     * The inventory of the GUI
     */
    protected final Inventory inventory;

    /**
     * The title of the GUI
     */
    @Getter private final String title;

    /**
     * The size of the GUI
     */
    @Getter private int size = -1;

    /**
     * The type of the GUI
     */
    @Getter private InventoryType type = null;

    /**
     * LegacyGUI constructor
     *
     * @param title The title of the GUI
     */
    @Deprecated
    public LegacyGUI(String title) {
        this(title, 3);
    }

    /**
     * LegacyGUI constructor
     *
     * @param title The title of the GUI
     * @param rows The amount of rows of the GUI
     */
    @Deprecated
    public LegacyGUI(String title, int rows) {
        this.title = title;
        this.size = rows * 9;
        this.inventory = Bukkit.createInventory(this, size, title);
    }

    /**
     * LegacyGUI constructor
     *
     * @param title The title of the GUI
     * @param type The type of the GUI
     */
    @Deprecated
    public LegacyGUI(String title, InventoryType type) {
        this.title = title;
        this.type = type;
        this.inventory = Bukkit.createInventory(this, type, title);
    }

    /**
     * Sets an item in the GUI
     *
     * @param x The x coordinate of the item
     * @param y The y coordinate of the item
     * @param stack The item to set
     */
    protected void setItem(int x, int y, ItemStack stack) {
        inventory.setItem(y * 9 + x, stack);
    }

    /**
     * Creates the GUI
     */
    public void create() {
        setContents();
    }

    /**
     * Opens the GUI for a player
     * @param player
     */
    public void open(Player player) {
        setContents();

        player.openInventory(this.inventory);
    }

    /**
     * Sets the contents of the GUI
     */
    protected abstract void setContents();

    /**
     * Get the inventory of the GUI
     *
     * @return Inventory
     */
    @Override public Inventory getInventory() {
        return inventory;
    }


    /**
     * Initializes the LegacyGUI
     *
     * @param plugin
     */
    public static void init(Plugin plugin) {
        Bukkit.getPluginManager().registerEvents(new Listener() {
            @EventHandler
            public void onClick(InventoryClickEvent event) {
                Inventory inventory = event.getClickedInventory();
                if (inventory == null) return;

                InventoryHolder inventoryHolder = inventory.getHolder();
                if (!(inventoryHolder instanceof LegacyGUI)) return;
                LegacyGUI gui = (LegacyGUI) inventoryHolder;

                event.setCancelled(true);
                gui.clickAction(event);

            }

            @EventHandler
            public void onClose(InventoryCloseEvent event) {
                Inventory inventory = event.getInventory();

                InventoryHolder inventoryHolder = inventory.getHolder();
                if (!(inventoryHolder instanceof LegacyGUI)) return;
                LegacyGUI gui = (LegacyGUI) inventoryHolder;

                gui.closeAction(event);

            }
        }, plugin);
    }

    /**
     * Draws an outline of the GUI
     *
     * @param inventory
     * @param rows
     * @param itemStack
     */
    protected void drawOutline(Inventory inventory, int rows, ItemStack itemStack) {
        for (int i = 1; i < 10; i++) {
            inventory.setItem(i - 1, itemStack);
            inventory.setItem((rows * 9) - i, itemStack);
        }

        for (int i = 1; i < rows; i++) {
            inventory.setItem(i * 9, itemStack);
            inventory.setItem(i * 9 - 1, itemStack);
        }
    }

    /**
     * Event when a player clicks in the GUI
     *
     * @param event InventoryClickEvent
     */
    protected abstract void clickAction(InventoryClickEvent event);

    /**
     * Event when a player closes the GUI
     *
     * @param event InventoryCloseEvent
     */
    protected abstract void closeAction(InventoryCloseEvent event);

}
