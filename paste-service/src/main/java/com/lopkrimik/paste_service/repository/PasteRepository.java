package com.lopkrimik.paste_service.repository;

import com.lopkrimik.paste_service.dto.PasteDto;
import com.lopkrimik.paste_service.paste.Access;
import com.lopkrimik.paste_service.paste.Paste;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Repository
public interface PasteRepository extends JpaRepository<Paste,Long> {
    Optional<Paste> getPasteById(Long paste_id);
    void deletePasteById (Long paste_id);

    void deleteAllByExpiredTimeIsBefore (LocalDateTime localDateTime);

    List<Paste> getAllByAccessOrderByLifecycleDesc(Access access);
}
