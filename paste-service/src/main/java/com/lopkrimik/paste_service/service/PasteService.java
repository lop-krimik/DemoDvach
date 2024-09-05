package com.lopkrimik.paste_service.service;

import com.lopkrimik.paste_service.dto.PasteDto;
import com.lopkrimik.paste_service.handler.ResourceNotFoundException;
import com.lopkrimik.paste_service.paste.Access;
import com.lopkrimik.paste_service.paste.Paste;
import com.lopkrimik.paste_service.paste.URL;
import com.lopkrimik.paste_service.repository.PasteRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class PasteService {
    private final PasteRepository pasteRepository;
    private final URL url;
    public void addPaste(PasteDto pasteDto) {
        pasteRepository.save(Paste.builder()
                .title(pasteDto.getTitle())
                .text(pasteDto.getText())
                .url(url.takeUrl("addPaste/"))
                .lifecycle(pasteDto.getLifecycle())
                .email(pasteDto.getEmail())
                .expiredTime(LocalDateTime.now().plus(pasteDto.getLifecycle().getDuration()))
                .access(pasteDto.getAccess())
                .build());
    }

    public PasteDto getPasteByPaste_id (Long paste_id){
        var paste = pasteRepository.getPasteById(paste_id).orElseThrow(
                () -> new ResourceNotFoundException("Paste with id" + paste_id + "not found"));
        return paste != null ?
                PasteDto.builder()
                        .text(paste.getText())
                        .title(paste.getTitle())
                        .url(paste.getUrl())
                        .email(paste.getEmail())
                        .lifecycle(paste.getLifecycle())
                        .expiredTime(LocalDateTime.now().plus(paste.getLifecycle().getDuration()))
                        .access(paste.getAccess())
                        .build() :
                null;
    }

    public void updateText (Long paste_id, String text){
        var paste = pasteRepository.getPasteById(paste_id).orElse(null);
        if (paste!=null){
            paste.setText(text);
            pasteRepository.save(paste);
        }
        else log.info(String.format("Paste with id: %s is not existing",paste_id));
    }

    @Transactional
    public void deletePasteByPaste_id (Long paste_id){
        var paste = pasteRepository.getPasteById(paste_id).orElse(null);
        if (paste!=null)
            pasteRepository.deletePasteById(paste_id);
        else log.info(String.format("Paste with id: %s is not existing",paste_id));
    }

    public List<Paste> getPublicPastes (){
        return pasteRepository.getAllByAccessOrderByLifecycleDesc(Access.PUBLIC);
    }


}
