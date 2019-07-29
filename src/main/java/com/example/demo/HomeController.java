package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
public class HomeController {
    @Autowired
    MessageRepository messageRepository;
   @Autowired
   private UserRepository userRepository;
   @Autowired
   private UserService userService;

       @RequestMapping("/")
       public String Homepage(Model model){
           return "homepage";
       }
       @RequestMapping("/login")
       public String loginpage(){
           return "login";
       }

//       @RequestMapping("/logout")
//       public String logout(HttpServletRequest, HttpServletResponse response){
//        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
//     if(auth!=null){
//         new SecurityContextLogoutHandler().logout(request, response, auth);
//     }
//     return "redirect:/login?logout"; //it redirects to login page again
//
//       }
       @RequestMapping("/register")
       public String showRegistration(Model model){
           model.addAttribute("user", new User());
           return "register";
       }
       @PostMapping("/register")
       public String processRegistration(@Valid @ModelAttribute("user") User user,BindingResult result ,Model model){
           model.addAttribute("user", user);
           if(result.hasErrors()){
               return "register";
           }else{
               userService.saveUser(user);
           }
           model.addAttribute("message","Account created!");
           return "Homepage"; //Homepage
       }
    @RequestMapping("/messagelist")
    public String listMessages(Model model ){
        model.addAttribute("messages",messageRepository.findAll());

        return "messagelist";
    }
    @GetMapping("/add")
    public String messageForm(Model model){
        model.addAttribute("message", new Message());
        return "messageform";
    }

    @PostMapping("/process")
    public String processForm(@Valid Message message, BindingResult result){
        if(result.hasErrors()){
            return "messageform";
        }
        messageRepository.save(message);
        return "redirect:/";
    }
    @RequestMapping("/detail/{id}")
    public String showMessage(@PathVariable("id") long id,Model model){
        model.addAttribute("message",messageRepository.findById(id).get());
        return "show";
    }
    @RequestMapping("/update/{id}")
    public String updateMessage(@PathVariable("id") long id, Model model){
        model.addAttribute("message", messageRepository.findById(id).get());
        return "messageform";
    }
    @RequestMapping("/delete/{id}")
    public String delMessage(@PathVariable("id") long id){
        messageRepository.deleteById(id);
        return "redirect:/";
    }
}
