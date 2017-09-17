package fi.haagahelia.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import fi.haagahelia.domain.Student;
import fi.haagahelia.domain.StudentRepository;
@Controller
public class StudentController {

	@Autowired
	private StudentRepository repository;
	
	@RequestMapping(value="/")
    public String watt() {
		return "index.html";
	}
	
	@RequestMapping(value="/studentData")
    public @ResponseBody List<Student> hello() {
        return (List<Student>) repository.findAll();
    }

	@RequestMapping(value="/deleteStudent")
    public String deleteStudent(@RequestParam(name="id", defaultValue="99999") Long id) {
		if ( id != 99999 )
		{
			System.out.println("deleting student" + id);
			repository.delete(id);
		}
		return "/";
    }
	
    @RequestMapping(value = "/createStudent", method = RequestMethod.POST)
    public String save(Student student) {
    	
    	System.out.println(student.toString());
    	if ( student.getFirstName().isEmpty() || student.getLastName().isEmpty() || student.getEmail().isEmpty() )
    		return "redirect:/";
    	else
    		repository.save(student);

        return "redirect:/";
    }   
}
