package demo;

import com.jfinal.plugin.IPlugin;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

/**
 * ����mongodb�Ĳ��
 * @author Administrator
 *
 */
public class MongodbPlugin implements IPlugin{
	public static MongoDatabase mongoDatabase;//����mongodb�����ݿ�
	@Override
	public boolean start() {
		try {
			// ���ӵ� mongodb ����
			MongoClient mongoClient= new MongoClient( "120.79.42.237" , 27017 );
	         // ���ӵ����ݿ�
	        mongoDatabase = mongoClient.getDatabase("student"); 
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public boolean stop() {
		// TODO Auto-generated method stub
		return false;
	}

}
