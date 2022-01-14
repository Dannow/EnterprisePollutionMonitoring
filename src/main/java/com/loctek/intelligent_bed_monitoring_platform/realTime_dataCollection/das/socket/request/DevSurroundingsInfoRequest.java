package com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.request;

public class DevSurroundingsInfoRequest extends BaseRequest{
	float temp;//温度

	short dampness;
	short lum;
	short noise;
	
	
	
	public float getTemp() {
		return temp;
	}
	public void setTemp(float temp) {
		System.out.print("temp = ");
		System.out.println(temp);
		this.temp = temp;
	}
	public short getDampness() {
		return dampness;
	}
	public void setDampness(short dampness) {
		System.out.print("dampness = ");
		System.out.println(dampness);
		this.dampness = dampness;
	}
	public short getLum() {
		return lum;
	}
	public void setLum(short lum) {
		System.out.print("lum = ");
		System.out.println(lum);
		this.lum = lum;
	}
	public short getNuise() {
		return noise;
	}
	public void setNoise(short noise) {
		System.out.print("noise = ");
		System.out.println(noise);
		this.noise = noise;
	}
}
