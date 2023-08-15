package be.moondevelopment.moonapi.spigot.framework.bungeeapi;
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

import com.google.common.base.Preconditions;
import java.awt.Color;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;
import lombok.Getter;

/**
 * Simplistic enumeration of all supported color values for chat.
 */
public final class ChatColor
{

    /**
     * The special character which prefixes all chat colour codes. Use this if
     * you need to dynamically convert colour codes from your custom format.
     */
    public static final char COLOR_CHAR = '\u00A7';
    public static final String ALL_CODES = "0123456789AaBbCcDdEeFfKkLlMmNnOoRrXx";
    /**
     * Pattern to remove all colour codes.
     */
    public static final Pattern STRIP_COLOR_PATTERN = Pattern.compile( "(?i)" + String.valueOf( COLOR_CHAR ) + "[0-9A-FK-ORX]" );
    /**
     * Colour instances keyed by their active character.
     */
    private static final Map<Character, ChatColor> BY_CHAR = new HashMap<Character, ChatColor>();
    /**
     * Colour instances keyed by their name.
     */
    private static final Map<String, ChatColor> BY_NAME = new HashMap<String, ChatColor>();
    /**
     * Represents black.
     */
    public static final ChatColor BLACK = new ChatColor( '0', "black", new Color( 0x000000 ) );
    /**
     * Represents dark blue.
     */
    public static final ChatColor DARK_BLUE = new ChatColor( '1', "dark_blue", new Color( 0x0000AA ) );
    /**
     * Represents dark green.
     */
    public static final ChatColor DARK_GREEN = new ChatColor( '2', "dark_green", new Color( 0x00AA00 ) );
    /**
     * Represents dark blue (aqua).
     */
    public static final ChatColor DARK_AQUA = new ChatColor( '3', "dark_aqua", new Color( 0x00AAAA ) );
    /**
     * Represents dark red.
     */
    public static final ChatColor DARK_RED = new ChatColor( '4', "dark_red", new Color( 0xAA0000 ) );
    /**
     * Represents dark purple.
     */
    public static final ChatColor DARK_PURPLE = new ChatColor( '5', "dark_purple", new Color( 0xAA00AA ) );
    /**
     * Represents gold.
     */
    public static final ChatColor GOLD = new ChatColor( '6', "gold", new Color( 0xFFAA00 ) );
    /**
     * Represents gray.
     */
    public static final ChatColor GRAY = new ChatColor( '7', "gray", new Color( 0xAAAAAA ) );
    /**
     * Represents dark gray.
     */
    public static final ChatColor DARK_GRAY = new ChatColor( '8', "dark_gray", new Color( 0x555555 ) );
    /**
     * Represents blue.
     */
    public static final ChatColor BLUE = new ChatColor( '9', "blue", new Color( 0x5555FF ) );
    /**
     * Represents green.
     */
    public static final ChatColor GREEN = new ChatColor( 'a', "green", new Color( 0x55FF55 ) );
    /**
     * Represents aqua.
     */
    public static final ChatColor AQUA = new ChatColor( 'b', "aqua", new Color( 0x55FFFF ) );
    /**
     * Represents red.
     */
    public static final ChatColor RED = new ChatColor( 'c', "red", new Color( 0xFF5555 ) );
    /**
     * Represents light purple.
     */
    public static final ChatColor LIGHT_PURPLE = new ChatColor( 'd', "light_purple", new Color( 0xFF55FF ) );
    /**
     * Represents yellow.
     */
    public static final ChatColor YELLOW = new ChatColor( 'e', "yellow", new Color( 0xFFFF55 ) );
    /**
     * Represents white.
     */
    public static final ChatColor WHITE = new ChatColor( 'f', "white", new Color( 0xFFFFFF ) );
    /**
     * Represents magical characters that change around randomly.
     */
    public static final ChatColor MAGIC = new ChatColor( 'k', "obfuscated" );
    /**
     * Makes the text bold.
     */
    public static final ChatColor BOLD = new ChatColor( 'l', "bold" );
    /**
     * Makes a line appear through the text.
     */
    public static final ChatColor STRIKETHROUGH = new ChatColor( 'm', "strikethrough" );
    /**
     * Makes the text appear underlined.
     */
    public static final ChatColor UNDERLINE = new ChatColor( 'n', "underline" );
    /**
     * Makes the text italic.
     */
    public static final ChatColor ITALIC = new ChatColor( 'o', "italic" );
    /**
     * Resets all previous chat colors or formats.
     */
    public static final ChatColor RESET = new ChatColor( 'r', "reset" );
    /**
     * Count used for populating legacy ordinal.
     */
    private static int count = 0;
    /**
     * This colour's colour char prefixed by the {@link #COLOR_CHAR}.
     */
    private final String toString;
    @Getter
    private final String name;
    private final int ordinal;
    /**
     * The RGB color of the ChatColor. null for non-colors (formatting)
     */
    @Getter
    private final Color color;

