private ArrayList<Person> readxmlForSAX() throws Exception{
	//获取文件资源建立输入流对象
	InputStream is = getAsserts().open("persons1.xml");
	//创建XML解析处理器
	SaxHelper ss = new SaxHelper();
	//得到SAX解析工厂
	SAXParserFactory factory = SAXParserFactory.newInstanse();
	//创建SAX解析器
	SAXParser parser = factory.newSAXParser();
	//将XML解析处理器分配给解析器，对文档进行解析，将事件发给处理器
	parser.parse(is, ss);
	is.close();
	return ss.getPersons();
}