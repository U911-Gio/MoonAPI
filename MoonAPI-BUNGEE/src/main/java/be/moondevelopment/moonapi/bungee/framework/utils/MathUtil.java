package be.moondevelopment.moonapi.bungee.framework.utils;
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

import java.util.ArrayList;
import java.util.Arrays;

public class MathUtil {

    /**
     * Formats number to format ('k','M','B','T','q','Q','QT','S','SP','O','N','D'
     * If number is or equal to 1000.0 will return the original number.
     * Supports also negative amounts
     *
     * @param amount number to format
     * @return Formatted number as string
     */
    public static String formatNumber(double amount) {

        boolean negative = amount < 0;
        String prefix = "";

        if (negative) {
            amount = Math.abs(amount);
            prefix = "-";
        }

        if (amount <= 1000.0D)
            return prefix + amount;
        ArrayList<String> suffixes = new ArrayList<>(Arrays.asList("", "k", "M", "B", "T", "q", "Q", "QT", "S", "SP", "O",
                "N", "D"));
        double chunks = Math.floor(Math.floor(Math.log10(amount) / 3.0D));
        amount /= Math.pow(10.0D, chunks * 3.0D - 1.0D);
        amount /= 10.0D;
        String suffix = suffixes.get((int) chunks);
        String format = String.valueOf(amount);
        if (format.replace(".", "").length() > 5)
            format = format.substring(0, 5);
        return prefix + format + suffix;
    }
}
