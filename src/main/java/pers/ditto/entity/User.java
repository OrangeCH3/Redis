package pers.ditto.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @author OrangeCH3
 * @create 2021-06-18 9:36
 */

@Component
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User implements Serializable {

    private String name;
    private int age;

}
