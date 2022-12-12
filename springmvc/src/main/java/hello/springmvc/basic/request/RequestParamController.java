package hello.springmvc.basic.request;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
// @RestController requestParamV2에서 return값이 string이기 때문에
// 이것을 response body에 넣기 위해선 이걸 써도 된다.
@Controller
public class RequestParamController {

  @RequestMapping("/request-param-v1")
  public void requestParamV1(
    HttpServletRequest request,
    HttpServletResponse response
  ) throws IOException {
    String username = request.getParameter("username");
    int age = Integer.parseInt(request.getParameter("age"));
    log.info("username ={}, age ={} ", username, age);

    // 예외처리 해줘야한다.
    response.getWriter().write("suwan");
  }

  // 위에 컨트롤러면서 string이면 view resolver를 찾는다.
  @ResponseBody // return값을 http response 응답 메세지에 넣어서 반환한다. =>restController랑 같다.
  @RequestMapping("/request-param-v2")
  public String requestParamV2(
    @RequestParam("username") String memberName,
    @RequestParam("age") int memberAge
  ) {
    log.info("username ={}, age ={} ", memberName, memberAge);
    return "suwan2";
  }

  @ResponseBody
  @RequestMapping("/request-param-v3")
  public String requestParamV3(
    @RequestParam String username,
    @RequestParam int age
  ) {
    log.info("username ={}, age ={} ", username, age);
    return "suwan3";
  }

  @ResponseBody
  @RequestMapping("/request-param-v4")
  public String requestParamV4(String username, int age) {
    log.info("username ={}, age ={} ", username, age);
    return "suwan4";
  }

  @ResponseBody
  @RequestMapping("/request-param-required")
  public String requestParamRequired(
    // true일때 http요청에 필수적으로 있어야 한다.
    @RequestParam(required = true) String username,
    @RequestParam(required = false) Integer age
  ) {
    log.info("username ={}, age ={} ", username, age);
    return "suwan is required";
  }

  @ResponseBody
  @RequestMapping("/request-param-default")
  public String requestParamDefault(
    // true일때 http요청에 필수적으로 있어야 한다.
    @RequestParam(required = true, defaultValue = "suwan") String username,
    @RequestParam(required = false, defaultValue = "-1") int age
  ) {
    log.info("username ={}, age ={} ", username, age);
    return "suwan is required";
  }

  @ResponseBody
  @RequestMapping("/request-param-map")
  public String requestParamMap(
    // 값이 하나면 걍 map 사용, 만약 값이 두개면
    // ex) ?username=suwan&username=?? => multiValueMap사용
    @RequestParam MultiValueMap<String, Object> paramMap
  ) {
    log.info(
      "username ={}, age ={} ",
      paramMap.get("username"),
      paramMap.get("age")
    );
    return "suwan is Map";
  }
}
