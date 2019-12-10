package demo;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;

public class MongodbModel {

	
	private MongoCollection<Document> collection;
	
	/**
	 * 构造方法，传集合的名称
	 */
	public MongodbModel(String colleName) {
		this.collection = MongodbPlugin.mongoDatabase.getCollection(colleName);
	}
	/**
	 * 保存数据
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
	 * 删除
	 */
	public void del() {
		
	}
	/**
	 * 查询
	 */
	public FindIterable<Document> find() {
		FindIterable<Document> findIterable = collection.find(); 
		return findIterable;
	}
	/**
	 * 修改
	 */
	public void update() {
		
	}
	/**
	 * 根据用户名获取用户信息
	 * 
	 */
	public FindIterable<Document> getUserByUsername(String username) {
		Document qu=new Document();
		qu.append("username", username);
		FindIterable<Document> rs=collection.find(qu);
		return rs;
	}


}
