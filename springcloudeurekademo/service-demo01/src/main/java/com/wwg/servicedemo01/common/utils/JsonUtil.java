package com.wwg.servicedemo01.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wwg.servicedemo01.common.enums.StatusCodeEnum;
import org.springframework.util.StringUtils;

public class JsonUtil {

    /**
     * 验证是否为json格式
     * @param param
     * @return
     */
    public static boolean isJson(String param){
        boolean flag = true;
        try{
            Object obj =  JSONObject.parse(param);
            if(obj instanceof JSONObject || obj instanceof JSONArray){
                flag = true;
            }else{
                flag = false;
            }
        }catch (Exception e){
            flag = false;
        }
        return flag;
    }

    public static JSONObject validateParam(BaseResult baseResult,String param,String... params){
        JSONObject jsonObject = null;
        if(param.equals("") || param.equals(null)){
            return jsonObject;
        }
        jsonObject = JSON.parseObject(param);
        if(!params.equals("") && !params.equals(null)) {

            //变量参数数组
            for (int i = 0; i < params.length; i++) {
                //key 名称
                String paramKey = params[i];
                if(paramKey.equals("") || paramKey.equals(null)){
                    continue;
                }
                //key对应的值类型
                String paramType = null;
                if (params[i].contains(",")) {
                    String[] splitArr = params[i].split(",");
                    paramKey = splitArr[0];
                    paramType = splitArr[1];
                }
                //判断参数是否存在
                if (!jsonObject.containsKey(paramKey) || jsonObject.get(paramKey) == null) {
                    baseResult.setCode(StatusCodeEnum.PARAM_ERROR.getCode());
                    baseResult.setMessage(StatusCodeEnum.PARAM_ERROR.getMsg() + "缺少参数：" + params[i]);
                    return jsonObject;
                }
                //判断参数类型是否正确
                String paramClassType = jsonObject.get(paramKey).getClass().getTypeName();
                if (!StringUtils.isEmpty(paramType)
                        && !paramClassType.equalsIgnoreCase(paramType)
                        && !paramClassType.contains(paramType)) {

                    baseResult.setCode(StatusCodeEnum.PARAM_ERROR.getCode());
                    baseResult.setMessage(StatusCodeEnum.PARAM_ERROR.getMsg() + "：" +
                            paramKey + " 实为: " +
                            (paramClassType.contains(".") ? paramClassType.substring(paramClassType.lastIndexOf(".") + 1) : paramClassType) +
                            ", 应为: " + paramType);
                    return jsonObject;
                }

            }
        }
        return jsonObject;
    }
}
