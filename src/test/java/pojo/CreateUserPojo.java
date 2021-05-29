package pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

//Adding not_null for optional field
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder(toBuilder = true)
public class CreateUserPojo {

    private String name;
    private String job;

    private UserAddressPojo address;

}

