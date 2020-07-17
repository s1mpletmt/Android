public int getConnect(String user, String key) throws Exception{
	//先发送get请求，获取cookie和__ViewState值
	HttpGet getLogin = new HttpGet(true_url);
	//第一步：主要的HTML：
	String loginhtml = "";
	HttpResponse loginResponse = new DefaultHttpClient().execute(getLogin);
	if(loginResponse.getStatusLine().getStatusCode() == 200){
		HttpEntity entity = loginResponse.getEntity();
		loginhtml = EntityUtils.toString(entity);
		//获取响应的cookie值
		cookie = loginResponse.getFirstHeader("Set-Cookie").getValue();
		System.out.println("cookie= " + cookie);
	}
	
	//第二步：模拟登录
	//发送Post请求，禁止重定向
	HttpPost httpPost = new HttpPost(true_url);
	httpPost.getParams().setParameter(ClientPNames.HANDLE_REDIRECTS, false);
	
	//设置Post提交的头信息的参数
	httpPost.setHeader("User-Agent", 
			"Mozilla/5.0 (Windows NT 6.3; WOW64; Trident/7.0; rv:11.0) like Gecko");
	httpPost.setHeader("Referer", true_url);
	httpPost.setHeader("Cookie", cookie);
	
	//设置请求数据
	List<NameValuePair> params = new ArrayList<>();
	
	params.add(new BasicNameValuePair("__VIEWSTATE", getViewState(loginhtml)));
	params.add(new BasicNameValuePair("Button1", ""));
	params.add(new BasicNameValuePair("hidPdrs", ""));
	params.add(new BasicNameValuePair("hidsc", ""));
	params.add(new BasicNameValuePair("lbLanguage", ""));
	params.add(new BasicNameValuePair("RadioButtonList1", "%D1%A7%C9%FA"));
	params.add(new BasicNameValuePair("txtUserName", user));
	params.add(new BasicNameValuePair("TextBox2", key));
	params.add(new BasicNameValuePair("txtSecretCode", ""));
	
	//设置编码方式，响应请求，获取响应状态码：
	httpPost.setEntity(new UrlEncodedFormEntity(params, "gb2312"));
	HttpResponse response = new DefaultHttpClient().execute(httpPost);
	int Status = response.getStatusLine().getStatusCode();
	if(Status == 200)return Status;
	System.out.println("Status= " + Status);
	
	//重定向状态码为302
	if(Status ==302 || Status ==301){
		//获取头部信息中Location的值
		location = response.getFirstHeader("Location").getValue();
		System.out.println(location);
		//第三步：获取管理信息的主页面
		//Get请求
		HttpGet httpGet = new HttpGet(ip_url + location);
		httpGet.setHeader("Referer", true_url);
		httpGet.setHeader("Cookie", cookie);
		
		//主页的html
		mainhtml = “";
		HttpResponse httpResponseget = new DefaultHttpClient().execute(httpGet);
		if(httpResponseget.getStatusLine().getStatusCode() == 200){
			HttpEntity entity = httpResponseget.getEntity();
			mainhtml= EntityUtils.toString(entity);
		}
	}
	return Status;
}