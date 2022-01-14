package com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.util;


import java.io.File;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ByteUtils
{

	public static boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() ){
            return false;
        }
        return true;
 }
	
	
    public static long bytesToLong(byte[] input, int offset)
    {
        String str = byte2String(input, offset, 8);
        return Long.parseLong(str);
    }

    private static String byte2String(byte[] input, int offset, int length)
    {
        StringBuffer sb = new StringBuffer();
        int end = (offset + length) > input.length ? input.length : offset + length;
        for (int index = offset; index < end; index++ )
        {
            sb.append(Byte.toString(input[index]));
        }
        return sb.toString();
    }

    // public static String bytesToHexString(byte[] input, int offset,int length) {
    // StringBuffer sb = new StringBuffer();
    // int end = (offset + length) > input.length ? input.length : offset + length;
    // for (int index=offset;index < end;index++)
    // {
    // sb.append(Long.toHexString(input[index]));
    // }
    // return sb.toString().toUpperCase();
    // }

    public static String byteToBinString(byte input)
    {
        String bin = Long.toBinaryString(input);
        return bin;
    }

    public static String getBitString(byte by)
    {
        StringBuffer sb = new StringBuffer();
        sb.append((by >> 7) & 0x1).append((by >> 6) & 0x1).append((by >> 5) & 0x1).append(
            (by >> 4) & 0x1).append((by >> 3) & 0x1).append((by >> 2) & 0x1).append(
                (by >> 1) & 0x1).append((by >> 0) & 0x1);
        return sb.toString();
    }

    public static String bytesToString(byte[] input, int offset, int length)
    {
        return byte2String(input, offset, length);
    }
    
    public static char[] getChars(byte[] bytes) {
        Charset cs = Charset.forName("UTF-8");
        ByteBuffer bb = ByteBuffer.allocate(bytes.length);
        bb.put(bytes);
        bb.flip();
        CharBuffer cb = cs.decode(bb);
        return cb.array();
    }


    public static String bytes2char2String(byte[] input, int offset, int length)
    {
    	char []buff_char = new char[length+1];
    	for(int i=0;i<length;i++)
    	{
    		buff_char[i] = (char)input[offset + i];
    	}
    	//log.debug(,"bytes2char2String ="+ String.valueOf(buff_char, 0, length));
        return String.valueOf(buff_char, 0, length);
    }
    
    
    

    /**
     * 把16进制字符串转换成字节数组
     * 
     * @param hexString
     * @return byte[]
     */
    public static byte[] hexStringToByte(String hex)
    {
        if (hex.isEmpty())
        {
            return null;
        }
        hex = hex.toUpperCase();
        int len = (hex.length() / 2);
        byte[] result = new byte[len];
        char[] achar = hex.toCharArray();
        for (int i = 0; i < len; i++ )
        {
            int pos = i * 2;
            result[i] = (byte)(toByte(achar[pos]) << 4 | toByte(achar[pos + 1]));
        }
        return result;
    }

    private static int toByte(char c)
    {
        byte b = (byte)"0123456789ABCDEF".indexOf(c);
        return b;
    }

    /**
     * 把字节数组转换成16进制字符串
     * @param input
     * @param offset
     * @param length
     * @return
     */
    public static String bytesToHexString(byte[] input, int offset, int length)
    {
        String ret = "";
        int end = (offset + length) > input.length ? input.length : offset + length;
        for (int i = offset; i < end; i++ )
        {
            String hex = Integer.toHexString(input[i] & 0xFF);
            if (hex.length() == 1)
            {
                hex = '0' + hex;
            }
            //System.out.print(i+":["+hex+ "]");
            ret += hex.toUpperCase();
        }
        return ret;
    }

    public static void main(String[] args)
    {
        byte[] bytes = new byte[] {1, 0x0E, 0x00, 0x0F, 0x08, 0x0D, 0x00, 0x04, 0x0F, 0x0F, 0x08,
            0x08};


    }

    public static Short bytesHexToShort(byte[] bytes)
    {
        String hexStr = bytesToHexString(bytes, 0, bytes.length);
        return Short.valueOf(hexStr, 16);
    }

    /**
     * getXor(对byte数组进行异或操作)
     * 
     * @param datas
     *            数组
     * @param offset
     *            偏移量
     * @return byte
     * @exception @since
     *                1.0.0
     */
    public static byte getXor(byte[] datas, int offset)
    {
        if (offset >= datas.length) return 0;

        byte temp = datas[offset];

        for (int i = offset + 1; i < datas.length; i++ )
        {
            temp ^= datas[i];
        }

        return temp;
    }

    public static String bytes2AsciiString(byte[] input, int offset, int length)
    {
        StringBuffer ret = new StringBuffer();
        int end = (offset + length) > input.length ? input.length : offset + length;
        for (int i = offset; i < end; i++ )
        {
            int number = input[i] & 0xFF;
            char c = (char)number;
            ret.append(c);
        }
        return ret.toString();
    }
    
    /**
	 * 浮点转换为字节
	 * 
	 * @param f
	 * @return
	 */
	public static byte[] float2byte(float f) {
		
		// 把float转换为byte[]
		int fbit = Float.floatToIntBits(f);
		
		byte[] b = new byte[4];  
	    for (int i = 0; i < 4; i++) {  
	        b[i] = (byte) (fbit >> (24 - i * 8));  
	    } 
	    
	    // 翻转数组
		int len = b.length;
		// 建立一个与源数组元素类型相同的数组
		byte[] dest = new byte[len];
		// 为了防止修改源数组，将源数组拷贝一份副本
		System.arraycopy(b, 0, dest, 0, len);
		byte temp;
		// 将顺位第i个与倒数第i个交换
		for (int i = 0; i < len / 2; ++i) {
			temp = dest[i];
			dest[i] = dest[len - i - 1];
			dest[len - i - 1] = temp;
		}
	    
	    return dest;
	    
	}
	
	/**
	 * 字节转换为浮点
	 * 
	 * @param b 字节（至少4个字节）
	 * @param index 开始位置
	 * @return
	 */
	public static float byte2float(byte[] b, int index) {  
	    int l;                                           
	    l = b[index + 0];                                
	    l &= 0xff;                                       
	    l |= ((long) b[index + 1] << 8);                 
	    l &= 0xffff;                                     
	    l |= ((long) b[index + 2] << 16);                
	    l &= 0xffffff;                                   
	    l |= ((long) b[index + 3] << 24);                
	    return Float.intBitsToFloat(l);                      
	    
	}
	
	 public static boolean FileExists(File file) {
		 
         if (file.exists()) {
             return true;
         } 
            
         return false;
            
       
 
     }
	 
	 

}
