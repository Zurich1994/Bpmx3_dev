insert into sys_user (USERID, FULLNAME, ACCOUNT, PASSWORD, ISEXPIRED, ISLOCK, CREATETIME, STATUS, EMAIL, MOBILE, PHONE, SEX, PICTURE, FROMTYPE)
values (1, '超级管理员', 'admin', 'a4ayc/80/OGda4BO/1o/V0etpOqiLx1JwB5S3beHW0s=', 0, 0, sysdate, 1, 'yg_zhangyg@126.com', '13580000000', '020-61151619', '1', '', 0);

insert into SYS_SUBSYSTEM (SYSTEMID, SYSNAME, ALIAS, LOGO, DEFAULTURL, MEMO, CREATETIME, CREATOR, ALLOWDEL, NEEDORG, ISACTIVE, ISLOCAL, HOMEPAGE)
values (1, 'BPM业务流程系统', 'bpm', '/styles/default/images/logo/bpmx3_logo.jpg', 'http:/www.jee-soft.cn', '',sysdate, 'admin', 0, null, 1, 1, '/platform/console/home.ht');

insert into sys_role (ROLEID, SYSTEMID, ALIAS, ROLENAME, MEMO, ALLOWDEL, ALLOWEDIT, ENABLED) values (1, 1, 'bpm_manager', '管理员', '管理员', 1, 1, 1);

insert into SYS_DEMENSION (DEMID, DEMNAME, DEMDESC) values (1, '行政维度', '行政维度');
insert into SYS_GL_TYPE (TYPEID, TYPENAME, NODEPATH,DEPTH,PARENTID,CATKEY,NODEKEY,SN,USERID,DEPID,TYPE,ISLEAF,NODECODE,NODECODETYPE) values (2, '默认分类', '3.1.',1,3,'FLOW_TYPE','DEFAULT',0,0,0,1,0,'',0);
insert into SYS_ORG_TYPE (ID, DEMID, NAME, LEVELS, MEMO, ICON) values (1, 1, '集团', 1, '', '/styles/default/images/resicon/archSat.png');
insert into SYS_ORG_TYPE (ID, DEMID, NAME, LEVELS, MEMO, ICON) values (2, 1, '公司/单位', 2, '', '/styles/default/images/resicon/ico_116.gif');
insert into SYS_ORG_TYPE (ID, DEMID, NAME, LEVELS, MEMO, ICON) values (3, 1, '部门', 3, '', '/styles/default/images/resicon/ico_32.gif');
insert into SYS_ORG_TYPE (ID, DEMID, NAME, LEVELS, MEMO, ICON) values (4, 1, '小组', 4, '', '/styles/default/images/resicon/nav-customer.png');
insert into SYS_ORG_TYPE (ID, DEMID, NAME, LEVELS, MEMO, ICON) values (5, 1, '其他组织', 5, '', '/styles/default/images/resicon/user.gif');

insert into SYS_PAUR (PAURID, PAURNAME, ALIASNAME, PAURVALUE, USERID) values (1, '皮肤','skin', 'default', 0);

insert into SYS_ORG (ORGID, DEMID, ORGNAME, ORGDESC, ORGSUPID, PATH, DEPTH, ORGTYPE, CREATORID, CREATETIME, UPDATEID, UPDATETIME, SN, FROMTYPE,ORGPATHNAME,CODE)
values (100, 1, '宏天集团', '广州宏天集团', 1, '1.100.', 0, 1, null, sysdate, null, sysdate, 100, 0,'/宏天集团','A001');

insert into sys_type_key (TYPEID, TYPEKEY, TYPENAME, FLAG, SN, TYPE) values (1, 'REPORT_TYPE', '报表分类', 1, 0, 1);
insert into sys_type_key (TYPEID, TYPEKEY, TYPENAME, FLAG, SN, TYPE) values (2, 'DIC', '数据字典', 1, 6, 1);
insert into sys_type_key (TYPEID, TYPEKEY, TYPENAME, FLAG, SN, TYPE) values (3, 'FLOW_TYPE', '流程分类', 1, 4, 1);
insert into sys_type_key (TYPEID, TYPEKEY, TYPENAME, FLAG, SN, TYPE) values (4, 'FORM_TYPE', '表单分类', 1, 5, 1);
insert into sys_type_key (TYPEID, TYPEKEY, TYPENAME, FLAG, SN, TYPE) values (5, 'ATTACH_TYPE', '附件分类', 1, 2, 1);
insert into sys_type_key (TYPEID, TYPEKEY, TYPENAME, FLAG, SN, TYPE) values (6, 'FILE_TYPE', '文件文类', 1, 3, 1);


-- 为系统用户增加管理员角色
insert into sys_user_role (userroleid,roleId,userId) values(1,1,1);

