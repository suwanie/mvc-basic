package hello.springmvc.basic.response;

import hello.springmvc.basic.HelloData;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@Controller
public class ResponseBodyController {

  @GetMapping("/response-body-string-v1")
  public void responseBodyV1(HttpServletResponse response) throws IOException {
    response.getWriter().write("suwan Writer");
  }

  @GetMapping("/response-body-string-v2")
  public ResponseEntity<String> responseBodyV2(HttpServletResponse response)
    throws IOException {
    return new ResponseEntity<String>("suwan", HttpStatus.OK);
  }

  @GetMapping("/response-body-string-v3")
  public String responseBodyV3(HttpServletResponse response) {
    return "suwan!";
  }

  //  ---------------------여기까진 문자처리-----------------------------

  // ----------------------json 처리-----------------------------------
  @GetMapping("/response-body-json-v1")
  public ResponseEntity<HelloData> responseBodyJsonV1() {
    HelloData data = new HelloData();
    data.setUsername("suwna");
    data.setAge(29);

    return new ResponseEntity<>(data, HttpStatus.OK);
  }

  // reqestbody만 있으면 안되니까 이렇게 제공해준다. 
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  @GetMapping("/response-body-json-v2")
  public HelloData responseBodyJsonV2() {
    HelloData data = new HelloData();
    data.setUsername("suwna");
    data.setAge(29);

    // 상태코드를 바꾸기 위한 어노테이션이 있다.
    return data;
  }
}
