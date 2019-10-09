package com.millieen.utils.ZipUnzipByStrIO;

import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Unit test for simple App.
 */
public class ParserTest 
{
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	@Test
	public void test() throws Exception{
		Parser parser = new Parser();
		String errorxml="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" + 
				"\n" + 
				"<root>\n" + 
				"  <ProcessData>\n" + 
				"    <result>0</result>\n" + 
				"    <message>104</message>\n" + 
				"    <returnfile></returnfile>\n" + 
				"  </ProcessData>\n" + 
				"</root>\n";
		String inputBase64ZippedStr="UEsDBBQACAAIAOBRSU8AAAAAAAAAAKgAAAAJAAAAZXJyb3IueG1ss7GvyM1RKEstKs7Mz7NVMtQzUFJIzUvOT8nMS7dVCg1x07VQsrfj4rIpys8vseNSULAJKMpPTi0udkksSQTxgSJFqcWlOSV2Bjb6UBZEOBeoKjE91c7QwMRGH8aB6SgpLcpLy8xJtQNpgnNA5uujWACUBlsMAFBLBwgQNhJddgAAAKgAAABQSwECFAAUAAgACADgUUlPEDYSXXYAAACoAAAACQAAAAAAAAAAAAAAAAAAAAAAZXJyb3IueG1sUEsFBgAAAAABAAEANwAAAK0AAAAAAA==";
		Map<String, byte[]> result = parser.UnzipBase64Str(inputBase64ZippedStr);
		assertTrue(result.containsKey("error.xml"));
		String decodedXMLStr = new String(result.get("error.xml"));
		assertTrue(decodedXMLStr.compareTo(errorxml) == 0);
	}
}
