package com.hotent.platform.model.system;


import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
public class WSysdefnodeComparator4 implements Comparator 
{
	 public int compare(Object arg0, Object arg1) {
		 int flag=0;
		 System.out.println("进入了WsysdefnodeComparator4.java");
		 if(arg0 instanceof WDefInformation){
			 
			 WDefInformation user0=(WDefInformation)arg0;
			 WDefInformation user1=(WDefInformation)arg1;
			 if(String.valueOf(user0.getOperateDependExeNum()).length()<String.valueOf(user1.getOperateDependExeNum()).length())
			 { flag=1;}
			 else if(String.valueOf(user0.getOperateDependExeNum()).length()>String.valueOf(user1.getOperateDependExeNum()).length())
			 { flag=-1;}
			 else if(String.valueOf(user0.getOperateDependExeNum()).length()==String.valueOf(user1.getOperateDependExeNum()).length())

			 { flag=String.valueOf(user0.getOperateDependExeNum()).compareTo(String.valueOf(user1.getOperateDependExeNum()));
			 if(flag<0)  flag=1;
			  else flag=-1;
			 }
			  System.out.println(user0.getOperateDependExeNum()+"---"+user1.getOperateDependExeNum());
			  System.out.println("flag为---------------------------"+"{"+flag+"}");
			  
			
			   } 
		 if(arg0 instanceof WNodeInformation){
			 
			 WNodeInformation user0=(WNodeInformation)arg0;
			 WNodeInformation user1=(WNodeInformation)arg1;
			 if(String.valueOf(user0.getOperateDependExeNum()).length()<String.valueOf(user1.getOperateDependExeNum()).length())
			 { flag=1;}
			 else if(String.valueOf(user0.getOperateDependExeNum()).length()>String.valueOf(user1.getOperateDependExeNum()).length())
			 { flag=-1;}
			 else if(String.valueOf(user0.getOperateDependExeNum()).length()==String.valueOf(user1.getOperateDependExeNum()).length())
        
			  {flag=String.valueOf(user0.getOperateDependExeNum()).compareTo(String.valueOf(user1.getOperateDependExeNum()));
			 if(flag<0)  flag=1;
			 else flag=-1;}
			  System.out.println(user0.getOperateDependExeNum()+"---"+user1.getOperateDependExeNum());
			  System.out.println("flag为---------------------------"+"{"+flag+"}");
			  
			
			   } 
		  if(arg0 instanceof OperateNodeInfo){
				 
       	   
       	   System.out.println("传过来了OperateNodeInfo对象");
       	   OperateNodeInfo user0=(OperateNodeInfo)arg0;
       	   OperateNodeInfo user1=(OperateNodeInfo)arg1;
       	 if(String.valueOf(user0.getDependExeNum()).length()<String.valueOf(user1.getDependExeNum()).length())
			 {flag=1;}
		 else if(String.valueOf(user0.getDependExeNum()).length()>String.valueOf(user1.getDependExeNum()).length())
		 { flag=-1;}
		 else if(String.valueOf(user0.getDependExeNum()).length()==String.valueOf(user1.getDependExeNum()).length())
			  {flag=String.valueOf(user0.getDependExeNum()).compareTo(String.valueOf(user1.getDependExeNum()));
       	        if(flag<0)  flag=1;
       	           else flag=-1;
       	       }
			  System.out.println(user0.getDependExeNum()+"---"+user1.getDependExeNum());
			  System.out.println("flag为---------------------------"+"{"+flag+"}");
			  
			
			   } 
		 return flag;
	 }
	 
	 
}