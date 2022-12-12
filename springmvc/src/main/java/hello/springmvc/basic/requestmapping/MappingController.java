package hello.springmvc.basic.requestmapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MappingController {

  private Logger log = LoggerFactory.getLogger(getClass());

  // 이 url 요청이 오면 아래가 호출된다.
  // 리턴문자를 http바디에 넣는다.
  @RequestMapping("/hello-basic")
  public String helloBasic() {
    log.info("helloBasic");
    return "ok";
  }

  // 이렇게 여러개의 url도 지정 가능
  @RequestMapping({ "/hello-basic", "/hello-suwan" })
  public String helloBasic2() {
    log.info("helloBasic2");
    return "ok";
  }

  // 해당 메소드에만 적용 가능,
  @RequestMapping(value = "/mapping-get-v1", method = RequestMethod.GET)
  public String HttpMethod() {
    log.info("HttpMethod");
    return "ok";
  }

  // 축약형 어노테이션 존재, post도 있고 다 있따.
  @GetMapping(value = "/mapping-get-v2")
  public String mappingGetV2() {
    log.info("mapping-get-v2");
    return "ok";
  }

  // @GetMapping("/mapping/{userId}")
  // public String mappingPath(@PathVariable("userId") String data) {
  //   log.info("mappingPath userId={}", data);
  //   return "ok";
  // }
  @GetMapping("/mapping/{userId}")
  public String mappingPath(@PathVariable String userId) {
    log.info("mappingPath userId={}", userId);
    return "ok";
  }

  // 다중사용
  @GetMapping("/mapping/users/{userId}/orders/{orderId}")
  public String mappingPath(
    @PathVariable String userId,
    @PathVariable Long orderId
  ) {
    log.info("mappingPath userId={}, orderId={}", userId, orderId);
    return "ok";
  }

  // 특정 파라미터 일때만, 잘 사용하지 않음
  @GetMapping(value = "/mapping-param", params = "mode=debug")
  public String mappingParam() {
    log.info("mappingParam");
    return "ok";
  }

  // 특정 헤더를 가졌을 때, 역시 잘 사용하지않음
  @GetMapping(value = "/mapping-header", headers = "mode=debug")
  public String mappingHeader() {
    log.info("mappingHeader");
    return "suwan";
  }

  // contentType에 맞게!! 여기선 Post, json
  // 소비하는 것이기에 consume이라고 한다고 ?
  @PostMapping(
    value = "/mapping-consume",
    consumes = MediaType.APPLICATION_JSON_VALUE
  )
  public String mappingConsumes() {
    log.info("mappingConsumes");
    return "ok";
  }

  // 이건 서버에서 만들어내는 것을 사용하기때문에 produce?
  // header에 accept와 produces의 값이 맞아야 한다.
  @PostMapping(value = "/mapping-produce", produces = MediaType.TEXT_HTML_VALUE)
  public String mappingProduces() {
    log.info("mappingProduces");
    return "ok";
  }
}