-- 为角色增加资源配置
	

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (1, '个人办公', 'officeDesk', 6, '/styles/default/images/resicon/destop.gif',0, '', 1, 1,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (2, '流程中心', 'processCenter', 1, '/styles/default/images/resicon/3.png',1, '', 1, 1,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (3, '我发起的流程', 'myStartPro', 1, '/styles/default/images/resicon/o_2.png',2, '', 1, 1,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (4, '新建流程', 'newProcess', 1, '/styles/default/images/resicon/o_1.png',3, '/platform/bpm/bpmDefinition/forMe.ht', 1, 1,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (5, '我的请求', 'myRequest', 2, '/styles/default/images/resicon/o_3.png',3, '/platform/bpm/processRun/myRequest.ht', 1, 1,0, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (6, '我的办结', 'myCompleted', 3, '/styles/default/images/resicon/o_4.png',3, '/platform/bpm/processRun/myCompleted.ht', 1, 1,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (7, '我的草稿', 'myDrafts', 4, '/styles/default/images/resicon/o_5.png',3, '/platform/bpm/processRun/myForm.ht', 1, 1,0, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (8, '删除草稿', 'delDraft', 1, '/styles/default/images/icon/tree_file.gif',7, '/platform/bpm/processRun/delDraft.ht', 0, 0,0, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (9, '我承接的流程', 'myUndertakePro', 2, '/styles/default/images/resicon/o_6.png',2, '', 1, 1,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (10, '待办事宜', 'agentMatters', 1, '/styles/default/images/resicon/o_11.png',9, '/platform/bpm/task/pendingMatters.ht', 0, 1,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (11, '已办事宜', 'alreadyMatters', 2, '/styles/default/images/resicon/o_9.png',9, '/platform/bpm/processRun/alreadyMatters.ht', 0, 1,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (12, '办结事宜', 'completedMatters', 3, '/styles/default/images/resicon/o_8.png',9, '/platform/bpm/processRun/completedMatters.ht', 0, 1,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (13, '转办代理事宜', 'accordingMatters', 4, '/styles/default/images/resicon/o_10.png',9, '/platform/bpm/bpmTaskExe/accordingMatters.ht', 0, 1,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (14, '加签流转事宜', 'transTo', 5, '/styles/default/images/resicon/p_5.png',9, '/platform/bpm/bpmProTransTo/matters.ht', 0, 1,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (15, '抄送转发事宜', 'bpmProCopyto', 6, '/styles/default/images/resicon/o_7.png',9, '/platform/bpm/bpmProCopyto/myList.ht', 0, 1,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (16, '我的流程日志', 'myBpmRunLogList', 4, '/styles/default/images/resicon/diary.png',2, '/platform/bpm/bpmRunLog/mylist.ht', 0, 1,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (17, '设置中心', 'settingcenter', 2, '/styles/default/images/resicon/i3.png',1, '', 1, 1,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (18, '常用语设置', 'taskApproval', 1, '/styles/default/images/resicon/s_p_2.png',17, '/platform/bpm/taskApprovalItems/list.ht?isAdmin=0', 0, 1,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (20, '流程代理授权', 'SYS_USER_AGENT', 3, '/styles/default/images/resicon/3.png',17, '/platform/bpm/agentSetting/list.ht', 1, 1,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (21, '查询代理', 'getAgent', 1, '/styles/default/images/icon/tree_file.gif',20, '/platform/bpm/agentSetting/get.ht', 0, 0,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (22, '修改代理', 'EditAgent', 1, '/styles/default/images/icon/tree_file.gif',20, '', 0, 0,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (23, '删除代理', 'DelAgent', 1, '/styles/default/images/icon/tree_file.gif',20, '/platform/system/sysUserAgent/del.ht', 0, 0,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (24, '添加代理', 'AddAgent', 1, '/styles/default/images/icon/tree_file.gif',20, '/platform/bpm/agentSetting/edit.ht', 0, 0,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (25, '内部消息', 'InnerMessage', 3, '/styles/default/images/resicon/email.png',1, '/platform/system/messageSend/form.ht', 1, 1,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (26, '收到的消息', 'readmessage', 1, '/styles/default/images/resicon/vote.gif',25, '/platform/system/messageReceiver/list.ht', 0, 1,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (27, '已发送消息', 'sendmessage', 2, '/styles/default/images/resicon/o_16.png',25, '/platform/system/messageSend/list.ht', 0, 1,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (28, '发送消息', 'sendMsg', 3, '/styles/default/images/resicon/ico_93.gif',25, '/platform/system/messageSend/edit.ht', 0, 1,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (29, '邮件管理', 'mail', 4, '/styles/default/images/resicon/site.gif',1, '', 1, 1,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (30, '外部邮件', 'outMailAll', 1, '/styles/default/images/resicon/o_14.png',29, '/platform/mail/outMail/warn.ht', 1, 1,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (31, '查看邮件', 'lookMail', 1, '/styles/default/images/resicon/ico_113.gif',30, '/platform/mail/outMail/get.ht', 0, 0,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (32, '删除邮件', 'delMail', 1, '/styles/default/images/resicon/del.png',30, '/platform/mail/outMail/del.ht', 0, 0,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (33, '新建邮件', 'mailAdd', 2, '/styles/default/images/resicon/0_15.png',29, '/platform/mail/outMail/edit.ht', 1, 1,0, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (34, '保存邮件', 'saveOutmail', 1, '/styles/default/images/resicon/tree_file.gif',33, '/platform/mail/outMail/send.ht', 0, 0,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (35, '发送邮件', 'sendMail', 1, '/styles/default/images/resicon/ico_117.gif',33, '/platform/mail/outMail/send.ht', 0, 0,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (36, '邮箱配置管理', 'mailManage', 3, '/styles/default/images/resicon/o_13.png',29, '/platform/mail/outMailUserSeting/list.ht', 1, 1,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (37, '设置默认邮箱', 'setDefault', 1, '/styles/default/images/resicon/3.png',36, '/platform/mail/outMailUserSeting/del.ht', 0, 0,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (38, '编辑邮箱配置', 'editOutmail', 2, '/styles/default/images/resicon/2.png',36, '/platform/mail/outMailUserSeting/edit.ht', 0, 0,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (39, '删除邮箱', 'deloutMail', 2, '/styles/default/images/resicon/del.png',36, '/platform/mail/outMailUserSeting/del.ht', 0, 0,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (40, '添加邮箱', 'addMail', 3, '/styles/default/images/resicon/add.png',36, '/platform/mail/outMailUserSeting/edit.ht', 0, 0,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (41, '我的日程', 'mytaskFlex', 5, '/styles/default/images/resicon/4.png',1, '/platform/calendar/calendar.ht', 0, 1,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (42, '下属管理', 'UserUnder', 6, '/styles/default/images/resicon/o_19.png',1, '/platform/system/userUnder/list.ht', 1, 1,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (43, '添加下属', 'addUnder', 1, '/styles/default/images/resicon/add.png',42, '/platform/system/userUnder/addUnderUser.ht', 0, 0,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (44, '查看个人资料', 'GetuserInfo', 7, '/styles/default/images/resicon/i3_14.png',1, '/platform/system/sysUser/get.ht', 1, 0,0, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (45, '组织管理', 'userOrg', 7, '/styles/default/images/resicon/user.gif',0, '', 1, 1,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (46, '用户管理', 'user_mgr', 1, '/styles/default/images/resicon/customer.png',45, '/platform/system/sysUser/list.ht', 1, 1,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (47, '重置密码', 'resetPwd', 1, '/styles/default/images/resicon/manage.png',46, '/platform/system/sysUser/resetPwdView.ht', 0, 0,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (48, 'AD同步', 'syncToLdap', 1, '/styles/default/images/icon/tree_file.gif',46, '', 0, 0,0, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (49, '切换用户', 'switch', 2, '/styles/default/images/icon/tree_file.gif',46, '', 0, 0,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (50, '查询用户', 'searchUser', 3, '/styles/default/images/resicon/list.png',46, '', 0, 0,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (51, '添加用户', 'addUser', 4, '/styles/default/images/resicon/add.png',46, '/platform/system/sysUser/edit.ht', 0, 0,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (52, '查看用户信息', 'userInfo', 4, '/styles/default/images/resicon/administrator.png',46, '/platform/system/sysUser/get.ht', 0, 0,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (53, '更改用户信息', 'updateUserInfo', 4, '/styles/default/images/resicon/2.png',46, '/platform/system/sysUser/edit.ht', 0, 0,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (54, '删除用户', 'delUser', 5, '/styles/default/images/resicon/del.png',46, '/platform/system/sysUser/del.ht', 0, 0,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (55, '参数属性', 'setParams', 6, '/styles/default/images/resicon/tree_file.gif',46, '/platform/system/sysUserParam/editByUserId.ht', 0, 0,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (56, '组织管理', 'org_manage', 2, '/styles/default/images/resicon/book_user.png',45, '/platform/system/sysOrg/list.ht', 1, 1,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (57, '修改组织', 'editOrg', 2, '/styles/default/images/resicon/2.png',56, '/platform/system/sysOrg/edit.ht', 0, 0,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (58, '添加组织', 'gradeOrgEdit', 4, '/styles/default/images/icon/tree_file.gif',56, '/platform/system/sysOrg/edit.ht', 0, 0,0, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (59, '添加组织', 'addOrg', 4, '/styles/default/images/resicon/add.png',56, '/platform/system/sysOrg/edit.ht', 0, 0,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (60, '删除组织', 'delOrg', 5, '/styles/default/images/resicon/del.png',56, '/platform/system/sysOrg/del.ht', 0, 0,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (61, '删除组织', 'gradeOrgDel', 6, '/styles/default/images/icon/tree_file.gif',56, '/platform/system/sysOrg/del.ht', 0, 0,0, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (62, '分级组织管理', 'gradeManager', 8, '/styles/default/images/resicon/s_o_1.png',56, '/platform/system/grade/manage.ht', 0, 1,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (63, '角色管理', 'sysRole', 3, '/styles/default/images/resicon/nav-customer.png',45, '/platform/system/sysRole/list.ht', 1, 1,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (64, '查询角色', 'searchRole', 1, '/styles/default/images/resicon/tree_file.gif',63, '', 0, 0,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (65, '删除角色', 'delRole', 1, '/styles/default/images/resicon/del.png',63, '/platformjavascript:selectIcon();/system/sysRole/del.ht', 0, 0,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (66, '启动角色', 'startRole', 1, '/styles/default/images/icon/tree_file.gif',63, '/platform/system/sysRole/runEnable.ht', 0, 0,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (67, '添加角色', 'addRole', 1, '/styles/default/images/resicon/add.png',63, '/platform/system/sysRole/edit.ht', 0, 0,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (68, '查看角色信息', 'roleDetail', 1, '/styles/default/images/resicon/administrator.png',63, '/platform/system/sysRole/get.ht', 0, 0,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (69, '人员分配', 'userRole', 1, '/styles/default/images/resicon/user.gif',63, '/platform/system/userRole/edit.ht', 0, 0,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (70, '资源分配', 'sourceRole', 1, '/styles/default/images/resicon/planmanage.png',63, '/platform/system/roleResources/edit.ht', 0, 0,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (71, '复制角色', 'copyRole', 1, '/styles/default/images/resicon/columnTemplate.gif',63, '/platform/system/sysRole/copy.ht', 0, 0,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (72, '修改角色', 'updRole', 1, '/styles/default/images/resicon/2.png',63, '/platform/system/sysRole/edit.ht', 0, 0,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (73, '批量资源授权', 'batResAuth', 1, '/styles/default/images/icon/tree_file.gif',63, '', 0, 0,0, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (74, '禁用角色', 'stopRole', 7, '/styles/default/images/icon/tree_file.gif',63, '/platform/system/sysRole/runEnable.ht', 0, 0,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (75, '职务管理', 'position2', 4, '/styles/default/images/resicon/u_1.png',45, '/platform/system/job/list.ht', 1, 1,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (76, '删除职务', 'delJob', 1, '/styles/default/images/resicon/del.png',75, '/platform/system/job/del.ht', 0, 0,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (77, '编辑职务', 'editJob', 6, '/styles/default/images/resicon/2.png',75, '/platform/system/job/edit.ht', 0, 0,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (78, '组织人员属性', 'sys_param', 6, '/styles/default/images/resicon/9.png',45, '/platform/system/sysParam/list.ht', 0, 1,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (79, '人员维度管理', 'sys_demension', 7, '/styles/default/images/resicon/tree_file.gif',45, '/platform/system/demension/list.ht', 0, 1,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (80, '流程管理', 'bpm', 8, '/styles/default/images/resicon/manage.png',0, '', 1, 1,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (81, '表单管理', 'formMgr', 1, '/styles/default/images/resicon/f_8.png',80, '', 1, 1,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (82, '创建视图', 'creatview', 0, '/styles/default/images/resicon/i3_10.png',81, '/platform/form/bpmFormTable/createView.ht', 0, 1,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (83, '自定义表', 'custTable', 1, '/styles/default/images/resicon/f_5.png',81, '/platform/form/bpmFormTable/list.ht', 1, 1,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (84, '删除表', 'delTable', 1, '/styles/default/images/icon/tree_file.gif',83, '', 0, 0,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (85, '添加表', 'addForm', 1, '/styles/default/images/icon/tree_file.gif',83, '', 0, 0,0, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (86, '自定义表单', 'custForm', 2, '/styles/default/images/resicon/f_4.png',81, '/platform/form/bpmFormDef/manage.ht', 1, 1,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (87, '删除表单', 'delForm', 1, '/styles/default/images/icon/tree_file.gif',86, '', 0, 0,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (88, '自定义查询', 'form_query', 3, '/styles/default/images/resicon/f_1.png',81, '/platform/bpm/bpmFormQuery/list.ht', 0, 1,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (89, '自定义对话框', 'bpmDialog', 4, '/styles/default/images/resicon/f_2.png',81, '/platform/form/bpmFormDialog/list.ht', 1, 1,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (90, '删除对话框', 'delFormDialog', 1, '/styles/default/images/icon/tree_file.gif',89, '', 0, 0,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (91, '表单规则验证', 'form_rule', 5, '/styles/default/images/resicon/f_7.png',81, '/platform/form/bpmFormRule/list.ht', 0, 1,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (92, '自定义sql查询', 'sqlquery', 8, '/styles/default/images/resicon/o_11.png',81, '/platform/system/sysQuerySql/list.ht', 0, 1,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (93, '自定义表单模板', 'custFormTemplate', 9, '/styles/default/images/resicon/f_3.png',81, '/platform/form/bpmFormTemplate/list.ht', 1, 1,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (94, '删除表单模板', 'delTemplate', 1, '/styles/default/images/resicon/del.png',93, '/platform/form/bpmFormTemplate/del.ht', 0, 0,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (95, '复制表单模板', 'copyTemplate', 1, '/styles/default/images/resicon/icon030a12.gif',93, '/platform/form/bpmFormTemplate/copy.ht', 0, 0,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (96, '添加模版', 'addTemplate', 1, '/styles/default/images/icon/tree_file.gif',93, '/platform/form/bpmFormTemplate/edit.ht', 0, 0,0, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (97, '初始化模版', 'initTemplate', 1, '/styles/default/images/icon/tree_file.gif',93, '', 0, 0,0, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (98, '编辑模版', 'editTemplate', 1, '/styles/default/images/icon/tree_file.gif',93, '', 0, 0,0, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (99, '流程管理', 'bpmFlow', 2, '/styles/default/images/resicon/i2.png',80, '', 1, 1,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (100, '分管授权', 'defAuthorize', 0, '/styles/default/images/resicon/s_s_8.png',99, '/platform/bpm/bpmDefAuthorize/list.ht', 0, 1,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (101, '常用语设置', 'admintaskApproval', 1, '/styles/default/images/resicon/f_5.png',99, '/platform/bpm/taskApprovalItems/list.ht?isAdmin=1', 0, 1,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (102, '流程定义管理', 'bpmprocdefind', 2, '/styles/default/images/resicon/p_6.png',99, '/platform/bpm/bpmDefinition/manage.ht', 1, 1,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (103, '流程授权', 'grantProcess', 1, '/styles/default/images/icon/tree_file.gif',102, '', 0, 0,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (104, '流程发布', 'publishProcess', 1, '/styles/default/images/icon/tree_file.gif',102, '', 0, 0,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (105, '启动流程', 'startProcess', 1, '/styles/default/images/resicon/tree_file.gif',102, '/platform/bpm/bpmDefinition/list.ht', 0, 0,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (106, '设计流程', 'flex', 1, '/styles/default/images/resicon/tree_file.gif',102, '/platform/bpm/bpmDefinition/flexGet.ht', 0, 0,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (107, '删除流程', 'delProcess', 1, '/styles/default/images/resicon/tree_file.gif',102, '/platform/bpm/bpmDefinition/del.ht', 0, 0,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (108, '流程设置', 'setBpm', 2, '/styles/default/images/resicon/tree_file.gif',102, '/platform/bpm/bpmDefinition/detail.ht', 0, 0,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (109, '流程任务管理', 'task', 3, '/styles/default/images/resicon/p_1.png',99, '/platform/bpm/task/list.ht', 1, 1,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (110, '删除任务', 'delTask', 1, '/styles/default/images/resicon/tree_file.gif',109, '/platform/bpm/task/delete.ht', 0, 0,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (111, '流程实例管理', 'processrunList', 4, '/styles/default/images/resicon/p_2.png',99, '/platform/bpm/processRun/list.ht', 1, 1,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (112, '删除实例', 'delRun', 1, '/styles/default/images/icon/tree_file.gif',111, '', 0, 0,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (113, '代理配置管理', 'agentSettingMgr', 5, '/styles/default/images/resicon/p_8.png',99, '/platform/bpm/agentSetting/manageList.ht', 1, 1,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (114, '查看代理', 'agentGet', 1, '/styles/default/images/resicon/administrator.png',113, '/platform/bpm/agentSetting/get.ht', 0, 0,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (115, '取消代理', '', 1, '/styles/default/images/resicon/ico_87.gif',113, '/platform/system/sysUserAgent/del.ht', 0, 0,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (116, '添加代理', 'agentAdd', 2, '/styles/default/images/resicon/add.png',113, '/platform/bpm/agentSetting/editAll.ht', 0, 0,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (117, '更改代理', 'changeagent', 3, '/styles/default/images/resicon/mail_draft.png',113, '/platform/system/sysUserAgent/editAll.ht', 0, 0,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (118, '流程历史管理', 'processrunhistroy', 6, '/styles/default/images/resicon/p_3.png',99, '/platform/bpm/processRun/history.ht', 1, 1,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (119, '删除历史', 'delHistory', 1, '/styles/default/images/icon/tree_file.gif',118, '', 0, 0,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (120, '流程操作日志', 'bpmRunLog', 7, '/styles/default/images/resicon/p_5.png',99, '/platform/bpm/bpmRunLog/list.ht', 0, 1,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (121, '转办代理实例', 'agentDelePro', 8, '/styles/default/images/resicon/p_9.png',99, '/platform/bpm/bpmTaskExe/list.ht', 0, 1,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (122, '系统管理', 'sys_mag', 9, '/styles/default/images/resicon/setting.png',0, '', 1, 1,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (123, '日历管理', 'duty', 1, '/styles/default/images/resicon/diary.png',122, '', 1, 1,0, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (124, '法定假期设置', 'legalHoliday', 1, '/styles/default/images/resicon/s_l_4.png',123, '/platform/worktime/vacation/list.ht', 0, 1,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (125, '班次设置管理', 'workTimeSetting', 2, '/styles/default/images/resicon/s_l_5.png',123, '/platform/worktime/workTimeSetting/list.ht', 0, 1,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (126, '工作日历设置', 'workCalendarSet', 3, '/styles/default/images/resicon/s_l_2.png',123, '/platform/worktime/sysCalendar/list.ht', 0, 1,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (127, '工作日历分配', 'workCalAssign', 4, '/styles/default/images/resicon/s_l_3.png',123, '/platform/worktime/calendarAssign/list.ht', 0, 1,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (128, '加班请假管理', 'overTime', 5, '/styles/default/images/resicon/s_l_6.png',123, '/platform/worktime/overTime/list.ht', 0, 1,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (137, '报表管理', 'reportManagement', 3, '/styles/default/images/resicon/s_f_4.png',122, '', 1, 1,0, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (138, '报表设置', 'reportSet', 1, '/styles/default/images/resicon/s_f_1.png',137, '/platform/system/reportTemplate/manage.ht', 0, 1,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (139, 'Jreport开源报表', 'Jreport', 1, '/styles/default/images/resicon/s_s_17.png',137, '/platform/system/sysReport/manage.ht', 0, 1,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (140, '报表数据源', 'reportDs', 2, '/styles/default/images/resicon/s_f_2.png',137, '/ReportServer?op=fr_platform', 0, 1,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (141, '常用流程分类统计', 'processTypeCount', 5, '/styles/default/images/resicon/s_f_7.png',137, '/ReportServer?reportlet=ProcessTypeCount.cpt', 0, 1,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (142, '常用流程定义统计', 'processDefCount', 6, '/styles/default/images/resicon/s_f_3.png',137, '/ReportServer?reportlet=ProcessDefCount.cpt', 0, 1,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (143, '流程定义任务执行效率', 'proDefTaskEff', 7, '/styles/default/images/resicon/s_f_5.png',137, '/ReportServer?reportlet=ProDefTaskEff.cpt', 0, 1,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (144, '流程辅助', 'flowAssist', 4, '/styles/default/images/resicon/s_p_3.png',122, '', 1, 1,0, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (145, '脚本管理', 'script', 1, '/styles/default/images/resicon/s_p_4.png',144, '/platform/system/script/list.ht', 1, 1,0, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (146, '编辑脚本', 'script_add', 1, '/styles/default/images/resicon/tag.gif',145, '/platform/system/script/del.ht', 0, 0,0, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (147, '删除脚本', 'script_del', 1, '/styles/default/images/resicon/del.png',145, '/platform/system/script/del.ht', 0, 0,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (148, '脚本列表', 'script_list', 1, '/styles/default/images/resicon/salesshdetail.png',145, '/platform/system/script/list.ht', 0, 0,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (149, '流水号管理', 'serialNo', 2, '/styles/default/images/resicon/s_p_1.png',144, '/platform/system/identity/list.ht', 1, 1,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (150, '删除流水号', 'delSerialNo', 1, '/styles/default/images/icon/tree_file.gif',149, '', 0, 0,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (151, '短信邮件模板', 'Sys_Template', 3, '/styles/default/images/resicon/s_p_5.png',144, '/platform/system/sysTemplate/list.ht', 0, 1,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (152, '条件脚本管理', 'conditionScriptMgr', 4, '/styles/default/images/resicon/s_p_2.png',144, '/platform/system/conditionScript/list.ht', 0, 1,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (153, '人员脚本管理', 'personScriptMgr', 5, '/styles/default/images/resicon/s_p_2.png',144, '/platform/system/personScript/list.ht', 0, 1,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (154, '别名脚本管理', 'aliasScript', 6, '/styles/default/images/resicon/s_s_6.png',144, '/platform/system/aliasScript/list.ht', 0, 1,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (155, 'WEB服务调用配置', 'COMMONWSSETMGR', 7, '/styles/default/images/resicon/i3.png',144, '/platform/bpmCommonWsSet/bpmCommonWsSet/list.ht', 1, 1,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (156, '编辑配置', 'COMMON_WS_EDIT', 1, '/styles/default/images/icon/tree_file.gif',155, '/platform/bpmCommonWsSet/bpmCommonWsSet/edit.ht', 0, 0,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (157, '删除配置', 'COMMON_WS_DEL', 2, '/styles/default/images/icon/tree_file.gif',155, '/platform/bpmCommonWsSet/bpmCommonWsSet/del.ht', 0, 0,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (158, '系统配置', 'sysSetting', 5, '/styles/default/images/resicon/s_s_12.png',122, '', 1, 1,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (159, '资源管理', 'resmag', 1, '/styles/default/images/resicon/s_s_16.png',158, '/platform/system/resources/tree.ht', 0, 1,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (160, '可配置数据源', 'kpzsjy', 1, '/styles/default/images/resicon/archYearReport.png',158, '', 1, 1,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (161, '数据源模板', 'sjymb', 1, '/styles/default/images/resicon/archYearReport.png',160, '/platform/system/sysDataSourceDef/list.ht', 0, 1,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (162, '数据源', 'sjy', 1, '/styles/default/images/resicon/archYearReport.png',160, '/platform/system/sysDataSource/list.ht', 0, 1,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (163, '定时计划', 'scheduler_list', 2, '/styles/default/images/resicon/s_s_4.png',158, '/platform/system/scheduler/getJobList.ht', 1, 1,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (164, '删除计划', 'delscheduler', 1, '/styles/default/images/icon/tree_file.gif',163, '', 0, 0,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (165, '系统附件', 'sysFile', 3, '/styles/default/images/resicon/s_s_11.png',158, '/platform/system/sysFile/list.ht', 1, 1,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (166, '删除附件', 'delFile', 1, '/styles/default/images/icon/tree_file.gif',165, '', 0, 0,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (167, '分类管理', 'classification', 5, '/styles/default/images/resicon/s_s_2.png',158, '', 1, 1,0, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (168, '数据字典', 'dic', 2, '/styles/default/images/resicon/s_s_8.png',167, '/platform/system/dictionary/tree.ht', 0, 1,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (169, '系统分类', 'glType', 3, '/styles/default/images/resicon/s_s_10.png',167, '/platform/system/globalType/tree.ht', 0, 1,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (170, '分类标识管理', 'typeKey', 4, '/styles/default/images/resicon/tree_file.gif',167, '/platform/system/sysTypeKey/list.ht', 1, 1,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (171, '删除分类标识键', 'delKey', 1, '/styles/default/images/resicon/del.png',170, '/platform/system/sysTypeKey/del.ht', 0, 0,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (172, '添加分类标识键', 'addKey', 1, '/styles/default/images/resicon/add.png',170, '/platform/system/sysTypeKey/edit.ht', 0, 0,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (173, '系统日志', 'sysAudit', 6, '/styles/default/images/resicon/s_s_13.png',158, '', 1, 1,0, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (174, '日志开关', 'sysLogSwitch', 1, '/styles/default/images/resicon/s_s_20.png',173, '/platform/system/sysLogSwitch/management.ht', 0, 1,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (175, '系统日志', 'subAudit', 2, '/styles/default/images/resicon/s_s_13.png',173, '/platform/system/sysAudit/list.ht', 0, 1,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (176, '错误日志', 'sysErrorLog', 3, '/styles/default/images/resicon/icon_xml.png',173, '/platform/system/sysErrorLog/list.ht', 0, 1,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (177, '消息日志管理', 'MessageLog', 4, '/styles/default/images/resicon/s_s_15.png',173, '/platform/system/messageLog/list.ht', 0, 1,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (178, '子系统管理', 'subSys', 7, '/styles/default/images/resicon/s_s_17.png',158, '/platform/system/subSystem/list.ht', 1, 1,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (179, '添加子系统', 'addSubsystem', 9, '/styles/default/images/resicon/add.png',178, '/platform/system/subSystem/edit.ht', 0, 0,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (180, '删除子系统', 'delSubsystem', 9, '/styles/default/images/resicon/del.png',178, '/platform/system/subSystem/del.ht', 0, 0,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (181, 'URL拦截管理', 'sysUrlPermission', 9, '/styles/default/images/resicon/s_s_19.png',158, '/platform/system/sysUrlPermission/list.ht', 0, 1,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (182, '个性设置管理', 'sysPaur', 10, '/styles/default/images/resicon/s_s_6.png',158, '/platform/system/sysPaur/list.ht', 0, 0,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (183, '访问地址管理', 'ipAccept', 11, '/styles/default/images/resicon/s_s_3.png',158, '/platform/system/sysAcceptIp/list.ht', 1, 1,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (184, '删除地址', 'delAddress', 1, '/styles/default/images/resicon/del.png',183, '/platform/system/sysAcceptIp/del.ht', 0, 0,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (185, '添加地址', 'addNode', 1, '/styles/default/images/resicon/tree_file.gif',183, '/platform/system/sysAcceptIp/edit.ht', 0, 0,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (186, '系统监控', 'sysMonitor', 6, '/styles/default/images/resicon/s_e_2.png',122, '', 1, 1,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (187, 'JMS队列监控', 'queues', 1, '/styles/default/images/resicon/s_e_1.png',186, '/platform/jms/queues/list.ht', 0, 1,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (188, '数据源信息监控', 'DataSourceInfoMonitor', 2, '/styles/default/images/resicon/i3_14.png',186, '/admin', 0, 1,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (189, 'Office相关配置', 'office', 7, '/styles/default/images/resicon/s_o_2.png',122, '', 1, 1,0, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (190, '印章管理', 'SealManage', 1, '/styles/default/images/resicon/s_o_1.png',189, '/platform/system/seal/list.ht', 0, 1,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (191, 'Office模板管理', 'sysOfficeTemplate', 3, '/styles/default/images/resicon/s_o_3.png',189, '/platform/system/sysOfficeTemplate/list.ht', 0, 1,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (192, '代码生成器', 'code', 8, '/styles/default/images/resicon/s_c_3.png',122, '', 1, 1,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (193, '生成配置', 'codegen', 1, '/styles/default/images/resicon/s_c_2.png',192, '/platform/system/sysCodegen/show.ht', 0, 1,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (194, '模版文件管理', 'codeTemp', 2, '/styles/default/images/resicon/s_c_1.png',192, '/platform/system/sysCodeTemplate/list.ht', 0, 1,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (195, '提醒功能', 'txgn', 1, '//styles/default/images/resicon/5.png',122, '/platform/system/sysPopupRemind/list.ht', 0, 1,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (196, '系统属性', 'xtsx', 1, '//styles/default/images/resicon/s_s_12.png',170, '/platform/system/sysProperty/list.ht', 0, 1,1, 1, '');

insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (197, '权限批量转移', 'qxplzy', 1, '//styles/default/images/resicon/9.png',117, '/platform/system/sysTransDef/list.ht', 0, 1,1, 1, '');

INSERT INTO SYS_RES VALUES (198, '首页管理', 'SysIndexManage', 1, '//styles/default/images/resicon/destopOld.gif', 122, NULL, 1, 1, 0, 

1, 0, '');
INSERT INTO SYS_RES VALUES (199, '首页栏目', 'SysIndexColumn', 1, '//styles/default/images/resicon/i3_4.png', 198, 

'/platform/system/sysIndexColumn/list.ht', 0, 1, 1, 1, 0, '');
INSERT INTO SYS_RES VALUES (200, '首页布局', 'SysIndexLayout', 2, '//styles/default/images/resicon/board.gif', 198, 

'/platform/system/sysIndexLayout/list.ht', 0, 1, 1, 1, 0, '');
INSERT INTO SYS_RES VALUES (201, '布局管理', 'SysIndexLayoutManage', 3, '//styles/default/images/resicon/9Old (2).png', 198, 

'/platform/system/sysIndexLayoutManage/list.ht', 0, 1, 1, 1, 0, '');
INSERT INTO SYS_RES VALUES (202, '我的首页布局', 'sysIndexMyLayout', 4, '//styles/default/images/resicon/s_o_1.png', 198, 

'/platform/system/sysIndexMyLayout/design.ht', 0, 1, 1, 1, 0, '');

INSERT INTO sys_res VALUES (203, '栏目公告', 'lmgg', 1, '//styles/default/images/resicon/file.png',1, null, 1, 1, 1, 1, 0, '');

INSERT INTO sys_res VALUES (204, '栏目管理', 'fgslmgl', 1, '//styles/default/images/resicon/file.png', 203, '/platform/system/sysBulletinColumn/list.ht', 1, 1, 1, 1, 0, '203');

INSERT INTO sys_res VALUES (205, '公告管理', 'jtgggl', 1, '//styles/default/images/resicon/icon003a18.gif', 203, '/platform/system/sysBulletin/list.ht', 1, 1, 1, 1, 0, '203');


insert into SYS_RES (RESID, RESNAME, ALIAS, SN, ICON,PARENTID, DEFAULTURL, ISFOLDER, ISDISPLAYINMENU, ISOPEN, SYSTEMID,PATH)
values (207, '权限批量转移', 'qxplzy', 1, '//styles/default/images/resicon/9.png',122, '/platform/system/sysTransDef/list.ht', 0, 1,1, 
1, '');

INSERT INTO SYS_RES VALUES (208, 'WORD模板管理', 'WORDmbgl', 1, '//styles/default/images/resicon/s_p_2.png', 2, 
'/platform/system/sysWordTemplate/list.ht', 0, 1, 1, 1, '', 0);

INSERT INTO SYS_RES VALUES (209, '批量转移', 'plzy', NULL, '//styles/default/images/resicon/s_s_9.png', 1, 
'/platform/system/sysTransDef/set.ht', 0, 1, 1, 1, 0, NULL);


INSERT INTO SYS_RES VALUES (210, '流程批量审批定义设置管理', 'plzy', NULL, '//styles/default/images/resicon/s_s_9.png', 122, 
'/platformmmBatchApprovalst.ht', 0, 1, 1, 1, 0, NULL);


INSERT INTO sys_res VALUES (211, '日程管理', 'rcgl', 1, '//styles/default/images/resicon/s_l_1.png', 1, NULL, 1, 1, 1, 1, '', 0);
INSERT INTO sys_res VALUES (212, '我提交的日程', 'wtjdrc', 1, '/styles/default/images/icon/tree_file.gif', 211, '/platform/system/sysPlan/submit.ht', 0, 1, 1, 1, 0, '');
INSERT INTO sys_res VALUES (213, '我负责的日程', 'wfzdrc', 1, '//styles/default/images/resicon/s_l_5.png', 211, '/platform/system/sysPlan/charge.ht', 0, 1, 1, 1, 0, '');
INSERT INTO sys_res VALUES (214, '我参与的日程', 'wcydrc', 1, '//styles/default/images/resicon/s_f_4.png', 211, '/platform/system/sysPlan/participant.ht', 0, 1, 1, 1, 0, '');
INSERT INTO sys_res VALUES (215, '我订阅的日程', 'wdydrc', 1, '/styles/default/images/icon/tree_file.gif', 211, '/platform/system/sysPlan/subscribeList.ht', 0, 1, 1, 1, 0, '');
INSERT INTO sys_res VALUES (216, '日程订阅', 'rcdy', 1, '/styles/default/images/icon/tree_file.gif', 211, '/platform/system/sysPlan/subscribe.ht', 0, 1, 1, 1, 0, '');
INSERT INTO sys_res VALUES (217, '订阅日程管理', 'dyrcgl', 1, '/styles/default/images/icon/tree_file.gif', 211, '/platform/system/sysPlan/underMatters.ht', 0, 1, 1, 1, 0, '');




insert into sys_type_key (TYPEID, TYPEKEY, TYPENAME, FLAG, SN, TYPE) values (7, 'INDEX_COLUMN_TYPE', '首页栏目分类', 1, 7, 0);

INSERT INTO sys_index_layout VALUES (1, '一列(12)', '一列(12)', '<div class="col-md-12 colum"></div>', 1);
INSERT INTO sys_index_layout VALUES (2, '二列(6,6)', '二列(6,6)', '<div class="col-md-6 column"></div><div class="col-md-6 column"></div>', 2);
INSERT INTO sys_index_layout VALUES (3, '二列(9,3)', '二列(9,3)', '<div class="col-md-9 column"></div><div class="col-md-3 column"></div>', 3);
INSERT INTO sys_index_layout VALUES (4, '二列(3,9)', '二列(3,9)', '<div class="col-md-3 column"></div><div class="col-md-9 column"></div>', 4);
INSERT INTO sys_index_layout VALUES (5, '二列(8,4)', '二列(8,4)', '<div class="col-md-8 column"></div><div class="col-md-4 column"></div>', 5);
INSERT INTO sys_index_layout VALUES (6, '二列(4,8)', '二列(4,8)', '<div class="col-md-4 column"></div><div class="col-md-8 column"></div>', 6);
INSERT INTO sys_index_layout VALUES (7, '二列(10,2)', '二列(10,2)', '<div class="col-md-10 column"></div><div class="col-md-2 column"></div>', 7);
INSERT INTO sys_index_layout VALUES (8, '二列(2,10)', '二列(2,10)', '<div class="col-md-2 column"></div><div class="col-md-10 column"></div>', 8);
INSERT INTO sys_index_layout VALUES (9, '三列(4,4,4)', '二列(4,4,4)', '<div class="col-md-4 column"></div><div class="col-md-4 column"></div><div class="col-md-4 column"></div>', 9);
INSERT INTO sys_index_layout VALUES (10, '三列(2,6,4)', '二列(2,6,4)', '<div class="col-md-2 column"></div><div class="col-md-6 column"></div><div class="col-md-4 column"></div>', 10);
INSERT INTO sys_index_layout VALUES (11, '三列(4,6,2)', '二列(4,6,2)', '<div class="col-md-4 column"></div><div class="col-md-6 column"></div><div class="col-md-2 column"></div>', 11);
		
		
			/*sys_script*/
			insert into sys_script (ID, NAME, SCRIPT, CATEGORY, MEMO)
			values (1, '获取当前日期。指定格式输出', 'return scriptImpl.getCurrentDate("yyyy-MM-dd HH:mm:ss"); ', '当前用户脚本', '获取当前日期。指定格式输出: 返回日期类型如下： 2002-11-06');

			insert into sys_script (ID, NAME, SCRIPT, CATEGORY, MEMO)
			values (2, '获取发起用户所属角色', 'return scriptImpl.getUserRoles(strUserId);', '其他用户脚本', '获取发起用户所属角色');

			insert into sys_script (ID, NAME, SCRIPT, CATEGORY, MEMO)
			values (3, '获取当前用户组织的ID', 'return scriptImpl.getCurrentOrgId();', '当前用户脚本', '获取当前用户组织的ID');

			insert into sys_script (ID, NAME, SCRIPT, CATEGORY, MEMO)
			values (4, '取得当前用户组织的名称', 'return scriptImpl.getCurrentOrgName();', '当前用户脚本', '取得当前用户组织的名称');

			insert into sys_script (ID, NAME, SCRIPT, CATEGORY, MEMO)
			values (5, '取得当前用户主组织名称', 'return scriptImpl.getCurrentPrimaryOrgName();', '当前用户脚本', '取得当前用户主组织名称');

			insert into sys_script (ID, NAME, SCRIPT, CATEGORY, MEMO)
			values (6, '判断用户是否属于某角色', 'return scriptImpl.isUserInRole(userId,role);', '判断脚本', '判断用户是否属于某角色');

			insert into sys_script (ID, NAME, SCRIPT, CATEGORY, MEMO)
			values (7, '判断用户是否属于某组织', 'return scriptImpl.isUserInOrg(String userId, String orgName);', '判断脚本', '判断用户是否属于某组织');

			insert into sys_script (ID, NAME, SCRIPT, CATEGORY, MEMO)
			values (8, '取得当前登录用户id', 'return scriptImpl.getCurrentUserId();', '当前用户脚本', '取得当前登录用户id');

			insert into sys_script (ID, NAME, SCRIPT, CATEGORY, MEMO)
			values (9, '启动某个流程', 'return scriptImpl.startFlow(String flowKey, String businnessKey,Map<String, Object> vars);', '流程脚本', '启动某个流程');

			insert into sys_script (ID, NAME, SCRIPT, CATEGORY, MEMO)
			values (10, '根据工号获取姓名', 'return scriptImpl.getFullNameByAccount(String accout);', '其他用户脚本', '根据工号获取姓名');

			insert into sys_script (ID, NAME, SCRIPT, CATEGORY, MEMO)
			values (11, '根据多工号获取多姓名', 'return scriptImpl.getFullNameByAccounts(String accouts);', '其他用户脚本', '根据多工号获取多姓名:工号字符串，多个以逗号隔开');

			insert into sys_script (ID, NAME, SCRIPT, CATEGORY, MEMO)
			values (12, '根据多工号获取多个用户ID', 'return scriptImpl.getUserIdsByAccounts(String accouts);', '其他用户脚本', '根据多工号获取多个用户ID');

			insert into sys_script (ID, NAME, SCRIPT, CATEGORY, MEMO)
			values (13, '根据工号获取用户ID', 'return scriptImpl.getUserIdByAccount(String accout);', '其他用户脚本', '根据工号获取用户ID');

			insert into sys_script (ID, NAME, SCRIPT, CATEGORY, MEMO)
			values (14, '根据工号获取组织名称', 'return scriptImpl.getOrgNameByAccount(String accout);', '其他用户脚本', '根据工号获取组织名称');

			insert into sys_script (ID, NAME, SCRIPT, CATEGORY, MEMO)
			values (15, '根据工号获取组织ID', 'return scriptImpl.getOrgIdByAccount(String account);', '其他用户脚本', '根据工号获取组织ID');

			insert into sys_script (ID, NAME, SCRIPT, CATEGORY, MEMO)
			values (16, '判断组织A是否为组织B的子组织', 'return scriptImpl.getOrgBelongTo(String sonOrgId,Long parentOrgId)', '判断脚本', '判断组织A是否为组织B的子组织');

			insert into sys_script (ID, NAME, SCRIPT, CATEGORY, MEMO)
			values (17, '根据组织id获取组织名称', 'return scriptImpl.getOrgNameById(Long orgId);', '其他用户脚本', '根据组织id获取组织名称');

			insert into sys_script (ID, NAME, SCRIPT, CATEGORY, MEMO)
			values (18, '返回当前组织的类型id', 'import com.hotent.platform.model.system.SysOrgType;SysOrgType sysOrgType =scriptImpl.getCurrentOrgType();return sysOrgType.getId();', '当前用户脚本', '返回当前组织的类型id');

			insert into sys_script (ID, NAME, SCRIPT, CATEGORY, MEMO)
			values (19, '返回当前组织类型的名称', 'return scriptImpl.getCurrentOrgTypeName();', '当前用户脚本', '返回当前组织类型的名称');

			insert into sys_script (ID, NAME, SCRIPT, CATEGORY, MEMO)
			values (20, '判断用户是否该部门的负责人', 'return scriptImpl.isDepartmentLeader(String userId, String orgId) ;', '判断脚本', '判断用户是否该部门的负责人');

			insert into sys_script (ID, NAME, SCRIPT, CATEGORY, MEMO)
			values (21, '获取用户领导id集合', 'return scriptImpl.getMyLeader();', '其他用户脚本', '获取用户领导id集合');

			insert into sys_script (ID, NAME, SCRIPT, CATEGORY, MEMO)
			values (22, '获取用户下属用户ID集合', 'return scriptImpl.getMyUnderUserId();', '其他用户脚本', '获取用户下属用户ID集合');

			insert into sys_script (ID, NAME, SCRIPT, CATEGORY, MEMO)
			values (23, '取得当前登录用户工号', 'return scriptImpl.getAccount() ;', '当前用户脚本', '取得当前登录用户工号');

			insert into sys_script (ID, NAME, SCRIPT, CATEGORY, MEMO)
			values (24, '取得当前登录用户名称', 'return scriptImpl.getCurrentName();', '当前用户脚本', '取得当前登录用户名称');

			insert into sys_script (ID, NAME, SCRIPT, CATEGORY, MEMO)
			values (25, '获取用户的主岗位名称', 'return scriptImpl.getUserPos(userId)', '其他用户脚本', '获取用户的主岗位名称');

			insert into sys_script (ID, NAME, SCRIPT, CATEGORY, MEMO)
			values (26, '获取当前日期', 'return scriptImpl.getCurrentDate();', '当前用户脚本', '获取当前日期');

			insert into sys_script (ID, NAME, SCRIPT, CATEGORY, MEMO)
			values (27, '判断当前用户是否属于该角色', 'return scriptImpl.hasRole(alias);', '判断脚本', '判断当前用户是否属于该角色');

			insert into sys_script (ID, NAME, SCRIPT, CATEGORY, MEMO)
			values (28, '取得当前用户主组织的ID', 'return scriptImpl.getCurrentPrimaryOrgId();', '当前用户脚本', '取得当前用户主组织的ID');

			insert into sys_script (ID, NAME, SCRIPT, CATEGORY, MEMO)
			values (29, '取得某用户主组织的Id', 'return scriptImpl.getUserOrgId(Long userId);', '其他用户脚本', '取得某用户主组织的Id');

			insert into sys_script (ID, NAME, SCRIPT, CATEGORY, MEMO)
			values (30, '取得某用户主组织的名称', 'return scriptImpl.getUserOrgName(Long userId);', '其他用户脚本', '取得某用户主组织的名称');

			insert into sys_script (ID, NAME, SCRIPT, CATEGORY, MEMO)
			values (31, '获取当前用户所属角色', ' return scriptImpl.getCurrentUserRoles();', '当前用户脚本', '获取当前用户所属角色');

			insert into sys_script (ID, NAME, SCRIPT, CATEGORY, MEMO)
			values (32, '根据用户ID获取岗位Id', 'return scriptImpl.getUserPosId(userId)', '其他用户脚本', '根据用户ID获取岗位Id');

			insert into sys_script (ID, NAME, SCRIPT, CATEGORY, MEMO)
			values (33, '获取当前用户的主岗位名称', 'return scriptImpl.getCurUserPos();', '当前用户脚本', '获取当前用户的主岗位名称');

			insert into sys_script (ID, NAME, SCRIPT, CATEGORY, MEMO)
			values (34, '根据工号获取岗位ID', ' return scriptImpl.getPosIdByAccount(account);', '其他用户脚本', '根据工号获取岗位ID');

			insert into sys_script (ID, NAME, SCRIPT, CATEGORY, MEMO)
			values (35, '根据工号获取岗位名称', 'return scriptImpl.getPosNameByAccount(account);', '其他用户脚本', '根据工号获取岗位名称');

			insert into sys_script (ID, NAME, SCRIPT, CATEGORY, MEMO)
			values (36, '获取当前用户的主岗位ID', 'return scriptImpl.getCurUserPosId();', '当前用户脚本', '获取当前用户的主岗位ID');

			insert into sys_script (ID, NAME, SCRIPT, CATEGORY, MEMO)
			values (37, '获取cmd对象', 'import com.hotent.core.bpm.model.ProcessCmd;return scriptImpl.getProcessCmd()', '其他用户脚本', '获取cmd对象');

			insert into sys_script (ID, NAME, SCRIPT, CATEGORY, MEMO)
			values (38, '通过用户账号设置任务执行人', '  return setAssigneeByAccount(TaskEntity task, String userAccout);', '流程脚本', '通过用户账号设置任务执行人');

			insert into sys_script (ID, NAME, SCRIPT, CATEGORY, MEMO)
			values (39, '根据当前用户取得指定参数key的参数值', 'return scriptImpl.getParaValue(String paramKey);', '其他用户脚本', '根据当前用户取得指定参数key的参数值');

			insert into sys_script (ID, NAME, SCRIPT, CATEGORY, MEMO)
			values (40, '根据用户ID和参数key获取参数值', 'return scriptImpl.getParaValueByUser(Long userId, String paramKey);', '其他用户脚本', '根据用户ID和参数key获取参数值');

			insert into sys_script (ID, NAME, SCRIPT, CATEGORY, MEMO)
			values (41, '获取流程当前用户直属领导的主岗位名称', 'return scriptImpl.getCurDirectLeaderPos();', '当前用户脚本', '获取流程当前用户直属领导的主岗位名称');

			insert into sys_script (ID, NAME, SCRIPT, CATEGORY, MEMO)
			values (42, '获取用户的组织的直属领导', 'return scriptImpl.getDirectLeaderByUserId(String userId);', '当前用户脚本', '获取用户的组织的直属领导');

			insert into sys_script (ID, NAME, SCRIPT, CATEGORY, MEMO)
			values (43, '执行用户自定义的sql语句', 'return scriptImpl.executeSql(String sql);', '执行sql脚本', '执行用户自定义的sql语句');

            insert into sys_script (ID, NAME, SCRIPT, CATEGORY, MEMO)
            values (44, '获取当前分公司或集团', 'return scriptImpl.getCurrentCompany();', '当前用户脚本', '获取当前分公司或集团');

            insert into sys_script (ID, NAME, SCRIPT, CATEGORY, MEMO)
            values (45, '获取当前单位类型', 'return scriptImpl.getCurrentCompanyType();', '当前用户脚本', '获取当前单位类型');

            insert into sys_script (ID, NAME, SCRIPT, CATEGORY, MEMO)
            values (46, '判断当前登陆者部门是否拥有该部门属性', 'return scriptImpl.hasParamKeyForOrg(String paramKey);', '判断脚本', '判断当前登陆者部门是否拥有该部门属性');

            insert into sys_script (ID, NAME, SCRIPT, CATEGORY, MEMO)
            values (47, '判断当前登陆者是否拥有该用户属性', 'return scriptImpl.hasParamKeyForUser(String paramKey);', '判断脚本', '判断当前登陆者部门是否拥有该部门属性');
           
            insert into sys_script (ID, NAME, SCRIPT, CATEGORY, MEMO)
			values (48, '逐级审批跳转规则(当前登陆者分公司或集团就停止)', 'return scriptImpl.isTopUpserApproveForCurUser();', '判断脚本', '逐级审批跳转规则到当前登陆者分公司或集团就停止');

			insert into sys_script (ID, NAME, SCRIPT, CATEGORY, MEMO)
			values (49, '逐级审批跳转规则', 'return scriptImpl.isTopUpserApprove(Long orgType);', '判断脚本', '逐级审批跳转规则');

			insert into sys_script (ID, NAME, SCRIPT, CATEGORY, MEMO)
			values (50, '获取当前分公司或集团的ID', 'return scriptImpl.getCurrentCompanyOrgId();', '当前用户脚本', '获取当前分公司或集团的ID');

			insert into sys_script (ID, NAME, SCRIPT, CATEGORY, MEMO)
			values (51, '获取当前分公司或集团的名称', 'return scriptImpl.getCurrentCompanyOrgName();', '当前用户脚本', '获取当前分公司或集团的名称');
            
			insert into sys_script (ID, NAME, SCRIPT, CATEGORY, MEMO)
            values (52, '获取当前用户的岗位名称', 'return scriptImpl.getCurrentPosName();', '当前用户脚本', '获取当前用户的岗位名称');
            
            insert into sys_script (ID, NAME, SCRIPT, CATEGORY, MEMO)
            values (53, '获取当前用户的岗位id', 'return scriptImpl.getCurrentPosId();', '当前用户脚本', '获取当前用户的岗位id');
	
			insert into sys_script (ID, NAME, SCRIPT, CATEGORY, MEMO)
			values (54, '获取当前用户的职务名称', 'return scriptImpl.getCurrentJob();', '当前用户脚本', '获取当前用户的职务名称');

			insert into sys_script (ID, NAME, SCRIPT, CATEGORY, MEMO)
			values (55, '获取当前用户的职务等级', 'return scriptImpl.getCurrentJobGrade();', '当前用户脚本', '获取当前用户的职务等级');
			
			insert into sys_script (ID, NAME, SCRIPT, CATEGORY, MEMO)
			values (56, '判断当前登陆者的分公司是否拥有该部门属性', 'return scriptImpl.hasParamKeyForCompany(String paramKey);', '判断脚本', '判断当前登陆者的分公司是否拥有该部门属性');

			
			insert into SYS_TEMPLATE (templateId,name,HTMLCONTENT,PLAINCONTENT,isDefault,useType,title)
			values (1, '审批提醒', '审批提醒：<br />您有新的工作流需要审批${事项名称}',  '审批提醒：您有新的工作流需要审批${事项名称}',1,3,'审批提醒：您有新的工作流需要审批${事项名称}');

			insert into SYS_TEMPLATE (templateId,name,HTMLCONTENT,PLAINCONTENT,isDefault,useType,title)
			values (2, '流程节点无人员', '您提交的${事项名称}${节点名称}节点无对应审批人，请尽快联络管理员处理！',  '您提交的${事项名称}${节点名称}节点无对应审批人，请尽快联络管理员处理！',1,13,'您提交的${事项名称}${节点名称}节点无对应审批人，请尽快联络管理员处理！');

			insert into SYS_TEMPLATE (templateId,name,HTMLCONTENT,PLAINCONTENT,isDefault,useType,title)
			values (3, '沟通提醒', '沟通提醒：<br />您有新的沟通${事项名称}，沟通原因：${原因}',  '沟通提醒：您有新的沟通${事项名称}，沟通原因：${原因}',1,6,'沟通提醒：您有新的沟通${事项名称}');

			insert into SYS_TEMPLATE (templateId,name,HTMLCONTENT,PLAINCONTENT,isDefault,useType,title)
			values (4, '跟进事项预警', '${事项名称}已到预警时间，请及时处理！',  '${事项名称}已到预警时间，请及时处理！',1,14,'${事项名称}已到预警时间，请及时处理！');

			insert into SYS_TEMPLATE (templateId,name,HTMLCONTENT,PLAINCONTENT,isDefault,useType,title)
			values (5, '跟进事项通知', '您有新的跟进事项需要处理${事项名称}',  '您有新的跟进事项需要处理${事项名称}',1,17,'您有新的跟进事项需要处理${事项名称}');

			insert into SYS_TEMPLATE (templateId,name,HTMLCONTENT,PLAINCONTENT,isDefault,useType,title)
			values (6, '取消代理', '用户${代理人}已取消代理，请知悉！',  '用户${代理人}已取消代理，请知悉！',1,11,'用户${代理人}已取消代理，请知悉！');

			insert into SYS_TEMPLATE (templateId,name,HTMLCONTENT,PLAINCONTENT,isDefault,useType,title)
			values (7, '逾期提醒', '${事项名称}已到达${逾期级别}级逾期时间，请及时处理！',  '${事项名称}已到达${逾期级别}级逾期时间，请及时处理！',1,19,'${事项名称}已到达${逾期级别}级逾期时间');

			insert into SYS_TEMPLATE (templateId,name,HTMLCONTENT,PLAINCONTENT,isDefault,useType,title)
			values (8, '代理提醒', '代理提醒:<br />您有新的代理事项需要审批${事项名称}',  '代理提醒:您有新的代理事项需要审批${事项名称}',1,22,'代理提醒:您有新的代理事项需要审批${事项名称},请尽快处理！');

			insert into SYS_TEMPLATE (templateId,name,HTMLCONTENT,PLAINCONTENT,isDefault,useType,title)
			values (9, '抄送提醒', '抄送提醒：<br />${事项名称}抄送给您，请查阅。',  '抄送提醒：${事项名称}抄送给您，请查阅。',1,12,'抄送提醒：${事项名称}抄送给您，请查阅。');

			insert into SYS_TEMPLATE (templateId,name,HTMLCONTENT,PLAINCONTENT,isDefault,useType,title)
			values (10, '撤销提醒', '撤销提醒：<br />${事项名称}已被撤消，撤销原因：${原因}，请知悉！',  '撤销提醒：${事项名称}已被撤消，撤销原因：${原因}，请知悉！',1,4,'撤销提醒：${事项名称}已被撤消，请知悉！');

			insert into SYS_TEMPLATE (templateId,name,HTMLCONTENT,PLAINCONTENT,isDefault,useType,title)
			values (11, '取消转办', '${事项名称}用户${转办人}已取消转办，请知悉！',  '${事项名称}用户${转办人}已取消转办，请知悉！',1,5,'${事项名称}用户${转办人}已取消转办，请知悉！');

			insert into SYS_TEMPLATE (templateId,name,HTMLCONTENT,PLAINCONTENT,isDefault,useType,title)
			values (12, '跟进事项完成,评价', '${事项名称}跟进事项已完成，请进行评价。', '${事项名称}跟进事项已完成，请进行评价。',1,16,'${事项名称}跟进事项已完成，请进行评价。');

			insert into SYS_TEMPLATE (templateId,name,HTMLCONTENT,PLAINCONTENT,isDefault,useType,title)
			values (13, '被沟通提交', '被沟通人已提交${事项名称},意見為：${事项意见}',  '被沟通人已提交${事项名称},意見為：${事项意见}',1,10,'被沟通人已提交${事项名称}');

			insert into SYS_TEMPLATE (templateId,name,HTMLCONTENT,PLAINCONTENT,isDefault,useType,title)
			values (14, '跟进事项已评价知会', '${事项名称}跟进事项已评价，请查阅。',  '${事项名称}跟进事项已评价，请查阅。',1,18,'${事项名称}跟进事项已完成，请进行评价');

			insert into SYS_TEMPLATE (templateId,name,HTMLCONTENT,PLAINCONTENT,isDefault,useType,title)
			values (15, '加签反馈', '您有一个加签反馈需要查看，事项名称：${事项名称}，反馈意见：${原因}',  '您有一个加签反馈需要查看，事项名称：${事项名称}，反馈意见：${原因}',1,27,'加签反馈：${事项名称}');

			insert into SYS_TEMPLATE (templateId,name,HTMLCONTENT,PLAINCONTENT,isDefault,useType,title)
			values (16, '终止提醒', '终止提醒：<br />${事项名称}已被终止，终止原因：${原因}，请知悉！<br />', '终止提醒：${事项名称}已被终止，终止原因：${原因}，请知悉！',1,1,'终止提醒：${事项名称}已被终止，请知悉！');

			insert into SYS_TEMPLATE (templateId,name,HTMLCONTENT,PLAINCONTENT,isDefault,useType,title)
			values (17, '退回提醒', '退回提醒：<br />${事项名称}已被退回，退回原因：${原因}，请知悉！',  '退回提醒：${事项名称}已被退回，退回原因：${原因}，请知悉！',1,9,'退回提醒：${事项名称}已被退回，请知悉！');

			insert into SYS_TEMPLATE (templateId,name,HTMLCONTENT,PLAINCONTENT,isDefault,useType,title)
			values (18, '转办提醒', '转办提醒：<br />您有一个转办${事项名称}，转办原因：${原因}，请审批！', '转办提醒：您有一个转办${事项名称}，转办原因：${原因}，请审批！',1,8,'转办提醒：您有一个转办${事项名称}，请审批！');

			insert into SYS_TEMPLATE (templateId,name,HTMLCONTENT,PLAINCONTENT,isDefault,useType,title)
			values (19, '归档提醒', '归档提醒：<br />${事项名称}已归档，请查阅。',  '归档提醒：${事项名称}已归档，请查阅。',1,7,'归档提醒：${事项名称}已归档，请查阅。');

			insert into SYS_TEMPLATE (templateId,name,HTMLCONTENT,PLAINCONTENT,isDefault,useType,title)
			values (20, '催办提醒', '催办提醒：<br />您有一个催办${事项名称}，催办原因：${原因}，请尽快处理！<br />',  '催办提醒：您有一个催办${事项名称}，催办原因：${原因}，请尽快处理！',1,2,'催办提醒：您有一个催办${事项名称},请尽快处理！');

			insert into SYS_TEMPLATE (templateId,name,HTMLCONTENT,PLAINCONTENT,isDefault,useType,title)
			values (21, '跟进事项到期', '${事项名称}已到期，请尽快处理！',  '${事项名称}已到期，请尽快处理！',1,15,'${事项名称}已到期，请尽快处理！');

			insert into SYS_TEMPLATE (templateId,name,HTMLCONTENT,PLAINCONTENT,isDefault,useType,title)
			values (22, '转发提醒', '转发提醒：<br />${事项名称}转发给您，请查阅。转发原因：${原因}', '转发提醒：${事项名称}转发给您，请查阅。',1,23,'转发提醒：${事项名称}转发给您，请查阅。');

			insert into SYS_TEMPLATE (templateId,name,HTMLCONTENT,PLAINCONTENT,isDefault,useType,title)
			values (23, '通知任务所属人(代理)', '通知任务所属人(代理)：<br />您有新的审批事项${事项名称}，现已被代理人承接。',  '通知任务所属人(代理)：您有新的审批事项${事项名称}，现已被代理人承接。',1,25,'通知任务所属人(代理)：事项${事项名称}');

			insert into SYS_TEMPLATE (templateId,name,HTMLCONTENT,PLAINCONTENT,isDefault,useType,title)
			values (24, '加签提醒', '加签提醒：<br />您有一个流转任务需要审批，事项名称：${事项名称}，原因：${原因}', '您有一个流转任务需要审批，事项名称：${事项名称}，原因：${原因}',1,26,'加签提醒：${事项名称}');
			
			insert into SYS_TEMPLATE (templateId,name,HTMLCONTENT,PLAINCONTENT,isDefault,useType,title)
			values (25, '消息转发', '消息转发：<br />您的事项名称：${事项名称}已经被转发', '您的事项名称：${事项名称}已经被转发',1,24,'消息转发：${事项名称}');

			
			insert into SYS_TEMPLATE (templateId,name,HTMLCONTENT,PLAINCONTENT,isDefault,useType,title)
			values (28, '取消流转', '取消流转提醒：<br />您的${事项名称}流转任务已经被取消', '取消流转提醒:您的${事项名称}流转任务已经被取消',1,28,'取消流转通知');
			
			insert into SYS_TEMPLATE (templateId,name,HTMLCONTENT,PLAINCONTENT,isDefault,useType,title)
			values (29, '补签提醒', '补签提醒：<br />您有新的补签${事项名称}', '补签提醒：您有新的补签${事项名称}',1,29,'补签提醒：您有新的补签${事项名称}');


			insert into BPM_FORM_RULE (id,name,REGULATION,memo,tipInfo)
			values (1, '手机号码', '^(((13[0-9]{1})|(15[0-9]{1}))+\d{8})$', '验证手机号码', '手机号码输入有误');

			insert into BPM_FORM_RULE (id,name,REGULATION,memo,tipInfo)
			values (2, 'email', '^\w+([-+.]\w+)*@\w+([-.]\w+)*.\w+([-.]\w+)*$', 'email规则验证', 'email格式输入有误');

			insert into BPM_FORM_RULE (id,name,REGULATION,memo,tipInfo)
			values (3, 'QQ号码', '^[1-9]*[1-9][0-9]*$', '验证QQ号码', '请输入有效的QQ号码');

			insert into BPM_FORM_RULE (id,name,REGULATION,memo,tipInfo)
			values (4, '整数', '^-?\d+$', '验证整数', '请输入整数');

			insert into BPM_FORM_RULE (id,name,REGULATION,memo,tipInfo)
			values (5, '正整数', '^[1-9]+\d*$', '验证正整数', '请输入正整数');

			insert into BPM_FORM_RULE (id,name,REGULATION,memo,tipInfo)
			values (6, '负整数', '^-{1}\d+$', '验证负整数', '请输入负整数');

			insert into BPM_FORM_RULE (id,name,REGULATION,memo,tipInfo)
			values (7, '汉字', '^[\u4E00-\u9FA5]+$', '验证汉字', '请输入汉字');

			insert into BPM_FORM_RULE (id,name,REGULATION,memo,tipInfo)
			values (8, '英数字', '^[a-zA-Z0-9]+$', '验证输入英文与数字', '请输入英文字母和数字');

			insert into BPM_FORM_RULE (id,name,REGULATION,memo,tipInfo)
			values (9, '非负整数', '^(0|[1-9]\d*)$', '验证非负整数', '请输入非负整数');

			insert into BPM_FORM_RULE (id,name,REGULATION,memo,tipInfo)
			values (10, '英文字母', '^[a-zA-Z]+$', '验证英文字母', '请输入英文字母');

			insert into BPM_FORM_RULE (id,name,REGULATION,memo,tipInfo)
			values (11, 'IP地址', '^([0-9]{1,3}\.{1}){3}[0-9]{1,3}$', '验证输入的IP地址是否正确', 'IP地址不正确');
			insert into sys_data_source_def (ID_, NAME_, CLASS_PATH_, SETTING_JSON_, INIT_METHOD_, IS_SYSTEM_, CLOSE_METHOD_)
			values (10000008880008, 'BasicDataSource', 'org.apache.commons.dbcp.BasicDataSource', '[{"name":"driverClassName","comment":"链接类型","type":"java.lang.String","baseAttr":true},{"name":"url","comment":"链接地址","type":"java.lang.String","baseAttr":true},{"name":"username","comment":"用户名","type":"java.lang.String","baseAttr":false},{"name":"password","comment":"密码","type":"java.lang.String","baseAttr":false}]', null, 1, null);
			
			insert into sys_data_source_def (ID_, NAME_, CLASS_PATH_, SETTING_JSON_, INIT_METHOD_, IS_SYSTEM_, CLOSE_METHOD_)
			values (10000008880012, 'ProxoolDataSource', 'org.logicalcobwebs.proxool.ProxoolDataSource', '[{"name":"alias","comment":"别名(唯一)","type":"java.lang.String","baseAttr":"1"},{"name":"driver","comment":"类型","type":"java.lang.String","baseAttr":"1"},{"name":"houseKeepingTestSql","comment":"未知houseKeepingTestSql","type":"java.lang.String","baseAttr":"0"},{"name":"maximumActiveTime","comment":"未知maximumActiveTime","type":"long","baseAttr":"0"},{"name":"maximumConnectionCount","comment":"最大连接数","type":"int","baseAttr":"1"},{"name":"minimumConnectionCount","comment":"最少连接数","type":"int","baseAttr":"1"},{"name":"password","comment":"密码","type":"java.lang.String","baseAttr":"1"},{"name":"simultaneousBuildThrottle","comment":"未知simultaneousBuildThrottle","type":"int","baseAttr":"0"},{"name":"driverUrl","comment":"链接地址","type":"java.lang.String","baseAttr":"1"},{"name":"user","comment":"用户名","type":"java.lang.String","baseAttr":"1"}]', null, 1, 'org.logicalcobwebs.proxool.ProxoolFacade|shutdown');
			INSERT INTO qrtz_job_details VALUES ('scheduler', '任务催办', 'group1', '任务催办依赖的定时计划', 'com.hotent.platform.job.ReminderJob', '1', '1', '0', '0', '');
            
insert into SYS_PROPERTY (id,NAME_,ALIAS_,VALUE_,GROUP_,SN_) 
values(1,'全局流水号','globalFlowNo','true','流程表单',1);
            
insert into SYS_PROPERTY (id,NAME_,ALIAS_,VALUE_,GROUP_,SN_,memo) 
values(2,'平台地址','serverUrl','http://192.168.1.8:8080/bpm','系统参数',1,'平台地址');

insert into SYS_PROPERTY (id,NAME_,ALIAS_,VALUE_,GROUP_,SN_,memo) 
values(3,'超级管理员帐号','account','admin','系统参数',2,'如果登录用户帐号和这个一致,那么这个用户为超级管理员');

insert into SYS_PROPERTY (id,NAME_,ALIAS_,VALUE_,GROUP_,SN_,memo) 
values(4,'附件存放类型','file.saveType','Folder','附件配置',3,'数据库：Database 文件夹：Folder');

insert into SYS_PROPERTY (id,NAME_,ALIAS_,VALUE_,GROUP_,SN_,memo) 
values(5,'附件存放地址','file.upload','D:\TEST','附件配置',4,'如果存放类型为Folder,这个是存放文件夹。');

insert into SYS_PROPERTY (id,NAME_,ALIAS_,VALUE_,GROUP_,SN_,memo) 
values(6,'发送邮件账号','mail.from','787874563@qq.com','发送邮件配置',6,'发送邮件账号');

insert into SYS_PROPERTY (id,NAME_,ALIAS_,VALUE_,GROUP_,SN_,memo) 
values(7,'邮件服务器','mail.host','smtp.qq.com','发送邮件配置',7,'邮件服务器');

insert into SYS_PROPERTY (id,NAME_,ALIAS_,VALUE_,GROUP_,SN_,memo) 
values(8,'发送邮件账户','mail.username','787874563@qq.com','发送邮件配置',8,'发送邮件账户');

insert into SYS_PROPERTY (id,NAME_,ALIAS_,VALUE_,GROUP_,SN_,memo) 
values(9,'用户密码','mail.password','20yongbufenli.','发送邮件配置',9,'发送邮件用户密码');

insert into SYS_PROPERTY (id,NAME_,ALIAS_,VALUE_,GROUP_,SN_,memo) 
values(10,'登录是否使用验证码','validCodeEnabled','false','系统参数',10,'登录的时候是否启用验证码');

insert into SYS_PROPERTY (id,NAME_,ALIAS_,VALUE_,GROUP_,SN_,memo) 
values(11,'系统模板文件的类型','officedoc','doc,docx,xls','系统参数',11,'系统模板文件的类型');

insert into SYS_PROPERTY (id,NAME_,ALIAS_,VALUE_,GROUP_,SN_,memo) 
values(12,'泳道标题背景颜色RGB','POOL_BACKGROUP_COLOR','211,215,212','流程图',12,'泳道标题背景颜色RGB');

insert into SYS_PROPERTY (id,NAME_,ALIAS_,VALUE_,GROUP_,SN_,memo) 
values(13,'泳道边框颜色RGB','LANE_BOUNDARY_COLOR','138,140,142','流程图',13,'泳道边框颜色RGB');

insert into SYS_PROPERTY (id,NAME_,ALIAS_,VALUE_,GROUP_,SN_,memo) 
values(14,'主页显示风格','UI_STYLE','default','系统参数',14,'default为默认风格，extendIndex为新风格');

insert into SYS_PROPERTY (id,NAME_,ALIAS_,VALUE_,GROUP_,SN_,memo) 
values(15,'BPM_BUS_LINK分区是否支持分区','supportPartion','1','系统参数',15,'是否支持数据库表BPM_BUS_LINK分区 0=不分区,1分区');

insert into SYS_PROPERTY (id,NAME_,ALIAS_,VALUE_,GROUP_,SN_,memo) 
values(16,'平台名称','appName','广州宏天软件有限公司--BPMX3流程业务管理平台','系统参数',16,'平台名称');

insert into SYS_PROPERTY (id,NAME_,ALIAS_,VALUE_,GROUP_,SN_,memo) 
values(17,'发送消息在多长时间内可编辑','send.timeout','200000','系统参数',17,'发送消息在多长时间内可编辑,单位为毫秒');

insert into SYS_PROPERTY (id,NAME_,ALIAS_,VALUE_,GROUP_,SN_,memo) 
values(18,'短信网关地址','smsUrl','','一丁短信',18,'一丁短信网关WEB服务地址');

insert into SYS_PROPERTY (id,NAME_,ALIAS_,VALUE_,GROUP_,SN_,memo) 
values(19,'短信租户ID','smsUserID','','一丁短信',19,'一丁短信网关已申请租户');

insert into SYS_PROPERTY (id,NAME_,ALIAS_,VALUE_,GROUP_,SN_,memo) 
values(20,'短信帐号','smsAccount','','一丁短信',20,'一丁短信网关已申请帐号');

insert into SYS_PROPERTY (id,NAME_,ALIAS_,VALUE_,GROUP_,SN_,memo) 
values(21,'短信密码','smsPassword','','一丁短信',21,'一丁短信网关账户对应密码');

insert into SYS_PROPERTY (id,NAME_,ALIAS_,VALUE_,GROUP_,SN_,memo) 
values(22,'显示岗位查找','userDialog.showPos','1','用户对话框',22,'显示岗位查找(1:显示,0:不显示)');

insert into SYS_PROPERTY (id,NAME_,ALIAS_,VALUE_,GROUP_,SN_,memo) 
values(23,'显示角色查找','userDialog.showRole','1','用户对话框',23,'显示角色查找(1:显示,0:不显示)');

insert into SYS_PROPERTY (id,NAME_,ALIAS_,VALUE_,GROUP_,SN_,memo) 
values(24,'显示在线用户','userDialog.showOnlineUser','1','用户对话框',24,'显示在线用户(1:显示,0:不显示)');

insert into SYS_PROPERTY (id,NAME_,ALIAS_,VALUE_,GROUP_,SN_,memo) 
values(25,'附件上传类型','uploadType','flash','附件配置',25,'选择附件类型flash和html');

insert into SYS_PROPERTY (id,NAME_,ALIAS_,VALUE_,GROUP_,SN_,memo) 
values(28,'任务预警类型','task.WarnLevel','[{level:1,name:"黄色预警",color:"yellow"},{level:2,name:"红色预警",color:"red"}]','流程配置',28,'配置任务预警类型');

insert into SYS_PROPERTY (id,NAME_,ALIAS_,VALUE_,GROUP_,SN_,memo) 
values(27,'数字类型','datatype.number','int,double,float,decimal,number,numeric','数据类型',27,'数据类型');

insert into SYS_PROPERTY (id,NAME_,ALIAS_,VALUE_,GROUP_,SN_,memo) 
values(26,'日期类型','datatype.date','date,timestamp,datetime','数据类型',26,'数据类型');

insert into SYS_PROPERTY (id,NAME_,ALIAS_,VALUE_,GROUP_,SN_,memo) 
values(29,'字符串类型','datatype.varchar','varchar,varchar2,char','数据类型',29,'数据类型');

insert into SYS_PROPERTY (id,NAME_,ALIAS_,VALUE_,GROUP_,SN_,memo) 
values(30,'大文本','datatype.text','clob,text','数据类型',30,'数据类型');


INSERT INTO bpm_form_dialog VALUES 
('1', '自定义对话框列表', 'zdydhklb', '0', '800', '600', '1', '1', '1', 'bpm_form_dialog', 
'[{"field":"NAME","comment":"对话框名称"}]',
'[{"field":"STYLE","comment":"显示样式            0,列表            1,树形","condition":"=","dbType":"number","defaultType":"4","defaultValue":""}]', 
'[{"field":"ID","comment":"主键ID"},{"field":"NAME","comment":"对话框名称"}]', 
'', 'LOCAL', '10', '[]');
INSERT INTO bpm_form_dialog VALUES 
('2', '业务数据模板', 'ywsjmb', 0, 800, 600, 1, 1, 0, 'v_formdef_template',
 '[{"field":"SUBJECT","comment":"主题"}]', '[]', 
 '[{"field":"SUBJECT","comment":"主题"},
 {"field":"FORMDEFID","comment":"表单ID"},
 {"field":"CONDITIONFIELD","comment":"条件字段"},
 {"field":"FORMKEY","comment":"表单别名"}]',
 '', 'LOCAL', 10, '[]');




commit;