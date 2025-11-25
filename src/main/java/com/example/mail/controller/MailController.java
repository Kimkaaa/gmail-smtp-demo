package com.example.mail.controller;

import com.example.mail.dto.MailRequest;
import com.example.mail.service.MailService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Controller
@RequestMapping("/mail")
@RequiredArgsConstructor
public class MailController {

    private final MailService mailService;

    @GetMapping("/form/text")
    public String textForm() {
        return "mail-text-form";
    }

    @GetMapping("/form/html")
    public String htmlForm() {
        return "mail-html-form";
    }

    @GetMapping("/form/attachment")
    public String attachmentForm() {
        return "mail-attachment-form";
    }

    /* 텍스트 메일 */
    @PostMapping(value = "/text")
    @ResponseBody
    public String sendTextFromForm(MailRequest req) {
        mailService.sendTextMail(req.getTo(), req.getSubject(), req.getMessage());
        return "텍스트 메일 발송 완료!";
    }

    /* HTML 메일 */
    @PostMapping("/html")
    @ResponseBody
    public String sendHtmlFromForm(
            @RequestParam String to,
            @RequestParam String subject,
            @RequestParam String code) throws MessagingException {

        mailService.sendHtmlMail(to, subject, code);
        return "HTML 메일 발송 완료!";
    }

    /* 첨부파일 메일 */
    @PostMapping("/attachment")
    @ResponseBody
    public String sendAttachmentFromForm(
            @RequestParam("to") String to,
            @RequestParam("subject") String subject,
            @RequestParam("message") String message,
            @RequestPart("file") MultipartFile file) throws Exception {

        File saved = File.createTempFile("upload-", "-" + file.getOriginalFilename());
        file.transferTo(saved);

        mailService.sendMailWithAttachment(to, subject, message, saved);
        return "첨부파일 메일 발송 완료!";
    }
}
