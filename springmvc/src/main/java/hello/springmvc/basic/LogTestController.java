package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class LogTestController {

  // Slf4j라는 어노테이션이 자동으로 추가해줌
  // private final Logger log = LoggerFactory.getLogger(getClass());

  @RequestMapping("/log-test")
  public String logTest() {
    String name = "Spring";

    // 아래는 과거의 유산
    // 얘는 다 뜬다, 운영서버에 다 남기게되면 로그 폭탄을 맞게된다고 한다.
    // System.out.println("name = "+name);

    // log를 찍을 때, 레벨을 정할 수 있다. => 어떤 상태인지
    log.trace("trace log= {}", name);

    // 디버그 할때 => 개발서버
    log.debug("trace log= {}", name);

    // 중요한 정보, 운영시스템에서도 봐야될 때
    log.info("info log= {}", name);

    // 경고
    log.warn("info log= {}", name);

    // 에러 => 빨리 해치워야 할 것,
    log.error("info log= {}", name);

    return "ok";
  }
}
