

package com.example.demo;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class HomeController {

    @Autowired
    StudentRepo repo; // Repository for StudentEntity

    @Autowired
    AdminRepo repoo; // Repository for AdminEntity

    // Redirect to home page
    @RequestMapping("/home")
    public String home() {
        return "home";
    }

    // Display admin login page
    @RequestMapping("/admin")
    public String admin(Model model) {
        AdminEntity adminn = new AdminEntity(); // Create a new AdminEntity object
        model.addAttribute("adminn", adminn); // Add to the model for the view
        return "admin";
    }

    // Handle admin login
    @RequestMapping("/login")
    public String login(@ModelAttribute("adminn") AdminEntity adminn, Model model) {
        Optional<AdminEntity> admin = repoo.findByUsername(adminn.getUsername());
        if (admin.isPresent()) {
            AdminEntity adminEntity = admin.get();
            if (adminEntity.getPassword().equals(adminn.getPassword())) {
                List<StudentEntity> data = (List<StudentEntity>) repo.findAll();
                data.forEach(student -> {
                    if (student.getUserPhoto() != null) {
                        // Convert byte array to Base64 string
                  String base64Image = Base64.getEncoder().encodeToString(student.getUserPhoto());
                        student.setBase64Image(base64Image);
                    }
                });
                model.addAttribute("studentsdata", data);
                return "login";
            } else {
                model.addAttribute("message", "Invalid Password");
                return "admin";
            }
        } else {
            model.addAttribute("message", "Admin not found");
            return "admin";
        }
    }

    // Display student registration page
    @RequestMapping("/student")
    public String student(Model model) {
        StudentEntity student = new StudentEntity(); // Create a new StudentEntity object
        model.addAttribute("student", student); // Add to the model for the view
        return "student";
    }

    // Handle student registration
    @RequestMapping(path = "/registered", method = RequestMethod.POST)
    public String registered(
        @RequestParam("name") String name,
        @RequestParam("email") String email,
        @RequestParam("phone") String phone,
        @RequestParam("dob") String dob,
        @RequestParam("tenth") double tenth,
        @RequestParam("inter") int inter,
        @RequestParam("photo") MultipartFile photoFile,
        Model model
    ) {
        try {
            // Check for duplicates
            Optional<StudentEntity> existingStudentPhone = repo.findByPhone(phone);
            Optional<StudentEntity> existingStudentMail = repo.findByEmail(email);

            if (existingStudentPhone.isPresent() || existingStudentMail.isPresent()) {
                model.addAttribute("message", "Phone number or Email already exists.");
                return "student";
            }

            // Create a new student entity
            StudentEntity student = new StudentEntity();
            student.setName(name);
            student.setEmail(email);
            student.setPhone(phone);
            student.setDob(dob);
            student.setTenth(tenth);
            student.setInter(inter);

            // Convert photo to byte array and set it
            if (!photoFile.isEmpty()) {
                student.setUserPhoto(photoFile.getBytes());
            }

            // Save to database
            repo.save(student);

            model.addAttribute("message", "Inserted Successfully");
        } catch (Exception e) {
            model.addAttribute("message", "Error occurred during registration.");
        }
        return "home";
    }

    // Allotment page
    @RequestMapping("/allotment")
    public String allotment() {
        return "allotment";
    }
    @RequestMapping("/students")
    public String getStudents(@RequestParam(defaultValue = "10") int count, Model model) {
    	Pageable pageable = PageRequest.of(0, count, Sort.by(Sort.Order.desc("tenth"), Sort.Order.desc("inter")));
        List<StudentEntity> students = repo.findTopStudents(pageable);
        System.out.println(students);
        students.forEach(student -> {
            if (student.getUserPhoto() != null) {
                // Convert byte array to Base64 string
          String base64Image = Base64.getEncoder().encodeToString(student.getUserPhoto());
                student.setBase64Image(base64Image);
            }
        });
        
        model.addAttribute("students", students);
        return "final";
    }
}
