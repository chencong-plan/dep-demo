package com.jytpay.depdemo.util;

/**
 * 异常代码，加入默认说明，这样减少配置文件的写入了
 */
public enum PlatformErrorCode {
	
	//渠道错误码,EC开头
	SUCCESS_RESPONSE_CODE("S00000","交易成功"),
	SEND_SMS_SUCCESS("S00001","短信已经发送"),
	DOING_RESPONSE_CODE("EC0001","处理中"),
	FAIL_RESPONSE_CODE("EC1000","交易暂不支持"),
	//交易系统错误，ES开头
	UNKNOWN_ERROR("ES0001", "系统异常"), 
	DB_ERROR("ES0002", "数据库操作错误"), 
	PARSE_MSG_ERROR("ES0003","报文解析异常"),
	NO_CHANNEL_USE("ES0004", "未找到相关渠道相关业务"),
	DUBBO_TIME_OUT("ES0005", "服务调用超时"),
	DATA_CONVERT_ERROR("ES0006","Map转XML类型转换异常"),
	HTTP_STATUS_ERROR("ES0007", "http访问渠道，响应状态码错误"),
	CHANNEL_STOP("ES0008", "渠道未启用"),
	CHANNEL_MAINTAIN("ES0009", "渠道系统维护"),
	CHANNEL_BANK_OUT_AMT("ES0010", "渠道单笔交易额度受限"),
	CHANNEL_NO_HAVE("ES0011", "无渠道可用"),
	BIZ_RPC_ERROR("ES0012","第三方调用RpcException异常"),
	VALIDATE_ERROR("ES0013","业务数据检验失败"),
	AUTH_FILD("ES0014","认证失败"),
	SIGN_E0000008("ES0015","数据编码不支持"),
	SIGN_E0000005("ES0016","DES解密失败"),
	MERCHANT_MAPPING_ERROR("ES0017","商户映射渠道信息未找到"),
	CHANNEL_BANK_CODE_ERROR("ES0018","渠道银行不支持"),
	NO_CHANNEL_PARAM("ES0019","渠道参数未配置"),
	CHANNEL_ERROR("ES0020","渠道异常"),
	CHANNEL_SERVICE_UNREALIZED("ES0021","渠道未实现"),
	UNDEFUND_ERROR_CODE("ES0022","未定义的错误码"),
	UN_SET_CHANNEL_TRAN_PROPERTIES("ES0023","未配置渠道交易属性"),
	CHANNEL_SET_PROPERTIES_ERROR("ES0024","渠道配置属性错误"),
	CHANNEL_RESPONSE_NULL("ES0025","渠道响应码为空"),
	//此异常直在渠道层未实现的方法抛出
	CHANNEL_BIZ_UNREALIZED("ES0026","渠道业务未实现"),
	TRAN_TYPE_NULL("ES0027","交易类型参数为空"),
	CHANNEL_REQ_VALIDATE_ERROR("ES0028","渠道层请求参数校验未通过"),
	

	CHNANEL_SING_ERROR("ES0020","签名异常"),
	CARD_TYPE_NOT_SUPPORT("ES0021","卡类型不支持"),
	
	HTTP_REQUEST_ERROR("ES0022","http请求渠道出现异常"),
	BASE64_AES_ENCRY_ERROR("ES0023","AES加密异常"),
	
	AMOUNT_FORMAT_ERROR("ES0024","金额格式错误"),
	
	
	PERMISSION_DENIED("E59101","管理系统身份校验失败！"),
	FILE_DOWNLOAD_ERROR("E59201","文件输出异常！"),
	PFX_FILE_NOT_EXIST("E59202","私钥证书不存在"),
	CER_FILE_NOT_EXIST("E59203","公钥证书不存在"),
	PFX_PASS_ERROR("E59204","私钥证书密码不存在"),
	CHANNEL_RES_DECRITY_ERROR("E59204","渠道响应解密失败"),
	CHANNEL_BANKINFO_CONVERT_NULL("E59205","渠道银行映射信息为空"),
	CHANNEL_RES_NULL("E59206","渠道响应为空"),
	CHANNEL_RES_ERROR("E59207","渠道返回报文格式异常"),
	CHANNEL_VERIFY_SIGN_ERROR("E59208","验证渠道签名失败"),
	BIND_CARD_ERROR("E59209","绑卡失败"),
	RECORD_NOT_FOUND("E59210","预绑卡记录不存在"),
	BIND_NOT_FOUND("E59211","银行卡未绑定该渠道，请绑卡后发起"),
	TRAN_ORDER_NOT_FOUNT("E59212","未获取到交易订单信息"),
	TRAN_ORDER_STATUS_ERROR("E59213","订单状态异常"),
	
	/**
	 * 一般不会出现该异常，该异常仅作为cg层收款、付款时trade_flow 或者 biz_flow 两流水号入库时触发唯一索引出现异常
	 */
	TRAN_TRADE_OR_BIZ_FLOW_CONFLICT("E59214","订单号冲突，稍后重试"),
	;
	
	private String code;

	private String defaultMessage;

	PlatformErrorCode(String code, String defaultMessage) {
		this.code = code;
		this.defaultMessage = defaultMessage;
	}

	public String getErrorCode() {
		return this.code;
	}

	public String getDefaultMessage() {
		return defaultMessage;
	}

}
