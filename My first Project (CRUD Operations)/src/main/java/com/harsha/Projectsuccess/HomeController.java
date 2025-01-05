package com.harsha.Projectsuccess;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
	@Autowired
	Studentrepo repo;
	@RequestMapping("/home")
	public String home(Model model)
	{
		model.addAttribute("message","Welcome to home Page");
		return "home";
	}
	@RequestMapping("/insert")
	public String insert(Model model) {
		StudentEntity cseform=new StudentEntity();
		model.addAttribute("cseform", cseform);
		return "insert";
	}
	@RequestMapping(path="/register",method=RequestMethod.POST)
	public String insert(Model model,@ModelAttribute("cseform") StudentEntity cseform)
	{
        Optional<StudentEntity> existingStudent = repo.findById(cseform.getId());
	    if (existingStudent.isPresent()) {
	    	model.addAttribute("message","Primary Key already there.");
	        return "home";
	    }
	    repo.save(cseform);
	    model.addAttribute("message","Inserted Successfully");
	    return "home";
	}
	@RequestMapping("/display")
	public String display(Model model)
	{
		List<StudentEntity> data=(List<StudentEntity>) repo.findAll();
		model.addAttribute("csedata",data);
		return "display";
	}
	@RequestMapping("/search")
	public String search(Model model) {
		List<String> data=new ArrayList<>();
		List<StudentEntity> alldata=(List<StudentEntity>) repo.findAll();
		data.add("id");
		data.add("name");
		data.add("college");
		data.add("branch");
		data.add("city");
		data.add("gender");
		data.add("skills");
		model.addAttribute("finddata",data);
		model.addAttribute("csedata",alldata);
		SearchingEntity searching=new SearchingEntity();
		model.addAttribute("searchthem",searching);
		return "search";
	}
	@RequestMapping(path="/searched",method=RequestMethod.POST)
	public String searched(Model model,@ModelAttribute("searchingform") SearchingEntity searchingform) {
		List<StudentEntity> alldata=(List<StudentEntity>) repo.findAll();
		model.addAttribute("csedata",alldata);
		model.addAttribute("searchthem",searchingform);
		return "searched";
	}
	@RequestMapping("/delete")
	public String delete(Model model) {
		List<String> data=new ArrayList<>();
		List<StudentEntity> alldata=(List<StudentEntity>) repo.findAll();
		data.add("id");
		data.add("name");
		data.add("college");
		data.add("branch");
		data.add("city");
		data.add("gender");
		data.add("skills");
		model.addAttribute("finddata",data);
		model.addAttribute("csedata",alldata);
		SearchingEntity searching=new SearchingEntity();
		model.addAttribute("searchthem",searching);
		return "delete";
	}
	@RequestMapping(path="/deleted", method=RequestMethod.POST)
    public String delete(Model model, @ModelAttribute("searchingform") SearchingEntity searchingform) {
        List<StudentEntity> students = new ArrayList<>();
        switch (searchingform.getSearchedField()) {
            case "id":
                Integer id = Integer.valueOf(searchingform.getSearchedTerm());
                repo.findById(id).ifPresent(students::add);
                break;
            case "name":
                students = repo.findByName(searchingform.getSearchedTerm());
                break;
            case "branch":
                students = repo.findByBranch(searchingform.getSearchedTerm());
                break;
            case "college":
                students = repo.findByCollege(searchingform.getSearchedTerm());
                break;
            case "city":
                students = repo.findByCity(searchingform.getSearchedTerm());
                break;
            case "gender":
                students = repo.findByGender(searchingform.getSearchedTerm());
                break;
            case "skills":
                students = repo.findBySkills(searchingform.getSearchedTerm());
                break;
            default:
                model.addAttribute("message", "Invalid search field");
                return "redirect:/home"; 
        }

        if (!students.isEmpty()) {
            for (StudentEntity student : students) {
                repo.deleteById(student.getId());
            }
            model.addAttribute("message", "Deleted successfully");
        } else {
            model.addAttribute("message", "Welcome to Home Page");
        }
        return "home";
    }
	@RequestMapping("/update")
	public String update(Model model) {
		List<String> data=new ArrayList<>();
		List<StudentEntity> alldata=(List<StudentEntity>) repo.findAll();
		data.add("id");
		data.add("name");
		data.add("college");
		data.add("branch");
		data.add("city");
		data.add("gender");
		data.add("skills");
		model.addAttribute("finddata",data);
		model.addAttribute("csedata",alldata);
		SearchingEntity searching=new SearchingEntity();
		model.addAttribute("searchthem",searching);
		return "update";
	}
	@RequestMapping(path="/updated",method=RequestMethod.POST)
	public String updated(Model model,@ModelAttribute("searchingform") SearchingEntity searchingform) {
		List<StudentEntity> alldata=(List<StudentEntity>) repo.findAll();
		List<String> data=new ArrayList<>();
		data.add("name");
		data.add("college");
		data.add("branch");
		data.add("city");
		data.add("gender");
		data.add("skills");
		model.addAttribute("finddata",data);
		model.addAttribute("csedata",alldata);
		SearchingEntity searching=new SearchingEntity();
		model.addAttribute("searchthem",searching);
		model.addAttribute("searching",searchingform);
		System.out.println(searching.searchedTerm);
		System.out.println(searching.searchedField);
		return "updated";
	}
	@RequestMapping("/updatecompleted")
	public String updatefunc(@RequestParam String id,@RequestParam String updateby, @RequestParam String updatevalue, Model model,@ModelAttribute("searching") SearchingEntity searching) {
		Optional<StudentEntity> studentOptional;
		System.out.println(searching.searchedTerm);
		System.out.println(updateby);
		switch (updateby) {
        case "id":
            studentOptional = repo.findById(id);
            break;
        case "name":
            studentOptional = repo.findByName(searching.searchedTerm).stream().findFirst();
            break;
        case "branch":
            studentOptional = repo.findByBranch(searching.searchedTerm).stream().findFirst();
            break;
        case "college":
            studentOptional = repo.findByCollege(searching.searchedTerm).stream().findFirst();
            break;
        case "city":
            studentOptional = repo.findByCity(searching.searchedTerm).stream().findFirst();
            break;
        case "gender":
            studentOptional = repo.findByGender(searching.searchedTerm).stream().findFirst();
            break;
        case "skills":
            studentOptional = repo.findBySkills(searching.searchedTerm).stream().findFirst();
            break;
        default:
            model.addAttribute("message", "Invalid search field");
            return "redirect:/home";
    }
		System.out.println(searching.searchedTerm);
		System.out.println(searching.searchedField);
		Integer studentId = Integer.parseInt(id);
	    Optional<StudentEntity> temp = repo.findById(studentId);
	    
	    if (temp.isPresent()) {
	    	StudentEntity student = temp.get();

	        if (updateby.equalsIgnoreCase("name")) {
	            student.setName(updatevalue);
	        } else if (updateby.equalsIgnoreCase("branch")) {
	            student.setBranch(updatevalue);
	        } else if (updateby.equalsIgnoreCase("college")) {
	            student.setCollege(updatevalue);
	        } else if (updateby.equalsIgnoreCase("city")) {
	            student.setCity(updatevalue);
	        }else if (updateby.equalsIgnoreCase("gender")) {
	            student.setGender(updatevalue);
	        }
	        else if (updateby.equalsIgnoreCase("skills")) {
	            student.setSkills(updatevalue);
	        }
	        else {
	            model.addAttribute("error", "Invalid field specified for update.");
	            return "home";
	        }
	        System.out.println(student);
	        repo.save(student);
	        model.addAttribute("message", "Student Data updated successfully!");
	    } else {
	        model.addAttribute("error", "Student not found ");
	    }
	    return "home";
	}


}
