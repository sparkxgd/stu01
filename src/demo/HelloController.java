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
     * �򿪵�¼ҳ��
     */
    public void openlogin() {
    	render("login.html");
    }
    /**
     * ��¼
     */
    public void login() {
    	/**
    	 * 1����ȡҳ����û���������
    	 * 2����ȡmongodb���ݿ���û���������
    	 * 2���ж��û����Ƿ���ڣ�
    	 * 2.1��������ڣ��ж�����
    	 * 2.2����������ڣ���ʾ�û������ڣ�
    	 * 3���ж�����
    	 * 3.1�����������ȷ���ͽ�����ҳ��
    	 * 3.2��������������ʾ�������
    	 */
    	
    	String username=getPara("username");
    	String password=getPara("password");
    	
    	//��ȡmongodb���ݿ���û���������
    	MongodbModel user=new MongodbModel("userinfo");
    	MongoCursor<Document> cs=user.getUserByUsername(username).iterator();
    	 List<Document> doclist=new ArrayList<Document>();
         while(cs.hasNext()){  
      	   doclist.add(cs.next());
         } 
    	if(doclist.size()>0) {//2���ж��û����Ƿ���ڣ�˵���������
    		//2.1��������ڣ��ж�����
    		String pw=doclist.get(0).getString("passsword");
    		if(password.equals(pw)) {//3.1�����������ȷ���ͽ�����ҳ��
    			setAttr("result", 0);//0�����¼�ɹ������Խ�����ҳ��
    			renderJson();
    			
    		}else {//3.2��������������ʾ�������
    			setAttr("result", 1);//1�����������
    			renderJson();
    		}
    		
    	}else {//2.2����������ڣ���ʾ�û������ڣ�
    		setAttr("result", -1);//0�����û�������
			renderJson();
    		
    	}
    	
    	
    }
        
   public void openmain() {
    	render("main.html");
    }
    
   /**
         * ��ȡѧ����Ϣ
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
