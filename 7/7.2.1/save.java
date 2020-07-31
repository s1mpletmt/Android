public static void save(List<Person> persons, OutputStream out) throws Exception{
	XmlSerializer serializer = Xml.newSerializer();
	serializer.setOutput(out, "UTF-8");
	serializer.startDocument("UTF-8", true);
	serializer.startTag(null, "persons");
	for(Person p : persons){
		serializer.startTag(null, "person");
		serializer.attribute(null, "id", p.getId() + "");
		serializer.startTag(null, "name");
		serializer.text(p.getName());
		serializer.endTag(null, "name");
		serializer.startTag(null, "age");
		serializer.text(p.getAge() + "");
		serializer.endTag(null, "age");
		serializer.endTag(null, "person");
	}
	
	serializer.endTag(null, "persons");
	serializer.endDocument();
	out.flush();
	out.close();
}