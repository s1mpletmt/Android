public class MainActivity extends Activity implements OnclickListner{
	
	private Button btnGet;
	private WebView wView;
	public static final int SHOW_DATA = 0X123;
	private String detail = "";
	
	private Handler handler = new Handler(){
		public void handleMessage(Message msg){
			if(msg.what == SHOW_DATA){
				wView.loadDataWithBaseURL("", detail, “text/html”, "UTF-8", "");
			}
		};
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
		setView();
	}
	
	private void initView(){
		btnGet = (Button)findViewById(R.id.btnGet);
		wView = (WebView)findViewById(R.id.wView);
	}
	
	private void setView(){
		btnGet.setOnClickListener(this);
		wView.getSetting().setDomStorageEnabled(true);
	}
	
	@Override
	public void onClick(View v){
		if(v.getId() == R.id.btnGet){
			GetByHttpClient();
		}
	}
	
	private void GetByHttpClient(){
		new Thread(){
			public void run(){
				try{
					HttpClient httpClient = new DefaultHttpClient();
					Http httpGet = new HttpGet("http://www/w3cschool.cc/python/python-turorial.html");
					HttpResponse httpResponse = httpClient.execute(httpGet);
					if(httpResponse.getStatusLine().getStatusCode() == 200){
						HttpEntitiy entity = httpResponse.getEntity();
						detail = EntityUtils.toString(entity, "utf-8");
						handler.sendEmptyMessage(SHOW_DATA);
					}
				}catch(Exception e){
					e.printStackTrace();
				}
			};
		}.start();
	}
}