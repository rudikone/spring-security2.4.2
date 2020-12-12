package web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import web.models.User;
import web.models.Role;
import web.service.RoleService;
import web.service.UserService;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("")
public class PeopleController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping("/people")
    public String index(Model model) {
        model.addAttribute("people", userService.index());
        return "people/index";
    }

    @GetMapping("/people/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", userService.show(id));
        return "people/show";
    }

    @GetMapping("/people/new")
    public String newPerson(Model model) {
        model.addAttribute("person", new User());

        return "people/new";
    }

    @PostMapping("/people")
    public String create(@ModelAttribute("person") @Valid User person,
                         @RequestParam(value = "ADMIN", required = false) boolean isAdmin,
                         @RequestParam(value = "USER", required = false) boolean isUser,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "people/new";
        }

        Set<Role> roles = new HashSet<>();

        if (isAdmin) {
            roles.add(roleService.findRoleById(1));
        }
        if (isUser) {
            roles.add(roleService.findRoleById(2));
        }

        person.setRoles(roles);

        userService.save(person);
        return "redirect:/people";
    }

    @GetMapping("/people/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("person", userService.show(id));
        return "people/edit";
    }

    @PatchMapping("/people/{id}")
    public String update(@ModelAttribute("person") @Valid User person,
                         @PathVariable("id") int id,
                         @RequestParam(value = "ADMIN", required = false) boolean isAdmin,
                         @RequestParam(value = "USER", required = false) boolean isUser,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "people/edit";
        }

        Set<Role> roles = new HashSet<>();

        if (isAdmin) {
            roles.add(roleService.findRoleById(1));
        }
        if (isUser) {
            roles.add(roleService.findRoleById(2));
        }

        person.setRoles(roles);

        userService.update(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/people/{id}")
    public String delete(@PathVariable("id") int id) {
        userService.delete(id);
        return "redirect:/people";
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "people/login";
    }

    @GetMapping(value = "/user")
    public String getUserPage() {
        return "people/user";
    }

    @GetMapping(value = "/")
    public String getHomePage() {
        return "people/homepage";
    }
}
