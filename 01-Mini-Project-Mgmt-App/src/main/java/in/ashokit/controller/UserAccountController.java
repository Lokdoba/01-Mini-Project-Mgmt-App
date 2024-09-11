package in.ashokit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import in.ashokit.entities.UserAccount;
import in.ashokit.service.UserAccountService;

@Controller
public class UserAccountController {

    @Autowired
    private UserAccountService service;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("user", new UserAccount());
        return "index";
    }

    @PostMapping("/save-user")
    public String handleSubmitBtn(@ModelAttribute("user") UserAccount user, Model model) {
        String msg = service.saveOrUpdateUserAcc(user);
        model.addAttribute("msg", msg);
        // Clear form after submission
        model.addAttribute("user", new UserAccount()); // Reset form data
        return "index";  // Return to the same form but with an empty object
    }


    @GetMapping("/view-users")
    public String getUsers(Model model) {
        List<UserAccount> userList = service.getAllUserAccount();
        model.addAttribute("users", userList);
        return "view-users";
    }
 // Edit User
    @GetMapping("/edit")
    public String editUser(@RequestParam("id") Integer id, Model model) {
        UserAccount userAcc = service.getUserAccount(id);
        model.addAttribute("user", userAcc);  // Pre-populate form with user data
        return "index";  // Redirect to the same form but filled with user's data
    }

    // Delete User
    @GetMapping("/delete")
    public String deleteUser(@RequestParam("id") Integer id, Model model) {
        boolean deleted = service.deleteUserAccount(id);
        if (deleted) {
            model.addAttribute("msg", "User deleted successfully");
        } else {
            model.addAttribute("msg", "User not found");
        }
        return "redirect:/view-users";  // Redirect back to the users list
    }
    
    @GetMapping("/toggle-status")
    public String toggleUserStatus(@RequestParam("id") Integer id, Model model) {
        UserAccount userAcc = service.getUserAccount(id);
        if (userAcc != null) {
            String currentStatus = userAcc.getActiveSw();
            String newStatus = currentStatus.equals("Y") ? "N" : "Y";  // Toggle between 'Y' and 'N'
            service.updateUserAccStatus(id, newStatus);
        }
        return "redirect:/view-users";  // Redirect back to the user list page
    }


    
    
}
