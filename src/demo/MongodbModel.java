package demo;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;

public class MongodbModel {

	
	private MongoCollection<Document> collection;
	
	/**
	 * ���췽���������ϵ�����
	 */
	public MongodbModel(String colleName) {
		this.collection = MongodbPlugin.mongoDatabase.getCollection(colleName);
	}
	/**
	 * ��������
	 */
	public void save(String name,String sex,int age,int no) {
		Document doc=new Document();
		doc.append("name", name);
		doc.append("sex",sex);
		doc.append("age", age);
		doc.append("no", no);
		this.collection.insertOne(doc);
	}
	/**
	 * ɾ��
	 */
	public void del() {
		
	}
	/**
	 * ��ѯ
	 */
	public FindIterable<Document> find() {
		FindIterable<Document> findIterable = collection.find(); 
		return findIterable;
	}
	/**
	 * �޸�
	 */
	public void update() {
		
	}
	/**
	 * �����û�����ȡ�û���Ϣ
	 * 
	 */
	public FindIterable<Document> getUserByUsername(String username) {
		Document qu=new Document();
		qu.append("username", username);
		FindIterable<Document> rs=collection.find(qu);
		return rs;
	}


}
