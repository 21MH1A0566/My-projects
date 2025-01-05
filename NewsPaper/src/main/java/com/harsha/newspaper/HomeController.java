package com.harsha.newspaper;

import java.io.IOException;
import java.util.Base64;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class HomeController {
	@Autowired
	UserRepo repo;
	@RequestMapping("/home")
	public String home(Model model)
	{
		return "home";
	}
	@RequestMapping("/register")
	public String register(Model model)
	{
		UserEntity user=new UserEntity();
		model.addAttribute("user",user);
		return "register";
	}
	@RequestMapping(path = "/registered", method = RequestMethod.POST)
    public String registered(@ModelAttribute("user") UserEntity user,@RequestParam("photo") MultipartFile photoFile,Model model) throws IOException 
	{
		Optional<UserEntity> existingUser = repo.findByEmail(user.email);
		Optional<UserEntity> existingUserr = repo.findByPhone(user.phone);
		if (existingUser.isPresent() || existingUserr.isPresent()) {
            model.addAttribute("message", "USER ALREADY FOUND");
            return "register";
        }
		if(!user.password.equals(user.repassword))
		{
			model.addAttribute("message","PASSWORD MISMATCH");
			return "register";
		}
		 if (!photoFile.isEmpty()) {
		    user.setProfileImage(photoFile.getBytes());
		 }
        repo.save(user);
        model.addAttribute("message","REGISTERED SUCCESSFULLY");
        return "home";
    }
	@RequestMapping("/login")
	public String login(Model model)
	{
		UserEntity user=new UserEntity();
		model.addAttribute("user",user);
		return "login";
	}
	@RequestMapping("/logon")
	public String logon(@ModelAttribute("emailorphone") String emailorphone,@ModelAttribute("password") String password,Model model)
	{
		Optional<UserEntity> correctUser = repo.findByPhone(emailorphone);
		Optional<UserEntity> correctUserr = repo.findByEmail(emailorphone);
		Optional<UserEntity> currentUser=Optional.empty();
		
		if(correctUser.isPresent() || correctUserr.isPresent())
		{
			if(correctUser.isPresent())
			{
				currentUser=correctUser;
			}
			else if(correctUserr.isPresent())
			{
				currentUser=correctUserr;
			}
			if(currentUser.get().password.equals(password))
			{
				model.addAttribute("message","LOGIN SUCCESSFUL");
				UserEntity user = currentUser.get();
				if(user.getProfileImage()!=null)
				{
					String base64Image = Base64.getEncoder().encodeToString(user.getProfileImage());
	                user.setBase64Image(base64Image);
				}
				else
				{
					user.setBase64Image(null);
				}
                model.addAttribute("user",user);
				return "news";
			}
			else 
			{
                model.addAttribute("message", "INVALID PASSWORD");
                return "login";
            }
		}
		else 
		{
            model.addAttribute("message", "USER NOT FOUND");
            return "login";
        }
	}
	@RequestMapping("/delete")
	public String delete(Model model)
	{
		UserEntity user=new UserEntity();
		model.addAttribute("user",user);
		return "delete";
	}
	@RequestMapping("/deleted")
	public String deleted(@ModelAttribute("emailorphone") String emailorphone,@ModelAttribute("password") String password,Model model)
	{
		Optional<UserEntity> correctUser = repo.findByPhone(emailorphone);
		Optional<UserEntity> correctUserr = repo.findByEmail(emailorphone);
		Optional<UserEntity> currentUser=Optional.empty();
		if(correctUser.isPresent() || correctUserr.isPresent())
		{
			if(correctUser.isPresent())
			{
				currentUser=correctUser;
			}
			else if(correctUserr.isPresent())
			{
				currentUser=correctUserr;
			}
			if(currentUser.get().password.equals(password))
			{
				repo.deleteByEmail(currentUser.get().email);
				model.addAttribute("message", "ACCOUNT DELETED SUCCESSFULLY");
				return "home";
			}
			else 
			{
                model.addAttribute("message", "INVALID PASSWORD");
                return "delete";
            }
		}
		else 
		{
            model.addAttribute("message", "USER NOT FOUND");
            return "delete";
        }
	}
	@GetMapping("/news")
    public ResponseEntity<?> getNews(@RequestParam String category) {
        String apiKey = "704d3fd54def4b23b7ca1b76b1082d00";
        String url = "https://newsapi.org/v2/top-headlines?category=" + category + "&apiKey=" + apiKey;

        RestTemplate restTemplate = new RestTemplate();
        try {
            String newsData = restTemplate.getForObject(url, String.class);
            return ResponseEntity.ok(newsData); 
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error fetching news");
        }
    }
}
