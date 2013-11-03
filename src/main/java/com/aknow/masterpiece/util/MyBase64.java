package com.aknow.masterpiece.util;

public class MyBase64 {
    final static char[] base64 = {
        'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
        'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f',
        'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
        'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'
    };

    public static String encode(String str){
        byte[] b = str.getBytes();
        int[] p = new int[b.length];
        String out = "";
        int i, j, bb = 0;

        for(i = 0; i < b.length; i++){
            if(b[i] < 0)p[i] = b[i] + 256;
            else p[i] = b[i];
        }

        for(i = j = 0; i < p.length; i++){
            bb = (bb << 8) + p[i];
            if(j == 2){
                out += encode24(bb, 2);
                j = bb = 0;
            }
            else j++;
        }
        if(j > 0)out += encode24(bb, j - 1);

        return out;
    }

    private static String encode24(int bb, int srclen){
        String out = "";
        int base, x;

        bb <<= 8 * (2 - srclen);
        for(base = 18, x = 0; x < srclen + 2; x++, base -= 6){
            out += base64[(bb >> base) & 0x3F];
        }
        for(int i = x; i < 4; i++)out += "=";

        return out;
    }

    public static String decode(String str){
        byte[] b = str.getBytes();
        int[] p = new int[b.length];
        int[] map = new int [128];
        byte[] out = new byte[p.length * 3 / 4];
        int i, j, bb = 0, count = 0;

        for(i = 0; i < b.length; i++){
            if(b[i] < 0)p[i] = b[i] + 256;
            else p[i] = b[i];
        }

        map['='] = 0;
        for(i = 0; i < base64.length; i++){
            map[(int)base64[i]] = i;
        }

        for(i = j = 0; i < p.length; i++){
            bb = (bb << 6) + map[(int)p[i]];
            if(j == 3){
                count += decode24(bb, out, count);
                j = bb = 0;
            }
            else j++;
        }

        byte[] out2 = new byte[count];
        for(i = 0; i < count; i++)out2[i] = out[i];

        return new String(out2);
    }

    private static int decode24(int bb, byte[] out, int count){
        int i;
        for(i = 0; bb != 0; i++, bb = (bb << 8) & 0xffffff){
            out[count++] = (byte)((bb & 0x00ff0000) >> 16);
        }
        return i;
    }
}
