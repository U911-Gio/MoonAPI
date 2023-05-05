package be.moondevelopment.moonapi.framework.utils;
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

public final class VersionUtil {

    /**
     * The string representation of the version, for example V1_13
     */
    private static String serverVersion;

    /**
     * The wrapper representation of the version
     */
    @Getter
    private static V current;

    /**
     * The version wrapper
     */
    public enum V {
        v1_20(20, false),
        v1_19(19),
        v1_18(18),
        v1_17(17),
        v1_16(16),
        v1_15(15),
        v1_14(14),
        v1_13(13),
        v1_12(12),
        v1_11(11),
        v1_10(10),
        v1_9(9),
        v1_8(8),
        v1_7(7),
        v1_6(6),
        v1_5(5),
        v1_4(4),
        v1_3_AND_BELOW(3);

        /**
         * The numeric version (the second part of the 1.x number)
         */
        private final int minorVersionNumber;

        /**
         * Is this library tested with this Minecraft version?
         */
        @Getter
        private final boolean tested;

        /**
         * Creates new enum for a MC version that is tested
         *
         * @param version
         */
        V(int version) {
            this(version, true);
        }

        /**
         * Creates new enum for a MC version
         *
         * @param version
         * @param tested
         */
        V(int version, boolean tested) {
            this.minorVersionNumber = version;
            this.tested = tested;
        }

        /**
         * Attempts to get the version from number
         *
         * @param number
         * @return
         * @throws RuntimeException if number not found
         */
        private static V parse(int number) {
            for (final V v : values()) {
                if (v.minorVersionNumber == number) {
                    return v;
                }
            }
            return null;
        }

        /**
         * @see java.lang.Enum#toString()
         */
        @Override
        public String toString() {
            return "1." + this.minorVersionNumber;
        }
    }

    /**
     * Does the current Minecraft version equal the given version?
     *
     * @param version
     * @return
     */
    public static boolean equals(V version) {
        return compareWith(version) == 0;
    }

    /**
     * Is the current Minecraft version older than the given version?
     *
     * @param version
     * @return
     */
    public static boolean olderThan(V version) {
        return compareWith(version) < 0;
    }

    /**
     * Is the current Minecraft version newer than the given version?
     *
     * @param version
     * @return
     */
    public static boolean newerThan(V version) {
        return compareWith(version) > 0;
    }

    /**
     * Is the current Minecraft version at equals or newer than the given version?
     *
     * @param version
     * @return
     */
    public static boolean atLeast(V version) {
        return equals(version) || newerThan(version);
    }

    // Compares two versions by the number
    private static int compareWith(V version) {
        try {
            return getCurrent().minorVersionNumber - version.minorVersionNumber;

        } catch (final Throwable t) {
            t.printStackTrace();

            return 0;
        }
    }

    /**
     * Return the class versioning such as v1_14_R1
     *
     * @return
     */
    public static String getServerVersion() {
        return serverVersion.equals("craftbukkit") ? "" : serverVersion;
    }

    // Initialize the version
    static {
        try {

            final String packageName = Bukkit.getServer() == null ? "" : Bukkit.getServer().getClass().getPackage().getName();
            final String curr = packageName.substring(packageName.lastIndexOf('.') + 1);
            final boolean hasGatekeeper = !"craftbukkit".equals(curr) && !"".equals(packageName);

            serverVersion = curr;

            if (hasGatekeeper) {
                int pos = 0;

                for (final char ch : curr.toCharArray()) {
                    pos++;

                    if (pos > 2 && ch == 'R')
                        break;
                }

                final String numericVersion = curr.substring(1, pos - 2).replace("_", ".");

                int found = 0;

                for (final char ch : numericVersion.toCharArray())
                    if (ch == '.')
                        found++;

                current = V.parse(Integer.parseInt(numericVersion.split("\\.")[1]));

            } else
                current = V.v1_3_AND_BELOW;

        } catch (final Throwable t) {
            t.printStackTrace();
        }
    }
}
