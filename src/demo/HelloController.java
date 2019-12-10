package demo;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.jfinal.core.Controller;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;
public class HelloController extends Controller {
    public void index() {
       renderText("Hello JFinal World.");
    }
    /**
     * 打开登录页面
     */
    public void openlogin() {
    	render("login.html");
    }
    /**
     * 登录
     */
    public void login() {
    	/**
    	 * 1、获取页面的用户名和密码
    	 * 2、获取mongodb数据库的用户名和密码
    	 * 2、判断用户名是否存在？
    	 * 2.1、如果存在，判断密码
    	 * 2.2、如果不存在，提示用户不存在，
    	 * 3、判断密码
    	 * 3.1、如果密码正确，就进入主页面
    	 * 3.2、如果密码错误，提示密码错误
    	 */
    	
    	String username=getPara("username");
    	String password=getPara("password");
    	
    	//获取mongodb数据库的用户名和密码
    	MongodbModel user=new MongodbModel("userinfo");
    	MongoCursor<Document> cs=user.getUserByUsername(username).iterator();
    	 List<Document> doclist=new ArrayList<Document>();
         while(cs.hasNext()){  
      	   doclist.add(cs.next());
         } 
    	if(doclist.size()>0) {//2、判断用户名是否存在？说明有这个人
    		//2.1、如果存在，判断密码
    		String pw=doclist.get(0).getString("passsword");
    		if(password.equals(pw)) {//3.1、如果密码正确，就进入主页面
    			setAttr("result", 0);//0代表登录成功，可以进入主页面
    			renderJson();
    			
    		}else {//3.2、如果密码错误，提示密码错误
    			setAttr("result", 1);//1代表密码错误
    			renderJson();
    		}
    		
    	}else {//2.2、如果不存在，提示用户不存在，
    		setAttr("result", -1);//0代表用户不存在
			renderJson();
    		
    	}
    	
    	
    }
        
   public void openmain() {
    	render("main.html");
    }
    
   /**
         * 获取学生信息
    */
   public void getstudents() {
	   MongodbModel m=new MongodbModel("studentinfo");
	   MongoCursor<Document> mongoCursor = m.find().iterator(); 
 	  List<Document> doclist=new ArrayList<Document>();
        while(mongoCursor.hasNext()){  
     	   doclist.add(mongoCursor.next());
        }  
 	setAttr("list", doclist);
 	renderJson();
   }
    
   
   
   
    public void opentest() {
    	render("test.html");
    }
    public void save() {
    	String name=getPara("name");
    	String sex=getPara("sex");
    	int age=getParaToInt("age");
    	int no= getParaToInt("no");
    	
    	MongodbModel m=new MongodbModel("test");
    	m.save(name, sex, age, no);
    			
    }
}
