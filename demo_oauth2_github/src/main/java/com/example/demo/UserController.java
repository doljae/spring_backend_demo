package com.example.demo;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
// 이거 쓰면 private final instance 들을 @Autowired 없이 주입받을 수 있음
@RequiredArgsConstructor
public class UserController {

    private final UserVoRepository repository;
    private final ModelMapper modelMapper;
    // localhost:8080
    @GetMapping("/")
    public String goIndex(Model model) {
        model.addAttribute("message", "오예 좋음");
        return "index";
    }

    // localhost:8080/form
    @GetMapping("/form")
    public String goForm(Model model) {
        model.addAttribute("UserVo", new UserVo());
        return "form";
    }

    // localhost:8080/form
    // RedirectAttributes
    // 이거 쓰면 redirect 할때도 데이터 넘길 수 있음, spring에서 지원하는 객체
    @PostMapping("/form")
    public String insertForm(@ModelAttribute @Valid UserVo user,
                             Errors errors,
                             Model model,
                             RedirectAttributes redirectAttributes) {
        if (errors.hasErrors()) {
            model.addAttribute("message", "폼에 입력을 잘못했어요.");
            return "form";
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // 현재 github로 인증받았기 떄문에 원래대로라면 들어가는 타입이 다름
        // spring security 는 default로 Default2Oauth2 로 담김
        DefaultOAuth2User defaultOAuth2User = (DefaultOAuth2User) authentication.getPrincipal();
        String github = defaultOAuth2User.getAttribute("login");

        UserVo existingUser = repository.findByGithub(github);
        if (existingUser != null) {
            //model.addAttribute("message", "이미 등록하셨습니다");
            // 현재 입력으로 들어온 유저 정보를 기존에 존재하는 유저정보에 덮어 씌운다
            Long id = existingUser.getId();
            String githubb = existingUser.getGithub();
            modelMapper.map(user, existingUser);
            existingUser.setId(id);
            existingUser.setGithub(githubb);
            // 기존 유저정보가 최신으로 갱신되었을테니 다시 저장해줌
            repository.save(existingUser);
        } else {
            user.setGithub(github);
            repository.save(user);
        }
        // 이 부분 공부 필요
        redirectAttributes.addAttribute("message", "등록되었습니다");
        //redirectAttributes.addFlashAttribute("message", "등록되었습니다");
        return "redirect:/";
    }

    // localhost:8080/all
    @GetMapping("/all")
    @ResponseBody
    public List<UserVo> all() {
        return repository.findAll();
    }
}
