<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<c:set var="path" value="${pageContext.request.contextPath}" />

<div class="dia_containt">	
	<div class="border">
		<form id="setPwdForm">
		<ul class="setPwdUL">
			<li>
				<span>新密码：</span>
				<input type="text" name="NEW_USER_PWD" id="NEW_USER_PWD" 
				validate="{isRequired: true, isDigitOrABC: true, setLengthScope: '6-10'}" 
				isRequired="请输入由数字或字母组成的6-10位新密码！"
				isDigitOrABC="请输入由数字或字母组成的6-10位新密码！"
				setLengthScope="请输入由数字或字母组成的6-10位新密码！"/>
				<b class="title_css">*</b>
			</li>
			<li>
				<span>重新输入：</span>
				<input type="text" name="NEW2_USER_PWD" id="NEW2_USER_PWD" 
				validate="{isRequired: true, isDigitOrABC: true, setLengthScope: '6-10'}" 
				isRequired="请再次输入由数字或字母组成的6-10位新密码！"
				isDigitOrABC="请再次输入由数字或字母组成的6-10位新密码！"
				setLengthScope="请再次输入由数字或字母组成的6-10位新密码！"/>
				<b class="title_css">*</b>
			</li>
		</ul>
		</form>
	</div>
</div>
