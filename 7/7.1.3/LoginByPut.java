public static String LoginByPut(Context mContext, String mobile, String password, int from, 
								String devid, String version_name, int remember_me){
	String resp = "";
	try{
		HttpURLConnection conn = (HttpURLConnection)new URL(LOGIN_URL).openConnection();
		conn.setRequestMethod("PUT");
		conn.setReadTimeout(5000);
		conn.setConnectTimeout(5000);
		conn.setDoOutput(true);
		conn.setDoInput(true);
		conn.setUseCaches(false);
		
		String data = "mobile= " + mobile + "&password= " + password + "&from= " + from + "&devid= " + devid
					+ "&version_name= " + version_name + "&remember_me= " + remember_me;
		
		//获取输出流
		OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
		writer.write(data);
		writer.flush();
		writer.close();
		
		//获取相应流对象
		InputStream in = conn.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		StringBuilder response = new StringBuilder();
		String line;
		while((line = reader.readline()) != null)
			response.append(line);
		SPUtils.put(mContext, "session", conn.getHeaderField("Set-Cookie"));
		//资源释放
		in.close();
		//返回字符串
		Log.e("HEHE", response.toString());
		return response.toString();
	}catch(Exception e){
		e.printStackTrace();
	}
	return "";
}