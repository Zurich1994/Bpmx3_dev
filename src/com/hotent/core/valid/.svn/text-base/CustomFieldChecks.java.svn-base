package com.hotent.core.valid;



import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.commons.validator.Field;
import org.apache.commons.validator.GenericValidator;
import org.apache.commons.validator.ValidatorAction;
import org.apache.commons.validator.util.ValidatorUtils;
import org.springframework.validation.Errors;
import org.springmodules.validation.commons.FieldChecks;

import com.hotent.core.util.DateUtil;
import com.hotent.core.util.StringUtil;

/**
 * 自定义扩展验证规则。
 * @author hotent
 *
 */
@SuppressWarnings("serial")
public class CustomFieldChecks extends FieldChecks {

	/**
	 * 判定两次输入是否一致。
	 * 
	 * <pre>
	 * 	&lt;var>
	 * 	&lt;var-name>equalTo &lt;/var-name>
	 * 	&lt;var-value>password &lt;/var-value>
	 * &lt;/var>
	 * </pre>
	 * 
	 * @param bean
	 * @param va
	 * @param field
	 * @param errors
	 * @return
	 */
	public static boolean validateEqual(Object bean, ValidatorAction va,
			Field field, Errors errors) {
		String value = extractValue(bean, field);
		String sProperty2 = field.getVarValue("equalTo");
		String value2 = ValidatorUtils.getValueAsString(bean, sProperty2);

		if (!GenericValidator.isBlankOrNull(value)) {
			try {
				if (!value.equals(value2)) {
					FieldChecks.rejectValue(errors, field, va);
					return false;
				}
			} catch (Exception e) {
				FieldChecks.rejectValue(errors, field, va);
				return false;
			}
		}

		return true;
	}
	
	public static boolean validateDateTime(Object bean, ValidatorAction va,
			Field field, Errors errors) {
		return false;

//		Date result = null;
//		extractValue(bean, field)
//		String value = extractValue(bean, field);
//		String datePattern = field.getVarValue("datePattern");
//		String datePatternStrict = field.getVarValue("datePatternStrict");
//
//		if (!GenericValidator.isBlankOrNull(value)) {
//			try {
//				if (datePattern != null && datePattern.length() > 0) {
//					result = GenericTypeValidator.formatDate(value,
//							datePattern, false);
//				} else if (datePatternStrict != null
//						&& datePatternStrict.length() > 0) {
//					result = GenericTypeValidator.formatDate(value,
//							datePatternStrict, true);
//				}
//			} catch (Exception e) {
//				FieldChecks.rejectValue(errors, field, va);
//			}
//
//			if (result == null) {
//				rejectValue(errors, field, va);
//			}
//		}
//
//		return result;
	}

	/**
	 * 使用正则表达式验证。
	 * 
	 * <pre>
	 * 	&lt;var>
	 * 	&lt;var-name>regex &lt;/var-name>
	 * 	&lt;var-value>正则表达式 &lt;/var-value>
	 * &lt;/var>
	 * </pre>
	 * 
	 * @param bean
	 * @param va
	 * @param field
	 * @param errors
	 * @return
	 */
	public static boolean validateRegx(Object bean, ValidatorAction va,
			Field field, Errors errors) {
		String mask = field.getVarValue("regex");
		String value = extractValue(bean, field);
		try {
			if (!GenericValidator.isBlankOrNull(value)
					&& !StringUtil.validByRegex(mask, value)) {
				rejectValue(errors, field, va);
				return false;
			} else {
				return true;
			}
		} catch (Exception e) {
			FieldChecks.rejectValue(errors, field, va);
			return false;
		}

	}

