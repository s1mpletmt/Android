public class StreamTool{
	public static byte[] read(InputStream inStream) throws Exception{
		ByteArrayOutputStream outStream = new ByteArrayOutStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		while((len = inStream.read(buffer)) != -1){
			outStream.write(buffer, 0, len);
		}	
		inStream.close();
		return outStream.toByteArray();
	}
}