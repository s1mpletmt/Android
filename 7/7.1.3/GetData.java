public class GetData{
	//获取网络图片数据的方法：
	public static byte[] getImage(String path) throws Exception{
		URL url = new URL(path);
		HttpURLConnection conn = (HttpURLConnection)url.openConnection();
		conn.setConnectionTimeout(5000);
		conn.setRequestMethod("GET");
		if(conn.getReponseCode() != 200){
				throw new RuntimeException("请求url失败");
		}
		InputStream inStream = conn.getInputStream();
		byte[] bt = StreamTool.read(inStream);
		inStream.close();
		return bt;
	}

	public static String getHtml(String path) throws Exception{
		URL url = new URL(path);
		HttpURLConnection conn = (HttpURLConnection)url.openConnection();
		conn.setConnectionTimeout(5000);
		conn.setRequestMethod("GET");
		if(conn.getReponseCode() == 200){
			InputStream in = conn.getInputStream();
			byte[] data = StreamTool.read(in);
			String html = new String(data, "UTF-8");
			return html;
		}
		return null;
	}
}