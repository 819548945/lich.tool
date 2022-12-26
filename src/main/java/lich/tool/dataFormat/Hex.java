package lich.tool.dataFormat;

import java.io.ByteArrayOutputStream;

/**
 * Converts hexadecimal Strings.
 */
public class Hex {
    private static final char[] DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public static String encodeHex(byte[] data) {
        return encodeHex(data, 0);
    }

    public static String encodeHex(byte[] data, int group) {
        int l = data.length;
        char[] out = new char[(l << 1) + (group > 0 ? (l / group) : 0)];
        // two characters form the hex value.
        for (int i = 0, j = 0; i < l; i++) {
            if ((group > 0) && ((i % group) == 0) && j > 0) {
                out[j++] = '-';
            }

            out[j++] = DIGITS[(0xF0 & data[i]) >>> 4];
            out[j++] = DIGITS[0x0F & data[i]];
        }
        return new String(out);
    }

    public static byte[] decodeHex(String hexString) {
        ByteArrayOutputStream bas = new ByteArrayOutputStream();
        for (int i = 0; i < hexString.length(); i += 2) {
            int b = Integer.parseInt(hexString.substring(i, i + 2), 16);
            bas.write(b);
        }
        return bas.toByteArray();
    }
}
