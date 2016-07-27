package com.class8.eduAdmin.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializeUtil {
	
	/**
	 * 序列化
	 * @param object
	 * @return
	 */
	public static byte[] serialize(Object object){
		ObjectOutputStream oos = null;
		ByteArrayOutputStream baos = null;
		try {
			baos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(baos);
			oos.writeObject(object);
			byte[] bytes = baos.toByteArray();
			return bytes;
		} catch (Exception e) {
			
		} finally {
			if(oos != null){
				try {
					oos.close();
				} catch (IOException e) {
					
				} finally {
					if(baos != null){
						try {
							baos.close();
						} catch (IOException e) {
							
						}
					}
				}
			}
		}
		return null;
	}
	
	/**
	 * 反序列化
	 * @param bytes
	 * @return
	 */
	public static Object unserialize(byte[] bytes){
		ObjectInputStream ois = null;
		ByteArrayInputStream bais = null;
		try {
			bais = new ByteArrayInputStream(bytes);
			ois = new ObjectInputStream(bais);
			return ois.readObject();
		} catch (Exception e) {
			
		} finally {
			if(ois != null){
				try {
					ois.close();
				} catch (IOException e) {
					
				} finally {
					if(bais != null){
						try {
							bais.close();
						} catch (IOException e) {
							
						}
					}
				}
			}
			
		}
		return null;
	}
}
