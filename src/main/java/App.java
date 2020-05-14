import objects.*;
import dao.*;
import security.*;
import exception.*;
import org.sql2o.*;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import sun.rmi.runtime.Log;

import static spark.Spark.*;
public class App{
    public static void main(String[] args) {
        staticFileLocation("/public");
        Sql2oBusinessDao businessDao;
        Sql2oReviewDao reviewDao;
        Sql2oUserDao userDao;
        Sql2oCategoryDao categoryDao;
        Connection con;

        userDao = new Sql2oUserDao(db.sql2o);
        businessDao = new Sql2oBusinessDao(db.sql2o);
        reviewDao = new Sql2oReviewDao(db.sql2o);
        categoryDao = new Sql2oCategoryDao(db.sql2o);

        // landing page
        get("/",(request, response) -> {
            Map<String,Object>model = new HashMap<>();
            return new ModelAndView(model,"index.hbs");
        }, new HandlebarsTemplateEngine());
        // signin
        get("/signin", (request, response) -> {
            Map<String,Object>model = new HashMap<>();
            String name = request.queryParams("name");
            String email = request.queryParams("email");
            String phone = request.queryParams("phone");
            String password = request.queryParams("password");
            model.put("name", name);
            model.put("email", email);
            model.put("phone", phone);
            model.put("password", password);
            return new ModelAndView(model,"signin-form.hbs");
        }, new HandlebarsTemplateEngine());
        post("/business", (request, response) -> {
            Map<String,Object>model = new HashMap<>();
            String name = request.queryParams("name");
            String email = request.queryParams("email");
            String phone = request.queryParams("phone");
            String password = request.queryParams("password");
            try{
                String securePassword = HashPassword.HashPassword(password);
                User user = new User(name,email,phone,securePassword);
                userDao.add(user);
            } catch (NoSuchAlgorithmException ex){ }
            return  new ModelAndView(model,"success.hbs");
        }, new HandlebarsTemplateEngine());
        // login
        get("/login",(request, response) -> {
            Map<String,Object>model = new HashMap<>();
            String name = request.queryParams("name");
            String password = request.queryParams("password");
            model.put("name", name);
            model.put("password", password);
            return new ModelAndView(model,"login-form.hbs");
        }, new HandlebarsTemplateEngine());
        post("/business",(request, response) -> {
            Map<String,Object>model = new HashMap<>();
            String name = request.queryParams("name");
            String password = request.queryParams("password");
            try{
                String convertPassword = HashPassword.HashPassword(password);
                Login login = new Login(name, convertPassword);
                User user = userDao.validate(name,convertPassword);
                if(user == null){
                    throw new ErrorException(404,"The user does exist");
                }
                model.put("login",login);
            } catch (NoSuchAlgorithmException ex){}
            return new ModelAndView(model,"success.hbs");
        }, new HandlebarsTemplateEngine());
        // business
        get("/business",(request, response) -> {
            Map<String,Object>model = new HashMap<>();
            model.put("businesses",businessDao.getAll());
            return new ModelAndView(model,"business.hbs");
        }, new HandlebarsTemplateEngine());
        get("/business/form",(request, response) -> {
          Map<String,Object>model = new HashMap<>();
          return new ModelAndView(model, "biz-form.hbs");
        }, new HandlebarsTemplateEngine());
        post("/business/new",(request, response) -> {
            Map<String,Object>model = new HashMap<>();
            String name = request.queryParams("name");
            String owner = request.queryParams("owner");
            String email = request.queryParams("email");
            String phone = request.queryParams("phone");
            int category = Integer.parseInt(request.queryParams("category_id"));
            String website = request.queryParams("website");
            User user = userDao.findOwnerId(owner);
            Business business = new Business(name,owner,email,phone,category,website);
            businessDao.add(business,user.getId());
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());
        // category
        get("/categories",(request, response) -> {
            Map<String,Object>model = new HashMap<>();
            model.put("categories",categoryDao.getAll());
            return new ModelAndView(model, "category.hbs");
        }, new HandlebarsTemplateEngine());
        get("/category/form",(request, response) -> {
            Map<String,Object>model = new HashMap<>();
            return new ModelAndView(model, "category-form.hbs");
        }, new HandlebarsTemplateEngine());
        post("/category/new", (request, response) -> {
            Map<String,Object>model = new HashMap<>();
            String name = request.queryParams("name");
            String description = request.queryParams("description");
            category category = new category(name,description);
            categoryDao.add(category);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());
        // review
        get("/reviews",(request, response) -> {
            Map<String,Object> model = new HashMap<>();
            model.put("reviews",reviewDao.getAll());
            return new ModelAndView(model,"reviews.hbs");
        }, new HandlebarsTemplateEngine());
        get("/reviews/form", (request, response) -> {
           Map<String,Object>model = new HashMap<>();
           return new ModelAndView(model, "review-form.hbs");
        }, new HandlebarsTemplateEngine());
        post("/reviews/new", (request, response) -> {
            Map<String,Object>model = new HashMap<>();
            String writtenBy = request.queryParams("writtenBy");
            String userReview = request.queryParams("review");
            int businessId =Integer.parseInt(request.queryParams("businessId"));
            int rating = Integer.parseInt(request.queryParams("rating"));
            review review = new review(businessId,writtenBy,userReview,rating);
            reviewDao.add(review);
            return new ModelAndView(model,"success.hbs");
        });



    }
}