	/**
	 * 判断是否是数字
	 * 
	 * @param bean
	 * @param va
	 * @param field
	 * @param errors
	 * @return
	 */
	public static boolean validateIsNumber(Object bean, ValidatorAction va,
			Field field, Errors errors) {
		String value = extractValue(bean, field);

		if (!GenericValidator.isBlankOrNull(value)) {
			try {
				if (!StringUtil.isNumberic(value)) {
					FieldChecks.rejectValue(errors, field, va);
					return false;
				}
			} catch (Exception e) {
				FieldChecks.rejectValue(errors, field, va);
				return false;
			}
		}

		return true;
	}

	/**
	 * 判断是否是整数。
	 * 
	 * @param bean
	 * @param va
	 * @param field
	 * @param errors
	 * @return
	 */
	public static boolean validateIsDigits(Object bean, ValidatorAction va,
			Field field, Errors errors) {
		String value = extractValue(bean, field);

		if (!GenericValidator.isBlankOrNull(value)) {
			try {
				if (!StringUtil.isInteger(value)) {
					FieldChecks.rejectValue(errors, field, va);
					return false;
				}
			} catch (Exception e) {
				FieldChecks.rejectValue(errors, field, va);
				return false;
			}
		}

		return true;
	}

	/**
	 * 校验邮件地址
	 * 
	 * @param bean
	 * @param va
	 * @param field
	 * @param errors
	 * @return
	 */
	public static boolean validateEmail(Object bean, ValidatorAction va,
			Field field, Errors errors) {
		String value = extractValue(bean, field);

		if (!GenericValidator.isBlankOrNull(value)) {
			try {
				if (!StringUtil.isEmail(value)) {
					FieldChecks.rejectValue(errors, field, va);
					return false;
				}
			} catch (Exception e) {
				FieldChecks.rejectValue(errors, field, va);
				return false;
			}
		}

		return true;
	}

	/**
	 * 验证最大值。<br>
	 * 
	 * <pre>
	 * 	&lt;var>
	 * 	&lt;var-name>max &lt;/var-name>
	 * 	&lt;var-value>100  &lt;/var-value>
	 * &lt;/var>
	 * </pre>
	 * 
	 * @param bean
	 * @param va
	 * @param field
	 * @param errors
	 * @return
	 */
	public static boolean validateMax(Object bean, ValidatorAction va,
			Field field, Errors errors) {
		String value = extractValue(bean, field);
		String max = field.getVarValue("max");
		if (!GenericValidator.isBlankOrNull(value)) {
			try {
				long lMax = Long.parseLong(max);
				long lValue = Long.parseLong(value);
				if (lValue >= lMax) {
					FieldChecks.rejectValue(errors, field, va);
					return false;
				}
			} catch (Exception e) {
				FieldChecks.rejectValue(errors, field, va);
				return false;
			}
		}

		return true;
	}

	/**
	 * 校验最小值
	 * 
	 * <pre>
	 * 	&lt;var>
	 * 	&lt;var-name>min &lt;/var-name>
	 * 	&lt;var-value>100  &lt;/var-value>
	 * &lt;/var>
	 * </pre>
	 * 
	 * @param bean
	 * @param va
	 * @param field
	 * @param errors
	 * @return
	 */
	public static boolean validateMin(Object bean, ValidatorAction va,
			Field field, Errors errors) {
		String value = extractValue(bean, field);
		String min = field.getVarValue("min");
		if (!GenericValidator.isBlankOrNull(value)) {
			try {
				long lMin = Long.parseLong(min);
				long lValue = Long.parseLong(value);
				if (lValue <= lMin) {
					FieldChecks.rejectValue(errors, field, va);
					return false;
				}
			} catch (Exception e) {
				FieldChecks.rejectValue(errors, field, va);
				return false;
			}
		}

		return true;
	}

