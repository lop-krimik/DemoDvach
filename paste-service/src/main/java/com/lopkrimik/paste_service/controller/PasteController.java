package com.lopkrimik.paste_service.controller;

import com.domain.basedomain.events.PasteEvent;
import com.lopkrimik.paste_service.dto.PasteDto;
import com.lopkrimik.paste_service.kafka.PasteProducer;
import com.lopkrimik.paste_service.paste.Access;
import com.lopkrimik.paste_service.paste.Paste;
import com.lopkrimik.paste_service.paste.URL;
import com.lopkrimik.paste_service.service.PasteService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@RestController
@RequestMapping("api/v1/paste")
@AllArgsConstructor
public class PasteController {
    private final PasteProducer pasteProducer;
    private final PasteService pasteService;
    private final URL url;


    @PostMapping("/addPaste")
    public ResponseEntity<String> addPaste(@RequestBody PasteDto pasteDto){
        pasteService.addPaste(pasteDto);
        PasteEvent pasteEvent = new PasteEvent();
        pasteEvent.setTitle(pasteDto.getTitle());
        pasteEvent.setText(pasteDto.getText());
        pasteEvent.setEmail(pasteDto.getEmail());
        pasteProducer.sendMessage(pasteEvent);
        return ResponseEntity.ok("Message has been sent");

    }

    @GetMapping("/getPasteByPaste_id/{paste_id}")
    public PasteDto getPaste (@PathVariable Long paste_id){
        var paste = pasteService.getPasteByPaste_id(paste_id);
        return paste != null ?
                PasteDto.builder()
                        .lifecycle(paste.getLifecycle())
                        .url(url.takeUrl("addPaste/"))
                        .title(paste.getTitle())
                        .text(paste.getText())
                        .email(paste.getEmail())
                        .access(paste.getAccess())
                        .expiredTime(LocalDateTime.now().plus(paste.getLifecycle().getDuration()))
        .build():
                null;
    }

    @PatchMapping("/updatePasteText/paste_id/{paste_id}")
    public void updateText (@PathVariable Long paste_id, @RequestBody String text){
        pasteService.updateText(paste_id,text);
    }

    @DeleteMapping("/{paste_id}")
    public void deletePaste (@PathVariable Long paste_id){
        pasteService.deletePasteByPaste_id(paste_id);
    }

    @GetMapping("/getPublicPastes")
    List<Paste> getPublicPastes (){
        return pasteService.getPublicPastes();
    }

}
