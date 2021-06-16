package cn.itcast.domin;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(value = {"handler"})
public class Dept {
    private Integer deptno;

    private String dname;

    private String loc;
}
