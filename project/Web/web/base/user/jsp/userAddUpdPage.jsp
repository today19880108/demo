<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<c:set var="path" value="${pageContext.request.contextPath}" />

<div class="dia_containt">	
	<div class="border">
		<input type="hidden" name="SEARCH_FLAG" id="SEARCH_FLAG" value="${param.SEARCH_FLAG}"/>
		<form id="userAddUdpForm">
		<input type="hidden" name="USER_ID" id="USER_ID" value="${userObj.USER_ID}"/>
		<ul class="addUpd">
			<li>
				<span>用户名称：</span>
				<input type="text" name="USER_NAME" id="USER_NAME" value="${userObj.USER_NAME}" 
				validate="{isRequired: true, maxLen: 30}" 
				isRequired="请输入用户名称！"
				maxLen="用户名称最长30字符！"/>
				<c:if test="${param.SEARCH_FLAG!='SEARCH'}">
				<b class="title_css">*</b>
				</c:if>
			</li>
			<li>
				<span>用户登录名：</span>
				<input type="text" name="USER_LOGIN" id="USER_LOGIN" value="${userObj.USER_LOGIN}" 
				validate="{isRequired: true, maxLen: 30}" 
				isRequired="请输入用户登录名！"
				maxLen="用户登录名最长30字符！"/>
				<c:if test="${param.SEARCH_FLAG!='SEARCH'}">
				<b class="title_css">*</b>
				</c:if>
			</li>
			<li>
				<span>所属部门：</span>
				<input type="text" name="DEPT_NAME2" id="DEPT_NAME2" value="${userObj.DEPT_NAME}"/>
				<input type="hidden" name="DEPT2" id="DEPT2" value="${userObj.DEPT}" 
				validate="{isRequired: true}" 
				isRequired="请选择所属部门！"/>
				<c:if test="${param.SEARCH_FLAG!='SEARCH'}">
				<b class="title_css">*</b>
				</c:if>
				<a href="javascript:;" id="DEPT_SELECT_BUT" class="select_but">选择</a>
			</li>
			<li>
				<span>身份证号：</span>
				<input type="text" name="USER_CODE" id="USER_CODE" value="${userObj.USER_CODE}"
				validate="{maxLen: 30}"
				maxLen="身份证号最长30字符！"/>
			</li>
			<li>
				<span>性别：</span>
				<select name="SEX" id="SEX">
					<option value="">---请选择---</option>
					<c:forEach var="varO" items="${SEXList}">
					<option value="${varO.ID}">${varO.NAME}</option>
					</c:forEach>
				</select>			
				<script>
				$("#userAddUdpForm #SEX").val("${userObj.SEX}");
				</script>	
			</li>
			<li>
				<span>年龄：</span>
				<input type="text" name="AGE" id="AGE" value="${userObj.AGE}"
				validate="{isDigit: true}"
				isDigit="请输入整数的年龄！"
				/>
			</li>
			<li>
				<span>联系电话：</span>
				<input type="text" name="TEL" id="TEL" value="${userObj.TEL}"
				validate="{maxLen: 30}"
				maxLen="联系电话最长30字符！"
				/>
			</li>
			<li>
				<span>邮箱：</span>
				<input type="text" name="EMAIL" id="EMAIL" value="${userObj.EMAIL}"
				validate="{maxLen: 30}"
				maxLen="邮箱最长30字符！"
				/>
			</li>
			<li>
				<span>显示顺序：</span>
				<input type="text" name="SHOW_ORDER" id="SHOW_ORDER" value="${userObj.SHOW_ORDER}"
				validate="{isDigit: true}"
				isDigit="请输入整数的显示顺序！"
				/>
			</li>
			<li>
				<span>状态：</span>
				<select name="ISUSER" id="ISUSER">
					<c:forEach var="varO" items="${ISUSERList}">
					<option value="${varO.ID}">${varO.NAME}</option>
					</c:forEach>
				</select>	
				<script>
				$("#userAddUdpForm #ISUSER").val("${userObj.ISUSER}");
				</script>			
			</li>
			<li class="all_width">
				<span>描述：</span>
				<textarea name="REMARK" id="REMARK"
				validate="{maxLen: 150}"
				maxLen="描述最长150字符！"
				>${userObj.REMARK}</textarea>
			</li>
		</ul>
		</form>
	</div>
</div>

<script type="text/javascript" src="${path}/web/base/user/js/userAddUpdPage.js"></script>
<link rel="stylesheet" type="text/css" href="${path}/web/base/user/css/userAddUpdPage.css" />
