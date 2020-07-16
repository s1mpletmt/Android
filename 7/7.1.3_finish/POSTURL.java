HttpPut httpPut = new HttpPut(PUTACKCODE_URL);
httpPut.setHeader(“Content-Type”, "application/json; charset=UTF-8");
List<NameValuePair> params = new ArrayList<NameValuePair>();
params.add(new BasicNameValuePair("activation_code", actCode));
params.add(new BasicNameValuePair("session", new JSONObject().put("id", cookie).toString()));
entity.setContentType("application/json");
httpPut.setEntity(entity);
HttpResponse course_response = new DefaultHttpClient().execute(httpPut);