package com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.server;


import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.request.ServerRequest;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.util.ByteUtils;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.domain.Product;

/**
 * <pre> 
 * 自己定义的协议 
 *  数据包格式 
 * +——----——+——-----——+——----——+ 
 * |协议开始标志|  长度             |   数据       | 
 * +——----——+——-----——+——----——+ 
 * 1.协议开始标志head_data，为int类型的数据，16进制表示为0X76 
 * 2.传输数据的长度contentLength，int类型 
 * 3.要传输的数据 
 * </pre> 
 */
public class IntelligentMattressProtocol
{
    /**
     * 消息的开头的信息标志
     */
    public static final int FIRST_HEAD_DATA = 0xa5;
    public static final int SECOND_HEAD_DATA = 0x5a;

    private String sn;
    
    /**
     * 消息的长度
     */
//    private int contentLength;
    
    /**
     * 指令码
     */
    private byte commandCode;

    /**
     * 消息的内容
     */
    private byte[] content;
    
    private Product product;
    
    private ServerRequest request;

    public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	

	/** 
     * 用于初始化，SmartCarProtocol 
     *  
     * @param contentLength 
     *            协议里面，消息数据的长度 
     * @param content 
     *            协议里面，消息的数据 
     */  
//    public IntelligentMattressProtocol(String sn,int contentLength,byte commandCode, byte[] content) {
//        this.sn = sn;
////        this.contentLength = contentLength;  
//        this.commandCode = commandCode;
//        this.content = content;
//        
//       
//    }
	public IntelligentMattressProtocol(ServerRequest request)
    {
		this.request = request;
        this.sn = request.getSn();
        this.commandCode = (byte)request.getDataHeader();
        this.content = request.getContent();
        byte[] serContent = toSerReqBytes();
                
        this.content = new byte[serContent.length - 15];
        System.arraycopy(serContent, 15, content, 0,serContent.length- 15);
        
    }
    
    public byte[] toSerReqBytes() {
        int length = content.length + 1;
        
        byte[] result = new byte[14 + length + 1];
        result[0] = (byte) FIRST_HEAD_DATA;
        result[1] = (byte) SECOND_HEAD_DATA;
        System.arraycopy(getSn().getBytes(), 0, result, 2, 10);
        
        int H = length / 256;
        int L = length % 256;
        result[12] = (byte)H;
        result[13] = (byte)L;
        
        // insert command code and param
        result[14] = (byte) commandCode;
        if (content.length > 0) {
            System.arraycopy(content, 0, result, 15, content.length);
        }
        
        // insert BBCcheck
        byte bbcCheck = ByteUtils.getXor(result, 2);
        result[15 + content.length] = bbcCheck;
        
        
        
        return result;
    }    

    public byte[] getContent()
    {
        return content;
    }

    public void setContent(byte[] content)
    {
        this.content = content;
    }

    public String getSn()
    {
        return sn;
    }

    public void setSn(String sn)
    {
        this.sn = sn;
    }

    public byte getCommandCode()
    {
        return commandCode;
    }

    public void setCommandCode(byte commandCode)
    {
        this.commandCode = commandCode;
    }

	public ServerRequest getRequest() {
		return request;
	}
    
    

}
