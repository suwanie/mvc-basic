package hello.springmvc.basic.response;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ResponseViewController {

  @RequestMapping("/response-view-v1")
  public ModelAndView responseViewV1() {
    // response/hello는 경로

    ModelAndView mav = new ModelAndView("response/hello")
      .addObject("data", "suwan!");

    return mav;
  }

  // string으로 반환, 여기서 만약 requestBody를 하면 return이 http 응답코드로 나간다, view를 안찾음
  @RequestMapping("/response-view-v2")
  public String responseViewV2(Model model) {
    model.addAttribute("data", "dduwan");

    // @Controller + string을 반환하면 = view의 논리적 이름
    return "response/hello";
  }

  // 절대 권장하지 않는 하나의 방법
  // 경로의 이름으로 넣을경우 return을 안해줘도 됨,
  @RequestMapping("/response/hello")
  public void responseViewV3(Model model) {
    model.addAttribute("data", "dduwan");
  }
}
