package com.notifier.backend.data;

import java.time.LocalDateTime;

public record Message(String content, LocalDateTime createdTime) {

}
