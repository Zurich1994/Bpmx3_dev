package com.hotent.platform.model.system;


import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
public class WSysdefnodeComparator implements Comparator 
{
	 public int compare(Object arg0, Object arg1) {
		 int flag=0;
		 System.out.println("进入了WsysdefnodeComparator.java");
		 if(arg0 instanceof WDefInformation){
			 
			 System.out.println("传过来了WDefInformation对象");
			 WDefInformation user0=(WDefInformation)arg0;
			 WDefInformation user1=(WDefInformation)arg1;
			 if(String.valueOf(user0.getExeNum()).length()<String.valueOf(user1.getExeNum()).length())
			 { flag=1;}
			 else if(String.valueOf(user0.getExeNum()).length()>String.valueOf(user1.getExeNum()).length())
			 { flag=-1;}
			 else if(String.valueOf(user0.getExeNum()).length()==String.valueOf(user1.getExeNum()).length())

			 { flag=String.valueOf(user0.getExeNum()).compareTo(String.valueOf(user1.getExeNum()));
			 if(flag<0)  flag=1;
			 else flag=-1;
			 }
			  System.out.println(user0.getExeNum()+"---"+user1.getExeNum());
			  System.out.println("flag为---------------------------"+"{"+flag+"}");
			  
			
			   } 
		 if(arg0 instanceof WNodeInformation){
			
			
			 System.out.println("传过来了WNodeInformation对象");
			 WNodeInformation user0=(WNodeInformation)arg0;
			 WNodeInformation user1=(WNodeInformation)arg1;
			 if(String.valueOf(user0.getExeNum()).length()<String.valueOf(user1.getExeNum()).length())
			 { flag=1;}
			 else if(String.valueOf(user0.getExeNum()).length()>String.valueOf(user1.getExeNum()).length())
			 { flag=-1;}
			 else if(String.valueOf(user0.getExeNum()).length()==String.valueOf(user1.getExeNum()).length())

			 { flag=String.valueOf(user0.getExeNum()).compareTo(String.valueOf(user1.getExeNum()));
			 if(flag<0)  flag=1;
			 else flag=-1;
			  }
			  System.out.println(user0.getExeNum()+"---"+user1.getExeNum());
			  System.out.println("flag为---------------------------"+"{"+flag+"}");
			  
			
			   } 
       
		 return flag;
	 }
	 
	 
}
