package service.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDto<T> {

    private HashMap<String,Object> data;
    public ResponseDto(String message) {
        this.data = new HashMap<>();
        this.data.put("info",message);
    }
    public ResponseDto(String parameterName, T data){
        this.data = new HashMap<>();
        this.data.put(parameterName, data);
    }
}
