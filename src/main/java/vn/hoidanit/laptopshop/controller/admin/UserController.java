package vn.hoidanit.laptopshop.controller.admin;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.ServletContext;
import jakarta.validation.Valid;
import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.service.UploadService;
import vn.hoidanit.laptopshop.service.UserService;

@Controller
public class UserController {

    private final UserService userService;
    private final UploadService uploadService;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserService userService, UploadService uploadService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.uploadService = uploadService;
        this.passwordEncoder = passwordEncoder;

    }

    @GetMapping("/admin/user/create")
    public String getCreateUserPage(Model model) {
        model.addAttribute("newUser", new User());
        return "admin/user/create";
    }

    @PostMapping("/admin/user/create")
    public String createUser(Model model, @ModelAttribute("newUser") @Valid User user,
            BindingResult newUserBindingResult,
            @RequestParam("avatarUserFile") MultipartFile file) {

        List<FieldError> errors = newUserBindingResult.getFieldErrors();
        for (FieldError error : errors) {
            System.out.println(">>>>" + error.getField() + " - " + error.getDefaultMessage());
        }

        // validate
        if (newUserBindingResult.hasErrors()) {
            return "admin/user/create";
        }
        String avatar = this.uploadService.handleSaveUploadFile(file, "avatar");
        String hashPassword = this.passwordEncoder.encode(user.getPassword());

        user.setAvatar(avatar);
        user.setPassword(hashPassword);

        user.setRole(this.userService.getRoleByName(user.getRole().getName()));
        // save
        this.userService.handleSaveUser(user);
        return "redirect:/admin/user";
    }

    @GetMapping("/admin/user")
    public String getUserPage(Model model) {
        List<User> lstUser = this.userService.getAllUsers();
        model.addAttribute("users", lstUser);
        return "admin/user/user";
    }

    @RequestMapping("/admin/user/{id}")
    public String getDetailUserPage(Model model, @PathVariable long id) {
        User user = this.userService.getUserById(id);
        model.addAttribute("user", user);
        model.addAttribute("id", id);
        return "admin/user/detail-user";
    }

    @RequestMapping("/admin/user/update/{id}")
    public String getUpdateUserPage(Model model, @PathVariable long id) {
        User user = this.userService.getUserById(id);
        String avatarUrl;

        if (user.getAvatar() != null) {
            avatarUrl = "/avatar/"
                    + user.getAvatar();
        } else {
            avatarUrl = "/avatar/"
                    + "avatardefault.png";
        }

        model.addAttribute("user", user);
        model.addAttribute("avatarUrl", avatarUrl);
        return "admin/user/update";
    }

    // @RequestMapping(value = "/admin/user/update", method = RequestMethod.POST)
    @PostMapping("/admin/user/update")
    public String updateUser(Model model, @ModelAttribute("user") User user,
            @RequestParam("avatarFile") MultipartFile file) {
        User currentUser = this.userService.getUserById(user.getId());

        if (currentUser != null) {
            currentUser.setFullName(user.getFullName());
            currentUser.setAddress(user.getAddress());
            currentUser.setPhone(user.getPhone());
            if (file != null) {
                String avatar = this.uploadService.handleSaveUploadFile(file, "avatar");
                currentUser.setAvatar(avatar);
            }
            this.userService.handleSaveUser(currentUser);
        }
        return "redirect:/admin/user";
    }

    // @RequestMapping("/admin/user/delete/{id}")
    @GetMapping("/admin/user/delete/{id}")
    public String getDeleteUserPage(Model model, @PathVariable long id) {
        model.addAttribute("id", id);
        model.addAttribute("user", new User());
        return "admin/user/delete";
    }

    @PostMapping("/admin/user/delete")
    public String deleteUser(Model model, @ModelAttribute("user") User user) {
        this.userService.handleDeleteUser(user.getId());
        return "redirect:/admin/user";
    }
}