    private ChatColor(char code, String name)
    {
        this( code, name, null );
    }

    private ChatColor(char code, String name, Color color)
    {
        this.name = name;
        this.toString = new String( new char[]
                {
                        COLOR_CHAR, code
                } );
        this.ordinal = count++;
        this.color = color;

        BY_CHAR.put( code, this );
        BY_NAME.put( name.toUpperCase( Locale.ROOT ), this );
    }

    private ChatColor(String name, String toString, int rgb)
    {
        this.name = name;
        this.toString = toString;
        this.ordinal = -1;
        this.color = new Color( rgb );
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode( this.toString );
        return hash;
    }

    @Override
    public boolean equals(Object obj)
    {
        if ( this == obj )
        {
            return true;
        }
        if ( obj == null || getClass() != obj.getClass() )
        {
            return false;
        }
        final ChatColor other = (ChatColor) obj;

        return Objects.equals( this.toString, other.toString );
    }

    @Override
    public String toString()
    {
        return toString;
    }

    /**
     * Strips the given message of all color codes
     *
     * @param input String to strip of color
     * @return A copy of the input string, without any coloring
     */
    public static String stripColor(final String input)
    {
        if ( input == null )
        {
            return null;
        }

        return STRIP_COLOR_PATTERN.matcher( input ).replaceAll( "" );
    }

    public static String translateAlternateColorCodes(char altColorChar, String textToTranslate)
    {
        char[] b = textToTranslate.toCharArray();
        for ( int i = 0; i < b.length - 1; i++ )
        {
            if ( b[i] == altColorChar && ALL_CODES.indexOf( b[i + 1] ) > -1 )
            {
                b[i] = ChatColor.COLOR_CHAR;
                b[i + 1] = Character.toLowerCase( b[i + 1] );
            }
        }
        return new String( b );
    }

    /**
     * Get the colour represented by the specified code.
     *
     * @param code the code to search for
     * @return the mapped colour, or null if non exists
     */
    public static ChatColor getByChar(char code)
    {
        return BY_CHAR.get( code );
    }

    public static ChatColor of(Color color)
    {
        return of( "#" + String.format( "%08x", color.getRGB() ).substring( 2 ) );
    }

    public static ChatColor of(String string)
    {
        Preconditions.checkArgument( string != null, "string cannot be null" );
        if ( string.startsWith( "#" ) && string.length() == 7 )
        {
            int rgb;
            try
            {
                rgb = Integer.parseInt( string.substring( 1 ), 16 );
            } catch ( NumberFormatException ex )
            {
                throw new IllegalArgumentException( "Illegal hex string " + string );
            }

            StringBuilder magic = new StringBuilder( COLOR_CHAR + "x" );
            for ( char c : string.substring( 1 ).toCharArray() )
            {
                magic.append( COLOR_CHAR ).append( c );
            }

            return new ChatColor( string, magic.toString(), rgb );
        }

        ChatColor defined = BY_NAME.get( string.toUpperCase( Locale.ROOT ) );
        if ( defined != null )
        {
            return defined;
        }

        throw new IllegalArgumentException( "Could not parse ChatColor " + string );
    }

    /**
     * See {@link Enum#valueOf(java.lang.Class, java.lang.String)}.
     *
     * @param name color name
     * @return ChatColor
     * @deprecated holdover from when this class was an enum
     */
    @Deprecated
    public static ChatColor valueOf(String name)
    {
        Preconditions.checkNotNull( name, "Name is null" );

        ChatColor defined = BY_NAME.get( name );
        Preconditions.checkArgument( defined != null, "No enum constant " + ChatColor.class.getName() + "." + name );

        return defined;
    }

    /**
     * Get an array of all defined colors and formats.
     *
     * @return copied array of all colors and formats
     * @deprecated holdover from when this class was an enum
     */
    @Deprecated
    public static ChatColor[] values()
    {
        return BY_CHAR.values().toArray( new ChatColor[ 0 ] );
    }

    /**
     * See {@link Enum#name()}.
     *
     * @return constant name
     * @deprecated holdover from when this class was an enum
     */
    @Deprecated
    public String name()
    {
        return getName().toUpperCase( Locale.ROOT );
    }

    /**
     * See {@link Enum#ordinal()}.
     *
     * @return ordinal
     * @deprecated holdover from when this class was an enum
     */
    @Deprecated
    public int ordinal()
    {
        Preconditions.checkArgument( ordinal >= 0, "Cannot get ordinal of hex color" );
        return ordinal;
    }
}
