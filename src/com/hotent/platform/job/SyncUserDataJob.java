/**  
 * @Title: SyncUserDataJob.java 
 * @Package com.jinfuzi.erp.job 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author zyl  
 * @date 2015年5月11日 上午11:30:34 
 * @version V1.0  
 */ 
package com.hotent.platform.job;

import org.quartz.JobExecutionContext;

import com.hotent.core.scheduler.BaseJob;


/** 
 * @ClassName: SyncUserDataJob 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zyl 
 * @date 2015年5月11日 上午11:30:34  
 */

public class SyncUserDataJob extends BaseJob 
{
    /**
     * @param context
     * @throws Exception 
     * @see com.hotent.core.scheduler.BaseJob#executeJob(org.quartz.JobExecutionContext) 
     */ 
    @Override
    public void executeJob(JobExecutionContext context)
        throws Exception
    {
//        EmployeeDao employeeDao = (EmployeeDao) AppUtil.getBean(EmployeeDao.class);
//        
//        List<Employee> list = new ArrayList<Employee>();
//        PageBean pb = new PageBean();
//        pb.setCurrentPage(1);
//        pb.setPagesize(10);
//        // 去掉进行分页的总记录数的查询
//        pb.setShowTotal(false);
//        try {
//            list = employeeDao.getMyAttend(
//                   ContextUtil.getCurrentUserId(), null, pb);
//       } catch (Exception e) {
//           // TODO: handle exception
//       }
//        
//        SysUserService sysUserService = (SysUserService) AppUtil.getBean(SysUserService.class);
        
        System.out.println("sdfsdjkl*********///////////////////");
        
    }
    
}


