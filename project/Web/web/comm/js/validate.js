//必填项
function isRequired(s) { 
	if ($.trim(s).length==0) {
		return false; 
	}
	return true; 
} 
//手机验证
function isMobil(s) { 
	var reg=/^((13[0-9])|(15[^4,\D])|(18[0,5-9]))\d{8}$/; 
	if (!reg.exec(s)) {
		return false; 
	}
	return true; 
} 
//固定电话
function isTelephoto(s){
	var reg = /^(([0\+]\d{2,3}-)?(0\d{2,3})-)?(\d{7,8})(-(\d{3,}))?$/;
	return reg.test(s);
}
//邮箱验证
function isEmail(s){
	var reg = /^(?:[a-z\d]+[_\-\+\.]?)*[a-z\d]+@(?:([a-z\d]+\-?)*[a-z\d]+\.)+([a-z]{2,})+$/i;
	return reg.test(s);
}
//邮件编码
function isZipCode(s){
	var reg = /^[0-9]{6}$/;
	return reg.test(s);
}
//QQ
function isQQ(s){
	var reg = /^\d{5,10}$/;
	return reg.test(s);
}
//MSN
function isMSN(s){
	var reg = /\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*/;
	return reg.test(s);
}
//数字或者字母
function isDigitOrABC(s){
	var reg=/^([0-9,a-z,A-Z])*$/; 
	return reg.test(s);
}
//整数且不等于给定值
function isDigitNotVal(s, val){ 
	var reg=/^0|([1-9][0-9]{0,14})$/; 
	if(reg.test(s)&&s!=val){
		return true;
	}else{
		return false;
	}
}
//判断是否是正浮点数
function isNumber(s){
	 var re = /^\d+(\.\d+)?$/;
	 return re.test(s);
}
//判断是否是浮点数
function isNumberAll(s){
	 var re = /^-?\d+(\.\d+)?$/;
	 return re.test(s);
}
//整数
function isDigit(s){
	var reg=/^0|([1-9][0-9]{0,14})$/; 
	return reg.test(s);
}
//数值范围判断
function setDigitScope(s, valStr){
	var array = valStr.split("-");
	//利用JS弱类型  字符转数字
	if(s*1<array[0]*1||s*1>array[1]*1){
		return false;
	}
	
	return true;
}
//字符长度范围判断
function setLengthScope(s, valStr){
	var array = valStr.split("-");
	//利用JS弱类型  字符转数字
	if(s.length<array[0]*1||s.length>array[1]*1){
		return false;
	}
	return true;
}
//最大长度判断
var maxLen = function(value, flag){
	if(value.length > flag){
		return false;
	}
	return true;
};
/**  
 * 身份证15位编码规则：dddddd yymmdd xx p   
 * dddddd：地区码   
 * yymmdd: 出生年月日   
 * xx: 顺序类编码，无法确定   
 * p: 性别，奇数为男，偶数为女  
 * <p />  
 * 身份证18位编码规则：dddddd yyyymmdd xxx y   
 * dddddd：地区码   
 * yyyymmdd: 出生年月日   
 * xxx:顺序类编码，无法确定，奇数为男，偶数为女   
 * y: 校验码，该位数值可通过前17位计算获得  
 * <p />  
 * 18位号码加权因子为(从右到左) Wi = [ 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2,1 ]  
 * 验证位 Y = [ 1, 0, 10, 9, 8, 7, 6, 5, 4, 3, 2 ]   
 * 校验位计算公式：Y_P = mod( ∑(Ai×Wi),11 )   
 * i为身份证号码从右往左数的 2...18 位; Y_P为脚丫校验码所在校验码数组位置  
 *   
 */  
var Wi = [ 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1 ];// 加权因子   
var ValideCode = [ 1, 0, 10, 9, 8, 7, 6, 5, 4, 3, 2 ];// 身份证验证位值.10代表X   
function IdCardValidate(idCard) {   
    idCard = trim(idCard.replace(/ /g, ""));   
    if (idCard.length == 15) {   
        return isValidityBrithBy15IdCard(idCard);   
    } else if (idCard.length == 18) {   
        var a_idCard = idCard.split("");// 得到身份证数组   
        if(isValidityBrithBy18IdCard(idCard)&&isTrueValidateCodeBy18IdCard(a_idCard)){   
            return true;   
        }else {   
            return false;   
        }   
    } else {   
        return false;   
    }   
}   
/**  
 * 判断身份证号码为18位时最后的验证位是否正确  
 * @param a_idCard 身份证号码数组  
 * @return  
 */  
