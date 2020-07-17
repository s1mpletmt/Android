List<BasicNameValuePair> params = new LinkedList<>();
params.add(new BasicNameValuePair("user", "猪小弟"));
params.add(new BasicNameValuePair("pawd", "123"));
String param = URLEncodeUtils.format(params, "UTF-8");
HttpGet httpGet = new HttpGet("http://www.baidu.com"+ "?" + param);