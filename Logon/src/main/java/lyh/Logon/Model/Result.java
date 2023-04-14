package lyh.Logon.Model;/*
 * @Auther:刘宇航
 * @Date:2023/4/5
 * @VERSON:1.0
 */

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
/**
 * JSON 返回模型
 */
public class Result<D> implements Serializable {

    @JsonProperty("isSuccess")
    private boolean success = false;//登陆状态
    //返回消息短码，一般用于出错时，简短描述错误
    private String code;
    // 返回消息具体信息，一般用于出错时，比较详细的描述错误
    private String message;
    // 返回的具体数据
    private D data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public D getData() {
        return data;
    }

    public void setData(D data) {
        this.data = data;
    }
}
