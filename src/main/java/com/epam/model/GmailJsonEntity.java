package com.epam.model;

import java.util.List;

public record GmailJsonEntity(MessageEntity message, List<UserEntity> users) {
}
