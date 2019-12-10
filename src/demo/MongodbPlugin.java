package demo;

import com.jfinal.plugin.IPlugin;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

/**
 * 链接mongodb的插件
 * @author Administrator
 *
 */
public class MongodbPlugin implements IPlugin{
	public static MongoDatabase mongoDatabase;//链接mongodb的数据库
	@Override
	public boolean start() {
		try {
			// 连接到 mongodb 服务
			MongoClient mongoClient= new MongoClient( "120.79.42.237" , 27017 );
	         // 连接到数据库
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
