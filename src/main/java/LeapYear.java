import java.util.HashMap;

import spark.ModelAndView; //terminal error
import spark.template.velocity.VelocityTemplateEngine;

import static spark.Spark.*;
import java.util.Map;


public class LeapYear {
  public static void main(String[] args) {
    String layout = "templates/layout.vtl";

    get("/leapyear", (request, response) -> {
      HashMap model = new HashMap();
      model.put("template", "templates/leapYear.vtl");

      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/displayResult", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/displayResult.vtl");

      String userInput = request.queryParams("userInput");
      Integer integerYear = Integer.parseInt(userInput);
      Boolean isLeapYear = isLeapYear(integerYear);

      model.put("isLeapYear", isLeapYear);
      model.put("userInput", request.queryParams("userInput"));
      model.put("year", integerYear);
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());
  }


  public static boolean isLeapYear(Integer year) {
    if ( year % 400 == 0 ) {
      return true;
//        String System.out.println(userInput + " is a leap year!");
    } else if ( year % 100 == 0 ) {
      return false;
//        String System.out.println(userInput + " is not a leap year!");
    } else {
      return year % 4 == 0;
    }
  }
}
