package hello.springmvc.basic;

import lombok.Data;

@Data // 아, 이거 쓰지 말랬는데, 가급적
public class HelloData {

  private String username;
  private int age;
}
