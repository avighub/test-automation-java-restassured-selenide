package pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

//Adding not_null for optional field
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder(toBuilder = true)
public class UserAddressPojo {

    private String country;
    private String city;
    private int zip;

}
