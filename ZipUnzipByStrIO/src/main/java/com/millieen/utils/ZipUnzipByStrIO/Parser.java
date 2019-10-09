package com.millieen.utils.ZipUnzipByStrIO;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.InflaterInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Hello world!
 *
 */
public class Parser 
{

	public Map<String, byte[]> UnzipBase64Str(String inputBase64ZippedStr) throws Exception{
		Map<String, byte[]> results = new HashMap<String, byte[]>(); 
		byte[] decodedBase64Buff = Base64.getDecoder().decode(inputBase64ZippedStr);
//		if(decodedBase64Buff == null)
//			return null;
		
		//unzip
		ZipInputStream zin = null;

		try { // try语句捕获可能发生的异常
			zin = new ZipInputStream(new ByteArrayInputStream(decodedBase64Buff));
			// 实例化对象，指明要进行解压的文件
			ZipEntry entry = null; // 获取下一个ZipEntry
			while (((entry = zin.getNextEntry()) != null)
					&& !entry.isDirectory()) {				 
                byte[] filebuff = getData(zin);
				zin.closeEntry(); // 关闭当前entry			
				results.put(entry.getName(), filebuff);
				System.out.println(entry.getName() + "解压成功");	
			}
			
		} finally {
			if(zin != null)
				zin.close(); // 关闭流
		}
		
		return results;
	}
    private byte[] getData(InflaterInputStream zis) throws Exception {

        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        byte[] temp = new byte[1024];
        byte[] buf = null;
        int length = 0;

        while ((length = zis.read(temp, 0, 1024)) != -1) {
            bout.write(temp, 0, length);
        }

        buf = bout.toByteArray();
        bout.close();
        return buf;
    }
}
