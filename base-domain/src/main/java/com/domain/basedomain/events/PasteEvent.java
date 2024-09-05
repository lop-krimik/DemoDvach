package com.domain.basedomain.events;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PasteEvent {
    private String title;
    private String text;
    private String email;
}
