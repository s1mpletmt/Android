public class MainActivity extends AppCompatActivity{
	private Textview txtMenu, txtshow;
	private ImageView imgPic;
	private WebView webView;
	private ScrollView scroll；
	private Bitmap bitmap；
	private String detail = "";
	private boolean flag = false;
	private final static String PIC_URL = "https://ww2.sinaimg.cn/large/7a8aed7bgw1evshgr5z3oj20hs0qo0vq.jpg";
	private final static String HTML_URL = "http://www.baidu.com"
	
	//刷新界面
	private Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg){
			switch(msg.what){
				case 0x001:
					hideAllwidget();
					imgPic.setVisibility(View.VISIBLE);
					imgPic.setImageBitmap(bitmap);
					Toase.makeText(MainActivity.this, "图片加载完毕", Toast.LENGTH_SHORT).show();
					break;
				case 0x002:
				    hideAllWidget();
                    scroll.setVisibility(View.VISIBLE);
                    txtshow.setText(detail);
                    Toast.makeText(MainActivity.this, "HTML代码加载完毕", Toast.LENGTH_SHORT).show();
                    break;	
				case 0x003:
				    hideAllWidget();
                    webView.setVisibility(View.VISIBLE);
					webView.loadDataWithBaseURL("", detail, "text/html", "UTF-8", "");
                    Toast.makeText(MainActivity.this, "网页加载完毕", Toast.LENGTH_SHORT).show();
                    break;	
				default:
					break;
			}
		};
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setViews();
	}
	
	private void setViews(){
		txtMenu = (TextView)findViewById(R.id.txtMenu);
		txtshow = (TextView)findViewById(R.id.txtshow);
		imgPic = (ImageView)findViewById(R.id.imgPic);
		webView = (WebView)findViewById(R.id.webView);
		scroll = (ScrollView)findViewById(R.id.scroll);
		registerForContextMenu(txtMenu);
	}
	
	//定义一个隐藏所有控件的方法：
	private void hideAllWidget(){
		imgPic.setVisibility(View.GONE);
		scroll.setVisibility(View.GONE);
		webView.setVisibility(View.GONE);
	}
	
	@Override
	//重写上下文菜单的创建方法
	public void onCreateContentMenu(ContentMenu menu, View v, ContentMenu.ContentMenuInfo menuInfo){
		MenuInflater inflator = new MenuInflater(this);
		inflator.inflate(R.menu.menus, menu);
		super.onCreateContentMenu(menu, v, menuInfo);
	}
	
	//上下文菜单被点击时触发该方法
	@Override
	public boolean onContextItemSelected(MenuItem item){
		switch(item.getItemId()){
			case R.id.one:
				new Thread(){
				public void run(){
					try{
						byte[] data = GetData.getImage(PIC_URL);
						bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
					}catch(Exception e){
						e.printStackTrace();
					}
					handler.sendEmptyMessage(0x001);
				};
			}.start();
				break;
			case R.id.two:
				new Thread(){
					public void run(){
						try{
							detail = GetData.getHtml(HTML.URL);
						}catch(Exception e){
							e.printStackTrace();
						}
						handler.sendEmptyMessage(0x002);
					};
				}.start();
				break;
			case R.id.three:
				if(detail.equals("")){
					Toast.makeText(MainActivity.this, "请先请求HTML"， Toast.LENGTH_SHORT).show();
				}else{
					handler.sendEmptyMessage(0x003);
				}
				break;
		}
	return true;
	}
}