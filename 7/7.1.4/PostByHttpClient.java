private void PostByHttpClient(final String url){
	new Thread(){
		public void run(){
			try{
				HttpClient httpClient = new DefaultHttpClient();
				HttpPost httpPost = new HttpPost(url);
				List<nameValuePair> params = new ArrayList<>();
				params.add(new BasicNameValuePair("user", "猪大哥"));
				params.add(new BasicNameValuePair("pawd", "123"));
				UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, "UTF-8");
				httpPost.setEntity(entity);
				HttpResponse httpResponse = httpClient.execute(httpPost);
				if(httpResponse.getStatusLine().getStatusCode() == 200){
					HttpEntit entity2 = httpResponse.getEntity();
					detail = EntityUtils.toString(entity2, "utf-8");
					handler.sendEmptyMessage(SHOW_DATA);
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		};
	}.start();
}