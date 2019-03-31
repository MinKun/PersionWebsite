package com.tydd.persion.util;

import com.tydd.persion.common.BaseCommon;
import com.tydd.persion.dto.BaseResponseDTO;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.List;

/**
 * @ClassName ResponseUtil
 * @Description 返回数据工具类
 * @Author kun
 * @Date 2019/3/31
 * @Version 1.0
 */
public class ResponseUtil {

    /**
     * 创建失败返回信息
     * @param errorMessage
     * @return
     */
    public static BaseResponseDTO buildFailResponse(String errorMessage) {
        BaseResponseDTO respData = new BaseResponseDTO();
        respData.setCode(BaseCommon.RESPONSE_STATUS_FAIL);
        respData.setMessage(errorMessage);
        return respData;
    }

    /**
     * 创建成功的返回信息
     * @param successMessage
     * @return
     */
    public static BaseResponseDTO buildSuccessResponse(String successMessage) {
        BaseResponseDTO respData = new BaseResponseDTO();
        respData.setCode(BaseCommon.RESPONSE_STATUS_SUCCESS);
        respData.setMessage(successMessage);
        return respData;
    }

    /**
     * 创建异常的返回信息
     * @param errorMessage
     * @return
     */
    public static BaseResponseDTO buildErrorResponse(String errorMessage) {
        BaseResponseDTO respData = new BaseResponseDTO();
        respData.setCode(BaseCommon.RESPONSE_STATUS_ERROR);
        respData.setMessage(errorMessage);
        return respData;
    }

    /**
     * 创建错误请求的返回信息
     * @param result
     * @return
     */
    public static BaseResponseDTO buildWrongRequestResponse(BindingResult result) {
        BaseResponseDTO responseData = new BaseResponseDTO();
        responseData.setCode(BaseCommon.RESPONSE_STATUS_REJECT);
        responseData.setMessage(buildErrorMsg(result));
        return responseData;
    }

    /**
     * 组合校验错误文本
     * @param result
     * @return
     */
    private static String buildErrorMsg(BindingResult result) {
        StringBuilder errorMsg = new StringBuilder();
        if (result != null) {
            List<ObjectError> errorList = result.getAllErrors();
            ObjectError error = null;
            for (int i = 0, length = errorList.size(); i < length; i++) {
                error = errorList.get(i);
                errorMsg.append(error.getCode())
                        .append("[")
                        .append(error.getDefaultMessage())
                        .append("]");
                if( i != (length - 1)) {
                    errorMsg.append("; ");
                }
            }
        }
        return errorMsg.toString();
    }
}
