package hello.springmvc.basic.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.springmvc.basic.HelloData;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

/* {"username":"suwan", "age":20}
content-type: application/json
 */
@Slf4j
@Controller
public class RequestBodyJsonController {

  // json이니까 있어야 한다?
  private ObjectMapper objectMapper = new ObjectMapper();

  @PostMapping("/request-body-json-v1")
  public void requestBodyJsonV1(
    HttpServletRequest request,
    HttpServletResponse response
  ) throws IOException {
    ServletInputStream inputStream = request.getInputStream();
    String msgBody = StreamUtils.copyToString(
      inputStream,
      StandardCharsets.UTF_8
    );

    log.info("msgBody ={}", msgBody);
    HelloData data = objectMapper.readValue(msgBody, HelloData.class);
    log.info("username={}, age={}", data.getUsername(), data.getAge());

    response.getWriter().write("hihi Json");
  }

  @ResponseBody
  @PostMapping("/request-body-json-v2")
  public String requestBodyJsonV2(@RequestBody String msgBody)
    throws IOException {
    log.info("msgBody ={}", msgBody);
    HelloData data = objectMapper.readValue(msgBody, HelloData.class);
    log.info("username={}, age={}", data.getUsername(), data.getAge());

    return "suwan!";
  }

  @ResponseBody
  @PostMapping("/request-body-json-v3")
  public String requestBodyJsonV3(@RequestBody HelloData data) {
    log.info("msgBody ={}", data);
    log.info("username={}, age={}", data.getUsername(), data.getAge());

    return "suwan hello!";
  }

  @ResponseBody
  @PostMapping("/request-body-json-v4")
  public String requestBodyJsonV4(HttpEntity<HelloData> httpEntity) {
    HelloData hellodata = httpEntity.getBody();
    log.info(
      "username={}, age={}",
      hellodata.getUsername(),
      hellodata.getAge()
    );

    return "suwan Entity!";
  }

  @ResponseBody
  @PostMapping("/request-body-json-v5")
  public HelloData requestBodyJsonV5(@RequestBody HelloData data) {
    log.info("username={}, age={}", data.getUsername(), data.getAge());
    // hellodata라는 객체가 httpMethodConverter에 의해 JSON으로 바뀌고, 이게 http응답 메세지에 박혀 브라우정로 나가게 된다.
    return data;
  }
}