	/**
	 * 校验长度在一个定义的范围内
	 * 
	 * <pre>
	 * 	&lt;var>
	 * 	&lt;var-name>minlength &lt;/var-name>
	 * 	&lt;var-value>3  &lt;/var-value>
	 * &lt;/var>
	 * 	&lt;var>
	 * 	&lt;var-name>maxlength &lt;/var-name>
	 * 	&lt;var-value>10  &lt;/var-value>
	 * &lt;/var>
	 * </pre>
	 * 
	 * @param bean
	 * @param va
	 * @param field
	 * @param errors
	 * @return
	 */
	public static boolean validateRangelength(Object bean, ValidatorAction va,
			Field field, Errors errors) {
		String value = extractValue(bean, field);
		int len = value.length();

		if (!GenericValidator.isBlankOrNull(value)) {
			try {
				int minlength = Integer
						.parseInt(field.getVarValue("minlength"));
				int maxlength = Integer
						.parseInt(field.getVarValue("maxlength"));
				if (len < minlength || len > maxlength) {
					FieldChecks.rejectValue(errors, field, va);
					return false;
				}
			} catch (Exception e) {
				FieldChecks.rejectValue(errors, field, va);
				return false;
			}
		}

		return true;
	}

	/**
	 * 判断是否验证URL。
	 * 
	 * @param bean
	 * @param va
	 * @param field
	 * @param errors
	 * @return
	 */
	public static boolean validateUrl(Object bean, ValidatorAction va,
			Field field, Errors errors) {
		String value = extractValue(bean, field);

		if (!GenericValidator.isBlankOrNull(value)) {
			try {

				if (!StringUtil.isUrl(value)) {
					FieldChecks.rejectValue(errors, field, va);
					return false;
				}
			} catch (Exception e) {
				FieldChecks.rejectValue(errors, field, va);
				return false;
			}
		}
		return true;
	}

	/**
	 * 是否手机号码
	 * 
	 * @param bean
	 * @param va
	 * @param field
	 * @param errors
	 * @return
	 */
	public static boolean validateMobile(Object bean, ValidatorAction va,
			Field field, Errors errors) {
		String value = extractValue(bean, field);

		if (!GenericValidator.isBlankOrNull(value)) {
			try {

				if (!StringUtil.isMobile(value)) {
					FieldChecks.rejectValue(errors, field, va);
					return false;
				}
			} catch (Exception e) {
				FieldChecks.rejectValue(errors, field, va);
				return false;
			}
		}
		return true;
	}

	/**
	 * 是否电话号码
	 * 
	 * @param bean
	 * @param va
	 * @param field
	 * @param errors
	 * @return
	 */
	public static boolean validatePhone(Object bean, ValidatorAction va,
			Field field, Errors errors) {
		String value = extractValue(bean, field);

		if (!GenericValidator.isBlankOrNull(value)) {
			try {

				if (!StringUtil.isPhone(value)) {
					FieldChecks.rejectValue(errors, field, va);
					return false;
				}
			} catch (Exception e) {
				FieldChecks.rejectValue(errors, field, va);
				return false;
			}
		}
		return true;
	}

	/**
	 * 邮编号码
	 * 
	 * @param bean
	 * @param va
	 * @param field
	 * @param errors
	 * @return
	 */
	public static boolean validateZip(Object bean, ValidatorAction va,
			Field field, Errors errors) {
		String value = extractValue(bean, field);

		if (!GenericValidator.isBlankOrNull(value)) {
			try {

				if (!StringUtil.isZip(value)) {
					FieldChecks.rejectValue(errors, field, va);
					return false;
				}
			} catch (Exception e) {
				FieldChecks.rejectValue(errors, field, va);
				return false;
			}
		}
		return true;
	}

	/**
	 * qq
	 * 
	 * @param bean
	 * @param va
	 * @param field
	 * @param errors
	 * @return
	 */
	public static boolean validateQq(Object bean, ValidatorAction va,
			Field field, Errors errors) {
		String value = extractValue(bean, field);

		if (!GenericValidator.isBlankOrNull(value)) {
			try {

				if (!StringUtil.isQq(value)) {
					FieldChecks.rejectValue(errors, field, va);
					return false;
				}
			} catch (Exception e) {
				FieldChecks.rejectValue(errors, field, va);
				return false;
			}
		}
		return true;
	}

