package exception;

public interface CommonErrorCode {
    ErrorCode ERROR_CODE_OF_EXCEPTION_500 = new ErrorCode(1000, "服务器端错误，请稍后再试");
    ErrorCode ERROR_CODE_OF_EXCEPTION_400 = new ErrorCode(1001, "默认捕获类消息");
    ErrorCode ERROR_CODE_INPUT_PARAMS_ERROR = new ErrorCode(1002, "入参错误");
    ErrorCode ERROR_CODE_DATE_STR_TO_DATE_ERROR = new ErrorCode(1003, "时间字符串转换时间出错");
    ErrorCode ERROR_CODE_CIRCUIT_BREAK = new ErrorCode(1004, "依赖服务被熔断");
    ErrorCode ERROR_CODE_MODEL_TYPE_IS_EMPTY = new ErrorCode(1005, "数据集模型类型为空");
    ErrorCode ERROR_CODE_DATA_SET_NAME_IS_EMPTY = new ErrorCode(1006, "数据集模型名称为空");
    ErrorCode ERROR_CODE_DATA_SET_STRATEGY_IS_EMPTY = new ErrorCode(1007, "数据集模型处理策略为空");
    ErrorCode ERROR_CODE_VERIFY_FIELD = new ErrorCode(1008, "数据集为空的列");
    ErrorCode ERROR_CODE_VERIFY_KEY = new ErrorCode(1009, "数据集为空的Key");
    ErrorCode ERROR_CODE_OF_GET_CALENDAR_ERROR = new ErrorCode(14000, "输入的日期为非交易日期");
    ErrorCode ERROR_CODE_OF_GET_NETALENDAR_ERROR = new ErrorCode(14003, "输入的交易日期个数应大于1 ");
    ErrorCode ERROR_CODE_OF_GET_FUNDCODE_ERROR = new ErrorCode(14002, "没有找到输入的产品名称 ");
    ErrorCode ERROR_CODE_FUNDCODE_TYPE_NOT_STOCK_OR_INDEX_FUTURE_ERROR = new ErrorCode(14004, "该基金产品下没找到对应的股票或股指期货持仓");
    ErrorCode ERROR_CODE_NO_RESULT = new ErrorCode(14005, "未找到结果");
    ErrorCode ERROR_CODE_TIMEOUT = new ErrorCode(14006, "访问超时");
    ErrorCode ERROR_CODE_MARKET_VALUE_RESULT = new ErrorCode(14006, "没有对应市值市值为空");
    ErrorCode ERROR_CODE_DATA_VALUE_RESULT = new ErrorCode(14007, "数据不完整，从新选择查询时间");
    ErrorCode ERROR_CODE_DATA_AFTER = new ErrorCode(14008, "结束时间不能大于当前时间");
    ErrorCode ERROR_CODE_DATA_START_END = new ErrorCode(14009, "查询时间间隔要大于当前时间的2个交易日以上");
    ErrorCode ERROR_CODE_DATA_NULL = new ErrorCode(14010, "该时间范围无数据返回");
    ErrorCode ERROR_CODE_STOCK_DATA_NULL = new ErrorCode(14011, "该产品无股票数据");
    ErrorCode ERROR_CODE_NOT_LEGAL_PRODUCT = new ErrorCode(14012, "该产品不存在");
    ErrorCode ERROR_CODE_THREE_MONTH = new ErrorCode(14013, "时间范围不能超过3个月");
    ErrorCode ERROR_CODE_DATA_END_START = new ErrorCode(14014, "结束时间不能小于开始时间");
    ErrorCode ERROR_CODE_NO_FACOTR_DATA = new ErrorCode(14015, "该时间范围无因子数据");
    ErrorCode ERROR_CODE_DATA_NOT_COMPLETE = new ErrorCode(14016, "数据不完整");
    ErrorCode ERROR_CODE_STOCK_NOT_EXIST = new ErrorCode(14016, "股票信息错误");}
