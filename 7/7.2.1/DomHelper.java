public class DomHelper{
	public static ArrayList<Person> queryXML(Context context){
		ArrayList<Person> Persons = new ArrayList<>();
		try{
			//获得DOM解析器工厂示例：
			DocumentBuilderFactory dbFactory = DocumetBuilderFactory.newInstance();
			//从DOM工厂中获得DOM解析器
			DocumentBuilder dbBuilder = dbFactory.newDocumentBuilder();
			//把要解析的XML文件读入DOM解析器
			Document doc = dbBuilder.parse(context.getAssets().open("person2.xml"));
			System.out.println("处理该文档的DomImplement对象~" + doc.getImplementation());
			//得到文档中名称为person的元素的结点列表
			NodeList nList = doc.getElementByTagName("person");
			//遍历该集合，显示集合中的元素以及子元素的名字
			for(int i = 0; i < nList.getLength(); i++){
				//先从Person元素开始解析
				Element personElement = (Element)nList.item(i);
				Person p = new Person();
				p.setId(Integer.valueOf(personElement.getAttrbute("id")));
				
				//获取person下的name和age的Node集合
				NodeList childNoList = personElement.getChildNodes();
				for(int j = 0; i < childNoList.getLength(); j++){
					Node childNode = childNoList.item(j);
					//判断子Node类型是否为元素Node
					if(childNode.getNodeType() == Node.ELEMENT_NODE){
						Element childElemnt = (Element)childNode;
						if("name".equals(childElemnt.getNodeName()))
							p.setName(childElement.getFirstChild().getNodeValue());
						else if("age".equals(childElement.getNodeName()))
							p.setAge(Integer.valueOf(childElement.getFirstChild().getNodeValue()));
					}
				}
				Persons.add(p);
			}
		}catch(Exception e){e.printStackTrace();}
		return Persons;
	}
}