function isTrueValidateCodeBy18IdCard(a_idCard) {   
    var sum = 0; // 声明加权求和变量   
    if (a_idCard[17].toLowerCase() == 'x') {   
        a_idCard[17] = 10;// 将最后位为x的验证码替换为10方便后续操作   
    }   
    for ( var i = 0; i < 17; i++) {   
        sum += Wi[i] * a_idCard[i];// 加权求和   
    }   
    valCodePosition = sum % 11;// 得到验证码所位置   
    if (a_idCard[17] == ValideCode[valCodePosition]) {   
        return true;   
    } else {   
        return false;   
    }   
}   
/**  
 * 通过身份证判断是男是女  
 * @param idCard 15/18位身份证号码   
 * @return 'female'-女、'male'-男  
 */  
function maleOrFemalByIdCard(idCard){   
    idCard = trim(idCard.replace(/ /g, ""));// 对身份证号码做处理。包括字符间有空格。   
    if(idCard.length==15){   
        if(idCard.substring(14,15)%2==0){   
            return 'female';   
        }else{   
            return 'male';   
        }   
    }else if(idCard.length ==18){   
        if(idCard.substring(14,17)%2==0){   
            return 'female';   
        }else{   
            return 'male';   
        }   
    }else{   
        return null;   
    }   
}   
/**  
 * 验证18位数身份证号码中的生日是否是有效生日  
 * @param idCard 18位书身份证字符串  
 * @return  
 */  
function isValidityBrithBy18IdCard(idCard18){   
    var year =  idCard18.substring(6,10);   
    var month = idCard18.substring(10,12);   
    var day = idCard18.substring(12,14);   
    var temp_date = new Date(year,parseFloat(month)-1,parseFloat(day));   
    // 这里用getFullYear()获取年份，避免千年虫问题   
    if(temp_date.getFullYear()!=parseFloat(year)   
          ||temp_date.getMonth()!=parseFloat(month)-1   
          ||temp_date.getDate()!=parseFloat(day)){   
            return false;   
    }else{   
        return true;   
    }   
}   
/**  
 * 验证15位数身份证号码中的生日是否是有效生日  
 * @param idCard15 15位书身份证字符串  
 * @return  
 */  
function isValidityBrithBy15IdCard(idCard15){   
	var year =  idCard15.substring(6,8);   
	var month = idCard15.substring(8,10);   
	var day = idCard15.substring(10,12);   
	var temp_date = new Date(year,parseFloat(month)-1,parseFloat(day));   
	// 对于老身份证中的你年龄则不需考虑千年虫问题而使用getYear()方法   
	if(temp_date.getYear()!=parseFloat(year)   
			||temp_date.getMonth()!=parseFloat(month)-1   
			||temp_date.getDate()!=parseFloat(day)){   
			return false;   
	}else{   
        return true;   
    }  
}   
//校验日期
var checkDate = function(str){
	var reg1 = /^\d{4}-((0?[1-9])|(1[0-2]))-\d{1,2}( ((0[0-9])|(1[0-9])|(2[0-3])):([0-5][0-9]):([0-5][0-9]))?$/;
	var year;
	var month;
	var dateStr = "";
	if(reg1.test(str)){
		year = parseInt(str.split("-",-1)[0],10); 
		month = parseInt(str.split("-",-1)[1],10); 
	}else{
		return false;
	}
	if((year%4==0&&year%100!=0)||(year%400==0)){
		if(month==2){
			dateStr = "((0?[1-9])|(1[0-9])|(2[0-9]))";
		}
	}else{
		if(month==2){
			dateStr = "((0?[1-9])|(1[0-9])|(2[0-8]))";
		}
	}
	if(month==1||month==3||month==5||month==7||month==8||month==10||month==12){
		dateStr = "((0?[1-9])|(1[0-9])|(2[0-9])|(3[0-1]))";
	}else if(month==4||month==6||month==9||month==11){
		dateStr = "((0?[1-9])|(1[0-9])|(2[0-9])|(30))";
	}
	eval("var reg2=/^\\d{4}-((0?[1-9])|(1[0-2]))-"+dateStr+"( ((0[0-9])|(1[0-9])|(2[0-3])):([0-5][0-9]):([0-5][0-9]))?$/");
	return reg2.test(str);
};
//校验
;(function($){
	$.fn.extend({
		myValidate : function(selectStr){
			if(!selectStr){
				selectStr = ":input[validate]";
			}
			var istrue = true;
			$(this).find(selectStr).each(function(){
				var obj = $(this);
				var obj_ = obj.attr("validate");
				var obj__ = eval("("+obj_+")");
				for(var o in obj__){
					if("isRequired"==o||("isRequired"!=o&&obj.val().length > 0)){
						if(!eval(o+"('"+obj.val()+"','"+obj__[o]+"')")){
							myDialog(obj.attr(o),0);
							istrue = false;
							return false;
						}
					}
				}
			});
			
			return istrue;
		}
	});
}(jQuery));

