# demo

1、平台说明

该平台是2015年个人针对某个小项目封装的一套快速开发平台，对于信息管理系统可快速开发完成；前端集成框架及插件有、jquery、jquery-easyui、jqplot、jqGrid、multiselect、ztree、fileupload、datetimepicker等，另外前端对以上框架插件做了封装以及编写一些工具类，方便前台页面使用；后台主要使用springmvc+mybatis，同时对dao层做了封装，方便使用；平台带有基础模块和项目管理业务模块。

2、目录说明

src\config                    后台配置目录，包含spring配置、数据源配置等
src\java\com\frame\base       后台基础模块java目录，基础模块包括单位、部门、用户、角色、菜单、字典管理等
src\java\com\frame\business   业务模块java目录
src\sqlmap\ibatis             sql语句存放目录，分基础模块、业务模块等目录
Web\ui                        前端集成框架、插件以及前台封装相关目录
Web\web\base                  前端基础模块目录
Web\web\business              前端业务模块目录
Web\web\comm                  前端公共资源及JS工具

3、部署说明 
 1）初始化数据库
 2）tomcat部署
 3）访问路径样例:http://localhost:8080/project
 4) 测试用户名密码admin/000000 ceshi001/0000

