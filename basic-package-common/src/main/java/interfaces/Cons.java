package interfaces;

/**
 * 公共常量声明接口
 */
public interface Cons {
    /**
     * 常用常量
     */
    String DOT = ".";
    String REGEX_DOT = "\\.";
    String SEMICOLON = ";";
    String COLON = ":";
    String UNDERLINE = "_";
    String COMMA = ",";
    String SLASH = "/";
    String AITE = "@";
    String LINE = "-";
    String SPACE = " ";
    String PERCENT = "%";

    char CHAR_DOT = '.';
    char CHAR_SEMICOLON = ';';
    char CHAR_COLON = ':';
    char CHAR_UNDERLINE = '_';
    char CHAR_LINE = '-';
    char CHAR_COMMA = ',';
    char CHAR_SLASH = '/';
    char CHAR_VERTICAL = '|';
    char CHAR_SPACE = ' ';
    char CHAR_AITE = '@';


    String ERROR_MORE_INFO_URL = "http://localhost:12345";

    /**
     * 时间转换格式常量
     */
    String DATA_SHORT_PATTERN = "yyyyMMdd";
    String DATA_NORMAL_PATTERN = "yyyy-MM-dd";
    String TIME_SHORT_PATTERN = "yyyyMMddHHmmss";
    String TIME_NORMAL_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * 开闭区间
     */
    String OPEN_INTERVAL = "open";
    String CLOSE_INTERVAL = "close";

    /**
     * 订阅消息的类型
     */
    /**
     * 日期
     */
    String LASTDAY_YEAR = "1231";

    /**
     * 股票行情推送消息
     */
    String GP_QUOTATIONS_MSG = "GP_QUOTATIONS_MSG";

    /**
     * 净值频率
     */
    int ONE_YEAR_DATE_NUM = 242;
    int ONE_YEAR_WEEK_NUM = 52;
    int ONE_YEAR_MONTH_NUM = 12;

    /**
     * 区间收益周期
     */
    int MONTH = 21;
    int QUARTER = 63;
    int HALF_A_YEAR = 121;
    int YEAR = 242;
    int TWO_YEAR = 242 * 2;
    int THREE_YEAR = 242 * 3;

    /**
     * 无风险利率
     */
    Double DEFAULT_NO_RISK_INTEREST_RATE = 0.03;
    /**
     * 置信度
     */
    Double LAMBDA95 = 0.95;
    Double LAMBDA99 = 0.99;

    /**
     * 重要指数代码
     */
    String SZ300 = "399300";
    String CSI500 = "000905";
    String SSE50 = "000016";
    String CSI1000 = "000852";
    String CSI_BOND = "h01001";
    String CCFI = "CCFI";
    String SZ001 = "000001";
    String ZXB = "399005";
    String CYBZ = "399006";
    String CSI_BOND_NEW = "H11001";

    /**
     * 重要指数代码中文名称
     */
    String SZ300_ZW = "沪深300指数";
    String CSI500_ZW = "中证500指数";
    String SSE50_ZW = "上证50指数";
    String CSI1000_ZW = "中证1000指数";
    String CSI_BOND_ZW = "中证全债指数(净价)";
    String CCFI_ZW = "商品指数";
    String SZ001_ZW = "上证指数";
    String ZXB_ZW = "中小板指";
    String CYBZ_ZW = "创业板指";
    String CSI_BOND_NEW_ZW = "中证全债";
    /**
     * 期权分类
     */
    String OPTION_GOU = "购";
    String OPTION_GU = "沽";
    String OPTION_BUY = "买入";
    String OPTION_SELL = "卖出";
    /**
     * 市场缩写
     */
    String A_MARTET_SH = "SH";
    String A_MARTET_SZ = "SZ";
    String A_ST = "ST";
    String A_T = "T";
    String A_SD = "SD";
    /**************
     * 有价证券类型
     */


    /**
     * 股票
     */
    String SECURITY_TYPE_STOCK = "stock";

    /**
     * 股指期货
     */
    String SECURITY_TYPE_STOCK_INDEX_FUTURES = "indexFutures";

    /**
     * 商品期货
     */
    String SECURITY_TYPE_COMMODITY_FUTURES = "commodityFutures";
    /**
     * 国债期权
     */
    String SECURITY_TYPE_NATIONAL_DEBT_OPTIONS = "nationalDebtOptions";
    /**
     * 个股期权
     */
    String SECURITY_TYPE_STOCK_OPTIONS = "stockOptions";
    /**
     * 期货期权
     */
    String SECURITY_TYPE_FUTURES_OPTIONS = "futuresOptions";
    /**
     * 交易所回购
     */
    String SECURITY_TYPE_EXCHANGE_REPURCHASE = "exchangeRepurchase";
    /**
     * 交易所基金
     */
    String SECURITY_TYPE_EXCHANGE_FUND = "exchangeFund";
    /**
     * 子基金
     */
    String SECURITY_TYPE_SUB_FUND = "subFund";

    /**
     * 开放式基金
     */
    String SECURITY_TYPE_OPEN_FUND = "openFund";

    /**
     * 未知类型
     */
    String SECURITY_TYPE_UNKNOW = "unknow";

    /**
     * 股指期货前缀
     */
    String SZ300_INDEX_FUTURES_PERFIX = "IF";
    String CSI500_INDEX_FUTURES_PERFIX = "IC";
    String HU50_INDEX_FUTURES_PERFIX = "IH";

    /**
     * 数据库中标识删除
     */
    String DB_DEL_FLAG = "D";

    /**
     * 一级行业提供商名称
     */
    String LEVEL1_SHEN_WAN_PROVIDER = "shenwan";

    /**
     * 股票市场代码
     */
    String STOCK_TRADE_MARKET_TYPE = "101001";

    /**
     * 数据自动缓存模型的类型
     */
    String HASH_MODEL = "hash";
    String TIMESERIES_MODEL = "timeSeries";
    String PARK_VISIT_MODEL = "parkVisit";
    String VERSION_MODEL = "version";

    /**
     * 上证A股前缀
     */
    String SH_PREFIX = "60";

    /**
     * 默认HashMapInitSize
     */
    int HASH_MAP_INIT_SIZE = 16;

    /**
     * 默认ArrayListInitSize
     */
    int ARRAY_LIST_INIT_SIZE = 10;

    /**
     * ZSet的index起点
     */
    int ZSET_START_INDEX = 0;
    /**
     * ZSet的index终点
     */
    int ZSET_END_INDEX = -1;

    /**
     * 数据集日期子key名称
     */
    String DATA_SET_TIME_SUB_TYPE = "DATE";

    /**
     * 周期标识(D)日
     */
    String PERIOD_D = "D";

    /**
     * 周期标识(W)周
     */
    String PERIOD_W = "W";

    /**
     * 周期标识(M)月
     */
    String PERIOD_M = "M";

    /**
     * 周期标识(Y)年
     */
    String PERIOD_Y = "Y";


    /**
     * 基准初始值
     */
    Double INITIA_LIZE = 1000D;

    /**
     * 申万行业分类
     */
    String INDCLASS_CODE = "2214";
}
