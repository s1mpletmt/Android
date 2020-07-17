public static int PutActCode(String actCode, String licPlate, Context mContext){
	int resp = 0;
	String cookie = (String)SPUtils.get(mContext, "Session", "");
	HttpPut httpPut = new HttpPut(PUTACKCODE_URL);
	httpPut.setHeader("Cookie", cookie);
	try{
		List<NameValuePair> params = new ArrayList<>();
		params.add(new BasicNameValuePair("activation_code", actCode));
		params.add(new BasicNameValuePair("license_plate", licPlate));
		httpPut.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
		HttpResponse course_response = new DefaultHttpClient().execute(httpPut);
		if(course_response.getStatusLine().getStatusCode() == 200){
			HttpEntity entity2 = course_response.getEntity();
			JSONObject jObject = new JSONObject(EntityUtils.toString(entity2));
			resp = Integer.parseInt(jObject.getString("status_code"));
			return resp;
		}
	}catch(Exception e){
		e.printStackTrace();
	}
	return resp;
}