	/**
	 * ip地址
	 * 
	 * @param bean
	 * @param va
	 * @param field
	 * @param errors
	 * @return
	 */
	public static boolean validateIp(Object bean, ValidatorAction va,
			Field field, Errors errors) {
		String value = extractValue(bean, field);

		if (!GenericValidator.isBlankOrNull(value)) {
			try {

				if (!StringUtil.isIp(value)) {
					FieldChecks.rejectValue(errors, field, va);
					return false;
				}
			} catch (Exception e) {
				FieldChecks.rejectValue(errors, field, va);
				return false;
			}
		}
		return true;
	}

	/**
	 * 判断中文
	 * 
	 * @param bean
	 * @param va
	 * @param field
	 * @param errors
	 * @return
	 */
	public static boolean validateChinese(Object bean, ValidatorAction va,
			Field field, Errors errors) {
		String value = extractValue(bean, field);

		if (!GenericValidator.isBlankOrNull(value)) {
			try {

				if (!StringUtil.isChinese(value)) {
					FieldChecks.rejectValue(errors, field, va);
					return false;
				}
			} catch (Exception e) {
				FieldChecks.rejectValue(errors, field, va);
				return false;
			}
		}
		return true;
	}

	/**
	 * 字母和数字的验证
	 * 
	 * @param bean
	 * @param va
	 * @param field
	 * @param errors
	 * @return
	 */
	public static boolean validateChrnum(Object bean, ValidatorAction va,
			Field field, Errors errors) {
		String value = extractValue(bean, field);

		if (!GenericValidator.isBlankOrNull(value)) {
			try {

				if (!StringUtil.isChrNum(value)) {
					FieldChecks.rejectValue(errors, field, va);
					return false;
				}
			} catch (Exception e) {
				FieldChecks.rejectValue(errors, field, va);
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 开始结束时间比较
	 * @param bean
	 * @param va
	 * @param field
	 * @param errors
	 * @return
	 */
	public static boolean compStartEndTime(Object bean, ValidatorAction va,
			Field field, Errors errors) {
		String sTimevalue = extractValue(bean, field);
		String sProperty2 = field.getVarValue("compStartEndTime");
		String eTimevalue = ValidatorUtils.getValueAsString(bean, sProperty2);

		try {
			
			DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
			Date sTime = null;
			Date eTime = null;
			if(sProperty2.toLowerCase().indexOf("end")!=-1){
				sTime = dateformat.parse(DateUtil.timeStrToDateStr(sTimevalue));
				eTime = dateformat.parse(DateUtil.timeStrToDateStr(eTimevalue));
			}else{
				sTime = dateformat.parse(DateUtil.timeStrToDateStr(eTimevalue));
				eTime = dateformat.parse(DateUtil.timeStrToDateStr(sTimevalue));
			}
			
			if(!sTime.before(eTime)){
				FieldChecks.rejectValue(errors, field, va);
				return false;
			}
			
		} catch (Exception e) {
			FieldChecks.rejectValue(errors, field, va);
			return false;
		}

		return true;
	}
	
	/**
	 * 计算一组数字的下限
	 * @param bean
	 * @param va
	 * @param field
	 * @param errors
	 * @return
	 */
	public static boolean digitsSum(Object bean, ValidatorAction va,
			Field field, Errors errors) {

		int sum = 0;
		int valLimit = 0;
		try {
			Map vars = (Map)field.getVars();
			valLimit = Integer.parseInt(vars.toString().split("  ")[1].split("=")[1]);
			String key = field.getKey();
			String methodName = "get"+key.substring(0, 1).toUpperCase()+key.substring(1, key.length());
			Method method = bean.getClass().getMethod(methodName, new Class[0]);
			String value = String.valueOf(method.invoke(bean, new Object[0]));
			String[] arrVal = value.split("[,]");
			for(String val:arrVal){
				sum += Integer.parseInt(val);
			}
			
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return sum<=valLimit?true:false;
	}
	
}
