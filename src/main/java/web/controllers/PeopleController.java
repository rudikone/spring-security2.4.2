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
import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("")
public class PeopleController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping("/admin/users")
    public String getAllUsers(Model model) {
        System.out.println(userService.getAllUsers());
        model.addAttribute("users", userService.getAllUsers());

        return "pages/index";
    }

    @GetMapping("/admin/users/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.show(id));
        return "pages/show";
    }

    @GetMapping("/admin/users/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());

        return "pages/new";
    }

    @PostMapping("/admin/users")
    public String create(@ModelAttribute("user") @Valid User user,
                         @RequestParam(value = "ADMIN", required = false) boolean isAdmin,
                         @RequestParam(value = "USER", required = false) boolean isUser,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin/users/new";
        }
        Set<Role> roles = new HashSet<>();

        if (isAdmin) {
            roles.add(roleService.findRoleById(1));
        }
        if (isUser) {
            roles.add(roleService.findRoleById(2));
        }

        user.setRoles(roles);
        userService.save(user);

        return "redirect:/admin/users";
    }

    @GetMapping("/admin/users/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userService.show(id));
        return "pages/edit";
    }

    @PatchMapping("/admin/users/{id}")
    public String update(@ModelAttribute("user") @Valid User user,
                         @PathVariable("id") int id,
                         @RequestParam(value = "ADMIN", required = false) boolean isAdmin,
                         @RequestParam(value = "USER", required = false) boolean isUser,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin/users/edit";
        }

        Set<Role> roles = new HashSet<>();

        if (isAdmin) {
            roles.add(roleService.findRoleById(1));
        }
        if (isUser) {
            roles.add(roleService.findRoleById(2));
        }

        user.setRoles(roles);

        userService.update(id, user);
        return "redirect:/admin/users";
    }

    @DeleteMapping("/admin/users/{id}")
    public String delete(@PathVariable("id") int id) {
        userService.delete(id);
        return "redirect:/admin/users";
    }


    @GetMapping(value = "/user")
    public String getUserPage(Model model, Principal principal) {
String userName = principal.getName();
model.addAttribute("user", userService.getUserByName(userName));
        return "pages/user";
    }
}
