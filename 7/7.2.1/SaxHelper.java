public class SaxHelper extends DefaultHandler{
	private Person person;
	private ArrayList<Person> persons;
	private String tagName = null;
	
	//当读到文档开始标志时触发，通常在这里完成一些初始化操作
	@Override
	public voi startDocument() throws SAXException{
		this.persons = new ArrayList<Person>();
		Log.i("SAX", "读取到文档头，开始解析xml")；
	}
	
	//读到一个开始标签时调用，第二个参数为标签名，最后一个参数为属性数组
	@Override
	public void startElement(String uri, String localName, String qName), Attributes attributes) throws SAXException{
		if(localName.equals("person")){
			person = new Person();
			 person.setId(Integer.parseInt(attributes.getValue("id")));
			 Log.i("SAX", "开始处理paerosn元素~");
		}
		this.tagName = localName;
	}
	
	//读取到内容，第一个参数为字符串内容，后面一次为起始位置与长度
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException{
		//判断当前标签是否有效
		if(this.tagName != null){
			String data = new String(ch, start, length);
			//读取标签中的内容
			if(this.tagName.equals("name")){
				this.person.setName(data);
				Log.i("SAX", "处理name元素内容")
			}else if(this.tagName.equals("age")){
				this.person.setAge(Integer.parseInt(data));
				Log.i("SAX", "处理age元素内容")；
			}
		}
	}
	
	//处理元素结束时触发，这里将对象添加到结合中
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException{
		if(localName.equals("person")){
			this.persons.add(person);
			person = null;
			Log.i("SAX", "处理person元素结束~")；
		}
		this.tagName = null;
	}
	
	//读取到文档结束时触发
	@Override
	public void endDocument() throws SAXException{
		super.endDocument();
		Log.i("SAX", "读取到文档尾，XML解析结束")；
	}
	
	//获取persons集合
	public ArrayList<Person> getPersons(){
		return persons;
	}
}