package com.clone.studyolle.study;

import com.clone.studyolle.account.Account;
import com.clone.studyolle.account.CurrentAccount;
import com.clone.studyolle.study.form.StudyDescriptionForm;
import com.clone.studyolle.tag.Tag;
import com.clone.studyolle.tag.TagForm;
import com.clone.studyolle.tag.TagRepository;
import com.clone.studyolle.tag.TagService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/study/{path}/settings")
@RequiredArgsConstructor
public class StudySettingsController {

    private final ModelMapper modelMapper;
    private final StudyService studyService;
    private final TagRepository tagRepository;
    private final ObjectMapper objectMapper;
    private final TagService tagService;

    @GetMapping("/description")
    public String viewStudySetting(@CurrentAccount Account account, @PathVariable String path, Model model) {
        Study study = studyService.getStudyToUpdate(account, path);
        model.addAttribute(account);
        model.addAttribute(study);
        model.addAttribute(modelMapper.map(study, StudyDescriptionForm.class));
        return "study/settings/description";
    }

    @PostMapping("/description")
    public String updateStudyInfo(@CurrentAccount Account account,
                                  @PathVariable String path,
                                  @Valid StudyDescriptionForm studyDescriptionForm,
                                  Errors errors,
                                  Model model, RedirectAttributes attributes) {
        Study study = studyService.getStudyToUpdate(account, path);
        if (errors.hasErrors()) {
            model.addAttribute(account);
            model.addAttribute(study);
            return "study/settings/description";
        }

        studyService.updateStudyDescription(study, studyDescriptionForm);
        attributes.addFlashAttribute("message", "스터디 소개를 수정했습니다.");
        return "redirect:/study/" + study.getEncodedPath() + "/settings/description";
    }

    @GetMapping("/banner")
    public String studyImageForm(@CurrentAccount Account account,
                                 @PathVariable String path,
                                 Model model) {
        Study study = studyService.getStudyToUpdate(account, path);
        model.addAttribute(account);
        model.addAttribute(study);
        return "study/settings/banner";
    }

    @PostMapping("/banner/enable")
    public String enableStudyBanner(@CurrentAccount Account account,
                                    @PathVariable String path) {
        Study study = studyService.getStudyToUpdate(account, path);
        studyService.enableStudyBanner(study);
        return "redirect:/study/" + study.getEncodedPath() + "/settings/banner";
    }

    @PostMapping("/banner/disable")
    public String disableStudyBanner(@CurrentAccount Account account,
                                     @PathVariable String path) {
        Study study = studyService.getStudyToUpdate(account, path);
        studyService.disableStudyBanner(study);
        return "redirect:/study/" + study.getEncodedPath() + "/settings/banner";
    }

    @GetMapping("/tags")
    public String studyTagsForm(@CurrentAccount Account account,
                                @PathVariable String path,
                                Model model)
            throws JsonProcessingException {
        Study study = studyService.getStudyToUpdate(account, path);
        model.addAttribute(account);
        model.addAttribute(study);

        model.addAttribute("tags", study.getTags().stream()
                .map(Tag::getTitle).collect(Collectors.toList()));
        List<String> allTagTitles = tagRepository.findAll().stream()
                .map(Tag::getTitle).collect(Collectors.toList());
        model.addAttribute("whitelist", objectMapper.writeValueAsString(allTagTitles));
        return "study/settings/tags";
    }

    @PostMapping("/tags/add")
    @ResponseBody
    public ResponseEntity addTag(@CurrentAccount Account account,
                                 @PathVariable String path,
                                 @RequestBody TagForm tagForm) {
        Study study = studyService.getStudyToUpdate(account, path);
        Tag tag = tagService.findOrCreateNew(tagForm.getTagTitle());
        studyService.addTag(study, tag);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/tags/remove")
    @ResponseBody
    public ResponseEntity removeTag(@CurrentAccount Account account,
                                    @PathVariable String path,
                                    @RequestBody TagForm tagForm) {
        Study study = studyService.getStudyToUpdate(account, path);
        Tag tag = tagRepository.findByTitle(tagForm.getTagTitle());
        if (tag == null) {
            return ResponseEntity.badRequest().build();
        }

        studyService.removeTag(study, tag);
        return ResponseEntity.ok().build();
    }
}

























